package com.lflweb.entity.act;

import java.util.List;

public class ActUserCouponMix{
	private List<ActUserCoupon> mianjianActUserCoupons;
	private List<ActUserCoupon> prdidActUserCoupons;
	public List<ActUserCoupon> getMianjianActUserCoupons() {
		return mianjianActUserCoupons;
	}
	public void setMianjianActUserCoupons(List<ActUserCoupon> mianjianActUserCoupons) {
		this.mianjianActUserCoupons = mianjianActUserCoupons;
	}
	public List<ActUserCoupon> getPrdidActUserCoupons() {
		return prdidActUserCoupons;
	}
	public void setPrdidActUserCoupons(List<ActUserCoupon> prdidActUserCoupons) {
		this.prdidActUserCoupons = prdidActUserCoupons;
	}
	
	
}