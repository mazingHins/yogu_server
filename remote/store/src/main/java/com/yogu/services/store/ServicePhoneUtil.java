package com.yogu.services.store;

import com.yogu.commons.utils.StringUtils;

/**
 * 客服电话号码工具类
 * 
 * @author sky 2016-01-29
 *
 */
public class ServicePhoneUtil {

	/**
	 * 分隔符
	 */
	public static final String SPLIT_REG = ",";

	/**
	 * 获取一个客服电话号码
	 * 
	 * @param phones DB中存在的客服电话号码,可以是多个号码组成的串,英文逗号','分隔
	 * @return 空字符串 or 第一个客服号码
	 * @author sky
	 */
	public static String getOne(String phones) {

		if (StringUtils.isBlank(phones))
			return "";

		if (!phones.contains(SPLIT_REG))
			return phones;

		String[] phoneArr = phones.split(SPLIT_REG);

		return phoneArr[0];

	}

}
