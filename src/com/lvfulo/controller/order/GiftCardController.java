package com.lvfulo.controller.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.ctm.CtmAddress;
import com.lflweb.entity.mar.MarCon;
import com.lflweb.entity.mar.MarMem;
import com.lvfulo.service.custom.UserService;
import com.lvfulo.service.order.GiftCardService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/order")
public class GiftCardController {
	@Autowired
	private GiftCardService giftCardService;
	
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
	 * 根据用户编号查询用户会员卡信息
	 * @param custId
	 * @return
	 */
	@RequestMapping(value = "/CardList/{custid}/{page}/{memstatus}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String GetCardListByCustId(@PathVariable("custid") String custid, @PathVariable("page") String page, @PathVariable("memstatus") String memstatus) {
		this.getRtv().SetValues(false, "", "null", false);
		
		System.out.println("=====礼品卡列表查询");
		//创建查询及分页条件
		MarMem marMem = new MarMem();
		marMem.getSearch().setSearch(" a.memstatus = '"+ memstatus+"' ");
		marMem.getSearch().setStart(ToolUtils.getstart(page));
		marMem.getSearch().setEnd(Consts.limit);
				
		//会员礼品卡列表查询，区分使用中和已用完
		List<MarMem> cardList = giftCardService.GetCardListByCustId(marMem, this.getRtv());	
		
		for (MarMem item : cardList) {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch("a.addressid = '" + item.getAddressid() + "'");
			List<CtmAddress> itemCtmAddresses = userService.SearchAddress2(itemAddress, this.getRtv());
			if (itemCtmAddresses.size() >= 1) {
				item.setAddress(itemCtmAddresses.get(0));
			}
		}
			
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(cardList, true));
		return this.getRtv().toString();
	}
	
	
	/**
	 * 根据会员卡编号查询配送信息
	 * @param memId
	 * @return
	 */
	@RequestMapping(value = "/cardSendList/{memId}")
	@ResponseBody
	public ReturnValue GetCardConByMemId(@PathVariable("memId") String memId) {		
		System.out.println("=====礼品卡配送列表查询");
		this.getRtv().SetValues(false, "", "null", false);
		//使用中列表
		List<MarCon> cardConList = giftCardService.GetCardConByMemId(memId, rtv);		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("list", MyTools.OutLists(cardConList, true));
			
		this.getRtv().setBean(true);
		this.getRtv().setData(JSONObject.fromObject(map.toString()));
		return this.getRtv();
	}
	
	

}
