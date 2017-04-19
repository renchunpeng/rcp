package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;
import com.lflweb.entity.mar.MarShopdetail;

public class PrdBase implements BaseBean {

	// 商品编号;
	private String prdid;

	// 商品名称;
	private String prdname;

	// 商品简介;
	private String prdbrief;

	// 商品类型;
	private String prdtype;

	// 商品基础价格;
	private double prdprice;

	// 单位类型;
	private String prdunit;

	// 商品税率;
	private double prdtax;

	// 征对销售渠道;
	private String custsource;

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

	// 商品所属套餐编号;
	private String meaid;

	// 商品热度;
	private int heat;

	// 商品商家编号;
	private String shopid;

	// 商品介绍;
	private String prdintroduction;

	// 商品视频;
	private String prdvedio;

	// 商品sku编号 图片;
	private PrdImage prdImage;

	// 商品图片信息
	private String imageid;

	// 店铺名称
	private String shopname;
	
	//商品SKU编号
	private String skuid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdBase> item;

	// 新增
	private String prdkind;

	// 购买赠送积分
	private int giveintegral;
	
	//商品Sku表信息
	//品牌表
	private String brandid;
	
	//SKU名称
	private String SKUname;
	
	//SKU描述
	private String SKUdescription;
	
	//SKU类型 
	private String SKUtype;
	
	//商品规格参数
	private String prdspec;
	
	//会员卡具体种类
	private String memtype;
	
	//商品sku图片
	private String imageurl;
	

	public PrdBase() {
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
		return ToolUtils.CompareProperty((PrdBase) item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { "商品编号:prdid", "商品名称:prdname", "商品简介:prdbrief", "商品类型:prdtype", "商品基础价格:prdprice", "单位类型:prdunit", "商品税率:prdtax", "征对销售渠道:custsource", "征对客户类型:custtype", "征对支付方式:paytype",
				"征对销售区域:salezone", "征对用户等级:custgrade", "商品状态:prdstatus", "预计上架时间:onsaledate", "预计下架时间:offsaledate", "创建日期:createdate", "创建人:createoperator", "最近修改日期:modifydate", "备注:remark", "搜索关键字:keyword",
				"商品所属套餐编号:meaid", "商品热度:heat", "商品商家编号:shopid", "商品介绍:prdintroduction", "商品视频:prdvedio", "商品SKU编号:skuid" };
	}

	@Override
	public String[] OnExclusions() {
		return new String[] { "deal", "item", "search" };
	}

	@Override
	public void OnInit() {
		this.prdid = "";
		this.prdname = "";
		this.prdbrief = "";
		this.prdtype = "";
		this.prdprice = 0;
		this.prdunit = "";
		this.prdtax = 0;
		this.custsource = "";
		this.custtype = "";
		this.paytype = "";
		this.salezone = "";
		this.custgrade = "";
		this.prdstatus = "";
//		this.onsaledate = ToolUtils.GetMinDate();
//		this.offsaledate = ToolUtils.GetMinDate();
//		this.createdate = ToolUtils.GetMinDate();
		this.createoperator = "";
//		this.modifydate = ToolUtils.GetMinDate();
		this.remark = "";
		this.keyword = "";
		this.meaid = "";
		this.heat = 0;
		this.shopid = "";
		this.prdintroduction = "";
		this.prdvedio = "";
		this.prdImage = new PrdImage();
		this.prdkind = "";
		this.giveintegral = 0;
		this.imageid = "";
		this.shopname = "";
		this.skuid = "";
		this.brandid = "";
		this.SKUname = "";
		this.SKUdescription = "";
		this.SKUtype = "";
		this.prdspec = "";
		this.memtype = "";
		this.imageurl = "";
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getMemtype() {
		return memtype;
	}

	public void setMemtype(String memtype) {
		this.memtype = memtype;
	}

	public String getPrdspec() {
		return prdspec;
	}

	public void setPrdspec(String prdspec) {
		this.prdspec = prdspec;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getSKUname() {
		return SKUname;
	}

	public void setSKUname(String sKUname) {
		SKUname = sKUname;
	}

	public String getSKUdescription() {
		return SKUdescription;
	}

	public void setSKUdescription(String sKUdescription) {
		SKUdescription = sKUdescription;
	}

	public String getSKUtype() {
		return SKUtype;
	}

	public void setSKUtype(String sKUtype) {
		SKUtype = sKUtype;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	public String getImageid() {
		return imageid;
	}

	public void setImageid(String imageid) {
		this.imageid = imageid;
	}

	public int getGiveintegral() {
		return giveintegral;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
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

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}

	public String getPrdname() {
		return prdname;
	}

	public void setPrdname(String prdname) {
		this.prdname = prdname;
	}

	public String getPrdbrief() {
		return prdbrief;
	}

	public void setPrdbrief(String prdbrief) {
		this.prdbrief = prdbrief;
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype = prdtype;
	}

	public double getPrdprice() {
		return prdprice;
	}

	public void setPrdprice(double prdprice) {
		this.prdprice = prdprice;
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

	public String getCustsource() {
		return custsource;
	}

	public void setCustsource(String custsource) {
		this.custsource = custsource;
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

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate = createdate;
	}

	public String getCreateoperator() {
		return createoperator;
	}

	public void setCreateoperator(String createoperator) {
		this.createoperator = createoperator;
	}

	public java.util.Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(java.util.Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getPrdintroduction() {
		return prdintroduction;
	}

	public void setPrdintroduction(String prdintroduction) {
		this.prdintroduction = prdintroduction;
	}

	public String getPrdvedio() {
		return prdvedio;
	}

	public void setPrdvedio(String prdvedio) {
		this.prdvedio = prdvedio;
	}

	public PrdImage getPrdImage() {
		return prdImage;
	}

	public void setPrdImage(PrdImage prdImage) {
		this.prdImage = prdImage;
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

	public SelectBean<PrdBase> getItem() {
		if (item == null)
			item = new SelectBean<PrdBase>();

		return item;
	}

	public void setItem(SelectBean<PrdBase> item) {
		this.item = item;
	}

}
