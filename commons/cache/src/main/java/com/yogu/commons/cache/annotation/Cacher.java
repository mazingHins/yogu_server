package com.yogu.commons.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 缓存方法返回的数据 <br>
 * key为'KEY前缀':param1_params... <br>
 * value为return的对象<br>
 * <br>
 * 
 * 建议只在 ServiceImpl、Remote方法中使用
 *
 * @author JFan 2015年8月19日 上午10:05:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cacher {

	/**
	 * 缓存时间（秒）<br>
	 * 0表示使用系统配置的时间（在配置文件中配置）<br>
	 * -1表示永久
	 */
	int time() default -1;

	/**
	 * KEY前缀
	 */
	String value();

}
