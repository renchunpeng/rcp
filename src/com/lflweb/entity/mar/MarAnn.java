package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarAnn implements BaseBean {

	// 通知编号;
	private String annid;

	// 通知序号;
	private String annorder;

	// 通知标题;
	private String anntitle;

	// 通知内容;
	private String anndetail;

	// 通知开始时间;
	private java.util.Date ondate;

	// 通知结束时间;
	private java.util.Date offdate;

	// 创建时间;
	private java.util.Date createdate;

	// 创建人;
	private String createoperator;

	// 最近修改日期;
	private java.util.Date modifydate;

	// 备注;
	private String remark;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarAnn> item;

	public MarAnn() {
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
		return ToolUtils.CompareProperty((MarAnn)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"通知编号:annid", "通知序号:annorder", "通知标题:anntitle", "通知内容:anndetail", "通知开始时间:ondate", "通知结束时间:offdate", "创建时间:createdate", "创建人:createoperator", "最近修改日期:modifydate", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.annid = "";
		this.annorder = "";
		this.anntitle = "";
		this.anndetail = "";
		this.ondate = ToolUtils.GetMinDate();
		this.offdate = ToolUtils.GetMinDate();
		this.createdate = ToolUtils.GetMinDate();
		this.createoperator = "";
		this.modifydate = ToolUtils.GetMinDate();
		this.remark = "";
	}

	public String getAnnid() {
		return annid;
	}

	public void setAnnid(String annid) {
		this.annid=annid;
	}

	public String getAnnorder() {
		return annorder;
	}

	public void setAnnorder(String annorder) {
		this.annorder=annorder;
	}

	public String getAnntitle() {
		return anntitle;
	}

	public void setAnntitle(String anntitle) {
		this.anntitle=anntitle;
	}

	public String getAnndetail() {
		return anndetail;
	}

	public void setAnndetail(String anndetail) {
		this.anndetail=anndetail;
	}

	public java.util.Date getOndate() {
		return ondate;
	}

	public void setOndate(java.util.Date ondate) {
		this.ondate=ondate;
	}

	public java.util.Date getOffdate() {
		return offdate;
	}

	public void setOffdate(java.util.Date offdate) {
		this.offdate=offdate;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public String getCreateoperator() {
		return createoperator;
	}

	public void setCreateoperator(String createoperator) {
		this.createoperator=createoperator;
	}

	public java.util.Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(java.util.Date modifydate) {
		this.modifydate=modifydate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark=remark;
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

	public SelectBean<MarAnn> getItem() {
		if (item == null)
			item = new SelectBean<MarAnn>();

		return item;
	}

	public void setItem(SelectBean<MarAnn> item) {
		this.item = item;
	}

}
