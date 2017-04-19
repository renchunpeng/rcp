package com.lvfulo.entity.gpersist;

public class ErrorMsg {
	String errmsg;
	
	public ErrorMsg() {
		this.errmsg = "";
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg += errmsg;
	}
}
