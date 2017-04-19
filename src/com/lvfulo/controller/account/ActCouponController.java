package com.lvfulo.controller.account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.act.ActUserCouponMix;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.service.account.AccountService;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Controller
@RequestMapping("/act")
public class ActCouponController {
	// @Autowired
	// private AccountDao accountDao;
	@Autowired
	private AccountService accountService;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	/**
	 * 未绑定优惠券查询，已有的优惠券不显示
	 * 
	 * @param custid
	 * @return
	 */
	@RequestMapping(value = "/coupon/{custid}")
	@ResponseBody
	public ReturnValue ClearIntegral2(@PathVariable("custid") String custid) {
		this.getRtv().SetValues(false, "", null, false);
		try {
			// 先获取到优惠券列表的所有数据
			ActCoupon items = new ActCoupon();
			String itemsearchString = " a.effmonth >= '"
					+ ToolUtils.GetFmtDate(new Date(), "yyyy-MM-dd hh:mm:ss")
					+ "'";
			items.getSearch().setSearch(itemsearchString);
			List<ActCoupon> lists = accountService.SearchActCoupon(items,
					this.getRtv());
			// 根据custid去用户绑定优惠券表中查询该用户已经有哪些优惠券了
			ActUserCoupon itemActUserCoupon = new ActUserCoupon();
			String rpgsearch = "a.custid = '" + custid + "'"
					+ " and a.isuse = '0'" + " and a.overdate >='"
					+ ToolUtils.GetFmtDate(new Date(), "yyyy-MM-dd hh:mm:ss")
					+ "'";

			itemActUserCoupon.getSearch().setSearch(rpgsearch);
			List<ActUserCoupon> itemActUserCoupons = accountService
					.SearchActUserCoupon(itemActUserCoupon, this.getRtv());

			for (ActUserCoupon actUserCoupon : itemActUserCoupons) {// 这是已经拥有的
				// 在优惠券列表中去除用户绑定优惠券
				for (int i = lists.size() - 1; i >= 0; i--) {// 这是总列表
					if (actUserCoupon.getCouponid().equals(
							lists.get(i).getCouponid())) {
						lists.remove(i);
						break;
					}
				}

			}
			this.getRtv().setSuccess(true);
			this.getRtv().setData(lists);
			return this.getRtv();
		} catch (Exception e) {
			this.getRtv().setSuccess(false);
			return this.getRtv();
		}
	}

	/**
	 * 选取的优惠券和客户绑定
	 */
	// TODO 优惠券一起绑定
	@RequestMapping(value = "/savecoupon", method = { RequestMethod.POST })
	@ResponseBody
	public ReturnValue SaveCoupon(@RequestBody String item) {
		// @RequestMapping(value = "/savecoupon")
		// @ResponseBody
		// public ReturnValue SaveCoupon(){
		this.getRtv().OnInit();
		// String item =
		// "{\"custid\":\"AC201703153115372960\",\"item\":[{\"couponid\":\"7C0A29DABACC0935\",\"prdid\":\"e706e6e7029dd495\",\"uplimit\":0,\"lowlimit\":0,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"75689678976\",\"prdid\":\"\",\"uplimit\":0,\"lowlimit\":100,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"756856789769\",\"prdid\":\"\",\"uplimit\":0,\"lowlimit\":100,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"5467865798\",\"prdid\":\"e706e6e7029dd495\",\"uplimit\":0,\"lowlimit\":0,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"38D3EB6511EE3A0E\",\"prdid\":\"\",\"uplimit\":0,\"lowlimit\":100,\"couponmoney\":5,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":false,\"bewrite\":\"满100元减5元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"38D3EB6511EE3A0A\",\"prdid\":\"\",\"uplimit\":0,\"lowlimit\":50,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"满50元减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"354647487\",\"prdid\":\"e706e6e7029dd495\",\"uplimit\":0,\"lowlimit\":0,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"23462363265\",\"prdid\":\"e706e6e7029dd495\",\"uplimit\":0,\"lowlimit\":0,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"23452352352\",\"prdid\":\"e706e6e7029dd495\",\"uplimit\":0,\"lowlimit\":0,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}},{\"couponid\":\"234\",\"prdid\":\"e706e6e7029dd495\",\"uplimit\":0,\"lowlimit\":0,\"couponmoney\":3,\"effmonth\":\"2017-10-01 00:00:00\",\"ismixuse\":true,\"bewrite\":\"凭此券可使优惠商品1号减3元\",\"search\":{\"search\":\"\",\"start\":0,\"end\":0,\"total\":0,\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"begindate\":\"\",\"enddate\":\"\",\"year\":\"\",\"month\":\"\",\"day\":\"\",\"selparam\":\"\",\"currentpage\":0,\"pagesize\":0},\"deal\":{\"action\":0,\"procedurename\":\"\",\"ip\":\"\",\"userid\":\"\",\"username\":\"\",\"deptid\":\"\",\"remark\":null,\"edbname\":\"\",\"accountid\":\"\"},\"item\":{\"getaction\":\"\",\"procedurename\":\"\",\"userid\":\"\",\"deptid\":\"\",\"edbname\":\"\",\"accountid\":\"\",\"tableid\":0,\"bean\":null}}]}";
		item = item.replace("effmonth", "overdate");
		JSONObject jo = JSONObject.fromObject(item);
		String custid = jo.getString("custid");
		String items = jo.getString("item");
		List<ActUserCoupon> itemActUserCoupons = MyTools.GetArrayFromJson(
				items, ActUserCoupon.class);

		for (ActUserCoupon itemActUserCoupon : itemActUserCoupons) {
			itemActUserCoupon.setBdcouponid(MyTools.CreateID("DB"));
			itemActUserCoupon.setCustid(custid);
			itemActUserCoupon.setGetdate(new Date());
			itemActUserCoupon.setIsuse(false);
			itemActUserCoupon.getDeal()
					.setAction(DataAction.Create.getAction());
			accountService.SaveActUserCoupon(itemActUserCoupon, this.getRtv());
		}
		this.getRtv().setSuccess(true);
		return this.getRtv();
	}

