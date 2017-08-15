package com.yogu.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.validation.constraints.MinInRange;
import com.yogu.language.MultiLanguageAdapter;

/**
 * 校验 数字 数字是否大于最小值 <br>
 * 执行器
 * 
 * @author Hins
 * @date 2015年10月21日 下午10:13:15
 */
public class MinInIntsValidator implements ConstraintValidator<MinInRange, Integer> {
	private int min;
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
	public void initialize(MinInRange a) {
		key = a.mkey();
		this.min = a.min();
		this.canNull = a.canNull();
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
		if (null != num && min <= num)
			return true;
		return false;
	}

}
