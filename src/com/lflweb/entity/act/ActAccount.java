package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActAccount implements BaseBean {

	// 账户编号;
	private String accountid;

	// 用户编号;
	private String custid;

	// 账户类型;
	private String accounttype;

	// 账户密码;
	private String accountpwd;

	// 账户状态;
	private String accountstatus;

	// 账户余额;
	private double accountbalance;

	// 累计续费次数;
	private String chargetimes;

	// 累计续费金额;
	private double chargeamt;

	// 创建日期;
	private java.util.Date createdate;

	// 有效时间;
	private java.util.Date expiredate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActAccount> item;

	public ActAccount() {
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
		return ToolUtils.CompareProperty((ActAccount)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"账户编号:accountid", "用户编号:custid", "账户类型:accounttype", "账户密码:accountpwd", "账户状态:accountstatus", "账户余额:accountbalance", "累计续费次数:chargetimes", "累计续费金额:chargeamt", "创建日期:createdate", "有效时间:expiredate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.accountid = "";
		this.custid = "";
		this.accounttype = "";
		this.accountpwd = "";
		this.accountstatus = "";
		this.accountbalance = 0;
		this.chargetimes = "";
		this.chargeamt = 0;
		this.createdate = ToolUtils.GetMinDate();
		this.expiredate = ToolUtils.GetMinDate();
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid=accountid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype=accounttype;
	}

	public String getAccountpwd() {
		return accountpwd;
	}

	public void setAccountpwd(String accountpwd) {
		this.accountpwd=accountpwd;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus=accountstatus;
	}

	public double getAccountbalance() {
		return accountbalance;
	}

	public void setAccountbalance(double accountbalance) {
		this.accountbalance=accountbalance;
	}

	public String getChargetimes() {
		return chargetimes;
	}

	public void setChargetimes(String chargetimes) {
		this.chargetimes=chargetimes;
	}

	public double getChargeamt() {
		return chargeamt;
	}

	public void setChargeamt(double chargeamt) {
		this.chargeamt=chargeamt;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public java.util.Date getExpiredate() {
		return expiredate;
	}

	public void setExpiredate(java.util.Date expiredate) {
		this.expiredate=expiredate;
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

	public SelectBean<ActAccount> getItem() {
		if (item == null)
			item = new SelectBean<ActAccount>();

		return item;
	}

	public void setItem(SelectBean<ActAccount> item) {
		this.item = item;
	}

}
