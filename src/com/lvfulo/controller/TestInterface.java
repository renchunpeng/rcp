package com.lvfulo.controller;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gpersist.entity.ReturnValue;

@Controller
public class TestInterface {
	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	/**
	 * 测试rtv初始化
	 * 
	 * @author rcp
	 */
	@RequestMapping(value = "/testrtv")
	@ResponseBody
	public ReturnValue TestRtv(HttpServletRequest request) {
		this.getRtv().OnInit();
		// 判断session是不是新创建的
		if (request.getSession(false)==null) {
			System.out.println("Session has been invalidated!");
		} else {
			System.out.println("Session is active!");
			HttpSession session = request.getSession();
			System.out.println(session.getAttribute("access_token"));
		}
		return rtv;
	}

	/**
	 * 测试rtv初始化2
	 * 
	 * @author rcp
	 */
	@RequestMapping(value = "/testrtv2/{url}")
	@ResponseBody
	public ReturnValue wxAuth(@PathVariable String url,HttpServletRequest request) {
		this.getRtv().OnInit();
		HttpSession session = request.getSession();
		session.setAttribute("access_token", url);
		session.setMaxInactiveInterval(10);//以秒为单位，即在没有活动30分钟后，session将失效
		String sessionid = session.getId();
		String sessionname = (String) session.getAttribute("access_token");
		// 判断session是不是新创建的
		if (session.isNew()) {
			System.out.println("新建session");
			System.out.println(sessionid);
			System.out.println(sessionname);
		} else {
			System.out.println("旧session");
			System.out.println(sessionid);
			System.out.println(sessionname);
		}
		return rtv;
	}

	/**
	 * 文件上传
	 * 
	 * @param common
	 */
	@RequestMapping(value = "/fileupload")
	@ResponseBody
	public ReturnValue SaveComment(@RequestParam(value = "common", required = false) MultipartFile common) {
		System.out.println(common.getOriginalFilename());
		ReturnValue rtvReturnValue = new ReturnValue();
		rtvReturnValue.setSuccess(true);
		return rtvReturnValue;
	}

	@RequestMapping(value = "/testget")
	@ResponseBody
	public void SaveComment(String name) {
		System.out.println(name);
		
	}
}