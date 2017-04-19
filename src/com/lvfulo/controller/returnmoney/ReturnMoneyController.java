package com.lvfulo.controller.returnmoney;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lflweb.entity.ord.OrdReturn;
import com.lvfulo.controller.returnmoney.wxreturn.RefundUtil;
import com.lvfulo.dao.ord.OrdDao;
import com.lvfulo.entity.gpersist.ReturnValue;
import com.lvfulo.utils.ToolUtils;

@Controller
public class ReturnMoneyController {
	@Autowired
	private OrdDao ordDao;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	@RequestMapping(value = "/returnmoney/{returnid}")
	@ResponseBody
	public ReturnValue DealOrderReturn(@PathVariable("returnid") String returnid) {
		this.getRtv().SetValues(false, "", "null", false);
		try {
			OrdReturn item = new OrdReturn();
			item.getSearch().setSearch(" a.returnid = '" + returnid + "'");
			List<OrdReturn> items = ordDao.SearchOrdReturn(item);
			OrdReturn oreturn = items.get(0);
			if (oreturn.getReturnprice() == 0) {
				rtv.setMsg("退款订单金额不能为0!");
			} else if (ToolUtils.StringIsEmpty(oreturn.getAppid())) {
				rtv.setMsg("此订单商户号为空!");
			} else {
				// 支付宝支付
				if ((oreturn != null) && oreturn.getPaytype().equals("02")) {
					// String zfbresult = Alipay.alipayRefundRequest(oreturn.getOrderid(),
					// oreturn.getAppid(), oreturn.getReturnprice());
					// JSONObject jsonObj = JSONObject.fromObject(zfbresult);
					// String resultcode =
					// jsonObj.getJSONObject("alipay_trade_refund_response").get("msg").toString();
					// if(resultcode.replace("\"", "").equals("Business Failed")){
					// rtv.setMsg(jsonObj.getJSONObject("alipay_trade_refund_response").get("sub_msg").toString().replace("\"",
					// ""));
					// return;
					// }
					// else{
					// oreturn.setPaysysid(jsonObj.getJSONObject("alipay_trade_refund_response").get("trade_no").toString().replace("\"",
					// ""));
					// }
				}
				// 微信支付
				else if ((oreturn != null) && oreturn.getPaytype().equals("01")) {
					String wxresult = RefundUtil.wechatRefund(oreturn.getOrderid(), oreturn.getReturnid(), oreturn.getOrderprice(), oreturn.getReturnprice(), oreturn.getAppid());
					JSONObject jsonObj = ToolUtils.xml2JSON(wxresult);
					if (jsonObj.get("result_code").toString().equals("FAIL")) {
						rtv.setMsg(jsonObj.get("err_code_des").toString());
					} else {
						oreturn.setPaysysid(jsonObj.get("refund_id").toString());
						rtv.setSuccess(true);
					}

				}
			}
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
		}
		return rtv;
	}
}