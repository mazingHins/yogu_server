package com.yogu.commons.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个cache key的组成描述 <br>
 * 由两部分组成：前缀、内容列表<br>
 * key为'KEY前缀':param1_params... <br>
 *
 * @author JFan 2015年8月19日 上午10:10:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CacheCleanExpr {

	/**
	 * 前缀
	 */
	String value();

	/**
	 * 内容列表，表达式<br>
	 * 指示如何从方法如参（args数组）中提取出 内容列表<br>
	 * 比如：缓存的key是Store:100，但是CacheClean标注的方法如参是一个Store对象，需要用到它的id<br>
	 * <br>
	 * 
	 * expr isBlank，使用原参数args作为内容<br>
	 * 其他情况按,分隔的‘x.y’<br>
	 * x为入参（args）的下标，y为象中的属性（只支持一层）<br>
	 * <br>
	 * 
	 * <pre>
	 * -@CacheClean(@CacheCleanExpr("Store"))
	 * public void update(long id) {
	 * }
	 * 最终生成的KEY为'Store:%id%'
	 * </pre>
	 * 
	 * <pre>
	 * -@CacheClean(@CacheCleanExpr("Store"))
	 * public void update(long id, String name) {
	 * }
	 * 最终生成的KEY为'Store:%id%_%name%'
	 * </pre>
	 * 
	 * <pre>
	 * -@CacheClean(@CacheCleanExpr(value = "Store", expr = "0.id"))
	 * public void update(Store store) {
	 * }
	 * 最终生成的KEY为'Store:%store.id%
	 * </pre>
	 * 
	 * <pre>
	 * -@CacheClean(@CacheCleanExpr(value = "Store", expr = "0.id, 0.dishId"))
	 * public void update(Store store) {
	 * }
	 * 最终生成的KEY为'Store:%store.id%_%store.dishId%
	 * </pre>
	 * 
	 * <pre>
	 * -@CacheClean(@CacheCleanExpr(value = "Store", expr = "0.id, 1.id"))
	 * public void update(Store store, Dish dish) {
	 * }
	 * 最终生成的KEY为'Store:%store.id%_%dish.id%
	 * </pre>
	 * 
	 * <pre>
	 * -@CacheClean(@CacheCleanExpr(value = "Store", expr = "0.id, 1"))
	 * public void update(Store store, long id) {
	 * }
	 * 最终生成的KEY为'Store:%store.id%_%id%
	 * </pre>
	 */
	String expr() default "";

}