	/**
	 * 查看我的优惠券
	 * 
	 * @param custid
	 * @return
	 */
	@RequestMapping(value = "/mycoupon/{custid}")
	@ResponseBody
	public ReturnValue ClearIntegral22(@PathVariable String custid) {
		this.getRtv().SetValues(false, "", null, false);
		try {

			// 先获取到用户绑定的所有优惠券
			ActUserCoupon itemActUserCoupon = new ActUserCoupon();
			String rpgsearch = "a.custid = '" + custid + "'"
					+ " and a.isuse = '0'" + " and a.overdate >='"
					+ ToolUtils.GetFmtDate(new Date(), "yyyy-MM-dd hh:mm:ss")
					+ "'";
			itemActUserCoupon.getSearch().setSearch(rpgsearch);
			List<ActUserCoupon> itemActUserCoupons = accountService
					.SearchActUserCoupon(itemActUserCoupon, this.getRtv());

			// 新建两个list，分别保存满减的优惠券和对应prdid折扣的优惠券
			List<ActUserCoupon> manjianActUserCoupons = new ArrayList<ActUserCoupon>();
			List<ActUserCoupon> prdidActUserCoupons = new ArrayList<ActUserCoupon>();

			for (ActUserCoupon list : itemActUserCoupons) {
				if (list.getPrdid().equals("")) {
					manjianActUserCoupons.add(list);
				} else {
					prdidActUserCoupons.add(list);
				}
			}

			ActUserCouponMix mix = new ActUserCouponMix();
			mix.setMianjianActUserCoupons(manjianActUserCoupons);
			mix.setPrdidActUserCoupons(prdidActUserCoupons);

			this.getRtv().setSuccess(true);
			this.getRtv().setData(mix);
			return this.getRtv();

		} catch (Exception e) {
			this.getRtv().setSuccess(false);
			return this.getRtv();
		}
	}

	/**
	 * 获取我的符合订单优惠券 item数据格式{ custid:"rcp", prdlist:"prdid1,prdid2",
	 * accountsum:150 }
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping(value = "/ordercoupon/{item}")
	@ResponseBody
	public ReturnValue ClearIntegral3(@PathVariable String item) {
		JSONObject kkObject = JSONObject.fromObject(item);
		this.getRtv().SetValues(false, "", null, false);
		try {
			// 先获取到用户绑定的所有优惠券
			ActUserCoupon itemActUserCoupon = new ActUserCoupon();
			String rpgsearch = "a.custid = '" + kkObject.getString("custid")
					+ "'" + " and a.isuse = '0'" + " and a.overdate >='"
					+ ToolUtils.GetFmtDate(new Date(), "yyyy-MM-dd hh:mm:ss")
					+ "'";
			itemActUserCoupon.getSearch().setSearch(rpgsearch);
			List<ActUserCoupon> itemActUserCoupons = accountService
					.SearchActUserCoupon(itemActUserCoupon, this.getRtv());

			// 新建两个list，分别保存满减的优惠券和对应prdid折扣的优惠券
			List<ActUserCoupon> manjianActUserCoupons = new ArrayList<ActUserCoupon>();
			List<ActUserCoupon> prdidActUserCoupons = new ArrayList<ActUserCoupon>();

			for (ActUserCoupon list : itemActUserCoupons) {
				if (list.getPrdid().equals("")) {
					manjianActUserCoupons.add(list);
				} else {
					prdidActUserCoupons.add(list);
				}
			}

			// 对两个list里的数据进行筛选
			// 满减的只需要判断价格区间就行
			for (int i = manjianActUserCoupons.size() - 1; i >= 0; i--) {
				ActUserCoupon itemActUserCoupon2 = manjianActUserCoupons.get(i);
				double price = kkObject.getDouble("accountsum");
				if (!(price >= itemActUserCoupon2.getLowlimit() && price <= itemActUserCoupon2
						.getUplimit())) {
					manjianActUserCoupons.remove(i);
				}
			}

			// 按照prdid的减免需要判断优惠券里的prdid是否和前台传过来的商品id符合，不符合就剔除
			for (int i = prdidActUserCoupons.size() - 1; i >= 0; i--) {
				ActUserCoupon www = prdidActUserCoupons.get(i);
				String prdidString = www.getPrdid();
				String[] stringArr = kkObject.getString("prdlist").split(","); // 注意分隔符是需要转译滴...
				// 数组转换成list
				List<String> list = Arrays.asList(stringArr);

				if (list.contains(prdidString)) {
					System.out.println("这个prdid存在");
				} else {
					prdidActUserCoupons.remove(i);
				}
			}

			ActUserCouponMix mix = new ActUserCouponMix();
			mix.setMianjianActUserCoupons(manjianActUserCoupons);
			mix.setPrdidActUserCoupons(prdidActUserCoupons);

			this.getRtv().setSuccess(true);
			this.getRtv().setData(mix);
			return this.getRtv();
		} catch (Exception e) {
			this.getRtv().setSuccess(false);
			return this.getRtv();
		}
	}

	public static void main(String[] args) {
		String rcpString = "{\"coupnid\":\"rcp11\",\"prdid\":\"er\",\"uplimit\":123,\"lowlimit\":100,\"couponmoney\":10,\"effmonth\":10,\"ismixuse\":1}";
		ActUserCoupon rcp = MyTools.GetOneFromJson(rcpString,
				ActUserCoupon.class);
		System.out.println(123);
	}
}