package com.yogu.services.user.base.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.cache.redis.RedisLock;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.encrypt.MD5Util;
import com.yogu.core.base.BaseParams;
import com.yogu.core.base.Point;
import com.yogu.core.consumer.BussinessType;
import com.yogu.core.consumer.messageBean.CouponObtainCheckBO;
import com.yogu.core.enums.pay.PayMode;
import com.yogu.core.utils.SmsUtil;
import com.yogu.core.web.LoginInfoService;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.UserErrorCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.encrypt.PasswordHelper;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;
import com.yogu.mq.impl.aliyun.CommandMQProducer;
import com.yogu.remote.config.id.IdGenRemoteService;
import com.yogu.remote.user.dto.User;
import com.yogu.remote.user.dto.UserProfile;
import com.yogu.remote.user.dto.utils.AccountConstants;
import com.yogu.services.user.base.constants.UserConstants;
import com.yogu.services.user.base.dao.UserDao;
import com.yogu.services.user.base.dao.UserNicknameDao;
import com.yogu.services.user.base.dao.UserProfileDao;
import com.yogu.services.user.base.dao.UserSettingDao;
import com.yogu.services.user.base.entry.UserNicknamePO;
import com.yogu.services.user.base.entry.UserPO;
import com.yogu.services.user.base.entry.UserProfilePO;
import com.yogu.services.user.base.entry.UserSettingPO;
import com.yogu.services.user.base.service.UserService;
import com.yogu.services.utils.LoginInfoUtil;
import com.yogu.web.DeviceUtil;
import com.yogu.web.UserToken;

/**
 * UserService 的实现类
 * 
 * @author JFan 2015-07-13
 */
