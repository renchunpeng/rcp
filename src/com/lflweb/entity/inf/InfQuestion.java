package com.lflweb.entity.inf;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class InfQuestion implements BaseBean {

	// 问题编号;
	private String questionid;

	// 提交人;
	private String custid;

	// 提交日期;
	private java.util.Date submitdate;

	// 问题描述;
	private String quedetail;

	// 是否已读;
	private boolean idread;

	// 查看人;
	private String reader;

	// 查看日期;
	private java.util.Date readdate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<InfQuestion> item;

	public InfQuestion() {
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
		return ToolUtils.CompareProperty((InfQuestion)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"问题编号:questionid", "提交人:custid", "提交日期:submitdate", "问题描述:quedetail", "是否已读:idread", "查看人:reader", "查看日期:readdate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.questionid = "";
		this.custid = "";
		this.submitdate = ToolUtils.GetMinDate();
		this.quedetail = "";
		this.idread = false;
		this.reader = "";
		this.readdate = ToolUtils.GetMinDate();
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid=questionid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public java.util.Date getSubmitdate() {
		return submitdate;
	}

	public void setSubmitdate(java.util.Date submitdate) {
		this.submitdate=submitdate;
	}

	public String getQuedetail() {
		return quedetail;
	}

	public void setQuedetail(String quedetail) {
		this.quedetail=quedetail;
	}

	public boolean getIdread() {
		return idread;
	}

	public void setIdread(boolean idread) {
		this.idread=idread;
	}

	public String getReader() {
		return reader;
	}

	public void setReader(String reader) {
		this.reader=reader;
	}

	public java.util.Date getReaddate() {
		return readdate;
	}

	public void setReaddate(java.util.Date readdate) {
		this.readdate=readdate;
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

	public SelectBean<InfQuestion> getItem() {
		if (item == null)
			item = new SelectBean<InfQuestion>();

		return item;
	}

	public void setItem(SelectBean<InfQuestion> item) {
		this.item = item;
	}

}
