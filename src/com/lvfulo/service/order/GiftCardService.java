package com.lvfulo.service.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.mar.MarCon;
import com.lflweb.entity.mar.MarMem;

@Service
public interface GiftCardService{
	
	/**
	 * 根据用户编号查询礼品卡列表
	 * @param custId
	 * @param memStatus
	 * @return
	 */
	public List<MarMem> GetCardListByCustId(MarMem marMem, ReturnValue rtv);

	/**
	 * 根据礼品卡号查询配送记录
	 * @param memId
	 * @return
	 */
	public List<MarCon> GetCardConByMemId(String memId, ReturnValue rtv);
}