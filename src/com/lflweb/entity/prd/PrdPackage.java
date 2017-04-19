package com.lflweb.entity.prd;

import java.util.ArrayList;
import java.util.List;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdPackage implements BaseBean {

	// 套餐编号;
	private String packageid;

	// 品牌号;
	private String brandid;

	// 套餐名称;
	private String packagename;

	// 套餐简介;
	private String packagedescription;

	// 商品类型;
	private String packagetype;

	// 套餐状态;
	private String packagestatus;

	// 套餐基础价格;
	private double packageprice;

	// 套餐单位;
	private String packageunit;

	// 套餐税率;
	private double packagetax;

	// 预计上架时间;
	private java.util.Date onsaledate;

	// 预计下架时间;
	private java.util.Date offsaledate;

	// 创建日期;
	private java.util.Date createdate;

	// 创建人;
	private String createoperator;

	// 最近修改日期;
	private java.util.Date modifydate;

	// 备注;
	private String remark;

	// 搜索关键字;
	private String keyword;

	// 套餐热度;
	private int heat;

	// 套餐商家编号;
	private String shopid;

	// 套餐介绍;
	private String packageintro;

	// 套餐视频;
	private String packagevedio;
	
	//店铺名称
	private String shopname;
	
	// 套餐图片;
	private PrdPackageimg prdPackageimg;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdPackage> item;
	
	//新增
	private String prdkind;
	
	// 购买赠送积分;
	private int giveintegral;
	
	//套餐详情
	private List<PrdPackagedetail> prdPackagedetails;
	
	//套餐图片
	private String imageurl;

	public PrdPackage() {
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
		return ToolUtils.CompareProperty((PrdPackage)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"套餐编号:packageid", "品牌号:brandid", "套餐名称:packagename", "套餐简介:packagedescription", "商品类型:packagetype", "套餐状态:packagestatus", "套餐基础价格:packageprice", "套餐单位:packageunit", "套餐税率:packagetax", "预计上架时间:onsaledate", "预计下架时间:offsaledate", "创建日期:createdate", "创建人:createoperator", "最近修改日期:modifydate", "备注:remark", "搜索关键字:keyword", "套餐热度:heat", "套餐商家编号:shopid", "套餐介绍:packageintro", "套餐视频:packagevedio"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.packageid = "";
		this.brandid = "";
		this.packagename = "";
		this.packagedescription = "";
		this.packagetype = "";
		this.packagestatus = "";
		this.packageprice = 0;
		this.packageunit = "";
		this.packagetax = 0;
//		this.onsaledate = ToolUtils.GetMinDate();
//		this.offsaledate = ToolUtils.GetMinDate();
//		this.createdate = ToolUtils.GetMinDate();
		this.createoperator = "";
//		this.modifydate = ToolUtils.GetMinDate();
		this.remark = "";
		this.keyword = "";
		this.heat = 0;
		this.shopid = "";
		this.packageintro = "";
		this.packagevedio = "";
		this.prdkind = "";
		this.giveintegral = 0;
		this.prdPackageimg = new PrdPackageimg();
		this.shopname = "";
		this.prdPackagedetails = new ArrayList<PrdPackagedetail>();
		this.imageurl = "";
	}



	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public List<PrdPackagedetail> getPrdPackagedetails() {
		return prdPackagedetails;
	}

	public void setPrdPackagedetails(List<PrdPackagedetail> prdPackagedetails) {
		this.prdPackagedetails = prdPackagedetails;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public PrdPackageimg getPrdPackageimg() {
		return prdPackageimg;
	}

	public void setPrdPackageimg(PrdPackageimg prdPackageimg) {
		this.prdPackageimg = prdPackageimg;
	}

	public int getGiveintegral() {
		return giveintegral;
	}

	public void setGiveintegral(int giveintegral) {
		this.giveintegral = giveintegral;
	}

	public String getPrdkind() {
		return prdkind;
	}

	public void setPrdkind(String prdkind) {
		this.prdkind = prdkind;
	}

	public String getPackageid() {
		return packageid;
	}

	public void setPackageid(String packageid) {
		this.packageid=packageid;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid=brandid;
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename=packagename;
	}

	public String getPackagedescription() {
		return packagedescription;
	}

	public void setPackagedescription(String packagedescription) {
		this.packagedescription=packagedescription;
	}

	public String getPackagetype() {
		return packagetype;
	}

	public void setPackagetype(String packagetype) {
		this.packagetype=packagetype;
	}

	public String getPackagestatus() {
		return packagestatus;
	}

	public void setPackagestatus(String packagestatus) {
		this.packagestatus=packagestatus;
	}

	public double getPackageprice() {
		return packageprice;
	}

	public void setPackageprice(double packageprice) {
		this.packageprice=packageprice;
	}

	public String getPackageunit() {
		return packageunit;
	}

	public void setPackageunit(String packageunit) {
		this.packageunit=packageunit;
	}

	public double getPackagetax() {
		return packagetax;
	}

	public void setPackagetax(double packagetax) {
		this.packagetax = packagetax;
	}

	public java.util.Date getOnsaledate() {
		return onsaledate;
	}

	public void setOnsaledate(java.util.Date onsaledate) {
		this.onsaledate=onsaledate;
	}

	public java.util.Date getOffsaledate() {
		return offsaledate;
	}

	public void setOffsaledate(java.util.Date offsaledate) {
		this.offsaledate=offsaledate;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword=keyword;
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat=heat;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid=shopid;
	}

	public String getPackageintro() {
		return packageintro;
	}

	public void setPackageintro(String packageintro) {
		this.packageintro=packageintro;
	}

	public String getPackagevedio() {
		return packagevedio;
	}

	public void setPackagevedio(String packagevedio) {
		this.packagevedio=packagevedio;
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

	public SelectBean<PrdPackage> getItem() {
		if (item == null)
			item = new SelectBean<PrdPackage>();

		return item;
	}

	public void setItem(SelectBean<PrdPackage> item) {
		this.item = item;
	}

}
