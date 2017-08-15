/**
 * 
 */
package com.yogu.core.server.container.auth;

import java.lang.reflect.Method;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.server.annotation.SecureAuthenticator;

/**
 * 对自定义注解 @SecureAuthenticator 的支持 <br>
 * 
 * JFan 2014年12月4日 下午4:04:59
 */
public class SecureAnnoFeature implements DynamicFeature {

	private static final Logger logger = LoggerFactory.getLogger(SecureAnnoFeature.class);

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.ws.rs.container.DynamicFeature#configure(javax.ws.rs.container.ResourceInfo, javax.ws.rs.core.FeatureContext)
	 */
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		Class<?> clazz = resourceInfo.getResourceClass();
		Method method = resourceInfo.getResourceMethod();

		SecureAuthenticator ns = method.getAnnotation(SecureAuthenticator.class);
		if (null == ns)
			ns = clazz.getAnnotation(SecureAuthenticator.class);

		if (null == ns)
			context.register(OAuthAuthenticatorFilter.class);// def
		else {
			Class<? extends ContainerRequestFilter> authenticator = ns.value();
			if (null != authenticator) {
				logger.debug("core#secure#feature | useAuth {} | class.method: {}.{}", authenticator.getName(), clazz.getName(), method.getName());
				context.register(authenticator);
			} else
				logger.info("core#secure#feature | No AuthenticatorFilter | class.method: {}.{}", clazz.getName(), method.getName());
		}

	}

}
