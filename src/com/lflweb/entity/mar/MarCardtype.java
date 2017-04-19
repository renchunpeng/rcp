package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarCardtype implements BaseBean {

	// 会员卡类型;
	private String cardtype;

	// 会员卡类型名称;
	private String cardtypename;

	// 套餐商品编号;
	private String prdid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarCardtype> item;

	public MarCardtype() {
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
		return ToolUtils.CompareProperty((MarCardtype)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"会员卡类型:cardtype", "会员卡类型名称:cardtypename", "套餐商品编号:prdid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.cardtype = "";
		this.cardtypename = "";
		this.prdid = "";
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype=cardtype;
	}

	public String getCardtypename() {
		return cardtypename;
	}

	public void setCardtypename(String cardtypename) {
		this.cardtypename=cardtypename;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
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

	public SelectBean<MarCardtype> getItem() {
		if (item == null)
			item = new SelectBean<MarCardtype>();

		return item;
	}

	public void setItem(SelectBean<MarCardtype> item) {
		this.item = item;
	}

}
