/**
 * 
 */
package com.yogu.core.server.container.auth;

import java.io.IOException;
import java.util.List;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ServiceLoaderUtils;
import com.yogu.core.server.auth.Authenticator;
import com.yogu.language.AuthMessages;

/**
 * APP请求验签 <br>
 * 此类存在多个实例（因为是以class的形式注册到DynamicFeature中）
 * 
 * <br>
 * 
 * @author JFan 2015年7月17日 上午9:44:12
 */
@Priority(Priorities.AUTHENTICATION)
public class OAuthAuthenticatorFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(OAuthAuthenticatorFilter.class);

	// @Inject
	// 由 DynamicFeature 动态 register 的Filter，无法ioc
	// 》》OAuthAuthenticatorFilter 类会被实例化很多次，所以需要加一个static，所有实例共享
	private static List<Authenticator> authenticatorList;

	@Context
	private HttpServletRequest request;

	public OAuthAuthenticatorFilter() {
		initial();// 加载authenticatorList
		Args.notNull(authenticatorList, AuthMessages.AUTH_OAUTH_INIT_EMPTY());
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.container.ContainerRequestFilter#filter(javax.ws.rs.container.ContainerRequestContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// No authorization throw AuthenticationException
		// 异常由框架捕获
		authenticate(request);
	}

	/**
	 * 逐个校验，异常时抛出AuthenticationException
	 */
	private void authenticate(HttpServletRequest request) {
		// List<Authenticator> list = authenticatorList();
		for (Authenticator auth : authenticatorList)
			auth.authenticate(request);
	}

	/**
	 * 启动时，有可能实例还没有被装载，所以走异步（需要时装载）的形式，但只初始化一次
	 */
	private List<Authenticator> initial() {
		if (null == authenticatorList) {
			logger.debug("Loading 'Authenticator' ......");
			authenticatorList = ServiceLoaderUtils.orderly(Authenticator.class);

			// 打印有那些校验器被装载了
			boolean one = true;
			StringBuffer str = new StringBuffer("[");
			for (Authenticator auth : authenticatorList) {
				if (!one)
					str.append(", ");
				str.append(auth.getClass());
				one = false;
			}
			str.append("]");
			logger.info("Loading 'Authenticator' size: {}, impl: {}", authenticatorList.size(), str.toString());
		}
		return authenticatorList;
	}

}
