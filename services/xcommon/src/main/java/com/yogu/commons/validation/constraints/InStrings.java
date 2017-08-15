/**
 * 
 */
package com.yogu.commons.validation.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.yogu.commons.validation.InStringsValidator;

/**
 * 验证字符串是在指定列表中 <br>
 * 多值约束
 * 
 * JFan 2014年12月10日 下午3:14:59
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InStringsValidator.class)
public @interface InStrings {

	/**
	 * 比较的值 列表<br>
	 * 为空时，永远返回false
	 */
	String[] value();
	
	/**
	 * 多语言提示的key
	 */
	String mkey() default "";

	/**
	 * 是否忽略大小写
	 */
	boolean ignoreCase() default false;

	String message() default "this pattern may not be right";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@interface List {
		InStrings[] value();
	}

}
