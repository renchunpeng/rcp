package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActIntegral implements BaseBean {

	// 积分账户编号;
	private String integralid;

	// 用户编号;
	private String custid;

	// 账户状态;
	private String accountstatus;

	// 积分余额;
	private int integralbalance;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActIntegral> item;

	public ActIntegral() {
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
		return ToolUtils.CompareProperty((ActIntegral)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"积分账户编号:integralid", "用户编号:custid", "账户状态:accountstatus", "积分余额:integralbalance"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.integralid = "";
		this.custid = "";
		this.accountstatus = "";
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

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus=accountstatus;
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

	public SelectBean<ActIntegral> getItem() {
		if (item == null)
			item = new SelectBean<ActIntegral>();

		return item;
	}

	public void setItem(SelectBean<ActIntegral> item) {
		this.item = item;
	}

}
