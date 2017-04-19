package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdPackagedetail implements BaseBean {

	// 套餐编号;
	private String packageid;

	// 商品编号;
	private String prdid;

	// 商品数量;
	private int prdnum;

	// 商品名称;
	private String prdname;
	
//	// 商品sku编号 图片;
//	private PrdImage prdImage;
	
	//商品基础信息
	private PrdBase prdBase;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdPackagedetail> item;

	public PrdPackagedetail() {
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
		return ToolUtils.CompareProperty((PrdPackagedetail) item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { "套餐编号:packageid", "商品编号:prdid", "商品数量:prdnum", "商品名称:prdname" };
	}

	@Override
	public String[] OnExclusions() {
		return new String[] { "deal", "item", "search" };
	}

	@Override
	public void OnInit() {
		this.packageid = "";
		this.prdid = "";
		this.prdnum = 0;
		this.prdname = "";
//		this.prdImage = new PrdImage();
		this.prdBase = new PrdBase();
	}

//	public PrdImage getPrdImage() {
//		return prdImage;
//	}
//
//	public void setPrdImage(PrdImage prdImage) {
//		this.prdImage = prdImage;
//	}


	public String getPackageid() {
		return packageid;
	}

	public PrdBase getPrdBase() {
		return prdBase;
	}

	public void setPrdBase(PrdBase prdBase) {
		this.prdBase = prdBase;
	}

	public void setPackageid(String packageid) {
		this.packageid = packageid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}

	public int getPrdnum() {
		return prdnum;
	}

	public void setPrdnum(int prdnum) {
		this.prdnum = prdnum;
	}

	public String getPrdname() {
		return prdname;
	}

	public void setPrdname(String prdname) {
		this.prdname = prdname;
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

	public SelectBean<PrdPackagedetail> getItem() {
		if (item == null)
			item = new SelectBean<PrdPackagedetail>();

		return item;
	}

	public void setItem(SelectBean<PrdPackagedetail> item) {
		this.item = item;
	}

}
