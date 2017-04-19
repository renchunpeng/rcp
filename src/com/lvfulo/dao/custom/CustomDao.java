package com.lvfulo.dao.custom;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.bas.BasCity;
import com.lflweb.entity.bas.BasCounty;
import com.lflweb.entity.bas.BasProvince;
import com.lflweb.entity.bas.BasTown;
import com.lflweb.entity.ctm.CtmAddress;

@Repository
public interface CustomDao {

	//用户地址列查询+地址明细查询
	public List<CtmAddress> searchaddress(CtmAddress itemAddress);

	//地址新增
	public void saveaddress(CtmAddress ctmAddress);
	
	//获取地址状态
	public List<CtmAddress> GetAddressStatue(CtmAddress itemAddress);
}
