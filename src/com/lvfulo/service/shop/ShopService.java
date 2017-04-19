package com.lvfulo.service.shop;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.mar.MarApl;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.prd.PrdBase;

@Service
public interface ShopService {
	
	/**
	 * 根据用户编号查询用户店铺入驻情况
	 * @param custid
	 * @return
	 */
    public List<MarApl> GetShopApl(String custid, ReturnValue rtv);
	
	/**
	 * 根据店铺编号查询店铺基础信息
	 * @param shopid
	 * @return
	 */
    public List<MarShopdetail> GetShopBase(String shopid, ReturnValue rtv);
    
    /**
	 * 店铺注册
	 * @param marApl
	 */
	public void SaveShopRegister(MarApl  marApl, ReturnValue rtv);
	
	/**
	 * 店铺基础信息维护
	 * @param marShopdetail
	 */
	public void SaveShopBase(MarShopdetail  marShopdetail, ReturnValue rtv);
	
	/**
	 * 根据店铺编号查询店铺内商品
	 * @param shopId
	 * @return
	 */
    public List<PrdBase> GetPrdListByShopId(PrdBase prdBase, ReturnValue rtv);
    
    /**
     * 店铺商品发布
     * @param prdBase
     */
    public void SaveShopPrd(PrdBase prdBase, ReturnValue rtv);
    
    /**
     * 店铺商品更新
     * @param prdBase
     */
    public void UpdateShopPrd(PrdBase prdBase, ReturnValue rtv);
    
    /**
     * 根据店铺编号查询店铺订单
     * @param shopId
     * @param rtv
     * @return
     */
    public List<OrdBase> GetOrdListByShopId(OrdBase ordBase, ReturnValue rtv);
    
    /**
     * 根据店铺号修改对应店铺名
     * @param shopid
     * @param shopname
     * @param rtv
     */
    public void UpdateShopName(String shopid, String shopname, ReturnValue rtv);
    
    /**
     * 根据店铺号修改对应店铺描述
     * @param shopid
     * @param shopdesc
     * @param rtv
     */
    public void UpdateShopDesc(String shopid, String shopdesc, ReturnValue rtv);
    
    /**
     * 根据店铺号修改对应店铺退货地址
     * @param shopid
     * @param shopdesc
     * @param rtv
     */
    public void UpdateReturnAddress(String shopid, String addressid, ReturnValue rtv);
	
}