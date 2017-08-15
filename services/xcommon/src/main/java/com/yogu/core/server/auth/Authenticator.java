package com.yogu.core.server.auth;

import javax.servlet.http.HttpServletRequest;

import com.yogu.core.server.exception.AuthenticationException;

/**
 * 对http请求进行校验的接口 <br>
 * 
 * @author JFan 2015年7月17日 上午10:42:14
 */
public interface Authenticator {

	/**
	 * 对request请求进行校验，正常则方法结束
	 * 
	 * @throws AuthenticationException 任何权限异常（不正确）都返回这个异常
	 */
	public void authenticate(HttpServletRequest request) throws AuthenticationException;

}
