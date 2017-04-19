package com.lflweb.entity.prd;

import com.gpersist.entity.BaseBean;
import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.publics.DataDeal;
import com.gpersist.entity.publics.SearchParams;
import com.gpersist.entity.publics.SelectBean;
import com.gpersist.utils.ToolUtils;

public class PrdBigType implements BaseBean {

	// 类别id;
	private String columnid;

	// 类别名称;
	private String columnname;

	// 类别图片url;
	private String columnimgurl;
	
	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdAllSelect> item;

	public PrdBigType() {
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
		return new String[] { "类别id:columnid", "类别名称:columnname", "类别图片url:columnimgurl" };
	}

	@Override
	public String[] OnExclusions() {
		return new String[] { "deal", "item", "search" };
	}

	@Override
	public void OnInit() {
		this.columnid = "";
		this.columnname = "";
		this.columnimgurl = "";
	}


	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public String getColumnimgurl() {
		return columnimgurl;
	}

	public void setColumnimgurl(String columnimgurl) {
		this.columnimgurl = columnimgurl;
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
