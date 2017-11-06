package com.yogu.services.user.resource.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.validation.constraints.Length;
import com.yogu.commons.validation.constraints.NotBlank;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.core.base.BaseParams;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.user.dto.User;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.user.resource.vo.UserProfileInsideVO;
import com.yogu.web.UserToken;

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
	
	@POST
	@Path("security/webLogin.do")
	public RestResult<Map<String, Object>> login(@FormParam("countryCode") String countryCode,
			@FormParam("passport") String passport, @FormParam("password") String password, @FormParam("ip") String ip, @Context HttpServletRequest request) {
		logger.info("user#api#webLogin | web登录start | ip: {}", ip);

		String token = UserToken.getNew();
		Map<String, Object> map = new HashMap<>(4);
		int code = ResultCode.FAILURE;
		String message = "失败";
		try {
			User loginUser = userService.webLogin(countryCode, passport, password, ip);
			if (loginUser == null) {
				message = "帐号或密码错误";
			} else {
				map.put("uid", loginUser.getUid());
				map.put("nickname", loginUser.getNickname());
				map.put("ut", token);

				//new LoginInfoStore(loginUser.getUid(), token, secret, new Date()).addDevice(passport, LoginInfoStore.WEB, "", "").save();
				BaseParams base = new BaseParams();
				base.setAppName("web");
				base.setAppVersion("1.0");
				base.setSysName("");
				base.setSysVersion("");
				ThreadLocalContext.putThreadValue(SecurityContext.BASE_PARAMS, base);
				code = ResultCode.SUCCESS;
				message = "成功";
			}
		} catch (ServiceException e) {
			code = e.getCode();
			message = e.getMessage();
		}

		logger.info("user#api#webLogin | web登录{} | code: {}, message: {}", (code == ResultCode.SUCCESS ? "成功" : "失败"), code, message);
		return new RestResult<>(code, message, map);
	}
	
	/**
	 * 创建一个用户帐号
	 * 
	 * @param adminId 管理员ID
	 * @param countryCode 国家代码，不能为空
	 * @param passport 帐号，比如手机号码
	 * @param profilePic 用户头像
	 * @param nickname 昵称，不能为空
	 * @param password 密码，不能为空
	 * @return 成功返回用户的 uid
	 * @author ten 2015/10/5
	 */
	@POST
	@Path("user/create.do")
	public RestResult<Long> createUser(
			@FormParam("adminId") long adminId,
			@FormParam("ip") String ip, // 新增参数IP
			@FormParam("countryCode") @NotBlank(message = "国家代码不能为空") String countryCode,
			@FormParam("passport") @NotBlank(message = "帐号不能为空") @Length(trim = true, min = 4, max = 20, message = "帐号长度为4~20个字符") String passport,
			@FormParam("profilePic") String profilePic,// 新增参数头像， add by june 2017-01-13
			@FormParam("nickname") @NotBlank(message = "昵称不能为空") @Length(trim = true, min = 2, max = 30, message = "昵称长度为2~30个字符") String nickname,
			@FormParam("password") @NotEmpty(message = "密码不能为空") @Length(trim = false, min = 8, max = 16, message = "密码长度为8~16个字符") String password) {
		logger.info("api#user#createUser | 管理员创建帐号 start | adminId:{}, nickname: {}, countryCode:{}, passport: {}", adminId, nickname,
				countryCode, LogUtil.hidePassport(passport));
		User user = new User();
		user.setCountryCode(countryCode.trim());
		user.setPassport(passport.trim());
		user.setProfilePic(profilePic == null ? "" : profilePic.trim());
		user.setNickname(nickname.trim());
		user.setPassword(password);
		long uid = userService.registerSale(user, ip);
		logger.info("api#user#createUser | 管理员创建帐号成功 | adminId: {}, countryCode:{}, passport: {}, profilePic: {}, nickname: {}, uid: {}", adminId,
				countryCode, LogUtil.hidePassport(passport), profilePic, nickname, uid);
		return new RestResult<>(uid);
	}
	
	/**
	 * 管理员对用户帐号充值密码
	 * 
	 * @param adminId 管理员ID
	 * @param uid 用户ID
	 * @param password 新的密码
	 * @return true=成功
	 * @author ten 2015/20/22
	 */
	@POST
	@Path("user/changePassword.do")
	public RestResult<Object> changePassword(@FormParam("adminId") long adminId, @FormParam("uid") long uid,
			@FormParam("password") String password) {

		logger.error("api#user#changPassword | 管理员修改用户密码start | adminId:{}, uid: {}", adminId, uid);
		UserProfile user = userService.getUserProfile(uid);
		if (user == null) {
			logger.error("api#user#changPassword | 管理员修改用户密码失败，帐号不存在 | uid: {}", uid);
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "用户帐号不存在");
		}

		userService.adminResetPassword(adminId, password, user.getPassport(), user.getCountryCode());

		logger.info("api#user#changPassword | 管理员修改用户密码成功 | adminId:{}, uid: {}", adminId, uid);
		return new RestResult<>(ResultCode.SUCCESS, "修改密码成功");
	}

}
