package com.yogu.services.user.base.service;

import com.yogu.remote.user.dto.UserSetting;

/**
 * 用户配置表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-08-14
 */
public interface UserSettingService {

	/**
	 * 根据主键读取记录
	 */
	public UserSetting getById(long uid);

	/**
	 * 设置客户端用户 是否接收推送
	 * 
	 * @param uid 用户id
	 * @param isPush 是否接收推送 1：是 2：否
	 * @return true,设置成功; false, 设置失败
	 * @author sky 2015-11-13
	 */
	public boolean setPush(long uid, short isPush);
}
