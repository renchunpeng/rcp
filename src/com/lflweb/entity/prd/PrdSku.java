package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdSku implements BaseBean {

	// sku编号;
	private String skuid;

	// 品牌号;
	private String brandid;

	// sku名称;
	private String skuname;

	// sku描述;
	private String skudescription;

	// sku类型;
	private String skutype;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdSku> item;

	public PrdSku() {
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
		return ToolUtils.CompareProperty((PrdSku)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"SKU编号:skuid", "品牌号:brandid", "SKU名称:skuname", "SKU描述:skudescription", "SKU类型:skutype"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.skuid = "";
		this.brandid = "";
		this.skuname = "";
		this.skudescription = "";
		this.skutype = "";
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid=skuid;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid=brandid;
	}

	public String getSkuname() {
		return skuname;
	}

	public void setSkuname(String skuname) {
		this.skuname=skuname;
	}

	public String getSkudescription() {
		return skudescription;
	}

	public void setSkudescription(String skudescription) {
		this.skudescription=skudescription;
	}

	public String getSkutype() {
		return skutype;
	}

	public void setSkutype(String skutype) {
		this.skutype=skutype;
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

	public SelectBean<PrdSku> getItem() {
		if (item == null)
			item = new SelectBean<PrdSku>();

		return item;
	}

	public void setItem(SelectBean<PrdSku> item) {
		this.item = item;
	}

}
