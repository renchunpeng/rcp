package com.lvfulo.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.enums.DataAction;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActIntegralDetail;
import com.lflweb.entity.act.ActIntegralMonth;
import com.lflweb.entity.ord.RuleIntegral;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.ord.OrdDao;
import com.lvfulo.utils.MyTools;
import com.sun.star.uno.RuntimeException;

public class JfClear {
	@Autowired
	private OrdDao ordDao;

	@Autowired
	private AccountDao accountDao;

	@Transactional
	public void ClearIntegral() {
		System.out.println("清除积分！");
		try {
			Calendar cal = Calendar.getInstance();

			int month = cal.get(Calendar.MONTH) + 1;
			int year = cal.get(Calendar.YEAR) - 1;// 删除去年的积分
			String ym = "";
			if (month >= 10) {
				ym = String.valueOf(year) + String.valueOf(month);
			} else {
				ym = String.valueOf(year) + "0" + String.valueOf(month);
			}

			ActIntegralMonth itemactIntegralMonth = new ActIntegralMonth();
			List<ActIntegralMonth> listactActIntegralMonths = accountDao.SearchActIntegralMonth(itemactIntegralMonth);
			for (ActIntegralMonth actIntegralMonth : listactActIntegralMonths) {
				if (actIntegralMonth.getTranym().compareTo(ym) < 0) {
					int oldjf = actIntegralMonth.getIntegralbalance();// 在积分清0之前记录下原有积分
					// 加入在月度结算表中有低于ym的数据，把可用积分直接清0
					actIntegralMonth.setIntegralbalance(0);
					actIntegralMonth.getDeal().setAction(DataAction.Modify.getAction());
					accountDao.SaveActIntegralMonth(actIntegralMonth);

					// 利用积分账户编号将该账户的积分余额扣减
					ActIntegral itemActIntegral = new ActIntegral();
					itemActIntegral.getSearch().setSearch("a.integralid = '" + actIntegralMonth.getIntegralid() + "'");
					List<ActIntegral> listacActIntegrals = accountDao.SearchActIntegral(itemActIntegral);
					listacActIntegrals.get(0).setIntegralbalance(listacActIntegrals.get(0).getIntegralbalance() - oldjf);
					listacActIntegrals.get(0).getDeal().setAction(DataAction.Modify.getAction());
					accountDao.SaveActIntegral(listacActIntegrals.get(0));

					// 积分明细表新增数据
					String trString = MyTools.CreateID("TR");
					ActIntegralDetail itemActIntegralDetail = new ActIntegralDetail();
					itemActIntegralDetail.setTraceid(trString);
					itemActIntegralDetail.setIntegralid(actIntegralMonth.getIntegralid());
					itemActIntegralDetail.setTranintegral(oldjf);
					itemActIntegralDetail.setTrantype("02");// 表示积分减少
					itemActIntegralDetail.setTrandate(new Date());
					itemActIntegralDetail.setTrandesc("积分清0");
					itemActIntegralDetail.getDeal().setAction(DataAction.Create.getAction());
					accountDao.SaveActIntegralDetail(itemActIntegralDetail);

				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();

		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		String ym = "";
		if (month >= 10) {
			ym = String.valueOf(year) + String.valueOf(month);
		} else {
			ym = String.valueOf(year) + "0" + String.valueOf(month);
		}

		System.out.println(ym);

	}
}