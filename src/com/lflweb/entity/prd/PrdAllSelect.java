package com.lflweb.entity.prd;

import com.gpersist.entity.BaseBean;
import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.publics.DataDeal;
import com.gpersist.entity.publics.SearchParams;
import com.gpersist.entity.publics.SelectBean;
import com.gpersist.utils.ToolUtils;

public class PrdAllSelect implements BaseBean {

	// 商品编号;
	private String prdid;

	// 商品类型;
	private String prdtype;

	// 创建时间;
	private java.util.Date createdate;
	
	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdAllSelect> item;

	public PrdAllSelect() {
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
		return ToolUtils.CompareProperty((PrdAllSelect) item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { "商品编号:prdid", "商品类别:prdtype", "创建时间:createdate" };
	}

	@Override
	public String[] OnExclusions() {
		return new String[] { "deal", "item", "search" };
	}

	@Override
	public void OnInit() {
		this.prdid = "";
		this.prdtype = "";
		this.createdate = ToolUtils.GetMinDate();
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype = prdtype;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public SearchParams getSearch() {
		return search;
	}

	public void setSearch(SearchParams search) {
		this.search = search;
	}

	public DataDeal getDeal() {
		return deal;
	}

	public void setDeal(DataDeal deal) {
		this.deal = deal;
	}

	public SelectBean<PrdAllSelect> getItem() {
		return item;
	}

	public void setItem(SelectBean<PrdAllSelect> item) {
		this.item = item;
	}

}
