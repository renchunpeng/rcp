package com.lvfulo.service.prd.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdBrand;
import com.lflweb.entity.prd.PrdImage;
import com.lflweb.entity.prd.PrdPackage;
import com.lflweb.entity.prd.PrdPackagedetail;
import com.lflweb.entity.prd.PrdPackageimg;
import com.lflweb.entity.prd.PrdType;
import com.lvfulo.dao.prd.PrdDao;
import com.lvfulo.service.prd.PrdService;
import com.sun.star.uno.RuntimeException;

@Service
public class PrdServiceImpl implements PrdService {
	@Autowired
	private PrdDao prdDao;

	/**
	 * @author：rcp
	 * @接口功能：商品列表分页查询查询
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<PrdBase> searchPrdBase(PrdBase item, ReturnValue rtv) {
		try {
			List<PrdBase> addresses = prdDao.searchPrdBase(item);
			rtv.setSuccess(true);
			return addresses;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdBase>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：商品sku图片查询
	 */
	@Override
	@Transactional
	public List<PrdImage> searchPrdimage(PrdImage item, ReturnValue rtv) {
		try {
			List<PrdImage> lists = prdDao.searchPrdImage(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdImage>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：商品类别查询
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<PrdType> searchPrdType(PrdType item, ReturnValue rtv) {
		try {
			List<PrdType> addresses = prdDao.searchPrdType(item);
			rtv.setSuccess(true);
			return addresses;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdType>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：查询商品品牌表
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<PrdBrand> searchPrdBrand(PrdBrand item, ReturnValue rtv) {
		try {
			List<PrdBrand> addresses = prdDao.searchPrdBrand(item);
			rtv.setSuccess(true);
			return addresses;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdBrand>();
		}

	}

	/**
	 * @author：rcp
	 * @接口功能：查询商品套餐表
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<PrdPackage> searchPrdPackage(PrdPackage item, ReturnValue rtv) {
		try {
			List<PrdPackage> addresses = prdDao.searchPrdPackage(item);
			rtv.setSuccess(true);
			return addresses;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdPackage>();
		}
	}

	@Override
	public List<PrdPackageimg> searchPrdPackageimg(PrdPackageimg item, ReturnValue rtv) {
		try {
			List<PrdPackageimg> lists = prdDao.searchPrdPackageimg(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdPackageimg>();
		}
	}

	@Override
	public List<PrdPackagedetail> SearchPrdPackagedetail(PrdPackagedetail item, ReturnValue rtv) {
		try {
			List<PrdPackagedetail> lists = prdDao.SearchPrdPackagedetail(item);
			rtv.setSuccess(true);
			return lists;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdPackagedetail>();
		}
	}

}
