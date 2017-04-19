package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarVerifyrecord implements BaseBean {

	// 商城审核记录编号;
	private String recordid;

	// 商城编号;
	private String shopid;

	// 提交人;
	private String submiter;

	// 提交日期;
	private java.util.Date submitdate;

	// 审核人;
	private String auditer;

	// 审核日期;
	private java.util.Date audidate;

	// 审核状态;
	private String audistatus;

	// 备注;
	private String remark;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarVerifyrecord> item;

	public MarVerifyrecord() {
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
		return ToolUtils.CompareProperty((MarVerifyrecord)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"商城审核记录编号:recordid", "商城编号:shopid", "提交人:submiter", "提交日期:submitdate", "审核人:auditer", "审核日期:audidate", "审核状态:audistatus", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.recordid = "";
		this.shopid = "";
		this.submiter = "";
		this.submitdate = ToolUtils.GetMinDate();
		this.auditer = "";
		this.audidate = ToolUtils.GetMinDate();
		this.audistatus = "";
		this.remark = "";
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid=recordid;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid=shopid;
	}

	public String getSubmiter() {
		return submiter;
	}

	public void setSubmiter(String submiter) {
		this.submiter=submiter;
	}

	public java.util.Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(java.util.Date submitdate) {
		this.submitdate=submitdate;
	}

	public String getAuditer() {
		return auditer;
	}

	public void setAuditer(String auditer) {
		this.auditer=auditer;
	}

	public java.util.Date getAudidate() {
		return audidate;
	}

	public void setAudidate(java.util.Date audidate) {
		this.audidate=audidate;
	}

	public String getAudistatus() {
		return audistatus;
	}

	public void setAudistatus(String audistatus) {
		this.audistatus=audistatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark=remark;
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

	public SelectBean<MarVerifyrecord> getItem() {
		if (item == null)
			item = new SelectBean<MarVerifyrecord>();

		return item;
	}

	public void setItem(SelectBean<MarVerifyrecord> item) {
		this.item = item;
	}

}
