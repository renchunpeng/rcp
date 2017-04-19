package com.lvfulo.controller.ord;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ctm.CtmBase;
import com.lflweb.entity.ord.OrdBaseMain;
import com.lflweb.entity.ord.OrdSubdetail;
import com.lvfulo.controller.wxping.WxMenuConsts;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.ord.OrdDao;
import com.lvfulo.entity.WxTokenModel;
import com.lvfulo.service.ord.OrdService;
import com.lvfulo.service.wxservice.WxAuthService;
import com.lvfulo.service.wxservice.WxScanPayService;
import com.lvfulo.utils.Arith;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;

@Controller
@RequestMapping("/wx")
public class WxController {
	@Autowired
	private AccountDao accountDao;

	@Autowired
	private OrdDao orderDao;

	@Autowired
	private OrdService orderservice;

	@Autowired
	private WxScanPayService wx;

	@Autowired
	private WxAuthService wxAuthService;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	@RequestMapping(value = "/wxPayAuth/{custid}/{orderid}")
	public String wxAuth(@PathVariable("custid") String custid, @PathVariable("orderid") String orderid, HttpServletRequest request, HttpServletResponse response) {

		// TODO 先从数据库或者配置文件中读取出appid和secret以及accesstoken等信息
		String authUrl = "";
		String appId = Consts.JH_APPID;
		String appSecret = Consts.JH_APP_SECRET;
		String redirectUrl = WxMenuConsts.HSKIP;
		String state = "";
		String accessToken = "";
		String expireDate = ""; // accessToken过期时间
		String code = "";

		// openid = "o_GFaw0o8zE491y0kFy1kj5s7mro";

		// 用户同意授权后，能获取到code
		code = request.getParameter("code");
		if (code == null) {
			code = "";
		}

		if (code.equals("")) {
			// 发起授权(第一次微信握手)
			authUrl = wxAuthService.GetWxCodeFree(appId, redirectUrl + "wx/wxPayAuth/" + custid + "/" + orderid, "index-wx.html#/payment/01/" + orderid);
			System.out.println(authUrl);
			try {
				response.sendRedirect(authUrl);
			} catch (IOException e) {
				System.out.println("重定向到微信服务器出错！");
			}
		} else {
			// 获取微信的accesstoken,openid(第二次微信握手)
			WxTokenModel wxTokenCode = new WxTokenModel();
			wxTokenCode = wxAuthService.GetAccessTokenByCode(appId, appSecret, code);
			accessToken = wxTokenCode.getAccessToken();
			String openid = wxTokenCode.getOpenId();

			System.out.println("获取到雀巢公众号对应的openid:" + openid);

			// region 将openid存入数据库
			CtmBase ctmBase = new CtmBase();
			ctmBase.getSearch().setSearch(" a.custid='" + custid + "'");
			List<CtmBase> userList = new ArrayList<CtmBase>();
			userList = accountDao.SearchUser(ctmBase);
			if (userList.size() > 0) {
				ctmBase = userList.get(0);
				ctmBase.setTestopenid(openid);
			}
			ctmBase.getDeal().setAction(DataAction.Modify.getAction());
			accountDao.saveUser(ctmBase);
			// endregion

			// http://localhost:8989/#/payMent/{custId}/{orderId}
			String jumpUrl = redirectUrl + "#/payMent/" + custid + "/" + orderid + "";// 这个要修改地址
			System.out.println("开始重定向到确认付款的页面！");
			try {
				response.sendRedirect(jumpUrl);
			} catch (Exception e) {
				System.out.println("重定向到付款页面出错！");
			}

		}
		return null;

	}

	@RequestMapping(value = "/GetPreOrderID/{custid}/{orderid}")
	@ResponseBody
	public String GetPreOrderID(@PathVariable("custid") String custid, @PathVariable("orderid") String orderid, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("提交的订单号为：" + orderid);
		JSONObject result = new JSONObject();
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
			result.put("msg", "订单中含有已经被使用的优惠券！");
			result.put("success", false);
			return result.toString();
		}
		// endregion

		this.getRtv().SetValues(false, "", "null", false);

		// String openid = "o_GFaw_usFRK0uex_DImMV6Jij-s";
		String openid = "";

		/* 获取用户的openid */

		// 第一步：根据custid查询用户在金禾送水的openid
		System.out.println("===支付前传值-用户custid=====" + custid);
		CtmBase ctmBase = new CtmBase();
		ctmBase.getSearch().setSearch(" a.custid='" + custid + "'");
		List<CtmBase> userList = new ArrayList<CtmBase>();
		userList = accountDao.SearchUser(ctmBase);
		if (userList.size() > 0) {
			openid = userList.get(0).getOpenid();
		}

		System.out.println("===有没有openid=====" + openid);

		// 第二步：判断openid是否已存在
		if (openid == null || openid.equals("")) {
			System.out.println("===openid不存在=====");
			result.put("msg", "01");
			result.put("success", true);
		} else {
			System.out.println("===openid已存在=====" + openid);

			// TODO 微信支付开始和结束时间
			String time_start = "";
			String time_expire = "";
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			time_start = f.format(date);

			long currentTime = System.currentTimeMillis();
			currentTime += 5 * 60 * 1000;
			date = new Date(currentTime);
			time_expire = f.format(date);

			// 从数据库里读出该订单的实际金额
			OrdBaseMain item = new OrdBaseMain();
			item.getSearch().setSearch(" a.orderid = '" + orderid + "'");
			item.getSearch().setStart(0);
			item.getSearch().setEnd(1);
			List<OrdBaseMain> items = orderDao.searchOrdBaseMain(item);
			System.out.println("订单号：" + orderid);
			System.out.println("微信将要支付金额：" + items.get(0).getRealprice());
			// 读取完成

			// 计算正确需要支付的金额
			BigDecimal b1 = new BigDecimal(String.valueOf(items.get(0).getRealprice()));
			BigDecimal b2 = new BigDecimal(String.valueOf(100));
			int x = (int) b1.multiply(b2).doubleValue();

			x = 1;// 实际环境中直接把这个删除
			wx.CreatePreOrder(orderid, x, time_start, time_expire, openid, this.getRtv());

			// 获取订单的详细信息，用于展示在支付页面
			OrdSubdetail itemdetail = new OrdSubdetail();
			itemdetail.getSearch().setSearch("a.orderid = '" + orderid + "'");
			List<OrdSubdetail> sons = orderservice.searchOrdSubdetails(itemdetail, this.getRtv());// 获取订单明细

			List<OrdBaseMain> father = items;// 获取订单基础表

			Map<String, String> map = new HashMap<String, String>();
			map.put("sons", MyTools.OutLists(sons, true));
			map.put("father", MyTools.OutLists(father, true));
			JSONObject orderifo = JSONObject.fromObject(map.toString());

			// ----------------------------------------------------------

			JSONObject resultJSON = JSONObject.fromObject(this.getRtv());

			result.put("msg", "02");

			result.put("orderifo", orderifo);

			result.put("data", resultJSON.get("msg"));
			result.put("success", resultJSON.get("success"));

		}
		System.out.println("订单支付页面获取的数据" + result);

		return result.toString();

	}

}