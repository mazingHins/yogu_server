package com.yogu.services.user.base.service;

import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.dto.UserSetting;
import com.yogu.services.user.base.dto.UserInvite;

/**
 * 用户信息表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface UserInviteService {
	
	/**
	 * 根据主键读取记录
	 */
	public UserInvite getById(long uid);

}
