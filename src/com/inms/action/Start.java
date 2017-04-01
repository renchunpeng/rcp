package com.inms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inms.action.tools.SHA1;
import com.inms.action.wx.CoreService;

//import com.souvc.weixin.service.CoreService;
//import com.souvc.weixin.util.SignUtil;

/**
 * 类名: CoreServlet
 * 
 * 描述: 来接收微信服务器传来信息
 * 
 * 开发人员： souvc
 * 
 * 创建时间：2015-9-29
 * 
 * 发布版本：V1.0
 */
public class Start extends HttpServlet {

	private static final long serialVersionUID = 4323197796926899691L;
	private static final String TOKEN = "rcppcr";

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String reSignature = null;
		try {
			String[] str = { TOKEN, timestamp, nonce };
			Arrays.sort(str);
			String bigStr = str[0] + str[1] + str[2];
			reSignature = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != reSignature && reSignature.equals(signature)) {
			// 请求来自微信
			System.out.println(echostr);
			out.write(echostr);
			out.flush();
			out.close();
		}

	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 消息的接收、处理、响应
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("come in");
		// 调用核心业务类接收消息、处理消息
		String respXml = CoreService.processRequest(request);
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respXml);
		out.close();
	}

}