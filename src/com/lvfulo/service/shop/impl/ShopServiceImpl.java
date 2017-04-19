package com.lvfulo.service.shop.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.mar.MarApl;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdBaseimg;
import com.lflweb.entity.prd.PrdImage;
import com.lflweb.entity.prd.PrdSku;
import com.lvfulo.dao.prd.PrdDao;
import com.lvfulo.dao.shop.ShopDao;
import com.lvfulo.service.shop.ShopService;
import com.lvfulo.utils.MyTools;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;

	@Autowired
	private PrdDao prdDao;

	/**
	 * 根据用户编号查询用户店铺入驻情况
	 */
	public List<MarApl> GetShopApl(String custid, ReturnValue rtv) {
		try {
			MarApl marApl = new MarApl();
			marApl.getSearch().setSearch(" a.custid = '" + custid + "'");
			List<MarApl> itemShop = shopDao.GetShopApl(marApl);

			rtv.setSuccess(true);
			return itemShop;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<MarApl>();
		}
	}

	/**
	 * 根据店铺编号查询店铺基础信息
	 */
	public List<MarShopdetail> GetShopBase(String shopid, ReturnValue rtv) {
		try {
			MarShopdetail marShopdetail = new MarShopdetail();
			marShopdetail.getSearch().setSearch(" a.shopid = '" + shopid + "'");
			List<MarShopdetail> itemShop = shopDao.GetShopBase(marShopdetail);

			rtv.setSuccess(true);
			return itemShop;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<MarShopdetail>();
		}
	}

	/**
	 * 店铺注册
	 */
	@Override
	@Transactional
	public void SaveShopRegister(MarApl marApl, ReturnValue rtv) {
		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (marApl.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			marApl.getDeal().setAction(DataAction.Create.getAction());
			shopDao.SaveShopRegister(marApl);

			rtv.setSuccess(true);
			rtv.setData("");
			rtv.setMsg("店铺信息已提交");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * 店铺基础信息维护
	 * 
	 * @param marShopdetail
	 */
	public void SaveShopBase(MarShopdetail marShopdetail, ReturnValue rtv) {
		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (marShopdetail.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			marShopdetail.getDeal().setAction(DataAction.Create.getAction());
			shopDao.SaveShopBase(marShopdetail);

			rtv.setSuccess(true);
			rtv.setData("");
			rtv.setMsg("店铺入驻信息保存成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * 根据店铺编号查询店铺内商品
	 * 
	 * @param shopId
	 * @return
	 */
	public List<PrdBase> GetPrdListByShopId(PrdBase prdBase, ReturnValue rtv) {
		try {
			List<PrdBase> prdList = shopDao.GetPrdListByShopId(prdBase);
			rtv.setSuccess(true);
			return prdList;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<PrdBase>();
		}
	}

	/**
	 * 店铺商品发布
	 * 
	 * @param prdBase
	 */
	@Override
	@Transactional
	public void SaveShopPrd(PrdBase prdBase, ReturnValue rtv) {

		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (prdBase.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			// 保存商品sku信息
			PrdSku prdSku = new PrdSku();
			String skuId = MyTools.CreateID("SK");
			prdSku.setSkuid(skuId);
			prdSku.setSkuname(prdBase.getPrdname());
			prdSku.setSkudescription(prdBase.getSKUdescription());
			prdSku.setSkutype("01"); // 普通商品
			prdSku.getDeal().setAction(DataAction.Create.getAction());
			shopDao.SaveShopPrdSku(prdSku);

			// 保存SKU图片信息
			PrdImage prdImage = new PrdImage();
			String imageId = MyTools.CreateID("SI");
			prdImage.setImageid(imageId);
			prdImage.setImagename(prdBase.getPrdname()); // 暂时将商品名称当做图片名称
			prdImage.setSkuid(skuId);
			prdImage.setImageurl(prdBase.getImageid());
			prdImage.getDeal().setAction(DataAction.Create.getAction());
			shopDao.SaveShopPrdImage(prdImage);

			// 保存商品信息
			String prdId = MyTools.CreateID("PR");
			prdBase.setPrdid(prdId);
			prdBase.setSkuid(skuId);
			prdBase.getDeal().setAction(DataAction.Create.getAction());
			shopDao.SaveShopPrd(prdBase);

			// 保存商品图片信息
			PrdBaseimg prdBaseimg = new PrdBaseimg();
			imageId = MyTools.CreateID("PI");
			prdBaseimg.setImageid(imageId); // 可沿用sku的图片编号
			prdBaseimg.setImagename(prdBase.getPrdname()); // 暂时将商品名称当做图片名称
			prdBaseimg.setPrdid(prdId);
			prdBaseimg.setImageurl(prdBase.getImageid());
			prdBaseimg.getDeal().setAction(DataAction.Create.getAction());
			shopDao.SaveShopPrdBaseimg(prdBaseimg);

			rtv.setSuccess(true);
			rtv.setData("");
			rtv.setMsg("店铺商品信息保存成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * 店铺商品更新
	 * 
	 * @param prdBase
	 */
	@Override
	@Transactional
	public void UpdateShopPrd(PrdBase prdBase, ReturnValue rtv) {

		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (prdBase.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			// 保存商品信息
			prdBase.getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopPrd(prdBase);

			// 保存商品sku信息
			// 查询出商品skuid对应的信息
			PrdSku itemprdSku = new PrdSku();
			itemprdSku.getSearch().setSearch(" a.skuid = '" + prdBase.getSkuid() + "'");
			List<PrdSku> prdSkus = prdDao.searchPrdSku(itemprdSku);
			if (prdSkus.size() <= 0) {
				rtv.setMsg("没有查到商品SKU信息！");
				return;
			}
			prdSkus.get(0).setSkuname(prdBase.getPrdname());
			prdSkus.get(0).setSkudescription(prdBase.getPrdbrief());
			prdSkus.get(0).getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopPrdSku(prdSkus.get(0));

			// 保存SKU图片信息
			PrdImage itemprdImage = prdBase.getPrdImage();
			itemprdImage.getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopPrdImage(itemprdImage);

			// 保存商品图片信息
			PrdBaseimg prdBaseimg = new PrdBaseimg();
			prdBaseimg.getSearch().setSearch(" a.prdid = '" + prdBase.getPrdid() + "'");
			List<PrdBaseimg> prdBaseimgs = prdDao.searchPrdBaseimg(prdBaseimg);
			if (prdBaseimgs.size() <= 0) {
				rtv.setMsg("没有查到商品图片信息！");
				return;
			}
			prdBaseimgs.get(0).setImagename(prdBase.getPrdname());
			prdBaseimgs.get(0).getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopPrdBaseimg(prdBaseimg);

			rtv.setSuccess(true);
			rtv.setData("");
			rtv.setMsg("店铺商品信息保存成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * 根据店铺号查询店铺订单
	 */
	public List<OrdBase> GetOrdListByShopId(OrdBase ordBase, ReturnValue rtv) {
		try {
			List<OrdBase> ordList = shopDao.GetOrdListByShopId(ordBase);
			rtv.setSuccess(true);
			return ordList;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<OrdBase>();
		}
	}

	/**
	 * 根据店铺号修改对应店铺名
	 */
	public void UpdateShopName(String shopid, String shopname, ReturnValue rtv) {
		MarShopdetail marShopDetail = new MarShopdetail();
		marShopDetail.getSearch().setSearch(" a.shopid = '" + shopid + "' ");
		try {
			MarShopdetail itemshop = new MarShopdetail();
			itemshop.getSearch().setSearch(" a.shopname = '" + shopname + "' ");
			// 判断店铺名是否已经存在
			List<MarShopdetail> shopList = shopDao.GetShopBase(itemshop);
			if (shopList.size() >= 1) {
				rtv.setMsg("该店铺名已存在");
				return;
			}

			List<MarShopdetail> shop = shopDao.GetShopBase(marShopDetail);
			marShopDetail = shop.get(0);
			marShopDetail.setShopname(shopname);
			marShopDetail.getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopBase(marShopDetail);

			rtv.setSuccess(true);
			rtv.setMsg("店铺名修改成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * 根据店铺号修改对应店铺描述
	 */
	public void UpdateShopDesc(String shopid, String shopdesc, ReturnValue rtv) {
		MarShopdetail marShopDetail = new MarShopdetail();
		marShopDetail.getSearch().setSearch(" a.shopid = '" + shopid + "' ");
		try {
			List<MarShopdetail> shop = shopDao.GetShopBase(marShopDetail);
			marShopDetail = shop.get(0);
			marShopDetail.setShopdescription(shopdesc);
			marShopDetail.getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopBase(marShopDetail);

			rtv.setSuccess(true);
			rtv.setMsg("店铺描述修改成功");

		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	public void UpdateReturnAddress(String shopid, String addressid, ReturnValue rtv) {
		MarShopdetail marShopDetail = new MarShopdetail();
		marShopDetail.getSearch().setSearch(" a.shopid = '" + shopid + "' ");
		try {
			List<MarShopdetail> shop = shopDao.GetShopBase(marShopDetail);
			marShopDetail = shop.get(0);
			marShopDetail.setAddressid(addressid);
			marShopDetail.getDeal().setAction(DataAction.Modify.getAction());
			shopDao.SaveShopBase(marShopDetail);

			rtv.setSuccess(true);
			rtv.setMsg("店铺退货地址修改成功");

		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

}