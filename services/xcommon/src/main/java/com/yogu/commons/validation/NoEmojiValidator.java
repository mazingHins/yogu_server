package com.yogu.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.Validator;
import com.yogu.commons.validation.constraints.NoEmoji;
import com.yogu.language.MultiLanguageAdapter;

/**
 * 验证字符串不含有emoji表情 <br>
 * 执行器
 * 
 * @author sky
 */
public class NoEmojiValidator implements ConstraintValidator<NoEmoji, String> {

	/**
	 * 多语言提示的key
	 */
	private String key;
	
	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	public void initialize(NoEmoji parameters) {
		key = parameters.mkey();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
		
		if (StringUtils.isNotBlank(key)) {
			String msg = MultiLanguageAdapter.fetchMessage(key);// 获取多语言提示
			constraintValidatorContext.disableDefaultConstraintViolation();// 禁用默认的message信息
			constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation(); // 重新添加错误提示信息
		}
		return !Validator.hasEmoji(string);

	}

}
