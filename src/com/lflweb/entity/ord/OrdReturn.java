package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class OrdReturn implements BaseBean {

	// 退单编号;
	private String returnid;

	// 订单编号;
	private String orderid;

	// 用户编号;
	private String custid;

	// 创建日期;
	private java.util.Date createdate;

	// 退单状态;
	private String returnstatus;

	// 退单原因;
	private String returnreason;

	// 订单金额;
	private double orderprice;

	// 退款金额;
	private double returnprice;

	// 退款完成时间;
	private java.util.Date okdate;

	// 备注;
	private String remark;
	
	//关联订单表数据和日志表
	// 商户ID
	private String appid;
	
	// 支付类型
	private String paytype;
	
	// 支付流水号
	private String paysysid;
	
	// 记录日志编号
	private String logid;
	
	private String userid;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdReturn> item;

	public OrdReturn() {
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
		return ToolUtils.CompareProperty((OrdReturn)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"退单编号:returnid", "订单编号:orderid", "用户编号:custid", "创建日期:createdate", "退单状态:returnstatus", "退单原因:returnreason", "订单金额:orderprice", "退款金额:returnprice", "退款完成时间:okdate", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.returnid = "";
		this.orderid = "";
		this.custid = "";
		this.createdate = ToolUtils.GetMinDate();
		this.returnstatus = "";
		this.returnreason = "";
		this.orderprice = 0;
		this.returnprice = 0;
//		this.okdate = ToolUtils.GetMinDate();
		this.remark = "";
		this.appid = "";
		this.paytype = "";
		this.paysysid = "";
		this.logid = "";
		this.userid = "";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getPaysysid() {
		return paysysid;
	}

	public void setPaysysid(String paysysid) {
		this.paysysid = paysysid;
	}

	public String getLogid() {
		return logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid=returnid;
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

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus=returnstatus;
	}

	public String getReturnreason() {
		return returnreason;
	}

	public void setReturnreason(String returnreason) {
		this.returnreason=returnreason;
	}

	public double getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(double orderprice) {
		this.orderprice=orderprice;
	}

	public double getReturnprice() {
		return returnprice;
	}

	public void setReturnprice(double returnprice) {
		this.returnprice=returnprice;
	}

	public java.util.Date getOkdate() {
		return okdate;
	}

	public void setOkdate(java.util.Date okdate) {
		this.okdate=okdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark=remark;
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

	public SelectBean<OrdReturn> getItem() {
		if (item == null)
			item = new SelectBean<OrdReturn>();

		return item;
	}

	public void setItem(SelectBean<OrdReturn> item) {
		this.item = item;
	}

}
