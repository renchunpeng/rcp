package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActInvoiceTransMain implements BaseBean {

	// 发票流水号;
	private String invoicetranid;

	// 发票编号;
	private String invoiceid;

	// 校验码;
	private String invoicecode;

	// 发票状态;
	private String invoicestatus;

	// 发票类型;
	private String invoicetype;

	// 发票性质;
	private String invoiceproperty;

	// 关联发票编号;
	private String invoicerelation;

	// 申请日期;
	private java.util.Date createdate;

	// 开票日期;
	private java.util.Date invoicedate;

	// 开票金额;
	private double invoiceamt;

	// 开票人;
	private String invoiceperson;

	// 用户编号;
	private String custid;

	// 发票单位名称;
	private String invoicetitle;

	// 纳税人识别号;
	private String taxid;

	// 注册地址;
	private String invoiceaddress;

	// 注册电话;
	private String invoicemobile;

	// 开户银行;
	private String openbank;

	// 银行帐号;
	private String bankaccount;

	// 支付方式;
	private String paytype;

	// 支付状态;
	private String paystatus;

	// 支付完成时间;
	private java.util.Date paydate;

	// 支付系统流水号;
	private String paysysid;

	// 应用编号;
	private String appid;

	// 用户快递费用;
	private double expressprice;

	// 金禾快递费用;
	private double jhexpressprice;

	// 快递单号;
	private String expressid;

	// 快递公司;
	private String expresscompany;

	// 邮寄地址;
	private String addressid;

	// 快递时间;
	private java.util.Date expressdate;

	// 备注;
	private String remark;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActInvoiceTrans> item;

	public ActInvoiceTransMain() {
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
		return ToolUtils.CompareProperty((ActInvoiceTrans)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"发票流水号:invoicetranid", "发票编号:invoiceid", "校验码:invoicecode", "发票状态:invoicestatus", "发票类型:invoicetype", "发票性质:invoiceproperty", "关联发票编号:invoicerelation", "申请日期:createdate", "开票日期:invoicedate", "开票金额:invoiceamt", "开票人:invoiceperson", "用户编号:custid", "发票单位名称:invoicetitle", "纳税人识别号:taxid", "注册地址:invoiceaddress", "注册电话:invoicemobile", "开户银行:openbank", "银行帐号:bankaccount", "支付方式:paytype", "支付状态:paystatus", "支付完成时间:paydate", "支付系统流水号:paysysid", "应用编号:appid", "用户快递费用:expressprice", "金禾快递费用:jhexpressprice", "快递单号:expressid", "快递公司:expresscompany", "邮寄地址:addressid", "快递时间:expressdate", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.invoicetranid = "";
		this.invoiceid = "";
		this.invoicecode = "";
		this.invoicestatus = "";
		this.invoicetype = "";
		this.invoiceproperty = "";
		this.invoicerelation = "";
		this.createdate = ToolUtils.GetMinDate();
		this.invoicedate = ToolUtils.GetMinDate();
		this.invoiceamt = 0;
		this.invoiceperson = "";
		this.custid = "";
		this.invoicetitle = "";
		this.taxid = "";
		this.invoiceaddress = "";
		this.invoicemobile = "";
		this.openbank = "";
		this.bankaccount = "";
		this.paytype = "";
		this.paystatus = "";
		this.paydate = ToolUtils.GetMinDate();
		this.paysysid = "";
		this.appid = "";
		this.expressprice = 0;
		this.jhexpressprice = 0;
		this.expressid = "";
		this.expresscompany = "";
		this.addressid = "";
		this.expressdate = ToolUtils.GetMinDate();
		this.remark = "";
	}

	public String getInvoicetranid() {
		return invoicetranid;
	}

	public void setInvoicetranid(String invoicetranid) {
		this.invoicetranid=invoicetranid;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid=invoiceid;
	}

	public String getInvoicecode() {
		return invoicecode;
	}

	public void setInvoicecode(String invoicecode) {
		this.invoicecode=invoicecode;
	}

	public String getInvoicestatus() {
		return invoicestatus;
	}

	public void setInvoicestatus(String invoicestatus) {
		this.invoicestatus=invoicestatus;
	}

	public String getInvoicetype() {
		return invoicetype;
	}

	public void setInvoicetype(String invoicetype) {
		this.invoicetype=invoicetype;
	}

	public String getInvoiceproperty() {
		return invoiceproperty;
	}

	public void setInvoiceproperty(String invoiceproperty) {
		this.invoiceproperty=invoiceproperty;
	}

	public String getInvoicerelation() {
		return invoicerelation;
	}

	public void setInvoicerelation(String invoicerelation) {
		this.invoicerelation=invoicerelation;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public java.util.Date getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(java.util.Date invoicedate) {
		this.invoicedate=invoicedate;
	}

	public double getInvoiceamt() {
		return invoiceamt;
	}

	public void setInvoiceamt(double invoiceamt) {
		this.invoiceamt=invoiceamt;
	}

	public String getInvoiceperson() {
		return invoiceperson;
	}

	public void setInvoiceperson(String invoiceperson) {
		this.invoiceperson=invoiceperson;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getInvoicetitle() {
		return invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle=invoicetitle;
	}

	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid=taxid;
	}

	public String getInvoiceaddress() {
		return invoiceaddress;
	}

	public void setInvoiceaddress(String invoiceaddress) {
		this.invoiceaddress=invoiceaddress;
	}

	public String getInvoicemobile() {
		return invoicemobile;
	}

	public void setInvoicemobile(String invoicemobile) {
		this.invoicemobile=invoicemobile;
	}

	public String getOpenbank() {
		return openbank;
	}

	public void setOpenbank(String openbank) {
		this.openbank=openbank;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount=bankaccount;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype=paytype;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus=paystatus;
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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid=appid;
	}

	public double getExpressprice() {
		return expressprice;
	}

	public void setExpressprice(double expressprice) {
		this.expressprice=expressprice;
	}

	public double getJhexpressprice() {
		return jhexpressprice;
	}

	public void setJhexpressprice(double jhexpressprice) {
		this.jhexpressprice=jhexpressprice;
	}

	public String getExpressid() {
		return expressid;
	}

	public void setExpressid(String expressid) {
		this.expressid=expressid;
	}

	public String getExpresscompany() {
		return expresscompany;
	}

	public void setExpresscompany(String expresscompany) {
		this.expresscompany=expresscompany;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid=addressid;
	}

	public java.util.Date getExpressdate() {
		return expressdate;
	}

	public void setExpressdate(java.util.Date expressdate) {
		this.expressdate=expressdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark=remark;
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

	public SelectBean<ActInvoiceTrans> getItem() {
		if (item == null)
			item = new SelectBean<ActInvoiceTrans>();

		return item;
	}

	public void setItem(SelectBean<ActInvoiceTrans> item) {
		this.item = item;
	}

}
