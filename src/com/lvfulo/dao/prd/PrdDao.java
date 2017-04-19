package com.lvfulo.dao.prd;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdBaseimg;
import com.lflweb.entity.prd.PrdBrand;
import com.lflweb.entity.prd.PrdImage;
import com.lflweb.entity.prd.PrdPackage;
import com.lflweb.entity.prd.PrdPackagedetail;
import com.lflweb.entity.prd.PrdPackageimg;
import com.lflweb.entity.prd.PrdSku;
import com.lflweb.entity.prd.PrdType;

@Repository
public interface PrdDao {
	
	//商品类型查询
	public List<PrdType> searchPrdType(PrdType item);

	//商品基础信息表查询，有分页
	public List<PrdBase> searchPrdBase(PrdBase item);
	
	//商品品牌表查询
	public List<PrdBrand> searchPrdBrand(PrdBrand item);
	
	//商品sku
	public List<PrdSku> searchPrdSku(PrdSku item);//仅有dao层
	
	//商品sku图片
	public List<PrdImage> searchPrdImage(PrdImage item);//仅有dao层
	
	
	//商品图片查询
	public List<PrdBaseimg> searchPrdBaseimg(PrdBaseimg item);//仅有dao层
	
	//商品对应的skutype查询
	public List<PrdBase> SearchSkuType(PrdBase item);//仅有dao层
	

	
	//region 套餐
	//商品套餐表查询
	public List<PrdPackage> searchPrdPackage(PrdPackage item);//仅有dao层
	
	//套餐商品明细
	public List<PrdPackagedetail> SearchPrdPackagedetail(PrdPackagedetail item);//仅有dao层
	
	//套餐图片查询
	public List<PrdPackageimg> searchPrdPackageimg(PrdPackageimg item);//仅有dao层
	//endregion

}
