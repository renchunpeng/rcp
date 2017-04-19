package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActInvoice implements BaseBean {

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

	// 备注;
	private String remark;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActInvoice> item;

	public ActInvoice() {
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
		return ToolUtils.CompareProperty((ActInvoice)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "发票单位名称:invoicetitle", "纳税人识别号:taxid", "注册地址:invoiceaddress", "注册电话:invoicemobile", "开户银行:openbank", "银行帐号:bankaccount", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.invoicetitle = "";
		this.taxid = "";
		this.invoiceaddress = "";
		this.invoicemobile = "";
		this.openbank = "";
		this.bankaccount = "";
		this.remark = "";
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

	public SelectBean<ActInvoice> getItem() {
		if (item == null)
			item = new SelectBean<ActInvoice>();

		return item;
	}

	public void setItem(SelectBean<ActInvoice> item) {
		this.item = item;
	}

}
