package com.lflweb.entity.ord;

import java.util.List;

import com.lflweb.entity.act.ActUserCoupon;

public class OrdSubdetailMix {
	private List<OrdSubdetail> ordSubdetail;

	private List<ActUserCoupon> actUserCoupons;
	
	private OrdBase main;

	public OrdBase getMain() {
		return main;
	}

	public void setMain(OrdBase main) {
		this.main = main;
	}

	public List<OrdSubdetail> getOrdSubdetail() {
		return ordSubdetail;
	}

	public void setOrdSubdetail(List<OrdSubdetail> ordSubdetail) {
		this.ordSubdetail = ordSubdetail;
	}

	public List<ActUserCoupon> getActUserCoupons() {
		return actUserCoupons;
	}

	public void setActUserCoupons(List<ActUserCoupon> actUserCoupons) {
		this.actUserCoupons = actUserCoupons;
	}



}