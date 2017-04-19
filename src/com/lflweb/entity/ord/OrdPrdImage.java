package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdPrdImage implements BaseBean {

	// 图片编号;
	private String imageid;

	// 商品评价编号;
	private String pcommentid;

	// 图片名称;
	private String imagename;

	// 图片地址;
	private String imageurl;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdPrdImage> item;

	public OrdPrdImage() {
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
		return ToolUtils.CompareProperty((OrdPrdImage)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"图片编号:imageid", "商品评价编号:pcommentid", "图片名称:imagename", "图片地址:imageurl"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.imageid = "";
		this.pcommentid = "";
		this.imagename = "";
		this.imageurl = "";
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid=imageid;
	}

	public String getPcommentid() {
		return pcommentid;
	}

	public void setPcommentid(String pcommentid) {
		this.pcommentid=pcommentid;
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

	public SelectBean<OrdPrdImage> getItem() {
		if (item == null)
			item = new SelectBean<OrdPrdImage>();

		return item;
	}

	public void setItem(SelectBean<OrdPrdImage> item) {
		this.item = item;
	}

}
