package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarApl implements BaseBean {

	// 申请资料编号;
	private String aplid;
	
	//申请人
	private String aplperson;

	// 经营产品分类;
	private String prdtype;

	// 身份证号;
	private String idencode;

	// 结算账号;
	private String bankcount;

	// 是否同意服务与质量承诺;
	private boolean isapprove;

	// 店铺状态;
	private String shopstatus;

	// 用户编号;
	private String custid;

	// 商家编号;
	private String shopid;
	
	//申请日期
	private java.util.Date apldate;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarApl> item;

	public MarApl() {
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
		return ToolUtils.CompareProperty((MarApl)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"申请资料编号:aplid", "申请人:aplperson", "经营产品分类:prdtype", "身份证号:idencode", "结算账号:bankcount",
				"是否同意服务与质量承诺:isapprove", "店铺状态:shopstatus",  "用户编号:custid", "商家编号:shopid", 
				"申请时间:apldate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.aplid = "";
		this.aplperson = "";
		this.prdtype = "";
		this.idencode = "";
		this.bankcount = "";
		this.isapprove = true;
		this.shopstatus = "";
		this.custid = "";
		this.shopid = "";
		this.apldate = ToolUtils.GetMinDate();
	}

	public String getAplid() {
		return aplid;
	}

	public void setAplid(String aplid) {
		this.aplid=aplid;
	}

	public String getAplperson() {
		return aplperson;
	}

	public void setAplperson(String aplperson) {
		this.aplperson = aplperson;
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype = prdtype;
	}

	public String getIdencode() {
		return idencode;
	}

	public void setIdencode(String idencode) {
		this.idencode = idencode;
	}

	public String getBankcount() {
		return bankcount;
	}

	public void setBankcount(String bankcount) {
		this.bankcount=bankcount;
	}

	public boolean isIsapprove() {
		return isapprove;
	}

	public void setIsapprove(boolean isapprove) {
		this.isapprove = isapprove;
	}

	public String getShopstatus() {
		return shopstatus;
	}

	public void setShopstatus(String shopstatus) {
		this.shopstatus=shopstatus;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid=shopid;
	}

	public java.util.Date getApldate() {
		return apldate;
	}

	public void setApldate(java.util.Date apldate) {
		this.apldate = apldate;
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

	public SelectBean<MarApl> getItem() {
		if (item == null)
			item = new SelectBean<MarApl>();

		return item;
	}

	public void setItem(SelectBean<MarApl> item) {
		this.item = item;
	}

}
