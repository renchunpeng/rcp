package com.lvfulo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lvfulo.entity.WxReturnValue;
import com.lvfulo.service.log.LogService;
import com.lvfulo.service.ord.OrdService;
import com.lvfulo.utils.MyTools;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
public class WxReturn {
	@Autowired
	private LogService logservice;

	@Autowired
	private OrdService orderservice;

//	@Autowired
//	private OrdDao ordDao;
//
//	@Autowired
//	private LogDao logDao;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	@RequestMapping(value = "/wxrrturn")
	@ResponseBody
	public void Registered(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			System.out.println("微信支付回调");
			this.getRtv().SetValues(false, "", null, false);

			// region 解析微信返回数据
			BufferedReader buffer = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}

			XStream xstream = new XStream(new DomDriver());
			xstream.alias("xml", WxReturnValue.class);
			WxReturnValue item = (WxReturnValue) xstream.fromXML(bs.toString());
			System.out.println(MyTools.OutListOne(item, true));
			System.out.println(item.getOut_trade_no());

			// 自定义返回数据
//			 WxReturnValue item = new WxReturnValue();
//			 item.setOut_trade_no("OR201703145367744586");
//			 item.setAppid("rcp123");
//			 item.setTransaction_id("rcp123");
			// endregion

			// region 开始拆单

			String orderid = item.getOut_trade_no();
			String paytype = "01";// 微信支付
			orderservice.RetuenChai(paytype, orderid, item.getAppid(), item.getTransaction_id(), this.getRtv());

			// endregion

			String xmlSuccess = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
			response.getWriter().write(xmlSuccess);
		} catch (Exception e) {
			String xmlSuccess = "<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>";
			response.getWriter().write(xmlSuccess);
		}
	}
}
