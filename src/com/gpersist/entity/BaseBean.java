package com.gpersist.entity;



public interface BaseBean {
	
	public String OnDebug();
	
	public void OnInit();
	
	public String[] OnExclusions();
	
	public boolean OnBeforeSave (ErrorMsg msg);
	
	/**
	 * 增加对象对比的接口函数 
	 * 
	 * @author cloudy 2015-05-10
	 * 
	 */
	public String OnCompare(BaseBean item) throws Exception;
	
	/**
	 * 增加对象的DEBUG、对比属性的集合
	 * 
	 * @author cloudy 2015-05-11
	 */
	public String[] OnProperties();
}
