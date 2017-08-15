/**
 * 
 */
package com.yogu.commons.server.context;

import javax.servlet.http.HttpServletRequest;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.StringUtils;

/**
 * 按照框架开发约定，开发人员不能直接使用 servlet-api中的内容 <br>
 * 当开发人员需要读取 Request对象中的某些信息时（param、header、cookie 除外），可以使用这个对象进行构造 <br>
 * 
 * JFan 2014年12月17日 下午4:19:58
 */
public class RequestInfo {

	private HttpServletRequest request;

	public RequestInfo(HttpServletRequest request) {
		Args.notNull(request, "'request'");
		this.request = request;
	}

	/**
	 * 获取请求者IP地址
	 */
	public String getRequestIp() {
		// return getRequestIp(request);// 统一使用Ten提供的方法
		return IpAddressUtils.getClientIpAddr(request);
	}

	// ####

	/**
	 * 获取请求者IP地址
	 */
	protected static String getRequestIp(HttpServletRequest request) {
		String ip = parseRequestIp(request.getHeader("HTTP_CDN_SRC_IP"));
		if (StringUtils.isBlank(ip))
			ip = parseRequestIp(request.getHeader("X-Forwarded-For"));
		if (StringUtils.isBlank(ip))
			ip = parseRequestIp(request.getHeader("HTTP_X_FORWARDED_FOR"));
		if (StringUtils.isBlank(ip))
			ip = parseRequestIp(request.getHeader("HTTP_CLIENT_IP"));
		if (StringUtils.isBlank(ip))
			ip = request.getRemoteAddr();
		return ip;
	}

	// ####
	// ## private func
	// ####

	private static String parseRequestIp(String s) {
		if (StringUtils.isBlank(s) || "unknown".equalsIgnoreCase(s))
			return null;

		String[] splits = s.split(",");
		if (splits == null || splits.length == 0)
			return null;

		for (int i = 0; i < splits.length; i++) {
			String split = splits[i];
			if (StringUtils.isNotBlank(split)) {
				String ip = split.trim();
				if (IpAddressUtils.isIP(ip) && !(IpAddressUtils.isLoopbackIP(ip)))
					return ip;
			}
		}
		return null;
	}

}
