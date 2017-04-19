package com.lvfulo.service.wxservice;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lvfulo.entity.WxPayResultInfo;

/**
 * 微信支付
 * @author lyp
 *
 */
@Service
public interface WxScanPayService {
	
	/**
	 * 调用微信统一下单接口，生成预支付交易单
	 * 用于公众号内支付
	 */
	public String CreatePreOrder(String out_trade_no, int total_fee, String time_start, String time_expire, String openid, ReturnValue rtv );	
	
	/**
	 * 调用微信统一下单接口，生成预支付交易单
	 * 用于微信APP支付
	 * type 用于区分是大山还是雀巢app 0-雀巢，1-大山
	 */
	public String CreateAppPreOrder(String out_trade_no, int total_fee, String time_start, String time_expire, String type, ReturnValue rtv );	
	
	/**
	 * 调用微信支付接口，查看支付状态
	 */
	public void WxOrderQuery(String out_trade_no);
	
	/**
	 * 公众号支付获取预支付流水号
	 * 扫码支付获取获取二维码code_url
	 */
	public WxPayResultInfo UnifiedOrder(String out_trade_no, int total_fee, String time_start, String time_expire,String trade_type, String openid );	
	
	
	
	public String getQrCode();
	
}