package com.inms.action.thinking_in_java.SessionAndCookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class SessionDemo1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8006301723360564883L;

	public void doGet() throws ServletException, IOException {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setCharacterEncoding("UTF=8");
		response.setContentType("text/html;charset=UTF-8");
		// 使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();

		// 读取session中的值
		System.out.println(session.getAttribute("data"));

		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组

		for (Cookie cookie : cookies) {

			System.out.println(cookie.getName());// get the cookie name

			System.out.println(cookie.getValue()); // get the cookie value

		}
		// end
		// 将数据存储到session中
		session.setAttribute("data", "孤傲苍狼");

		Cookie cookie = new Cookie("name", "rcp");

		response.addCookie(cookie);
		// 获取session的Id
		String sessionId = session.getId();
		// 判断session是不是新创建的
		if (session.isNew()) {
			response.getWriter().print("session创建成功，session的id是：" + sessionId);
		} else {
			response.getWriter().print("服务器已经存在该session了，session的id是：" + sessionId);
		}
	}

}