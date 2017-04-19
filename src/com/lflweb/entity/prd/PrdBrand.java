package com.lflweb.entity.prd;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class PrdBrand implements BaseBean {

	// 品牌号;
	private String brandid;

	// 品牌名称;
	private String brandname;

	// 品牌logo;
	private String brandlogo;

	// 品牌简介;
	private String brandintroduction;

	// 品牌所属公司;
	private String brandcompany;

	// 品牌创始人;
	private String brandcreator;

	// 品牌热度;
	private int brandpopularity;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<PrdBrand> item;

	public PrdBrand() {
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
		return ToolUtils.CompareProperty((PrdBrand)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"品牌号:brandid", "品牌名称:brandname", "品牌LOGO:brandlogo", "品牌简介:brandintroduction", "品牌所属公司:brandcompany", "品牌创始人:brandcreator", "品牌热度:brandpopularity"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.brandid = "";
		this.brandname = "";
		this.brandlogo = "";
		this.brandintroduction = "";
		this.brandcompany = "";
		this.brandcreator = "";
		this.brandpopularity = 0;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid=brandid;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname=brandname;
	}

	public String getBrandlogo() {
		return brandlogo;
	}

	public void setBrandlogo(String brandlogo) {
		this.brandlogo=brandlogo;
	}

	public String getBrandintroduction() {
		return brandintroduction;
	}

	public void setBrandintroduction(String brandintroduction) {
		this.brandintroduction=brandintroduction;
	}

	public String getBrandcompany() {
		return brandcompany;
	}

	public void setBrandcompany(String brandcompany) {
		this.brandcompany=brandcompany;
	}

	public String getBrandcreator() {
		return brandcreator;
	}

	public void setBrandcreator(String brandcreator) {
		this.brandcreator=brandcreator;
	}

	public int getBrandpopularity() {
		return brandpopularity;
	}

	public void setBrandpopularity(int brandpopularity) {
		this.brandpopularity=brandpopularity;
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

	public SelectBean<PrdBrand> getItem() {
		if (item == null)
			item = new SelectBean<PrdBrand>();

		return item;
	}

	public void setItem(SelectBean<PrdBrand> item) {
		this.item = item;
	}

}
