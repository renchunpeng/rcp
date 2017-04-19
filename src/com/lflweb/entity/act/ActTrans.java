package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActTrans implements BaseBean {

	// 交易跟踪号;
	private String traceid;

	// 账户编号;
	private String accountid;

	// 交易日期;
	private java.util.Date trandate;

	// 交易年份;
	private String tranyear;

	// 交易月份;
	private String tranmonth;

	// 交易日;
	private String tranday;

	// 交易年月;
	private String tranym;

	// 交易年月日;
	private String tranymd;

	// 交易人员;
	private String tranoper;

	// 交易类型;
	private String trantype;

	// 交易金额;
	private double tranamt;

	// 可用金额;
	private double balanceamt;

	// 订单编号;
	private String orderid;

	// 交易状态;
	private String transtatus;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActTrans> item;

	public ActTrans() {
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
		return ToolUtils.CompareProperty((ActTrans)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"交易跟踪号:traceid", "账户编号:accountid", "交易日期:trandate", "交易年份:tranyear", "交易月份:tranmonth", "交易日:tranday", "交易年月:tranym", "交易年月日:tranymd", "交易人员:tranoper", "交易类型:trantype", "交易金额:tranamt", "可用金额:balanceamt", "订单编号:orderid", "交易状态:transtatus"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.traceid = "";
		this.accountid = "";
		this.trandate = ToolUtils.GetMinDate();
		this.tranyear = "";
		this.tranmonth = "";
		this.tranday = "";
		this.tranym = "";
		this.tranymd = "";
		this.tranoper = "";
		this.trantype = "";
		this.tranamt = 0;
		this.balanceamt = 0;
		this.orderid = "";
		this.transtatus = "";
	}

	public String getTraceid() {
		return traceid;
	}

	public void setTraceid(String traceid) {
		this.traceid=traceid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid=accountid;
	}

	public java.util.Date getTrandate() {
		return trandate;
	}

	public void setTrandate(java.util.Date trandate) {
		this.trandate=trandate;
	}

	public String getTranyear() {
		return tranyear;
	}

	public void setTranyear(String tranyear) {
		this.tranyear=tranyear;
	}

	public String getTranmonth() {
		return tranmonth;
	}

	public void setTranmonth(String tranmonth) {
		this.tranmonth=tranmonth;
	}

	public String getTranday() {
		return tranday;
	}

	public void setTranday(String tranday) {
		this.tranday=tranday;
	}

	public String getTranym() {
		return tranym;
	}

	public void setTranym(String tranym) {
		this.tranym=tranym;
	}

	public String getTranymd() {
		return tranymd;
	}

	public void setTranymd(String tranymd) {
		this.tranymd=tranymd;
	}

	public String getTranoper() {
		return tranoper;
	}

	public void setTranoper(String tranoper) {
		this.tranoper=tranoper;
	}

	public String getTrantype() {
		return trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype=trantype;
	}

	public double getTranamt() {
		return tranamt;
	}

	public void setTranamt(double tranamt) {
		this.tranamt=tranamt;
	}

	public double getBalanceamt() {
		return balanceamt;
	}

	public void setBalanceamt(double balanceamt) {
		this.balanceamt=balanceamt;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getTranstatus() {
		return transtatus;
	}

	public void setTranstatus(String transtatus) {
		this.transtatus=transtatus;
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

	public SelectBean<ActTrans> getItem() {
		if (item == null)
			item = new SelectBean<ActTrans>();

		return item;
	}

	public void setItem(SelectBean<ActTrans> item) {
		this.item = item;
	}

}
