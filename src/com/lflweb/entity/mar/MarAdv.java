package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarAdv implements BaseBean {

	// 广告编号;
	private String advid;

	// 广告序号;
	private String advorder;

	// 广告标题;
	private String advtitle;

	// 广告表述;
	private String advdescription;

	// 广告图片链接;
	private String advimgurl;

	// 广告跳转链接;
	private String advredurl;

	// 广告播放开始时间;
	private java.util.Date ondate;

	// 广告播放结束时间;
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

	private SelectBean<MarAdv> item;
	
	// advtype
	private String advtype;
	
	//uploadimgurl
	private String uploadimgurl;

	public MarAdv() {
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
		return ToolUtils.CompareProperty((MarAdv)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"广告编号:advid", "广告序号:advorder", "广告标题:advtitle", "广告表述:advdescription", "广告图片链接:advimgurl", "广告跳转链接:advredurl", "广告播放开始时间:ondate", "广告播放结束时间:offdate", "创建时间:createdate", "创建人:createoperator", "最近修改日期:modifydate", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.advid = "";
		this.advorder = "";
		this.advtitle = "";
		this.advdescription = "";
		this.advimgurl = "";
		this.advredurl = "";
		this.ondate = ToolUtils.GetMinDate();
		this.offdate = ToolUtils.GetMinDate();
		this.createdate = ToolUtils.GetMinDate();
		this.createoperator = "";
		this.modifydate = ToolUtils.GetMinDate();
		this.remark = "";
		this.advtype = "";
		this.uploadimgurl = "";
	}

	public String getAdvtype() {
		return advtype;
	}

	public void setAdvtype(String advtype) {
		this.advtype = advtype;
	}

	public String getUploadimgurl() {
		return uploadimgurl;
	}

	public void setUploadimgurl(String uploadimgurl) {
		this.uploadimgurl = uploadimgurl;
	}

	public String getAdvid() {
		return advid;
	}

	public void setAdvid(String advid) {
		this.advid=advid;
	}

	public String getAdvorder() {
		return advorder;
	}

	public void setAdvorder(String advorder) {
		this.advorder=advorder;
	}

	public String getAdvtitle() {
		return advtitle;
	}

	public void setAdvtitle(String advtitle) {
		this.advtitle=advtitle;
	}

	public String getAdvdescription() {
		return advdescription;
	}

	public void setAdvdescription(String advdescription) {
		this.advdescription=advdescription;
	}

	public String getAdvimgurl() {
		return advimgurl;
	}

	public void setAdvimgurl(String advimgurl) {
		this.advimgurl=advimgurl;
	}

	public String getAdvredurl() {
		return advredurl;
	}

	public void setAdvredurl(String advredurl) {
		this.advredurl=advredurl;
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

	public SelectBean<MarAdv> getItem() {
		if (item == null)
			item = new SelectBean<MarAdv>();

		return item;
	}

	public void setItem(SelectBean<MarAdv> item) {
		this.item = item;
	}

}
