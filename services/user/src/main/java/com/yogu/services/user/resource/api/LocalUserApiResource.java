package com.yogu.services.user.resource.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.validation.constraints.NotBlank;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.language.UserMessages;
import com.yogu.remote.user.dto.User;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.user.resource.vo.UserProfileInsideVO;

/**
 * 用户相关的 本地API，提供给集群内部使用的
 * 
 * @author Hins createTime：2015年7月17日 上午11:14:37
 */
@Named
@Path("api")
@Singleton
@Produces("application/json;charset=UTF-8")
public class LocalUserApiResource {

	private static final Logger logger = LoggerFactory.getLogger(LocalUserApiResource.class);


	@Inject
	private UserService userService;

	/**
	 * @Description: 根据userId获取UserProfile信息
	 * @author Hins
	 * @date 2015年8月17日 上午11:58:13
	 * 
	 * @param uid
	 * @return
	 */
	@GET
	@Path("v1/user/id/{uid:[0-9]+}")
	public RestResult<UserProfile> getUserProfileUid(@PathParam("uid") long uid) {
		logger.info("api#user#getUserProfileUid |get user information  | uid: {}", uid);

		UserProfile user = userService.getUserProfile(uid);
		if (user == null) {
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "not found.");
		}
		return new RestResult<>(user);
	}

	/**
	 * 根据用户的passport获取用户的ID和昵称
	 * 
	 * @param countryCode 国家代码，比如086
	 * @param passport 帐号，比如手机号码
	 * @return ID和昵称
	 */
	@GET
	@Path("v1/user/get")
	public RestResult<UserProfileInsideVO> getUid(@QueryParam("countryCode") @NotBlank(message = "国家代码不能为空") String countryCode,
			@QueryParam("passport") @NotBlank(message = "帐号不能为空") String passport) {

		logger.info("api#user#getUid | 读取UID和昵称 | countryCode: {}, passport: {}", countryCode, passport);
		ParameterUtil.assertNotBlank(countryCode, "countryCode is null");
		ParameterUtil.assertNotBlank(passport, "passport is null");

		User user = userService.getUser(countryCode, passport);
		if (user == null) {
			logger.error("api#user#getUid | user not exist | passport: {}", LogUtil.hidePassport(passport));
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, UserMessages.USER_GET_USER_PROFILE_ERROR());
		}
		UserProfile profile = userService.getUserProfile(user.getUid());
		if (profile == null) {
			logger.error("api#user#getUid | userProfile not exist | passport: {}", LogUtil.hidePassport(passport));
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, UserMessages.USER_GET_USER_PROFILE_ERROR());
		}
		UserProfileInsideVO result = new UserProfileInsideVO();
		result.setUid(profile.getUid());
		result.setNickname(profile.getNickname());
		return new RestResult<>(result);
	}
	
	/**
	 * @Description: 根据userId获取UserProfile信息(可多个userId)
	 * @author Hins
	 * @date 2015年8月17日 上午11:58:23
	 * 
	 * @param uids
	 * @return
	 */
	@GET
	@Path("v1/user/list")
	public RestResult<List<UserProfile>> getUserProfileUids(@QueryParam("uids") String uids) {
		logger.info("api#user#getUserProfileUids |get user information  | uids: {}", uids);
		ParameterUtil.assertNotBlank(uids, "参数为空");
		List<UserProfile> result = userService.listUserProfile(ParameterUtil.str2longs(uids));
		if (result == null) {
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "not found.");
		}
		return new RestResult<>(result);
	}

}
