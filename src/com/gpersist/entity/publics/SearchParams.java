package com.gpersist.entity.publics;

import com.gpersist.entity.*;
import com.gpersist.utils.*;

public class SearchParams implements BaseBean {
	private String search;
	
	private int start;
	
	private int end;
	
	private int total;
	
	private String getaction;
	
	private String procedurename;
	
	private String userid;
	
	private String deptid;
	
	private String begindate;
	
	private String enddate;
	
	private String year;
	
	private String month;
	
	private String day;
	
	private String selparam;
	private int currentpage;
	private int pagesize;
	
	public SearchParams() {
		OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((SearchParams)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.search = "";
		this.start = 0;
		this.end = 0;
		this.total = 0;
		this.getaction = "";
		this.procedurename = "";
		this.userid = "";
		this.deptid = "";
		this.begindate = "";
		this.enddate = "";
		this.year = "";
		this.month = "";
		this.day = "";
		
    this.selparam = "";
		this.currentpage = 0;
		this.pagesize = 0;
		
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getGetaction() {
		return getaction;
	}

	public void setGetaction(String getaction) {
		this.getaction = getaction;
	}

	public String getProcedurename() {
		return procedurename;
	}

	public void setProcedurename(String procedurename) {
		this.procedurename = procedurename;
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {

		return false;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}	
	
	public String getSelparam() {
		return selparam;
	}

	public void setSelparam(String selparam) {
		this.selparam = selparam;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String GetDateBetween(String datedefine) {
		String rtn = "";
				
		if (!ToolUtils.StringIsEmpty(this.getEnddate()))
			rtn += ToolUtils.GetAndSearch(rtn) + " " + datedefine + " <= " + ToolUtils.GetEndDate(this.getEnddate()) + " ";
		
		if (!ToolUtils.StringIsEmpty(this.getBegindate()))
			rtn += ToolUtils.GetAndSearch(rtn) + " " + datedefine + " >= " + ToolUtils.GetBeginDate(this.getBegindate()) + " ";
		
		return rtn;
	}
	
	public String GetExportDateBetween() {		
		return Consts.STR_CN_DATE + Consts.STR_CN_COLON + this.getBegindate() + Consts.STR_CN_DATESPLIT + this.getEnddate();
	}
	
	public String GetDayBetween(String datedefine) {
		String rtn = "";
				
		if (!ToolUtils.StringIsEmpty(this.getDay()))
			rtn += ToolUtils.GetAndSearch(rtn) + " " + datedefine + " <= " + ToolUtils.GetEndDate(this.getDay()) + " ";
		
		if (!ToolUtils.StringIsEmpty(this.getDay()))
			rtn += ToolUtils.GetAndSearch(rtn) + " " + datedefine + " >= " + ToolUtils.GetBeginDate(this.getDay()) + " ";
		
		return rtn;
	}
	
	public String GetExportDayBetween() {		
		return Consts.STR_CN_DATE + Consts.STR_CN_COLON + this.getDay();
	}
}
