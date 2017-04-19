package com.lflweb.entity.ctm;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class CtmGrade implements BaseBean {

	// 用户编号;
	private String custid;

	// 原等级;
	private String origingrade;

	// 用户等级;
	private String custgrade;

	// 变更时间;
	private java.util.Date updatedate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<CtmGrade> item;

	public CtmGrade() {
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
		return ToolUtils.CompareProperty((CtmGrade)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "原等级:origingrade", "用户等级:custgrade", "变更时间:updatedate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.origingrade = "";
		this.custgrade = "";
		this.updatedate = ToolUtils.GetMinDate();
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getOrigingrade() {
		return origingrade;
	}

	public void setOrigingrade(String origingrade) {
		this.origingrade=origingrade;
	}

	public String getCustgrade() {
		return custgrade;
	}

	public void setCustgrade(String custgrade) {
		this.custgrade=custgrade;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate=updatedate;
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

	public SelectBean<CtmGrade> getItem() {
		if (item == null)
			item = new SelectBean<CtmGrade>();

		return item;
	}

	public void setItem(SelectBean<CtmGrade> item) {
		this.item = item;
	}

}
