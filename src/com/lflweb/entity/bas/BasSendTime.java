package com.lflweb.entity.bas;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class BasSendTime implements BaseBean {

	// 时间段编号;
	private String timeid;

	// 时间段说明;
	private String timedetail;

	// 开始时间;
	private String sendbegin;

	// 结束时间;
	private String sendend;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<BasSendTime> item;

	public BasSendTime() {
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
		return ToolUtils.CompareProperty((BasSendTime)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"时间段编号:timeid", "时间段说明:timedetail", "开始时间:sendbegin", "结束时间:sendend"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.timeid = "";
		this.timedetail = "";
		this.sendbegin = "";
		this.sendend = "";
	}

	public String getTimeid() {
		return timeid;
	}

	public void setTimeid(String timeid) {
		this.timeid=timeid;
	}

	public String getTimedetail() {
		return timedetail;
	}

	public void setTimedetail(String timedetail) {
		this.timedetail=timedetail;
	}

	public String getSendbegin() {
		return sendbegin;
	}

	public void setSendbegin(String sendbegin) {
		this.sendbegin=sendbegin;
	}

	public String getSendend() {
		return sendend;
	}

	public void setSendend(String sendend) {
		this.sendend=sendend;
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

	public SelectBean<BasSendTime> getItem() {
		if (item == null)
			item = new SelectBean<BasSendTime>();

		return item;
	}

	public void setItem(SelectBean<BasSendTime> item) {
		this.item = item;
	}

}
