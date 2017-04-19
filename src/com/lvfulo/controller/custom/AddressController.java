package com.lvfulo.controller.custom;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.ctm.CtmAddress;
import com.lvfulo.service.custom.UserService;
import com.lvfulo.utils.MyTools;

@Controller
@RequestMapping("/address")
public class AddressController{
	
	@Autowired
	private UserService addressservice;

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
	 * @description 用户地址列表获取
	 */
	@RequestMapping(value = "/shuseraddress/{custid}")
	@ResponseBody
	public JSONObject SearchAddress(@PathVariable("custid") String custid) {
		this.getRtv().SetValues(false, "", "null", false);
		List<CtmAddress> lists = addressservice.SearchAddress(custid, this.getRtv());
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @author rcp
	 * @description 获取单个地址详情
	 */
	@RequestMapping(value = "/shoneaddress/{addressid}")
	@ResponseBody
	public JSONObject SearchOneAddress(@PathVariable("addressid") String addressid) {
		this.getRtv().SetValues(false, "", "null", false);
		List<CtmAddress> lists = addressservice.Getaddress(addressid, this.getRtv());
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @author rcp
	 * @description 用户地址列表获取
	 */
	@RequestMapping(value = "/seuseraddress", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject SaveAddress(@RequestBody String item) {
		this.getRtv().SetValues(false, "", "null", false);
		JSONObject jsonstore = JSONObject.fromObject(item);
		CtmAddress itemAddress = MyTools.GetOneFromJson(jsonstore.toString(), CtmAddress.class);
		addressservice.SaveAddress(itemAddress, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
	/**
	 * @author rcp
	 * @description 用户地址保存
	 */
	@RequestMapping(value = "/updateaddress", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject UpdateAddress(@RequestBody String item) {
		this.getRtv().SetValues(false, "", null, false);
		JSONObject jsonstore = JSONObject.fromObject(item);
		CtmAddress itemAddress = MyTools.GetOneFromJson(jsonstore.toString(), CtmAddress.class);
		addressservice.UpdateAddress(itemAddress, this.getRtv());
		return JSONObject.fromObject(this.getRtv().toString());
	}
	
}