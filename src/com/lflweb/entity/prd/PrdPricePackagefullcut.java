package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdPricePackagefullcut implements BaseBean {

	// 价格策略编号;
	private String strategyid;

	// 开始日期;
	private java.util.Date begindate;

	// 结束日期;
	private java.util.Date enddate;

	// 价格下限;
	private double pricelow;

	// 价格上限;
	private double priceup;

	// 满减金额;
	private double priceminus;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdPricePackagefullcut> item;

	public PrdPricePackagefullcut() {
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
		return ToolUtils.CompareProperty((PrdPricePackagefullcut)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"价格策略编号:strategyid", "开始日期:begindate", "结束日期:enddate", "价格下限:pricelow", "价格上限:priceup", "满减金额:priceminus"};
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
		this.pricelow = 0;
		this.priceup = 0;
		this.priceminus = 0;
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

	public double getPricelow() {
		return pricelow;
	}

	public void setPricelow(double pricelow) {
		this.pricelow=pricelow;
	}

	public double getPriceup() {
		return priceup;
	}

	public void setPriceup(double priceup) {
		this.priceup=priceup;
	}

	public double getPriceminus() {
		return priceminus;
	}

	public void setPriceminus(double priceminus) {
		this.priceminus=priceminus;
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

	public SelectBean<PrdPricePackagefullcut> getItem() {
		if (item == null)
			item = new SelectBean<PrdPricePackagefullcut>();

		return item;
	}

	public void setItem(SelectBean<PrdPricePackagefullcut> item) {
		this.item = item;
	}

}
