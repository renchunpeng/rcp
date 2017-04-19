package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdImage implements BaseBean {

	// 图片编号;
	private String imageid;

	// 图片名称;
	private String imagename;

	// 图片url;
	private String imageurl;

	// 图片类型;
	private String imagetype;

	// sku编号;
	private String skuid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdImage> item;

	public PrdImage() {
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
		return ToolUtils.CompareProperty((PrdImage)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"图片编号:imageid", "图片名称:imagename", "图片url:imageurl", "图片类型:imagetype", "SKU编号:skuid"};
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
		this.skuid = "";
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

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid=skuid;
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

	public SelectBean<PrdImage> getItem() {
		if (item == null)
			item = new SelectBean<PrdImage>();

		return item;
	}

	public void setItem(SelectBean<PrdImage> item) {
		this.item = item;
	}

}