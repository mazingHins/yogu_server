package com.yogu.services.user.resource.base;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.Validator;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.core.base.BaseParams;
import com.yogu.core.base.Point;
import com.yogu.core.server.annotation.FrequencyLimitation;
import com.yogu.core.utils.SmsUtil;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.remote.config.idcode.IdCodeService;
import com.yogu.remote.user.dto.User;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.utils.LoginInfoUtil;
import com.yogu.web.DeviceUtil;
import com.yogu.web.UserToken;

/**
 * @author Hins
 * @version createTime：2015年7月14日 下午6:53:04 帐号登录 对于同一个帐号（mobile）： 1. 同类型的设备，只允许一个登录。比如在iphone手机登录后，如果在其他手机登录，iphone手机的登录token将被清除； 2.
 *          目前只支持phone/pad/pc三种设备登录
 */

@Named
@Path("p")
@Singleton
@Produces("application/json;charset=UTF-8")
public class LoginResource {

	private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);

	@Inject
	private UserService userService;
	
	@Inject
	private IdCodeService idCodeService;

	@Inject
	private LoginInfoService loginInfoService;

	/**
	 * 登录
	 * 
	 * @param countryCode - 国家地区编号
	 * @param mobile - 手机号
	 * @param password - 密码
	 * @return
	 */
	@POST
	@Path("v1/user/login.do")
	public RestResult<Map<String, Object>> login(
			@FormParam("countryCode") @NotEmpty(message = "countryCode must not be empty", mkey = UserMessages.USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY) String countryCode,
			@FormParam("mobile") @NotEmpty(message = "请输入手机号码", mkey = UserMessages.USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY) String mobile,
			@FormParam("password") @NotEmpty(message = "请输入登录密码", mkey = UserMessages.USER_LOGIN_PASSWORD_CAN_NOT_BE_EMPTY) String password, @Context HttpServletRequest request) {
		logger.info("user#login| before login | mobile: {}", SmsUtil.hideMobile(mobile));
		mobile = RegisterResource.decrypt(mobile);
		password = RegisterResource.decrypt(password);
		countryCode = SmsUtil.trimCountryCode(countryCode);
		// 获取用户IP
		String userIp = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP, "127.0.0.1");
		
		User user = new User();
		user.setCountryCode(countryCode);
		user.setPassport(mobile);
		user.setPassword(password);
		return doLogin(userService, loginInfoService, user, SecurityContext.getBaseParams(), SecurityContext.getPoint(), DeviceUtil.getRequestDevice(), userIp, true);
	}
	
	/**
	 * 发送无密登录的短信验证码, 并告诉客户端用户是否已经在米星注册过
	 * 
	 * @param receiver 加密后的手机号
	 * @param countryCode 国家代码
	 * @return isRegistered - 1 - 标识已注册
	 *						    其他 - 未注册
	 */
	@POST
	@Path("v1/user/smsCode.do")
	@FrequencyLimitation(key = FrequencyLimitation.FrequencyKey.DID, unit = FrequencyLimitation.FrequencyUnit.MINUTE, value = 1)
	public RestResult<Object> getSmsCode(@FormParam("mobile") String mobile,
			@FormParam("countryCode") String countryCode, @FormParam("func") String func) {

		// String ip = getClientIp();
		String ip = ThreadLocalContext.getThreadValue(ThreadLocalContext.REQ_CLIENT_IP);

		logger.info("config#sms#code | send sms start | receiver: {}, countryCode: {}, func: {}, ip: {}",
				SmsUtil.hideMobile(mobile), countryCode, func, ip);
		RestResult<Object> result = null;

		if (StringUtils.isBlank(mobile))
			return new RestResult<>(ResultCode.PARAMETER_ERROR, "请输入手机号码");

		if (!Validator.isMobileNo(mobile))
			return new RestResult<>(ResultCode.PARAMETER_ERROR, "手机号码不正确,请重新输入");

		if (!idCodeService.isValidFunc(func)) {
			// // error
			logger.error("error func|func=" + func + "|ip=" + ip);
			result = new RestResult<>(ResultCode.PARAMETER_ERROR, "func错误");
		} else if (mobile.length() == 11 && NumberUtils.isDigits(mobile) && mobile.startsWith("1")) {

			logger.info("config#sms#code | 检查用户是否需要发验证码. | func: {}, compare: {}", func, func.equals(IdCodeService.FUNC_REG));
			if (!func.equals(IdCodeService.FUNC_REG) && !func.equals(IdCodeService.FUNC_LOGIN_NO_PWD)) {
				User user = userService.getUser(countryCode, mobile);
				if (null == user) {
					logger.warn("config#sms#code | 用户不存在, 无法发送验证码");
					return new RestResult<>(ResultCode.PARAMETER_ERROR, "用户不存在, 无法发送验证码");
				}
			}

//			if (idCodeService.sendSmsIdCode(countryCode, receiver, func)) {
				result = new RestResult<>(ResultCode.SUCCESS, "发送短信验证码成功");
//			} else {
//				result = new RestResult<>(ResultCode.UNKNOWN_ERROR, "发送短信验证码失败");
//			}
			// }
		} else {
			// TODO 支持email的发送
			result = new RestResult<>(ResultCode.PARAMETER_ERROR, "手机号码不正确,请重新输入");
		}
		logger.info("config#sms#code | result: {}", JsonUtils.toJSONString(result));
		return result;
	}
	

	/**
	 * 执行登录的过程
	 * 
	 * @param userService - UserService
	 * @param countryCode - 国家代码
	 * @param mobile - 手机号码
	 * @param password - 密码
	 * @param base - 基础封签参数
	 * @param point - 坐标值
	 * @return
	 */
	public static RestResult<Map<String, Object>> doLogin(UserService userService, LoginInfoService storeService, User user, BaseParams base, Point point, String s, String ip, boolean needPwd) {
		RestResult<Map<String, Object>> result = null;

		String token = UserToken.getNew();

		User loginUser = needPwd ? userService.appLogin(user.getCountryCode(), user.getPassport(), user.getPassword(), base, point, ip, token)
				: userService.appLoginWithoutPwd(user.getCountryCode(), user.getPassport(), base, point, ip, token);

		if (loginUser == null) {
			result = new RestResult<Map<String, Object>>(ResultCode.FAILURE, needPwd ? UserMessages.USER_LOGIN_NEED_PW_USER_NOT_EXIST()
					: UserMessages.USER_LOGIN_NO_PW_USER_NOT_EXIST());
		} else {
			String secret = UserToken.getNew();
			Map<String, Object> map = new HashMap<>(4);
			map.put("ut", token);
			map.put("uid", loginUser.getUid());
			map.put("imId", loginUser.getImId());
			map.put("secret", secret);
			result = new RestResult<>(ResultCode.SUCCESS, "Success", map);
			
			logger.info("user#login | success | uid: {}, token(ut): {}", loginUser.getUid(), token);

			// String sysName = null == base ? "" : SecurityContext.getBaseParams().getSysName();
			// String target = null == base ? "" : SecurityContext.getBaseParams().getTarget();
			// new LoginInfoStore(loginUser.getUid(), token, secret, new Date()).addDevice(user.getPassport(), s, sysName, target).save();
			// 改成使用两层缓存的实现；jfan 2016-04-07
			long uid = loginUser.getUid();
			storeService.saveLoginInfo(uid, user.getPassport(), token, secret);

		}
		// 1. 判断参数是否正确
		// 2. 判断 device 规则是否正确
		// 3. 登录
		// 4. token写入redis，登录用户信息增加 device
		return result;
	}


	/**
	 * 检查无密注册参数是否正确，如果错误抛出异常
	 * 
	 * @param countryCode - 国家代码
	 * @param mobile - 手机号码
	 * @param idcode - 验证码
	 */
	private void validateRegNoPwdParams(String countryCode, String mobile, String idcode) {
		ParameterUtil.assertNotBlank(mobile, UserMessages.USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY());

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
