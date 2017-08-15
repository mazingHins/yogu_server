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

import com.yogu.commons.validation.LengthValidator;

/**
 * 验证字符串是否在限定的长度范围内 <br>
 *
 * @author JFan 2015年9月2日 上午10:28:45
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { LengthValidator.class })
public @interface Length {

	/** 是否允许空数据（null & ‘’） */
	boolean canEmpty() default false;

	/**
	 * 是否去除两端的空格，在校验长度<br>
	 * 注意：只是在校验的时候去除两端空格，你实际拿到的数据是没有去除两端空格的
	 */
	boolean trim() default false;
	
	/**
	 * 多语言提示的key
	 */
	String mkey() default "";

	/** 最少长度 */
	int min() default 0;

	/** 最大长度 */
	int max() default Integer.MAX_VALUE;

	// long max() default Long.MAX_VALUE;

	String message() default "长度不在限制的范围内";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Defines several {@link Length} annotations on the same element.
	 */
	@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
	@Retention(RUNTIME)
	@Documented
	@interface List {

		Length[] value();
	}
}
