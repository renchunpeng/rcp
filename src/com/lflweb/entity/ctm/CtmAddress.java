package com.lflweb.entity.ctm;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class CtmAddress implements BaseBean {

	// 地址编号;
	private String addressid;

	// 用户编号;
	private String custid;

	// 联系人;
	private String addcontact;

	// 联系电话;
	private String addmobile;

	// 联系人性别;
	private String contactsex;

	// 公司名称;
	private String companyname;

	// 国家;
	private String addcountry;

	// 省份;
	private String addprov;

	// 城市;
	private String addcity;

	// 县/区;
	private String addcounty;

	// 乡镇/街道;
	private String addtown;

	// 详细地址;
	private String adddetail;

	// 省份编号;
	private String provid;

	// 城市编号;
	private String cityid;

	// 县区编号;
	private String countyid;

	// 乡镇编号;
	private String townid;

	// 地址类型;
	private String addtype;

	// 是否默认;
	private boolean isdefault;

	// 地址状态;
	private String addstatus;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<CtmAddress> item;
	
	//性别name
	private String sexname;
	
	//地址类别名称
	private String addtypename;
	

	public CtmAddress() {
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
		return ToolUtils.CompareProperty((CtmAddress)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"地址编号:addressid", "用户编号:custid", "联系人:addcontact", "联系电话:addmobile", "联系人性别:contactsex", "公司名称:companyname", "国家:addcountry", "省份:addprov", "城市:addcity", "县/区:addcounty", "乡镇/街道:addtown", "详细地址:adddetail", "省份编号:provid", "城市编号:cityid", "县区编号:countyid", "乡镇编号:townid", "地址类型:addtype", "是否默认:isdefault", "地址状态:addstatus"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.addressid = "";
		this.custid = "";
		this.addcontact = "";
		this.addmobile = "";
		this.contactsex = "";
		this.companyname = "";
		this.addcountry = "";
		this.addprov = "";
		this.addcity = "";
		this.addcounty = "";
		this.addtown = "";
		this.adddetail = "";
		this.provid = "";
		this.cityid = "";
		this.countyid = "";
		this.townid = "";
		this.addtype = "";
		this.isdefault = false;
		this.addstatus = "";
		this.sexname = "";
		this.addtypename = "";
	}

	public String getSexname() {
		return sexname;
	}

	public void setSexname(String sexname) {
		this.sexname = sexname;
	}

	public String getAddtypename() {
		return addtypename;
	}

	public void setAddtypename(String addtypename) {
		this.addtypename = addtypename;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid=addressid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getAddcontact() {
		return addcontact;
	}

	public void setAddcontact(String addcontact) {
		this.addcontact=addcontact;
	}

	public String getAddmobile() {
		return addmobile;
	}

	public void setAddmobile(String addmobile) {
		this.addmobile=addmobile;
	}

	public String getContactsex() {
		return contactsex;
	}

	public void setContactsex(String contactsex) {
		this.contactsex=contactsex;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname=companyname;
	}

	public String getAddcountry() {
		return addcountry;
	}

	public void setAddcountry(String addcountry) {
		this.addcountry=addcountry;
	}

	public String getAddprov() {
		return addprov;
	}

	public void setAddprov(String addprov) {
		this.addprov=addprov;
	}

	public String getAddcity() {
		return addcity;
	}

	public void setAddcity(String addcity) {
		this.addcity=addcity;
	}

	public String getAddcounty() {
		return addcounty;
	}

	public void setAddcounty(String addcounty) {
		this.addcounty=addcounty;
	}

	public String getAddtown() {
		return addtown;
	}

	public void setAddtown(String addtown) {
		this.addtown=addtown;
	}

	public String getAdddetail() {
		return adddetail;
	}

	public void setAdddetail(String adddetail) {
		this.adddetail=adddetail;
	}

	public String getProvid() {
		return provid;
	}

	public void setProvid(String provid) {
		this.provid=provid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid=cityid;
	}

	public String getCountyid() {
		return countyid;
	}

	public void setCountyid(String countyid) {
		this.countyid=countyid;
	}

	public String getTownid() {
		return townid;
	}

	public void setTownid(String townid) {
		this.townid=townid;
	}

	public String getAddtype() {
		return addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype=addtype;
	}

	public boolean getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(boolean isdefault) {
		this.isdefault=isdefault;
	}

	public String getAddstatus() {
		return addstatus;
	}

	public void setAddstatus(String addstatus) {
		this.addstatus=addstatus;
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

	public SelectBean<CtmAddress> getItem() {
		if (item == null)
			item = new SelectBean<CtmAddress>();

		return item;
	}

	public void setItem(SelectBean<CtmAddress> item) {
		this.item = item;
	}

}
