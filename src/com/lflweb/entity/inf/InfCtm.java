package com.lflweb.entity.inf;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class InfCtm implements BaseBean {

	// 消息编号;
	private String infoid;

	// 用户编号;
	private String custid;

	// 消息内容;
	private String infocontent;

	// 消息日期;
	private java.util.Date infodate;

	// 消息状态;
	private String infostatus;

	// 消息类型;
	private String infotype;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<InfCtm> item;

	public InfCtm() {
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
		return ToolUtils.CompareProperty((InfCtm)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"消息编号:infoid", "用户编号:custid", "消息内容:infocontent", "消息日期:infodate", "消息状态:infostatus", "消息类型:infotype"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.infoid = "";
		this.custid = "";
		this.infocontent = "";
		this.infodate = ToolUtils.GetMinDate();
		this.infostatus = "";
		this.infotype = "";
	}

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid=infoid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getInfocontent() {
		return infocontent;
	}

	public void setInfocontent(String infocontent) {
		this.infocontent=infocontent;
	}

	public java.util.Date getInfodate() {
		return infodate;
	}

	public void setInfodate(java.util.Date infodate) {
		this.infodate=infodate;
	}

	public String getInfostatus() {
		return infostatus;
	}

	public void setInfostatus(String infostatus) {
		this.infostatus=infostatus;
	}

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype=infotype;
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

	public SelectBean<InfCtm> getItem() {
		if (item == null)
			item = new SelectBean<InfCtm>();

		return item;
	}

	public void setItem(SelectBean<InfCtm> item) {
		this.item = item;
	}

}
