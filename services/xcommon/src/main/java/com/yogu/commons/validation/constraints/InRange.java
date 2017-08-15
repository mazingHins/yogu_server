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

import com.yogu.commons.validation.InRangeValidator;

/**
 * 校验 数字 必须在指定的反问内（闭区间） <br>
 * 默认是判定[0 ~ INT_MAX]闭区间；也就是大于等于0<br>
 * 是否设定是否允许null
 *
 * @author JFan 2015年8月20日 下午2:54:15
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = InRangeValidator.class)
public @interface InRange {

	/** 是否允许为null */
	boolean canNull() default false;

	/** 起始值（含） */
	int min() default 0;

	/** 最大值（含） */
	int max() default Integer.MAX_VALUE;
	
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
		InRange[] value();
	}

}
