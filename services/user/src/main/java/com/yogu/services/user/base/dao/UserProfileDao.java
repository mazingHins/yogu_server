package com.yogu.services.user.base.dao;

import com.yogu.services.user.base.entry.UserProfilePO;

/**
 * mz_user_profile 表的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface UserProfileDao {

	/**
	 * 保存数据
	 * 
	 * @param po对象
	 * 
	 * @return 更新的行数
	 */
	public int save(UserProfilePO po);

	/**
	 * 修改昵称
	 * 
	 * @param uid - 用户ID
	 * @param nickName - 新的昵称
	 * @return 更新的行数
	 */
	public int updateNickName(long uid, String nickName);

	/**
	 * 根据主键读取记录
	 */
	public UserProfilePO getById(long uid);

	/**
	 * 更新用户的头像信息 ，
	 * 
	 * @param uid - 用户id
	 * @param profilePic - 头像地址
	 * @return 返回更新的行数
	 */
	int updateProfilePic(long uid, String profilePic);

	/**
	 * 更新用户语言标识
	 * 
	 * @param uid 用户uid
	 * @param langCode 语言代码
	 * @param cityCode 城市编码
	 * @return
	 * @author sky 2016-03-18
	 */
	public int updateLanguage(long uid, String langCode, String cityCode);

}
