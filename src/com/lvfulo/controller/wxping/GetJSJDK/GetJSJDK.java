package com.lvfulo.controller.wxping.GetJSJDK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.lvfulo.utils.Consts;

public class GetJSJDK {

	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			System.out.println(bs.toString());
			jsonObject = JSONObject.fromObject(bs.toString());

		} catch (IOException e) {
			System.out.println(e);
		}
		return jsonObject;
	}

	/**
	 * 方法名：getWxConfig</br> 详述：获取微信的配置信息 </br> 开发人员：souvc </br> 创建时间：2016-1-5
	 * </br>
	 * 
	 * @param request
	 * @return 说明返回值含义
	 * @throws 说明发生此异常的条件
	 */
	public static Map<String, Object> getWxConfig(String pathurl,HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();

		String appId = Consts.JH_APPID; // 必填，公众号的唯一标识
		String secret = Consts.JH_APPIDSECRET;

//	  String requestUrl = request.getRequestURL().toString();
//	  System.out.println(requestUrl);
		String requestUrl = pathurl;
		String access_token = "";
		String jsapi_ticket = "";
		String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
		String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + secret;

		JSONObject json = GetJSJDK.httpRequest(url, "GET", null);

		if (json != null) {
			// 要注意，access_token需要缓存
			access_token = json.getString("access_token");

			url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
			json = GetJSJDK.httpRequest(url, "GET", null);
			if (json != null) {
				jsapi_ticket = json.getString("ticket");
			}
		}
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		String sign = "jsapi_ticket=" + jsapi_ticket + 
				"&noncestr=" + nonceStr + 
				"&timestamp=" + timestamp + 
				"&url=" + requestUrl;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(sign.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("appId", appId);
		ret.put("timestamp", timestamp);
		ret.put("nonceStr", nonceStr);
		ret.put("signature", signature);
		return ret;
	}

	/**
	 * 方法名：byteToHex</br> 详述：字符串加密辅助方法 </br> 开发人员：souvc </br> 创建时间：2016-1-5 </br>
	 * 
	 * @param hash
	 * @return 说明返回值含义
	 * @throws 说明发生此异常的条件
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;

	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
//		map = getWxConfig("http://mp.weixin.qq.com?params=value");
		System.out.println(map.toString());
	}
}