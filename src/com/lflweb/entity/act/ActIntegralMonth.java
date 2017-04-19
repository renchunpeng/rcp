package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActIntegralMonth implements BaseBean {

	// 积分账户编号;
	private String integralid;

	// 用户编号;
	private String custid;

	// 积分年月;
	private String tranym;

	// 可用积分;
	private int integralbalance;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActIntegralMonth> item;

	public ActIntegralMonth() {
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
		return ToolUtils.CompareProperty((ActIntegralMonth)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"积分账户编号:integralid", "用户编号:custid", "积分年月:tranym", "可用积分:integralbalance"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.integralid = "";
		this.custid = "";
		this.tranym = "";
		this.integralbalance = 0;
	}

	public String getIntegralid() {
		return integralid;
	}

	public void setIntegralid(String integralid) {
		this.integralid=integralid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getTranym() {
		return tranym;
	}

	public void setTranym(String tranym) {
		this.tranym=tranym;
	}

	public int getIntegralbalance() {
		return integralbalance;
	}

	public void setIntegralbalance(int integralbalance) {
		this.integralbalance=integralbalance;
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

	public SelectBean<ActIntegralMonth> getItem() {
		if (item == null)
			item = new SelectBean<ActIntegralMonth>();

		return item;
	}

	public void setItem(SelectBean<ActIntegralMonth> item) {
		this.item = item;
	}

}
