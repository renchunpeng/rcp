package com.lflweb.entity.mar;

import com.gpersist.entity.BaseBean;
import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.publics.DataDeal;
import com.gpersist.entity.publics.SearchParams;
import com.gpersist.entity.publics.SelectBean;
import com.gpersist.utils.ToolUtils;
import com.lflweb.entity.ctm.CtmAddress;

public class MarShopdetail implements BaseBean {

	// 店铺编号;
	private String shopid;

	// 店铺名称;
	private String shopname;

	// 店铺头像;
	private String shopavator;

	// 店铺介绍;
	private String shopdescription;

	// 收退货地址;
	private String addressid;

	// 是否接收消息;
	private boolean isreceivemessage;

	// 店铺创建时间;
	private java.util.Date createdate;

	// 店铺创建人;
	private String operator;

	// 备注;
	private String remark;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarShopdetail> item;
	
	private CtmAddress address;

	public MarShopdetail() {
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
		return ToolUtils.CompareProperty((MarShopdetail)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"店铺编号:shopid", "店铺名称:shopname", "店铺头像:shopavator", "店铺介绍:shopdescription", "收退货地址:addressid", "是否接收消息:isreceivemessage", "店铺创建时间:createdate", "店铺创建人:operator", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.shopid = "";
		this.shopname = "";
		this.shopavator = "";
		this.shopdescription = "";
		this.addressid = "";
		this.isreceivemessage = false;
		this.createdate = ToolUtils.GetMinDate();
		this.operator = "";
		this.remark = "";
		this.address = new CtmAddress();
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid=shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname=shopname;
	}

	public String getShopavator() {
		return shopavator;
	}

	public void setShopavator(String shopavator) {
		this.shopavator=shopavator;
	}

	public String getShopdescription() {
		return shopdescription;
	}

	public void setShopdescription(String shopdescription) {
		this.shopdescription=shopdescription;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid=addressid;
	}
	
	public boolean isIsreceivemessage() {
		return isreceivemessage;
	}

	public void setIsreceivemessage(boolean isreceivemessage) {
		this.isreceivemessage = isreceivemessage;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator=operator;
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

	public SelectBean<MarShopdetail> getItem() {
		if (item == null)
			item = new SelectBean<MarShopdetail>();

		return item;
	}

	public void setItem(SelectBean<MarShopdetail> item) {
		this.item = item;
	}

	public CtmAddress getAddress() {
		return address;
	}

	public void setAddress(CtmAddress address) {
		this.address = address;
	}

}
