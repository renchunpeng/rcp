package com.lflweb.entity.bas;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BasCity implements BaseBean {

	// 城市编号;
	private String cityid;

	// 城市名称;
	private String cityname;

	// 省份编号;
	private String provinceid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BasCity> item;

	public BasCity() {
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
		return ToolUtils.CompareProperty((BasCity)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"城市编号:cityid", "城市名称:cityname", "省份编号:provinceid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.cityid = "";
		this.cityname = "";
		this.provinceid = "";
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid=cityid;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname=cityname;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid=provinceid;
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

	public SelectBean<BasCity> getItem() {
		if (item == null)
			item = new SelectBean<BasCity>();

		return item;
	}

	public void setItem(SelectBean<BasCity> item) {
		this.item = item;
	}

}
