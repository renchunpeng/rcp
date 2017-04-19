package com.lvfulo.controller.ord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ord.OrdBaseMain;
import com.lvfulo.controller.ord.alipay.config.AlipayConfig;
import com.lvfulo.controller.ord.alipay.util.AlipaySubmit;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.log.LogDao;
import com.lvfulo.dao.ord.OrdDao;

@Controller
@RequestMapping("/zfb")
public class ZfbController {
	@Autowired
	private LogDao logDao;

	@Autowired
	private OrdDao orderDao;
	
	@Autowired
	private AccountDao accountDao;

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午5:25:50
	 * @描述: web端支付宝支付
	 * @param orderid
	 * @param APPID
	 * @param RSA_PRIVATE
	 * @param rtv
	 * @return
	 */
	@RequestMapping(value = "/zfbpay/{orderid}")
	@ResponseBody
	public ModelAndView getQRCode(@PathVariable("orderid") String orderid, HttpServletResponse request, HttpServletResponse response) {
		boolean isuse = false;
		// region 判断订单关联的优惠券是否已经被使用
		// 先根据orderid取出所有预支付订单绑定优惠券
		ActPreOrderCoupon preitem = new ActPreOrderCoupon();
		preitem.getSearch().setSearch("a.orderid = '" + orderid + "'");
		List<ActPreOrderCoupon> prelists = accountDao.SearchActPreOrderCoupon(preitem);

		// 然后去用户绑定优惠券中将涉及的优惠券都绑定订单号，标注是否使用和使用日期,顺便计算出使用的优惠券一共优惠了多少钱
		for (ActPreOrderCoupon actPreOrderCoupon : prelists) {
			ActUserCoupon actuseritem = new ActUserCoupon();
			actuseritem.getSearch().setSearch(" a.bdcouponid = '" + actPreOrderCoupon.getBdcouponid() + "'");
			List<ActUserCoupon> actuserlists = accountDao.SearchActUserCoupon(actuseritem);
			if (actuserlists.get(0).getIsuse()) {
				isuse = true;
			}
		}
		if (isuse) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("/eshop/error.html");
			return mv;
		}
		// endregion
		// 从数据库里读出该订单的实际金额
		OrdBaseMain item = new OrdBaseMain();
		item.getSearch().setSearch(" a.orderid = '" + orderid + "'");
		item.getSearch().setStart(0);
		item.getSearch().setEnd(1);
		List<OrdBaseMain> items = orderDao.searchOrdBaseMain(item);
		System.out.println("订单号：" + orderid);
		System.out.println("支付宝将要支付金额：" + items.get(0).getRealprice());
		// 读取完成

		String x = String.valueOf(items.get(0).getRealprice());
		System.out.println("订单号：" + orderid);
	  x = "0.01";// 实际情况中把这个删除就行

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(orderid);

		// 订单名称，必填
		String subject = new String("测试订单");

		// 付款金额，必填
		String total_fee = new String(x);

		// 商品描述，可空
		String body = new String("商品测试");

		// 超时时间
		String it_b_pay = "5m";

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("it_b_pay", it_b_pay);
		// 其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
		// 如sParaTemp.put("参数名","参数值");

		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/eshop/zfbpay.jsp");
		mv.addObject("date", sHtmlText);
		return mv;
	}
}