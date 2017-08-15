/**
 * api
 *
 * http://www.mazing.com
 * Copyright (c) 2008-2014. All Rights Reserved.
 * */

package com.yogu.core.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;
import javax.ws.rs.container.ContainerRequestFilter;

import com.yogu.core.server.container.auth.NotAuthenticatorFilter;

/**
 * 使用此注解，模式是不验签
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface SecureAuthenticator {

	/**
	 * 决定使用何种验签方式<br>
	 * 默认不做验签
	 */
	Class<? extends ContainerRequestFilter> value() default NotAuthenticatorFilter.class;

}
