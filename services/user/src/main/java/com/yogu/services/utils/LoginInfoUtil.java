package com.yogu.services.utils;

import com.yogu.commons.utils.Validator;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.UserMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginInfoUtil {

	/**
	 * key为被禁用的密码、value为一个存在的值的键值对map
	 */
	private static Map<String, Integer> forbidMap = new HashMap<String, Integer>();
	/**
	 * 被禁用的密码的串, 英文逗号‘,’ 拼接
	 */
	private static final String forbidListStr = "123456,1234567,12345678,123456789,1234567890,0123456789,654321,7654321,87654321,987654321,0987654321,9876543210,abcdef,abcdefg,qwerty,qwertyu,asdfgh,asdfghj,zxcvbn,zxcvbnm";

	static {
		String[] passArr = forbidListStr.split(",");
		for (int i = 0; i < passArr.length; i++) {
			forbidMap.put(passArr[i], i);
		}
	}

	/**
	 * 密码检查(长度,组合,强度校验)
	 * 
	 * @param password 明文密码
	 * @author sky
	 * 
	 * @return 不符合业务要求的将抛出异常并提示相应的内容
	 * 
	 */
	@Deprecated
	public static void passwordCheck(String password) {

		ParameterUtil.assertNotBlank(password, "请输入密码");

		String blankRex = "(?=.*\\s)+.*";// 是否含有空白字符
		if (match(blankRex, password))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码不能含有空格");

		String chineseRex = ".*[\u4E00-\u9FA5].*";
		if (match(chineseRex, password))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码为8-16位字母、数字或字符，至少包含两种");

		if (password.length() < 8)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码位数不足,请输入8-16个字符");

		if (password.length() > 16)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码位数过长,请输入8-16个字符");

		String rex1 = "(?=.*[A-Z])+.*";// 大写字母
		String rex2 = "(?=.*[a-z])+.*";// 小写字母
		String rex3 = "(?=.*\\d)+.*";// 包含数字
		String rex4 = "(?=.*[^\\da-zA-Z])+.*";// 非字母数字的其它字符

		int matchCount = 0;

		if (match(rex1, password))// 是否包含大小字母
			matchCount = matchCount + 1;
		if (match(rex2, password))// 是否包含小写字母
			matchCount = matchCount + 1;
		if (match(rex3, password))// 是否包含数字
			matchCount = matchCount + 1;
		if (match(rex4, password))// 是否包含特殊字符
			matchCount = matchCount + 1;

		if (matchCount < 2)// 至少是以上4种中 两种的组合
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码为8-16位字母、数字或字符，至少包含两种");
		// else
		// System.out.println("ok");
	}

	/**
	 * 密码检查 1, 长度 8-16 2, 不能纯数字 3, 不能纯小写字母 4, 不能纯大写字母
	 * 
	 * @param password 明文密码
	 * @author felix
	 * 
	 * @return 不符合业务要求的将抛出异常并提示相应的内容
	 * 
	 */
	@Deprecated
	public static void passwordCheck2(String password) {
		ParameterUtil.assertNotBlank(password, "请输入密码");

		if (password.length() < 8)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码位数不足,请输入8-16个字符");

		if (password.length() > 16)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码位数过长,请输入8-16个字符");

		if (Validator.isNumber(password))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码不能是纯数字，请使用更复杂的密码，提高安全性");

		if (Validator.islowerCase(password))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码不能是纯小写字母，请使用更复杂的密码，提高安全性");

		if (Validator.isUpperCase(password))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "密码不能是纯大写字母，请使用更复杂的密码，提高安全性");
	}

	/**
	 * 对注册用户的密码、修改密码、重置密码 进行密码校验
	 * 
	 * 1. 长度为 6~20位 2. 成员变量 forbidListStr 中的字符除外(不可以为密码) 3. 不可以全由相同字符组成
	 * 
	 * @param password 待检测的明文密码
	 * @return 不符合条件的将抛出异常提示
	 * @author sky 2016-03-09
	 */
	public static void passwordCheck3(String password) {
		ParameterUtil.assertNotBlank(password, UserMessages.USER_SERVICE_DOLOGIN_PASSWORD_CAN_NOT_BE_EMPTY());

		// 改回8位 2016/3/16 by ten
		if (password.length() < 8)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_PASSWORD_TOO_SHORT());

		if (password.length() > 20)
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_PASSWORD_TOO_LONG());

		if (Validator.isSameSymbol(password) || forbidMap.containsKey(password))// 相同字符组成的串 or 在禁用的列表中 都不通过校验
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_PASSWORD_TOO_SIMPLE());
	}

	/**
	 * 手机号检查, 不符合业务要求的将抛出异常
	 * 
	 * @param mobile
	 */
	public static void mobileCheck(String mobile) {

		ParameterUtil.assertNotBlank(mobile, UserMessages.USER_LOGIN_MOBILE_CAN_NOT_BE_EMPTY());

		if (!Validator.isMobileNo(mobile))
			throw new ServiceException(ResultCode.PARAMETER_ERROR, UserMessages.USER_ADDRESS_SAVE_ADDRESS_PHONE_INVALID());
	}

	private static boolean match(String regex, String str) {

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	public static void main(String[] args) {

		String password = "111111a号";// "123456789";

		// String regex = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,16}";
		//
		// String rex2 = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{8,16}$";// 字母数字的组合,可以有特殊字符
		//
		String v1 = "(?=.*[A-Z])+.*";// 大写字母
		String v2 = "(?=.*[a-z])+.*";// 小写字母
		String v3 = "(?=.*\\d)+.*";// 包含数字
		String v4 = "(?=.*[^\\da-zA-Z])+.*";// 非字母数字的其它字符

		// "(.*(?=.*[^a-zA-Z0-9])+.*)";//"(.*(?=[\\x21-\\x7e])[^A-Za-z0-9])+.*";//"(?=.*[^a-zA-Z0-9])";//
		// 特殊字符

		// String blankRex = "(?=.*\\s)+.*";

		System.out.println(match(v1, password));
		System.out.println(match(v2, password));
		System.out.println(match(v3, password));
		System.out.println(match(v4, password));

		// String rex = "(.*(?=[\\x21-\\x7e]+)[^A-Za-z0-9]).*";

		String rex = ".*[\u4E00-\u9FA5]+.*";
		System.out.println(match(rex, password));

		// passwordCheck(password);

		String haha = "5566655522";
		// String regex = haha.substring(0, 1) + "{" + haha.length() + "}";
		// System.out.println("all equals ?" + isSameSymbol(haha));

		System.out.println(forbidMap);
		passwordCheck3(haha);

	}
}
