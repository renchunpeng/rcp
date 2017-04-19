package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActIntegralDetail implements BaseBean {

	// 交易跟踪号;
	private String traceid;

	// 积分账户编号;
	private String integralid;

	// 交易积分;
	private int tranintegral;

	// 交易类型;
	private String trantype;

	// 交易日期;
	private java.util.Date trandate;

	// 交易描述;
	private String trandesc;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActIntegralDetail> item;

	public ActIntegralDetail() {
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
		return ToolUtils.CompareProperty((ActIntegralDetail)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"交易跟踪号:traceid", "积分账户编号:integralid", "交易积分:tranintegral", "交易类型:trantype", "交易日期:trandate", "交易描述:trandesc"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.traceid = "";
		this.integralid = "";
		this.tranintegral = 0;
		this.trantype = "";
		this.trandate = ToolUtils.GetMinDate();
		this.trandesc = "";
	}

	public String getTraceid() {
		return traceid;
	}

	public void setTraceid(String traceid) {
		this.traceid=traceid;
	}

	public String getIntegralid() {
		return integralid;
	}

	public void setIntegralid(String integralid) {
		this.integralid=integralid;
	}

	public int getTranintegral() {
		return tranintegral;
	}

	public void setTranintegral(int tranintegral) {
		this.tranintegral=tranintegral;
	}

	public String getTrantype() {
		return trantype;
	}

	public void setTrantype(String trantype) {
		this.trantype=trantype;
	}

	public java.util.Date getTrandate() {
		return trandate;
	}

	public void setTrandate(java.util.Date trandate) {
		this.trandate=trandate;
	}

	public String getTrandesc() {
		return trandesc;
	}

	public void setTrandesc(String trandesc) {
		this.trandesc=trandesc;
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

	public SelectBean<ActIntegralDetail> getItem() {
		if (item == null)
			item = new SelectBean<ActIntegralDetail>();

		return item;
	}

	public void setItem(SelectBean<ActIntegralDetail> item) {
		this.item = item;
	}

}
