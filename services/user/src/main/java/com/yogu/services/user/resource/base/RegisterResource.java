package com.yogu.services.user.resource.base;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.Validator;
import com.yogu.commons.utils.encrypt.DES3;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.core.base.BaseParams;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.utils.SmsUtil;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.config.idcode.IdCodeService;
import com.yogu.remote.user.dto.User;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.utils.LoginInfoUtil;
import com.yogu.web.DeviceUtil;

/**
 * 注册
 * 
 * @author Hins 上午11:51:14
 *
 */
@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class RegisterResource {

	private static final Logger logger = LoggerFactory.getLogger(RegisterResource.class);

	@Inject
	private UserService userService;

	@Inject
	private IdCodeService idCodeService;

	@Inject
	private LoginInfoService loginInfoService;

	/**
	 * 用户注册
	 * 
	 * @param countryCode - 国家号码，比如86表示中国，邮箱注册的时候不需要
	 * @param mobile - 手机号码或邮箱
	 * @param password - 密码
	 * @param nickname
	 * @param idcode - 验证码, 使用邮箱的时候不需要验证码
	 * @param inviteCode - 邀请码
	 * @return
	 */
	@POST
	@Path("v1/user/reg.do")
	public RestResult<Map<String, Object>> reg(
			@FormParam("countryCode") @NotEmpty(message = "countryCode must not be empty") String countryCode,
			@FormParam("mobile") @NotEmpty(message = "请输入手机号码", mkey = UserMessages.USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY) String mobile,
			@FormParam("password") @NotEmpty(message = "请输入登录密码", mkey = UserMessages.USER_LOGIN_PASSWORD_CAN_NOT_BE_EMPTY) String password,
			@FormParam("nickname") @NotEmpty(message = "请输入昵称", mkey = UserMessages.USER_UPDATE_NICKNAME_NICKNAME_CAN_NOT_BE_EMPTY) String nickname, 
			@FormParam("idcode") String idcode, @FormParam("inviteCode") String inviteCode,
			@Context HttpServletRequest request) {
		logger.info("user#reg |register start | mobile: {}, idcode: {}, nickname: {}", SmsUtil.hideMobile(mobile), idcode, nickname);
		BaseParams baseParams = SecurityContext.getBaseParams();
		// 获取用户的IP
		String registerIp = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP, "127.0.0.1");

		mobile = decrypt(mobile);
		password = decrypt(password);
		countryCode = SmsUtil.trimCountryCode(countryCode);

		validateRegParameters(countryCode, mobile, password, nickname, idcode);

		// 验证码是否正确
		boolean idCodeOK = true;
		if (NumberUtils.isDigits(mobile)) {
			// 手机注册需要检查验证码，邮箱不需要
			idCodeOK = "123456".equals(idcode);
//			idCodeOK = idCodeService.validateSmsIdCode(mobile, IdCodeService.FUNC_REG, idcode);
		} else {
			countryCode = ""; // 对于邮箱来说，重置为空
		}

		RestResult<Map<String, Object>> result = null;
		if (idCodeOK) {
			User user = new User();
			user.setPassport(mobile);
			user.setPassword(password);
			user.setNickname(nickname);
			user.setCountryCode(countryCode);
			user.setCityCode(SecurityContext.getCityCode());
			// 注册
			long uid = userService.register(user, inviteCode, registerIp);
			logger.info("account#reg | register success | mobile: {}, nickname: {}", SmsUtil.hideMobile(mobile), nickname);
			if (logger.isDebugEnabled())
				logger.debug("user#reg#result | result uid | uid: {}, userInfo: {}", uid, JsonUtils.toJSONString(user));

			// 保存头像
			result = LoginResource.doLogin(userService, loginInfoService, user, baseParams, SecurityContext.getPoint(), DeviceUtil.getRequestDevice(),
					registerIp, true);
			if (!(result.isSuccess())) {
				logger.error("login failed after register|result: {}", JsonUtils.toJSONString(result));
			}
		} else {
			logger.info("account#reg | register error | mobile: {}, nickname: {}", SmsUtil.hideMobile(mobile), nickname);
			result = new RestResult<>(UserErrorCode.IDCODE_ERROR, UserMessages.USER_PASSWORD_RESET_IDCODE_INVALID(), Collections.<String, Object> emptyMap());
		}
		return result;
	}
	
	/**
	 * 3des解密字符串
	 * 
	 * @param str
	 * @return
	 */
	static String decrypt(String str) {
		String appSecret = ConfigRemoteService.getConfig(ConfigGroupConstants.APP_KEY, SecurityContext.getBaseParams().getAppKey());
		try {
			if (str != null)
				return DES3.decrypt(str, appSecret);
			return null;
		} catch (Exception e) {
			logger.error("解密字符串出错: " + str);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_LOGIN_NEED_PW_USER_NOT_EXIST());
		}
	}

	/**
	 * 检查参数是否正确，如果错误抛出异常
	 * 
	 * @param countryCode - 国家代码
	 * @param mobile - 手机号码
	 * @param password - 密码
	 * @param nickname - 昵称
	 * @param idcode - 验证码
	 */
	private void validateRegParameters(String countryCode, String mobile, String password, String nickname, String idcode) {
		ParameterUtil.assertNotBlank(mobile, UserMessages.USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY());

		if (Validator.containsSpecialCharacter(nickname))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_REGISTER_NICKNAME_EMOJI_ERROR());

		// 密码校验
		LoginInfoUtil.passwordCheck3(password);

		if (NumberUtils.isDigits(mobile)) {

			// 手机号合法性校验
			LoginInfoUtil.mobileCheck(mobile);
			// 手机注册不需要国家代码
			ParameterUtil.assertNotBlank(countryCode, UserMessages.USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2());
			ParameterUtil.assertNotBlank(idcode, UserMessages.USER_LOGIN_IDCODE_CAN_NOT_BE_EMPTY());
		} else if (mobile.indexOf("@") <= 0) {
			logger.error("account#reg | 不是合法的邮箱 | mobile: {}", mobile);
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_LOGIN_EMAIL_ILLEGAL());
		}
	}
	

}
