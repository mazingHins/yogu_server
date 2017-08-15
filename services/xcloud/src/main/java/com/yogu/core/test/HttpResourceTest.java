/**
 * 
 */
package com.yogu.core.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.commons.core.KeyValue;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.HttpClientUtils.HCMethod;
import com.yogu.commons.utils.encrypt.DES3;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 以HTTP的形式发送测试请求 <br>
 * 
 * <br>
 * JFan - 2015年6月5日 下午2:55:03
 */
public class HttpResourceTest {

	/** 如果需要修改，请在 Test类 “构造函数” 中覆盖此值 */
	protected String host = "http://xxx.mazing.com";
	protected String userHost = "http://userapi.mazing.com";// 比如你要测试的是 storeapi域，但是登录访问的是 userapi。你懂的

	protected ApiReq<RestResult<?>> build(String url) {
		return build(url, ApiReq.defAppKey, ApiReq.defAppSecret);
	}

	protected ApiReq<RestResult<?>> build(String url, String appKey, String appSecret) {
		String reqUrl = url.trim();
		boolean isA;
		if (!(reqUrl.startsWith("http://"))) {
			isA = (reqUrl.startsWith("/") ? reqUrl.startsWith("/a/") : reqUrl.startsWith("a/"));
			if (!(isA))
				isA = (reqUrl.startsWith("/") ? reqUrl.startsWith("/s/") : reqUrl.startsWith("s/"));
			reqUrl = host + (reqUrl.startsWith("/") ? "" : "/") + url;
		} else {
			isA = (-1 != reqUrl.indexOf("/a/"));
			if (!(isA))
				isA = (-1 != reqUrl.indexOf("/s/"));
		}

		ApiReq<RestResult<?>> req = new HttpRequest(userHost, isA, reqUrl, appKey, appSecret);
		req.def();
		return req;
	}

	/**
	 * 3des 加密字符串
	 */
	protected String encrypt(String str) throws Exception {
		return DES3.encrypt(str, ApiReq.defAppSecret);
	}

	protected void assertNull(Object object) {
		Assert.assertNull(object);
	}

	protected void assertNotNull(Object object) {
		Assert.assertNotNull(object);
	}

	protected void assertNotBlank(Object object) {
		assertNotNull(object);
		Assert.assertTrue(object instanceof CharSequence);
		Assert.assertTrue(StringUtils.isNotBlank((CharSequence) object));
	}

	protected void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	protected void assertEquals(long l1, long l2) {
		Assert.assertEquals(l1, l2);
	}

	protected void assertEquals(Object o1, Object o2) {
		Assert.assertEquals(o1, o2);
	}

	protected Map<?, ?> assertMap(RestResult<?> result) {
		Map<?, ?> map = assertObject(result);
		return map;
	}

	protected List<?> assertList(RestResult<?> result) {
		List<?> list = assertObject(result);
		return list;
	}

	@SuppressWarnings("unchecked")
	protected <T> T assertObject(RestResult<?> result) {
		Assert.assertNotNull(result);
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
		Object object = result.getObject();
		Assert.assertNotNull(object);
		return (T) object;
	}

}

/**
 * http请求执行器 <br>
 * 
 * @author JFan 2015年7月17日 下午3:23:18
 */
class HttpRequest extends ApiReq<RestResult<?>> {

	// 调用login方法登录时，才使用
	private String userHost = null;// 是 HttpResourceTest 这个类传进来的 userHost 值
	private int timeOut = 30000;

	protected HttpRequest(String userHost, boolean isA, String uri, String appKey, String appSecret) {
		super(isA, uri, appKey, appSecret);
		this.userHost = userHost;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.test.HttpReq#req(boolean, boolean)
	 */
	@Override
	protected RestResult<?> req(boolean get, boolean forceMultipart) {
		String url = url(get);
		Map<String, String[]> hs = header();

		String json = null;
		if (get) {
			json = HttpClientUtils.doGet(url, timeOut, dan(hs));
		} else {
			print("请求数据Body部分", postparams);
			if (fileList.isEmpty() && !forceMultipart)
				json = HttpClientUtils.doPost(url, timeOut, dan(hs), dan(postparams));
			else {
				Map<String, String> dan = dan(postparams);
				Map<String, Object> ps = new HashMap<String, Object>();
				ps.putAll(dan);
				for (UploadFile f : fileList)
					ps.put(f.getName(), f.getFile());
				// json = HttpClientUtils.doPost(url, dan(hs), ps);
				json = HttpClientUtils.call(HCMethod.POST, url, timeOut, dan(hs), ps, null, null, 9);
			}
		}
		System.out.println("<<<<响应内容：" + json);

		// return json;
		return JsonUtils.parseObject(json, new TypeReference<RestResult<?>>() {
		});
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.test.ApiReq#login(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	protected KeyValue<String, String> login(String countryCode, String mobile, String password, Map<String, String> baseParam) {
		try {
			String key = baseParam.get("akey");
			String appSecret = baseParam.get("appSecret");
			HttpRequest r = new HttpRequest(null, false, userHost + "/p/v1/user/login.do", key, appSecret);
			r.setSysName(baseParam.get("sname"));
			r.setSysVersion(baseParam.get("sver"));
			r.setAppName(baseParam.get("aname"));
			r.setAppVersion(baseParam.get("aver"));
			r.setDid(baseParam.get("did"));
			r.setCityCode(baseParam.get("citycode"));
			r.signToGet();
			r.putPost("countryCode", countryCode);
			r.putPost("mobile", DES3.encrypt(mobile, appSecret));
			r.putPost("password", DES3.encrypt(password, appSecret));

			RestResult<?> result = r.doPost();
			if (!(result.isSuccess()))
				throw new RuntimeException(result.getMessage());

			Map<?, ?> obj = (Map<?, ?>) result.getObject();
			String userToken = obj.get("ut").toString();
			String userSecret = obj.get("secret").toString();

			return new KeyValue<String, String>(userToken, userSecret);
		} catch (Exception e) {
			throw new RuntimeException("执行登录时发生异常：" + e.getMessage(), e);
		}
	}

	private Map<String, String> dan(Map<String, String[]> s) {
		if (null == s)
			return null;
		Map<String, String> d = new HashMap<String, String>();
		for (Entry<String, String[]> entry : s.entrySet()) {
			String[] value = entry.getValue();
			if (1 != value.length)
				throw new RuntimeException("暂时不支持传递多个值");
			d.put(entry.getKey(), (0 == value.length ? "" : value[0]));
		}
		return d;
	}

}
