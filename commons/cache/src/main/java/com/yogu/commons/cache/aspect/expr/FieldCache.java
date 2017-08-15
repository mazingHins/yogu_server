/**
 * 
 */
package com.yogu.commons.cache.aspect.expr;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 从对象中读取 field <br>
 * 反射get方法<br>
 * 
 * 反射到的Method对象会缓存
 *
 * @author JFan 2015年8月21日 下午5:13:51
 */
public class FieldCache {

	/**
	 * className.field作为KEY<br>
	 * field的get方法作为VALUE
	 */
	private static final Map<String, Method> methodCache = new ConcurrentHashMap<>();

	/**
	 * 读取arg对象中的field属性值<br>
	 * arg为null时，返回null<br>
	 * 
	 * 异常直接往外层抛
	 */
	public static Object readField(Object arg, String field) throws Exception {
		if (null == arg)
			return null;

		// to key
		Class<? extends Object> clz = arg.getClass();
		String key = clz.getName() + "." + field;

		// 取得 get方法
		Method getMethod = methodCache.get(key);
		if (null == getMethod) {
			BeanInfo beanInfo = Introspector.getBeanInfo(clz, Object.class);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (ArrayUtils.isNotEmpty(propertyDescriptors))
				for (PropertyDescriptor pd : propertyDescriptors)
					if (pd.getName().equals(field)) {
						getMethod = pd.getReadMethod();
						break;
					}

			if (null == getMethod)
				throw new Exception("not found '" + field + "' readMethod by " + clz);

			methodCache.put(key, getMethod);
		}

		// 执行get方法 拿到结果
		return getMethod.invoke(arg);
	}

}
