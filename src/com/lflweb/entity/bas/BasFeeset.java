package com.lflweb.entity.bas;

import com.gpersist.entity.BaseBean;
import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.publics.DataDeal;
import com.gpersist.entity.publics.SearchParams;
import com.gpersist.entity.publics.SelectBean;
import com.gpersist.utils.ToolUtils;

public class BasFeeset implements BaseBean {

	// 费用编号;
	private String feeid;

	// 费用描述;
	private String feedesc;

	// 费用金额/比率;
	private double fee;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BasFeeset> item;

	public BasFeeset() {
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
		return ToolUtils.CompareProperty((BasFeeset)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"费用编号:feeid", "费用描述:feedesc", "费用金额/比率:fee"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.feeid = "";
		this.feedesc = "";
		this.fee = 0;
	}

	public String getFeeid() {
		return feeid;
	}

	public void setFeeid(String feeid) {
		this.feeid=feeid;
	}

	public String getFeedesc() {
		return feedesc;
	}

	public void setFeedesc(String feedesc) {
		this.feedesc=feedesc;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee=fee;
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

	public SelectBean<BasFeeset> getItem() {
		if (item == null)
			item = new SelectBean<BasFeeset>();

		return item;
	}

	public void setItem(SelectBean<BasFeeset> item) {
		this.item = item;
	}

}
