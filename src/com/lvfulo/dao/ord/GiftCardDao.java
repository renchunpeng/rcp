package com.lvfulo.dao.ord;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.mar.MarCon;
import com.lflweb.entity.mar.MarMem;

@Repository
public interface GiftCardDao {

	/**
	 * 根据用户编号查询礼品卡列表
	 * @param custId
	 * @param memStatus
	 * @return
	 */
	public List<MarMem> GetCardListByCustId(MarMem marmem);

	/**
	 * 根据礼品卡号查询配送记录
	 * @param memId
	 * @return
	 */
	public List<MarCon> GetCardConByMemId(String memId);
	
}
