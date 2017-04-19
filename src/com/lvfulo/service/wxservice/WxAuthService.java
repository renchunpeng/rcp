package com.lvfulo.service.wxservice;

import org.springframework.stereotype.Service;

import com.lvfulo.entity.WxTokenModel;
import com.lvfulo.entity.WxUserInfo;

/**
 * 微信用户授权
 * @author lyp
 *
 */
@Service
public interface WxAuthService {
	
	/**
	 * 获取微信code
	 * @param appId
	 * @param redirectUrl
	 * @return
	 */
	public String GetWxCode(String appId, String redirectUrl, String state);
	
	/**
	 * 获取微信code,自动获取，无需用户授权
	 * @param appId
	 * @param redirectUrl
	 * @return
	 */
	public String GetWxCodeFree(String appId, String redirectUrl, String state);
	
	/**
	 * 根据code获取accesstoken等信息
	 * @return
	 */
	public WxTokenModel GetAccessTokenByCode(String appId, String appSecret, String code);
	
	
	/**
	 * 根据accessToken和openId拉取用户信息
	 * @return
	 */
	public WxUserInfo GetWxUserInfo(String accessToken, String openId);
	
	
}