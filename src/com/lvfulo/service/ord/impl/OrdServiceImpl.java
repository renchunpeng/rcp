package com.lvfulo.service.ord.impl;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActIntegralDetail;
import com.lflweb.entity.act.ActIntegralMonth;
import com.lflweb.entity.act.ActInvoiceTrans;
import com.lflweb.entity.act.ActInvoiceTransMain;
import com.lflweb.entity.act.ActInvoiceTransdetail;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.bas.BasFeeset;
import com.lflweb.entity.log.LogOrderPay;
import com.lflweb.entity.mar.MarMem;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.ord.OrdBaseMain;
import com.lflweb.entity.ord.OrdDeliveryComment;
import com.lflweb.entity.ord.OrdHistoryShop;
import com.lflweb.entity.ord.OrdPrdComment;
import com.lflweb.entity.ord.OrdReturn;
import com.lflweb.entity.ord.OrdReturnTrack;
import com.lflweb.entity.ord.OrdShop;
import com.lflweb.entity.ord.OrdSubdetail;
import com.lflweb.entity.ord.OrdTrack;
import com.lflweb.entity.ord.RuleIntegral;
import com.lflweb.entity.prd.PrdAllSelect;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdPackage;
import com.lflweb.entity.prd.PrdTypeSelect;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.log.LogDao;
import com.lvfulo.dao.mar.MarDao;
import com.lvfulo.dao.ord.OrdDao;
import com.lvfulo.dao.prd.PrdDao;
import com.lvfulo.service.log.LogService;
import com.lvfulo.service.ord.OrdService;
import com.lvfulo.utils.Arith;
import com.lvfulo.utils.MyTools;
import com.lvfulo.utils.ToolUtils;

@Service
public class OrdServiceImpl implements OrdService {
	@Autowired
	private OrdDao ordDao;

	@Autowired
	private PrdDao prdDao;

	@Autowired
	private MarDao marDao;

	@Autowired
	private SqlSessionTemplate sqlSessionTemp;

	private LogDao logDao;

	@Autowired
	private LogService logservice;

	@Autowired
	private AccountDao accountDao;

