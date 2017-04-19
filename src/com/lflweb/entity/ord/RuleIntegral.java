package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class RuleIntegral implements BaseBean {

	// 规则编号;
	private String ruleid;

	// 金额下限;
	private double lowerlimit;

	// 金额上限;
	private double upperlimit1;

	// 赠送积分;
	private int giveintegral;

	// 是否可与其他方是同享;
	private boolean isalsoenjoy;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<RuleIntegral> item;

	public RuleIntegral() {
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
		return ToolUtils.CompareProperty((RuleIntegral)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"规则编号:ruleid", "金额下限:lowerlimit", "金额上限:upperlimit1", "赠送积分:giveintegral", "是否可与其他方是同享:isalsoenjoy"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.ruleid = "";
		this.lowerlimit = 0;
		this.upperlimit1 = 0;
		this.giveintegral = 0;
		this.isalsoenjoy = false;
	}

	public String getRuleid() {
		return ruleid;
	}

	public void setRuleid(String ruleid) {
		this.ruleid=ruleid;
	}

	public double getLowerlimit() {
		return lowerlimit;
	}

	public void setLowerlimit(double lowerlimit) {
		this.lowerlimit=lowerlimit;
	}

	public double getUpperlimit1() {
		return upperlimit1;
	}

	public void setUpperlimit1(double upperlimit1) {
		this.upperlimit1=upperlimit1;
	}

	public int getGiveintegral() {
		return giveintegral;
	}

	public void setGiveintegral(int giveintegral) {
		this.giveintegral=giveintegral;
	}

	public boolean getIsalsoenjoy() {
		return isalsoenjoy;
	}

	public void setIsalsoenjoy(boolean isalsoenjoy) {
		this.isalsoenjoy=isalsoenjoy;
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

	public SelectBean<RuleIntegral> getItem() {
		if (item == null)
			item = new SelectBean<RuleIntegral>();

		return item;
	}

	public void setItem(SelectBean<RuleIntegral> item) {
		this.item = item;
	}

}
