package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdBaseimg implements BaseBean {

	// 图片编号;
	private String imageid;

	// 图片名称;
	private String imagename;

	// 图片url;
	private String imageurl;

	// 图片类型;
	private String imagetype;

	// 商品编号;
	private String prdid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdBaseimg> item;

	public PrdBaseimg() {
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
		return ToolUtils.CompareProperty((PrdBaseimg)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"图片编号:imageid", "图片名称:imagename", "图片url:imageurl", "图片类型:imagetype", "商品编号:prdid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.imageid = "";
		this.imagename = "";
		this.imageurl = "";
		this.imagetype = "";
		this.prdid = "";
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid=imageid;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename=imagename;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl=imageurl;
	}

	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype=imagetype;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
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

	public SelectBean<PrdBaseimg> getItem() {
		if (item == null)
			item = new SelectBean<PrdBaseimg>();

		return item;
	}

	public void setItem(SelectBean<PrdBaseimg> item) {
		this.item = item;
	}

}
