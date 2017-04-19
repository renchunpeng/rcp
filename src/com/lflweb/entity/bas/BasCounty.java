package com.lflweb.entity.bas;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BasCounty implements BaseBean {

	// 县区编号;
	private String countyid;

	// 县区名称;
	private String countyname;

	// 区域代号;
	private String zonecode;

	// 城市编号;
	private String cityid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BasCounty> item;

	public BasCounty() {
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
		return ToolUtils.CompareProperty((BasCounty)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"县区编号:countyid", "县区名称:countyname", "区域代号:zonecode", "城市编号:cityid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.countyid = "";
		this.countyname = "";
		this.zonecode = "";
		this.cityid = "";
	}

	public String getCountyid() {
		return countyid;
	}

	public void setCountyid(String countyid) {
		this.countyid=countyid;
	}

	public String getCountyname() {
		return countyname;
	}

	public void setCountyname(String countyname) {
		this.countyname=countyname;
	}

	public String getZonecode() {
		return zonecode;
	}

	public void setZonecode(String zonecode) {
		this.zonecode=zonecode;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid=cityid;
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

	public SelectBean<BasCounty> getItem() {
		if (item == null)
			item = new SelectBean<BasCounty>();

		return item;
	}

	public void setItem(SelectBean<BasCounty> item) {
		this.item = item;
	}

}
