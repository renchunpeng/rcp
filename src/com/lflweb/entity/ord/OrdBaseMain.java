package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdBaseMain implements BaseBean,Cloneable {

	// 订单编号;
	private String orderid;

	// 用户编号;
	private String custid;

	// 地址编号;
	private String addressid;

	// 配送日期;
	private java.util.Date senddate;

	// 订单金额;
	private double totalprice;

	// 实付金额;
	private double realprice;

	// 支付方式;
	private String paytype;

	// 订单来源;
	private String ordersource;

	// 积分支付金额;
	private double scoreamt;

	// 优惠券支付金额;
	private double discountamt;

	// 价格策略编号;
	private String strategyid;

	// 创建日期;
	private java.util.Date createdate;

	// 支付状态;
	private String paystatus;

	// 支付完成时间;
	private java.util.Date paydate;

	// 支付系统流水号;
	private String paysysid;

	// 应用编号;
	private String appid;

	// 关联订单编号;
	private String relatedorder;

	// 订单完成时间;
	private java.util.Date okdate;

	// 订单状态;
	private String orderstatus;

	// 订单标记（正常，退单，删除）;
	private String orderflag;

	// 退单编号;
	private String returnid;

	// 退单状态;
	private String returnstatus;

	// 是否已开发票;
	private boolean isinvoice;

	// 发票流水号;
	private String invoicetranid;

	// 发票打印时间;
	private java.util.Date printdate;

	// 是否已评价;
	private boolean iscomment;

	// 评价时间;
	private java.util.Date commentdate;

	// 对账标记;
	private String busflag;

	// 备注;
	private String remark;

	// 订单销售来源;
	private String ordersalesource;

	// 商家编号;
	private String shopid;

	// 订单生成方式;
	private String generatetype;

	private SearchParams search;

	private DataDeal deal;
	

	private SelectBean<OrdBase> item;

	public OrdBaseMain() {
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
		return ToolUtils.CompareProperty((OrdBase)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"订单编号:orderid", "用户编号:custid", "地址编号:addressid", "配送日期:senddate", "订单金额:totalprice", "实付金额:realprice", "支付方式:paytype", "订单来源:ordersource", "积分支付金额:scoreamt", "优惠券支付金额:discountamt", "价格策略编号:strategyid", "创建日期:createdate", "支付状态:paystatus", "支付完成时间:paydate", "支付系统流水号:paysysid", "应用编号:appid", "关联订单编号:relatedorder", "订单完成时间:okdate", "订单状态:orderstatus", "订单标记（正常，退单，删除）:orderflag", "退单编号:returnid", "退单状态:returnstatus", "是否已开发票:isinvoice", "发票流水号:invoicetranid", "发票打印时间:printdate", "是否已评价:iscomment", "评价时间:commentdate", "对账标记:busflag", "备注:remark", "订单销售来源:ordersalesource", "商家编号:shopid", "订单生成方式:generatetype"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}
	
  @Override  
  public Object clone() {  
      OrdBase stu = null;  
      try{  
          stu = (OrdBase)super.clone();  
      }catch(CloneNotSupportedException e) {  
          e.printStackTrace();  
      }  
      return stu;  
  } 

	@Override
	public void OnInit() {
		this.orderid = "";
		this.custid = "";
		this.addressid = "";
		this.senddate = ToolUtils.GetMinDate();
		this.totalprice = 0;
		this.realprice = 0;
		this.paytype = "";
		this.ordersource = "";
		this.scoreamt = 0;
		this.discountamt = 0;
		this.strategyid = "";
		this.createdate = ToolUtils.GetMinDate();
		this.paystatus = "";
		this.paydate = ToolUtils.GetMinDate();
		this.paysysid = "";
		this.appid = "";
		this.relatedorder = "";
		this.okdate = ToolUtils.GetMinDate();
		this.orderstatus = "";
		this.orderflag = "";
		this.returnid = "";
		this.returnstatus = "";
		this.isinvoice = false;
		this.invoicetranid = "";
		this.printdate = ToolUtils.GetMinDate();
		this.iscomment = false;
		this.commentdate = ToolUtils.GetMinDate();
		this.busflag = "";
		this.remark = "";
		this.ordersalesource = "";
		this.shopid = "";
		this.generatetype = "";
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid=orderid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid=addressid;
	}

	public java.util.Date getSenddate() {
		return senddate;
	}

	public void setSenddate(java.util.Date senddate) {
		this.senddate=senddate;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice=totalprice;
	}

	public double getRealprice() {
		return realprice;
	}

	public void setRealprice(double realprice) {
		this.realprice=realprice;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype=paytype;
	}

	public String getOrdersource() {
		return ordersource;
	}

	public void setOrdersource(String ordersource) {
		this.ordersource=ordersource;
	}

	public double getScoreamt() {
		return scoreamt;
	}

	public void setScoreamt(double scoreamt) {
		this.scoreamt=scoreamt;
	}

	public double getDiscountamt() {
		return discountamt;
	}

	public void setDiscountamt(double discountamt) {
		this.discountamt=discountamt;
	}

	public String getStrategyid() {
		return strategyid;
	}

	public void setStrategyid(String strategyid) {
		this.strategyid=strategyid;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus=paystatus;
	}

	public java.util.Date getPaydate() {
		return paydate;
	}

	public void setPaydate(java.util.Date paydate) {
		this.paydate=paydate;
	}

	public String getPaysysid() {
		return paysysid;
	}

	public void setPaysysid(String paysysid) {
		this.paysysid=paysysid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid=appid;
	}

	public String getRelatedorder() {
		return relatedorder;
	}

	public void setRelatedorder(String relatedorder) {
		this.relatedorder=relatedorder;
	}

	public java.util.Date getOkdate() {
		return okdate;
	}

	public void setOkdate(java.util.Date okdate) {
		this.okdate=okdate;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus=orderstatus;
	}

	public String getOrderflag() {
		return orderflag;
	}

	public void setOrderflag(String orderflag) {
		this.orderflag=orderflag;
	}

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid=returnid;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus=returnstatus;
	}

	public boolean getIsinvoice() {
		return isinvoice;
	}

	public void setIsinvoice(boolean isinvoice) {
		this.isinvoice=isinvoice;
	}

	public String getInvoicetranid() {
		return invoicetranid;
	}

	public void setInvoicetranid(String invoicetranid) {
		this.invoicetranid=invoicetranid;
	}

	public java.util.Date getPrintdate() {
		return printdate;
	}

	public void setPrintdate(java.util.Date printdate) {
		this.printdate=printdate;
	}

	public boolean getIscomment() {
		return iscomment;
	}

	public void setIscomment(boolean iscomment) {
		this.iscomment=iscomment;
	}

	public java.util.Date getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(java.util.Date commentdate) {
		this.commentdate=commentdate;
	}

	public String getBusflag() {
		return busflag;
	}

	public void setBusflag(String busflag) {
		this.busflag=busflag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark=remark;
	}

	public String getOrdersalesource() {
		return ordersalesource;
	}

	public void setOrdersalesource(String ordersalesource) {
		this.ordersalesource=ordersalesource;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid=shopid;
	}

	public String getGeneratetype() {
		return generatetype;
	}

	public void setGeneratetype(String generatetype) {
		this.generatetype=generatetype;
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

	public SelectBean<OrdBase> getItem() {
		if (item == null)
			item = new SelectBean<OrdBase>();

		return item;
	}

	public void setItem(SelectBean<OrdBase> item) {
		this.item = item;
	}

}
