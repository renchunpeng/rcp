package com.lvfulo.service.prd;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.prd.PrdBase;
import com.lflweb.entity.prd.PrdBrand;
import com.lflweb.entity.prd.PrdImage;
import com.lflweb.entity.prd.PrdPackage;
import com.lflweb.entity.prd.PrdPackagedetail;
import com.lflweb.entity.prd.PrdPackageimg;
import com.lflweb.entity.prd.PrdType;

@Service
public interface PrdService {

	//分页查找商品基础信息
	public List<PrdBase> searchPrdBase(PrdBase item, ReturnValue rtv);

	//商品类型表查询
	public List<PrdType> searchPrdType(PrdType item, ReturnValue rtv);
	
	//查询商品品牌表
	public List<PrdBrand> searchPrdBrand(PrdBrand item, ReturnValue rtv);
	
	//查询商品套餐表
	public List<PrdPackage> searchPrdPackage(PrdPackage item, ReturnValue rtv);

	//商品sku图片查询
	public List<PrdImage> searchPrdimage(PrdImage item, ReturnValue rtv);
	
	//套餐图片查询
	public List<PrdPackageimg> searchPrdPackageimg(PrdPackageimg item,ReturnValue rtv);
	
	//套餐商品明细
	public List<PrdPackagedetail> SearchPrdPackagedetail(PrdPackagedetail item,ReturnValue rtv);
}
