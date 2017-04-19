package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdGeneratetype implements BaseBean {

	// 订单生成方式类型;
	private String generatetype;

	// 订单生成方式名称;
	private String generatename;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdGeneratetype> item;

	public OrdGeneratetype() {
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
		return ToolUtils.CompareProperty((OrdGeneratetype)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"订单生成方式类型:generatetype", "订单生成方式名称:generatename"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.generatetype = "";
		this.generatename = "";
	}

	public String getGeneratetype() {
		return generatetype;
	}

	public void setGeneratetype(String generatetype) {
		this.generatetype=generatetype;
	}

	public String getGeneratename() {
		return generatename;
	}

	public void setGeneratename(String generatename) {
		this.generatename=generatename;
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

	public SelectBean<OrdGeneratetype> getItem() {
		if (item == null)
			item = new SelectBean<OrdGeneratetype>();

		return item;
	}

	public void setItem(SelectBean<OrdGeneratetype> item) {
		this.item = item;
	}

}
