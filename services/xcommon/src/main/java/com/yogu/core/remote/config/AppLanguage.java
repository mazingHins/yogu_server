/**
 * 
 */
package com.yogu.core.remote.config;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * 多语言定义<br>
 *
 * @author JFan 2016年3月18日 上午11:13:15
 */
public enum AppLanguage {

	// http://www.lingoes.cn/zh/translator/langcode.htm

	zh(Locale.CHINESE, "中文", "Chinese") //
	// , ZH_CN(Locale.SIMPLIFIED_CHINESE, "中文(简体)", "Chinese (Simplified)") //

	, en(Locale.ENGLISH, "英文", "English") //
	// , EN_US(Locale.US, "英文(美国)", "English (US)") //
	;

	/** 语言code */
	private String code;
	/** 该语言的中文名称 */
	private String zhName;
	/** 该语言的英文名称 */
	private String enName;

	private Locale locale;

	private AppLanguage(Locale locale, String zhName, String enName) {
		this.locale = locale;
		this.zhName = zhName;
		this.enName = enName;
		// 2 code
		String c = locale.getLanguage();
		String country = StringUtils.trimToNull(locale.getCountry());
		if (null != country)
			c += ("-" + country);
		this.code = c;
	}

	// ##

	/**
	 * 系统默认的语言
	 */
	public static AppLanguage getDefault() {
		return zh;
	}

	/**
	 * 根据code反查AppLanguage对象<br>
	 * 如果没找到，则返回null
	 * 
	 * 暂时不开放出去，避免用错
	 */
	public static AppLanguage giveLanguageByCode(String code) {
		for (AppLanguage al : values())
			if (al.code.equals(code))// 暂时区分大小写
				return al;
		return null;
	}

	/**
	 * 根据code（区分大小写）查询 AppLanguage 对象<br>
	 * 如果没有找到，则返回默认语言（中文）
	 */
	public static AppLanguage giveLanguageOrDef(String code) {
		AppLanguage al = giveLanguageByCode(code);
		if (null == al)
			al = getDefault();
		return al;
	}

	// ####

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return zhName
	 */
	public String getZhName() {
		return zhName;
	}

	/**
	 * @return enName
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * @return locale
	 */
	public Locale getLocale() {
		return locale;
	}

}
