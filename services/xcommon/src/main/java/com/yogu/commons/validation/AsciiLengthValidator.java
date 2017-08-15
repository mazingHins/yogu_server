package com.yogu.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.WordCountUtils;
import com.yogu.commons.validation.constraints.AsciiLength;
import com.yogu.language.MultiLanguageAdapter;

/**
 * 校验字符串ascii长度 <br>
 * 执行器
 *
 * @author JFan 2015年12月23日 下午12:19:47
 */
public class AsciiLengthValidator implements ConstraintValidator<AsciiLength, String> {

	private boolean canEmpty;
	private boolean trim;
	private int min;
	private int max;
	
	/**
	 * 多语言提示的key
	 */
	private String key;

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	public void initialize(AsciiLength l) {
		key = l.mkey();
		this.canEmpty = l.canEmpty();
		this.trim = l.trim();
		this.min = l.min();
		this.max = l.max();

		// 大小互换
		if (min > max) {
			this.max = l.min();
			this.min = l.max();
		}
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
		
		// trim
		String str = (trim ? StringUtils.trim(string) : string);
		// 不允许为空，但是却为null
		if (null == str && !(canEmpty))
			return false;
		// 允许empty
		if (StringUtils.isEmpty(str) && canEmpty)
			return true;
		// check
		int length = WordCountUtils.getWordCount(str);
		if (min <= length && length <= max)
			return true;
		return false;
	}

}
