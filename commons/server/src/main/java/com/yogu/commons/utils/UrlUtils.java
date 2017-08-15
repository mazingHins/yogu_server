package com.yogu.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.DigestUtil;
import com.yogu.commons.utils.StringUtils;

/**
 * URL 以及参数相关操作 <br>
 * 包含MD5、SHA1封签方法
 * 
 * JFan 2014年12月19日 下午1:39:48
 */
public final class UrlUtils {

	private UrlUtils() {
	}

	private static final Logger logger = LoggerFactory.getLogger(UrlUtils.class);

	public static final String DEFAULT_HTTP_ENC = "UTF-8";

	/**
	 * 截取url地址中 ? 之前部分
	 */
	public static String getBaseUrl(String url) {
		Args.notEmpty(url, "'url'");

		int index = url.indexOf('?');
		if (index == -1)
			return url;
		return url.substring(0, index);
	}

	/**
	 * 将url中的queryString解析成SortedMap
	 */
	public static SortedMap<String, String> param2Map(String url) {
		return param2Map(url, DEFAULT_HTTP_ENC);
	}

	/**
	 * 将url中的queryString解析成SortedMap<br>
	 * 参数值，按指定编码转换<br>
	 * 当转换异常时，塞入原值
	 */
	public static SortedMap<String, String> param2Map(String url, String enc) {
		Args.notEmpty(url, "'url'");

		int index = url.indexOf('?');
		if (index == -1)
			return null;

		String queryStr = url.substring(index + 1);
		if (StringUtils.isBlank(queryStr))
			return null;

		SortedMap<String, String> paramMap = new TreeMap<String, String>();

		for (String paramStr : queryStr.split("[&]")) {
			int eIndex = paramStr.indexOf('=');
			if (eIndex == -1)
				continue;
			String name = paramStr.substring(0, eIndex);
			String value = paramStr.substring(eIndex + 1);
			try {
				value = URLDecoder.decode(value, enc);
			} catch (UnsupportedEncodingException e) {
				logger.error("P2M Decode URL Param '{}' '{}' ERROR.", enc, value);
			}
			paramMap.put(name, value);
		}

		return paramMap;
	}

	/**
	 * 将url中的queryString参数，合并到params中
	 */
	public static SortedMap<String, String> mergeQuery2Map(String url, SortedMap<String, String> params) {
		return mergeQuery2Map(url, DEFAULT_HTTP_ENC, params);
	}

	/**
	 * 将url中的queryString参数，合并到params中<br>
	 * 参数值，按指定编码转换<br>
	 * 当转换异常时，塞入原值
	 */
	public static SortedMap<String, String> mergeQuery2Map(String url, String enc, SortedMap<String, String> params) {
		SortedMap<String, String> urlParamMap = UrlUtils.param2Map(url, enc);
		if (urlParamMap == null)
			return params;
		if (params == null)
			return urlParamMap;

		params.putAll(urlParamMap);
		return params;
	}

	/**
	 * 将参数合并到url queryString中
	 */
	public static String mergeParam2Url(Map<String, String> params, String url) {
		return mergeParam2Url(params, url, DEFAULT_HTTP_ENC);
	}

	/**
	 * 将参数合并到url queryString中
	 */
	public static String merge2Url(String url, String paramName, String paramValue) {
		Map<String, String> params = new HashMap<>();
		params.put(paramName, paramValue);
		return mergeParam2Url(params, url, DEFAULT_HTTP_ENC);
	}

	/**
	 * 将参数合并到url queryString中<br>
	 * 使用指定编码进行URL转码
	 */
	public static String mergeParam2Url(Map<String, String> params, String url, String enc) {
		Args.notNull(url, "'url'");
		Args.notNull(enc, "'enc'");

		if (null == params || params.isEmpty())
			return url;

		StringBuffer sb = new StringBuffer(url);
		if (-1 == url.indexOf('?'))
			sb.append('?');

		boolean and = (-1 != url.indexOf('='));
		for (Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isBlank(key) || null == value)
				continue;

			if (and)
                sb.append('&');
			sb.append(key);
			sb.append('=');

			try {
				sb.append(URLEncoder.encode(value, enc));
			} catch (UnsupportedEncodingException e) {
				logger.warn("P2U Encode '{}' '{}' ERROR.", enc, value);
				sb.append(value);
			}

			and = true;
		}

		return sb.toString();
	}

//	/**
//	 * 将object中的属性和值，拼接到url queryString中<br>
//	 * 自动忽略EmptyValue<br>
//	 * 使用默认{@link DEFAULT_HTTP_ENC}编码<br>
//	 */
//	public static String mergeObject2Url(Object object, String url) {
//		return mergeObject2Url(object, url, true, DEFAULT_HTTP_ENC);
//	}
//
//	/**
//	 * 将object中的属性和值，拼接到url queryString中<br>
//	 * 可设置是否忽略EmptyValue<br>
//	 * 使用默认{@link DEFAULT_HTTP_ENC}编码<br>
//	 */
//	public static String mergeObject2Url(Object object, String url, boolean ignoreEmpty) {
//		return mergeObject2Url(object, url, ignoreEmpty, DEFAULT_HTTP_ENC);
//	}
//
//	/**
//	 * 将object中的属性和值，拼接到url queryString中<br>
//	 * 可设置是否忽略EmptyValue<br>
//	 * 使用默认指定的编码<br>
//	 */
//	public static String mergeObject2Url(Object object, String url, boolean ignoreEmpty, String enc) {
//		Args.notNull(url, "'url'");
//
//		SortedMap<String, String> params = ObjectUtils.toMap(object, new TreeMap<String, String>(), ignoreEmpty);
//		return mergeParam2Url(params, url, enc);
//	}

	// ####
	// ## MD5 Digest
	// ####

	public static String makeMd5Hash(String url, SortedMap<String, String> paramMap, String secret) {
		SortedMap<String, String> mergedMap = mergeQuery2Map(url, paramMap);
		return makeMd5Hash(mergedMap, secret);
	}

	public static String makeMd5Hash(SortedMap<String, String> paramMap, String secret) {
		Args.notEmpty(paramMap, "'paramMap'");
		Args.notEmpty(secret, "'secret'");

		StringBuilder sb = new StringBuilder(32);
		for (String key : paramMap.keySet())
			sb.append(paramMap.get(key));
		sb.append(secret);
		return DigestUtil.md5(sb.toString().getBytes());
	}

	// ####
	// ## SHA1 Digest
	// ####

	public static String makeSha1Hash(String url, SortedMap<String, String> paramMap, String secret) {
		return makeSha1Hash(url, paramMap, secret, null);
	}

	public static String makeSha1Hash(String url, SortedMap<String, String> paramMap, String secret, String tokenSecret) {
		SortedMap<String, String> mergedMap = mergeQuery2Map(url, paramMap);
		return makeSha1Hash(mergedMap, secret, tokenSecret);
	}

	public static String makeSha1Hash(SortedMap<String, String> paramMap, String secret) {
		return makeSha1Hash(paramMap, secret, null);
	}

	public static String makeSha1Hash(SortedMap<String, String> paramMap, String secret, String tokenSecret) {
		Args.notEmpty(paramMap, "'paramMap'");
		Args.notEmpty(secret, "'secret'");

		StringBuilder sb = new StringBuilder(32);
		sb.append(secret);
		if (StringUtils.isNotBlank(tokenSecret)) {
			sb.append('&');
			sb.append(tokenSecret);
		}
		for (String value : paramMap.values())
			sb.append(value);
		return DigestUtil.sha1(sb.toString());
	}

}
