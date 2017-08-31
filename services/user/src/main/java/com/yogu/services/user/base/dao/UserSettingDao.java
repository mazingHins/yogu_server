package com.yogu.services.user.base.dao;

import com.yogu.services.user.base.entry.UserSettingPO;

/**
 * yg_user_setting 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface UserSettingDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 * 
	 * @return 更新的行数
	 */
	public int save(UserSettingPO po);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(UserSettingPO po);

	/**
	 * 根据主键读取记录
	 */
	public UserSettingPO getById(long uid);

	/**
	 * 更新是否接收ios推送
	 * 
	 * @param uid 用户id
	 * @param isPush 是否接收推送 1：是 2：否
	 * @return
	 * @author sky
	 */
	public int updateIosPush(long uid, short isPush);

}
