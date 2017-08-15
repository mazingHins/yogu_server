/**
 * 
 */
package com.mazing.test.runtime;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 读取mazing.conf配置文件的类
 * 
 * @author jfan 2016年9月29日 上午11:16:39
 */
public class Config {

	private static Random r = new Random();
	private static Map<String, String[]> cache = new HashMap<>();

	/**
	 * 获取两个分段之间（包括空白值）随机一个配置值
	 */
	public static String giveRandomByOriginal(String group) {
		String[] vs = cache.get("original_" + group);
		return random(vs);
	}

	/**
	 * 获取两个分段之间（包括空白值）去重之后的随机一个配置值
	 */
	public static String giveRandomBySet(String group) {
		String[] vs = cache.get("setBlank_" + group);
		return random(vs);
	}

	/**
	 * 获取两个分段之间（包括空白值）去重之后（不含blank）的随机一个配置值
	 */
	public static String giveRandomBySetNotBlank(String group) {
		String[] vs = cache.get("setNotBlank_" + group);
		return random(vs);
	}

	public static String[] giveAllNotBlank(String group) {
		return cache.get("setNotBlank_" + group);
	}

	private static String random(String[] vs) {
		if (ArrayUtils.isEmpty(vs))
			return null;
		int index = r.nextInt(vs.length);
		return vs[index];
	}

	// ####
	// #### 读取配置文件
	// ####

	// 这3个map是临时的
	private static Map<String, Set<String>> setNotBlank = new HashMap<>();
	private static Map<String, List<String>> original = new HashMap<>();
	private static Map<String, Set<String>> setBlank = new HashMap<>();
	static {
		init("/mazing.conf");
	}

	private static void init(String path) {
		try {
			log("read start ...");
			// 读取资源文件
			InputStream is = Config.class.getResourceAsStream(path);
			if (null == is)
				throw new RuntimeException("无法读取资源文件：" + path);

			List<String> lines = IOUtils.readLines(is, "UTF-8");
			log("lines: " + lines.size());

			String group = null;
			for (String line : lines) {
				String str = StringUtils.trimToEmpty(line);
				if (str.startsWith("#"))
					continue;

				// 是否分组[G] -> G
				if (str.startsWith("[") && str.endsWith("]")) {
					group = str.substring(1, str.length() - 1);
					log("发现分组：'" + group + "'");
					initGroup(group);
					continue;
				}

				// 在读到分组之前，忽略配置
				if (null == group) {
					if (!("".equals(str)))
						System.out.println("发现不属于任何分组的配置：'" + str + "'");
					continue;
				}

				// 拿到配置，属于分组 group
				log("\t得到配置[" + group + "] -> '" + str + "'");
				setGroupValue(group, str);
			}

			log("read end");

			// to cache all
			log("init cache ...");
			toCache(original, "original");
			toCache(setBlank, "setBlank");
			toCache(setNotBlank, "setNotBlank");
			// original = null;
			// setBlank = null;
			// setNotBlank = null;
			log("init cache end");
		} catch (Exception e) {
			throw new RuntimeException("读取配置文件数据时发生异常：" + e.getMessage(), e);
		}
	}

	private static void log(String str) {
		// System.out.println(str);
	}

	private static void initGroup(String group) {
		List<String> list = original.get(group);
		if (null == list) {
			list = new LinkedList<>();
			original.put(group, list);
		}

		Set<String> nbset = setNotBlank.get(group);
		if (null == nbset) {
			nbset = new HashSet<>();
			setNotBlank.put(group, nbset);
		}

		Set<String> bset = setBlank.get(group);
		if (null == bset) {
			bset = new HashSet<>();
			setBlank.put(group, bset);
		}
	}

	private static void setGroupValue(String group, String config) {
		original.get(group).add(config);
		setBlank.get(group).add(config);
		if (StringUtils.isNotBlank(config))
			setNotBlank.get(group).add(config);
	}

	private static void toCache(Map<String, ?> map, String name) {
		Set<String> keys = map.keySet();
		for (String group : keys) {
			Collection<?> values = (Collection<?>) map.get(group);
			String[] vs = values.toArray(new String[values.size()]);
			cache.put(name + "_" + group, vs);
		}
	}

}
