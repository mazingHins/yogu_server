/**
 * 
 */
package com.yogu.core.server.container.permit;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;

import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.web.ResultCode;

/**
 * 访问是否继续 的 校验 <br>
 *
 * @author JFan 2015年8月7日 下午11:48:50
 */
@Priority(Priorities.AUTHORIZATION)
public abstract class PermitValidateFilter implements ContainerRequestFilter {

	@Context
	private HttpServletRequest request;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		boolean vali = validation(request);
		if (!(vali))
			throw new AuthenticationException(ResultCode.UNAUTHORIZED_ACCESS, "unauthorized access.");
	}

	/**
	 * 启动时，有可能实例还没有被装载，所以走异步（需要时装载）的形式，但只初始化一次
	 */
	public abstract boolean validation(HttpServletRequest request);

}
