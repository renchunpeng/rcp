package com.lvfulo.controller.custom;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ctm.CtmBase;
import com.lflweb.entity.log.LogCtmLogin;
import com.lvfulo.controller.wxping.WxMenuConsts;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.entity.WxTokenModel;
import com.lvfulo.entity.WxUserInfo;
import com.lvfulo.service.account.AccountService;
import com.lvfulo.service.wxservice.WxAuthService;
import com.lvfulo.utils.Consts;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/WXuser")
public class WXUserController {
	@Autowired
	AccountDao accountDao;

	@Autowired
	private WxAuthService wxAuthService;

	@Autowired
	private AccountService accountservice;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	// 服务器地址
	public String Address = WxMenuConsts.HSKIP;

	@RequestMapping(value = "/wxAuth/{state}")
	@ResponseBody
	public String wxAuth(@PathVariable("state") String state, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		System.out.println("刚进入授权页面的state：" + state);
		String authUrl = "";

		// TODO 先从数据库或者配置文件中读取出appid和secret以及accesstoken等信息
		String appId = Consts.JH_APPID;
		String appSecret = Consts.JH_APPIDSECRET;
		String accessToken = "";
		String openId = "";
		String code = "";

		// 用户同意授权后，能获取到code
		code = request.getParameter("code");
		// state = request.getParameter("state");
		System.out.println("第二次请求的state为：" + state);
		if (code == null) {
			code = "";
		}

		// code为空
		if (code.equals("") || code.equals("authdeny")) {
			if (code.equals("")) {
				System.out.println("====获取code01====这里是第一次与微信握手");
				// 发起授权(第一次微信握手)
				authUrl = wxAuthService.GetWxCode(appId, Address + "WXuser/wxAuth/" + state, "index-wx.html??#/onlineMarket/");

				System.out.println("第一次握手之后" + authUrl);
				try {
					response.sendRedirect(authUrl);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		} else {
			System.out.println("====有code了====");
			// 获取微信的accesstoken,openid(第二次微信握手)
			WxTokenModel wxTokenCode = new WxTokenModel();
			wxTokenCode = wxAuthService.GetAccessTokenByCode(appId, appSecret, code);

			accessToken = wxTokenCode.getAccessToken();
			openId = wxTokenCode.getOpenId();

			// 将openId存到cookies中
			System.out.println("获取到的openID为：" + openId);
			Cookie openIdCookie = new Cookie("openId", openId);
			// openid到期时间
			openIdCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(openIdCookie);

			// 获取微信用户信息(第三次微信握手)
			WxUserInfo wxUserInfo = new WxUserInfo();
			wxUserInfo = wxAuthService.GetWxUserInfo(accessToken, openId);

			System.out.println(wxUserInfo.getNickname());
			Cookie custwechatCookie = new Cookie("custwechat", URLEncoder.encode(wxUserInfo.getNickname(), "utf-8"));// 昵称
			Cookie wechatavatarCookie = new Cookie("wechatavatar", URLEncoder.encode(wxUserInfo.getHeadImgUrl(), "utf-8"));// 头像
			openIdCookie.setPath("/");
			custwechatCookie.setPath("/");
			wechatavatarCookie.setPath("/");

			response.addCookie(openIdCookie);
			response.addCookie(custwechatCookie);
			response.addCookie(wechatavatarCookie);
			System.out.println(custwechatCookie.getName() + "  将cookie加入到微信浏览器当中 " + custwechatCookie.getValue());

			// 如果这个openid在数据库存在的话，就只要直接返回就行
			CtmBase item = new CtmBase();
			item.getSearch().setSearch(" a.openid = '" + openId + "'");
			List<CtmBase> items = accountDao.SearchOnlyUser(item);

			if (items.size() < 1) {
				// 利用openid创建一个用户基本信息
				System.out.println("创建新用户！");
				this.getRtv().OnInit();
				CtmBase itemBase = new CtmBase();
				itemBase.setOpenid(openId);
				itemBase.setCustname(wxUserInfo.getNickname());
				itemBase.setWechatavatar(wxUserInfo.getHeadImgUrl());
				System.out.println(wxUserInfo.getHeadImgUrl());
				accountservice.WXRegistered(itemBase, "03", this.getRtv());

				// 加入返回信息出错直接返回
				if (!this.getRtv().isSuccess()) {
					System.out.println(this.getRtv().getMsg());
					return this.getRtv().toString();
				}
			}

			// 将openid存入本地之后还是返回getUserSession接口重新走一遍
			// String jumpUrl = Address + "WXuser/getUserSession/" + state;
			String jumpUrl = Address + "???#/onlineMarket/market";
			try {
				response.sendRedirect(jumpUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 微信判断用户是否已经授权登录过
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserSession/{state}")
	@ResponseBody
	public void getUserSession(HttpServletRequest request, @PathVariable("state") String state, HttpServletResponse response) {
		String openid = "";
		String custwechatCookie = "";// 用户微信昵称
		String wechatavatarCookie = "";// 微信头像

		// 从cookies中获取openid
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			System.out.println("通过这里确认cookie中有用户信息！");
			for (Cookie cookieWx : cookies) {
				String cookieIdentity = cookieWx.getName();
				// 查找身份串
				if (cookieIdentity.equals("openId")) {
					openid = cookieWx.getValue();
					System.out.println("此处查找到了openid" + openid);
				}
				// 用户微信昵称
				if (cookieIdentity.equals("custwechat")) {
					custwechatCookie = cookieWx.getValue();
					try {
						URLDecoder.decode(custwechatCookie, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					System.out.println("此处查找到了微信昵称" + custwechatCookie);
				}
				// 用户微信头像
				if (cookieIdentity.equals("wechatavatar")) {
					wechatavatarCookie = cookieWx.getValue();
					try {
						URLDecoder.decode(wechatavatarCookie, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					System.out.println("此处查找到了微信头像" + wechatavatarCookie);
				}

			}
		}

		// 如果没有openid
		if (openid.equals("")) {
			// 进行用户授权
			System.out.println("由于cookie中没有找到用户信息，则我们通过用户授权的方式来获取openid");
			try {
				response.sendRedirect(Address + "WXuser/wxAuth/" + state);
			} catch (IOException e) {
			}
		} else {
			System.out.println("======" + "用openid和用户表进行匹配");

			// 用openid和用户表进行匹配
			CtmBase item = new CtmBase();
			item.getSearch().setSearch(" a.openid = '" + openid + "'");
			List<CtmBase> items = accountDao.SearchOnlyUser(item);

			// 若用户存在，页面继续进行
			if (items.size() >= 1) {

				// 最终会进入这个页面
				try {
					response.sendRedirect(Address + "???#/onlineMarket/market");
				} catch (IOException e) {
				}

				// 用户登录日志保存完毕
			} else {
				// 本地有openid但是数据库没有查到数据，可能是数据库数据被清空了，重新授权建立一个账号就行
				try {
					response.sendRedirect(Address + "WXuser/wxAuth/" + state);
				} catch (IOException e) {
				}

			}
		}
		// System.out.println("此处显示的是最后的输出结果：" + result);
		// return result;
	}

	/**
	 * 返回信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserDate")
	@ResponseBody
	public JSONObject getUserDate(HttpServletRequest request, HttpServletResponse response) {
		String openid = "";
		Map<String, Object> modelMap = new HashMap<String, Object>();
		JSONObject result = new JSONObject();

		// 从cookies中获取openid
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookieWx : cookies) {
				String cookieIdentity = cookieWx.getName();
				// 查找身份串
				if (cookieIdentity.equals("openId")) {
					openid = cookieWx.getValue();
					System.out.println("此处查找到了openid111" + openid);
				}
			}
		}

		// 用openid和用户表进行匹配
		CtmBase item = new CtmBase();
		item.getSearch().setSearch(" a.openid = '" + openid + "'");
		List<CtmBase> items = accountDao.SearchOnlyUser(item);

		if (items.size() < 1) {
			System.out.println("没有查找到用户信息");
		} else {
			System.out.println("在数据库里找到了用户openid，在这种情况下将用户的信息返回！");
			// region 查询出用户的积分余额和优惠券的张数
			// 查询积分余额
			ActIntegral itemmain = new ActIntegral();
			itemmain.getSearch().setSearch(" a.custid = '" + items.get(0).getCustid() + "'");
			List<ActIntegral> listmaIntegrals = accountDao.SearchActIntegral(itemmain);
			int integralbalance = 0;
			if (listmaIntegrals.size() >= 1) {
				integralbalance = listmaIntegrals.get(0).getIntegralbalance();// 取出积分余额
			}

			// 查询优惠券张数
			ActUserCoupon itemActUserCoupon = new ActUserCoupon();
			String rpgsearch = "a.custid = '" + items.get(0).getCustid() + "'" + " and a.isuse = '0'" + " and a.overdate >='" + ToolUtils.GetFmtDate(new Date(), "yyyy-MM-dd hh:mm:ss") + "'";
			itemActUserCoupon.getSearch().setSearch(rpgsearch);
			List<ActUserCoupon> itemActUserCoupons = accountDao.SearchActUserCoupon(itemActUserCoupon);

			int sum = itemActUserCoupons.size();

			// endregion

			// endregion
			JSONObject user = JSONObject.fromObject(MyTools.OutListOne(items.get(0), true));
			JSONObject data = new JSONObject();
			System.out.println(user);
			data.put("custid", user.get("custid"));
			data.put("openid", user.get("dsopenid"));
			data.put("custname", user.get("custname"));
			data.put("custmobile", user.get("custmobile"));
			data.put("countyname", user.get("countyname"));
			data.put("countyid", user.get("custlocation"));
			data.put("zonecode", user.get("zonecode"));
			data.put("wechatavatar", user.get("wechatavatar"));
			data.put("integralbalance", integralbalance);
			data.put("sum", sum);
			modelMap.put("data", data);
			modelMap.put("success", true);
			modelMap.put("message", "success");
			// 拼接返回url,后期可以根据state拼接
			modelMap.put("url", Address + "WXuser/wxAuth/" + "#/onlineMarket/index");
			result = JSONObject.fromObject(modelMap);

			// 更新用户信息表当前登录时间
			CtmBase itemBase = items.get(0);
			itemBase.setLogindate(new Date());
			itemBase.getDeal().setAction(DataAction.Modify.getAction());
			accountDao.saveUser(itemBase);

			// 记录用户登录日志t_log_ctm_login
			LogCtmLogin itemCtmLogin = new LogCtmLogin();
			itemCtmLogin.setCustid((String) user.get("custid"));
			itemCtmLogin.setLogindate(new Date());
			itemCtmLogin.setCustsource("03");
			itemCtmLogin.setLoginip(ToolUtils.getIpAddress(request));
			itemCtmLogin.setLogindetail("登陆");
			itemCtmLogin.getDeal().setAction(DataAction.Create.getAction());
			accountDao.saveLogCtmLogin(itemCtmLogin);
		}

		return result;
	}

}
