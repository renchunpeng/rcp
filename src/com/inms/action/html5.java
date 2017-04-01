package com.inms.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;


public class html5 extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4069046879247413586L;

	public void test(){
		System.out.println("come in");
		JSONObject iJsonObject = new JSONObject();
		iJsonObject.put("name", "rcp");
		 responseHtmlText(iJsonObject.toString());
	}
	
	public void responseHtmlText(String text) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF8");
		try {
			response.getWriter().write(text);
		} catch (IOException e) {
			return;
		}
	}
}





