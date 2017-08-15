package com.yogu.services.user.resource.base;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.core.utils.SmsUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.config.idcode.IdCodeService;
import com.yogu.remote.user.dto.utils.UserFieldEncoder;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.utils.LoginInfoUtil;

/**
 * 用户账户密码管理 接口<br>
 * 
 * @author sky
 *
 */
@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class PasswordResource {

	private static final Logger logger = LoggerFactory.getLogger(PasswordResource.class);

	@Inject
	private IdCodeService idCodeService;
	@Inject
	private UserService userService;

	/**
	 * 重置密码
	 * 
	 * @author sky
	 * @date 2015/08/25 14:54:23
	 * 
	 * @param idcode 短信验证码
	 * @param password 新密码
	 * @param mobile 手机号
	 * @param countryCode 国家代码
	 */
	@POST
	@Path("v1/user/password/reset.do")
	public RestResult<Integer> reset(@FormParam("idcode") @NotEmpty(message = "验证码不能为空", mkey = UserMessages.USER_LOGIN_IDCODE_CAN_NOT_BE_EMPTY) String idcode,
			@FormParam("password") @NotEmpty(message = "密码不能为空", mkey = UserMessages.USER_PASSWORD_RESET_PW_CAN_NOT_BE_EMPTY) String password,
			@FormParam("mobile") @NotEmpty(message = "手机号不能为空", mkey = UserMessages.USER_PASSWORD_RESET_MOBILE_CAN_NOT_BE_EMPTY) String mobile,
			@FormParam("countryCode") @NotEmpty(message = "国家代码不能为空", mkey = UserMessages.USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2) String countryCode) {

		// 解密
		mobile = UserFieldEncoder.getInstance().decryptField(mobile, UserMessages.USER_PASSWORD_RESET_MOBILE_INVALID());
		// 新密码
		password = UserFieldEncoder.getInstance().decryptField(password, UserMessages.USER_PASSWORD_RESET_PASSWORD_INVALID());

		countryCode = SmsUtil.trimCountryCode(countryCode);

		// 校验参数合法性
		validateReset(idcode, password, mobile);

		logger.info("user#resetpassword | before reset | idcode: {}, password: {}, mobile: {}, countryCode: {}", idcode, password,
				SmsUtil.hideMobile(mobile), countryCode);

		// 校验手机短信验证码
		checkIdCode(mobile, idcode);

		userService.resetPassword(password, mobile, countryCode);

		return new RestResult<Integer>(1);
	}

	/**
	 * 修改密码
	 * 
	 * @param oldpassword 旧密码
	 * @param password 新密码
	 * @param mobile 手机号
	 * @param countryCode 国家代码
	 * @return
	 */
	@POST
	@Path("v1/user/password/update.do")
	public RestResult<Map<String, Object>> update(
			@FormParam("oldpassword") @NotEmpty(message = "旧密码不能为空", mkey = UserMessages.USER_PASSWORD_UPDATE_OLD_PASSWORD_CAN_NOT_BE_EMPTY) String oldpassword,
			@FormParam("password") @NotEmpty(message = "密码不能为空", mkey = UserMessages.USER_PASSWORD_RESET_PW_CAN_NOT_BE_EMPTY) String password,
			@FormParam("mobile") @NotEmpty(message = "手机号不能为空", mkey = UserMessages.USER_PASSWORD_RESET_MOBILE_CAN_NOT_BE_EMPTY) String mobile,
			@FormParam("countryCode") @NotEmpty(message = "国家代码不能为空", mkey = UserMessages.USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2) String countryCode) {

		// 解密
		mobile = UserFieldEncoder.getInstance().decryptField(mobile, UserMessages.USER_PASSWORD_RESET_MOBILE_INVALID());
		// 新密码
		password = UserFieldEncoder.getInstance().decryptField(password, UserMessages.USER_PASSWORD_RESET_PASSWORD_INVALID());
		// 旧密码
		oldpassword = UserFieldEncoder.getInstance().decryptField(oldpassword, UserMessages.USER_PASSWORD_RESET_PASSWORD_INVALID());

		countryCode = SmsUtil.trimCountryCode(countryCode);

		// 校验参数合法性
		validateUpdate(oldpassword, password, mobile);

		// 登录校验
		long uid = SecurityContext.getUid();

		logger.info("user#updatepassword |before update | uid: {}, oldpassword: {}, password: {}, mobile: {}, countryCode: {}", uid,
				oldpassword, password, SmsUtil.hideMobile(mobile), countryCode);

		// 更新
		Map<String, Object> map = userService.updatePassword(uid, password, oldpassword, countryCode, mobile);

		return new RestResult<Map<String, Object>>(map);

	}

	/**
	 * 检查 短信验证码是否匹配
	 * 
	 * @param mobile
	 * @param idcode
	 */
	private void checkIdCode(String mobile, String idcode) {
		// 验证码 是否 匹配
		boolean pass_check = false;

		pass_check = idCodeService.validateSmsIdCode(mobile, IdCodeService.FUNC_CH, idcode);
		if (!pass_check)
			throw new ServiceException(UserErrorCode.IDCODE_ERROR, UserMessages.USER_PASSWORD_RESET_IDCODE_INVALID());
	}

	/**
	 * 校验参数合法性
	 * 
	 * @param oldpassword
	 * @param password
	 * @param mobile
	 */
	private void validateUpdate(String oldpassword, String password, String mobile) {

		// 暂时不做这步检查
		// if (oldpassword.equals(password))
		// throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码没有变化");
		// 手机号合法性校验
		LoginInfoUtil.mobileCheck(mobile);
		// 密码安全性校验
		LoginInfoUtil.passwordCheck3(password);

	}

	/**
	 * 校验参数合法性
	 * 
	 * @param idcode
	 * @param password
	 * @param mobile
	 */
	private void validateReset(String idcode, String password, String mobile) {

		if (StringUtils.isBlank(idcode))
			throw new ServiceException(UserErrorCode.IDCODE_ERROR, UserMessages.USER_PASSWORD_RESET_IDCODE_INVALID());
		// 手机号合法性校验
		LoginInfoUtil.mobileCheck(mobile);
		// 密码安全性校验
		LoginInfoUtil.passwordCheck3(password);

	}
}
