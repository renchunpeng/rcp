package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActUserCoupon implements BaseBean {
	
	//优惠券绑定id
	private String bdcouponid;

	// 用户id;
	private String custid;

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

	// 领取时间;
	private java.util.Date getdate;

	// 过期时间;
	private java.util.Date overdate;

	// 是否已经使用;
	private boolean isuse;

	// 绑定订单号;
	private String orderid;
	
	//使用时间
	private java.util.Date usedate;
	
	//是否可混用
	private boolean ismixuse;
	
	//优惠券描述
	private String bewrite;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<ActUserCoupon> item;

	public ActUserCoupon() {
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
		return ToolUtils.CompareProperty((ActUserCoupon)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户ID:custid", "优惠券ID:couponid", "商品ID:prdid", "满减限制:limit", "优惠金额:couponmoney", "领取时间:getdate", "过期时间:overdate", "是否已经使用:isuse", "绑定订单号:orderid"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.couponid = "";
		this.prdid = "";
		this.lowlimit = 0;
		this.uplimit = 0;
		this.couponmoney = 0;
//		this.getdate = ToolUtils.GetMinDate();
//		this.overdate = ToolUtils.GetMinDate();
		this.isuse = false;
		this.orderid = "";
		this.bdcouponid = "";
		this.ismixuse = false;
		this.bewrite = "";
	}

	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	public boolean isIsmixuse() {
		return ismixuse;
	}

	public void setIsmixuse(boolean ismixuse) {
		this.ismixuse = ismixuse;
	}

	public java.util.Date getUsedate() {
		return usedate;
	}

	public void setUsedate(java.util.Date usedate) {
		this.usedate = usedate;
	}

	public String getBdcouponid() {
		return bdcouponid;
	}

	public void setBdcouponid(String bdcouponid) {
		this.bdcouponid = bdcouponid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
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

	public double getCouponmoney() {
		return couponmoney;
	}

	public void setCouponmoney(double couponmoney) {
		this.couponmoney=couponmoney;
	}

	public java.util.Date getGetdate() {
		return getdate;
	}

	public void setGetdate(java.util.Date getdate) {
		this.getdate=getdate;
	}

	public java.util.Date getOverdate() {
		return overdate;
	}

	public void setOverdate(java.util.Date overdate) {
		this.overdate=overdate;
	}

	public boolean getIsuse() {
		return isuse;
	}

	public void setIsuse(boolean isuse) {
		this.isuse=isuse;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
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

	public SelectBean<ActUserCoupon> getItem() {
		if (item == null)
			item = new SelectBean<ActUserCoupon>();

		return item;
	}

	public void setItem(SelectBean<ActUserCoupon> item) {
		this.item = item;
	}

}
