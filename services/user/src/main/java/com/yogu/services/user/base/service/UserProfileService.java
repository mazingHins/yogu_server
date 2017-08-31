package com.yogu.services.user.base.service;

import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.dto.UserSetting;

/**
 * 用户信息表 <br>
 * 的操作接口
 * 
 * @author JFan 2015-07-13
 */
public interface UserProfileService {

	/**
	 * 根据主键读取记录
	 */
	public UserProfile getById(long uid);

	/**
	 * 根据IMID读取记录
	 */
	public UserProfile getByImid(long imid);

	/**
	 * 保存用户的个人头像，如果头像存在，将替换旧的图片。
	 * 
	 * @param uid - 用户ID
	 * @param originalFilename - 原始图片名称
	 * @param content - 图片内容
	 * @return 成功返回图片http地址
	 */
	String saveProfilePic(long uid, String originalFilename, byte[] content);

	/**
	 * 修改用户昵称
	 * 
	 * @param uid 用户ID
	 * @param nickname 新的昵称
	 */
	public void updateNickname(long uid, String nickname);

}
