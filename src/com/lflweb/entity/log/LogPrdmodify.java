package com.lflweb.entity.log;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class LogPrdmodify implements BaseBean {

	// 日志编号;
	private String logid;

	// 操作日期;
	private java.util.Date logdate;

	// 操作员;
	private String logoperator;

	// 操作类型;
	private String logtype;

	// 日志信息;
	private String logdetail;

	// 商品编号;
	private String prdid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<LogPrdmodify> item;

	public LogPrdmodify() {
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
		return ToolUtils.CompareProperty((LogPrdmodify)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"日志编号:logid", "操作日期:logdate", "操作员:logoperator", "操作类型:logtype", "日志信息:logdetail", "商品编号:prdid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.logid = "";
		this.logdate = ToolUtils.GetMinDate();
		this.logoperator = "";
		this.logtype = "";
		this.logdetail = "";
		this.prdid = "";
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid=logid;
	}

	public java.util.Date getLogdate() {
		return logdate;
	}

	public void setLogdate(java.util.Date logdate) {
		this.logdate=logdate;
	}

	public String getLogoperator() {
		return logoperator;
	}

	public void setLogoperator(String logoperator) {
		this.logoperator=logoperator;
	}

	public String getLogtype() {
		return logtype;
	}

	public void setLogtype(String logtype) {
		this.logtype=logtype;
	}

	public String getLogdetail() {
		return logdetail;
	}

	public void setLogdetail(String logdetail) {
		this.logdetail=logdetail;
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

	public SelectBean<LogPrdmodify> getItem() {
		if (item == null)
			item = new SelectBean<LogPrdmodify>();

		return item;
	}

	public void setItem(SelectBean<LogPrdmodify> item) {
		this.item = item;
	}

}
