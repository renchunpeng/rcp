package com.inms.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.entity.TreeItem;
import com.entity.people;
import com.gpersist.utils.DBUtils;

public class TestAction {

	public void test1() {
		try {
			people rcp = new people();
			rcp.setAge(24);
			rcp.setName("rcp");

			people wq = new people();
			wq.setAge(23);
			wq.setName("wq");

			List<people> testList = new ArrayList<people>();
			Set<people> testset = new HashSet<people>();
			testList.add(rcp);
			testList.add(wq);

			testset.add(rcp);
			testset.add(wq);
			testset.add(rcp);
			// System.out.println(testList.get(1).getAge());
			// for (people p1 : testset) {
			// System.out.println(p1.getAge());
			// }

			Map<String, String> testmap = new HashMap<String, String>();
			testmap.put("rcp", "男");
			testmap.put("wq", "女");

			String sex = testmap.get("rcp");
			System.out.println(sex);

			// JsonConfig config = new JsonConfig();
			JSONArray JsonObject = JSONArray.fromObject(testList);

			ServletActionContext.getResponse().getWriter().write(JsonObject.toString());
			// ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			// ServletActionContext.getResponse().getWriter().write(testmap.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test2() {
		List<TreeItem> lists = new ArrayList<TreeItem>();
		for (int i = 0; i < 10; i++) {
			TreeItem tiItem = new TreeItem();
			tiItem.setText(String.valueOf(i));
			tiItem.setId(String.valueOf(i));
			lists.add(tiItem);
		}
		TreeItem t6 = new TreeItem();
		t6.setText(String.valueOf("6"));
		t6.setLeaf(true);
		lists.get(6).getChildren().add(t6);

		JSONArray JsonObject = JSONArray.fromObject(lists);
		System.out.println(JsonObject.toString());
		try {
			ServletActionContext.getResponse().getWriter().write(JsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void test3() {
		System.out.println(1);
		String alerttitle = ServletActionContext.getRequest().getParameter("alerttitle");
		System.out.println(alerttitle);
	}

	public String SearchTown() {
		SqlSession session = DBUtils.getFactory();
		String insertsql = "select * from T_Bas_Town";

		PreparedStatement ps;
		try {
			ps = session.getConnection().prepareStatement(insertsql);

			ResultSet rest = ps.executeQuery();
			while (rest.next()) {

				System.out.println(rest.getString("TownName"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

		return null;

	}

	// public static void main(String[] args) {
	// TestAction testaaaAction = new TestAction();
	// // testaaaAction.test1();
	// testaaaAction.SearchTown();
	//
	// }

	public void testwx() {
		System.out.println("威信进来了");

	}

	/**
	 * 作者：rcp 时间：2016/9/28
	 */
	public String gettoken() {
		// 此处改为自己想要的结构体，替换即可
		String action = "http://www.chinapesticide.gov.cn/myquery/rest/login?u=shinterface&p=b18z1010";
		String message = "";
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			message = new String(jsonBytes, "UTF-8");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		message = message.replace("{", "");
		message = message.replace("}", "");
		try {
			System.out.println(message);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return message;

	}

	/**
	 * 作者：rcp 时间：2016/9/28
	 */
	public void getdjz() {
		String token = gettoken();

		String action = "http://www.chinapesticide.gov.cn/myquery/rest/djz?u=shinterface&t=" + token + "&djzh=PD20101973";
		String message = "";
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.connect();

			InputStream is = http.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
			}
			reader.close();
			message = sbf.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(message);
			ServletActionContext.getResponse().getWriter().write(message);
			// HttpServletResponse response = ServletActionContext.getResponse();
			// response.setCharacterEncoding("UTF-8");
			// response.getWriter().write(message);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 作者：rcp 时间：2016/9/28
	 */
	public void getbq() {
		String token = gettoken();

		String action = "http://www.chinapesticide.gov.cn/myquery/rest/bq?u=shinterface&t=" + token + "&djzh=PD20101973";
		String message = "";
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("GET");
			http.connect();

			InputStream is = http.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
			}
			reader.close();
			message = sbf.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println(message);
			ServletActionContext.getResponse().getWriter().write(message);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public void returnjson(){
		JSONObject rcpJsonObject = new JSONObject();
		rcpJsonObject.put("name", "rcp");
		rcpJsonObject.put("age", "25");
		try {
			ServletActionContext.getResponse().getWriter().write(rcpJsonObject.toString());
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}