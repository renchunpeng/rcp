package com.lflweb.entity.ctm;

import com.gpersist.entity.*;
import com.gpersist.entity.publics.*;
import com.gpersist.utils.*;

public class CtmBase implements BaseBean {

	// 用户编号;
	private String custid;

	// 用户名;
	private String custname;

	// 用户密码;
	private String custpwd;

	// 真实姓名;
	private String realname;

	// 手机号码;
	private String custmobile;

	// 用户邮箱;
	private String custemail;

	// 用户性别;
	private String custsex;

	// 用户来源;
	private String custsource;

	// 用户类型;
	private String custtype;

	// 用户等级;
	private String custgrade;

	// 用户状态;
	private String custstatus;

	// 所属城市;
	private String custlocation;

	// 注册日期;
	private java.util.Date regdate;

	// 最近登录日期;
	private java.util.Date logindate;

	// 用户头像;
	private String custavatar;

	// 用户微信昵称;
	private String custwechat;

	// 微信头像;
	private String wechatavatar;

	// 微信openid;
	private String openid;

	// 安全等级;
	private String safelevel;

	// 备注;
	private String remark;

	private SearchParams search;

	private DataDeal deal;

	private SelectBean<CtmBase> item;
	
	//新增
	private String testopenid;
	

	public CtmBase() {
		this.OnInit();
	}

	@Override
	public boolean OnBeforeSave(ErrorMsg msg) {
		msg.setErrmsg("");
		boolean rtn = false;
		
//		if (ToolUtils.StringIsEmpty(this.getCustname())) {
//			msg.setErrmsg("用户名不能为空");
//			rtn = true;
//		}

		return rtn;
	}

	@Override
	public String OnDebug() {
		return ToolUtils.DebugProperty(this, this.OnProperties());
	}

	@Override
	public String OnCompare(BaseBean item) throws Exception {
		return ToolUtils.CompareProperty((CtmBase)item, this, this.OnProperties());
	}

	@Override
	public String[] OnProperties() {
		return new String[] {"用户编号:custid", "用户名:custname", "用户密码:custpwd", "真实姓名:realname", "手机号码:custmobile", "用户邮箱:custemail", "用户性别:custsex", "用户来源:custsource", "用户类型:custtype", "用户等级:custgrade", "用户状态:custstatus", "所属城市:custlocation", "注册日期:regdate", "最近登录日期:logindate", "用户头像:custavatar", "用户微信昵称:custwechat", "微信头像:wechatavatar", "微信openid:openid", "安全等级:safelevel", "备注:remark"};
	}

	@Override
	public String[] OnExclusions() {
		return new String[] {"deal", "item", "search"};
	}

	@Override
	public void OnInit() {
		this.custid = "";
		this.custname = "";
		this.custpwd = "";
		this.realname = "";
		this.custmobile = "";
		this.custemail = "";
		this.custsex = "";
		this.custsource = "";
		this.custtype = "";
		this.custgrade = "";
		this.custstatus = "";
		this.custlocation = "";
		this.regdate = ToolUtils.GetMinDate();
		this.logindate = ToolUtils.GetMinDate();
		this.custavatar = "";
		this.custwechat = "";
		this.wechatavatar = "";
		this.openid = "";
		this.safelevel = "";
		this.remark = "";
		this.testopenid = "";
	}

	public String getTestopenid() {
		return testopenid;
	}

	public void setTestopenid(String testopenid) {
		this.testopenid = testopenid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid=custid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname=custname;
	}

	public String getCustpwd() {
		return custpwd;
	}

	public void setCustpwd(String custpwd) {
		this.custpwd=custpwd;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname=realname;
	}

	public String getCustmobile() {
		return custmobile;
	}

	public void setCustmobile(String custmobile) {
		this.custmobile=custmobile;
	}

	public String getCustemail() {
		return custemail;
	}

	public void setCustemail(String custemail) {
		this.custemail=custemail;
	}

	public String getCustsex() {
		return custsex;
	}

	public void setCustsex(String custsex) {
		this.custsex=custsex;
	}

	public String getCustsource() {
		return custsource;
	}

	public void setCustsource(String custsource) {
		this.custsource=custsource;
	}

	public String getCusttype() {
		return custtype;
	}

	public void setCusttype(String custtype) {
		this.custtype=custtype;
	}

	public String getCustgrade() {
		return custgrade;
	}

	public void setCustgrade(String custgrade) {
		this.custgrade=custgrade;
	}

	public String getCuststatus() {
		return custstatus;
	}

	public void setCuststatus(String custstatus) {
		this.custstatus=custstatus;
	}

	public String getCustlocation() {
		return custlocation;
	}

	public void setCustlocation(String custlocation) {
		this.custlocation=custlocation;
	}

	public java.util.Date getRegdate() {
		return regdate;
	}

	public void setRegdate(java.util.Date regdate) {
		this.regdate=regdate;
	}

	public java.util.Date getLogindate() {
		return logindate;
	}

	public void setLogindate(java.util.Date logindate) {
		this.logindate=logindate;
	}

	public String getCustavatar() {
		return custavatar;
	}

	public void setCustavatar(String custavatar) {
		this.custavatar=custavatar;
	}

	public String getCustwechat() {
		return custwechat;
	}

	public void setCustwechat(String custwechat) {
		this.custwechat=custwechat;
	}

	public String getWechatavatar() {
		return wechatavatar;
	}

	public void setWechatavatar(String wechatavatar) {
		this.wechatavatar=wechatavatar;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid=openid;
	}

	public String getSafelevel() {
		return safelevel;
	}

	public void setSafelevel(String safelevel) {
		this.safelevel=safelevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark=remark;
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

	public SelectBean<CtmBase> getItem() {
		if (item == null)
			item = new SelectBean<CtmBase>();

		return item;
	}

	public void setItem(SelectBean<CtmBase> item) {
		this.item = item;
	}

}
