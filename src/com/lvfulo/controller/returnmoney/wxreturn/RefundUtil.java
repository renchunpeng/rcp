package com.lvfulo.controller.returnmoney.wxreturn;

import java.util.SortedMap;
import java.util.TreeMap;






import com.gpersist.utils.ToolUtils;
import com.lvfulo.utils.Consts;
public class RefundUtil {
	
	/**
	 * 退款函数，该方法可以对曾经部分退款的订单进行再次退款
	 * @param out_trade_no 商户订单号
	 * @param total_fee1 退款对应的订单的总金额（以“元”为单位）
	 * @param refund_fee1 计划退款的金额（以“元”为单位）
	 * @return
	 * @throws Exception 
	 */
	public static String wechatRefund1(String out_trade_no,String out_refund_no,double total_fee1,double refund_fee1, String app_id) throws Exception{
	  String refundResult = "";
		int total_fee = (int) (total_fee1*100);//订单的总金额,以分为单位（填错了貌似提示：同一个out_refund_no退款金额要一致）
		int refund_fee = (int) (refund_fee1*100);;// 退款金额，以分为单位（填错了貌似提示：同一个out_refund_no退款金额要一致）
		String nonce_str = ToolUtils.getRandomString(16);// 随机字符串
		//微信公众平台文档：“基本配置”--》“开发者ID”
		//微信公众平台文档：“基本配置”--》“开发者ID”
		String appsecret = app_id;
		//商户号
		//微信公众平台文档：“微信支付”--》“商户信息”--》“商户号”，将该值赋值给partner
		String mch_id = Consts.JH_MCHID;
		String op_user_id = Consts.JH_MCHID;//就是MCHID
		
		//region 如果有多个账户需要区分 这里不需要
//		if (!ToolUtils.StringIsEmpty(app_id) && app_id.endsWith("wx77839b105d6b3221")){
//		  mch_id = "1401092202";
//		  op_user_id = "1401092202";
//		}else if (!ToolUtils.StringIsEmpty(app_id) && app_id.endsWith("wx793589bff5d401cd")){
//		  mch_id = "1399211002";
//      op_user_id = "1399211002";
//		}else if (!ToolUtils.StringIsEmpty(app_id) && app_id.endsWith("wx173183ea84a24afd")){
//		  mch_id = "1401565202";
//      op_user_id = "1401565202";
//		}
		//endregion
		
		//微信公众平台："微信支付"--》“商户信息”--》“微信支付商户平台”（登录）--》“API安全”--》“API密钥”--“设置密钥”（设置之后的那个值就是partnerkey，32位）
		String partnerkey = Consts.SIGN_KEY;
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", app_id);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", total_fee+"");
		packageParams.put("refund_fee", refund_fee+"");
		packageParams.put("op_user_id", op_user_id);
	
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(app_id, appsecret, partnerkey);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + 
				"<appid>" + app_id + "</appid>" + 
				"<mch_id>" + mch_id + "</mch_id>" + 
				"<nonce_str>" + nonce_str + "</nonce_str>" + 
				"<sign><![CDATA[" + sign + "]]></sign>"	+ 
				"<out_trade_no>" + out_trade_no + "</out_trade_no>"	+ 
				"<out_refund_no>" + out_refund_no + "</out_refund_no>" + 
				"<total_fee>" + total_fee + "</total_fee>" + 
				"<refund_fee>" + refund_fee + "</refund_fee>" + 
				"<op_user_id>" + op_user_id + "</op_user_id>" + 
				"</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
		  refundResult = ClientCustomSSL.doRefund(createOrderURL, app_id, xml);
		  System.out.print(refundResult);
		} catch (Exception e) {
		  refundResult = ClientCustomSSL.doRefund(createOrderURL, app_id, xml);;
		}
		return refundResult;
	}
	
	/**
	 * 该方法默认全额退款，但如果该订单曾经退款一部分，那么就不可使用该方法
	 * @param out_trade_no 商户订单号
	 * @param total_fee1 总的退款金额（以“元”为单位）
	 * @return 
	 * @throws Exception 
	 */
	public static String wechatRefund(String out_trade_no, String out_refund_no, double total_fee1, double refund_fee1, String app_id) throws Exception{
	  
	  String result = "";
		
	  result = wechatRefund1(out_trade_no, out_refund_no, total_fee1, refund_fee1, app_id);
		
		return result;
		
	}
}
