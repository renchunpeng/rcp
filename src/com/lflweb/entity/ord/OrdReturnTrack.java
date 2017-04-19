package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdReturnTrack implements BaseBean {

	// 退单编号;
	private String returnid;

	// 更新日期;
	private java.util.Date updatedate;

	// 处理人员;
	private String dealoperator;

	// 原状态;
	private String originstatus;

	// 新状态;
	private String newstatus;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdReturnTrack> item;

	public OrdReturnTrack() {
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
		return ToolUtils.CompareProperty((OrdReturnTrack)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"退单编号:returnid", "更新日期:updatedate", "处理人员:dealoperator", "原状态:originstatus", "新状态:newstatus"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.returnid = "";
		this.updatedate = ToolUtils.GetMinDate();
		this.dealoperator = "";
		this.originstatus = "";
		this.newstatus = "";
	}

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid=returnid;
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

	public SelectBean<OrdReturnTrack> getItem() {
		if (item == null)
			item = new SelectBean<OrdReturnTrack>();

		return item;
	}

	public void setItem(SelectBean<OrdReturnTrack> item) {
		this.item = item;
	}

}
