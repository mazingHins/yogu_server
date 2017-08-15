package com.yogu.commons.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectConvertMapUtil {

	private static final Logger logger = LoggerFactory.getLogger(ObjectConvertMapUtil.class);

	/**
	 * object对象转为map对象
	 * @param object
	 * @return
	 * @throws Exception    
	 * @author east
	 * @date 2017年4月5日 下午6:27:24
	 */
	public static Map<String, String> toMapString(Object object) {
		Map<String, String> map = new HashMap<>();

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_mm_ss);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();
					Object value = (getter != null ? getter.invoke(object) : null);
					if (value != null) {
						if (value instanceof Date) {
							map.put(key, sdf.format((Date) value));
						} else {
							map.put(key, String.valueOf(value));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("ObjectConvertMapUtil#toMapString object转map错误 | message:{}", e.getMessage(), e);
		}
		return map;
	}
}
