package com.lvfulo.service.custom.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpersist.entity.ErrorMsg;
import com.gpersist.entity.ReturnValue;
import com.gpersist.enums.DataAction;
import com.lflweb.entity.ctm.CtmAddress;
import com.lvfulo.dao.account.AccountDao;
import com.lvfulo.dao.custom.CustomDao;
import com.lvfulo.service.custom.UserService;
import com.lvfulo.utils.MyTools;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private CustomDao customDao;

	@Autowired
	private AccountDao accountDao;

	/**
	 * @author：rcp
	 * @接口功能：用户地址列表查询
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<CtmAddress> SearchAddress(String custid, ReturnValue rtv) {
		try {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch(" a.custid = '" + custid + "'");
			List<CtmAddress> addresses = customDao.searchaddress(itemAddress);

			rtv.setSuccess(true);
			return addresses;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<CtmAddress>();
		}
	}
	
	/**
	 * @author：rcp
	 * @接口功能：用户地址列表查询
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<CtmAddress> SearchAddress2(CtmAddress item, ReturnValue rtv) {
		try {
			List<CtmAddress> addresses = customDao.searchaddress(item);
			rtv.setSuccess(true);
			return addresses;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<CtmAddress>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：地址明细查询接口
	 * @存储过程：P_Search_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public List<CtmAddress> Getaddress(String addressid, ReturnValue rtv) {
		try {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch(" a.addressid = '" + addressid + "'");
			List<CtmAddress> itemdatabase = customDao.searchaddress(itemAddress);

			rtv.setSuccess(true);
			return itemdatabase;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<CtmAddress>();
		}
	}

	/**
	 * @author：rcp
	 * @接口功能：地址新增接口
	 * @存储过程：P_Save_Address
	 * @时间：2016/11/02
	 */
	@Override
	@Transactional
	public void SaveAddress(CtmAddress item, ReturnValue rtv) {
		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (item.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			item.setAddressid(MyTools.CreateID("CU"));
			item.setAddstatus("01");// 地址状态为有效
			item.getDeal().setAction(DataAction.Create.getAction());
			customDao.saveaddress(item);

			if (item.getIsdefault()) {
				CtmAddress itemAddress = new CtmAddress();
				itemAddress.setAddressid(item.getAddressid());
				itemAddress.setCustid(item.getCustid());
				itemAddress.getDeal().setAction(7);
				customDao.saveaddress(itemAddress);
			}

			rtv.setSuccess(true);
			rtv.setData(item.getAddressid());
			rtv.setMsg("地址保存成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午2:27:30
	 * @描述: 地址修改接口---1.原地址状态改为无效2.新增新的地址信息
	 * @param item
	 * @param rtv
	 */
	@Override
	@Transactional
	public void UpdateAddress(CtmAddress item, ReturnValue rtv) {
		try {
			ErrorMsg errmsg = new ErrorMsg();

			if (item.OnBeforeSave(errmsg)) {
				rtv.setMsg(errmsg.getErrmsg());
				return;
			}

			String oldaddressidString = item.getAddressid();
			item.setAddressid(MyTools.CreateID("CU"));
			item.setAddstatus("01");// 表示地址有效
			item.getDeal().setAction(DataAction.Create.getAction());
			customDao.saveaddress(item);// 保存新的地址信息

			if (item.getIsdefault()) {
				CtmAddress itemAddress = new CtmAddress();
				itemAddress.setAddressid(item.getAddressid());
				itemAddress.setCustid(item.getCustid());
				itemAddress.getDeal().setAction(7);
				customDao.saveaddress(itemAddress);
			}

			CtmAddress modifyAddress = new CtmAddress();
			modifyAddress.getSearch().setSearch(" a.addressid = '" + oldaddressidString + "'");
			List<CtmAddress> addresses = customDao.searchaddress(modifyAddress);
			addresses.get(0).setAddstatus("02");// 表示地址无效
			addresses.get(0).getDeal().setAction(DataAction.Modify.getAction());
			customDao.saveaddress(addresses.get(0));

			rtv.setSuccess(true);
			rtv.setData(item.getAddressid());
			rtv.setMsg("地址修改成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}
	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午2:29:47
	 * @描述: 地址删除接口---1.将地址状态改为无效
	 * @param addressid
	 * @param rtv
	 */
	@Override
	@Transactional
	public void DeleteAddress(String addressid, ReturnValue rtv) {
		try {
			CtmAddress itemAddress = new CtmAddress();
			itemAddress.getSearch().setSearch(" a.addressid = '" + addressid + "'");
			List<CtmAddress> itemdatabase = customDao.searchaddress(itemAddress);
			itemdatabase.get(0).setAddstatus("02");// 地址状态修改为无效
			itemdatabase.get(0).getDeal().setAction(DataAction.Modify.getAction());
			customDao.saveaddress(itemdatabase.get(0));

			rtv.setSuccess(true);
			rtv.setMsg("地址删除成功");
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			throw new RuntimeException();
		}

	}

	/**
	 * @author rcp
	 * @version 创建时间:2016年12月9日 下午2:29:47
	 * @描述: 获取地址状态
	 * @param addressid
	 * @param rtv
	 */
	@Override
	@Transactional
	public List<CtmAddress> GetAddressStatue(String addressid, ReturnValue rtv) {
		try {
			CtmAddress item = new CtmAddress();
			item.getSearch().setSearch(" a.addressid = '" + addressid + "'");
			List<CtmAddress> items = customDao.GetAddressStatue(item);

			rtv.setSuccess(true);
			return items;
		} catch (Exception e) {
			rtv.setMsg(e.getMessage());
			return new ArrayList<CtmAddress>();
		}
	}

	


}
