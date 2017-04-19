package com.lflweb.entity.act;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class ActPreOrderCoupon implements BaseBean {

  // 订单编号;
  private String orderid;

  // 优惠券绑定id;
  private String bdcouponid;

  private SearchParams search;

  private DataDeal deal;

  private SelectBean<ActPreOrderCoupon> item;

  public ActPreOrderCoupon() {
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
    return ToolUtils.CompareProperty((ActPreOrderCoupon)item, this, this.OnProperties());
  }

  @Override
  public String[] OnProperties() {
    return new String[] {"订单编号:orderid", "优惠券绑定ID:bdcouponid"};
  }

  @Override
  public String[] OnExclusions() {
    return new String[] {"deal", "item", "search"};
  }

  @Override
  public void OnInit() {
    this.orderid = "";
    this.bdcouponid = "";
  }

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid=orderid;
  }

  public String getBdcouponid() {
    return bdcouponid;
  }

  public void setBdcouponid(String bdcouponid) {
    this.bdcouponid=bdcouponid;
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

  public SelectBean<ActPreOrderCoupon> getItem() {
    if (item == null)
      item = new SelectBean<ActPreOrderCoupon>();

    return item;
  }

  public void setItem(SelectBean<ActPreOrderCoupon> item) {
    this.item = item;
  }

}
