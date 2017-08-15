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

import com.yogu.commons.validation.MinInIntsValidator;

/**
 * 校验 数字 是否大于等于某个值 <br>
 * 默认是否大于等于0<br>
 * 是否设定是否允许null
 *
 * @author JFan 2015年8月20日 下午2:54:15
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = MinInIntsValidator.class)
public @interface MinInRange {
	/** 是否允许为null */
	boolean canNull() default false;

	/** 起始值（含） */
	int min() default 0;
	
	/**
	 * 多语言提示的key
	 */
	String mkey() default "";

	String message() default "number is not legal";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Target({ METHOD, FIELD, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@interface List {
		MinInRange[] value();
	}
}
