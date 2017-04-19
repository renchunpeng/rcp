package com.lvfulo.service.shop;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;

@Service
public interface ShopOrderService {
	
	/**
	 * 店铺订单处理-订单发货
	 * @param orderid
	 * @param custid
	 * @param rtv
	 */
    public void UpdateOrdSend(String orderid, String custid, ReturnValue rtv);
    
    /**
     * 店铺订单处理-配送完成
     * @param orderid
     * @param custid
     * @param rtv
     */
    public void UpdateOrdSendOk(String orderid, String custid, ReturnValue rtv);
    
    /**
     * 店铺订单处理-同意取消
     * @param orderid
     * @param custid
     * @param rtv
     */
    public void UpdateOrdCancel(String orderid, String custid, ReturnValue rtv);
	
}