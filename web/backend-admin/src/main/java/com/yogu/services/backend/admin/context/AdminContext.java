package com.yogu.services.backend.admin.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.sdk.user.MazingLoginContext;
import com.yogu.commons.utils.MazingCookie;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.commons.utils.encrypt.HMacSHA1;
import com.yogu.core.web.encrypt.StaticKeyHelper;

/**
 * 后台管理员上下文信息
 * @author linyi 2015/6/6.
 */
public class AdminContext {

	private static final Logger logger = LoggerFactory.getLogger(AdminContext.class);

	private static ThreadLocal<AdminLoginUser> loginUserThreadLocal = new ThreadLocal<>();

	public static final String REQUEST_KEY = "admin.httprequest";

	/** 各种ID保存在cookie里的名称 */
	public static final String COOKIE_ID_NAME = "mzadmin_id";

	/** 一个签名，用于验证数据的合法性 */
	public static final String COOKIE_SIGN = "mzadmin_sign";

	public static final String DOMAIN = "admin.mazing.com";

	private static final String SIGN_KEY = "@uu2t[]2oisa&*93292)(---KJEIJi3u2";

	/**
	 * 返回已经登录的管理员ID
	 * @return
	 */
	public static long getAccountId() {
		AdminLoginUser u = loginUserThreadLocal.get();
		return (u == null ? 0 : u.getAccountId());
	}

	/**
	 * 返回当前登录的应用系统ID
	 *
	 * @return
	 */
	public static int getAppId() {
		AdminLoginUser u = loginUserThreadLocal.get();
		return (u == null ? 0 : u.getAppId());
	}

	public static String getToken() {
		AdminLoginUser u = loginUserThreadLocal.get();
		return (u == null ? "" : u.getToken());
	}

	/**
	 * 返回管理员名称
	 * @return
	 */
	public static String getName() {
		// TODO 修改为管理系统的realname
		return MazingLoginContext.getNickname();
	}

	/**
	 * 返回 HttpServletRequest。这个值在 AdminFilter 里设置了。
	 * 主要用于 AuthorityInterceptionService。
	 * 因为 jersey 没有地方很方便地获取 request
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ThreadLocalContext.getThreadValue(REQUEST_KEY);
	}

	/**
	 * 清除数据
	 */
	public static void clear() {
		loginUserThreadLocal.remove();
	}

	/**
	 * 清除cookie
	 */
	public static void clearCookie(HttpServletResponse response) {
		MazingCookie mc = new MazingCookie(DOMAIN, true);
		mc.setCookie(response, COOKIE_ID_NAME, "", 0);
		mc.setCookie(response, COOKIE_SIGN, "", 0);
	}

	/**
	 * 保存到 cookie
	 * @param response Http Servlet Reponse
	 * @param user - 用户信息
	 */
	public static void saveToCookie(HttpServletResponse response, AdminLoginUser user) {
		String context = user.toString();
		String contextEncrypted = StaticKeyHelper.encryptKey(context);
		String sign = HMacSHA1.getSignature(context, SIGN_KEY);
		MazingCookie mc = new MazingCookie(DOMAIN, false);
		mc.setCookie(response, COOKIE_ID_NAME, contextEncrypted);
		mc.setCookie(response, COOKIE_SIGN, sign);
		loginUserThreadLocal.set(user);
	}

	/**
	 * 从 Cookie 读取管理员登录信息
	 */
	public static void loadFromCookie(HttpServletRequest request) {
		AdminLoginUser user = new AdminLoginUser();
		user.setAccountId(5002);
		user.setAppId(5002);
		user.setToken("token");
		loginUserThreadLocal.set(user);
		
		
//		MazingCookie mc = new MazingCookie(DOMAIN, false);
//		String contextEncrypted = mc.getCookie(request, COOKIE_ID_NAME);
//		String sign = mc.getCookie(request, COOKIE_SIGN);
//		if (StringUtils.isNotBlank(contextEncrypted) && StringUtils.isNotBlank(sign)) {
//			try {
//				String context = StaticKeyHelper.descryptKey(contextEncrypted);
//				String tmpSign = HMacSHA1.getSignature(context, SIGN_KEY);
//				if (tmpSign.equals(sign)) {
//					AdminLoginUser user = AdminLoginUser.parse(context);
//					loginUserThreadLocal.set(user);
//				}
//			} catch (Exception e) {
//				logger.error("admin#context#load | Load cookie info error | contextEncrypted: " + contextEncrypted + ", sign: " + sign, e);
//			}
//		}
	}

}
