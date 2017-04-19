package com.lvfulo.controller.ord;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActInvoiceTrans;
import com.lflweb.entity.act.ActInvoiceTransMain;
import com.lflweb.entity.act.ActInvoiceTransdetail;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ctm.CtmAddress;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.ord.OrdBaseMain;
import com.lflweb.entity.ord.OrdDeliveryComment;
import com.lflweb.entity.ord.OrdPrdComment;
import com.lflweb.entity.ord.OrdReturn;
import com.lflweb.entity.ord.OrdShop;
import com.lflweb.entity.ord.OrdSubdetail;
import com.lflweb.entity.ord.OrdSubdetailMix;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdPackage;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.custom.CustomDao;
import com.lvfulo.dao.prd.PrdDao;
import com.lvfulo.service.account.AccountService;
import com.lvfulo.service.custom.UserService;
import com.lvfulo.service.ord.OrdService;
import com.lvfulo.service.prd.PrdService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/ord")
public class OrdController {
	@Autowired
	private OrdService ordservice;

	@Autowired
	private PrdService prdService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

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
	 * @description 购物车查询
	 */
	@RequestMapping(value = "/shordshop/{custid}/{page}")
	@ResponseBody
	public JSONObject ShPrdBase(@PathVariable("custid") String custid, @PathVariable("page") String page) {
		this.getRtv().SetValues(false, "", "null", false);
		OrdShop itemBase = new OrdShop();
		itemBase.getSearch().setSearch("a.custid = '" + custid + "'");
		itemBase.getSearch().setStart(ToolUtils.getstart(page));
		itemBase.getSearch().setEnd(20);
		List<OrdShop> lists = ordservice.searchOrdShop(itemBase, this.getRtv());
		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return JSONObject.fromObject(this.getRtv().toString());
	}

	/**
	 * 查询购物车列表
	 * 
	 * @param custid
	 * @return
	 */
	@RequestMapping(value = "/ordershop/{custid}")
	@ResponseBody
	public JSONObject SearchOrdShop(@PathVariable("custid") String custid) {
		this.getRtv().SetValues(false, "", "null", false);
		if (!ToolUtils.StringIsEmpty(custid)) {
			OrdShop ordShop = new OrdShop();
			ordShop.setCustid(custid);
			List<OrdShop> lists = ordservice.GetListOrdShop(ordShop, this.getRtv());
			this.getRtv().setBean(true);
			this.getRtv().setData(MyTools.OutLists(lists, true));
		}
		return JSONObject.fromObject(this.getRtv().toString());

	}

