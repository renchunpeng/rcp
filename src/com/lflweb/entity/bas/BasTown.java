package com.lflweb.entity.bas;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BasTown implements BaseBean {

	// 乡镇编号;
	private String townid;

	// 乡镇名称;
	private String townname;

	// 区域代号;
	private String zonecode;

	// 县区编号;
	private String countyid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BasTown> item;

	public BasTown() {
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
		return ToolUtils.CompareProperty((BasTown)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"乡镇编号:townid", "乡镇名称:townname", "区域代号:zonecode", "县区编号:countyid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.townid = "";
		this.townname = "";
		this.zonecode = "";
		this.countyid = "";
	}

	public String getTownid() {
		return townid;
	}

	public void setTownid(String townid) {
		this.townid=townid;
	}

	public String getTownname() {
		return townname;
	}

	public void setTownname(String townname) {
		this.townname=townname;
	}

	public String getZonecode() {
		return zonecode;
	}

	public void setZonecode(String zonecode) {
		this.zonecode=zonecode;
	}

	public String getCountyid() {
		return countyid;
	}

	public void setCountyid(String countyid) {
		this.countyid=countyid;
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

	public SelectBean<BasTown> getItem() {
		if (item == null)
			item = new SelectBean<BasTown>();

		return item;
	}

	public void setItem(SelectBean<BasTown> item) {
		this.item = item;
	}

}
