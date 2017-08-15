package com.yogu.services.backend.admin.annotation.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志的注解，标识要记录的内容：<br/>
 * <pre>
 *     type:         日志类型，为空时取 MenuResource 的值
 *     args:         要记录的 method 的参数名列表，例如："storeId,uid,status"，用 k1=v1,k2=v2 这样的格式记录到日志
 *     level:        日志级别，默认是 LEVEL_NONE_EXCEPTION
 *     extraMessage: 要记录的额外的日志信息，可以为空
 * </pre>
 * @author ten 2015/10/6.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdminLog {
    /**
     * 常量，表示所有参数
     */
    String ALL = "@all";

    /**
     * 日志级别1：只要没有发生异常，就记录日志。出异常的时候不记录日志.
     */
    int LEVEL_NONE_EXCEPTION = 1;

    /**
     * 日志级别2：只有返回值类型为 RestResult，并且 isSuccess()=true的时候会记录日志
     */
    int LEVEL_ONLY_SUCCESS = 2;

    /**
     * 日志类型，可以为空，比如：创建角色。
     * 当为空时，优先使用注解 {@link com.yogu.commons.utils.resource.MenuResource} 的描述
     * @return
     */
    String type() default "";

    /**
     * 要记录到日志的参数名列表，英文逗号隔开，例如："storeId,uid,name"。
     * 默认是全部，使用 k1=v1,k2=v2 这样的格式记录
     * @return
     */
    String args() default ALL;

    /**
     * 日志级别，LEVEL_NOT_EXCEPTION 或 LEVEL_ONLY_SUCCESS
     * @return
     */
    int level() default LEVEL_NONE_EXCEPTION;

    /**
     * 额外的 log 信息，默认是空
     * @return
     */
    String extraMessage() default "";
}
