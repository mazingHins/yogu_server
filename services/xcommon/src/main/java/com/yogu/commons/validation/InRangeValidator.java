/**
 * 
 */
package com.yogu.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.validation.constraints.InRange;
import com.yogu.language.MultiLanguageAdapter;

/**
 * 校验 数字 必须在指定的反问内（闭区间） <br>
 * 执行器
 *
 * @author JFan 2015年8月20日 下午2:54:09
 */
public class InRangeValidator implements ConstraintValidator<InRange, Integer> {

	private int min;
	private int max;
	private boolean canNull;

	/**
	 * 多语言提示的key
	 */
	private String key;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	public void initialize(InRange a) {
		key = a.mkey();

		this.min = a.min();
		this.max = a.max();
		this.canNull = a.canNull();

		// 大小互换
		if (min > max) {
			this.max = a.min();
			this.min = a.max();
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	public boolean isValid(Integer num, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isNotBlank(key)) {
			String msg = MultiLanguageAdapter.fetchMessage(key);// 获取多语言提示
			constraintValidatorContext.disableDefaultConstraintViolation();// 禁用默认的message信息
			constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation(); // 重新添加错误提示信息
		}

		if (null == num && canNull)
			return true;
		if (null != num && min <= num && num <= max)
			return true;
		return false;
	}

}
