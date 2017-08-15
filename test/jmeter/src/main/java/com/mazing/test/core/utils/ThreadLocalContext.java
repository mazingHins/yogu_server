/**
 * 
 */
package com.mazing.test.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取当前线程中存储的上下文内容
 * 
 * @author jfan 2016年10月13日 下午4:04:33
 */
public class ThreadLocalContext {

	private static final ThreadLocal<Map<String, Object>> local = new ThreadLocal<Map<String, Object>>() {
		protected Map<String, Object> initialValue() {
			return new HashMap<>();
		}
	};

	public static void putThreadValue(String key, Object value) {
		local.get().put(key, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getThreadValue(String key) {
		return (T) local.get().get(key);
	}

}
