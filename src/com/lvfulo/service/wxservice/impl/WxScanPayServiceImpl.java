package com.lvfulo.service.wxservice.impl;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lvfulo.entity.WxOrderResultInfo;
import com.lvfulo.entity.WxPayResultInfo;
import com.lvfulo.service.wxservice.WxScanPayService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.ToolUtils;
import com.lvfulo.utils.WxUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Service
public class WxScanPayServiceImpl implements WxScanPayService {

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午4:59:39
	 * @描述: 这个是web端调取微信支付的方法---微信支付也掉了这里的方法
	 * @param countyid
	 * @param rtv
	 * @return
	 */
	public WxPayResultInfo UnifiedOrder(String out_trade_no, int total_fee, String time_start, String time_expire, String trade_type, String openid) {
		System.out.println("这里显示的是微信支付传入的数据：" + total_fee);
		String appid = Consts.JH_APPID; // 公众号ID
		String mch_id = Consts.JH_MCHID; // 商户号
		String device_info = "WEB"; // 设备号，默认为WEB
		String nonce_str = ToolUtils.getRandomString(16); // 16位随机字符串
		String sign = ""; // 签名
		String body = "饮用水等多件商品(测试)"; // 商品描述
		if (out_trade_no.substring(0, 2).equals("IN")) {
			body = "发票邮寄费用(测试)";
		}
		String detail = ""; // 商品详情（暂时未用）
		String attach = "test test"; // 附件数据
		String fee_type = "CNY"; // 货币类型，默认为人民币
		String spbill_create_ip = "127.0.0.1"; // 终端IP,
																						// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		String goods_tag = ""; // 商品标记，暂时未用
		String notify_url = Consts.WX_RETURN;
		// String trade_type = "JSAPI"; //交易类型 ，公众号支付
		// trade_type = "NATIVE"; //交易类型 ，公众号支付
		String product_id = ""; // 商品ID(暂时未用)
		String limit_pay = ""; // 指定支付方式（暂时未用）

		String connStr = "appid=" + appid + "&attach=" + attach + "&body=" + body + "&device_info=" + device_info + "&fee_type=" + fee_type + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str
				+ "&notify_url=" + notify_url;
		if (openid != null && !openid.equals("")) {
			connStr += "&openid=" + openid;
		}
		connStr += "&out_trade_no=" + out_trade_no

		+ "&spbill_create_ip=" + spbill_create_ip + "&time_expire=" + time_expire + "&time_start=" + time_start + "&total_fee=" + total_fee + "&trade_type=" + trade_type + "&key=" + Consts.SIGN_KEY;

		System.out.println("========connstr=====" + connStr);

		sign = ToolUtils.GetMD5(connStr);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("device_info", device_info);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("sign", sign);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("fee_type", fee_type);
		packageParams.put("total_fee", String.valueOf(total_fee));
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("time_start", time_start);
		packageParams.put("time_expire", time_expire);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);

		if (openid != null && !openid.equals("")) {
			packageParams.put("openid", openid);
		}

		// 参数组合成xml
		String paramXml = WxUtils.getRequestXml(packageParams);
		System.out.println("==========paramXml=========" + paramXml);

