package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdPrdComment implements BaseBean {

	// 商品评价编号;
	private String pcommentid;

	// 订单编号;
	private String orderid;

	// 商品编号;
	private String prdid;

	// 商品满意度;
	private String prdsatisfaction;

	// 商品评价;
	private String prdcomment;

	// 评价日期;
	private java.util.Date commdate;

	// 评价回复;
	private String commreply;

	// 回复人;
	private String replyer;

	// 回复日期;
	private java.util.Date replydate;

	// 评价标记;
	private String commstatus;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdPrdComment> item;

	public OrdPrdComment() {
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
		return ToolUtils.CompareProperty((OrdPrdComment)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"商品评价编号:pcommentid", "订单编号:orderid", "商品编号:prdid", "商品满意度:prdsatisfaction", "商品评价:prdcomment", "评价日期:commdate", "评价回复:commreply", "回复人:replyer", "回复日期:replydate", "评价标记:commstatus"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.pcommentid = "";
		this.orderid = "";
		this.prdid = "";
		this.prdsatisfaction = "";
		this.prdcomment = "";
		this.commdate = ToolUtils.GetMinDate();
		this.commreply = "";
		this.replyer = "";
		this.replydate = ToolUtils.GetMinDate();
		this.commstatus = "";
	}

	public String getPcommentid() {
		return pcommentid;
	}

	public void setPcommentid(String pcommentid) {
		this.pcommentid=pcommentid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
	}

	public String getPrdsatisfaction() {
		return prdsatisfaction;
	}

	public void setPrdsatisfaction(String prdsatisfaction) {
		this.prdsatisfaction=prdsatisfaction;
	}

	public String getPrdcomment() {
		return prdcomment;
	}

	public void setPrdcomment(String prdcomment) {
		this.prdcomment=prdcomment;
	}

	public java.util.Date getCommdate() {
		return commdate;
	}

	public void setCommdate(java.util.Date commdate) {
		this.commdate=commdate;
	}

	public String getCommreply() {
		return commreply;
	}

	public void setCommreply(String commreply) {
		this.commreply=commreply;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer=replyer;
	}

	public java.util.Date getReplydate() {
		return replydate;
	}

	public void setReplydate(java.util.Date replydate) {
		this.replydate=replydate;
	}

	public String getCommstatus() {
		return commstatus;
	}

	public void setCommstatus(String commstatus) {
		this.commstatus=commstatus;
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

	public SelectBean<OrdPrdComment> getItem() {
		if (item == null)
			item = new SelectBean<OrdPrdComment>();

		return item;
	}

	public void setItem(SelectBean<OrdPrdComment> item) {
		this.item = item;
	}

}
