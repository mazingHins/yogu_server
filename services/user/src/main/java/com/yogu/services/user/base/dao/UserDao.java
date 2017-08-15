package com.yogu.services.user.base.dao;

import java.util.List;

import com.yogu.services.user.base.entry.UserPO;

/**
 * mz_user 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface UserDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 * 
	 * @return 更新的行数
	 */
	public int save(UserPO po);

	/**
	 * 根据通行证读取记录
	 * 
	 * @param countryCode - 国家代码，比如+86表示中国
	 * @param passport - 手机号码或email
	 * @return 如果有记录返回UserPO，否则返回null
	 */
	UserPO getByPassport(String countryCode, String mobile);


	/**
	 * 更新用户密码
	 * 
	 * @param uid 用户id
	 * @param countryCode 国家代码
	 * @param mobile 手机号码
	 * @param newPassword 新的密码
	 * @return
	 * @author sky
	 * @date 2015-10-02 22:33:44
	 */
	int udpatePasswordByUid(long uid, String countryCode, String mobile, String newPassword);

	/**
	 * 通过表名获取所有user
	 * 
	 * @author sky
	 * @date 2015-08-31 10:48:20
	 * @param tableName
	 * @return
	 */
	public List<UserPO> getAll(String tableName);

	/**
	 * 修改帐号状态
	 * 
	 * @param uid 用户ID
	 * @param countryCode 国家代码
	 * @param passport 帐号
	 * @param oldStatus 旧的状态
	 * @param newStatus 要设置的新的状态
	 * @return 返回修改的行数，成功修改返回1
	 */
	int updateUserStatus(long uid, String countryCode, String passport, short oldStatus, short newStatus);
	
	/**
	 * 根据用户ID查找账户状态
	 * 
	 * @param uid 用户ID
	 * @param countryCode 国家代码
	 * @param passport 帐号
	 * @return
	 * 
	 * @author felix
	 * @date 2015-10-22
	 */
	short getUserStatusById(long uid, String countryCode, String passport);

	/**
	 * 分表操作，临时使用的，其他地方请勿调用
	 * @author ben
	 * @date 2015年11月16日 上午10:36:31 
	 * @return
	 */
	List<UserPO> listAll();
}