	/**
	 * 分页查询购物车
	 * 
	 * @param custid
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/ordershop/{custid}/{page}")
	@ResponseBody
	public JSONObject SearchOrdShop(@PathVariable("custid") String custid, @PathVariable("page") String page) {
		this.getRtv().SetValues(false, "", "null", false);
		if (!ToolUtils.StringIsEmpty(custid)) {
			OrdShop ordShop = new OrdShop();
			String search = " a.custid = '" + custid + "'";
			ordShop.getSearch().setSearch(search);
			ordShop.getSearch().setStart(ToolUtils.getstart(page));
			ordShop.getSearch().setEnd(Consts.limit);
			List<OrdShop> lists = ordservice.SearchOrdShop(ordShop, this.getRtv());
			this.getRtv().setBean(true);
			this.getRtv().setData(MyTools.OutLists(lists, true));
		}
		return JSONObject.fromObject(this.getRtv().toString());

	}

	/**
	 * 保存,修改购物车
	 * 
	 * @param type
	 *          (1 保存, 2 修改)
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/ordershop/save", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject SaveOrdShop (@RequestBody String saveList){
		
		this.getRtv().SetValues(false, "", "null", false);
		JSONObject jsonObject = JSONObject.fromObject(saveList);
		String type = jsonObject.getString("type");
		String json = jsonObject.getString("list");
		if(!ToolUtils.StringIsEmpty(json) && (!type.equals("1") || !type.equals("2"))){
			try {
				List<OrdShop> lists = MyTools.GetArrayFromJson(json, OrdShop.class);
				if (type.equals("2")) {
					// 修改之前清空购物车记录
					OrdShop ordshop = new OrdShop();
					ordshop.setCustid(lists.get(0).getCustid());
					ordshop.getDeal().setAction(DataAction.Delete.getAction());
					ordservice.SaveOrdShop(ordshop, this.getRtv());
				}
				ordservice.SaveOrdShopList(lists, this.getRtv());
			} catch (Exception e) {
				this.getRtv().setMsg("数据格式错误!");
			}
		} else
			this.getRtv().setMsg("数据格式错误!");
		return JSONObject.fromObject(this.getRtv().toString());
	}

	/**
	 * @author rcp
	 * @description 用户订单基础表查询
	 */
	@RequestMapping(value = "/shordbase/{custid}/{page}/{status}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String ShOrdBase(@PathVariable("custid") String custid, @PathVariable("page") String page, @PathVariable("status") String status) {
		this.getRtv().SetValues(false, "", "null", false);
		OrdBase itemBase = new OrdBase();
		if (status.equals("110")) {
			itemBase.getSearch().setSearch("a.custid = '" + custid + "'");
		} else {
			itemBase.getSearch().setSearch("a.custid = '" + custid + "' and a.orderstatus = '" + status + "'");
		}

		itemBase.getSearch().setStart(ToolUtils.getstart(page));
		itemBase.getSearch().setEnd(20);

		List<OrdBase> lists = ordservice.searchOrdBase(itemBase, this.getRtv());

		for (OrdBase ordBase : lists) {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch("a.addressid = '" + ordBase.getAddressid() + "'");
			List<CtmAddress> itemCtmAddresses = userService.SearchAddress2(itemAddress, this.getRtv());
			if (itemCtmAddresses.size() >= 1) {
				ordBase.setCtmAddress(itemCtmAddresses.get(0));
			}

		}

		this.getRtv().setBean(true);
		this.getRtv().setData(MyTools.OutLists(lists, true));
		return this.getRtv().toString();
	}

	/**
	 * @author rcp
	 * @description 订单明细查询,带上订单中商品和套餐的详细信息
	 * @return  返回订单商品详情，优惠券信息，主表信息
	 */
	@RequestMapping(value = "/shordsundetails/{orderid}")
	@ResponseBody
	public ReturnValue ShOrdSubdetails(@PathVariable("orderid") String orderid) {
		System.out.println("========"+orderid);
		this.getRtv().SetValues(false, "", "null", false);
		OrdSubdetail itemBase = new OrdSubdetail();
		itemBase.getSearch().setSearch("a.orderid = '" + orderid + "'");
		List<OrdSubdetail> lists = ordservice.searchOrdSubdetails(itemBase, this.getRtv());
		for (OrdSubdetail ordSubdetail : lists) {
			if (ordSubdetail.getPrdkind().equals("02")) {// 是套餐
				PrdPackage itemPrdPackage = new PrdPackage();
				itemPrdPackage.getSearch().setSearch("a.packageid = '" + ordSubdetail.getPrdid() + "'");
				itemPrdPackage.getSearch().setStart(0);
				itemPrdPackage.getSearch().setEnd(1);
				List<PrdPackage> prdPackages = prdService.searchPrdPackage(itemPrdPackage, this.getRtv());
				ordSubdetail.setPrdPackage(prdPackages.get(0));
			} else {
				PrdBase itemPrdBase = new PrdBase();
				itemPrdBase.getSearch().setSearch("a.prdid = '" + ordSubdetail.getPrdid() + "'");
				itemPrdBase.getSearch().setStart(0);
				itemPrdBase.getSearch().setEnd(1);
				List<PrdBase> prdbaselBases = prdService.searchPrdBase(itemPrdBase, this.getRtv());
				ordSubdetail.setPrdBase(prdbaselBases.get(0));
			}

		}
		// TODO将优惠券的信息带回
		List<ActUserCoupon> returnlists = new ArrayList<ActUserCoupon>();
		ActPreOrderCoupon itempreActPreOrderCoupon = new ActPreOrderCoupon();
		itempreActPreOrderCoupon.getSearch().setSearch(" a.orderid = '" + orderid + "'");
		List<ActPreOrderCoupon> rcplistsActPreOrderCoupons = accountService.SearchActPreOrderCoupon(itempreActPreOrderCoupon, this.getRtv());
		for (ActPreOrderCoupon actPreOrderCoupon : rcplistsActPreOrderCoupons) {
			ActUserCoupon itemUserCoupon = new ActUserCoupon();
			itemUserCoupon.getSearch().setSearch(" a.bdcouponid = '" + actPreOrderCoupon.getBdcouponid() + "'");
			ActUserCoupon itemrcpActUserCoupon = accountService.SearchActUserCoupon(itemUserCoupon, this.getRtv()).get(0);
			returnlists.add(itemrcpActUserCoupon);
		}

		// TODO 主表数据
		OrdBase itemmain = new OrdBase();
		itemmain.getSearch().setSearch("a.orderid = '" + orderid + "'");

		itemmain.getSearch().setStart(0);
		itemmain.getSearch().setEnd(20);

		List<OrdBase> listsmain = ordservice.searchOrdBase(itemmain, this.getRtv());
		
		for (OrdBase ordBase : listsmain) {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch("a.addressid = '" + ordBase.getAddressid() + "'");
			List<CtmAddress> itemCtmAddresses = userService.SearchAddress2(itemAddress, this.getRtv());
			if (itemCtmAddresses.size() >= 1) {
				ordBase.setCtmAddress(itemCtmAddresses.get(0));
			}

		}

		OrdSubdetailMix mix = new OrdSubdetailMix();
		mix.setOrdSubdetail(lists);
		mix.setActUserCoupons(returnlists);
		mix.setMain(listsmain.get(0));
		this.getRtv().setData(mix);
		return this.getRtv();
	}

