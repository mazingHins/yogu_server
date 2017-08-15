package com.yogu.commons.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在某个方法正常结束时，执行清理cache的动作 <br>
 * 允许一次清理多个KEY <br>
 * 每个KEY如何组装，由CacheCleanExpr决定
 *
 * @author JFan 2015年8月19日 上午10:10:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheClean {

	/**
	 * 需要清理的规则
	 */
	CacheCleanExpr[] value();

	/**
	 * 是否强制执行清理动作<br>
	 * true：无论方法是否正常结束，都执行清理动作<br>
	 * false：只有方法正常结束才执行
	 */
	boolean force() default false;

}
