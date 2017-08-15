package com.yogu;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共常量
 * 
 * @author linyi 2015/5/29.
 */
public class CommonConstants {

	private static final Logger logger = LoggerFactory.getLogger(CommonConstants.class);

	public static final String MEDIA_TYPE_JSON_UTF8 = "application/json;charset=UTF-8";

	/** 上传的图片最大值，单位 byte */
	public static final int UPLOAD_MAX_PIC_SIZE = 204800;

	/** USER域名 */
	public static final String USER_DOMAIN;

	/** STORE域名 */
	public static final String STORE_DOMAIN;

	/** ORDER域名 */
	public static final String ORDER_DOMAIN;

	/**
	 * 页面引入静态资源的时候，使用的 域名前缀<br>
	 * 只有prod环境会使用固定的 static.mazing.com 域名<br>
	 * 所以，如果你更改了静态资源（webapp/static下的文件）记得使用工具上传最新内容到OSS中，并刷新CDN
	 */
	public static final String STATIC_DOMAIN;

	/**
	 * 字母和数字（区分大小写） 2016/1/11 by ten
	 */
	public static final String LETTER_NUMBER_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	static {
		USER_DOMAIN = findEnvOrDefault("USER_DOMAIN", "http://userapi.internal.mazing.com");
		STORE_DOMAIN = findEnvOrDefault("STORE_DOMAIN", "http://storeapi.internal.mazing.com");
		ORDER_DOMAIN = findEnvOrDefault("ORDER_DOMAIN", "http://orderapi.internal.mazing.com");

		// 只有prod使用CDN，其他的使用本地
		if (GlobalSetting.PROJENV_PROD.equals(GlobalSetting.getProjenv()))
			STATIC_DOMAIN = "https://static.mazing.com";
		else
			STATIC_DOMAIN = "";

	}

	/**
	 * 先尝试从env配置中获取指定的配置，没有则使用默认值
	 */
	private static String findEnvOrDefault(String key, String def) {
		String conf = GlobalSetting.getSysCongfig(key);
		conf = StringUtils.trimToNull(conf);
		if (null != conf) {
			logger.info("system#init | {}域使用指定的配置 | {}", key, conf);
			return conf;
		}
		return def;
	}

	/** 美食状态-下架 */
	public static final short DISH_STATUS_OFF_SHELF = 2;

	/** 美食状态-整除 */
	public static final short DISH_STATUS_NORMAL = 1;

	/** 图片状态-有效 */
	public static final short PIC_EFFECTIVE = 1;

	/** 图片状态-无效 */
	public static final short PIC_NULLITY = 2;

	/**
	 * 验证手机号码合法性正则表达式
	 */
	public static final String PHONE_MATCH = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	/**
	 * 米星管理员uid
	 */
	public static final long MAZING_UID = 0;
	
	/**
	 * 默认的城市code
	 */
	public static final String DEFAULT_CITY = "020";

	/**
	 * 推送内容的长度限制: 76个字符，不区分中英文
	 * 2016/2/3 by ten
	 */
	public static final int MAX_PUSH_MSG_LENGTH = 76;

}
