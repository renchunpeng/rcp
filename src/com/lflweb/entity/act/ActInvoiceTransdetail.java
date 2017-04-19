package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActInvoiceTransdetail implements BaseBean {

	// 发票流水号;
	private String invoicetranid;

	// 订单编号;
	private String orderid;

	// 订单金额;
	private double orderamt;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActInvoiceTransdetail> item;

	public ActInvoiceTransdetail() {
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
		return ToolUtils.CompareProperty((ActInvoiceTransdetail)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"发票流水号:invoicetranid", "订单编号:orderid", "订单金额:orderamt"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.invoicetranid = "";
		this.orderid = "";
		this.orderamt = 0;
	}

	public String getInvoicetranid() {
		return invoicetranid;
	}

	public void setInvoicetranid(String invoicetranid) {
		this.invoicetranid=invoicetranid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public double getOrderamt() {
		return orderamt;
	}

	public void setOrderamt(double orderamt) {
		this.orderamt=orderamt;
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

	public SelectBean<ActInvoiceTransdetail> getItem() {
		if (item == null)
			item = new SelectBean<ActInvoiceTransdetail>();

		return item;
	}

	public void setItem(SelectBean<ActInvoiceTransdetail> item) {
		this.item = item;
	}

}
