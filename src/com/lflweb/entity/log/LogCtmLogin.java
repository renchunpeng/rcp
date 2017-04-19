package com.lflweb.entity.log;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class LogCtmLogin implements BaseBean {

	// 用户编号;
	private String custid;

	// 登录时间;
	private java.util.Date logindate;

	// 登录渠道;
	private String custsource;

	// 登录ip;
	private String loginip;

	// 登录描述;
	private String logindetail;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<LogCtmLogin> item;

	public LogCtmLogin() {
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
		return ToolUtils.CompareProperty((LogCtmLogin)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "登录时间:logindate", "登录渠道:custsource", "登录IP:loginip", "登录描述:logindetail"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.logindate = ToolUtils.GetMinDate();
		this.custsource = "";
		this.loginip = "";
		this.logindetail = "";
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public java.util.Date getLogindate() {
		return logindate;
	}

	public void setLogindate(java.util.Date logindate) {
		this.logindate=logindate;
	}

	public String getCustsource() {
		return custsource;
	}

	public void setCustsource(String custsource) {
		this.custsource=custsource;
	}

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip=loginip;
	}

	public String getLogindetail() {
		return logindetail;
	}

	public void setLogindetail(String logindetail) {
		this.logindetail=logindetail;
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

	public SelectBean<LogCtmLogin> getItem() {
		if (item == null)
			item = new SelectBean<LogCtmLogin>();

		return item;
	}

	public void setItem(SelectBean<LogCtmLogin> item) {
		this.item = item;
	}

}
