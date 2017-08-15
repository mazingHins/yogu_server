/**
 * 
 */
package com.yogu.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.validation.constraints.InInts;
import com.yogu.language.MultiLanguageAdapter;

/**
 * 校验 数字 必须是指定的内容之一 <br>
 * 执行器
 * 
 * @author JFan 2015年8月20日 下午2:53:07
 */
public class InIntsValidator implements ConstraintValidator<InInts, Integer> {

	private int[] values;
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
	public void initialize(InInts a) {
		key = a.mkey();
		this.values = a.value();
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
		if (null != num && ArrayUtils.isNotEmpty(values))
			for (int value : values)
				if (value == num)
					return true;
		return false;
	}

}
