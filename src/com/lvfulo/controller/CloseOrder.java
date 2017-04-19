package com.lvfulo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActPreOrderCoupon;
import com.lflweb.entity.ord.OrdBase;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.ord.OrdDao;

public class CloseOrder {
	@Autowired
	private OrdDao ordDao;

	@Autowired
	private AccountDao accountDao;

	@Transactional
	public void CloseOrd() {
		System.out.println("关闭订单！");
		OrdBase itemBase = new OrdBase();
		List<OrdBase> lists = ordDao.searchAllOrdBase(itemBase);
		for (OrdBase ordBase : lists) {
			if (ordBase.getOrderstatus().equals("01")) {// 扫描未支付订单
				long createdate = ordBase.getCreatedate().getTime();
				Date nowDate = new Date();
				long nowdate = nowDate.getTime();
				if ((createdate + 1000 * 60 * 30) < nowdate) {
					// 说明订单已经超过半小时了 这时候要将订单状态设为03
					ordBase.setOrderstatus("03");
					ordBase.getDeal().setAction(DataAction.Modify.getAction());
					ordDao.saveOrdBase(ordBase);

					// 将该订单绑定的优惠券解绑
					ActPreOrderCoupon item2 = new ActPreOrderCoupon();
					item2.getSearch().setSearch(" a.orderid = '" + ordBase.getOrderid() + "'");
					List<ActPreOrderCoupon> lists2 = accountDao.SearchActPreOrderCoupon(item2);
					for (ActPreOrderCoupon actPreOrderCoupon : lists2) {
						actPreOrderCoupon.getDeal().setAction(DataAction.Delete.getAction());
						accountDao.SaveActPreOrderCoupon(actPreOrderCoupon);
					}
				}

			}
		}
	}
}