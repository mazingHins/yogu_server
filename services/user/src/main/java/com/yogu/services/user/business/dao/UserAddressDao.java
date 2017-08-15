package com.yogu.services.user.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.user.business.entry.UserAddressPO;

/**
 * mz_user_address 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
@TheDataDao
public interface UserAddressDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 */
	public void save(UserAddressPO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(long addressId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(UserAddressPO po);

	/**
	 * 根据主键读取记录
	 */
	public UserAddressPO getById(long addressId);

	/**
	 * 根据用户id查询记录
	 * 
	 * @param uid - 用户id
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<UserAddressPO> queryByFilter(long uid);

	/**
	 * 统计默认的收货地址数量
	 * 
	 * @param uid - 用户ID
	 * @param status - 地址类型
	 * @return
	 */
	public int countStatus(@Param("uid") long uid, @Param("status") short status);

	/**
	 * 修改地址默认状态
	 * 
	 * @param uid - 用户ID
	 * @param statusEQ - 修改前的地址类型
	 * @param status - 修改后的地址类型
	 * @return
	 */
	public int updateStatus(@Param("uid") long uid, @Param("statusEQ") short statusEQ, @Param("status") short status);

}
