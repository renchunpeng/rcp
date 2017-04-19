package com.lvfulo.controller.returnmoney.alipay.util;

public class AlipayRefundInfo {
  
  private String out_trade_no;
  
  private String app_id;
  
  private double refund_amount;
  
  private String trade_no;

  public String getOut_trade_no() {
    return out_trade_no;
  }
  
  public String getApp_id() {
    return app_id;
  }

  public void setApp_id(String app_id) {
    this.app_id = app_id;
  }

  public void setOut_trade_no(String out_trade_no) {
    this.out_trade_no = out_trade_no;
  }

  public double getRefund_amount() {
    return refund_amount;
  }

  public void setRefund_amount(double refund_amount) {
    this.refund_amount = refund_amount;
  }

  public String getTrade_no() {
    return trade_no;
  }

  public void setTrade_no(String trade_no) {
    this.trade_no = trade_no;
  }
  
  

}
