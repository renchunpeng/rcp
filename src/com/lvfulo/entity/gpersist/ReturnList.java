package com.lvfulo.entity.gpersist;

import com.lvfulo.utils.ToolUtils;

public class ReturnList implements BaseBean {
	private int total;
	
	private Object data;
	
	public ReturnList() {
		OnInit();
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((ReturnList)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.total = 0;
		this.data = null;
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString() {
		if (this.data == null)
			return "{\"total\":0,\"data\":[]}";
		else {
			return "{\"total\":" + this.total + ",\"data\":" + this.data.toString() + "}";
		}
	}
	
	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {

		return false;
	}
}
