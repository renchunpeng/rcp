package com.lvfulo.controller.wxping;


public class PageRoute{
	
	public  String pageone_1;
	
	public  String pageone_2;
	
	public String pageone_3;
	
	public String pageone_4;
	
	public String pagetwo;
	
	public String pagethree;
	
	public PageRoute(){
		this.OnInit();
	}
	
	public void OnInit() {
		this.pageone_1 = "pageroute/pageone_1/pageone_1.html";
		this.pageone_2 = "pageroute/pageone_2/pageone_2.html";
		this.pageone_3 = "pageroute/pageone_3/pageone_3.html";
		this.pageone_4 = "pageroute/pageone_4/pageone_4.html";
		this.pagetwo = "pageroute/pageone_2/pageone_2.html";
		this.pagethree = "WXuser/getUserSession/111";
	}

	public String getPageone_1() {
		return pageone_1;
	}

	public void setPageone_1(String pageone_1) {
		this.pageone_1 = pageone_1;
	}

	public String getPageone_2() {
		return pageone_2;
	}

	public void setPageone_2(String pageone_2) {
		this.pageone_2 = pageone_2;
	}

	public String getPageone_3() {
		return pageone_3;
	}

	public void setPageone_3(String pageone_3) {
		this.pageone_3 = pageone_3;
	}

	public String getPageone_4() {
		return pageone_4;
	}

	public void setPageone_4(String pageone_4) {
		this.pageone_4 = pageone_4;
	}

	public String getPagetwo() {
		return pagetwo;
	}

	public void setPagetwo(String pagetwo) {
		this.pagetwo = pagetwo;
	}

	public String getPagethree() {
		return pagethree;
	}

	public void setPagethree(String pagethree) {
		this.pagethree = pagethree;
	}
	
	
}