package com.yogu.services.user.resource.base;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.Validator;
import com.yogu.commons.utils.encrypt.DES3;
import com.yogu.core.enums.user.UserType;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.StoreErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.dto.UserSetting;
import com.yogu.services.user.base.constants.UserConstants;
import com.yogu.services.user.base.dto.UserInvite;
import com.yogu.services.user.base.service.UserInviteService;
import com.yogu.services.user.base.service.UserProfileService;
import com.yogu.services.user.base.service.UserSettingService;
import com.yogu.services.user.resource.param.UserUpdateParam;
import com.yogu.services.user.resource.vo.UserProfileVO;

/**
 * 个人相关
 * 
 * @author Hins
 * @version createTime：2015年7月15日 上午11:05:38
 */
@Named
@Path("a")
@Singleton
@Produces("application/json;charset=UTF-8")
public class UserResource {

	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

	@Inject
	private UserProfileService userProfileService;

	@Inject
	private UserSettingService userSettingService;
	
	@Inject
	private UserInviteService userInviteService;

	/**
	 * 当用户登录后，获取用户信息
	 * 
	 * @return 返回用户信息
	 */
	@GET
	@Path("v1/user/profile")
	public RestResult<UserProfileVO> get() {
		long uid = SecurityContext.getUid();

		logger.info("user#get |get user information  | uid: {}", uid);

		UserProfile user = userProfileService.getById(uid);
		if (user == null) {
			logger.error("user profile is null|uid=" + uid);
			return new RestResult<UserProfileVO>(ResultCode.FAILURE, UserMessages.USER_GET_FAILURE());
		}
		
		// 封装返回信息
		UserProfileVO result = new UserProfileVO();
		result.setNickname(user.getNickname());
		result.setProfilePic(user.getProfilePic());
		result.setUserType(user.getUserType());
		result.setPassport(user.getPassport());// #TODO 加密解密算法暂时不可用
		if (user.getUserType() == UserType.SALE) {
			UserInvite invite = userInviteService.getById(uid);
			result.setInviteCode(null == invite ? null : invite.getInviteCode());
		}
		
		return new RestResult<UserProfileVO>(result);
	}

	/**
	 * 修改用户昵称
	 */
	@POST
	@Path("v1/user/profile/updateNickname.do")
	public RestResult<Object> updateNickname(@FormParam("nickname") String nickname) {
		long uid = SecurityContext.getUid();
		userProfileService.updateNickname(uid, nickname);// 内部进行有效性校验
		logger.info("user#profile#updateNickname | 用户更新昵称 | uid: {}, nickname: {}", uid, nickname);
		return new RestResult<Object>(null);
	}

	/**
	 * 设置 是否接收推送消息接口
	 * 
	 * @param isPush 是否接收推送 1：是 2：否
	 * @return 成功返回1,失败返回错误代码 ResultCode.FAILURE
	 * @author sky 2015-11-13
	 */
	@POST
	@Path("v1/user/setting/setpush.do")
	public RestResult<Integer> setPush(@FormParam("isPush") short isPush) {

		long uid = SecurityContext.getUid();

		logger.info("userResource#setPush | before set | uid: {},  isPush: {}", uid, isPush);

		if (isPush != UserConstants.ALLOW_PUSH && isPush != UserConstants.NOT_ALLOW_PUSH)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_SET_PUSH_PARAM_ERROR());

		boolean success = userSettingService.setPush(uid, isPush);

		logger.info("userResource#setPush | after set | uid: {}, isPush: {}, result: {}", uid, isPush, success);
		if (success)
			return new RestResult<Integer>(1);
		else
			throw new ServiceException(ResultCode.FAILURE, UserMessages.USER_SET_PUSH_FAILURE());

	}

	/**
	 * 3des加密字符串
	 * 
	 * @param str 待加密文本
	 * @return
	 */
	static String encrypt(String str) {
		String appSecret = ConfigRemoteService.getConfig(ConfigGroupConstants.APP_KEY, SecurityContext.getBaseParams().getAppKey());
		try {
			if (str != null)
				return DES3.encrypt(str, appSecret);
			return null;
		} catch (Exception e) {
			logger.error("解密字符串出错: " + str);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_LOGIN_NEED_PW_USER_NOT_EXIST());
		}
	}
	

}
