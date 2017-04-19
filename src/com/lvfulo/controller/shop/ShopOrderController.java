package com.lvfulo.controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lvfulo.service.custom.UserService;
import com.lvfulo.service.shop.ShopOrderService;

@Controller
@RequestMapping("/shop")
public class ShopOrderController {
	@Autowired
	private ShopOrderService shopOrderService;
	
	@Autowired
	private UserService userService;

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
	 * @description 该接口用于修改订单状态——订单发货
	 * @return
	 */
	@RequestMapping(value = "/upOrdSend/{orderid}/{custid}")
	@ResponseBody
	public ReturnValue UpdateOrdSend(@PathVariable("orderid") String orderid, @PathVariable String custid) {
		this.getRtv().SetValues(false, "", null, false);
		shopOrderService.UpdateOrdSend(orderid, custid, this.getRtv());
		return this.getRtv();
	}
	
	/**
	 * @description 该接口用于修改订单状态——订单发货
	 * @return
	 */
	@RequestMapping(value = "/upOrdSendOk/{orderid}/{custid}")
	@ResponseBody
	public ReturnValue UpdateOrdSendOk(@PathVariable("orderid") String orderid, @PathVariable String custid) {
		this.getRtv().SetValues(false, "", null, false);
		shopOrderService.UpdateOrdSendOk(orderid, custid, this.getRtv());
		return this.getRtv();
	}
	
}

	