/**
 * 
 */
package com.yogu.commons.datasource.annocation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yogu.commons.datasource.Source;

/**
 * 在service的func上使用 <br>
 * 描述使用何种数据源
 * 
 * JFan 2014年12月4日 下午7:43:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TheDataSource {

	/**
	 * 需要使用什么数据源
	 */
	Source value() default Source.MASTER;

}
