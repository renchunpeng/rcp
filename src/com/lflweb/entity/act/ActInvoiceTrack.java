package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActInvoiceTrack implements BaseBean {

	// 发票流水号;
	private String invoicetranid;

	// 更新日期;
	private java.util.Date updatedate;

	// 操作人员;
	private String dealoperator;

	// 原状态;
	private String originstatus;

	// 新状态;
	private String newstatus;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActInvoiceTrack> item;

	public ActInvoiceTrack() {
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
		return ToolUtils.CompareProperty((ActInvoiceTrack)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"发票流水号:invoicetranid", "更新日期:updatedate", "操作人员:dealoperator", "原状态:originstatus", "新状态:newstatus"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.invoicetranid = "";
		this.updatedate = ToolUtils.GetMinDate();
		this.dealoperator = "";
		this.originstatus = "";
		this.newstatus = "";
	}

	public String getInvoicetranid() {
		return invoicetranid;
	}

	public void setInvoicetranid(String invoicetranid) {
		this.invoicetranid=invoicetranid;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate=updatedate;
	}

	public String getDealoperator() {
		return dealoperator;
	}

	public void setDealoperator(String dealoperator) {
		this.dealoperator=dealoperator;
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

	public SelectBean<ActInvoiceTrack> getItem() {
		if (item == null)
			item = new SelectBean<ActInvoiceTrack>();

		return item;
	}

	public void setItem(SelectBean<ActInvoiceTrack> item) {
		this.item = item;
	}

}
