package com.lflweb.entity.log;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class LogOrderPay implements BaseBean {

	// 日志编号;
	private String logid;

	// 用户编号;
	private String custid;

	// 订单编号;
	private String orderid;

	// 支付类型;
	private String paytype;

	// 支付/退款完成时间;
	private java.util.Date paydate;

	// 支付系统流水号;
	private String paysysid;

	// 付款/退款金额;
	private double payprice;

	// 支付交易类型;
	private String paykind;

	// 手续费金额;
	private double servicefee;

	// 手续费费率;
	private double feerate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<LogOrderPay> item;

	public LogOrderPay() {
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
		return ToolUtils.CompareProperty((LogOrderPay)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"日志编号:logid", "用户编号:custid", "订单编号:orderid", "支付类型:paytype", "支付/退款完成时间:paydate", "支付系统流水号:paysysid", "付款/退款金额:payprice", "支付交易类型:paykind", "手续费金额:servicefee", "手续费费率:feerate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.logid = "";
		this.custid = "";
		this.orderid = "";
		this.paytype = "";
		this.paydate = ToolUtils.GetMinDate();
		this.paysysid = "";
		this.payprice = 0;
		this.paykind = "";
		this.servicefee = 0;
		this.feerate = 0;
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

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype=paytype;
	}

	public java.util.Date getPaydate() {
		return paydate;
	}

	public void setPaydate(java.util.Date paydate) {
		this.paydate=paydate;
	}

	public String getPaysysid() {
		return paysysid;
	}

	public void setPaysysid(String paysysid) {
		this.paysysid=paysysid;
	}

	public double getPayprice() {
		return payprice;
	}

	public void setPayprice(double payprice) {
		this.payprice=payprice;
	}

	public String getPaykind() {
		return paykind;
	}

	public void setPaykind(String paykind) {
		this.paykind=paykind;
	}

	public double getServicefee() {
		return servicefee;
	}

	public void setServicefee(double servicefee) {
		this.servicefee=servicefee;
	}

	public double getFeerate() {
		return feerate;
	}

	public void setFeerate(double feerate) {
		this.feerate=feerate;
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

	public SelectBean<LogOrderPay> getItem() {
		if (item == null)
			item = new SelectBean<LogOrderPay>();

		return item;
	}

	public void setItem(SelectBean<LogOrderPay> item) {
		this.item = item;
	}

}
