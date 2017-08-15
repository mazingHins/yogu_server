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

import com.yogu.commons.validation.InIntsValidator;

/**
 * 校验 数字 必须是指定的内容之一 <br>
 * 支持设置是否允许null
 *
 * @author JFan 2015年8月20日 下午2:47:59
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InIntsValidator.class)
public @interface InInts {

	/** 是否允许为null */
	boolean canNull() default false;

	/**
	 * 指定的值 列表<br>
	 * 为空时，永远返回false
	 */
	int[] value();
	
	/**
	 * 多语言提示的key
	 */
	String mkey() default "";

	String message() default "not legal number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@interface List {
		InInts[] value();
	}

}
