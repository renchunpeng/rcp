package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdDispatch implements BaseBean {

	// 订单配送编号;
	private String dispatchid;

	// 订单编号;
	private String ordid;

	// 配送人员姓名;
	private String dispatcher;

	// 联系电话;
	private String phonenumber;

	// 配送开始时间;
	private java.util.Date starttime;

	// 预计到达时间;
	private java.util.Date endtime;

	// 快递公司编号;
	private String expressid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdDispatch> item;

	public OrdDispatch() {
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
		return ToolUtils.CompareProperty((OrdDispatch)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"订单配送编号:dispatchid", "订单编号:ordid", "配送人员姓名:dispatcher", "联系电话:phonenumber", "配送开始时间:starttime", "预计到达时间:endtime", "快递公司编号:expressid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.dispatchid = "";
		this.ordid = "";
		this.dispatcher = "";
		this.phonenumber = "";
		this.starttime = ToolUtils.GetMinDate();
		this.endtime = ToolUtils.GetMinDate();
		this.expressid = "";
	}

	public String getDispatchid() {
		return dispatchid;
	}

	public void setDispatchid(String dispatchid) {
		this.dispatchid=dispatchid;
	}

	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid=ordid;
	}

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher=dispatcher;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber=phonenumber;
	}

	public java.util.Date getStarttime() {
		return starttime;
	}

	public void setStarttime(java.util.Date starttime) {
		this.starttime=starttime;
	}

	public java.util.Date getEndtime() {
		return endtime;
	}

	public void setEndtime(java.util.Date endtime) {
		this.endtime=endtime;
	}

	public String getExpressid() {
		return expressid;
	}

	public void setExpressid(String expressid) {
		this.expressid=expressid;
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

	public SelectBean<OrdDispatch> getItem() {
		if (item == null)
			item = new SelectBean<OrdDispatch>();

		return item;
	}

	public void setItem(SelectBean<OrdDispatch> item) {
		this.item = item;
	}

}
