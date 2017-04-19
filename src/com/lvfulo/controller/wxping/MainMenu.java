package com.lvfulo.controller.wxping;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MainMenu {

	public static void main(String[] args) throws Exception {
		
		//获取页面url
		PageRoute pageRoute = new PageRoute();
		
		//花生壳ip
		String ipString = WxMenuConsts.HSKIP;

		ViewButton vbt1 = new ViewButton();
		vbt1.setUrl(ipString + pageRoute.getPageone_1());
		vbt1.setName("好山好水");
		vbt1.setType("view");
		
		ViewButton vbt2 = new ViewButton();
		vbt2.setUrl(ipString + pageRoute.getPageone_2());
		vbt2.setName("品牌故事");
		vbt2.setType("view");
		
		ViewButton vbt3 = new ViewButton();
		vbt3.setUrl(ipString + pageRoute.getPageone_3());
		vbt3.setName("可追溯");
		vbt3.setType("view");
		
		ViewButton vbt4 = new ViewButton();
		vbt4.setUrl(ipString + pageRoute.getPageone_4());
		vbt4.setName("检查报告");
		vbt4.setType("view");


		JSONArray sub_button = new JSONArray();
		sub_button.add(vbt1);
		sub_button.add(vbt2);
		sub_button.add(vbt3);
		sub_button.add(vbt4);
		
		ViewButton vbt5 = new ViewButton();
		vbt5.setUrl(ipString + pageRoute.getPagetwo());
		vbt5.setName("品牌故事");
		vbt5.setType("view");
		
		ViewButton vbt6 = new ViewButton();
		vbt6.setUrl(ipString + pageRoute.getPagethree());
		vbt6.setName("进入商城");
		vbt6.setType("view");

		JSONObject buttonOne = new JSONObject();
		buttonOne.put("name", "品牌推介");
		buttonOne.put("sub_button", sub_button);

		JSONArray button = new JSONArray();
		button.add(buttonOne);
		button.add(vbt5);
		button.add(vbt6);

		JSONObject menujson = new JSONObject();
		menujson.put("button", button);
		System.out.println(menujson);
		// 这里为请求接口的url +号后面的是token，这里就不做过多对token获取的方法解释
		String message = createMenu(menujson.toString(), GetAccessToken.getAccess_token());
		System.out.println(message);

	}

	public static String createMenu(String params, String accessToken) {
		String menu = params;
		// 此处改为自己想要的结构体，替换即可
		String access_token = accessToken;
		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			java.io.OutputStream os = http.getOutputStream();
			os.write(menu.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "返回信息" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "createMenu 失败";
	}

	/**
	 * 删除当前Menu
	 * 
	 * @Title: deleteMenu
	 * @Description: 删除当前Menu
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String deleteMenu() {
		String access_token = GetAccessToken.getAccess_token();
		String action = "https://api.weixin.qq.com/cgi-bin/menu/delete? access_token=" + access_token;
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			OutputStream os = (OutputStream) http.getOutputStream();
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			return "deleteMenu返回信息:" + message;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "deleteMenu 失败";
	}
}