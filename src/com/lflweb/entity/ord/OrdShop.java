package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdShop implements BaseBean {

	// 用户编号;
	private String custid;

	// 商品编号;
	private String prdid;

	// 商品数量;
	private int prdcount;

	// 加入日期;
	private java.util.Date createdate;

	// 记录状态;
	private String shopstatus;

	// 状态变更日期;
	private java.util.Date updatedate;

	// 商品类型
	private String prdtype;
	
	// 商品类别
	private String prdkind;
	
	// 店铺编号
	private String shopid;
	
	/***预留补全字段***/
	//商品名
	private String prdname;
	
	//商品价格
	private double prdprice;
	
	//商品介绍
	private String prdintroduction;
	
	//商品类型名称
	private String prdtypename;
	
	// 商品类别名称
	private String prdkindname;
	
	// 店铺名称
	private String shopname;
	
	// 商品图片地址
	private String imageurl;
	
	//商品单位
	private String prdunitname;
	
	// 商品简介;
	private String prdbrief;
	
	// 征对客户类型;
	private String custtype;

	// 征对支付方式;
	private String paytype;

	// 征对销售区域;
	private String salezone;

	// 征对用户等级;
	private String custgrade;

	// 商品状态;
	private String prdstatus;

	// 预计上架时间;
	private java.util.Date onsaledate;

	// 预计下架时间;
	private java.util.Date offsaledate;

	// 搜索关键字;
	private String keyword;

	// 商品所属套餐编号;
	private String meaid;

	// 商品热度;
	private int heat;
	
	// 图片名称;
	private String imagename;
	
	// 图片类型;
	private String imagetype;
	
	// 单位
	private String prdunit;
	
	//税率
	private double prdtax;
	
	// 赠送积分
	
	private int giveintegral;
	
	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdShop> item;
	
	public OrdShop() {
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
		return ToolUtils.CompareProperty((OrdShop)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "商品编号:prdid", "商品数量:prdcount", "加入日期:createdate", "记录状态:shopstatus", "状态变更日期:updatedate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.prdid = "";
		this.prdcount = 0;
		this.createdate = ToolUtils.GetMinDate();
		this.shopstatus = "";
		this.updatedate = ToolUtils.GetMinDate();
		this.prdtype = "";
		this.prdkind = "";
		this.shopid = "";
		this.prdname = "";
		this.prdprice = 0.0;
		this.prdintroduction = "";
		this.prdtypename = "";
		this.prdkindname = "";
		this.shopname = "";
		this.imageurl = "";
		this.prdunit = "";
		this.prdunitname = "";
		this.prdtax = 0.00;
		this.giveintegral = 0;
		this.prdbrief = "";
		this.custtype = "";
		this.paytype = "";
		this.salezone = "";
		this.custgrade = "";
		this.prdstatus = "";
		this.keyword = "";
		this.meaid = "";
		this.heat = 0;
		this.imagename = "";
		this.imagetype = "";
	}

	public String getPrdunitname() {
		return prdunitname;
	}

	public void setPrdunitname(String prdunitname) {
		this.prdunitname = prdunitname;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
	}

	public int getPrdcount() {
		return prdcount;
	}

	public void setPrdcount(int prdcount) {
		this.prdcount=prdcount;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public String getShopstatus() {
		return shopstatus;
	}

	public void setShopstatus(String shopstatus) {
		this.shopstatus=shopstatus;
	}

	public java.util.Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(java.util.Date updatedate) {
		this.updatedate=updatedate;
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype = prdtype;
	}

	public String getPrdkind() {
		return prdkind;
	}

	public void setPrdkind(String prdkind) {
		this.prdkind = prdkind;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getPrdname() {
		return prdname;
	}

	public void setPrdname(String prdname) {
		this.prdname = prdname;
	}

	public double getPrdprice() {
		return prdprice;
	}

	public void setPrdprice(double prdprice) {
		this.prdprice = prdprice;
	}

	public String getPrdintroduction() {
		return prdintroduction;
	}

	public void setPrdintroduction(String prdintroduction) {
		this.prdintroduction = prdintroduction;
	}

	public String getPrdtypename() {
		return prdtypename;
	}

	public void setPrdtypename(String prdtypename) {
		this.prdtypename = prdtypename;
	}

	public String getPrdkindname() {
		return prdkindname;
	}

	public void setPrdkindname(String prdkindname) {
		this.prdkindname = prdkindname;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getPrdbrief() {
		return prdbrief;
	}

	public void setPrdbrief(String prdbrief) {
		this.prdbrief = prdbrief;
	}

	public String getCusttype() {
		return custtype;
	}

	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getSalezone() {
		return salezone;
	}

	public void setSalezone(String salezone) {
		this.salezone = salezone;
	}

	public String getCustgrade() {
		return custgrade;
	}

	public void setCustgrade(String custgrade) {
		this.custgrade = custgrade;
	}

	public String getPrdstatus() {
		return prdstatus;
	}

	public void setPrdstatus(String prdstatus) {
		this.prdstatus = prdstatus;
	}

	public java.util.Date getOnsaledate() {
		return onsaledate;
	}

	public void setOnsaledate(java.util.Date onsaledate) {
		this.onsaledate = onsaledate;
	}

	public java.util.Date getOffsaledate() {
		return offsaledate;
	}

	public void setOffsaledate(java.util.Date offsaledate) {
		this.offsaledate = offsaledate;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getMeaid() {
		return meaid;
	}

	public void setMeaid(String meaid) {
		this.meaid = meaid;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public String getPrdunit() {
		return prdunit;
	}

	public void setPrdunit(String prdunit) {
		this.prdunit = prdunit;
	}

	public double getPrdtax() {
		return prdtax;
	}

	public void setPrdtax(double prdtax) {
		this.prdtax = prdtax;
	}

	public int getGiveintegral() {
		return giveintegral;
	}

	public void setGiveintegral(int giveintegral) {
		this.giveintegral = giveintegral;
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

	public SelectBean<OrdShop> getItem() {
		if (item == null)
			item = new SelectBean<OrdShop>();

		return item;
	}

	public void setItem(SelectBean<OrdShop> item) {
		this.item = item;
	}

}
