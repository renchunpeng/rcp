package com.lvfulo.service.ord;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.act.ActInvoiceTrans;
import com.lflweb.entity.act.ActInvoiceTransMain;
import com.lflweb.entity.act.ActInvoiceTransdetail;
import com.lflweb.entity.act.ActUserCoupon;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.ord.OrdBaseMain;
import com.lflweb.entity.ord.OrdDeliveryComment;
import com.lflweb.entity.ord.OrdPrdComment;
import com.lflweb.entity.ord.OrdReturn;
import com.lflweb.entity.ord.OrdShop;
import com.lflweb.entity.ord.OrdSubdetail;
import com.lflweb.entity.prd.PrdAllSelect;
import com.lflweb.entity.prd.PrdTypeSelect;

@Service
public interface OrdService {

	// 购物车查询
	public List<OrdShop> searchOrdShop(OrdShop item, ReturnValue rtv);

	// 查询订单基础表
	public List<OrdBase> searchOrdBase(OrdBase item, ReturnValue rtv);

	// 查询明细查询
	public List<OrdSubdetail> searchOrdSubdetails(OrdSubdetail item, ReturnValue rtv);

	// 查询购物车列表
	public List<OrdShop> GetListOrdShop(OrdShop ordShop, ReturnValue rtv);

	// 查询用户购物车分页
	public List<OrdShop> SearchOrdShop(OrdShop ordShop, ReturnValue rtv);

	// 订单保存
	public void SaveOrdBaseMid(OrdBaseMain itemOrdBaseMain, OrdBase itemOrdBase, List<OrdSubdetail> details, ActInvoiceTransMain invoiceTransMain, ActInvoiceTrans invoiceTrans,
			List<ActInvoiceTransdetail> invoiceTransdetails, List<ActUserCoupon> coupons, boolean flag, Boolean isyouhui, ReturnValue rtv);

	// 根据ordShop保存购物车
	public void SaveOrdShop(OrdShop ordShop, ReturnValue rtv);

	// 根据ordShopList保存购物车列表
	public void SaveOrdShopList(List<OrdShop> ordShopList, ReturnValue rtv);

	// 确认收货
	public void UpdateOrdBase(String orderid, String custid, ReturnValue rtv);

	// 退单
	public void ReturnOrdBase(OrdReturn item, ReturnValue rtv);

	// 微信回掉拆单
	public void RetuenChai(String paytype, String orderid, String appid, String paysysid, ReturnValue rtv);

	// 订单评价
	public void SaveComment(OrdDeliveryComment delivery, List<OrdPrdComment> prds, ReturnValue rtv);
	
	// 整站精选
	public List<PrdAllSelect> searchPrdAllSelect(PrdAllSelect item, ReturnValue rtv);
	
	// 精挑好货
	public List<PrdTypeSelect> searchPrdTypeSelect(PrdTypeSelect item, ReturnValue rtv);
	
	// 商品评价查询
	public List<OrdPrdComment> SearchPrdCommon(OrdPrdComment item, ReturnValue rtv);

}
