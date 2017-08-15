package com.yogu.language;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.StringUtils;
import com.yogu.core.remote.config.AppLanguage;
import com.yogu.core.web.context.SecurityContext;

/**
 * 
 * 多语言提示获取类， 该类负责加载配置的语言提示至内存
 * 
 * @author sky 2016-03-24
 *
 */
public class MultiLanguageAdapter {

	private static final Logger logger = LoggerFactory.getLogger(MultiLanguageAdapter.class);

	/**
	 * 中文语言资源
	 */
	private static Properties ZH_PRO = new Properties();
	/**
	 * 英文语言资源
	 */
	private static Properties EN_PRO = new Properties();

	static {

		// 加载中文提示
		InputStream zh = MultiLanguageAdapter.class.getResourceAsStream("/META-INF/lang_zh_CN.properties");
		// 加载英文提示
		InputStream en = MultiLanguageAdapter.class.getResourceAsStream("/META-INF/lang_en_US.properties");

		try {

			ZH_PRO.load(zh);

			EN_PRO.load(en);

		} catch (Exception e) {
			logger.error("MultiLanguage#load | 加载语言文件发生错误 | zh_CN: {}, en_US: {}", zh, en, e.getMessage(), e);
		}
	}

	/**
	 * 
	 * 
	 * 
	 * 根据对应的提示的key获取符合语言环境的提示信息，调用者不需要关心语言环境
	 * 
	 * @param key 提示所对应的key值
	 * @param args 传入提示语中的参数，可以是多个参数，对应提示语相应位置的参数(注意：最好是参数的英文名)
	 * @return 对应语言的提示
	 * @author sky 2016-03-24
	 */
	public static String fetchMessage(String key, Object... args) {

		Object messageValue = null;
		// 最终message
		String message = "";
		// 语言代码
		AppLanguage appLanguage = SecurityContext.getAppLanguage();

		if (AppLanguage.en.equals(appLanguage))
			messageValue = EN_PRO.get(key);
		else
			messageValue = ZH_PRO.get(key);

		if (messageValue != null) {

			message = messageValue.toString().trim();
			message = MessageFormat.format(message, args);
		} else {
			logger.error("MultiLanguage#getMessage |  获取提示失败, 不存在key所对应的语言提示 | key: {}, appLang: {}, args: {}", key, appLanguage, args);
		}

		return message;
	}

	public static void main(String[] args) {
		// System.out.println(MultiLanguageAdapter.class.getResource(""));
		// System.out.println(MultiLanguageAdapter.class.getResource("/"));
		// System.out.println(MultiLanguageAdapter.class.getClassLoader().getResource(""));
		// System.out.println(MultiLanguageAdapter.class.getClassLoader().getResource("/"));
		// System.out.println(MultiLanguageAdapter.class.getResource("lang_ZH.properties"));
		// System.out.println(MultiLanguageAdapter.class.getResourceAsStream("/META-INF/lang_ZH.properties"));

		System.out.println(ConfigMessages.BASE_GETNEXTID_IDNAME_CAN_NOT_BE_EMPTY());

	}
	
	/**
	 * 根据语言版本，返回对应版本的名称
	 * 如果是英文，返回英文，如果英文为空，返回中文
	 * 否则返回中文
	 * @param enName - 英文
	 * @param cnName - 中文
	 * @author hins
	 * @date 2017年1月3日 下午2:12:19
	 * @return String
	 */
	public static String getNameByLanguage(String enName, String cnName) {
		// 语言代码
		AppLanguage appLanguage = SecurityContext.getAppLanguage();

		if (AppLanguage.en.equals(appLanguage))
			return StringUtils.isBlank(enName) ? cnName : enName;
		else
			return cnName;
	}

}
