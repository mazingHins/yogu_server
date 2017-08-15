package com.mazing.test.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mazing.test.core.KeyValue;

/**
 * 参数判断的工具类
 * 
 * @author linyi 2014年3月21日
 */
public class ParameterUtil {

	public static String readJsonParam(String json, String name) {
		KeyValue<Integer, String> v = readJsonParam(json, 0, name);
		return (null == v ? null : v.getValue());
	}

	public static String[] readJsonParams(String json, String name) {
		List<String> result = new ArrayList<>();

		int index = 0;
		KeyValue<Integer, String> v = null;
		while (null != (v = readJsonParam(json, index, name))) {
			result.add(v.getValue());
			index = v.getKey();
		}

		return result.toArray(new String[result.size()]);
	}

	public static KeyValue<Integer, String> readJsonParam(String json, int fromIndex, String name) {
		if (null == json)
			return null;

		int i = json.indexOf(name, fromIndex);
		if (-1 >= i)
			return null;

		int j = name.length() + i + 2;

		int k1 = json.indexOf(',', j);
		int k2 = json.indexOf('}', j);
		int end = (k1 < k2 ? k1 : k2);
		String str = json.substring(j, end);

		if (str.startsWith("\"") && str.endsWith("\""))
			str = str.substring(1, str.length() - 1);

		return new KeyValue<>(end, str);
	}

	/**
	 * 按指定的字符分隔字符串，转换成String[]<br>
	 * 忽略blank的
	 */
	public static String[] str2strs(String str, char separatorChar) {
		String string = StringUtils.trimToNull(str);
		if (null == string)
			return null;

		String[] split = StringUtils.split(string, separatorChar);
		List<String> list = new ArrayList<String>();
		for (String s : split) {
			String tmp = StringUtils.trimToNull(s);
			if (null == tmp)
				continue;
			list.add(tmp);
		}

		return list.toArray(new String[list.size()]);
	}

	/**
	 * 将英文逗号分隔的字符串，转换成String[]<br>
	 * 忽略blank的
	 */
	public static String[] str2strs(String str) {
		return str2strs(str, ',');
	}
	
	/**
	 * 将英文逗号分隔的字符串，转换成int[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static int[] str2ints(String str) {
		String[] strs = str2strs(str);
		if (null == strs)
			return null;

		int[] ls = new int[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Integer.parseInt(tmp);
		return ls;
	}

	/**
	 * 将英文逗号分隔的字符串，转换成long[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static long[] str2longs(String str) {
		String[] strs = str2strs(str);
		if (null == strs)
			return new long[0];

		long[] ls = new long[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Long.parseLong(tmp);
		return ls;
	}
	
	/**
	 * 将英文逗号分隔的字符串，转换成long[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static short[] str2shorts(String str) {
		String[] strs = str2strs(str);
		if (null == strs)
			return null;

		short[] ls = new short[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Short.parseShort(tmp);
		return ls;
	}

	/**
	 * 将英文逗号分隔的字符串，转换成double[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static double[] str2double(String str) {
		String[] strs = str2strs(str);
		if (null == strs)
			return null;

		double[] ls = new double[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Double.parseDouble(tmp);
		return ls;
	}

}
