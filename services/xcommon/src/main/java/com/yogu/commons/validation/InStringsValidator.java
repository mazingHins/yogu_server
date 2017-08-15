/**
 * 
 */
package com.yogu.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.validation.constraints.InStrings;
import com.yogu.language.MultiLanguageAdapter;

/**
 * 验证字符串是在指定列表中 <br>
 * 执行器
 * 
 * JFan 2014年12月10日 下午3:16:07
 */
public class InStringsValidator implements ConstraintValidator<InStrings, String> {

	private String[] values;
	private boolean ignoreCase;
	/**
	 * 多语言提示的key
	 */
	private String key;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	public void initialize(InStrings a) {
		key = a.mkey();
		this.values = a.value();
		this.ignoreCase = a.ignoreCase();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
		if (StringUtils.isNotBlank(key)) {
			String msg = MultiLanguageAdapter.fetchMessage(key);// 获取多语言提示
			constraintValidatorContext.disableDefaultConstraintViolation();// 禁用默认的message信息
			constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation(); // 重新添加错误提示信息
		}
		
		if (ArrayUtils.isNotEmpty(values))
			for (String value : values)
				if (ignoreCase) {
					if (value.equalsIgnoreCase(string))
						return true;
				} else {
					if (value.equals(string))
						return true;
				}
		return false;
	}

}
