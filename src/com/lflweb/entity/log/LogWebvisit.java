package com.lflweb.entity.log;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class LogWebvisit implements BaseBean {

	// 日志编号;
	private String logid;

	// 用户编号;
	private String custid;

	// 访问渠道;
	private String custsource;

	// ip地址;
	private String ipaddress;

	// 访问时间;
	private java.util.Date visitdate;

	// 访问时长;
	private String visitduration;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<LogWebvisit> item;

	public LogWebvisit() {
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
		return ToolUtils.CompareProperty((LogWebvisit)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"日志编号:logid", "用户编号:custid", "访问渠道:custsource", "IP地址:ipaddress", "访问时间:visitdate", "访问时长:visitduration"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.logid = "";
		this.custid = "";
		this.custsource = "";
		this.ipaddress = "";
		this.visitdate = ToolUtils.GetMinDate();
		this.visitduration = "";
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid=logid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getCustsource() {
		return custsource;
	}

	public void setCustsource(String custsource) {
		this.custsource=custsource;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress=ipaddress;
	}

	public java.util.Date getVisitdate() {
		return visitdate;
	}

	public void setVisitdate(java.util.Date visitdate) {
		this.visitdate=visitdate;
	}

	public String getVisitduration() {
		return visitduration;
	}

	public void setVisitduration(String visitduration) {
		this.visitduration=visitduration;
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

	public SelectBean<LogWebvisit> getItem() {
		if (item == null)
			item = new SelectBean<LogWebvisit>();

		return item;
	}

	public void setItem(SelectBean<LogWebvisit> item) {
		this.item = item;
	}

}
