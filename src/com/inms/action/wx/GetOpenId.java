package com.inms.action.wx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.inms.action.tools.GetAccessToken;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;



public class GetOpenId {
	/**
	 * 获取 关注者列表
	 * 
	 * @Title: getOpenids
	 * @Description: TODO(获取 关注者列表)
	 * @param @return 参数
	 * @return JSONArray 返回类型
	 * @throws
	 */
	public static JSONArray getOpenids() {
		JSONArray strings = null;
		try {
			long begintime = System.currentTimeMillis();
			String urlstr = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + GetAccessToken.getAccess_token() + "&next_openid=" + "";
			URL url = new URL(urlstr);

			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
//			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			System.out.println(bs.toString());
			JSONObject jsonObject = JSONObject.fromObject(bs.toString());
		  strings = JSONArray.fromObject(JSONObject.fromObject(jsonObject.get("data").toString()).get("openid"));
			System.out.println(strings.toString());
			
			System.out.println("总共执行时间为：" + (System.currentTimeMillis() - begintime) + "毫秒");
		} catch (IOException e) {
			System.out.println(e);
		}
		return strings;
	}
	
	public static void getuserinfo(JSONArray jsonArray) {
		try {
			String urlstr = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + GetAccessToken.getAccess_token() + "&openid="+ jsonArray.get(0) +"&lang=zh_CN";
			URL url = new URL(urlstr);

			HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
//			urlcon.connect(); // 获取连接
			InputStream is = urlcon.getInputStream();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			System.out.println(bs.toString());
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	

	public static void main(String[] args){
		JSONArray jjArray = getOpenids();
		getuserinfo(jjArray);
	}
}