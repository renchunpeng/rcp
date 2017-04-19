package com.lvfulo.dao.log;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lflweb.entity.bas.BasFeeset;
import com.lflweb.entity.log.LogOrderPay;

@Repository
public interface LogDao {

	// 基础配置表查询
	public List<BasFeeset> SearchBasFeeset(BasFeeset item);

	// 支付日志表保存
	public void SaveLogOrderPay(LogOrderPay item);

}
