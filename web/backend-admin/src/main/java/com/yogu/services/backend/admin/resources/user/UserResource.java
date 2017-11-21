package com.yogu.services.backend.admin.resources.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.remote.user.vo.UserProfileVO;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.context.AdminContext;


/**
 * 用户管理逻辑
 * 
 * @author ten 2015/9/17.
 */
@Menu(name = "用户管理", sequence = 4000000)
@Controller
@RequestMapping("/admin/user/")
public class UserResource {

	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private UserRemoteService userRemoteService;

	/**
	 * 增加管理员主页，xhtml 仅用于展示页面，ajax 调用 .do 接口获取参数
	 * 
	 * @return
	 */
	@RequestMapping("queryUser.xhtm")
	@MenuResource("用户管理主页")
	public String index() {
		return ("/user/query_user");
	}

	/**
	 * 查询用户帐号的详细信息，包括昵称、ID、头像、状态等所有信息
	 * 
	 * @param countryCode 国家代码
	 * @param passport 搜索的关键字
	 * @param uid 用户ID，如果passport和uid同时填，uid优先
	 * @return result.object=数据列表，数据列表不为null
	 */
	@RequestMapping("queryUser")
	@MenuResource("查询用户帐号")
	@ResponseBody
	public RestResult<UserProfileVO> queryUser(String countryCode, String passport, long uid, HttpServletRequest request) {

		logger.info("admin#user#queryUser | 管理员查询帐号 | adminId: {}, countryCode: {}, passport: {}, uid: {}", AdminContext.getAccountId(),
				countryCode, LogUtil.hidePassport(passport), uid);
		RestResult<UserProfileVO> user = null;
		if (uid > 0) {
			user = userRemoteService.adminGetUser(uid);
		} else {
			ParameterUtil.assertNotBlank(countryCode, "国家代码不能为空");
			ParameterUtil.assertNotBlank(passport, "帐号不能为空");
			user = userRemoteService.getUserProfileByPassport(countryCode.trim(), passport.trim());
		}

		return user;
	}

	/**
	 * 根据uid获取操作的用户信息表
	 * 
	 * @param uid - 用户id
	 * @return 操作的表名
	 */
	public static String getTableName(long uid) {
		// 分表64个
		int n = (int) (Math.abs(uid) % 64);
		return "mz_user.mz_user_profile_" + n;
	}

	/**
	 * 根据国家区号和通行证获取操作的表
	 *
	 * @param countryCode 国家区号
	 * @param passport 通行证
	 * @return
	 */
	public static String getTableName(String countryCode, String passport) {
		String str = countryCode + "|" + passport;
		// 分表128个
		int n = Math.abs(str.hashCode()) % 128;
		return "mz_user.mz_user_" + n;
	}

	/**
	 * 创建一个用户帐号，主要用于米星帮助商家创建帐号
	 * 
	 * @param countryCode 国家代码，不能为空
	 * @param passport 手机号码，不能为空
	 * @param profilePic 用户头像，不能为空
	 * @param nickname 昵称，不能为空
	 * @param password 密码，不能为空
	 * @return 成功返回用户的基本信息：{countryCode: '', passport: '', uid: 用户id, nickname: ''}
	 */
	@RequestMapping(value = "createUser.do", method = RequestMethod.POST)
	@MenuResource("创建用户帐号")
	@ResponseBody
	@AdminLog
	public RestResult<Map<String, Object>> createUser(String countryCode, String passport, String profilePic, String nickname, String password) {
		logger.info("admin#user#createUser | 管理员创建帐号start | countryCode:{}, passport: {}, nickname: {}, adminId: {}", countryCode,
				LogUtil.hidePassport(passport), nickname, AdminContext.getAccountId());
		ParameterUtil.assertNotBlank(countryCode, "国家代码不能为空");
		ParameterUtil.assertNotBlank(passport, "帐号不能为空");
		ParameterUtil.assertNotBlank(nickname, "昵称不能为空");
		ParameterUtil.assertNotNull(password, "密码不能为空");
		passport = passport.trim();
		countryCode = countryCode.trim();
		nickname = nickname.trim();
		if (passport.length() < 4 || passport.length() > 20) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "帐号长度为4~20个字符");
		}
		if (nickname.length() < 2 || nickname.length() > 30) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "昵称长度为2~30个字符");
		}
		if (password.length() < 8 || password.length() > 16) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码长度为8~16个字符");
		}

		// 如果是生产环境，只能创建14000开头的用户
		if (GlobalSetting.PROJENV_PROD.equals(GlobalSetting.getProjenv()) && !(passport.startsWith("14000")) && !(passport.startsWith("10000")))
			throw ResultCode.paramExcp("只能创建14000或10000开头的账号！");
		
		profilePic = StringUtils.EMPTY;

		// 获取IP
		String ip = IpAddressUtils.getClientIpAddr(AdminContext.getHttpServletRequest());
		RestResult<Long> createUserResult = userRemoteService.adminCreateUser(AdminContext.getAccountId(), countryCode, passport, profilePic, nickname,
				password, ip);
		if (createUserResult.isSuccess()) {
			Map<String, Object> map = new HashMap<>(4);
			map.put("countryCode", countryCode);
			map.put("passport", passport);
			map.put("profilePic", profilePic);
			map.put("nickname", nickname);
			map.put("uid", createUserResult.getObject());
			logger.info("admin#user#createUser | 管理员创建帐号成功 | countryCode:{}, passport: {}, profilePic: {}, nickname: {}, uid: {}, adminId: {}",
					countryCode, LogUtil.hidePassport(passport), nickname, createUserResult.getObject(), profilePic, AdminContext.getAccountId());
			return new RestResult<>(ResultCode.SUCCESS, "创建帐号成功：" + passport, map);
		}
		return new RestResult<>(createUserResult.getCode(), createUserResult.getMessage());
	}

	/**
	 * 修改用户的密码
	 * 
	 * @param uid 用户ID
	 * @param password 新密码
	 * @return 修改成功返回 success=true
	 */
	@RequestMapping(value = "changeUserPassword.do", method = RequestMethod.POST)
	@MenuResource("修改用户密码")
	@ResponseBody
	@AdminLog
	public RestResult<Object> changeUserPassword(long uid, String password) {
		long adminId = AdminContext.getAccountId();
		logger.info("admin#user#changePassword | 管理员修改用户密码start | adminId:{}, uid: {}", adminId, uid);
		ParameterUtil.assertNotNull(password, "密码不能为空");
		if (password.length() < 8 || password.length() > 16) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码长度为8~16个字符");
		}

		RestResult<Object> result = userRemoteService.adminChangePassword(adminId, uid, password);
		logger.info("admin#user#changePassword | 管理员修改用户密码 {} | adminId:{}, uid: {}", result.isSuccess(), adminId, uid);

		return result;
	}
	
}
