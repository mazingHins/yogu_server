package com.yogu.commons.utils.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 资源名称定义，URL读取 方法中 RequestMapping 的定义
 * @author linyi
 * 2014年2月25日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface MenuResource {

	/**
	 * 资源名称
	 * @return
	 */
	String value();
}
