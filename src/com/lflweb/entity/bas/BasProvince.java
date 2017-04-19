package com.lflweb.entity.bas;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BasProvince implements BaseBean {

	// 省份编号;
	private String provinceid;

	// 省份名称;
	private String provincename;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BasProvince> item;

	public BasProvince() {
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
		return ToolUtils.CompareProperty((BasProvince)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"省份编号:provinceid", "省份名称:privincename"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.provinceid = "";
		this.provincename = "";
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid=provinceid;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
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

	public SelectBean<BasProvince> getItem() {
		if (item == null)
			item = new SelectBean<BasProvince>();

		return item;
	}

	public void setItem(SelectBean<BasProvince> item) {
		this.item = item;
	}

}
