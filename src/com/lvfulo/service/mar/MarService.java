package com.lvfulo.service.mar;

import java.util.List;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.mar.MarAdv;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.prd.PrdBigType;

/**
 * @author rcp
 * @date 创建时间：2017年3月20日 下午1:48:56
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface MarService {
	// 首页轮播图
	public List<MarAdv> SearchMarAdv(MarAdv item, ReturnValue rtv);
	
	// 首页大类
	public List<PrdBigType> SearchPrdBigType(PrdBigType item, ReturnValue rtv);
	
	// 店铺基础信息表查询
	public List<MarShopdetail> SearchMarShopdetail(MarShopdetail item, ReturnValue rtv);
	
	// 店铺基础信息表保存
	public void SaveMarShopdetail(MarShopdetail item, ReturnValue rtv);
}
