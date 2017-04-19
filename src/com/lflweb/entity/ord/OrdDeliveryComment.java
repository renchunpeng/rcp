package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdDeliveryComment implements BaseBean {

	// 配送评论编号;
	private String dcommentid;

	// 订单编号;
	private String orderid;

	// 商品包装评价;
	private String prdpacking;

	// 送货速度评价;
	private String deliveryspeed;

	// 配送员服务态度评价;
	private String deliveryservice;

	// 评价日期;
	private java.util.Date commdate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdDeliveryComment> item;

	public OrdDeliveryComment() {
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
		return ToolUtils.CompareProperty((OrdDeliveryComment)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"配送评论编号:dcommentid", "订单编号:orderid", "商品包装评价:prdpacking", "送货速度评价:deliveryspeed", "配送员服务态度评价:deliveryservice", "评价日期:commdate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.dcommentid = "";
		this.orderid = "";
		this.prdpacking = "";
		this.deliveryspeed = "";
		this.deliveryservice = "";
		this.commdate = ToolUtils.GetMinDate();
	}

	public String getDcommentid() {
		return dcommentid;
	}

	public void setDcommentid(String dcommentid) {
		this.dcommentid=dcommentid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getPrdpacking() {
		return prdpacking;
	}

	public void setPrdpacking(String prdpacking) {
		this.prdpacking=prdpacking;
	}

	public String getDeliveryspeed() {
		return deliveryspeed;
	}

	public void setDeliveryspeed(String deliveryspeed) {
		this.deliveryspeed=deliveryspeed;
	}

	public String getDeliveryservice() {
		return deliveryservice;
	}

	public void setDeliveryservice(String deliveryservice) {
		this.deliveryservice=deliveryservice;
	}

	public java.util.Date getCommdate() {
		return commdate;
	}

	public void setCommdate(java.util.Date commdate) {
		this.commdate=commdate;
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

	public SelectBean<OrdDeliveryComment> getItem() {
		if (item == null)
			item = new SelectBean<OrdDeliveryComment>();

		return item;
	}

	public void setItem(SelectBean<OrdDeliveryComment> item) {
		this.item = item;
	}

}
