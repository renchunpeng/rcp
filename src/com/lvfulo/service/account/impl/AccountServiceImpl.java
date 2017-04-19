package com.lvfulo.service.account.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActCoupon;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActIntegralDetail;
import com.lflweb.entity.act.ActIntegralMonth;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ctm.CtmBase;
import com.lflweb.entity.ctm.CtmLogin;
import com.lflweb.entity.log.LogCtmLogin;
import com.lflweb.entity.log.LogCustmodify;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.service.account.AccountService;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDao accountDao;

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 上午10:42:25
	 * @描述: 
	 *      app和web端的注册接口---1.验证用户名和手机号码是否已经被注册，假如有邮箱的话验证邮箱是否已经被注册2.在用户登录类型表中插入用户名登陆
	 *      ，手机号码登陆，如果有邮箱的话插入邮箱登陆
	 * @param user
	 * @param custsource
	 * @param rtv
	 */
	@Override
	@Transactional
	public void Registered(CtmBase user, String custsource, ReturnValue rtv) {

		ErrorMsg errmsg = new ErrorMsg();
		if (user.OnBeforeSave(errmsg)) {
			rtv.setMsg(errmsg.getErrmsg());
			return;
		}
		try {
			user.setCustid(MyTools.CreateID("AC"));
			user.setCustsource(custsource);// 用户来源
			user.setCuststatus("01");// 用户状态-有效
			user.setCustgrade("01");// 用户登记-铜牌会员
			user.setSafelevel("01");// 安全登记-低
			// user.setCustlocation("01");// custlocation默认为01
			user.setRegdate(new Date());
			// 将密码加密为MD5
			user.setCustpwd(ToolUtils.GetMD5(user.getCustpwd()));

			user.getDeal().setAction(DataAction.Create.getAction());

			CtmBase item1 = new CtmBase();// 用于验证用户名是否已经被注册
			item1.getSearch().setSearch(" a.custname= '" + user.getCustname() + "'");
			List<CtmBase> items1 = accountDao.SearchOnlyUser(item1);

			CtmBase item2 = new CtmBase();// 用于验证手机号码是否已经被注册
			item2.getSearch().setSearch(" a.custmobile= '" + user.getCustmobile() + "'");
			List<CtmBase> items2 = accountDao.SearchOnlyUser(item2);

			if (items2.size() >= 1) {
				rtv.setMsg("该手机号码已被注册");
				return;
			} else if (items1.size() >= 1) {
				rtv.setMsg("该用户名已被注册");
				return;
				// } else if (user.getCustemail().length() != 0) {
			} else if (!ToolUtils.StringIsEmpty(user.getCustemail())) {
				CtmBase item3 = new CtmBase();// 用于验证邮箱是否已经被注册
				item3.getSearch().setSearch(" a.custemail= '" + user.getCustemail() + "'");
				List<CtmBase> items3 = accountDao.SearchOnlyUser(item3);
				if (items3.size() >= 1) {
					rtv.setMsg("该邮箱已被注册");
					return;
				}
			}

			accountDao.saveUser(user);// 用户基础表保存信息

			CtmLogin itemCtmLogin = new CtmLogin();
			itemCtmLogin.setCustid(user.getCustid());
			itemCtmLogin.setLoginid(user.getCustname());
			itemCtmLogin.setLogintype("01");
			itemCtmLogin.getDeal().setAction(DataAction.Create.getAction());
			accountDao.SaveCtmLogin(itemCtmLogin);// 用户名登录类型保存

			CtmLogin itemCtmLogin2 = new CtmLogin();
			itemCtmLogin2.setCustid(user.getCustid());
			itemCtmLogin2.setLoginid(user.getCustmobile());
			itemCtmLogin2.setLogintype("02");
			itemCtmLogin2.getDeal().setAction(DataAction.Create.getAction());
			accountDao.SaveCtmLogin(itemCtmLogin2);// 手机号码登录类型保存

			// if (!user.getCustemail().equals("")) {
			if (!ToolUtils.StringIsEmpty(user.getCustemail())) {
				CtmLogin itemCtmLogin3 = new CtmLogin();
				itemCtmLogin3.setCustid(user.getCustid());
				itemCtmLogin3.setLoginid(user.getCustemail());
				itemCtmLogin3.setLogintype("03");
				itemCtmLogin3.getDeal().setAction(DataAction.Create.getAction());
				accountDao.SaveCtmLogin(itemCtmLogin3);// 邮箱登录类型保存
			}

			rtv.setSuccess(true);
			rtv.setMsg("用户注册成功");

		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 上午10:09:52
	 * @描述: 登陆验证---1.根据登录名去登陆类型表中查找登陆类型，然后根据登陆类型去基础表中相应字段进行匹配 2.更新用户登录时间，用户登录日志保存
	 * @param username
	 *          登陆名（用户名，手机号码，邮箱都可以）
	 * @param password
	 *          登陆密码
	 * @param custsource
	 *          用户来源
	 * @param ipaddString
	 *          登陆ip
	 * @param rtv
	 * @return
	 */
	@Override
	@Transactional
	public CtmBase Login(String username, String password, String custsource, String ipaddString, ReturnValue rtv) {
		CtmBase userBase = new CtmBase();
		List<CtmLogin> itemsCtmLogins = accountDao.GetLoginType(username);// 获取用户登录类型
		if (itemsCtmLogins.size() >= 2) {
			System.out.println("出现用户名，手机号码，邮箱号码相同的情况！");
		}
		if (itemsCtmLogins.size() >= 1) {
			if (itemsCtmLogins.get(0).getLogintype().equals("01")) {
				userBase.getSearch().setSearch(" a.custname= '" + username + "'");// 表示用用户名去匹配

			} else if (itemsCtmLogins.get(0).getLogintype().equals("02")) {
				userBase.getSearch().setSearch(" a.custmobile= '" + username + "'");// 表示用手机号码去匹配

			} else if (itemsCtmLogins.get(0).getLogintype().equals("03")) {
				userBase.getSearch().setSearch(" a.custmail= '" + username + "'");// 表示用邮箱号码去匹配

			}
		} else {
			rtv.setMsg("登录类型表中不存在此id");
			return new CtmBase();
		}

		try {
			List<CtmBase> user = accountDao.SearchUser(userBase);
			if (user.size() == 1) {
				String datapwd = user.get(0).getCustpwd();
				if (datapwd.equals(ToolUtils.GetMD5(password))) {

					// 更新用户信息表当前登录时间
					CtmBase itemBase = user.get(0);
					itemBase.setLogindate(new Date());
					itemBase.getDeal().setAction(DataAction.Modify.getAction());
					accountDao.saveUser(itemBase);

					// 记录用户登录日志t_log_ctm_login
					LogCtmLogin itemCtmLogin = new LogCtmLogin();
					itemCtmLogin.setCustid(itemBase.getCustid());
					itemCtmLogin.setLogindate(new Date());
					itemCtmLogin.setCustsource(custsource);
					itemCtmLogin.setLoginip(ipaddString);
					itemCtmLogin.setLogindetail("登录");
					itemCtmLogin.getDeal().setAction(DataAction.Create.getAction());
					accountDao.saveLogCtmLogin(itemCtmLogin);

					rtv.setSuccess(true);
					return user.get(0);
				} else {
					rtv.setMsg("密码不正确！");
					return new CtmBase();
				}
			} else {
				rtv.setMsg("该用户不存在，或者出现用户信息混乱，请联系客服！");
				return new CtmBase();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 上午10:27:55
	 * @描述: 修改用户名---1.判断新用户名是否已经存在2.用户基础表修改用户名3.在用户登录表中根据custid和logintype锁定原用户名，
	 *      修改为新用户名4.用户信息修改表保存信息
	 * @param custid
	 * @param username
	 *          新用户名
	 * @param rtv
	 */
	@Override
	@Transactional
	public void UpdateUserName(String custid, String username, ReturnValue rtv) {
		CtmBase userBase = new CtmBase();
		userBase.getSearch().setSearch(" a.custid= '" + custid + "'");
		try {
			CtmBase itemBase = new CtmBase();
			itemBase.getSearch().setSearch(" a.custname= '" + username + "'");
			// 判断修改后的用户名是否已经存在
			List<CtmBase> itemBases = accountDao.SearchUser(itemBase);
			if (itemBases.size() >= 1) {
				rtv.setMsg("该用户名已存在");
				return;
			}
			// 查找需要修改用户名的用户
			List<CtmBase> user = accountDao.SearchUser(userBase);
			user.get(0).setCustname(username);
			user.get(0).getDeal().setAction(DataAction.Modify.getAction());
			// 修改用户基础表的用户名
			accountDao.saveUser(user.get(0));

			// 如果是微信创建的用户是没有用户名的，登陆类型表中也没有用户名登陆类型，这里要区分一下
			boolean flagString = Judge(custid, "01");
			CtmLogin itemCtmLogin = new CtmLogin();

			if (flagString) {
				// 登录类型修改
				itemCtmLogin.setCustid(custid);
				itemCtmLogin.setLogintype("01");// 登陆类型为用户名
				itemCtmLogin.setLoginid(username);
				itemCtmLogin.getDeal().setAction(DataAction.Modify.getAction());
			} else {
				// 新建登陆类型
				itemCtmLogin.setCustid(custid);
				itemCtmLogin.setLogintype("01");
				itemCtmLogin.setLoginid(username);
				itemCtmLogin.getDeal().setAction(DataAction.Create.getAction());
			}
			accountDao.SaveCtmLogin(itemCtmLogin);

			LogCustmodify itemCustmodify = new LogCustmodify();
			itemCustmodify.setCustid(custid);
			UUID uuid = UUID.randomUUID();
			itemCustmodify.setLogid(uuid.toString().replace("-", ""));
			itemCustmodify.setLogdate(new Date());
			itemCustmodify.setLogoperator(custid);
			itemCustmodify.setLogtype("05");
			itemCustmodify.setLogdetail("用户修改用户名");
			itemCustmodify.getDeal().setAction(DataAction.Create.getAction());// 日志保存
			accountDao.Savelogcustmodify(itemCustmodify);

			rtv.setSuccess(true);
			rtv.setMsg("用户名修改成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 上午10:32:51
	 * @描述: 修改手机号码---1.判断新手机号码是否已经存在2.修改手机号码并且在用户登陆类型表中修改手机号码3.704插入信息
	 * @param custid
	 * @param phone
	 *          新手机号码
	 * @param rtv
	 */
	@Override
	@Transactional
	public void UpdatePhone(String custid, String phone, ReturnValue rtv) {
		CtmBase userBase = new CtmBase();
		userBase.getSearch().setSearch(" a.custid = '" + custid + "'");
		try {
			CtmBase itemBase = new CtmBase();

			itemBase.getSearch().setSearch(" a.custmobile = '" + phone + "'");
			List<CtmBase> itemBases = accountDao.SearchUser(itemBase);// 判断修改后的手机号码是否已经存在
			if (itemBases.size() >= 1) {
				rtv.setMsg("该手机号码已存在");
				return;
			}

			List<CtmBase> user = accountDao.SearchUser(userBase);
			user.get(0).setCustmobile(phone);
			user.get(0).getDeal().setAction(DataAction.Modify.getAction());
			accountDao.saveUser(user.get(0));// 修改用户基础表的手机号码
			
			// 如果是微信创建的用户是没有用户名的，登陆类型表中也没有用户名登陆类型，这里要区分一下
			boolean flagString = Judge(custid, "02");
			CtmLogin itemCtmLogin = new CtmLogin();

			if (flagString) {
				// 登录类型修改
				itemCtmLogin.setCustid(custid);
				itemCtmLogin.setLogintype("02");// 登陆类型为 手机号码
				itemCtmLogin.setLoginid(phone);
				itemCtmLogin.getDeal().setAction(DataAction.Modify.getAction());
			} else {
				// 新建登陆类型
				itemCtmLogin.setCustid(custid);
				itemCtmLogin.setLogintype("02");
				itemCtmLogin.setLoginid(phone);
				itemCtmLogin.getDeal().setAction(DataAction.Create.getAction());
			}
			accountDao.SaveCtmLogin(itemCtmLogin);

			LogCustmodify itemCustmodify = new LogCustmodify();
			itemCustmodify.setCustid(custid);
			UUID uuid = UUID.randomUUID();
			itemCustmodify.setLogid(uuid.toString().replace("-", ""));
			itemCustmodify.setLogdate(new Date());
			itemCustmodify.setLogoperator(custid);
			itemCustmodify.setLogtype("01");
			itemCustmodify.setLogdetail("用户修改手机号码");
			itemCustmodify.getDeal().setAction(DataAction.Create.getAction());
			accountDao.Savelogcustmodify(itemCustmodify);// 日志保存

			rtv.setSuccess(true);
			rtv.setMsg("手机号码修改成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 上午10:34:50
	 * @描述: 修改密码
	 * @param custid
	 * @param password
	 *          新密码
	 * @param rtv
	 */
	@Override
	@Transactional
	public void UpdatePassWord(String custid, String password, ReturnValue rtv) {
		CtmBase userBase = new CtmBase();
		userBase.getSearch().setSearch(" a.custid = '" + custid + "'");
		try {
			List<CtmBase> user = accountDao.SearchUser(userBase);
			if (user.size() < 1) {
				rtv.setMsg("该用户不存在");
				return;
			}
			user.get(0).setCustpwd(ToolUtils.GetMD5(password));
			user.get(0).getDeal().setAction(DataAction.Modify.getAction());// 修改t_ctm_base中用户的密码
			accountDao.saveUser(user.get(0));

			LogCustmodify itemCustmodify = new LogCustmodify();
			itemCustmodify.setCustid(custid);
			UUID uuid = UUID.randomUUID();
			itemCustmodify.setLogid(uuid.toString().replace("-", ""));
			itemCustmodify.setLogdate(new Date());
			itemCustmodify.setLogoperator(custid);
			itemCustmodify.setLogtype("02");// 用户修改密码
			itemCustmodify.setLogdetail("用户修改密码");
			itemCustmodify.getDeal().setAction(2);

			accountDao.Savelogcustmodify(itemCustmodify);
			rtv.setSuccess(true);
			rtv.setMsg("密码修改成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 上午10:34:50
	 * @描述: 微信快捷注册
	 */
	@Override
	@Transactional
	public void WXRegistered(CtmBase user, String custsource, ReturnValue rtv) {
		try {
			// 微信用户基础表保存信息

			// 用户关联必要信息
			user.setCustid(MyTools.CreateID("AC"));
			user.setCustsex("00");// 用户性别-未知
			user.setCustsource(custsource);// 用户来源
			user.setCusttype("00");// 用户类型-未知
			user.setCustgrade("01");// 用户登记-铜牌会员
			user.setSafelevel("01");// 安全登记-低
			user.setCuststatus("01");// 用户状态-有效

			// 用户创建日期和用户初始登陆日期
			user.setLogindate(new Date());
			user.setRegdate(new Date());

			user.getDeal().setAction(DataAction.Create.getAction());
			accountDao.saveUser(user);
			
			//创建用户送积分
			String jfString = MyTools.CreateID("JF");
			ActIntegral itemActIntegral = new ActIntegral();
			itemActIntegral.setIntegralid(jfString);
			itemActIntegral.setCustid(user.getCustid());
			itemActIntegral.setAccountstatus("01");//积分账户正常
			itemActIntegral.setIntegralbalance(100);//初始积分账户先设为100后面肯定要从数据库读出来
			itemActIntegral.getDeal().setAction(DataAction.Create.getAction());
			accountDao.SaveActIntegral(itemActIntegral);
			
			//积分结算表
			ActIntegralMonth itemActIntegralMonth = new ActIntegralMonth();
			itemActIntegralMonth.setIntegralid(jfString);
			itemActIntegralMonth.setCustid(user.getCustid());
			itemActIntegralMonth.setTranym(ToolUtils.GetFmtDate(new Date(), "yyyyMM"));
			itemActIntegralMonth.setIntegralbalance(itemActIntegral.getIntegralbalance());
			itemActIntegralMonth.getDeal().setAction(DataAction.Create.getAction());
			accountDao.SaveActIntegralMonth(itemActIntegralMonth);
			
			
			//积分明细表新增数据
			String trString = MyTools.CreateID("TR");
			ActIntegralDetail itemActIntegralDetail = new ActIntegralDetail();
			itemActIntegralDetail.setTraceid(trString);
			itemActIntegralDetail.setIntegralid(jfString);
			itemActIntegralDetail.setTranintegral(100);
			itemActIntegralDetail.setTrantype("01");//表示积分增加
			itemActIntegralDetail.setTrandate(new Date());
			itemActIntegralDetail.setTrandesc("新建用户送积分");
			itemActIntegralDetail.getDeal().setAction(DataAction.Create.getAction());
			accountDao.SaveActIntegralDetail(itemActIntegralDetail);
			
			
			
			

			rtv.setSuccess(true);
			rtv.setMsg("用户注册成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * @author rcp
	 * @param custid
	 * @param username
	 * @param rtv
	 * @return
	 */
	public boolean Judge(String custid, String logintype) {
		boolean flag = false;
		// 根据custid和logintype去登陆类型表查看存不存在信息
		CtmLogin item = new CtmLogin();
		item.getSearch().setSearch("a.custid = '" + custid + "' and a.logintype = '" + logintype + "'");
		List<CtmLogin> itemsCtmLogins = accountDao.SearchLoginType(item);// 获取用户登录类型

		if (itemsCtmLogins.size() >= 1) {
			flag = true;
		}

		return flag;

	}
	
	public static void main(String[] args){
		
		
		System.out.println(ToolUtils.GetFmtDate(new Date(), "yyyyMMdd"));
		
	}

	@Override
	@Transactional
	public List<ActCoupon> SearchActCoupon(ActCoupon item, ReturnValue rtv) {
		try {
			List<ActCoupon> lists = accountDao.SearchActCoupon(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<ActCoupon>();
		}
	}

	@Override
	@Transactional
	public List<ActUserCoupon> SearchActUserCoupon(ActUserCoupon item, ReturnValue rtv) {
		try {
			List<ActUserCoupon> lists = accountDao.SearchActUserCoupon(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<ActUserCoupon>();
		}
	}

	@Override
	@Transactional
	public void SaveActUserCoupon(ActUserCoupon item, ReturnValue rtv) {
		try {
			accountDao.SaveActUserCoupon(item);
			rtv.setSuccess(true);
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public List<ActIntegral> SearchActIntegral(ActIntegral item, ReturnValue rtv) {
		try {
			List<ActIntegral> lists = accountDao.SearchActIntegral(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<ActIntegral>();
		}
	}

	@Override
	public List<ActIntegralMonth> SearchActIntegralMonth(ActIntegralMonth item, ReturnValue rtv) {
		try {
			List<ActIntegralMonth> lists = accountDao.SearchActIntegralMonth(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<ActIntegralMonth>();
		}
	}

	@Override
	public List<ActIntegralDetail> SearchActIntegralDetail(ActIntegralDetail item, ReturnValue rtv) {
		try {
			List<ActIntegralDetail> lists = accountDao.SearchActIntegralDetail(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<ActIntegralDetail>();
		}
	}

	@Override
	public List<ActPreOrderCoupon> SearchActPreOrderCoupon(ActPreOrderCoupon item, ReturnValue rtv) {
		try {
			List<ActPreOrderCoupon> lists = accountDao.SearchActPreOrderCoupon(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<ActPreOrderCoupon>();
		}
	}

}
