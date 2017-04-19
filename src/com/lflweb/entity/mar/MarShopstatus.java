package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarShopstatus implements BaseBean {

	// 状态编号;
	private String statuscode;

	// 状态名称;
	private String statusname;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarShopstatus> item;

	public MarShopstatus() {
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
		return ToolUtils.CompareProperty((MarShopstatus)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"状态编号:statuscode", "状态名称:statusname"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.statuscode = "";
		this.statusname = "";
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode=statuscode;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname=statusname;
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

	public SelectBean<MarShopstatus> getItem() {
		if (item == null)
			item = new SelectBean<MarShopstatus>();

		return item;
	}

	public void setItem(SelectBean<MarShopstatus> item) {
		this.item = item;
	}

}
