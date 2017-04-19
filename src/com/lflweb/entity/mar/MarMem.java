package com.lflweb.entity.mar;

import com.gpersist.entity.BaseBean;
import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.publics.DataDeal;
import com.gpersist.entity.publics.SearchParams;
import com.gpersist.entity.publics.SelectBean;
import com.gpersist.utils.ToolUtils;
import com.lflweb.entity.ctm.CtmAddress;

public class MarMem implements BaseBean {

	// 会员卡编号;
	private String memid;

	// 商品编号
	private String prdid;
	
	// 会员卡所有人;
	private String custid;
	
	// 订单编号;
	private String orderid;

	// 会员卡办理日期;
	private java.util.Date createdate;

	// 会员卡有效日期;
	private java.util.Date ondate;

	// 会员卡失效日期;
	private java.util.Date offdate;

	// 会员卡已使用购买次数;
	private int usedtimes;

	// 会员卡剩余购买次数;
	private int resttimes;

	// 会员卡类型;
	private String memtype;

	// 会员卡消费地址;
	private String addressid;
	
	// 会员卡状态;
	private String memstatus;

	private SearchParams search;

	private DataDeal deal;
	
	private CtmAddress address;

	private SelectBean<MarMem> item;

	public MarMem() {
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
		return ToolUtils.CompareProperty((MarMem)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"会员卡编号:memid", "会员卡所有人:custid", "会员卡办理日期:createdate", "会员卡有效日期:ondate", "会员卡失效日期:offdate", "会员卡已使用购买次数:usedtimes", "会员卡剩余购买次数:resttimes", "会员卡类型:memtype", "会员卡消费地址:address"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.memid = "";
		this.prdid = "";
		this.custid = "";
		this.orderid = "";
		this.createdate = ToolUtils.GetMinDate();
//		this.ondate = ToolUtils.GetMinDate();
//		this.offdate = ToolUtils.GetMinDate();
//		this.usedtimes = 0;
//		this.resttimes = 0;
		this.memtype = "";
		this.addressid = "";
		this.memstatus = "";
		this.address = new CtmAddress();
	}

	public String getMemstatus() {
		return memstatus;
	}

	public void setMemstatus(String memstatus) {
		this.memstatus = memstatus;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid=memid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
	}

	public java.util.Date getOndate() {
		return ondate;
	}

	public void setOndate(java.util.Date ondate) {
		this.ondate=ondate;
	}

	public java.util.Date getOffdate() {
		return offdate;
	}

	public void setOffdate(java.util.Date offdate) {
		this.offdate=offdate;
	}

	public int getUsedtimes() {
		return usedtimes;
	}

	public void setUsedtimes(int usedtimes) {
		this.usedtimes=usedtimes;
	}

	public int getResttimes() {
		return resttimes;
	}

	public void setResttimes(int resttimes) {
		this.resttimes=resttimes;
	}

	public String getMemtype() {
		return memtype;
	}

	public void setMemtype(String memtype) {
		this.memtype=memtype;
	}

	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
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

	public SelectBean<MarMem> getItem() {
		if (item == null)
			item = new SelectBean<MarMem>();

		return item;
	}

	public void setItem(SelectBean<MarMem> item) {
		this.item = item;
	}

	public CtmAddress getAddress() {
		return address;
	}

	public void setAddress(CtmAddress address) {
		this.address = address;
	}

}
