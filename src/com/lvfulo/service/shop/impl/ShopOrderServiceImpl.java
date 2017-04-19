package com.lvfulo.service.shop.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.ord.OrdTrack;
import com.lvfulo.dao.ord.OrdDao;
import com.lvfulo.service.shop.ShopOrderService;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
	
	@Autowired
	private OrdDao ordDao;
	
	/**
	 * 店铺订单发货
	 */
	@Override
	@Transactional
	public void UpdateOrdSend(String orderid, String custid, ReturnValue rtv) {
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
			lists.get(0).setOrderstatus("04");// 订单状态改为已收货
			lists.get(0).getDeal().setAction(DataAction.Modify.getAction());
			ordDao.saveOrdBase(lists.get(0));

			// 订单状态跟踪表新增记录t_ord_track
			OrdTrack ordTrack = new OrdTrack();
			ordTrack.setOrderid(lists.get(0).getOrderid());
			ordTrack.setUpdatedate(new Date());
			ordTrack.setOriginstatus(oldstatus);
			ordTrack.setNewstatus("04");// 订单状态为已支付
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
	 * 店铺订单配送完成
	 */
	@Override
	@Transactional
	public void UpdateOrdSendOk(String orderid, String custid, ReturnValue rtv) {
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
			lists.get(0).setOrderstatus("05");// 订单状态改为已收货
			lists.get(0).getDeal().setAction(DataAction.Modify.getAction());
			ordDao.saveOrdBase(lists.get(0));

			// 订单状态跟踪表新增记录t_ord_track
			OrdTrack ordTrack = new OrdTrack();
			ordTrack.setOrderid(lists.get(0).getOrderid());
			ordTrack.setUpdatedate(new Date());
			ordTrack.setOriginstatus(oldstatus);
			ordTrack.setNewstatus("05");// 订单状态为已支付
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
	 * 店铺订单同意取消
	 */
	@Override
	@Transactional
	public void UpdateOrdCancel(String orderid, String custid, ReturnValue rtv) {
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
			lists.get(0).setOrderstatus("09");// 订单状态改为已收货
			lists.get(0).getDeal().setAction(DataAction.Modify.getAction());
			ordDao.saveOrdBase(lists.get(0));

			// 订单状态跟踪表新增记录t_ord_track
			OrdTrack ordTrack = new OrdTrack();
			ordTrack.setOrderid(lists.get(0).getOrderid());
			ordTrack.setUpdatedate(new Date());
			ordTrack.setOriginstatus(oldstatus);
			ordTrack.setNewstatus("09");// 订单状态为已支付
			ordTrack.setDealoperator(custid);
			ordTrack.getDeal().setAction(DataAction.Create.getAction());
			ordDao.saveOrdTrack(ordTrack);

			rtv.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}
	
}