package com.yogu.services.user.business.service;

import java.util.List;

import com.yogu.remote.user.dto.UserAddress;


/**
 * 用户收货地址 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-15
 */
public interface UserAddressService {

	/**
	 * 根据主键读取记录
	 */
	public UserAddress getById(long addressId);

	/**
	 * 若地址id为0则新增地址，若非0则修改地址
	 * 
	 * @param dto - 要操作的数据
	 * @return 地址id
	 */
	public long saveOrUpdate(UserAddress dto);

	/**
	 * 查询我的全部收货地址记录
	 * 
	 * @param uid
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<UserAddress> listMyAddress(long uid);

	/**
	 * 根据主键删除 用户的收货地址
	 * 
	 * @param uid 收货地址所属用户id
	 * @param addressId 收货地址主键id
	 * @return 成功时返回1;失败时返回0
	 * @author sky 2015-12-08
	 */
	public int delete(long uid, long addressId);

}
