/**
 * 
 */
package com.mazing.test.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.mazing.test.core.des.DES3;
import com.mazing.test.core.http.HttpClientUtils;
import com.mazing.test.core.utils.ParameterUtil;
import com.mazing.test.core.utils.ThreadLocalContext;

/**
 * 以HTTP的形式发送测试请求 <br>
 * 
 * <br>
 * JFan - 2015年6月5日 下午2:55:03
 */
public class HttpRequestBuild {

	/** 如果需要修改，请在 Test类 “构造函数” 中覆盖此值 */
	protected String host = "http://storeapi.mazing.com";
	protected String userHost = "http://userapi.mazing.com";// 比如你要测试的是 storeapi域，但是登录访问的是 userapi。你懂的

	public HttpRequestBuild() {
	}

	public HttpRequestBuild(String host, String userHost) {
		this.userHost = userHost;
		this.host = host;
	}

	public ApiReq<String> build(String url) {
		return build(url, ApiReq.defAppKey, ApiReq.defAppSecret);
	}

	public ApiReq<String> build(String url, String appKey, String appSecret) {
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

		String did = ThreadLocalContext.getThreadValue("did");
		if (null == did) {
			did = UUID.randomUUID().toString().replaceAll("-", "");
			ThreadLocalContext.putThreadValue("did", did);
		}

		ApiReq<String> req = new HttpRequest(userHost, isA, reqUrl, appKey, appSecret);
		req.def();
		req.setDid(did);
		return req;
	}

	/**
	 * 3des 加密字符串
	 */
	protected String encrypt(String str) throws Exception {
		return DES3.encrypt(str, ApiReq.defAppSecret);
	}

}

/**
 * http请求执行器 <br>
 * 
 * @author JFan 2015年7月17日 下午3:23:18
 */
class HttpRequest extends ApiReq<String> {

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
	protected String req(boolean get, boolean forceMultipart) {
		String url = url(get);
		Map<String, String[]> hs = header();

		String json = null;
		if (get) {
			// json = HttpClientUtils.doGet(url, timeOut, dan(hs));
			try {
				json = HttpClientUtils.doGet(url, timeOut);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			// print("请求数据Body部分", postparams);
			if (fileList.isEmpty() && !forceMultipart)
				// json = HttpClientUtils.doPost(url, timeOut, dan(hs), dan(postparams));
				json = HttpClientUtils.doPost(url, "UTF-8", dan(postparams), timeOut, dan(hs));
			else {
				// Map<String, String> dan = dan(postparams);
				// Map<String, Object> ps = new HashMap<String, Object>();
				// ps.putAll(dan);
				// for (UploadFile f : fileList)
				// ps.put(f.getName(), f.getFile());
				// // json = HttpClientUtils.doPost(url, dan(hs), ps);
				// json = HttpClientUtils.call(HCMethod.POST, url, timeOut, dan(hs), ps, null, null, 9);
				throw new RuntimeException("暂时不支持提交附件");
			}
		}

		return json;
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

			// String json = r.doPost();
			// HashMap<?, ?> result = JsonUtils.parseObject(json, HashMap.class);
			// if (!("1".equals(result.get("code"))))
			// throw new RuntimeException((String)result.get("message"));
			//
			// Map<?, ?> obj = (Map<?, ?>) result.get("object");
			// String userToken = obj.get("ut").toString();
			// String userSecret = obj.get("secret").toString();
			//
			// return new KeyValue<String, String>(userToken, userSecret);

			String json = r.doPost();
			return readUserToken(json);
		} catch (Exception e) {
			throw new RuntimeException("执行登录时发生异常：" + e.getMessage(), e);
		}
	}

	private static KeyValue<String, String> readUserToken(String json) {
		String code = ParameterUtil.readJsonParam(json, "code");
		if (!"1".equals(code)) {
			String msg = ParameterUtil.readJsonParam(json, "message");
			throw new RuntimeException(msg);
		}

		String userToken = ParameterUtil.readJsonParam(json, "ut");
		String userSecret = ParameterUtil.readJsonParam(json, "secret");
		return new KeyValue<String, String>(userToken, userSecret);
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
