package com.lvfulo.service.custom;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpersist.entity.ReturnValue;
import com.lflweb.entity.ctm.CtmAddress;

@Service
public interface UserService {
	
	public List<CtmAddress> SearchAddress(String custid, ReturnValue rtv);// 查找用户收货地址
	
	public void SaveAddress(CtmAddress item, ReturnValue rtv);// 保存收货地址

	public List<CtmAddress> Getaddress(String addressid, ReturnValue rtv);// 查询收货地址详情

	public void UpdateAddress(CtmAddress item, ReturnValue rtv);// 更新用户地址

	public void DeleteAddress(String addressid, ReturnValue rtv);// 删除用户地址

	public List<CtmAddress> GetAddressStatue(String addressid, ReturnValue rtv);// 查询地址状态
	
	public List<CtmAddress> SearchAddress2(CtmAddress item, ReturnValue rtv);// 查找用户收货地址

}
