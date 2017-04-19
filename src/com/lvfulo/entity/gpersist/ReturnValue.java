package com.lvfulo.entity.gpersist;

import com.lvfulo.utils.ToolUtils;


public class ReturnValue implements BaseBean {
	private boolean success;
	
	private String msg;
	
	private Object data;

	private boolean bean;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}		
		
	public boolean isBean() {
		return bean;
	}

	public void setBean(boolean bean) {
		this.bean = bean;
	}

	public void SetValues(boolean success, String msg, Object data, boolean isbean) {
		this.success = success;
		this.msg = msg;
		this.data = data;
		this.bean = isbean;
	}
	
	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((ReturnValue)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] { };
	}

	@Override
	public void OnInit() {
		// TODO Auto-generated method stub
		this.success = false;
		this.msg = "";
		this.data = null;
		this.bean = false;
	}
	
	public String toString() {
		if (this.data == null)
			return "{\"success\":" + (this.isSuccess() ? "true" : "false") + ",\"msg\":\"" + this.getMsg() + "\",\"data\":null}";
		
		if (this.bean)
			return "{\"success\":" + (this.isSuccess() ? "true" : "false") + ",\"msg\":\"" + this.getMsg() + "\",\"data\":" + this.data.toString() + "}";
		else {
			return "{\"success\":" + (this.isSuccess() ? "true" : "false") + ",\"msg\":\"" + this.getMsg() + "\",\"data\":\"" + this.data.toString() + "\"}";
		}
	}

	@Override
	public String[] OnExclusions() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {

		return false;
	}

}
