package com.lflweb.entity.ctm;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class CtmLogin implements BaseBean {

	// 用户编号;
	private String custid;

	// 登录账号;
	private String loginid;

	// 登录类型;
	private String logintype;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<CtmLogin> item;

	public CtmLogin() {
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
		return ToolUtils.CompareProperty((CtmLogin)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "登录账号:loginid", "登录类型:logintype"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.loginid = "";
		this.logintype = "";
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid=loginid;
	}

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype=logintype;
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

	public SelectBean<CtmLogin> getItem() {
		if (item == null)
			item = new SelectBean<CtmLogin>();

		return item;
	}

	public void setItem(SelectBean<CtmLogin> item) {
		this.item = item;
	}

}
