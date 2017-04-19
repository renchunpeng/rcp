package com.lflweb.entity.mar;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class MarVedio implements BaseBean {

	// 视频编号;
	private String vedioid;

	// 视频url;
	private String vediourl;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<MarVedio> item;

	public MarVedio() {
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
		return ToolUtils.CompareProperty((MarVedio)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"视频编号:vedioid", "视频url:vediourl"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.vedioid = "";
		this.vediourl = "";
	}

	public String getVedioid() {
		return vedioid;
	}

	public void setVedioid(String vedioid) {
		this.vedioid=vedioid;
	}

	public String getVediourl() {
		return vediourl;
	}

	public void setVediourl(String vediourl) {
		this.vediourl=vediourl;
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

	public SelectBean<MarVedio> getItem() {
		if (item == null)
			item = new SelectBean<MarVedio>();

		return item;
	}

	public void setItem(SelectBean<MarVedio> item) {
		this.item = item;
	}

}
