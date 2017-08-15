package com.yogu.core.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.web.encrypt.StaticKeyHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookie操作
 * 
 * @author tendy 2014/2/18
 */
public final class CookieOperation {

	private static final Logger logger = LoggerFactory.getLogger(CookieOperation.class);

	/**
	 * 设置cookie，加密处理，域名为：.mazing.com
	 * @param response - HttpServletResponse
	 * @param name
	 *            - cookie名字
	 * @param value
	 *            - cookie值
	 */
	public static void setCookie(HttpServletResponse response,String name, String value) {
		try {
			value = URLEncoder.encode(StaticKeyHelper.encryptKey(value), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("不支持utf-8编码", e);
		}
		setCookie(response,name, value, -1); 
	}

	/**
	 * 设置 cookie，域名为：.mazing.com
	 * 
	 * @param name
	 *            - 名称
	 * @param value
	 *            - 值
	 * @param maxAge
	 *            - 有效时间
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value == null ? "" : value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		cookie.setDomain(".mazing.com");
		response.addCookie(cookie);
	}

	/**
	 * 读取cookie的值，自动解密
	 * 
	 * @param name
	 *            - cookie名字
	 * @return - cookie值，不存在时为null
	 */
	public static String getCookie(HttpServletRequest request, String name) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest();
		Cookie[] cookies = request.getCookies();
		
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				String value = cookies[i].getValue();
				try {
					value = StaticKeyHelper.descryptKey(URLDecoder.decode(
							cookies[i].getValue(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
				}
				return value;
			}
		}
		return null;
	}

}
