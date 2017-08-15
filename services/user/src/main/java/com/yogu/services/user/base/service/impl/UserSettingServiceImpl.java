package com.yogu.services.user.base.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.user.dto.UserSetting;
import com.yogu.services.user.base.constants.UserConstants;
import com.yogu.services.user.base.dao.UserSettingDao;
import com.yogu.services.user.base.entry.UserSettingPO;
import com.yogu.services.user.base.service.UserSettingService;

/**
 * UserSettingService 的实现类
 * 
 * @author JFan 2015-08-14
 */
@Named
public class UserSettingServiceImpl implements UserSettingService {

	private static final Logger logger = LoggerFactory.getLogger(UserSettingServiceImpl.class);

	@Inject
	private UserSettingDao dao;

	@Override
	public UserSetting getById(long uid) {
		UserSettingPO po = dao.getById(uid);
		if (null == po)
			return null;
		return po2dto(po);
	}

	// ####
	// ## private func
	// ####

	public UserSetting po2dto(UserSettingPO po) {
		return VOUtil.from(po, UserSetting.class);
	}

	public UserSettingPO dto2po(UserSetting dto) {
		return VOUtil.from(dto, UserSettingPO.class);
	}

	@Override
	public boolean setPush(long uid, short isPush) {
		// 客户端 设置是否接收推送

		if (isPush != UserConstants.ALLOW_PUSH && isPush != UserConstants.NOT_ALLOW_PUSH)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_SET_PUSH_PARAM_ERROR());

		try {
			dao.updateIosPush(uid, isPush);
		} catch (Exception e) {
			logger.error("userSettingService#setIosPush | 更新数据库失败 | uid: {}, isPush: {}, errorMsg: {}", uid, isPush, e.getMessage(), e);

			return false;
		}

		return true;

	}

}
