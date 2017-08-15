package com.yogu.commons.utils.encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public final class SignUtils {
	// private static final Logger logger =
	// LoggerFactory.getLogger(SignUtils.class);

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
	 * 拼接签名源
	 */
	public static String signSourceMap(Map<String, String> params) {
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.putAll(params);
		StringBuilder sb = new StringBuilder();
		for (String value : map.values())
			sb.append(null == value ? "" : value);
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
		if (ArrayUtils.isEmpty(vs))
			return defValue;
		// logger.info("#Sign params# show parameter | key: {}, value: {}",
		// paramName, StringUtils.join(vs));
		return StringUtils.join(vs);
	}

	// ####

	public static void main(String[] args) {
		SignUtils s = new SignUtils();

		String url = "/s/v1/dish/list?sname=android_4.3&sver=18&aname=android_mx&aver=1.7.1.47&did=068662326fdf7ceacdf88b9f1a30a0a3&akey=7c3ffaa8c8c5486989d6958ee08ddda1&citycode=020&t=1458522022&ut=10abd160689972b18b394dabb8d3d1aa2b08ac29&storeId=17501&sign=20dbede6c0930918c0327c4d7e23e941c1c24799";
		System.out.println(s.vali(url));
	}

	private boolean outMsg = true;

	private boolean vali(String url) {
		Map<String, String[]> params = toParams(url);
		String secret = secret(getValue(params, "akey"));
		String sign = getValue(params, "sign");
		params.remove("sign");
		println("请求者签名：" + sign);

		String source = signSource(params);
		String sign2 = signHmacSha1(source, secret);
		println("计算出签名：" + sign2);
		return sign.equals(sign2);
	}

	private String secret(String key) {
		if (StringUtils.isNotBlank(key)) {
			if ("ddd270dc3a614da98b30da843091ae9b".equals(key))
				return "00441643c045422a8b98b0ef4dbe2445";
			if ("e515dc06e13b4ea98c91e7132e98e319".equals(key))
				return "6a194dc7e44b49f4b731a1e92423cffd";
			if ("13a9b526b3d44bdd8d1553346ebcf9b8".equals(key))
				return "D5EFAEE2D37B423AAA43AC67B5EDC3FB";
			if ("7c3ffaa8c8c5486989d6958ee08ddda1".equals(key))
				return "5B073EC6DB1A450598E5E5B4BAEBAD1D";
		}
		throw new RuntimeException("未知的akey:" + key);
	}

	private Map<String, String[]> toParams(String url) {
		Map<String, String[]> params = new HashMap<String, String[]>();
		if (StringUtils.isNoneBlank(url)) {
			String paramStr = StringUtils.split(url, '?')[1];
			String[] ps = StringUtils.split(paramStr, '&');
			for (String p : ps) {
				String[] kv = StringUtils.split(p, '=');
				String key = kv[0];
				String value = (1 < kv.length ? kv[1] : "");
				if (StringUtils.isBlank(value))
					println("\t>>>>WARN: value is bland");

				String[] v = params.get(key);
				if (null == v)
					v = new String[] { value };
				else {
					v = Arrays.copyOf(v, v.length + 1);
					v[v.length - 1] = value;
				}
				params.put(key, v);
			}
		}
		return params;
	}

	private String getValue(Map<String, String[]> params, String key) {
		if (null == params)
			return null;
		String[] vs = params.get(key);
		return (ArrayUtils.isEmpty(vs) ? null : vs[0]);
	}

	private void println(String msg) {
		if (outMsg)
			System.out.println(msg);
	}

	/**
	 * 把request请求的参数转为字符串，格式为key1=value1&key2=value2
	 */
	public static String signMap(Map<String, String> params) {
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.putAll(params);

		StringBuilder sb = new StringBuilder();

		List<String> keys = new ArrayList<String>(map.keySet());
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);
			if (StringUtils.isNotBlank(value)) {
				sb.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
		}
		return sb.toString();
	}

	private static String toSnakeCase(String str) {
		StringBuilder columnName = new StringBuilder();
		int l = str.length();
		for (int i = 0; i < l; i++) {
			char c = str.charAt(i);
			if (i > 0 && Character.isUpperCase(c)) {
				columnName.append("_");
				columnName.append(c);
			} else {
				columnName.append(c);
			}
		}
		return columnName.toString().toLowerCase();
	}
}
