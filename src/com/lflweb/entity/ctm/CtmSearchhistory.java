package com.lflweb.entity.ctm;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class CtmSearchhistory implements BaseBean {

	// 搜索记录编号;
	private String searchid;

	// 搜索人;
	private String custid;

	// 搜索日期;
	private java.util.Date searchdate;

	// 搜索内容;
	private String searchcontent;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<CtmSearchhistory> item;

	public CtmSearchhistory() {
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
		return ToolUtils.CompareProperty((CtmSearchhistory)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"搜索记录编号:searchid", "搜索人:custid", "搜索日期:searchdate", "搜索内容:searchcontent"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.searchid = "";
		this.custid = "";
		this.searchdate = ToolUtils.GetMinDate();
		this.searchcontent = "";
	}

	public String getSearchid() {
		return searchid;
	}

	public void setSearchid(String searchid) {
		this.searchid=searchid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public java.util.Date getSearchdate() {
		return searchdate;
	}

	public void setSearchdate(java.util.Date searchdate) {
		this.searchdate=searchdate;
	}

	public String getSearchcontent() {
		return searchcontent;
	}

	public void setSearchcontent(String searchcontent) {
		this.searchcontent=searchcontent;
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

	public SelectBean<CtmSearchhistory> getItem() {
		if (item == null)
			item = new SelectBean<CtmSearchhistory>();

		return item;
	}

	public void setItem(SelectBean<CtmSearchhistory> item) {
		this.item = item;
	}

}
