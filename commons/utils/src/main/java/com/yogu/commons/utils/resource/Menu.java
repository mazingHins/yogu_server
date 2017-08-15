package com.yogu.commons.utils.resource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单定义
 * @author linyi
 * 2014年2月25日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
public @interface Menu {

	/**
	 * 菜单名称
	 * @return
	 */
	String name();
	
	/**
	 * 父菜单名称
	 * @return
	 */
	String parent() default "";
	
	/**
	 * 排列顺序
	 * @author chenjianhong
	 * @return
	 */
	int sequence() default 0;
}