	/**
	 * @author rcp
	 * @description 该接口用于提交保存的订单数据
	 */
	@RequestMapping(value = "/seordbase", method = { RequestMethod.POST })
	@ResponseBody
	public JSONObject SaveOrdBase(@RequestBody String ordbase) {
		this.getRtv().SetValues(false, "", "null", false);
		JSONObject jsonObject = JSONObject.fromObject(ordbase);

		// 主表数据组装完毕
		OrdBaseMain itemOrdBaseMain = MyTools.GetOneFromJson(jsonObject.getString("main").toString(), OrdBaseMain.class);
		OrdBase itemOrdBase = MyTools.GetOneFromJson(jsonObject.getString("main").toString(), OrdBase.class);

		// 子表数据组装完毕
		List<OrdSubdetail> details = MyTools.GetArrayFromJson(jsonObject.get("son").toString(), OrdSubdetail.class);

		System.out.println("是否需要发票：" + jsonObject.get("flag").toString());
		Boolean flag = new Boolean(jsonObject.get("flag").toString());

		// 定义发票对象
		ActInvoiceTransMain invoiceTransMain;
		ActInvoiceTrans invoiceTrans;
		List<ActInvoiceTransdetail> invoiceTransdetails;
		if (flag) {
			// 获取发票信息
			JSONObject invoiceJsonObject = (JSONObject) jsonObject.get("invoicetransbean");

			// 发票流水表信息组装完毕
			invoiceTransMain = MyTools.GetOneFromJson(invoiceJsonObject.get("main").toString(), ActInvoiceTransMain.class);
			invoiceTrans = MyTools.GetOneFromJson(invoiceJsonObject.get("main").toString(), ActInvoiceTrans.class);
			invoiceTrans.setInvoicefather(invoiceTrans.getInvoicetranid());

			// 发票订单明细
			invoiceTransdetails = MyTools.GetArrayFromJson(invoiceJsonObject.get("son").toString(), ActInvoiceTransdetail.class);

		} else {
			invoiceTrans = new ActInvoiceTrans();
			invoiceTransMain = new ActInvoiceTransMain();
			invoiceTransdetails = new ArrayList<ActInvoiceTransdetail>();
		}

		// 获取优惠券列表
		List<ActUserCoupon> coupons = new ArrayList<ActUserCoupon>();
		String cxString = jsonObject.getString("coupon").toString();
		boolean isyouhui = false;
		if (cxString.equals("[]")) {
			System.out.println("没有使用优惠券");
		} else {
			coupons = MyTools.GetArrayFromJson(jsonObject.getString("coupon").toString(), ActUserCoupon.class);
			isyouhui = true;
		}

		ordservice.SaveOrdBaseMid(itemOrdBaseMain, itemOrdBase, details, invoiceTransMain, invoiceTrans, invoiceTransdetails, coupons, flag, isyouhui, this.getRtv());
		System.out.println(this.getRtv().toString());
		return JSONObject.fromObject(this.getRtv().toString());
	}

