package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdType implements BaseBean {

	// 商品类型;
	private String prdtype;

	// 商品类型名称;
	private String prdtypename;

	// 商品类型图表;
	private String iconurl;

	// 商品类型简介;
	private String prdtypeintrduction;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdType> item;

	public PrdType() {
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
		return ToolUtils.CompareProperty((PrdType)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"商品类型:prdtype", "商品类型名称:prdtypename", "商品类型图表:iconurl", "商品类型简介:prdtypeintrduction"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.prdtype = "";
		this.prdtypename = "";
		this.iconurl = "";
		this.prdtypeintrduction = "";
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype=prdtype;
	}

	public String getPrdtypename() {
		return prdtypename;
	}

	public void setPrdtypename(String prdtypename) {
		this.prdtypename=prdtypename;
	}

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl=iconurl;
	}

	public String getPrdtypeintrduction() {
		return prdtypeintrduction;
	}

	public void setPrdtypeintrduction(String prdtypeintrduction) {
		this.prdtypeintrduction=prdtypeintrduction;
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

	public SelectBean<PrdType> getItem() {
		if (item == null)
			item = new SelectBean<PrdType>();

		return item;
	}

	public void setItem(SelectBean<PrdType> item) {
		this.item = item;
	}

}
