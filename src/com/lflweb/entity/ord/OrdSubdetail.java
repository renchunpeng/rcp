package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdPackage;

public class OrdSubdetail implements BaseBean {

	// 订单编号;
	private String orderid;

	// 商品编号;
	private String prdid;

	// 购买数量;
	private int prdnum;

	// 商品单位;
	private String prdunit;

	// 商品单价;
	private double prdprice;

	// 总金额;
	private double sumprice;

	// 税率;
	private double prdtax;

	// 税额;
	private double taxamt;
	
	//商品类型
	private String prdtype;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdSubdetail> item;
	
	//新增
	//商品类别
	private String prdkind;
	
	//店铺编号
	private String shopid;
	
	//商品基础表
	private PrdBase prdBase;
	
	//套餐基础表
	private PrdPackage prdPackage;
	
	

	public OrdSubdetail() {
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
		return ToolUtils.CompareProperty((OrdSubdetail)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"订单编号:orderid", "商品编号:prdid", "购买数量:prdnum", "商品单位:prdunit", "商品单价:prdprice", "总金额:sumprice", "税率:prdtax", "税额:taxamt"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.orderid = "";
		this.prdid = "";
		this.prdnum = 0;
		this.prdunit = "";
		this.prdprice = 0;
		this.sumprice = 0;
		this.prdtax = 0;
		this.taxamt = 0;
		this.prdkind = "";
		this.shopid = "";
		this.prdtype = "";
		this.prdBase = new PrdBase();
		this.prdPackage = new PrdPackage();
	}

	public PrdPackage getPrdPackage() {
		return prdPackage;
	}

	public void setPrdPackage(PrdPackage prdPackage) {
		this.prdPackage = prdPackage;
	}

	public PrdBase getPrdBase() {
		return prdBase;
	}

	public void setPrdBase(PrdBase prdBase) {
		this.prdBase = prdBase;
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype = prdtype;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getPrdkind() {
		return prdkind;
	}

	public void setPrdkind(String prdkind) {
		this.prdkind = prdkind;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
	}

	public int getPrdnum() {
		return prdnum;
	}

	public void setPrdnum(int prdnum) {
		this.prdnum=prdnum;
	}

	public String getPrdunit() {
		return prdunit;
	}

	public void setPrdunit(String prdunit) {
		this.prdunit=prdunit;
	}

	public double getPrdprice() {
		return prdprice;
	}

	public void setPrdprice(double prdprice) {
		this.prdprice=prdprice;
	}

	public double getSumprice() {
		return sumprice;
	}

	public void setSumprice(double sumprice) {
		this.sumprice=sumprice;
	}

	public double getPrdtax() {
		return prdtax;
	}

	public void setPrdtax(double prdtax) {
		this.prdtax=prdtax;
	}

	public double getTaxamt() {
		return taxamt;
	}

	public void setTaxamt(double taxamt) {
		this.taxamt=taxamt;
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

	public SelectBean<OrdSubdetail> getItem() {
		if (item == null)
			item = new SelectBean<OrdSubdetail>();

		return item;
	}

	public void setItem(SelectBean<OrdSubdetail> item) {
		this.item = item;
	}

}
