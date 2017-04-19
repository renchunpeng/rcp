package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarCon implements BaseBean {

	// 商城会员卡消费流水编号;
	private String conid;

	// 消费日期;
	private java.util.Date productiondate;

	// 消费套餐;
	private String meal;

	// 消费地址;
	private String address;

	// 配送电话;
	private String phonenumber;

	// 配送状态;
	private String deliverstatus;

	// 订单编号;
	private String orderid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarCon> item;

	public MarCon() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;

		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((MarCon)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"商城会员卡消费流水编号:conid", "消费日期:productiondate", "消费套餐:meal", "消费地址:address", "配送电话:phonenumber", "配送状态:deliverstatus", "订单编号:orderid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.conid = "";
		this.productiondate = ToolUtils.GetMinDate();
		this.meal = "";
		this.address = "";
		this.phonenumber = "";
		this.deliverstatus = "";
		this.orderid = "";
	}

	public String getConid() {
		return conid;
	}

	public void setConid(String conid) {
		this.conid=conid;
	}

	public java.util.Date getProductiondate() {
		return productiondate;
	}

	public void setProductiondate(java.util.Date productiondate) {
		this.productiondate=productiondate;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal=meal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address=address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}

	public String getDeliverstatus() {
		return deliverstatus;
	}

	public void setDeliverstatus(String deliverstatus) {
		this.deliverstatus=deliverstatus;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public SearchParams getSearch() {
		if (search == null)
			search = new SearchParams();

		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public DataDeal getDeal() {
		if (deal == null)
			deal = new DataDeal();

		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}

	public SelectBean<MarCon> getItem() {
		if (item == null)
			item = new SelectBean<MarCon>();

		return item;
	}

	public void setItem(SelectBean<MarCon> item) {
		this.item = item;
	}

}
