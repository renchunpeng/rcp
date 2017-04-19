package com.lflweb.entity.bus;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BusErrorDetail implements BaseBean {

	// 文件编号;
	private String fileid;

	// 订单编号;
	private String orderid;

	// 订单金额;
	private double orderamt;

	// 实际金额;
	private double realamt;

	// 差错说明;
	private String errormark;

	// 处理人员;
	private String dealoperator;

	// 处理标志;
	private String dealflag;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BusErrorDetail> item;

	public BusErrorDetail() {
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
		return ToolUtils.CompareProperty((BusErrorDetail)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"文件编号:fileid", "订单编号:orderid", "订单金额:orderamt", "实际金额:realamt", "差错说明:errormark", "处理人员:dealoperator", "处理标志:dealflag"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.fileid = "";
		this.orderid = "";
		this.orderamt = 0;
		this.realamt = 0;
		this.errormark = "";
		this.dealoperator = "";
		this.dealflag = "";
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid=fileid;
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

	public double getRealamt() {
		return realamt;
	}

	public void setRealamt(double realamt) {
		this.realamt=realamt;
	}

	public String getErrormark() {
		return errormark;
	}

	public void setErrormark(String errormark) {
		this.errormark=errormark;
	}

	public String getDealoperator() {
		return dealoperator;
	}

	public void setDealoperator(String dealoperator) {
		this.dealoperator=dealoperator;
	}

	public String getDealflag() {
		return dealflag;
	}

	public void setDealflag(String dealflag) {
		this.dealflag=dealflag;
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

	public SelectBean<BusErrorDetail> getItem() {
		if (item == null)
			item = new SelectBean<BusErrorDetail>();

		return item;
	}

	public void setItem(SelectBean<BusErrorDetail> item) {
		this.item = item;
	}

}
