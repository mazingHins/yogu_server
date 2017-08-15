package com.yogu.commons.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ArrayUtils;
import com.yogu.commons.utils.StringUtils;

public final class RequestUtils {

	private RequestUtils() {
	}

	public static final String HEADER_SEPARATOR = ";";

	/**
	 * 获取请求者的相对路径
	 */
	public static String getRelativePath(HttpServletRequest request) {
		// if (request.getAttribute("javax.servlet.include.request_uri") != null) {
		// String result = (String) request.getAttribute("javax.servlet.include.path_info");
		// if (result == null)
		// result = (String) request.getAttribute("javax.servlet.include.servlet_path");
		// else
		// result = (String) request.getAttribute("javax.servlet.include.servlet_path") + result;
		// if ((result == null) || (result.equals("")))
		// result = "/";
		// return result;
		// }

		// System.out.println(">>>>"+request.getContextPath());
		// System.out.println(">>>>"+request.getPathInfo());
		// System.out.println(">>>>"+request.getRequestURI());
		// System.out.println(">>>>"+request.getServletPath());
		// System.out.println(">>>>"+request.getServletContext());

		String result = request.getPathInfo();
		if (result == null)
			result = request.getServletPath();
		else
			result = request.getServletPath() + result;
		if ((result == null) || (result.equals("")))
			result = "/";
		return result;
	}

	/**
	 * <p>
	 * Create a sorted map from the specified request. Empty parameter names and values are filtered out.
	 * <p>
	 * For request with multiple identical parameter names, like warehouse=xxx&wareshouse=yyy, only the first entry is preserved (e.g. warehouse=xxx), this is the exising behaviour and we just follow
	 * it for compability.
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public static SortedMap<String, String> createParamMap(ServletRequest request, String... exc) {
		Args.notNull(request, "'request'");

		SortedMap<String, String> map = new TreeMap<String, String>();
		Enumeration<String> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			boolean b = false;
			if (null != exc)
				for (String e : exc)
					if (e.equals(paramName)) {
						b = true;
						break;
					}
			if (b)
				break;
			String value = request.getParameter(paramName);
			if (StringUtils.isNotEmpty(paramName) && StringUtils.isNotEmpty(value)) {
				map.put(paramName, value);
			}
		}
		return map;
	}

	/**
	 * 将map中的值拼接成URL
	 */
	public static String mapToParamUrl(SortedMap<String, String> map, String url) {
		String suffixUrl = "";
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().toString();

			value = map.get(key).toString();
			suffixUrl += "&" + key + "=" + value;

		}
		// 将第一个& 换成?, 其他的保持不变
		suffixUrl = suffixUrl.replaceFirst("&", "?");
		StringBuilder urlStringBuilder = new StringBuilder();
		urlStringBuilder.append(url).append(suffixUrl);
		return urlStringBuilder.toString();
	}

	/**
	 * Normalize outbound service parameters.
	 * 
	 * @param paramMap
	 */
	public static void normalizeServiceParamMap(Map<String, String> paramMap) {
		if (paramMap == null || paramMap.size() == 0) {
			return;
		}

		for (Iterator<Entry<String, String>> it = paramMap.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			if (StringUtils.isEmpty(key)) {
				it.remove();
			}
		}
	}

	public static String getHeaderValue(HttpServletRequest request, String name, String key) {
		Map<String, String> map = getHeaderValues(request, name);
		if (map == null || map.isEmpty()) {
			return null;
		}
		return map.get(key);
	}

	public static Map<String, String> getHeaderValues(HttpServletRequest request, String name) {
		return getHeaderValues(request, name, HEADER_SEPARATOR);
	}

	public static Map<String, String> getHeaderValues(HttpServletRequest request, String name, String separator) {
		Args.notEmpty(name, "'header name'");

		if (StringUtils.isEmpty(separator)) {
			separator = HEADER_SEPARATOR;
		}

		String header = request.getHeader(name);
		if (StringUtils.isBlank(header)) {
			return null;
		}

		String value = null;
		if (header.startsWith("OAuth ")) {
			int index = header.indexOf(" ");
			value = header.substring(index + 1);
		}

		String[] splits = value.split(separator);
		if (ArrayUtils.isEmpty(splits)) {
			return null;
		}

		Map<String, String> map = new HashMap<String, String>();
		for (String split : splits) {
			if (StringUtils.isNotBlank(split)) {
				String[] tokens = split.trim().split("=");
				if (tokens != null && tokens.length == 2) {
					map.put(tokens[0], tokens[1]);
				}
			}
		}
		return map;
	}

	public static String getRequestUrl(HttpServletRequest request) {
		return request.getRequestURL().toString();
	}

	public static String getFullRequestUrl(HttpServletRequest request) {
		StringBuffer requestUrl = request.getRequestURL();
		String queryString = request.getQueryString();

		if (StringUtils.isNotEmpty(queryString)) {
			requestUrl.append('?').append(queryString);
		}
		return requestUrl.toString();
	}

}
