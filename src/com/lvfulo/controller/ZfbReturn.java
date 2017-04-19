package com.lvfulo.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.ord.OrdBase;
import com.lvfulo.dao.ord.OrdDao;
import com.lvfulo.service.ord.OrdService;
import com.lvfulo.utils.MyTools;

@Controller
public class ZfbReturn {
	@Autowired
	private OrdService orderservice;
	
	@Autowired
	private OrdDao ordDao;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	@RequestMapping(value = "/zfbreturn")
	@ResponseBody
	public String Registered(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
				System.out.println("支付宝回调");
				Map<String, String> params = new HashMap<String, String>();
				Map requestParams = request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
					}
					System.out.println(">>>>>参数" + name + ":" + valueStr);
					params.put(name, valueStr);
				}
				requestParams.get("trade_status");
				String outtradeNo = request.getParameter("out_trade_no");// 商家订单号
				String trade_no = request.getParameter("trade_no");// 支付宝订单号号
				String tradeStatus = request.getParameter("trade_status");
				System.out.println(outtradeNo + "  " + tradeStatus);

				// region 开始拆单
				String paytype = "02";// 支付宝支付
				orderservice.RetuenChai(paytype, outtradeNo, "zfbappid", trade_no, this.getRtv());

				// endregion

			}

			return "success";

		} catch (Exception e) {
			return "success";
		}

	}

	@RequestMapping(value = "/testsave")
	@ResponseBody
	public OrdBase tetssave(){
		OrdBase rcpBase = new OrdBase();
//		rcpBase.setOrderid("endrcp2");
//		rcpBase.setSenddate(new Date());
//		rcpBase.setCreatedate(new Date());
////		rcpBase.setPaydate(new Date());
////		rcpBase.setOkdate(new Date());
////		rcpBase.setPrintdate(new Date());
//		rcpBase.getDeal().setAction(DataAction.Create.getAction());
//		ordDao.saveOrdBase(rcpBase);
		rcpBase.getSearch().setSearch("a.orderid = 'OR201703034053529077'");
		rcpBase.getSearch().setStart(0);
		rcpBase.getSearch().setEnd(1);
		List<OrdBase> lists = ordDao.searchOrdBase(rcpBase);
		OrdBase rcpBase2 = lists.get(0);
		return rcpBase2;
		
		
	}
	
	public static void main(String[] args){
		OrdBase rcpBase = new OrdBase();
		JSONObject rrrJsonObject = new JSONObject();
		rrrJsonObject.put("senddate", "2015-10-10 10:30:25");
		OrdBase yuBase = MyTools.GetOneFromJson(rrrJsonObject.toString(), OrdBase.class);
		System.out.println(yuBase);
	}
}
