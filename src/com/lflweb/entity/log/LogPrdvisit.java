package com.lflweb.entity.log;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class LogPrdvisit implements BaseBean {

	// 日志编号;
	private String logid;

	// 商品编号;
	private String prdid;

	// 用户编号;
	private String custid;

	// 访问时间;
	private java.util.Date visitdate;

	// 访问渠道;
	private String visitsource;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<LogPrdvisit> item;

	public LogPrdvisit() {
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
		return ToolUtils.CompareProperty((LogPrdvisit)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"日志编号:logid", "商品编号:prdid", "用户编号:custid", "访问时间:visitdate", "访问渠道:visitsource"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.logid = "";
		this.prdid = "";
		this.custid = "";
		this.visitdate = ToolUtils.GetMinDate();
		this.visitsource = "";
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid=logid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public java.util.Date getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(java.util.Date visitdate) {
		this.visitdate=visitdate;
	}

	public String getVisitsource() {
		return visitsource;
	}

	public void setVisitsource(String visitsource) {
		this.visitsource=visitsource;
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

	public SelectBean<LogPrdvisit> getItem() {
		if (item == null)
			item = new SelectBean<LogPrdvisit>();

		return item;
	}

	public void setItem(SelectBean<LogPrdvisit> item) {
		this.item = item;
	}

}
