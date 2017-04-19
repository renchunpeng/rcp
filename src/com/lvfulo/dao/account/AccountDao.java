package com.lvfulo.dao.account;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.act.ActCoupon;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActIntegralDetail;
import com.lflweb.entity.act.ActIntegralMonth;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ctm.CtmBase;
import com.lflweb.entity.ctm.CtmGrade;
import com.lflweb.entity.ctm.CtmLogin;
import com.lflweb.entity.ctm.CtmSmsCode;
import com.lflweb.entity.log.LogCtmLogin;
import com.lflweb.entity.log.LogCustmodify;
import com.lflweb.entity.log.LogWebvisit;


@Repository
public interface AccountDao {
	
	//region 用户基本信息
	//通过custname或者custmobile判断用户是否存在
	public List<CtmBase> SearchOnlyUser(CtmBase item);

	//保存用户基础信息
	public void saveUser(CtmBase user);
	
	//用户登录类型保存
	public void SaveCtmLogin(CtmLogin item);
	
	//获取用户登录类型
	public List<CtmLogin> GetLoginType(String loginid);
	
	//这个查找用户后台代码看一下（加了很多约束条件）
	public List<CtmBase> SearchUser(CtmBase item);

	//保存用户访问日志表
	public void saveLogCtmLogin(LogWebvisit item);

	//用户信息修改日志保存
	public void Savelogcustmodify(LogCustmodify item);
	
	//用户登录保存
	public void saveLogCtmLogin(LogCtmLogin item);
	
	//用户等级变更
	public void SaveChangeGrade(CtmGrade item);//仅有dao层
	
	//保存用户短信验证码
	public void SaveSmsCode(CtmSmsCode item);//仅有dao层
	
	//查找用户短信验证码
	public CtmSmsCode SearchSmsCode(CtmSmsCode item);//仅有dao层
	
	//获取用户登录类型,用custid和logintype
	public List<CtmLogin> SearchLoginType(CtmLogin item);//仅有dao层
	//endregion
	
	
	
	//region 积分
	//用户积分账户保存
	public void SaveActIntegral(ActIntegral item);//仅有dao层
	//用户积分账户查询
	public List<ActIntegral> SearchActIntegral(ActIntegral item);//仅有dao层
	
	
	
	//用户积分明细修改
	public void SaveActIntegralDetail(ActIntegralDetail item);//仅有dao层
	//查询明细查询
	public List<ActIntegralDetail> SearchActIntegralDetail(ActIntegralDetail item);//仅有dao层

	
	
	//积分月度结算表
	public void SaveActIntegralMonth(ActIntegralMonth item);//仅有dao层
	//查询积分月度结算表
	public List<ActIntegralMonth> SearchActIntegralMonth(ActIntegralMonth item);//仅有dao层
	
	//endregion

	
	
	//region 优惠券
	//优惠券查询
	public List<ActCoupon> SearchActCoupon(ActCoupon item);
	
	//用户绑定优惠券查询
	public List<ActUserCoupon> SearchActUserCoupon(ActUserCoupon item);
	
	//用户绑定优惠券保存
	public void SaveActUserCoupon(ActUserCoupon item);//仅有dao层
	
	//订单绑定优惠券保存
	public void SaveActPreOrderCoupon(ActPreOrderCoupon item);
	
	//查询预支付订单绑定优惠券
	public List<ActPreOrderCoupon> SearchActPreOrderCoupon(ActPreOrderCoupon item);
	//endregion

}
