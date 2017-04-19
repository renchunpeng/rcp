package com.lvfulo.service.order.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.mar.MarCon;
import com.lflweb.entity.mar.MarMem;
import com.lvfulo.dao.ord.GiftCardDao;
import com.lvfulo.service.order.GiftCardService;

@Service
public class GiftCardServiceImpl implements GiftCardService {
	
	@Autowired
	private  GiftCardDao giftCardDao;
	
	@Override
	public List<MarMem> GetCardListByCustId(MarMem marMem, ReturnValue rtv) {
		try {
			List<MarMem> marMemList = giftCardDao.GetCardListByCustId(marMem);
			rtv.setSuccess(true);
			return marMemList;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<MarMem>();
		}		
	}

	@Override
	public List<MarCon> GetCardConByMemId(String memId, ReturnValue rtv) {
		try {
			List<MarCon> marConList = giftCardDao.GetCardConByMemId(memId);
			rtv.setSuccess(true);
			return marConList;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<MarCon>();
		}
	}
	
}