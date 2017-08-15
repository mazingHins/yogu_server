package com.yogu.core.server.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yogu.core.enums.Role;

/**
 * 标识某个方法要进行用户权限的检验：
 * 1, 只能用于/s域
 * 2, 可以使用多个权限值
 * 
 * @author Felix 2015年9月9日
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface OperationAuthenticator {
	/**
	 * 限制用户需要的权限
	 */
	Role[] value();
}