	/**
	 * @author：rcp
	 * @接口功能：购物车查询
	 * @时间：2017/2/27
	 */
	@Override
	@Transactional
	public List<OrdShop> searchOrdShop(OrdShop item, ReturnValue rtv) {
		try {
			List<OrdShop> lists = ordDao.searchOrdShop(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			return new ArrayList<OrdShop>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：用户订单基础表查询
	 * @时间：2017/2/27
	 */
	@Override
	@Transactional
	public List<OrdBase> searchOrdBase(OrdBase item, ReturnValue rtv) {
		try {
			List<OrdBase> lists = ordDao.searchOrdBase(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			return new ArrayList<OrdBase>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：订单明细查询
	 * @时间：2017/2/27
	 */
	@Override
	@Transactional
	public List<OrdSubdetail> searchOrdSubdetails(OrdSubdetail item, ReturnValue rtv) {
		try {
			List<OrdSubdetail> lists = ordDao.searchOrdSubdetails(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			return new ArrayList<OrdSubdetail>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：对订单保存接口进行细化 1.如果使用了优惠券，那么在前面判定的基础上接着判定优惠金额加实际支付金额是否等于订单金额
	 *                   2.如果没有使用优惠券，那么判定实际支付金额是否等于订单金额
	 * @时间：2017/2/27
	 */
	@Override
	@Transactional
	public void SaveOrdBaseMid(OrdBaseMain itemOrdBaseMain, OrdBase itemOrdBase, List<OrdSubdetail> details, ActInvoiceTransMain invoiceTransMain, ActInvoiceTrans invoiceTrans,
			List<ActInvoiceTransdetail> invoiceTransdetails, List<ActUserCoupon> coupons, boolean flag, Boolean isyouhui, ReturnValue rtv) {
		try {
			// TODO 判断商品是否已经下架，同时判断价格，税率
			for (int i = 0; i < details.size(); i++) {
				// 对订单明细中的数据按照prdkind区分，01是商品，02是套餐
				if (details.get(i).getPrdkind().equals("01")) {
					String prdidString = details.get(i).getPrdid();
					PrdBase itempPrdBase = new PrdBase();
					itempPrdBase.getSearch().setSearch(" a.prdid='" + prdidString + "'");
					itempPrdBase.getSearch().setStart(0);
					itempPrdBase.getSearch().setEnd(1);
					List<PrdBase> items = prdDao.searchPrdBase(itempPrdBase);
					if (items.get(0).getPrdstatus().equals("02")) {
						System.out.println("该商品已经下架：" + prdidString);
						rtv.setMsg("该商品已经下架：" + prdidString);
						return;
					}

					// 判断传入的税额和商品基础表中的是否一致
					String detailprdtax = String.valueOf(details.get(i).getPrdtax());
					String prdlistprdtax = String.valueOf(items.get(0).getPrdtax());
					if (!detailprdtax.equals(prdlistprdtax)) {
						System.out.println("该商品税率与后台不符:" + prdidString);
						rtv.setMsg("该商品税率与后台不符：" + prdidString);
						return;
					}

					// 判断传入的单价和商品基础表中的是否一致
					String detailprice = String.valueOf(details.get(i).getPrdprice());
					String prdlistprdprice = String.valueOf(items.get(0).getPrdprice());
					if (!detailprice.equals(prdlistprdprice)) {
						System.out.println("该商品单价与后台不符:" + prdidString);
						rtv.setMsg("该商品单价与后台不符：" + prdidString);
						return;
					}
				} else if (details.get(i).getPrdkind().equals("02")) {
					// 查找商品套餐表信息
					String packageidString = details.get(i).getPrdid();
					PrdPackage itempprdpacPackage = new PrdPackage();
					itempprdpacPackage.getSearch().setSearch(" a.packageid='" + packageidString + "'");
					itempprdpacPackage.getSearch().setStart(0);
					itempprdpacPackage.getSearch().setEnd(1);
					List<PrdPackage> items = prdDao.searchPrdPackage(itempprdpacPackage);

					if (items.get(0).getPackagestatus().equals("02")) {
						System.out.println("该套餐已经下架：" + packageidString);
						rtv.setMsg("该套餐已经下架：" + packageidString);
						return;
					}

					// 判断传入的税额和商品基础表中的是否一致
					String detailprdtax = String.valueOf(details.get(i).getPrdtax());
					String prdlistprdtax = String.valueOf(items.get(0).getPackagetax());
					if (!detailprdtax.equals(prdlistprdtax)) {
						System.out.println("该套餐税率与后台不符:" + packageidString);
						rtv.setMsg("该套餐税率与后台不符：" + packageidString);
						return;
					}

					// 判断传入的单价和商品基础表中的是否一致
					String detailprice = String.valueOf(details.get(i).getPrdprice());
					String prdlistprdprice = String.valueOf(items.get(0).getPackageprice());
					if (!detailprice.equals(prdlistprdprice)) {
						System.out.println("该套餐基础价格与后台不符:" + packageidString);
						rtv.setMsg("该套餐基础价格与后台不符：" + packageidString);
						return;
					}
				}

			}

			Double b = new Double(0);// 初始化订单计算金额
			for (int i = 0; i < details.size(); i++) {
				int num = details.get(i).getPrdnum();// 商品数量
				double price = details.get(i).getPrdprice();// 商品单价
				b = Arith.add(String.valueOf(b), String.valueOf(Arith.mul(String.valueOf(num), String.valueOf(price))));

				// 计算订单总金额的同时计算税额
				String b1 = String.valueOf(details.get(i).getSumprice());// 该商品总价
				String b2 = String.valueOf(details.get(i).getPrdtax() / 100);// 税率
				double xxString = Arith.mul(b1, b2);
				details.get(0).setTaxamt(xxString);
			}

			if (!(itemOrdBaseMain.getTotalprice() == b)) {
				System.out.println("计算金额：" + b);
				System.out.println("订单金额：" + itemOrdBaseMain.getTotalprice());
				rtv.setMsg("订单金额与计算金额不符");
				return;
			}

			if (flag) {
				// TODO 判断订单和发票的总金额是否相等，然后后判断各自总金额和计算总金额是否相等
				if (itemOrdBaseMain.getRealprice() != invoiceTrans.getInvoiceamt()) {
					rtv.setMsg("订单实付金额和开票金额不对应！");
					return;
				}
			}

			if (isyouhui) {
				// 判断是否存在混用的优惠券
				int mixflag = 0;
				for (ActUserCoupon actUserCoupon : coupons) {
					if (!actUserCoupon.isIsmixuse()) {
						mixflag++;
					}
				}

				if (mixflag >= 1 && coupons.size() >= 2) {
					rtv.setMsg("存在不可混用的优惠券");
					return;
				}

				// 判断传入的用户绑定优惠券有没有失效，同时判断优惠金额和满减区间是否正确

				Double sum = new Double(0);// 初始化优惠券计算金额
				for (ActUserCoupon actUserCoupon : coupons) {

					sum = Arith.add(String.valueOf(sum), String.valueOf(actUserCoupon.getCouponmoney()));

					actUserCoupon.getSearch().setSearch(" a.bdcouponid = '" + actUserCoupon.getBdcouponid() + "'");
					List<ActUserCoupon> lists = accountDao.SearchActUserCoupon(actUserCoupon);
					String quString = ToolUtils.GetFmtDate(lists.get(0).getOverdate(), "yyyy-MM-dd hh:mm:ss");
					String nowString = ToolUtils.GetFmtDate(new Date(), "yyyy-MM-dd hh:mm:ss");
					if (quString.compareTo(nowString) < 0) {
						rtv.setMsg("该优惠券已经过期：" + actUserCoupon.getBdcouponid());
						return;
					}

					if (actUserCoupon.getPrdid().equals("")) {
						if (lists.get(0).getLowlimit() != actUserCoupon.getLowlimit()) {
							rtv.setMsg("优惠券订单下限与数据库不符：" + actUserCoupon.getBdcouponid());
							return;
						}

						if (lists.get(0).getUplimit() != actUserCoupon.getUplimit()) {
							rtv.setMsg("优惠券订单上限与数据库不符:" + actUserCoupon.getBdcouponid());
							return;
						}
					}

					if (lists.get(0).getCouponmoney() != actUserCoupon.getCouponmoney()) {
						rtv.setMsg("优惠券优惠金额与数据库不符:" + actUserCoupon.getBdcouponid());
						return;
					}

				}

				// TODO 判断实付金额加上优惠金额是否和订单金额相等
				double jisuan = Arith.add(String.valueOf(sum), String.valueOf(itemOrdBase.getRealprice()));
				if (jisuan != itemOrdBase.getTotalprice()) {
					rtv.setMsg("实付金额加优惠金额不等于订单金额：" + jisuan + " " + itemOrdBase.getTotalprice());
					return;
				}
			} else {
				// TODO 没有使用优惠券判定实付金额和订单金额是否相等
				if (itemOrdBaseMain.getRealprice() != itemOrdBaseMain.getTotalprice()) {
					rtv.setMsg("没有使用优惠券，实付金额和订单金额不等！");
					return;
				}
			}

			SaveOrdBaseEnd(itemOrdBaseMain, itemOrdBase, details, invoiceTransMain, invoiceTrans, invoiceTransdetails, coupons, flag, rtv);

			rtv.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @return
	 * @author：rcp
	 * @接口功能：未拆单保存订单
	 * @时间：2017/2/27
	 */
	@Transactional
	public void SaveOrdBaseEnd(OrdBaseMain itemOrdBaseMain, OrdBase itemOrdBase, List<OrdSubdetail> details, ActInvoiceTransMain invoiceTransMain, ActInvoiceTrans invoiceTrans,
			List<ActInvoiceTransdetail> invoiceTransdetails, List<ActUserCoupon> coupons, boolean flag, ReturnValue rtv) {
		try {
			String createorderidString = MyTools.CreateID("OR");
			String fapiaoid = "";
			if (flag) {
				// TODO 发票流水表主表保存
				fapiaoid = MyTools.CreateID("IN");
				invoiceTransMain.setInvoicetranid(fapiaoid);
				invoiceTransMain.setCreatedate(new Date());// 创建时间
				invoiceTransMain.setInvoicestatus("01");// 发票预生成
				invoiceTransMain.getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveActInvoiceTransMain(invoiceTransMain);

				// TODO 发票拆单表保存
				invoiceTrans.setInvoicetranid(fapiaoid);
				invoiceTrans.setCreatedate(new Date());// 创建时间
				invoiceTrans.setInvoicestatus("01");// 发票预生成
				invoiceTrans.getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveActInvoiceTrans(invoiceTrans);

				// TODO 发票子表保存
				for (int i = 0; i < invoiceTransdetails.size(); i++) {
					invoiceTransdetails.get(i).setInvoicetranid(fapiaoid);
					invoiceTransdetails.get(i).setOrderid(createorderidString);
					invoiceTransdetails.get(i).getDeal().setAction(DataAction.Create.getAction());
					ordDao.saveActInvoiceTransDetail(invoiceTransdetails.get(i));
				}
			}

			// TODO 订单基础表主表保存
			System.out.println(createorderidString);
			itemOrdBaseMain.setOrderid(createorderidString);
			itemOrdBaseMain.setOrderstatus("01");// 订单状态
			itemOrdBaseMain.setPaystatus("01");// 支付状态
			itemOrdBaseMain.setOrderflag("01");// 订单标记
			itemOrdBaseMain.setCreatedate(new Date());// 订单创建时间
			itemOrdBaseMain.setIsinvoice(flag);// 先默认未开发票
			itemOrdBaseMain.setInvoicetranid(fapiaoid);// 发票流水号

			itemOrdBaseMain.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdBaseMain(itemOrdBaseMain);

			// TODO 订单基础表保存
			System.out.println(createorderidString);
			itemOrdBase.setOrderid(createorderidString);
			itemOrdBase.setOrderstatus("01");// 订单状态
			itemOrdBase.setPaystatus("01");// 支付状态
			itemOrdBase.setOrderflag("01");// 订单标记
			itemOrdBase.setCreatedate(new Date());// 订单创建时间
			itemOrdBase.setIsinvoice(flag);// 先默认未开发票
			itemOrdBase.setInvoicetranid(fapiaoid);// 发票流水号

			itemOrdBase.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdBase(itemOrdBase);

			// TODO 订单子表保存
			for (int i = 0; i < details.size(); i++) {
				details.get(i).setOrderid(createorderidString);
				details.get(i).getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveOrdSubdetails(details.get(i));
			}

			// 删除购物车的商品信息
			for (OrdSubdetail detail : details) {
				String custidString = itemOrdBase.getCustid();
				OrdShop itemOrdShop = new OrdShop();
				itemOrdShop.getDeal().setAction(5);// 根据cusuid和orderid删除数据
				itemOrdShop.setCustid(custidString);
				itemOrdShop.setPrdid(detail.getPrdid());
				ordDao.saveOrdShop(itemOrdShop);// 删除已经购买的商品

				// // 将已经购买的商品放入历史购物车(t_ord_history_shop)
				OrdHistoryShop itemHistoryShop = new OrdHistoryShop();
				itemHistoryShop.setCustid(custidString);
				itemHistoryShop.setPrdid(detail.getPrdid());
				itemHistoryShop.setPrdcount(detail.getPrdnum());
				itemHistoryShop.setCreatedate(new Date());
				itemHistoryShop.setPrdtype(detail.getPrdtype());
				itemHistoryShop.setShopid(detail.getShopid());
				itemHistoryShop.setPrdkind(detail.getPrdkind());
				itemHistoryShop.getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveOrdHistoryShop(itemHistoryShop);// 保存到历史购物车
			}

			// 保存订单关联优惠券

			// 保存订单关联优惠券
			for (ActUserCoupon actUserCoupon : coupons) {
				ActPreOrderCoupon itemActPreOrderCoupon = new ActPreOrderCoupon();
				itemActPreOrderCoupon.setOrderid(createorderidString);
				itemActPreOrderCoupon.setBdcouponid(actUserCoupon.getBdcouponid());
				itemActPreOrderCoupon.getDeal().setAction(DataAction.Create.getAction());
				accountDao.SaveActPreOrderCoupon(itemActPreOrderCoupon);
			}

			rtv.setSuccess(true);
			rtv.setMsg(createorderidString);

		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			throw new RuntimeException();
		}
	}

	/**
	 * @return
	 * @author：rcp
	 * @接口功能：专门为拆单准备的数据统一保存接口
	 * @时间：2017/2/27
	 */
	@Transactional
	public void SaveOrdBaseEnd2(OrdBase itemOrdBase, List<OrdSubdetail> details, ActInvoiceTrans invoiceTrans, List<ActInvoiceTransdetail> invoiceTransdetails, boolean flag, ReturnValue rtv) {
		try {

			String fapiaoid = "";
			String createorderidString = MyTools.CreateID("OR");
			if (flag) {
				// TODO 发票拆单表保存
				fapiaoid = MyTools.CreateID("IN");
				invoiceTrans.setInvoicetranid(fapiaoid);
				invoiceTrans.setCreatedate(new Date());// 创建时间
				invoiceTrans.setInvoicestatus("01");// 发票预生成
				invoiceTrans.getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveActInvoiceTrans(invoiceTrans);

				// TODO 发票子表保存
				for (int i = 0; i < invoiceTransdetails.size(); i++) {
					invoiceTransdetails.get(i).setInvoicetranid(fapiaoid);
					invoiceTransdetails.get(i).setOrderid(createorderidString);
					invoiceTransdetails.get(i).getDeal().setAction(DataAction.Create.getAction());
					ordDao.saveActInvoiceTransDetail(invoiceTransdetails.get(i));
				}
			}

			// TODO 订单基础表保存
			System.out.println(createorderidString);
			itemOrdBase.setOrderid(createorderidString);
			itemOrdBase.setInvoicetranid(fapiaoid);// 发票流水号
			itemOrdBase.setPaydate(new Date());// 支付完成时间
			itemOrdBase.setShopid(details.get(0).getShopid());// 商家编号
			itemOrdBase.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdBase(itemOrdBase);

			// TODO 订单子表保存
			for (int i = 0; i < details.size(); i++) {
				details.get(i).setOrderid(createorderidString);
				details.get(i).getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveOrdSubdetails(details.get(i));
			}

			// t_ord_track新增记录
			OrdTrack ordTrack = new OrdTrack();
			ordTrack.setOrderid(createorderidString);
			ordTrack.setUpdatedate(new Date());
			ordTrack.setOriginstatus("01");
			ordTrack.setNewstatus("02");// 订单状态为已支付
			ordTrack.setDealoperator(itemOrdBase.getCustid());
			ordTrack.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdTrack(ordTrack);

			// region 支付日志
			savelogordpay(itemOrdBase, rtv);
			// endregion

			rtv.setSuccess(true);
			rtv.setMsg(createorderidString);
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @时间：2017/3/7
	 * @接口功能：支付日志保存接口
	 */
	@Transactional
	public void savelogordpay(OrdBase itemOrdBase, ReturnValue rtv) {
		try {
			// region 支付日志
			LogOrderPay itemLogOrderPay = new LogOrderPay();
			UUID uuid = UUID.randomUUID();
			itemLogOrderPay.setLogid(uuid.toString().replace("-", ""));
			itemLogOrderPay.setOrderid(itemOrdBase.getOrderid()); /* 订单编号 */
			itemLogOrderPay.setPaytype(itemOrdBase.getPaytype()); /* 获取主订单的支付类型 */
			itemLogOrderPay.setPaydate(new Date());
			itemLogOrderPay.setPaysysid(itemOrdBase.getPaysysid());
			/* 付款金额 */
			double fuc = (double) itemOrdBase.getTotalprice() / 100;
			System.out.println("调整过后的金额：" + fuc);
			itemLogOrderPay.setPayprice(fuc);
			/* 支付交易类型,这个是指支付还是退款，这里01是支付 */
			itemLogOrderPay.setPaykind("01");
			/* 用户编号 */
			itemLogOrderPay.setCustid(itemOrdBase.getCustid());

			/* 获取税率，不知道为什么老是取不出 */
			// BasFeeset item1 = new BasFeeset();
			// item1.getSearch().setSearch(" a.feeid = '03' ");
			// List<BasFeeset> itemsFeesets = logDao.SearchBasFeeset(item1);
			List<BasFeeset> itemsFeesets = new ArrayList<BasFeeset>();
			BasFeeset item1 = new BasFeeset();
			item1.setFee(0.6);
			itemsFeesets.add(item1);
			/* end */

			itemLogOrderPay.setFeerate((double) itemsFeesets.get(0).getFee());
			String b1 = String.valueOf(itemLogOrderPay.getPayprice()); /* 付款金额 */
			String b2 = String.valueOf((double) itemsFeesets.get(0).getFee() / 100); /* 税率 */
			String xxString = String.valueOf(Arith.mul(b1, b2));

			itemLogOrderPay.setServicefee(Arith.savetwo(xxString));
			itemLogOrderPay.getDeal().setAction(DataAction.Create.getAction());
			logservice.SaveLogOrderPay(itemLogOrderPay, rtv);
			// endregion
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * @return
	 * @author：rcp
	 * @接口功能：拆单
	 * @时间：2017/2/27
	 */
	@Transactional
	public void SplitOrder(OrdBase itemOrdBase, List<OrdSubdetail> details, ActInvoiceTrans invoiceTrans, boolean flag, ReturnValue rtv) {
		try {
			// 优惠券列表
			List<ActUserCoupon> coupontotal = new ArrayList<ActUserCoupon>();
			double youhui = 0;
			// 先根据orderid取出所有预支付订单绑定优惠券
			ActPreOrderCoupon preitem = new ActPreOrderCoupon();
			preitem.getSearch().setSearch("a.orderid = '" + itemOrdBase.getOrderid() + "'");
			List<ActPreOrderCoupon> prelists = accountDao.SearchActPreOrderCoupon(preitem);

			// 然后去用户绑定优惠券中将涉及的优惠券都绑定订单号，标注是否使用和使用日期,顺便计算出使用的优惠券一共优惠了多少钱
			for (ActPreOrderCoupon actPreOrderCoupon : prelists) {
				ActUserCoupon actuseritem = new ActUserCoupon();
				actuseritem.getSearch().setSearch(" a.bdcouponid = '" + actPreOrderCoupon.getBdcouponid() + "'");
				List<ActUserCoupon> actuserlists = accountDao.SearchActUserCoupon(actuseritem);
				actuserlists.get(0).setIsuse(true);
				actuserlists.get(0).setOrderid(actPreOrderCoupon.getOrderid());
				actuserlists.get(0).setUsedate(new Date());
				actuserlists.get(0).getDeal().setAction(DataAction.Modify.getAction());
				accountDao.SaveActUserCoupon(actuserlists.get(0));
				coupontotal.add(actuserlists.get(0));
				// 这里只计算满减的优惠，单独对商品的优惠剔除
				if (actuserlists.get(0).getPrdid().equals("")) {
					String xString = String.valueOf(youhui);
					String yString = String.valueOf(actuserlists.get(0).getCouponmoney());
					youhui = Arith.add(xString, yString);
				}
			}

			// 获取需要拆成多少单
			Set<String> set = new HashSet<String>();
			for (int i = 0; i < details.size(); i++) {
				set.add(details.get(i).getShopid());
			}

			Iterator<String> it = set.iterator();
			// 遍历set取出shopid拆单
			while (it.hasNext()) {
				// 先拆子表，算出总金额，再克隆主表赋值
				List<OrdSubdetail> itemdetail1 = new ArrayList<OrdSubdetail>();// 给月卡的
				List<OrdSubdetail> itemdetail2 = new ArrayList<OrdSubdetail>();// 给普通商品的
				List<OrdSubdetail> itemdetail3 = new ArrayList<OrdSubdetail>();// 给预售商品的

				List<ActInvoiceTransdetail> itemActInvoiceTransdetails1 = new ArrayList<ActInvoiceTransdetail>();// 给月卡的
				List<ActInvoiceTransdetail> itemActInvoiceTransdetails2 = new ArrayList<ActInvoiceTransdetail>();// 给普通商品的
				List<ActInvoiceTransdetail> itemActInvoiceTransdetails3 = new ArrayList<ActInvoiceTransdetail>();// 给预售商品的

				double itemprice1 = 0;// 给月卡的
				double itemprice2 = 0;// 给普通商品的
				double itemprice3 = 0;// 给预售商品的
				String shopid = it.next();
				for (int i = 0; i < details.size(); i++) {
					// 取出openid相同并且prdkind不是03的数据
					if (shopid.equals(details.get(i).getShopid())) {
						if (details.get(i).getPrdkind().equals("03")) {// 拆月卡
							// region 购买月卡之后生成会员卡基础信息记录
							SaveMarMem(itemOrdBase.getCustid(), itemOrdBase.getOrderid(), details.get(i).getPrdid());// 需要传入custid,orderid,prdid
							// endregion
							itemdetail1.add(details.get(i));
							if (flag) {
								// 新建发票明细表插入
								ActInvoiceTransdetail rcpActInvoiceTransdetail = new ActInvoiceTransdetail();
								rcpActInvoiceTransdetail.setOrderamt(details.get(i).getPrdprice());
								itemActInvoiceTransdetails1.add(rcpActInvoiceTransdetail);
							}

							int num = details.get(i).getPrdnum();// 商品数量
							double price = details.get(i).getPrdprice();// 商品单价
							String mid = String.valueOf(Arith.mul(String.valueOf(num), String.valueOf(price)));
							itemprice1 = Arith.add(String.valueOf(itemprice1), mid);
						} else if (details.get(i).getPrdkind().equals("04")) {// 拆预售商品
							itemdetail3.add(details.get(i));
							if (flag) {
								// 新建发票明细表插入
								ActInvoiceTransdetail rcpActInvoiceTransdetail = new ActInvoiceTransdetail();
								rcpActInvoiceTransdetail.setOrderamt(details.get(i).getPrdprice());
								itemActInvoiceTransdetails3.add(rcpActInvoiceTransdetail);
							}

							int num = details.get(i).getPrdnum();// 商品数量
							double price = details.get(i).getPrdprice();// 商品单价
							String mid = String.valueOf(Arith.mul(String.valueOf(num), String.valueOf(price)));
							itemprice3 = Arith.add(String.valueOf(itemprice3), mid);

						} else {
							itemdetail2.add(details.get(i));
							if (flag) {
								// 新建发票明细表插入
								ActInvoiceTransdetail rcpActInvoiceTransdetail = new ActInvoiceTransdetail();
								rcpActInvoiceTransdetail.setOrderamt(details.get(i).getPrdprice());
								itemActInvoiceTransdetails2.add(rcpActInvoiceTransdetail);
							}

							int num = details.get(i).getPrdnum();// 商品数量
							double price = details.get(i).getPrdprice();// 商品单价
							String mid = String.valueOf(Arith.mul(String.valueOf(num), String.valueOf(price)));
							itemprice2 = Arith.add(String.valueOf(itemprice2), mid);
						}

					}
				}

				// 删除原来主表数据
				itemOrdBase.getDeal().setAction(DataAction.Delete.getAction());
				ordDao.saveOrdBase(itemOrdBase);

				// 删除原来发票数据
				invoiceTrans.getDeal().setAction(DataAction.Delete.getAction());
				ordDao.saveActInvoiceTrans(invoiceTrans);

				// 给月卡的
				if (itemdetail1.size() >= 1) {
					// 新建订单主表
					OrdBase itemmain = new OrdBase();
					itemmain = (OrdBase) itemOrdBase.clone();
					itemmain.setOrderfather(itemmain.getOrderid());// 以后可以找到是由什么订单拆过来的
					// itemmain.setPaytype("02");// 表示支付成功
					itemmain.setShopid(itemdetail1.get(0).getShopid());// 添加店铺号
					itemmain.setTotalprice(itemprice1);

					// 订单实际支付金额要按比例扣除优惠券金额
					double mid = ToolUtils.kehcu(itemprice1, itemOrdBase.getTotalprice(), youhui);
					double endrealprice = jisuanrealprice(itemdetail1, coupontotal, mid);
					itemmain.setRealprice(endrealprice);

					// 新建发票主表
					ActInvoiceTrans itemActInvoiceTrans = new ActInvoiceTrans();
					if (flag) {
						itemActInvoiceTrans = (ActInvoiceTrans) invoiceTrans.clone();
						itemActInvoiceTrans.setInvoicefather(itemActInvoiceTrans.getInvoiceid());
						itemActInvoiceTrans.setInvoiceamt(itemmain.getRealprice());
					}

					SaveOrdBaseEnd2(itemmain, itemdetail1, itemActInvoiceTrans, itemActInvoiceTransdetails1, flag, rtv);
				}

				// 给普通商品的
				if (itemdetail2.size() >= 1) {
					OrdBase itemmain = new OrdBase();
					itemmain = (OrdBase) itemOrdBase.clone();
					itemmain.setOrderfather(itemmain.getOrderid());// 以后可以找到是由什么订单拆过来的
					// itemmain.setPaytype("02");// 表示支付成功
					itemmain.setShopid(itemdetail2.get(0).getShopid());// 添加店铺号
					itemmain.setTotalprice(itemprice2);
					// 订单实际支付金额要按比例扣除优惠券金额
					// itemmain.setRealprice(ToolUtils.kehcu(itemprice2,
					// itemOrdBase.getTotalprice(), youhui));
					double mid = ToolUtils.kehcu(itemprice2, itemOrdBase.getTotalprice(), youhui);
					double endrealprice = jisuanrealprice(itemdetail2, coupontotal, mid);
					itemmain.setRealprice(endrealprice);
					// 新建发票主表
					ActInvoiceTrans itemActInvoiceTrans = new ActInvoiceTrans();
					if (flag) {
						itemActInvoiceTrans = (ActInvoiceTrans) invoiceTrans.clone();
						itemActInvoiceTrans.setInvoicefather(itemActInvoiceTrans.getInvoiceid());
						itemActInvoiceTrans.setInvoiceamt(itemmain.getRealprice());
					}

					SaveOrdBaseEnd2(itemmain, itemdetail2, itemActInvoiceTrans, itemActInvoiceTransdetails2, flag, rtv);
				}

				// 给预售商品的
				if (itemdetail3.size() >= 1) {
					OrdBase itemmain = new OrdBase();
					itemmain = (OrdBase) itemOrdBase.clone();
					itemmain.setOrderfather(itemmain.getOrderid());// 以后可以找到是由什么订单拆过来的
					// itemmain.setPaytype("02");// 表示支付成功
					itemmain.setShopid(itemdetail3.get(0).getShopid());// 添加店铺号
					itemmain.setTotalprice(itemprice3);
					// 订单实际支付金额要按比例扣除优惠券金额
					// itemmain.setRealprice(ToolUtils.kehcu(itemprice3,
					// itemOrdBase.getTotalprice(), youhui));
					double mid = ToolUtils.kehcu(itemprice3, itemOrdBase.getTotalprice(), youhui);
					double endrealprice = jisuanrealprice(itemdetail3, coupontotal, mid);
					itemmain.setRealprice(endrealprice);

					// 新建发票主表
					ActInvoiceTrans itemActInvoiceTrans = new ActInvoiceTrans();
					if (flag) {
						itemActInvoiceTrans = (ActInvoiceTrans) invoiceTrans.clone();
						itemActInvoiceTrans.setInvoicefather(itemActInvoiceTrans.getInvoiceid());
						itemActInvoiceTrans.setInvoiceamt(itemmain.getRealprice());
					}

					SaveOrdBaseEnd2(itemmain, itemdetail3, itemActInvoiceTrans, itemActInvoiceTransdetails3, flag, rtv);
				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @return
	 * @author：rcp
	 * @接口功能：根据shopid判断是否要拆单
	 * @时间：2017/2/27
	 */
	public boolean judge(List<OrdSubdetail> details) {
		boolean chai = false;
		Set<String> set = new HashSet<String>();

		if (details.size() == 1) {

		} else {
			for (int i = 0; i < details.size(); i++) {
				set.add(details.get(i).getShopid());
			}

			if (set.size() >= 2) {
				chai = true;
			}
		}

		return chai;
	}

	/**
	 * @return
	 * @author：rcp
	 * @接口功能：这里传过来的数据保证shopid都是一样的
	 * @时间：2017/2/27
	 */
	public boolean judge2(List<OrdSubdetail> details) {
		boolean chai = false;
		int x = 0;
		for (int i = 0; i < details.size(); i++) {
			if (details.get(i).getPrdkind().equals("03")) {// 03代表礼品卡
				chai = true;
				x++;
			}

		}

//		if (x == details.size()) {
//			chai = false;
//		}

		return chai;
	}

	/**
	 * @return
	 * @author：rcp
	 * @接口功能：微信回掉之后的相关处理
	 * @时间：2017/2/27
	 */
	@Override
	@Transactional
	public void RetuenChai(String paytype, String orderid, String appid, String paysysid, ReturnValue rtv) {
		try {

			boolean kpflag = false;
			// 获取订单明细
			OrdSubdetail itemOrdSubdetail = new OrdSubdetail();
			itemOrdSubdetail.getSearch().setSearch("a.orderid = '" + orderid + "'");
			List<OrdSubdetail> lists = ordDao.searchOrdSubdetails(itemOrdSubdetail);
			boolean flag = judge(lists);// 是否需要拆单的标志

			// 获取订单基础表
			OrdBase itemordBase = new OrdBase();
			itemordBase.getSearch().setSearch("a.orderid = '" + orderid + "'");
			itemordBase.getSearch().setStart(0);
			itemordBase.getSearch().setEnd(1);
			List<OrdBase> ordBases = ordDao.searchOrdBase(itemordBase);
			OrdBase itemBase2 = ordBases.get(0);
			itemBase2.setOrderstatus("02");// 订单状态改为支付成功
			itemBase2.setPaydate(new Date());// 订单支付时间
			itemBase2.setPaystatus("02");// 支付状态改为成功支付
			itemBase2.setPaysysid(paysysid);// 微信服务器传过来的支付订单号
			itemBase2.setPaytype(paytype);// 确定最终支付方式
			itemBase2.setAppid(appid);// 保存appid用来区分来自大山还是雀巢
			itemBase2.setOrderfather(orderid);// 假如后面拆单的话会把这个覆盖掉

			// 创建发票流水对象
			ActInvoiceTrans rcpInvoiceTrans = new ActInvoiceTrans();

			if (!itemBase2.getInvoicetranid().equals("")) {
				kpflag = true;
				ActInvoiceTrans itemactActInvoiceTrans = new ActInvoiceTrans();
				itemactActInvoiceTrans = new ActInvoiceTrans();
				itemactActInvoiceTrans.getSearch().setSearch("a.invoicetranid = '" + itemBase2.getInvoicetranid() + "'");
				itemactActInvoiceTrans.getSearch().setStart(0);
				itemactActInvoiceTrans.getSearch().setEnd(1);
				List<ActInvoiceTrans> listsActInvoiceTrans = ordDao.searchActInvoiceTrans(itemactActInvoiceTrans);
				rcpInvoiceTrans = listsActInvoiceTrans.get(0);
				rcpInvoiceTrans.setCreatedate(new Date());// 创建日期
				rcpInvoiceTrans.setPaytype(paytype);
				rcpInvoiceTrans.setPaysysid(paysysid);
				rcpInvoiceTrans.setAppid(appid);
				rcpInvoiceTrans.setPaystatus("02");
			}

			// region 拆单之前先做积分结算
			/*
			 * 1.首先循环明细表去数据库找出所有商品对应的积分是多少 2.用custid查询出积分主表信息 3.将计算出的积分信息累加到用户积分账户
			 * 4.然后将计算出的积分累加到月度结算表，如果存在同一个月的就相加，没有同一个月的新建 5.最后将累加的积分添加到账户明细
			 */
			int accountjf = 0;
			for (OrdSubdetail list : lists) {
				String prdidString = list.getPrdid();
				// 这里要做一个区分，区分到底是基础商品还是套餐商品
				if (list.getPrdkind().equals("02")) {
					PrdPackage itemPrdPackage = new PrdPackage();
					itemPrdPackage.getSearch().setSearch("a.packageid = '" + prdidString + "'");
					itemPrdPackage.getSearch().setStart(0);
					itemPrdPackage.getSearch().setEnd(1);
					List<PrdPackage> prdPackages = prdDao.searchPrdPackage(itemPrdPackage);
					accountjf += prdPackages.get(0).getGiveintegral();

				} else {
					PrdBase itemprdBase = new PrdBase();
					itemprdBase.getSearch().setSearch("a.prdid = '" + prdidString + "'");
					itemprdBase.getSearch().setStart(0);
					itemprdBase.getSearch().setEnd(1);
					List<PrdBase> prdbaselists = prdDao.searchPrdBase(itemprdBase);
					accountjf += prdbaselists.get(0).getGiveintegral();
				}

			}

			// TODO 这里还要加上积分规则表需要加上的数据
			RuleIntegral itemIntegral = new RuleIntegral();
			List<RuleIntegral> listruIntegrals = ordDao.searchruleintegral(itemIntegral);
			double realprice = itemBase2.getRealprice();
			for (RuleIntegral ruleIntegral : listruIntegrals) {
				if (ruleIntegral.getLowerlimit() <= realprice && ruleIntegral.getUpperlimit1() >= realprice) {
					if (ruleIntegral.getIsalsoenjoy()) {
						// 可共享
						accountjf += ruleIntegral.getGiveintegral();

					} else {
						// 不可共享
						if (accountjf >= ruleIntegral.getGiveintegral()) {

						} else {
							accountjf = ruleIntegral.getGiveintegral();
						}
					}
					break;
				}
			}

			// 用户积分主表添加积分
			String custidString = itemBase2.getCustid();
			ActIntegral itemactActIntegral = new ActIntegral();
			itemactActIntegral.getSearch().setSearch("a.custid = '" + custidString + "'");
			List<ActIntegral> itemact2 = accountDao.SearchActIntegral(itemactActIntegral);
			int midmain = accountjf + itemact2.get(0).getIntegralbalance();
			itemact2.get(0).setIntegralbalance(midmain);
			itemact2.get(0).getDeal().setAction(DataAction.Modify.getAction());
			accountDao.SaveActIntegral(itemact2.get(0));

			// 月度结算表修改或者新建信息
			String newdataString = ToolUtils.GetFmtDate(new Date(), "yyyyMM");
			ActIntegralMonth itemActIntegralMonth = new ActIntegralMonth();
			itemActIntegralMonth.getSearch().setSearch("a.integralid = '" + itemact2.get(0).getIntegralid() + "' and a.tranym = '" + newdataString + "'");
			List<ActIntegralMonth> listactActIntegralMonths = accountDao.SearchActIntegralMonth(itemActIntegralMonth);

			if (listactActIntegralMonths.size() >= 1) {
				ActIntegralMonth actIntegralMonth = listactActIntegralMonths.get(0);
				// 结算表中已经存在该月信息
				int midmonth = accountjf + actIntegralMonth.getIntegralbalance();
				actIntegralMonth.setIntegralbalance(midmonth);
				actIntegralMonth.getDeal().setAction(DataAction.Modify.getAction());
				accountDao.SaveActIntegralMonth(actIntegralMonth);
			} else {
				ActIntegralMonth actm2 = new ActIntegralMonth();
				actm2.setIntegralid(itemact2.get(0).getIntegralid());
				actm2.setCustid(itemact2.get(0).getCustid());
				actm2.setTranym(newdataString);
				actm2.setIntegralbalance(accountjf);
				actm2.getDeal().setAction(DataAction.Create.getAction());
				accountDao.SaveActIntegralMonth(actm2);
			}

			// 积分明细表新增数据
			String trString = MyTools.CreateID("TR");
			ActIntegralDetail itemActIntegralDetail = new ActIntegralDetail();
			itemActIntegralDetail.setTraceid(trString);
			itemActIntegralDetail.setIntegralid(itemact2.get(0).getIntegralid());
			itemActIntegralDetail.setTranintegral(accountjf);
			itemActIntegralDetail.setTrantype("01");// 表示积分增加
			itemActIntegralDetail.setTrandate(new Date());
			itemActIntegralDetail.setTrandesc("买商品送积分");
			itemActIntegralDetail.getDeal().setAction(DataAction.Create.getAction());
			accountDao.SaveActIntegralDetail(itemActIntegralDetail);

			// endregion

			// region 开始拆
			if (!flag) {
				// 订单中只有一个店铺
				if (judge2(lists)) {
					// SplitOrder 传过去的数据最好将一些数据已经写好这样后面直接克隆就行
					SplitOrder(itemBase2, lists, rcpInvoiceTrans, kpflag, rtv);
				} else {
					// 先根据orderid取出所有预支付订单绑定优惠券
					ActPreOrderCoupon preitem = new ActPreOrderCoupon();
					preitem.getSearch().setSearch("a.orderid = '" + itemBase2.getOrderid() + "'");
					List<ActPreOrderCoupon> prelists = accountDao.SearchActPreOrderCoupon(preitem);

					// 然后去用户绑定优惠券中将涉及的优惠券都绑定订单号，标注是否使用和使用日期,顺便计算出使用的优惠券一共优惠了多少钱
					for (ActPreOrderCoupon actPreOrderCoupon : prelists) {
						ActUserCoupon actuseritem = new ActUserCoupon();
						actuseritem.getSearch().setSearch(" a.bdcouponid = '" + actPreOrderCoupon.getBdcouponid() + "'");
						List<ActUserCoupon> actuserlists = accountDao.SearchActUserCoupon(actuseritem);
						actuserlists.get(0).setIsuse(true);
						actuserlists.get(0).setOrderid(actPreOrderCoupon.getOrderid());
						actuserlists.get(0).setUsedate(new Date());
						actuserlists.get(0).getDeal().setAction(DataAction.Modify.getAction());
						accountDao.SaveActUserCoupon(actuserlists.get(0));
					}
					// 不需要拆单，改变订单状态就可以了
					itemBase2.getDeal().setAction(DataAction.Modify.getAction());
					ordDao.saveOrdBase(itemBase2);

					if (!itemBase2.getInvoicetranid().equals("")) {
						rcpInvoiceTrans.getDeal().setAction(DataAction.Modify.getAction());
						ordDao.saveActInvoiceTrans(rcpInvoiceTrans);
					}

					// t_ord_track新增记录
					OrdTrack ordTrack = new OrdTrack();
					ordTrack.setOrderid(orderid);
					ordTrack.setUpdatedate(new Date());
					ordTrack.setOriginstatus("01");
					ordTrack.setNewstatus("02");// 订单状态为已支付
					ordTrack.setDealoperator(itemBase2.getCustid());
					ordTrack.getDeal().setAction(DataAction.Create.getAction());
					ordDao.saveOrdTrack(ordTrack);

					// region 支付日志
					savelogordpay(itemBase2, rtv);
					// endregion
				}

			} else {
				// 订单中包含多个店铺 SplitOrder 传过去的数据最好将一些数据已经写好这样后面直接克隆就行
				SplitOrder(itemBase2, lists, rcpInvoiceTrans, kpflag, rtv);
			}
			// endregion
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public List<OrdShop> GetListOrdShop(OrdShop ordShop, ReturnValue rtv) {
		try {
			List<OrdShop> lists = ordDao.GetListOrdShop(ordShop);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			rtv.setMsg(e.getMessage());
			return new ArrayList<OrdShop>();
		}
	}

	@Override
	public List<OrdShop> SearchOrdShop(OrdShop ordShop, ReturnValue rtv) {
		try {
			List<OrdShop> ordList = ordDao.searchOrdShop(ordShop);
			rtv.setSuccess(true);
			return ordList;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<OrdShop>();
		}
	}

	@Override
	public void SaveOrdShop(OrdShop ordShop, ReturnValue rtv) {
		try {
			ordDao.saveOrdShop(ordShop);
			rtv.setSuccess(true);
			rtv.setMsg("成功提交");
		} catch (Exception e) {
			rtv.setSuccess(false);
			rtv.setMsg(e.getMessage());
		}

	}

	@Override
	public void SaveOrdShopList(List<OrdShop> ordShopList, ReturnValue rtv) {
		ErrorMsg errmsg = new ErrorMsg();
		SqlSession session = sqlSessionTemp.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		String insertsql = "insert into t_ord_shop(custid,prdid,prdcount,createdate,shopstatus,updatedate,prdtype,prdkind,shopid) values " + "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = session.getConnection().prepareStatement(insertsql);
			Timestamp time = new java.sql.Timestamp(new Date().getTime());
			for (OrdShop ordShop : ordShopList) {
				if (ordShop.OnBeforeSave(errmsg)) {
					rtv.setMsg(errmsg.getErrmsg());
					return;
				}
				ps.setString(1, ordShop.getCustid());
				ps.setString(2, ordShop.getPrdid());
				ps.setInt(3, ordShop.getPrdcount());
				ps.setTimestamp(4, time);
				ps.setString(5, ordShop.getShopstatus());
				ps.setTimestamp(6, time);
				ps.setString(7, ordShop.getPrdtype());
				ps.setString(8, ordShop.getPrdkind());
				ps.setString(9, ordShop.getShopid());
				ps.addBatch();
			}
			ps.executeBatch();
			rtv.setSuccess(true);
			rtv.setMsg("成功提交");

			session.commit();
			session.clearCache();
			ps.clearBatch();

		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			session.rollback();
			throw new RuntimeException();
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @author rcp
	 * @version 创建时间:2017年3月7日 上午9:18:10
	 * @描述: 确认收货
	 * @param custid
	 * @param rtv
	 */
	@Override
	@Transactional
	public void UpdateOrdBase(String orderid, String custid, ReturnValue rtv) {
		try {
			// 在修改订单状态之前将订单状态保存下来
			String oldstatus = "";

			// 修改订单状态
			OrdBase item = new OrdBase();
			item.getSearch().setSearch("a.orderid = '" + orderid + "'");
			item.getSearch().setStart(0);
			item.getSearch().setEnd(1);
			List<OrdBase> lists = ordDao.searchOrdBase(item);
			oldstatus = lists.get(0).getOrderstatus();
			lists.get(0).setOrderstatus("06");// 订单状态改为已收货
			lists.get(0).getDeal().setAction(DataAction.Modify.getAction());
			ordDao.saveOrdBase(lists.get(0));

			// 订单状态跟踪表新增记录t_ord_track
			OrdTrack ordTrack = new OrdTrack();
			ordTrack.setOrderid(lists.get(0).getOrderid());
			ordTrack.setUpdatedate(new Date());
			ordTrack.setOriginstatus(oldstatus);
			ordTrack.setNewstatus("06");// 订单状态为已支付
			ordTrack.setDealoperator(custid);
			ordTrack.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdTrack(ordTrack);

			rtv.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * 
	 * @author rcp
	 * @version 创建时间:2017年3月6日 下午5:52:08
	 * @名称: 订单退单接口
	 * @描述:首先取出订单id去订单表查询订单信息，然后判断订单状态，假如是还没有支付的就直接关闭订单，然后去预支付订单绑定优惠券解除绑定的优惠券 
	 *                                                                        假如是已经付款的将订单状态改为申请撤销
	 *                                                                        -08，
	 *                                                                        在订单退单表和退单跟踪表新增数据
	 * @param custid
	 * @param rtv
	 */
	@Override
	@Transactional
	public void ReturnOrdBase(OrdReturn itemreOrdReturn, ReturnValue rtv) {
		try {
			// 查询订单信息
			OrdBase ordBaseitem1 = new OrdBase();
			ordBaseitem1.getSearch().setSearch("a.orderid = '" + itemreOrdReturn.getOrderid() + "'");
			ordBaseitem1.getSearch().setStart(0);
			ordBaseitem1.getSearch().setEnd(1);
			List<OrdBase> lists = ordDao.searchOrdBase(ordBaseitem1);
			OrdBase ordbaseitem2 = lists.get(0);
			// 判断订单状态
			if (ordbaseitem2.getOrderstatus().equals("01")) {
				// TODO 订单未支付
				ordbaseitem2.setOrderstatus("03");// 订单未支付已取消
				ordbaseitem2.getDeal().setAction(DataAction.Modify.getAction());
				ordDao.saveOrdBase(ordbaseitem2);

				// 将该订单绑定的优惠券解绑
				ActPreOrderCoupon item2 = new ActPreOrderCoupon();
				item2.getSearch().setSearch(" a.orderid = '" + itemreOrdReturn.getOrderid() + "'");
				List<ActPreOrderCoupon> lists2 = accountDao.SearchActPreOrderCoupon(item2);
				for (ActPreOrderCoupon actPreOrderCoupon : lists2) {
					actPreOrderCoupon.getDeal().setAction(DataAction.Delete.getAction());
					accountDao.SaveActPreOrderCoupon(actPreOrderCoupon);
				}
				rtv.setSuccess(true);
			} else {
				// TODO 申请撤销
				ordbaseitem2.setOrderstatus("08");// 申请撤销
				ordbaseitem2.getDeal().setAction(DataAction.Modify.getAction());
				ordDao.saveOrdBase(ordbaseitem2);

				// 订单退单表
				itemreOrdReturn.setReturnid(MyTools.CreateID("RE"));
				itemreOrdReturn.setReturnstatus("01");// 退单状态为-申请撤销
				itemreOrdReturn.setCreatedate(new Date());// 创建时间
				itemreOrdReturn.setOrderprice(ordbaseitem2.getRealprice());//订单实际支付金额
				itemreOrdReturn.setReturnprice(ordbaseitem2.getRealprice());//订单实际支付金额
				itemreOrdReturn.getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveOrdReturn(itemreOrdReturn);

				// 退单状态跟踪表
				OrdReturnTrack itemOrdReturnTrack = new OrdReturnTrack();
				itemOrdReturnTrack.setReturnid(itemreOrdReturn.getReturnid());
				itemOrdReturnTrack.setUpdatedate(new Date());
				itemOrdReturnTrack.setDealoperator(itemreOrdReturn.getCustid());
				itemOrdReturnTrack.setNewstatus("01");// 没有旧状态，新状态为申请撤销
				itemOrdReturnTrack.getDeal().setAction(DataAction.Create.getAction());
				ordDao.saveReturnTrack(itemOrdReturnTrack);
				rtv.setSuccess(true);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月8日 下午5:06:46
	 * @描述: 订单评价 1.将配送评价和商品评价分别保存 2.订单状态改为已评价，iscomment改为true，记录评价时间 3.订单跟踪表中插入记录
	 * @param delivery
	 *          配送评价
	 * @param prds
	 *          商品评价列表--每个商品都有单独的评价
	 * @param rtv
	 */
	@Override
	@Transactional
	public void SaveComment(OrdDeliveryComment delivery, List<OrdPrdComment> prds, ReturnValue rtv) {
		try {

			delivery.setDcommentid(MyTools.CreateID("PD"));
			delivery.setCommdate(new Date());
			delivery.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdDeliveryComment(delivery);

			for (OrdPrdComment prd : prds) {
				prd.setPcommentid(MyTools.CreateID("PP"));
				prd.setCommdate(new Date());
				prd.getDeal().setAction(DataAction.Create.getAction());
				prd.setCommstatus("01");
				ordDao.saveOrdPrdComment(prd);
			}

			// t_ord_base改为已评价
			OrdBase itemBase = new OrdBase();
			itemBase.getSearch().setSearch(" a.orderid = '" + delivery.getOrderid() + "'");
			itemBase.getSearch().setStart(0);
			itemBase.getSearch().setEnd(1);
			List<OrdBase> itemBases = ordDao.searchOrdBase(itemBase);

			String originstatus = itemBases.get(0).getOrderstatus();
			OrdBase itemBase2 = itemBases.get(0);
			itemBase2.setOrderstatus("07");// 订单状态改为已评论
			itemBase2.setIscomment(true);// 已评论
			itemBase2.setCommentdate(new Date());// 评价时间
			// 修改订单状态为以评价
			itemBase2.getDeal().setAction(3);
			ordDao.saveOrdBase(itemBase2);

			// t_ord_track新增记录
			OrdTrack ordTrack = new OrdTrack();
			ordTrack.setOrderid(delivery.getOrderid());
			ordTrack.setUpdatedate(new Date());
			ordTrack.setOriginstatus(originstatus);
			ordTrack.setNewstatus("07");// 订单状态为已评价
			ordTrack.setDealoperator(itemBase2.getCustid());
			// 向订单跟踪表中插入记录
			ordTrack.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdTrack(ordTrack);

			rtv.setSuccess(true);
			rtv.setMsg("评论保存成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	@Override
	public List<PrdAllSelect> searchPrdAllSelect(PrdAllSelect item, ReturnValue rtv) {
		try {
			List<PrdAllSelect> lists = ordDao.searchPrdAllSelect(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<PrdAllSelect>();
		}
	}

	@Override
	public List<PrdTypeSelect> searchPrdTypeSelect(PrdTypeSelect item, ReturnValue rtv) {
		try {
			List<PrdTypeSelect> lists = ordDao.searchPrdTypeSelect(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<PrdTypeSelect>();
		}
	}

	@Override
	public List<OrdPrdComment> SearchPrdCommon(OrdPrdComment item, ReturnValue rtv) {
		try {
			List<OrdPrdComment> lists = ordDao.SearchPrdCommon(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setSuccess(false);
			return new ArrayList<OrdPrdComment>();
		}
	}

	/**
	 * 计算realprice
	 */
	public double jisuanrealprice(List<OrdSubdetail> itemdetail, List<ActUserCoupon> actUserCoupons, double mid) {
		double prdpricecoupon = 0;
		for (OrdSubdetail itemdetail1detail : itemdetail) {
			for (ActUserCoupon actUserCoupon : actUserCoupons) {
				if (actUserCoupon.getPrdid().equals(itemdetail1detail.getPrdid())) {
					prdpricecoupon = Arith.add(String.valueOf(actUserCoupon.getCouponmoney()), String.valueOf(prdpricecoupon));
				}

			}
		}

		return Arith.sub(String.valueOf(mid), String.valueOf(prdpricecoupon));

	}

	/**
	 * 生成会员基础信息表记录
	 * 
	 * @author rcp
	 */
	@Transactional
	public void SaveMarMem(String custid, String orderid, String prdid) {
		try {
			MarMem marMem = new MarMem();
			String memid = MyTools.CreateID("ME");
			marMem.setMemid(memid);
			marMem.setPrdid(prdid);
			marMem.setCustid(custid);
			marMem.setOrderid(orderid);
			marMem.setCreatedate(new Date());
			// 查找商品对应的skutype
			String memtype = SearchSkuType(prdid);
			marMem.setMemtype(memtype);
			marMem.setMemstatus("01");
			marMem.getDeal().setAction(DataAction.Create.getAction());
			marDao.SaveMarMem(marMem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * 根据prdid查找sku类型
	 */
	public String SearchSkuType(String prdid) {
		PrdBase itemBase = new PrdBase();
		itemBase.getSearch().setSearch(" a.prdid = '" + prdid + "'");
		List<PrdBase> lists = prdDao.SearchSkuType(itemBase);
		return lists.get(0).getMemtype();

	}

	// public static void main(String[] args) {
	// OrdBase itemBase = new OrdBase();
	// itemBase.setAppid("666");
	// OrdBase rcpBase = (OrdBase) itemBase.clone();
	// OrdBase wqBase = (OrdBase) itemBase.clone();
	// wqBase.setAppid("wq");
	// System.out.println(rcpBase.getAppid());
	// }
}
