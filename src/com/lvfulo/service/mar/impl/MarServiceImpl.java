package com.lvfulo.service.mar.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.mar.MarAdv;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.prd.PrdBigType;
import com.lvfulo.dao.mar.MarDao;
import com.lvfulo.service.mar.MarService;

/**
 * @author rcp
 * @date 创建时间：2017年3月20日 下午1:50:40
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
@Service
public class MarServiceImpl implements MarService {
	@Autowired
	private MarDao marDao;

	@Override
	public List<MarAdv> SearchMarAdv(MarAdv item, ReturnValue rtv) {
		try {
			List<MarAdv> lists = marDao.SearchMarAdv(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<MarAdv>();
		}
	}

	@Override
	public List<PrdBigType> SearchPrdBigType(PrdBigType item, ReturnValue rtv) {
		try {
			List<PrdBigType> lists = marDao.SearchPrdBigType(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdBigType>();
		}
	}

	@Override
	public List<MarShopdetail> SearchMarShopdetail(MarShopdetail item, ReturnValue rtv) {
		try {
			List<MarShopdetail> lists = marDao.SearchMarShopdetail(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<MarShopdetail>();
		}
	}

	@Override
	@Transactional
	public void SaveMarShopdetail(MarShopdetail item, ReturnValue rtv) {
		try {
			marDao.SaveMarShopdetail(item);
			rtv.setSuccess(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
		
	}

}
