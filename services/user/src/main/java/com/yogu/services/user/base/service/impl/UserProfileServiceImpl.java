package com.yogu.services.user.base.service.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.WordCountUtils;
import com.yogu.core.remote.config.AppLanguage;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.config.fs.service.FileStoreService;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.dto.UserSetting;
import com.yogu.services.user.base.dao.UserInviteDao;
import com.yogu.services.user.base.dao.UserNicknameDao;
import com.yogu.services.user.base.dao.UserProfileDao;
import com.yogu.services.user.base.dao.UserSettingDao;
import com.yogu.services.user.base.entry.UserInvitePO;
import com.yogu.services.user.base.entry.UserNicknamePO;
import com.yogu.services.user.base.entry.UserProfilePO;
import com.yogu.services.user.base.entry.UserSettingPO;
import com.yogu.services.user.base.service.UserProfileService;

/**
 * UserProfileService 的实现类
 * 
 * @author JFan 2015-07-13
 */
@Named
public class UserProfileServiceImpl implements UserProfileService {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

	@Inject
	private UserProfileDao dao;

	@Inject
	private UserSettingDao userSettingDao;

	@Inject
	private UserNicknameDao userNicknameDao;
	
	@Inject
	private UserInviteDao userInviteDao;

	@Inject
	private FileStoreService fileStoreService;

	@Override
	public UserProfile getById(long uid) {
		UserProfilePO po = dao.getById(uid);
		if (null == po)
			return null;
		return po2dto(po);
	}

	@Override
	public UserProfile getByImid(long imid) {
		UserNicknamePO un = userNicknameDao.getByImid(imid);
		if (null != un)
			return getById(un.getUid());
		return null;
	}

	// ####
	// ## private func
	// ####

	public UserProfile po2dto(UserProfilePO po) {
		return VOUtil.from(po, UserProfile.class);
	}

	public UserProfilePO dto2po(UserProfile dto) {
		return VOUtil.from(dto, UserProfilePO.class);
	}

	@Override
	public String saveProfilePic(long uid, String originalFilename, byte[] content) {
		ParameterUtil.assertNotBlank(originalFilename, UserMessages.USER_SAVE_PROFILE_FILENAME_CAN_NOT_BE_EMPTY());
		ParameterUtil.assertTrue(uid > 0, UserMessages.USER_SAVE_PROFILE_UID_INCORRECT());
		if (content == null || content.length == 0) {
			throw new ServiceException(UserErrorCode.FILE_DATA_ERROR, UserMessages.USER_SAVE_PROFILE_CONTENT_INVALID());
		}
		// 保存用户头像
		String filename = fileStoreService.saveProfileImage(uid, originalFilename, content);
		int rows = dao.updateProfilePic(uid, filename);
		if (rows <= 0) {
			logger.error("account#userservice#saveProfilePic | save failed | uid: {}, originalFilename: {}", uid, originalFilename);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, UserMessages.USER_SAVE_PROFILE_ERROR());
		}
		logger.info("account#userservice#saveProfilePic | save successed | uid: {}, originalFilename: {}, filename: {}", uid, originalFilename, filename);
		return filename;
	}

	public void updateUserInformation(UserProfile userProfile, UserSetting setting, String cityCode) {
		if (userProfile != null) {
			dao.updateNickName(userProfile.getUid(), userProfile.getNickname());
		}
		if (setting != null) {
			UserSettingPO settingPO = VOUtil.from(setting, UserSettingPO.class);
			int updSettingRow = userSettingDao.update(settingPO);

			if (updSettingRow == 0) {// 若修改成功返回条数为0，则查询数据库是否存在该用户的设置记录，若不存在，则新增
				UserSettingPO po = userSettingDao.getById(settingPO.getUid());
				if (po == null) {
					settingPO.setCreateTime(new Date());
					userSettingDao.save(settingPO);
				}
			}
		}
	}

	@Override
	public void updateNickname(long uid, String nickname) {
		String name = StringUtils.trimToNull(nickname);
		if (null == name)
			throw ResultCode.paramExcp(UserMessages.USER_UPDATE_NICKNAME_NICKNAME_CAN_NOT_BE_EMPTY());
		if (30 < WordCountUtils.getWordCount(name))
			throw ResultCode.paramExcp(UserMessages.USER_UPDATE_NICKNAME_NICKNAME_LENGTH_INVALID());

		int row = dao.updateNickName(uid, name);

		if (row <= 0) {
			logger.error("user#service#updateNickname | 数据库更新用户昵称失败 | uid: {}, nickname: '{}'", uid, nickname);
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, UserMessages.USER_UPDATE_NICKNAME_ERROR());
		}
	}

	@Override
	public String getNicknameByUid(long uid) {
		UserNicknamePO user = userNicknameDao.getById(uid);
		if (null == user) {
			return null;
		}

		return user.getNickname();
	}

	@Override
	public String getInviteCodeByUid(long uid) {
		UserInvitePO user = userInviteDao.getByUid(uid);
		if (null == user) {
			return null;
		}

		return user.getInviteCode();
	}


}
