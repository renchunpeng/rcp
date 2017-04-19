package com.lvfulo.service.log.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.log.LogOrderPay;
import com.lvfulo.dao.log.LogDao;
import com.lvfulo.service.log.LogService;
import com.sun.star.uno.RuntimeException;

/**
 * @author rcp
 *
 */
@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Override
	public void SaveLogOrderPay(LogOrderPay item, ReturnValue rtv) {
		try {
			logDao.SaveLogOrderPay(item);
			rtv.setSuccess(true);
			rtv.setMsg("支付日志保存成功！");
		} catch (Exception e) {
			System.err.println(e.getStackTrace());
			throw new RuntimeException();
		}

	}
}
