package com.lvfulo.dao.mar;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.mar.MarAdv;
import com.lflweb.entity.mar.MarMem;
import com.lflweb.entity.mar.MarShopdetail;
import com.lflweb.entity.prd.PrdBigType;

/** 
 * @author  rcp 
 * @date 创建时间：2017年3月20日 下午1:44:25 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
@Repository
public interface MarDao {
	//首页轮播图查询
	public List<MarAdv> SearchMarAdv(MarAdv item);
	
	//首页大类查询
	public List<PrdBigType> SearchPrdBigType(PrdBigType item);
	
	//保存会员卡基础信息
	public void SaveMarMem(MarMem item);
	
	//店铺基础信息表
	public List<MarShopdetail> SearchMarShopdetail(MarShopdetail item);
	
	//保存店铺基础信息表
	public void SaveMarShopdetail(MarShopdetail item);
}
