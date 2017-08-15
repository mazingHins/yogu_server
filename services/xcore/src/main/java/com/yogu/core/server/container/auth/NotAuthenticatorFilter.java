/**
 * 
 */
package com.yogu.core.server.container.auth;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

/**
 * 不需要验签 <br>
 * 
 * JFan 2014年12月9日 下午4:02:54
 */
@Priority(Priorities.AUTHENTICATION)
public class NotAuthenticatorFilter implements ContainerRequestFilter {

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// ignore
	}

}
