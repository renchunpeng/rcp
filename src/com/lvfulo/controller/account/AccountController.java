package com.lvfulo.controller.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.ctm.CtmBase;
import com.lvfulo.service.account.AccountService;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/account")
public class AccountController {
	//这是一段注释性的话语
	@Autowired
	private AccountService accountservice;

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
	 * @author rcp
	 * @description 用户注册接口
	 */

	@RequestMapping(value = "/Registered", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject Registered(@RequestBody String item) {
		String custsource = "03";
		System.out.println(item);
		this.getRtv().SetValues(false, "", "null", false);
		JSONObject jsonstore = JSONObject.fromObject(item);
		CtmBase user = MyTools.GetOneFromJson(jsonstore.toString(), CtmBase.class);
		accountservice.Registered(user, custsource, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @author rcp
	 * @description 用户登陆接口
	 */
	@RequestMapping(value = "/Login/{username}/{password}")
	@ResponseBody
	public JSONObject Login(@PathVariable("username") String username, @PathVariable("password") String password, HttpServletRequest request) {
		String custsource = "03";
		System.out.println(username + "  " + password + "  " + custsource);
		this.getRtv().SetValues(false, "", "null", false);
		CtmBase item = accountservice.Login(username, password, custsource, ToolUtils.getIpAddress(request), this.getRtv());
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutListOne(item, true));
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @author rcp
	 * @description 用户修改用户名接口，传入的参数为custid、username
	 */

	@RequestMapping(value = "/UpdateUserName/{custid}/{username}")
	@ResponseBody
	public JSONObject UpdateUserName(@PathVariable("custid") String custid, @PathVariable("username") String username,HttpServletResponse response) {
		this.getRtv().SetValues(false, "", "null", false);
		username = ToolUtils.Decode(username);
		accountservice.UpdateUserName(custid, username, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @author rcp
	 * @description 用户修改密码接口，传入的参数为custid、password
	 */

	@RequestMapping(value = "/UpdatePassWord/{custid}/{password}")
	@ResponseBody
	public JSONObject UpdatePassWord(@PathVariable("custid") String custid, @PathVariable("password") String password) {
		this.getRtv().SetValues(false, "", "null", false);
		accountservice.UpdatePassWord(custid, password, this.getRtv());
		System.out.println(JSONObject.fromObject(this.getRtv().toString()));
		return JSONObject.fromObject(this.getRtv().toString());
	}

	/**
	 * @author rcp
	 * @description 用户修改手机号码接口，传入的参数为custid、password
	 */

	@RequestMapping(value = "/UpdatePhone/{custid}/{phone}")
	@ResponseBody
	public JSONObject UpdatePhone(@PathVariable("custid") String custid, @PathVariable("phone") String phone) {
		this.getRtv().SetValues(false, "", "null", false);
		accountservice.UpdatePhone(custid, phone, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}

}