@Named
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private UserDao dao;

	@Inject
	@Named("redis")
	private Redis redis;

	@Inject
	private UserNicknameDao userNicknameDao;

	@Inject
	private UserProfileDao userProfileDao;

	@Inject
	private IdGenRemoteService idGenRemoteService;

	@Inject
	private UserSettingDao userSettingDao;

	@Inject
	private LoginInfoService loginInfoService;

	// ####
	// ## private func
	// ####

	public User po2dto(UserPO po) {
		return VOUtil.from(po, User.class);
	}

	public UserPO dto2po(User dto) {
		return VOUtil.from(dto, UserPO.class);
	}

	/**
	 * 检查参数是否正确
	 * 
	 * @param user
	 */
	private void validateUser(User user) {
		ParameterUtil.assertNotNull(user, UserMessages.USER_REGISTER_PARAM_USER_CAN_NOT_BE_EMPTY());
		ParameterUtil.assertNotBlank(user.getPassport(), UserMessages.USER_LOGIN_PASSPORT_CAN_NOT_BE_EMPTY());
		// modify by sky 2015-12-09 check password
		LoginInfoUtil.passwordCheck3(user.getPassword());

		ParameterUtil.assertNotBlank(user.getNickname(), UserMessages.USER_LOGIN_NICKNAME_CAN_NOT_BE_EMPTY());

		if (NumberUtils.isDigits(user.getPassport())) {
			// 手机需要国家代码
			ParameterUtil.assertNotBlank(user.getCountryCode(), UserMessages.USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2());
			String countryCode = SmsUtil.trimCountryCode(user.getCountryCode());
			user.setCountryCode(countryCode);
		} else if (user.getCountryCode() == null) {
			user.setCountryCode("");
		}
	}

	/**
	 * 执行登录逻辑
	 * 
	 * @param countryCode 国家代码，不能为空
	 * @param passport 帐号，不能为空
	 * @param password 密码，不能为空
	 * @param base 基础信息，可能为null
	 * @param point 坐标，可能为null
	 * @param ip 用户登录的IP，可能为null
	 * @param ut 用户登录的token，可能为null
	 * @param source 登录来源，不可以为null
	 * @return 成功返回用户信息
	 * @author ten 2016/3/11
	 */
	private User doLogin(String countryCode, String passport, String password, BaseParams base, Point point, String ip, String ut,
			String source) {
		ParameterUtil.assertNotBlank(passport, UserMessages.USER_LOGIN_PASSPORT_CAN_NOT_BE_EMPTY());
		ParameterUtil.assertNotBlank(password, UserMessages.USER_SERVICE_DOLOGIN_PASSWORD_CAN_NOT_BE_EMPTY());
		if (NumberUtils.isDigits(passport)) {
			// 手机号码需要国家代码
			ParameterUtil.assertNotBlank(countryCode, UserMessages.USER_LOGIN_COUNTRY_CODE_CAN_NOT_BE_EMPTY2());
			countryCode = SmsUtil.trimCountryCode(countryCode);
		} else if (countryCode == null) {
			countryCode = "";
		}

		logger.info("user#userservice#doLogin | before login | passport:{}, encryptedPassport: {}, ip: {}", LogUtil.hidePassport(passport),
				LogUtil.encrypt(passport), ip);
		UserPO po = dao.getByPassport(countryCode, passport);
		if (po == null) {
			logger.error("user#userservice#doLogin | 帐号不存在 | passport:{}", SmsUtil.hideMobile(passport));
			throw new ServiceException(UserErrorCode.USER_NOT_FOUND, UserMessages.USER_LOGIN_NEED_PW_USER_NOT_EXIST());
		}

		// 检测密码\用户状态
		checkAccount(po.getPassword(), po.getStatus(), password);

		UserProfilePO profilePO = userProfileDao.getById(po.getUid());

		User user = VOUtil.from(po, User.class);
		user.setNickname(profilePO.getNickname());

		logger.info("user#userservice#login | success | uid: {}, ip: {}, source: {}", po.getUid(), ip, source);
		return user;
	}

	/**
	 * 检测帐号密码\状态<br>
	 * 如果密码不正确,或者被封号,会抛出异常
	 * 
	 * @param dbpwd DB中用户的密码
	 * @param status DB中用户的状态
	 * @param reqpwd 用户输入的密码
	 */
	private void checkAccount(String dbpwd, short status, String reqpwd) {
		// 判断密码是否正确
		String salt = PasswordHelper.resolvePassword(dbpwd).get("salt");// 获取加密的盐
		String encryptedPassword = PasswordHelper.encrypt(reqpwd, salt);
		if (!dbpwd.equals(encryptedPassword)) {
			logger.error("user#userservice#login | 密码错误 | passport:{}", SmsUtil.hideMobile(reqpwd));
			throw new ServiceException(UserErrorCode.USER_NOT_FOUND, UserMessages.USER_LOGIN_NEED_PW_USER_NOT_EXIST());
		}
	}

	@Override
	public UserProfile getUserProfile(long uid) {
		return VOUtil.from(userProfileDao.getById(uid), UserProfile.class);
	}

	@Override
	public User getUser(String countryCode, String passport) {
		return VOUtil.from(dao.getByPassport(countryCode, passport), User.class);
	}

	@Override
	public List<UserProfile> listUserProfile(long... uid) {
		if (uid != null) {
			List<UserProfile> list = new ArrayList<UserProfile>(uid.length);
			for (long id : uid) {
				UserProfilePO user = userProfileDao.getById(id);
				if (user != null) {
					list.add(VOUtil.from(user, UserProfile.class));
				}
			}
			return list;
		}
		return Collections.emptyList();
	}

	@Override
	public void resetPassword(String password, String mobile, String countryCode) {

		// 重置密码(找回密码)

		boolean neededCheckOldPassWord = false;// 是否需要对旧密码进行校验(重置密码是不需要输入旧密码的)
		boolean neededCheckBaned = true;// 是否需要检查封号

		confirmAndChange(password, "", countryCode, mobile, neededCheckOldPassWord, neededCheckBaned);
	}

	@Override
	public Map<String, Object> updatePassword(long uid, String password, String oldpassword, String countryCode, String mobile) {
		// 更新密码,修改密码

		boolean neededCheckOldPassWord = true;// 是否需要对旧密码进行校验(重置密码是不需要输入旧密码的)
		boolean neededCheckBaned = true;// 是否需要检查封号

		confirmAndChange(password, oldpassword, countryCode, mobile, neededCheckOldPassWord, neededCheckBaned);

		// 得到当前用户的token
		// String oldToken = SecurityContext.getUserToken();
		// 得到当前设备用户 的 设备名
		String deviceName = DeviceUtil.getRequestDevice();

		// String currDevice = session.getSimpleDevice(deviceName);
		// session.forcedOthersOffLine(uid, currDevice, oldToken);
		// 改成使用两层缓存的实现；jfan 2016-04-07
		loginInfoService.clearUserAllLoginCache(uid, uid);

		// 生成新的 user token
		String newToken = UserToken.getNew();

		// 颁发新的登录密钥
		String secret = UserToken.getNew();

		// 系统参数
		String sysName = SecurityContext.getBaseParams().getSysName();
		String target = SecurityContext.getBaseParams().getTarget();
		// 目前修改密码后的策略： 保持用户登录状态， 无需另执行手动登录
		updateCurrentUserLoginInfoStore(uid, newToken, secret, mobile, deviceName, sysName, target);

		// 数据下发
		Map<String, Object> map = new HashMap<>(3);
		map.put("ut", newToken);
		map.put("uid", uid);
		map.put("secret", secret);

		return map;
	}

	/**
	 * 更行当前 保存用户登录信息的 容器
	 * 
	 * @param uid 用戶id
	 * @param token 登录验证token
	 * @param secret 登录验证密钥
	 * @param mobile
	 * @param s device name
	 * @param sysName 系统名 ios/android
	 * @param target ios版本区分线上、测试版本参数
	 */
	private void updateCurrentUserLoginInfoStore(long uid, String token, String secret, String mobile, String s, String sysName,
			String target) {
		// new LoginInfoStore(uid, token, secret, new Date()).addDevice(mobile, s, sysName, target).save();
		// 改成使用两层缓存的实现；jfan 2016-04-07
		loginInfoService.saveLoginInfo(uid, mobile, token, secret);
	}

	/**
	 * 检查密码是否有变化, 更新用户账号密码
	 * 
	 * @param password
	 * @param oldpassword
	 * @param countryCode
	 * @param mobile
	 * @param neededCheckOldPassWord 是否需要做旧的密码核查
	 * @param neededCheckBaned 是否需要做封号检查
	 */
	private void confirmAndChange(String password, String oldpassword, String countryCode, String mobile, boolean neededCheckOldPassWord,
			boolean neededCheckBaned) {

		logger.info(
				"user#userService#changePWD | before change | password: {}, oldpassword: {}, countryCode: {}, mobile: {}, needCheckOldPWD: {}, needCheckBaned: {}",
				LogUtil.hidePassport(password), LogUtil.hidePassport(oldpassword), countryCode, LogUtil.hidePassport(mobile),
				neededCheckOldPassWord, neededCheckBaned);

		// 参数校验
		validate(password, countryCode, mobile);

		// 获取db 中的user 信息
		User user = null;
		try {
			user = getUser(countryCode, mobile);
		} catch (Exception e) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_SET_PUSH_PARAM_ERROR());
		}

		if (null == user)
			throw new ServiceException(UserErrorCode.USER_NOT_FOUND, UserMessages.USER_BIND_EMAIL_EMAIL_USER_NOT_EXIST());

		// 是否需要做旧的密码匹配检查
		if (neededCheckOldPassWord) {// 需要

			String encryptedOldPassword = user.getPassword(); // 数据库中经过加盐处理的加密密码
			String salt = PasswordHelper.resolvePassword(encryptedOldPassword).get("salt");// 获取加密的盐

			if (StringUtils.isBlank(oldpassword))
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "旧密码为空");

			String outputOldPassword = PasswordHelper.encrypt(oldpassword, salt);// 当前用户输入的旧密码 的 加密密码
			// 旧密码校验
			if (!encryptedOldPassword.equals(outputOldPassword))
				throw new ServiceException(UserErrorCode.PASSWORD_ERROR, "旧密码错误");
		}

		// 新盐
		String newSalt = PasswordHelper.salt();
		// 获取经过加盐处理的新密码 的加密密码
		String encryptedNewPassword = PasswordHelper.encrypt(password, newSalt);

		long uid = user.getUid();
		// 更新密码
		try {
			dao.udpatePasswordByUid(uid, countryCode, mobile, encryptedNewPassword);
		} catch (Exception e) {

			logger.error("user#changepassword | update password error | msg: {}", e.getMessage());
			throw new ServiceException(ResultCode.UNKNOWN_ERROR, "未知错误");
		}
	}

	/**
	 * 参数校验
	 * 
	 * @param password 密码
	 * @param countryCode 国家代码
	 * @param mobile 手机号
	 */
	private void validate(String password, String countryCode, String mobile) {

		ParameterUtil.assertNotBlank(countryCode, "国家代码不能为空");

		// 手机号合法性校验
		LoginInfoUtil.mobileCheck(mobile);
		// 密码安全性校验
		LoginInfoUtil.passwordCheck3(password);

	}

	@Override
	public void adminResetPassword(long adminUid, String password, String mobile, String countryCode) {

		logger.info(
				"user#userService#adminResetPassword | before admin execute operate | adminUid: {},password: {},  mobile: {}, countryCode: {}",
				adminUid, LogUtil.hidePassport(password), LogUtil.hidePassport(mobile), countryCode);

		boolean neededCheckOldPassWord = false;// 重置密码是不需要输入旧密码的
		boolean neededCheckBaned = false;// 管理员修改密码是不需要做封号检查的

		confirmAndChange(password, "", countryCode, mobile, neededCheckOldPassWord, neededCheckBaned);

		logger.info(
				"user#userService#adminResetPassword | admin resetpassword success | adminUid: {},password: {},  mobile: {}, countryCode: {}",
				adminUid, LogUtil.hidePassport(password), LogUtil.hidePassport(mobile), countryCode);
	}

	@Override
	public List<UserPO> listAll() {
		return dao.listAll();
	}

	@Override
	public User appLogin(String countryCode, String passport, String password, BaseParams base, Point point, String ip, String ut) {
		User user = doLogin(countryCode, passport, password, base, point, ip, ut, "appLogin");

		return user;
	}

	@Override
	public User appLoginWithoutPwd(String countryCode, String passport, BaseParams base, Point point, String ip, String ut) {
		UserPO po = dao.getByPassport(countryCode, passport);
		if (po == null) {
			logger.error("user#userservice#login | 帐号不存在 | passport:{}", SmsUtil.hideMobile(passport));
			throw new ServiceException(UserErrorCode.USER_NOT_FOUND, "用户帐号或密码错误");
		}

		UserProfilePO profilePO = userProfileDao.getById(po.getUid());
		User user = VOUtil.from(po, User.class);
		user.setNickname(profilePO.getNickname());

		logger.info("user#userservice#loginWithoutPwd | success | uid: {}, ip: {}", po.getUid(), ip);
		return user;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public long register(User user, String ip) {
		String hideMobile = SmsUtil.hideMobile(user.getPassport());
		String rip = (StringUtils.isNotBlank(ip) ? ip : "");

		// 加个redis锁，防止并发提交
		String key = "USER_REG:" + user.getCountryCode() + "_" + user.getPassport();
		RedisLock lock = new RedisLock(redis, key, 60);
		// 拿不到锁，直接终止
		if (!(lock.lock())) {
			logger.error("account#userservice#register | 重复提交注册请求，被拒绝 | passport: {} ", hideMobile);
			throw new ServiceException(UserErrorCode.USER_EXISTED, "请不要重复提交请求。");
		}

		// 1. 检查参数是否正确
		validateUser(user);

		UserPO current = dao.getByPassport(user.getCountryCode(), user.getPassport());
		if (current != null) {
			// 兼容重复提交注册信息的情况,如果2分钟内重复提交,只要密码一致则接受（直接返回uid）
			Date userRegTime = current.getCreateTime();
			if (null != userRegTime) {
				// 当前时间与用户注册时间的时差
				long timeDifference = System.currentTimeMillis() - userRegTime.getTime();
				timeDifference = Math.abs(timeDifference);

				if (timeDifference <= (2 * 60 * 1000)) {// 2分钟（先固定写在这吧！）
					// 检测密码与DB中的密码是否一致（不一致，其内部会抛异常）
					// 同时检测了用户是否被冻结
					checkAccount(current.getPassword(), current.getStatus(), user.getPassword());
					return current.getUid();
				}
			}

			logger.error("account#userservice#register | Account is already exists | passport: {} ", hideMobile);
			throw new ServiceException(UserErrorCode.USER_EXISTED, "帐号已经存在，请登录。");
		}

		// 2. 获取uid
		long uid = idGenRemoteService.getNextUserId();

		// 3. 加密密码
		String encryptedPassword = PasswordHelper.encrypt(user.getPassword());

		// 4. 保存
		UserPO po = VOUtil.from(user, UserPO.class);
		po.setCreateTime(new Date());
		po.setSource(AccountConstants.SOURCE_APP_REG);
		po.setUid(uid);
		if (user.getPassport().indexOf("@") >= 0) {
			// 邮箱要激活
			po.setStatus(AccountConstants.STATUS_WAITING_ACTIVED);
		} else {
			po.setStatus(AccountConstants.STATUS_NORMAL);
		}
		po.setPassword(encryptedPassword);
		dao.save(po);

		// 保存 profile
		UserProfilePO profilePO = new UserProfilePO();
		String cityCode = StringUtils.isNotBlank(user.getCityCode()) ? user.getCityCode() : "";
		String lang = SecurityContext.getAppLanguage().getCode();

		profilePO.setUid(uid);
		profilePO.setCountryCode(po.getCountryCode());
		profilePO.setNickname(user.getNickname());
		profilePO.setCreateTime(po.getCreateTime());
		profilePO.setLastUpdateTime(po.getCreateTime());
		profilePO.setPassport(user.getPassport());
		profilePO.setProfilePic(user.getProfilePic());// 兼容管理后台创建用户要上传头像，add by june 2017-01-13
		profilePO.setRegisterIp(rip);
		userProfileDao.save(profilePO);

		// 保存nickname
		UserNicknamePO nicknamePO = new UserNicknamePO();
		nicknamePO.setUid(uid);
		nicknamePO.setRegisterIp(rip);
		nicknamePO.setRegisterLang(lang);
		nicknamePO.setRegisterCityCode(cityCode);
		nicknamePO.setPassport(user.getPassport());
		nicknamePO.setNickname(user.getNickname());
		nicknamePO.setCreateTime(po.getCreateTime());
		nicknamePO.setCountryCode(po.getCountryCode());
		userNicknameDao.save(nicknamePO);

		// 5. TODO Send Message to MessageQueue
		// 6 Felix 新注册用户添加setting记录
		logger.info("account#userservice#register | create user setting info");

		UserSettingPO setting = new UserSettingPO();
		setting.setUid(uid);
		setting.setDefaultCityCode(StringUtils.isNotBlank(user.getCityCode()) ? user.getCityCode() : "020");
		setting.setDefaultLanguageId("");
		setting.setDefaultPayMode(PayMode.ALIPAY.getValue());
		setting.setIsPush(UserConstants.ALLOW_PUSH);
		setting.setCreateTime(new Date());
		userSettingDao.save(setting);

//		By jfan 2016-08-30 去掉了 UserImidIndex 表的使用，改成UserNickname表中使用IMID
//		// 7 添加imid - uid的索引
//		logger.info("account#userservice#register | create user imid index info");
//		UserImidIndexPO index = new UserImidIndexPO();
//		index.setImId(imid);
//		index.setUid(uid);
//		userImidIndexDao.save(index);

		// 发送MQ , 检查是否有领取优惠券的记录 add by sky 2015-12-30
		CouponObtainCheckBO bo = new CouponObtainCheckBO();
		bo.setUid(uid);
		String passport = user.getPassport();

		bo.setAccount(MD5Util.getMD5String(passport));

		// new add by sky 2016-02-27 新注册用户派发礼包
		int length = passport.length();
		String phoneSuffix = passport.substring(length - 4, length);
		String desMobile = LogUtil.encrypt(passport);
		bo.setPhoneSuffix(phoneSuffix);
		bo.setMobile(desMobile);

		// 调用领取优惠券的接口(假手机不发放优惠券，即长度=15的手机号)  add by june 2017-03-31
		if(user.getPassport().length() != 15){
			CommandMQProducer.get().sendJSON(BussinessType.COUPON_CHECK_AND_ASSIGN, bo);
		} else {
			logger.info("account#userservice#register | 假手机不发放优惠券  | uid: {}, passport: {}", uid, user.getPassport());
		}

		logger.info("account#userservice#register | success | uid: {}, passport: {}", uid, hideMobile);

		logger.info("Sending mail ... TODO ...");
		return uid;
	}

}
