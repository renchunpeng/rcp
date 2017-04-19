package com.lvfulo.service.log;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.log.LogOrderPay;

@Service
public interface LogService {

	public void SaveLogOrderPay(LogOrderPay item, ReturnValue rtv);// 订单支付日志表
	
	
}
