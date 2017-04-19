package com.lvfulo.service.wxservice.impl;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import com.lvfulo.entity.WxTokenModel;
import com.lvfulo.entity.WxUserInfo;
import com.lvfulo.service.wxservice.WxAuthService;
import com.lvfulo.utils.ToolUtils;

@Service
public class WxAuthServiceImpl implements WxAuthService{

	public String GetWxCode(String appId, String redirectUrl, String state) {
		String code = "";				 
		//微信用户授权url，scope为snsapi_userinfo
		code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId 
				+ "&redirect_uri=" + ToolUtils.Encode(redirectUrl) + "&response_type=code&scope=snsapi_userinfo&"
				+ "state="+ToolUtils.Encode(state)+"#wechat_redirect";	 
		return code;
			
	}	
	
	public String GetWxCodeFree(String appId, String redirectUrl, String state){
		String code = "";				 
		//微信用户授权url，scope为snsapi_base
		code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId 
				+ "&redirect_uri=" + ToolUtils.Encode(redirectUrl) + "&response_type=code&scope=snsapi_base&"
				+ "state="+ToolUtils.Encode(state)+"#wechat_redirect";	 
		return code;
	}
	
	public WxTokenModel GetAccessTokenByCode(String appId, String appSecret, String code) {
		
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret
				+ "&code=" + code + "&grant_type=authorization_code";
		
		String jsonStr = ToolUtils.GetHttpRequest(url);		
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);	
		
		WxTokenModel  wxTokenModel =  new WxTokenModel();
		wxTokenModel.setAccessToken(jsonObject.getString("access_token"));
		wxTokenModel.setExpireIn(jsonObject.getString("expires_in"));
		wxTokenModel.setRefreshToken(jsonObject.getString("refresh_token"));
		wxTokenModel.setOpenId(jsonObject.getString("openid"));
		wxTokenModel.setScope(jsonObject.getString("scope"));
		//wxTokenModel = (WxTokenModel)JSONObject.toBean(jsonObject,WxTokenModel.class);
		
		return wxTokenModel;
		
	}	

	public WxUserInfo GetWxUserInfo(String accessToken, String openId) {
		  
		  String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
		  String jsonStr = ToolUtils.GetHttpRequest(url);
		  JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		  
		  WxUserInfo wxUserInfo = new WxUserInfo();
		  wxUserInfo.setOpenId(jsonObject.getString("openid"));
		  wxUserInfo.setNickname(jsonObject.getString("nickname"));
		  wxUserInfo.setSex(jsonObject.getString("sex"));
		  wxUserInfo.setLanguage(jsonObject.getString("language"));
		  wxUserInfo.setCity(jsonObject.getString("city"));
		  wxUserInfo.setProvince(jsonObject.getString("province"));
		  wxUserInfo.setCountry(jsonObject.getString("country"));
		  wxUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));	  
		  System.out.println(wxUserInfo);
		  
		  return wxUserInfo;
	}
	
}
	