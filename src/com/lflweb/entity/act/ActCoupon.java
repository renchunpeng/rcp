package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActCoupon implements BaseBean {

	// 优惠券id;
	private String couponid;

	// 商品id;
	private String prdid;

	// 满减上限;
	private double uplimit;
	
	// 满减下限;
	private double lowlimit;

	// 优惠金额;
	private double couponmoney;

	// 有效月份;
	private java.util.Date effmonth;

	// 是否可混用;
	private boolean ismixuse;
	
	// 优惠券描述;
	private String bewrite;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActCoupon> item;

	public ActCoupon() {
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
		return ToolUtils.CompareProperty((ActCoupon)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"优惠券ID:couponid", "商品ID:prdid", "满减限制:limit", "优惠金额:couponmoney", "有效月份:effmonth", "是否可混用:ismixuse"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.couponid = "";
		this.prdid = "";
		this.uplimit = 0;
		this.couponmoney = 0;
		this.ismixuse = false;
		this.lowlimit = 0;
		this.bewrite = "";
	}

	
	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	public double getUplimit() {
		return uplimit;
	}

	public void setUplimit(double uplimit) {
		this.uplimit = uplimit;
	}

	public double getLowlimit() {
		return lowlimit;
	}

	public void setLowlimit(double lowlimit) {
		this.lowlimit = lowlimit;
	}

	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid=couponid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
	}


	public double getCouponmoney() {
		return couponmoney;
	}

	public void setCouponmoney(double couponmoney) {
		this.couponmoney=couponmoney;
	}



	public java.util.Date getEffmonth() {
		return effmonth;
	}

	public void setEffmonth(java.util.Date effmonth) {
		this.effmonth = effmonth;
	}

	public boolean getIsmixuse() {
		return ismixuse;
	}

	public void setIsmixuse(boolean ismixuse) {
		this.ismixuse=ismixuse;
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

	public SelectBean<ActCoupon> getItem() {
		if (item == null)
			item = new SelectBean<ActCoupon>();

		return item;
	}

	public void setItem(SelectBean<ActCoupon> item) {
		this.item = item;
	}

}
