package com.lvfulo.service.account;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.act.ActCoupon;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActIntegralDetail;
import com.lflweb.entity.act.ActIntegralMonth;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ctm.CtmBase;

@Service
public interface AccountService {

	// 用户注册
	public void Registered(CtmBase user, String custsource, ReturnValue rtv);
	
	// 微信用户快捷注册
	public void WXRegistered(CtmBase user, String custsource, ReturnValue rtv);

	// 用户登录
	public CtmBase Login(String username, String password, String custsource, String ipaddString, ReturnValue rtv);

	// 修改用户名
	public void UpdateUserName(String custid, String username, ReturnValue rtv);

	// 修改手机号码
	public void UpdatePhone(String custid, String phone, ReturnValue rtv);

	// 修改密码
	public void UpdatePassWord(String custid, String password, ReturnValue rtv);
	
	// 未绑定优惠券查询
	public List<ActCoupon> SearchActCoupon(ActCoupon item,  ReturnValue rtv);
	
	// 用户绑定优惠券查询
	public List<ActUserCoupon> SearchActUserCoupon(ActUserCoupon item,  ReturnValue rtv);
	
	// 用户绑定优惠券保存
	public void SaveActUserCoupon(ActUserCoupon item,  ReturnValue rtv);
	
	// 用户积分查询
	public List<ActIntegral> SearchActIntegral(ActIntegral item,  ReturnValue rtv);
	
	// 积分月度结算表查询
	public List<ActIntegralMonth> SearchActIntegralMonth(ActIntegralMonth item,  ReturnValue rtv);
	
	// 积分账户明细表查询
	public List<ActIntegralDetail> SearchActIntegralDetail(ActIntegralDetail item,  ReturnValue rtv);
	
	// 预支付订单优惠券查询
	public List<ActPreOrderCoupon> SearchActPreOrderCoupon(ActPreOrderCoupon item,  ReturnValue rtv);

}