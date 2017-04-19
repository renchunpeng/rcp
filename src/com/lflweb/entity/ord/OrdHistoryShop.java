package com.lflweb.entity.ord;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;
import com.lflweb.entity.prd.PrdType;

public class OrdHistoryShop implements BaseBean {

	// 用户编号;
	private String custid;

	// 商品编号;
	private String prdid;

	// 商品数量;
	private int prdcount;

	// 加入日期;
	private java.util.Date createdate;
	

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<OrdHistoryShop> item;
	
	//商品类型
	private String prdtype;
	
	//店铺编号
	private String shopid;
	
	//新增
	private String prdkind;

	public OrdHistoryShop() {
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
		return ToolUtils.CompareProperty((OrdHistoryShop)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "商品编号:prdid", "商品数量:prdcount", "加入日期:createdate"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.prdid = "";
		this.prdcount = 0;
		this.createdate = ToolUtils.GetMinDate();
		this.prdkind = "";
		this.prdtype = "";
		this.shopid = "";
	}

	public String getPrdtype() {
		return prdtype;
	}

	public void setPrdtype(String prdtype) {
		this.prdtype = prdtype;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getPrdkind() {
		return prdkind;
	}

	public void setPrdkind(String prdkind) {
		this.prdkind = prdkind;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid=prdid;
	}

	public int getPrdcount() {
		return prdcount;
	}

	public void setPrdcount(int prdcount) {
		this.prdcount=prdcount;
	}

	public java.util.Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(java.util.Date createdate) {
		this.createdate=createdate;
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

	public SelectBean<OrdHistoryShop> getItem() {
		if (item == null)
			item = new SelectBean<OrdHistoryShop>();

		return item;
	}

	public void setItem(SelectBean<OrdHistoryShop> item) {
		this.item = item;
	}

}