		// 统一下单接口
		String resultXml = WxUtils.getReponseXml(Consts.WX_UNIFIED_ORDER_URL, paramXml);

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WxPayResultInfo.class);
		WxPayResultInfo wxPayResultInfo = (WxPayResultInfo) xstream.fromXML(resultXml);
		System.out.println("wxPayResultInfo=" + resultXml);

		return wxPayResultInfo;

	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午4:59:39
	 * @描述: 这个是微信端调取微信支付的方法
	 * @param countyid
	 * @param rtv
	 * @return
	 */
	public String CreatePreOrder(String out_trade_no, int total_fee, String time_start, String time_expire, String openid, ReturnValue rtv) {

		WxPayResultInfo wxPayResultInfo = UnifiedOrder(out_trade_no, total_fee, time_start, time_expire, "JSAPI", openid);

		// 返回微信支付所需数据
		// 生成签名
		long timestamp = (new Date().getTime()) / 1000;

		String paysign = "appId=" + Consts.JH_APPID + "&nonceStr=" + Consts.JH_MCHID + "&package=" + "prepay_id=" + wxPayResultInfo.getPrepay_id() + "&signType=" + "MD5" + "&timeStamp=" + timestamp
				+ "&key=" + Consts.SIGN_KEY;

		String paySign = ToolUtils.GetMD5(paysign);

		JSONObject jObject = new JSONObject();
		jObject.put("appId", Consts.JH_APPID);
		jObject.put("nonceStr", Consts.JH_MCHID);
		jObject.put("package", "prepay_id=" + wxPayResultInfo.getPrepay_id());
		jObject.put("signType", "MD5");
		jObject.put("timeStamp", String.valueOf(timestamp));
		jObject.put("paySign", paySign);

		System.out.println(jObject.toString());
		rtv.setMsg(jObject.toString());
		rtv.setSuccess(true);

		return null;

	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午4:59:39
	 * @描述: 这个是app调取微信支付的方法
	 * @param countyid
	 * @param rtv
	 * @return
	 */
	public String CreateAppPreOrder(String out_trade_no, int total_fee, String time_start, String time_expire, String type, ReturnValue rtv) {

		String appid = ""; // 公众号ID
		String mch_id = ""; // 商户号
		if (type.equals("0")) {
			appid = Consts.QC_OPEN_APPID; // 公众号ID
			mch_id = Consts.QC_APP_MCHID; // 商户号
		} else if (type.equals("1")) {
			appid = Consts.DS_OPEN_APPID; // 公众号ID
			mch_id = Consts.DS_APP_MCHID; // 商户号
		}
		String device_info = "WEB"; // 设备号，默认为WEB
		String nonce_str = ToolUtils.getRandomString(16); // 16位随机字符串
		String sign = ""; // 签名
		String body = "饮用水等多件商品(测试)"; // 商品描述
		if (out_trade_no.substring(0, 2).equals("IN")) {
			body = "发票邮寄费用(测试)";
		}
		String detail = ""; // 商品详情（暂时未用）
		String attach = "test test"; // 附件数据
		String fee_type = "CNY"; // 货币类型，默认为人民币
		String spbill_create_ip = "127.0.0.1"; // 终端IP,
																						// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		String goods_tag = ""; // 商品标记，暂时未用
		String notify_url = Consts.WX_RETURN;
		// String trade_type = "JSAPI"; //交易类型 ，公众号支付
		String trade_type = "APP"; // 交易类型 ，公众号支付
		String product_id = ""; // 商品ID(暂时未用)
		String limit_pay = ""; // 指定支付方式（暂时未用）

		String connStr = "appid=" + appid + "&attach=" + attach + "&body=" + body + "&device_info=" + device_info + "&fee_type=" + fee_type + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str
				+ "&notify_url=" + notify_url + "&out_trade_no=" + out_trade_no + "&spbill_create_ip=" + spbill_create_ip + "&time_expire=" + time_expire + "&time_start=" + time_start + "&total_fee="
				+ total_fee + "&trade_type=" + trade_type + "&key=" + Consts.SIGN_KEY;

		sign = ToolUtils.GetMD5(connStr);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("device_info", device_info);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("sign", sign);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("fee_type", fee_type);
		packageParams.put("total_fee", String.valueOf(total_fee));
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("time_start", time_start);
		packageParams.put("time_expire", time_expire);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);

		// 参数组合成xml
		String paramXml = WxUtils.getRequestXml(packageParams);
		System.out.println("==========paramXml=========" + paramXml);

		// 统一下单接口
		String resultXml = WxUtils.getReponseXml(Consts.WX_UNIFIED_ORDER_URL, paramXml);

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WxPayResultInfo.class);
		WxPayResultInfo wxPayResultInfo = (WxPayResultInfo) xstream.fromXML(resultXml);
		System.out.println("wxPayResultInfo=" + resultXml);

		// 返回微信APP支付所需数据
		// 生成签名
		long timestamp = (new Date().getTime()) / 1000;
		String paysign = "appid=" + appid + "&noncestr=" + nonce_str + "&package=Sign=WXPay" + "&partnerid=" + mch_id + "&prepayid=" + wxPayResultInfo.getPrepay_id() + "&timestamp=" + timestamp + "&key="
				+ Consts.SIGN_KEY;

		String paySign = ToolUtils.GetMD5(paysign);

		System.out.println("=====wxPayResultInfo.getPrepay_id() ====" + wxPayResultInfo.getPrepay_id());
		System.out.println("=====nonce_str ====" + nonce_str);
		System.out.println("=====timestamp ====" + timestamp);
		System.out.println("=====paySign ====" + paySign);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("appid", appid);
		jsonObject.put("mchid", mch_id);
		jsonObject.put("prepayid", wxPayResultInfo.getPrepay_id());
		jsonObject.put("noncestr", nonce_str);
		jsonObject.put("timestamp", timestamp);
		jsonObject.put("paySign", paySign);
		rtv.setBean(true);
		rtv.setData(jsonObject.toString());
		rtv.setSuccess(true);

		return null;
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午4:59:39
	 * @描述: 这个应该是当是萍姐写的测试方法，现在没用了
	 * @param countyid
	 * @param rtv
	 * @return
	 */
	public void WxOrderQuery(String out_trade_no) {

		String appid = Consts.JH_APPID; // 公众号ID
		String mch_id = Consts.JH_MCHID; // 商户号
		String nonce_str = ToolUtils.getRandomString(16); // 16位随机字符串
		String sign = ""; // 签名

		// 生成签名
		String connStr = "appid=" + appid + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str + "&out_trade_no=" + out_trade_no + "&key=" + Consts.SIGN_KEY;

		sign = ToolUtils.GetMD5(connStr);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("sign", sign);

		// 参数组合成xml
		String paramXml = WxUtils.getRequestXml(packageParams);
		System.out.println("==========paramXml=========" + paramXml);

		// 查询订单接口
		String resultXml = WxUtils.getReponseXml(Consts.WX_ORDER_QUERY_URL, paramXml);

		// 解析返回结果
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WxOrderResultInfo.class);
		WxOrderResultInfo wxOrderResultInfo = (WxOrderResultInfo) xstream.fromXML(resultXml);
		System.out.println("wxOrderResultInfo=" + wxOrderResultInfo.getReturn_code());

	}

	public String getQrCode() {
		String appid = Consts.JH_APPID; // 公众号ID
		String mch_id = Consts.JH_MCHID; // 商户号
		String device_info = "WEB"; // 设备号，默认为WEB
		String nonce_str = ToolUtils.getRandomString(16); // 16位随机字符串
		String sign = ""; // 签名
		String body = "雀巢测试"; // 商品描述
		String detail = ""; // 商品详情（暂时未用）
		String attach = "金禾代售"; // 附件数据
		String fee_type = "CNY"; // 货币类型，默认为人民币
		String spbill_create_ip = "127.0.0.1"; // 终端IP,
																						// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		String goods_tag = ""; // 商品标记，暂时未用
		String notify_url = "http://www.weixin.qq.com/wxpay/pay.php";
		// String trade_type = "JSAPI"; //交易类型 ，公众号支付
		String trade_type = "JSAPI"; // 交易类型 ，公众号支付
		String product_id = ""; // 商品ID(暂时未用)
		String limit_pay = ""; // 指定支付方式（暂时未用）
		String openid = "o_GFawwpc-N6I74d111v5xRfXNjI";

		String connStr = "appid=" + appid + "&attach=" + attach + "&body=" + body + "&device_info=" + device_info + "&fee_type=" + fee_type + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str
				+ "&notify_url=" + notify_url + "&openid=" + openid + "&out_trade_no=" + "459889912" + "&spbill_create_ip=" + spbill_create_ip + "&time_expire=" + "20161029192500" + "&time_start="
				+ "20161029172500" + "&total_fee=" + 1 + "&trade_type=" + trade_type + "&key=" + Consts.SIGN_KEY;

		sign = ToolUtils.GetMD5(connStr);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("device_info", device_info);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("sign", sign);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", "459889912");
		packageParams.put("fee_type", fee_type);
		packageParams.put("total_fee", "1");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("time_start", "20161029172500");
		packageParams.put("time_expire", "20161029192500");
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openid);

		// 参数组合成xml
		String paramXml = WxUtils.getRequestXml(packageParams);
		System.out.println("==========paramXml=========" + paramXml);

		// 统一下单接口
		String resultXml = WxUtils.getReponseXml(Consts.WX_UNIFIED_ORDER_URL, paramXml);

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WxPayResultInfo.class);
		WxPayResultInfo wxPayResultInfo = (WxPayResultInfo) xstream.fromXML(resultXml);
		System.out.println("wxPayResultInfo=" + wxPayResultInfo.getCode_url());
		return wxPayResultInfo.getCode_url();

	}

	public static void main(String[] args) {
		String appid = Consts.QC_OPEN_APPID; // 公众号ID
		String mch_id = Consts.QC_APP_MCHID; // 商户号
		String device_info = "WEB"; // 设备号，默认为WEB
		String nonce_str = ToolUtils.getRandomString(16); // 16位随机字符串
		String sign = ""; // 签名
		String body = "雀巢测试"; // 商品描述
		String detail = ""; // 商品详情（暂时未用）
		String attach = "金禾代售"; // 附件数据
		String fee_type = "CNY"; // 货币类型，默认为人民币
		String spbill_create_ip = "127.0.0.1"; // 终端IP,
																						// APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
		String goods_tag = ""; // 商品标记，暂时未用
		String notify_url = Consts.WX_RETURN;
		// String trade_type = "JSAPI"; //交易类型 ，公众号支付
		String trade_type = "APP"; // 交易类型 ，公众号支付
		String product_id = ""; // 商品ID(暂时未用)
		String limit_pay = ""; // 指定支付方式（暂时未用）
		String openid = "";
		String out_trade_no = String.valueOf((new Date()).getTime());

		String connStr = "appid=" + appid + "&attach=" + attach + "&body=" + body + "&device_info=" + device_info + "&fee_type=" + fee_type + "&mch_id=" + mch_id + "&nonce_str=" + nonce_str
				+ "&notify_url=" + notify_url + "&out_trade_no=" + out_trade_no + "&spbill_create_ip=" + spbill_create_ip + "&time_expire=" + "20161102240000" + "&time_start=" + "20161102234000"
				+ "&total_fee=" + 1 + "&trade_type=" + trade_type + "&key=" + Consts.SIGN_KEY;

		sign = ToolUtils.GetMD5(connStr);

		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("device_info", device_info);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("sign", sign);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("fee_type", fee_type);
		packageParams.put("total_fee", "1");
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("time_start", "20161102234000");
		packageParams.put("time_expire", "20161102240000");
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);

		// 参数组合成xml
		String paramXml = WxUtils.getRequestXml(packageParams);
		System.out.println("==========paramXml=========" + paramXml);

		// 统一下单接口
		String resultXml = WxUtils.getReponseXml(Consts.WX_UNIFIED_ORDER_URL, paramXml);

		XStream xstream = new XStream(new DomDriver());
		xstream.alias("xml", WxPayResultInfo.class);
		WxPayResultInfo wxPayResultInfo = (WxPayResultInfo) xstream.fromXML(resultXml);
		System.out.println("wxPayResultInfo=" + resultXml);

		// 返回微信APP支付所需数据
		// 生成签名
		long timestamp = (new Date().getTime()) / 1000;
		String paysign = "appid=" + Consts.QC_OPEN_APPID + "&noncestr=" + nonce_str + "&package=Sign=WXPay" + "&partnerid=" + Consts.QC_APP_MCHID + "&prepayid=" + wxPayResultInfo.getPrepay_id()
				+ "&timestamp=" + timestamp + "&key=" + Consts.SIGN_KEY;

		String paySign = ToolUtils.GetMD5(paysign);

		System.out.println("=====wxPayResultInfo.getPrepay_id() ====" + wxPayResultInfo.getPrepay_id());
		System.out.println("=====nonce_str ====" + nonce_str);
		System.out.println("=====timestamp ====" + timestamp);
		System.out.println("=====paySign ====" + paySign);

	}

}
