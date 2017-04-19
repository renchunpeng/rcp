package com.lvfulo.dao.ord;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.act.ActInvoiceTrans;
import com.lflweb.entity.act.ActInvoiceTransMain;
import com.lflweb.entity.act.ActInvoiceTransdetail;
import com.lflweb.entity.ord.OrdBase;
import com.lflweb.entity.ord.OrdBaseMain;
import com.lflweb.entity.ord.OrdDeliveryComment;
import com.lflweb.entity.ord.OrdDispatch;
import com.lflweb.entity.ord.OrdHistoryShop;
import com.lflweb.entity.ord.OrdPrdComment;
import com.lflweb.entity.ord.OrdReturn;
import com.lflweb.entity.ord.OrdReturnTrack;
import com.lflweb.entity.ord.OrdShop;
import com.lflweb.entity.ord.OrdSubdetail;
import com.lflweb.entity.ord.OrdTrack;
import com.lflweb.entity.ord.RuleIntegral;
import com.lflweb.entity.prd.PrdAllSelect;
import com.lflweb.entity.prd.PrdTypeSelect;

@Repository
public interface OrdDao {

	// region 原始订单表，发票表
	// 查询
	public List<OrdBaseMain> searchOrdBaseMain(OrdBaseMain item);

	// 保存
	public void saveOrdBaseMain(OrdBaseMain item);// 仅有dao层

	// 保存
	public void saveActInvoiceTransMain(ActInvoiceTransMain item);// 仅有dao层
	// endregion

	// 购物车列表查询
	public List<OrdShop> GetListOrdShop(OrdShop item);// 仅有dao层

	// 查询订单基础表

	public List<OrdBase> searchOrdBase(OrdBase item);

	// 查询订单基础表

	public List<OrdBase> searchAllOrdBase(OrdBase item);

	// 保存
	public void saveOrdBase(OrdBase item);// 仅有dao层

	// 查询
	public List<ActInvoiceTrans> searchActInvoiceTrans(ActInvoiceTrans item);

	// 保存
	public void saveActInvoiceTrans(ActInvoiceTrans item);// 仅有dao层

	// 订单状态跟踪表
	public void saveOrdTrack(OrdTrack item);// 仅有dao层

	// 保存订单配送表
	public void saveOrdDispatch(OrdDispatch item);// 仅有dao层

	// 查询积分规则表
	public List<RuleIntegral> searchruleintegral(RuleIntegral item);// 仅有dao层

	// endregion

	// region 订单，发票明细
	// 查询
	public List<OrdSubdetail> searchOrdSubdetails(OrdSubdetail item);

	// 订单
	public void saveOrdSubdetails(OrdSubdetail item);// 仅有dao层

	// 保存
	public void saveActInvoiceTransDetail(ActInvoiceTransdetail item);// 仅有dao层
	// 查询

	public List<ActInvoiceTransdetail> searchActInvoiceTransdetail(ActInvoiceTransdetail item);

	// endregion

	// region 购物车
	// 购物车查询
	public List<OrdShop> searchOrdShop(OrdShop item);

	// 购物车保存
	public void saveOrdShop(OrdShop item);// 仅有dao层

	public void saveOrdHistoryShop(OrdHistoryShop item);// 保存到历史购物车
	// endregion

	// region 退单表
	// 订单退单表
	public void saveOrdReturn(OrdReturn item);// 仅有dao层
	
	public List<OrdReturn> SearchOrdReturn(OrdReturn item);

	// 退单状态跟踪表
	public void saveReturnTrack(OrdReturnTrack item);// 仅有dao层
	// endregion

	// region 评价表
	// 订单 配送评价表
	public void saveOrdDeliveryComment(OrdDeliveryComment item);// 仅有dao层

	// 商品评价表
	public void saveOrdPrdComment(OrdPrdComment item);// 仅有dao层
	// endregion

	// region 首页信息配置
	// 整站精选
	public List<PrdAllSelect> searchPrdAllSelect(PrdAllSelect item);
	
	// 精挑好货
	public List<PrdTypeSelect> searchPrdTypeSelect(PrdTypeSelect item);
	// endregion
	
	// 商品评价查询
	public List<OrdPrdComment> SearchPrdCommon(OrdPrdComment item);
}
