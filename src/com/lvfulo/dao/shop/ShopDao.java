package com.lvfulo.dao.shop;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.mar.MarApl;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdBaseimg;
import com.lflweb.entity.prd.PrdImage;
import com.lflweb.entity.prd.PrdSku;

@Repository
public interface ShopDao {
	
	/**
	 * 根据用户编号查询店铺入驻情况
	 * @param marApl
	 * @return
	 */
    public List<MarApl> GetShopApl(MarApl marApl);
	
	/**
	 * 查询店铺基础信息
	 * @param marShopdetail
	 * @return
	 */
    public List<MarShopdetail> GetShopBase(MarShopdetail  marShopdetail);
    
    /**
	 * 店铺申请注册
	 * @param marApl
	 */
	public void SaveShopRegister(MarApl  marApl);
	
	/**
	 * 店铺信息维护
	 * @param marShopdetail
	 */
	public void SaveShopBase(MarShopdetail  marShopdetail);
	
	/**
	 * 根据店铺编号查询店铺内商品
	 * @param shopId
	 * @return
	 */
    public List<PrdBase> GetPrdListByShopId(PrdBase prdBase);
    
    /**
     * 店铺商品保存
     * @param prdBase
     */
    public void SaveShopPrd(PrdBase prdBase);
    
    /**
     * 店铺商品sku保存
     * @param prdSku
     */
    public void SaveShopPrdSku(PrdSku prdSku);
    
    /**
     * 店铺SKU图片保存
     * @param prdImage
     */
    public void SaveShopPrdImage(PrdImage prdImage);
    
    /**
     * 店铺商品图片保存
     * @param prdBaseimg
     */
    public void SaveShopPrdBaseimg(PrdBaseimg prdBaseimg);
    
    /**
	 * 根据店铺编号查询店铺订单
	 * @param shopId
	 * @return
	 */
    public List<OrdBase> GetOrdListByShopId(OrdBase ordBase);
	
}