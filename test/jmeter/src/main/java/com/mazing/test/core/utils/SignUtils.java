package com.mazing.test.core.utils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.mazing.test.core.des.HMacSHA1;

public final class SignUtils {
	//private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);

	/**
	 * 拼接签名源
	 */
	public static String signSource(Map<String, String[]> params) {
		SortedMap<String, String[]> map = new TreeMap<String, String[]>();
		map.putAll(params);
		StringBuilder sb = new StringBuilder();
		for (String key : map.keySet())
			sb.append(getParam(map, key, ""));
		return sb.toString();
	}

	/**
	 * 计算签名
	 */
	public static String signHmacSha1(String source, String secret) {
		String sign = HMacSHA1.getSignature(source, secret);
		return sign;
	}

	/**
	 * 从params中读取参数，如果有多值，则无缝拼接
	 */
	public static String getParam(Map<String, String[]> params, String paramName, String defValue) {
		String[] vs = params.get(paramName);
		if (null == vs || 0 >= vs.length)
			return defValue;
		// logger.info("#Sign params# show parameter | key: {}, value: {}", paramName, StringUtils.join(vs));
		StringBuilder sb = new StringBuilder();
		for (String v : vs)
			sb.append(v);
		return sb.toString();
	}

}
