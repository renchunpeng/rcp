package com.lvfulo.controller.wxping;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.lvfulo.utils.Consts;

import net.sf.json.JSONObject;

public class GetAccessToken{
	
	public static String getAccess_token() {

		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Consts.JH_APPID + "&secret=" + Consts.JH_APPIDSECRET;
		String accessToken = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);
			accessToken = demoJson.getString("access_token");
			System.out.println(accessToken);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
}