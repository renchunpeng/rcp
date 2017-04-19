package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdPriceDiscount implements BaseBean {

	// 价格策略编号;
	private String strategyid;

	// 开始日期;
	private java.util.Date begindate;

	// 结束日期;
	private java.util.Date enddate;

	// 商品数量下限;
	private int numlow;

	// 商品数量上限;
	private int numup;

	// 价格折扣;
	private String pricediscount;

	// 商品促销价;
	private double pricepromotion;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdPriceDiscount> item;

	public PrdPriceDiscount() {
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
		return ToolUtils.CompareProperty((PrdPriceDiscount)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"价格策略编号:strategyid", "开始日期:begindate", "结束日期:enddate", "商品数量下限:numlow", "商品数量上限:numup", "价格折扣:pricediscount", "商品促销价:pricepromotion"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.strategyid = "";
		this.begindate = ToolUtils.GetMinDate();
		this.enddate = ToolUtils.GetMinDate();
		this.numlow = 0;
		this.numup = 0;
		this.pricediscount = "";
		this.pricepromotion = 0;
	}

	public String getStrategyid() {
		return strategyid;
	}

	public void setStrategyid(String strategyid) {
		this.strategyid=strategyid;
	}

	public java.util.Date getBegindate() {
		return begindate;
	}

	public void setBegindate(java.util.Date begindate) {
		this.begindate=begindate;
	}

	public java.util.Date getEnddate() {
		return enddate;
	}

	public void setEnddate(java.util.Date enddate) {
		this.enddate=enddate;
	}

	public int getNumlow() {
		return numlow;
	}

	public void setNumlow(int numlow) {
		this.numlow=numlow;
	}

	public int getNumup() {
		return numup;
	}

	public void setNumup(int numup) {
		this.numup=numup;
	}

	public String getPricediscount() {
		return pricediscount;
	}

	public void setPricediscount(String pricediscount) {
		this.pricediscount=pricediscount;
	}

	public double getPricepromotion() {
		return pricepromotion;
	}

	public void setPricepromotion(double pricepromotion) {
		this.pricepromotion=pricepromotion;
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

	public SelectBean<PrdPriceDiscount> getItem() {
		if (item == null)
			item = new SelectBean<PrdPriceDiscount>();

		return item;
	}

	public void setItem(SelectBean<PrdPriceDiscount> item) {
		this.item = item;
	}

}
