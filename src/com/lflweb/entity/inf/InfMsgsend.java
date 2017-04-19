package com.lflweb.entity.inf;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class InfMsgsend implements BaseBean {

	// 发送编号;
	private String sendid;

	// 接收人;
	private String custid;

	// 手机号码;
	private String custmobile;

	// 发送时间;
	private java.util.Date senddate;

	// 发送类型;
	private String sendtype;

	// 成功标志;
	private String sengflag;

	// 短信内容;
	private String msgcontent;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<InfMsgsend> item;

	public InfMsgsend() {
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
		return ToolUtils.CompareProperty((InfMsgsend)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"发送编号:sendid", "接收人:custid", "手机号码:custmobile", "发送时间:senddate", "发送类型:sendtype", "成功标志:sengflag", "短信内容:msgcontent"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.sendid = "";
		this.custid = "";
		this.custmobile = "";
		this.senddate = ToolUtils.GetMinDate();
		this.sendtype = "";
		this.sengflag = "";
		this.msgcontent = "";
	}

	public String getSendid() {
		return sendid;
	}

	public void setSendid(String sendid) {
		this.sendid=sendid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getCustmobile() {
		return custmobile;
	}

	public void setCustmobile(String custmobile) {
		this.custmobile=custmobile;
	}

	public java.util.Date getSenddate() {
		return senddate;
	}

	public void setSenddate(java.util.Date senddate) {
		this.senddate=senddate;
	}

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype=sendtype;
	}

	public String getSengflag() {
		return sengflag;
	}

	public void setSengflag(String sengflag) {
		this.sengflag=sengflag;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent=msgcontent;
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

	public SelectBean<InfMsgsend> getItem() {
		if (item == null)
			item = new SelectBean<InfMsgsend>();

		return item;
	}

	public void setItem(SelectBean<InfMsgsend> item) {
		this.item = item;
	}

}
