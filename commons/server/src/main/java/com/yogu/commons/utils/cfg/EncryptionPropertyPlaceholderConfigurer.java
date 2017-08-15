package com.yogu.commons.utils.cfg;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.yogu.commons.utils.cfg.PropertiesEncoder;

/**
 * properties文件加密、解密类
 * 
 * @author tendy
 */
public class EncryptionPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private PropertiesEncoder encoder;

	private static Map<String, String> propMap = new HashMap<>();

	public PropertiesEncoder getEncoder() {
		return encoder;
	}

	public void setEncoder(PropertiesEncoder encoder) {
		this.encoder = encoder;
	}

	@Override
	protected void convertProperties(Properties props) {
		convertProp(encoder, props);
		super.convertProperties(props);
	}

	public static void convertProp(PropertiesEncoder encoder, Properties props) {
		Enumeration<?> propertyNames = props.propertyNames();
		while (propertyNames.hasMoreElements()) {
			String propertyName = (String) propertyNames.nextElement();
			String lowerName = propertyName.toLowerCase();
			if (lowerName.indexOf("username") >= 0 //
					|| lowerName.indexOf("password") >= 0 //
					|| lowerName.indexOf("accesskey") >= 0 //
					|| lowerName.indexOf("accesssecret") >= 0 //
					|| lowerName.indexOf("certsecret") >= 0 //
					|| lowerName.indexOf("alipay") >= 0 //
					|| lowerName.indexOf("appid") >= 0 //
					|| lowerName.indexOf("appsecret") >= 0 //
					|| lowerName.indexOf("cardid") >= 0 //
					|| lowerName.indexOf("wechat") >= 0 //
					|| lowerName.indexOf("rediscluster") >= 0 //
					|| lowerName.indexOf("singleredis") >= 0 //
					|| lowerName.indexOf("restkey") >= 0 //
					|| lowerName.indexOf("pushappkey") >= 0 //
					|| lowerName.indexOf("pushappmastersecret") >= 0) {
				String propertyValue = props.getProperty(propertyName);
				if (propertyValue == null) {
					propertyValue = "";
				}
				String decodedValue = encoder.decode(propertyValue);
				props.setProperty(propertyName, decodedValue);
			}
			propMap.put(propertyName, props.getProperty(propertyName));
		}
	}

	public static Map<String, String> getConfig() {
		return propMap;
	}

	public static String getConfig(String key) {
		return propMap.get(key);
	}

}
