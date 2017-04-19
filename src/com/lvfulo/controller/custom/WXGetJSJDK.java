package com.lvfulo.controller.custom;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lvfulo.controller.wxping.GetJSJDK.GetJSJDK;

@Controller
@RequestMapping("/wxjs")
public class WXGetJSJDK {
	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	@RequestMapping(value = "/getjsjdk", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue wxAuth(@RequestBody String url,HttpServletRequest request) {
		this.getRtv().OnInit();
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject urlJsonObject = JSONObject.fromObject(url);
		String newurl = urlJsonObject.getString("url");
		map = GetJSJDK.getWxConfig(newurl,request);
		JSONObject jObject = JSONObject.fromObject(map);
		rtv.setData(jObject);
		rtv.setSuccess(true);
		System.out.println(jObject.toString());
		return rtv;
	}

}
