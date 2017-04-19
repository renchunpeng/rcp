package com.lflweb.entity.bus;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BusThreeTrans implements BaseBean {

	// 交易平台流水号;
	private String tranid;

	// 商户订单号;
	private String orderid;

	// 交易商品名称;
	private String tranname;

	// 流水来源;
	private String transource;

	// 交易类型;
	private String trantype;

	// 交易状态;
	private String transtatus;

	// 收入金额;
	private double tranin;

	// 支出金额;
	private double tranout;

	// 交易平台退款流水号;
	private String tranreturn;

	// 商户退单号;
	private String returnid;

	// 支付宝支付流水号;
	private String payid;

	// 微信公众号;
	private String wechatmp;

	// 微信商户号;
	private String wechatpay;

	// 用户标识;
	private String wechatuser;

	// 导入日期;
	private java.util.Date importdate;

	// 对账标志;
	private String busflag;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BusThreeTrans> item;

	public BusThreeTrans() {
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
		return ToolUtils.CompareProperty((BusThreeTrans)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"交易平台流水号:tranid", "商户订单号:orderid", "交易商品名称:tranname", "流水来源:transource", "交易类型:trantype", "交易状态:transtatus", "收入金额:tranin", "支出金额:tranout", "交易平台退款流水号:tranreturn", "商户退单号:returnid", "支付宝支付流水号:payid", "微信公众号:wechatmp", "微信商户号:wechatpay", "用户标识:wechatuser", "导入日期:importdate", "对账标志:busflag"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.tranid = "";
		this.orderid = "";
		this.tranname = "";
		this.transource = "";
		this.trantype = "";
		this.transtatus = "";
		this.tranin = 0;
		this.tranout = 0;
		this.tranreturn = "";
		this.returnid = "";
		this.payid = "";
		this.wechatmp = "";
		this.wechatpay = "";
		this.wechatuser = "";
		this.importdate = ToolUtils.GetMinDate();
		this.busflag = "";
	}

	public String getTranid() {
		return tranid;
	}

	public void setTranid(String tranid) {
		this.tranid=tranid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getTranname() {
		return tranname;
	}

	public void setTranname(String tranname) {
		this.tranname=tranname;
	}

	public String getTransource() {
		return transource;
	}

	public void setTransource(String transource) {
		this.transource=transource;
	}

	public String getTrantype() {
		return trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype=trantype;
	}

	public String getTranstatus() {
		return transtatus;
	}

	public void setTranstatus(String transtatus) {
		this.transtatus=transtatus;
	}

	public double getTranin() {
		return tranin;
	}

	public void setTranin(double tranin) {
		this.tranin=tranin;
	}

	public double getTranout() {
		return tranout;
	}

	public void setTranout(double tranout) {
		this.tranout=tranout;
	}

	public String getTranreturn() {
		return tranreturn;
	}

	public void setTranreturn(String tranreturn) {
		this.tranreturn=tranreturn;
	}

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid=returnid;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid=payid;
	}

	public String getWechatmp() {
		return wechatmp;
	}

	public void setWechatmp(String wechatmp) {
		this.wechatmp=wechatmp;
	}

	public String getWechatpay() {
		return wechatpay;
	}

	public void setWechatpay(String wechatpay) {
		this.wechatpay=wechatpay;
	}

	public String getWechatuser() {
		return wechatuser;
	}

	public void setWechatuser(String wechatuser) {
		this.wechatuser=wechatuser;
	}

	public java.util.Date getImportdate() {
		return importdate;
	}

	public void setImportdate(java.util.Date importdate) {
		this.importdate=importdate;
	}

	public String getBusflag() {
		return busflag;
	}

	public void setBusflag(String busflag) {
		this.busflag=busflag;
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

	public SelectBean<BusThreeTrans> getItem() {
		if (item == null)
			item = new SelectBean<BusThreeTrans>();

		return item;
	}

	public void setItem(SelectBean<BusThreeTrans> item) {
		this.item = item;
	}

}
