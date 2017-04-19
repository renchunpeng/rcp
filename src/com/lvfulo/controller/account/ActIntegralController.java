package com.lvfulo.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.act.ActIntegral;
import com.lflweb.entity.act.ActIntegralDetail;
import com.lflweb.entity.act.ActIntegralMix;
import com.lflweb.entity.act.ActIntegralMonth;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.service.account.AccountService;

@Controller
@RequestMapping("/act")
public class ActIntegralController {

	// @Autowired
	// AccountDao accountDao;

	@Autowired
	private AccountService accountService;

	private ReturnValue rtv;

	public ReturnValue getRtv() {
		if (rtv == null)
			rtv = new ReturnValue();
		return rtv;
	}

	public void setRtv(ReturnValue rtv) {
		this.rtv = rtv;
	}

	/**
	 * 查询用户积分
	 * 
	 * @param custid
	 * @return
	 */
	@RequestMapping(value = "/integral/{custid}")
	@ResponseBody
	public ReturnValue SearchIntegral(@PathVariable("custid") String custid) {
		this.getRtv().OnInit();
		ActIntegralMix mix = new ActIntegralMix();
		// 查询用户积分账户表
		ActIntegral itemmain = new ActIntegral();
		itemmain.getSearch().setSearch(" a.custid = '" + custid + "'");
		List<ActIntegral> listmaIntegrals = accountService.SearchActIntegral(itemmain,this.getRtv());
		String integralString = listmaIntegrals.get(0).getIntegralid();// 取出积分账户编号
		mix.setActIntegral(listmaIntegrals.get(0));

		// 查询积分月度结算表,可用积分为0的剔除
		ActIntegralMonth itemMonth = new ActIntegralMonth();
		itemMonth.getSearch().setSearch(" a.integralid = '" + integralString + "'");
		List<ActIntegralMonth> listMonths = accountService.SearchActIntegralMonth(itemMonth,this.getRtv());
		for (int i = listMonths.size() - 1; i >= 0; i--) {
			if (listMonths.get(i).getIntegralbalance() == 0) {
				listMonths.remove(i);
			}
		}

		// 积分账户明细查询
		ActIntegralDetail itemActIntegralDetail = new ActIntegralDetail();
		itemActIntegralDetail.getSearch().setSearch(" a.integralid = '" + integralString + "'");
		List<ActIntegralDetail> listdetaIntegralDetails = accountService.SearchActIntegralDetail(itemActIntegralDetail,this.getRtv());
		mix.setActIntegralDetails(listdetaIntegralDetails);

		this.getRtv().setSuccess(true);
		this.getRtv().setData(mix);

		return this.getRtv();
	}
}