	/**
	 * @param orderid
	 * @description 该接口用于修改订单状态
	 * @return
	 */
	@RequestMapping(value = "/upordbase/{orderid}/{custid}")
	@ResponseBody
	public ReturnValue UpdateOrdBase(@PathVariable("orderid") String orderid, @PathVariable String custid) {
		this.getRtv().SetValues(false, "", null, false);
		ordservice.UpdateOrdBase(orderid, custid, this.getRtv());
		return this.getRtv();
	}

	/**
	 * @param orderid
	 * @description 订单退单
	 * @return
	 */
	@RequestMapping(value = "/reordbase", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue ReturnOrdBase(@RequestBody String ordreturn) {
		this.getRtv().OnInit();
		OrdReturn item = MyTools.GetOneFromJson(ordreturn, OrdReturn.class);
		ordservice.ReturnOrdBase(item, this.getRtv());
		return this.getRtv();
	}

	/**
	 * @author rcp
	 * @param common
	 * @description 保存评价接口
	 * @data 2017-3-17
	 */
	@RequestMapping(value = "/SaveComment", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue SaveComment(@RequestBody String common) {
		// @RequestMapping(value = "/SaveComment")
		// @ResponseBody
		// public ReturnValue SaveComment() {
		// String common =
		// "{\"delivery\":{\"dcommentid\":\"\",\"orderid\":\"OR201703186889057317\",\"prdpacking\":4,\"deliveryspeed\":3,\"deliveryservice\":2,\"commdate\":\"2017-03-18 01:40:42\"},\"prds\":[{\"pcommentid\":\"\",\"orderid\":\"OR201703186889057317\",\"prdid\":\"c11929ff1d6caa92\",\"prdsatisfaction\":4,\"prdcomment\":\"SDFGSFDGSDGS\",\"commdate\":\"2017-03-18 01:40:42\",\"commreply\":\"\",\"replyer\":\"\",\"replydate\":\"2017-03-18 01:40:42\",\"commstatus\":\"01\"}]}";
		this.getRtv().OnInit();
		JSONObject jsonObject = JSONObject.fromObject(common);
		JSONObject jsonmain = JSONObject.fromObject(jsonObject.getString("delivery"));// 配送评价表
		JSONArray jsonson = JSONArray.fromObject(jsonObject.getString("prds"));// 商品评价表

		// 配送评价组装完毕
		OrdDeliveryComment delivery = MyTools.GetOneFromJson(jsonmain.toString(), OrdDeliveryComment.class);

		// 商品评价组装完毕
		List<OrdPrdComment> prds = MyTools.GetArrayFromJson(jsonson.toString(), OrdPrdComment.class);

		ordservice.SaveComment(delivery, prds, this.getRtv());
		return this.getRtv();
	}

	/**
	 * @author rcp
	 * @param common
	 * @description 查询商品评价
	 * @data 2017-3-18
	 */
	@RequestMapping(value = "/SearchPrdComment/{prdid}")
	@ResponseBody
	public ReturnValue SearchPrdCommon(@PathVariable String prdid) {
		this.getRtv().OnInit();

		OrdPrdComment item = new OrdPrdComment();
		item.getSearch().setSearch(" a.prdid = '" + prdid + "'");
		List<OrdPrdComment> lists = ordservice.SearchPrdCommon(item, this.getRtv());
		this.getRtv().setData(lists);

		return this.getRtv();
	}
}
