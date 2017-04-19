package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdTrack implements BaseBean {

	// 订单编号;
	private String orderid;

	// 更新日期;
	private java.util.Date updatedate;

	// 原状态;
	private String originstatus;

	// 新状态;
	private String newstatus;

	// 处理人员;
	private String dealoperator;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdTrack> item;

	public OrdTrack() {
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
		return ToolUtils.CompareProperty((OrdTrack)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"订单编号:orderid", "更新日期:updatedate", "原状态:originstatus", "新状态:newstatus", "处理人员:dealoperator"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.orderid = "";
		this.updatedate = ToolUtils.GetMinDate();
		this.originstatus = "";
		this.newstatus = "";
		this.dealoperator = "";
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate=updatedate;
	}

	public String getOriginstatus() {
		return originstatus;
	}

	public void setOriginstatus(String originstatus) {
		this.originstatus=originstatus;
	}

	public String getNewstatus() {
		return newstatus;
	}

	public void setNewstatus(String newstatus) {
		this.newstatus=newstatus;
	}

	public String getDealoperator() {
		return dealoperator;
	}

	public void setDealoperator(String dealoperator) {
		this.dealoperator=dealoperator;
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

	public SelectBean<OrdTrack> getItem() {
		if (item == null)
			item = new SelectBean<OrdTrack>();

		return item;
	}

	public void setItem(SelectBean<OrdTrack> item) {
		this.item = item;
	}

}
