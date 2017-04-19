package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdImgtype implements BaseBean {

	// 图片类型编号;
	private String imgtypeid;

	// 图片类型名称;
	private String imgtypename;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdImgtype> item;

	public PrdImgtype() {
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
		return ToolUtils.CompareProperty((PrdImgtype)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"图片类型编号:imgtypeid", "图片类型名称:imgtypename"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.imgtypeid = "";
		this.imgtypename = "";
	}

	public String getImgtypeid() {
		return imgtypeid;
	}

	public void setImgtypeid(String imgtypeid) {
		this.imgtypeid=imgtypeid;
	}

	public String getImgtypename() {
		return imgtypename;
	}

	public void setImgtypename(String imgtypename) {
		this.imgtypename=imgtypename;
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

	public SelectBean<PrdImgtype> getItem() {
		if (item == null)
			item = new SelectBean<PrdImgtype>();

		return item;
	}

	public void setItem(SelectBean<PrdImgtype> item) {
		this.item = item;
	}

}
