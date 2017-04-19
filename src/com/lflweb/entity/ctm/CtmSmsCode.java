package com.lflweb.entity.ctm;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class CtmSmsCode implements BaseBean {

	// 手机号码;
	private String custmobile;

	// 验证码;
	private String verifycode;

	// 创建时间;
	private java.util.Date createdate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<CtmSmsCode> item;

	public CtmSmsCode() {
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
		return ToolUtils.CompareProperty((CtmSmsCode)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"手机号码:custmobile", "验证码:verifycode", "创建时间:createdate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custmobile = "";
		this.verifycode = "";
		this.createdate = ToolUtils.GetMinDate();
	}

	public String getCustmobile() {
		return custmobile;
	}

	public void setCustmobile(String custmobile) {
		this.custmobile=custmobile;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode=verifycode;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
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

	public SelectBean<CtmSmsCode> getItem() {
		if (item == null)
			item = new SelectBean<CtmSmsCode>();

		return item;
	}

	public void setItem(SelectBean<CtmSmsCode> item) {
		this.item = item;
	}

}
