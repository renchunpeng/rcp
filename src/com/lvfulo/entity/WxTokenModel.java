package com.lvfulo.entity;

/**
 * @Description: 通过code获取access_token请求成功的实体
 * @author lyp
 */
public class WxTokenModel {
	
    // 接口调用凭证
    public String accessToken;

    // access_token接口调用凭证超时时间，单位（秒）
    public String expireIn;

    // 用户刷新access_token
    public String refreshToken;

    // 授权用户唯一标识
    public String openId;

    // 用户授权的作用域，使用逗号（,）分隔
    public String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(String expireIn) {
		this.expireIn = expireIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
    
	
}