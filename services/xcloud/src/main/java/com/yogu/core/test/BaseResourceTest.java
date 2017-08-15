/**
 * 
 */
package com.yogu.core.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Assert;

import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.core.KeyValue;
import com.yogu.commons.utils.encrypt.DES3;
import com.yogu.commons.utils.encrypt.SignUtils;
import com.yogu.core.server.CoreApplicationConfig;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * jersey 基础测试类 <br>
 * 
 * @author JFan 2015年7月16日 上午12:01:52
 */
public class BaseResourceTest extends JerseyTest {

	protected String host = "http://storeapi.mazing.com";// host在这里没什么用处 Http test才用得到
	protected String userHost = "http://userapi.mazing.com";

	private static CoreApplicationConfig config;

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.glassfish.jersey.test.JerseyTest#configure()
	 */
	@Override
	protected Application configure() {
		GlobalSetting.cloud = "local";
		GlobalSetting.idc = "home";
		System.setProperty("PROJENV", "dev");
		System.setProperty("modul", "user");
		System.setProperty("test", "true");

		// forceSet(TestProperties.LOG_TRAFFIC, "true");
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		if (null == config) {
			config = new CoreApplicationConfig();
			config.property("contextConfigLocation", BaseServiceTest.LOCATIONS);
		}
		return config;
	}

	protected ApiReq<RestResult<?>> build(String uri) {
		return build(uri, ApiReq.defAppKey, ApiReq.defAppSecret);
	}

	protected ApiReq<RestResult<?>> build(String uri, String appKey, String appSecret) {
		boolean isA = (uri.startsWith("/") ? uri.startsWith("/a/") : uri.startsWith("a/"));
		if (!(isA))
			isA = (uri.startsWith("/") ? uri.startsWith("/s/") : uri.startsWith("s/"));
		ApiReq<RestResult<?>> req = new JerseyReq(isA, this, uri, appKey, appSecret);
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
 * 请求执行器 <br>
 * 
 * @author JFan 2015年7月17日 下午3:22:38
 */
class JerseyReq extends ApiReq<RestResult<?>> {

	private JerseyTest test;

	protected JerseyReq(boolean isA, JerseyTest test, String uri, String appKey, String appSecret) {
		super(isA, uri, appKey, appSecret);
		this.test = test;
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.test.HttpReq#req(boolean, boolean)
	 */
	@Override
	protected RestResult<?> req(boolean get, boolean forceMultipart) {
		// String url = url(get);

		WebTarget target = test.target(uri);
		// query params
		Map<String, String[]> gps = new HashMap<String, String[]>(getparams);
		if (get)
			gps.putAll(postparams);
		for (Entry<String, String[]> entry : gps.entrySet()) {
			String key = entry.getKey();
			Object[] values = entry.getValue();
			target = target.queryParam(key, values);
		}

		// sign
		String sign = sign();
		if (useHeaderSendSign)
			setHeader("sign", sign);
		else
			target = target.queryParam("sign", sign);

		Builder request = target.request(MediaType.APPLICATION_JSON_TYPE);

		// header params
		Map<String, String[]> header = header();
		if (null != header)
			for (Entry<String, String[]> entry : header.entrySet())
				for (String value : entry.getValue())
					request.header(entry.getKey(), value);

		if (get) {
			return request.get(RestResult.class);
		} else {
			Entity<?> entity;

			// 附件形式提交
			if (forceMultipart || (null != fileList && !(fileList.isEmpty()))) {
				FormDataMultiPart form = new FormDataMultiPart();
				MediaType mediaType = MediaType.MULTIPART_FORM_DATA_TYPE;
				// add text
				if (null != postparams && !(postparams.isEmpty()))
					for (Entry<String, String[]> entry : postparams.entrySet()) {
						String[] values = entry.getValue();
						String key = entry.getKey();
						for (String value : values)
							form.field(key, value);
					}
				// add file
				if (null != fileList && !(fileList.isEmpty())) {
					for (UploadFile entry : fileList)
						form.field(entry.getName(), entry.getFile(), MediaType.APPLICATION_OCTET_STREAM_TYPE);
					throw new RuntimeException("暂时不支持提交文件！");
				}
				// to body
				entity = Entity.entity(form, mediaType);

				// post params
			} else {
				Form form = new Form();
				MediaType mediaType = MediaType.APPLICATION_FORM_URLENCODED_TYPE;
				// add text
				if (null != postparams && !(postparams.isEmpty()))
					for (Entry<String, String[]> entry : postparams.entrySet()) {
						String[] values = entry.getValue();
						String key = entry.getKey();
						for (String value : values)
							form.param(key, value);
					}
				// to body
				entity = Entity.entity(form, mediaType);
			}

			return request.post(entity, RestResult.class);
		}
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.core.test.ApiReq#login(java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	protected KeyValue<String, String> login(String countryCode, String mobile, String password, Map<String, String> baseParam) {
		try {
			WebTarget target = test.target("/p/v1/user/login.do");

			// query params
			Map<String, String> gps = new HashMap<>(baseParam);
			for (Entry<String, String> entry : gps.entrySet()) {
				String key = entry.getKey();
				Object values = entry.getValue();
				target = target.queryParam(key, values);
			}

			// sign
			String appSecret = baseParam.get("appSecret");
			String mobileDes = DES3.encrypt(mobile, appSecret);
			String passwordDes = DES3.encrypt(password, appSecret);
			gps.put("countryCode", countryCode);
			gps.put("mobile", mobileDes);
			gps.put("password", passwordDes);

			Map<String, String[]> tos = tos(gps);
			String source = SignUtils.signSource(tos);
			System.out.println("登录签名元数据：" + source);
			System.out.println("登录签名密匙：" + appSecret);
			String sign = SignUtils.signHmacSha1(source, appSecret);
			System.out.println("登录签名值：" + sign);
			target = target.queryParam("sign", sign);

			Builder request = target.request(MediaType.APPLICATION_JSON_TYPE);

			Form form = new Form();
			MediaType mediaType = MediaType.APPLICATION_FORM_URLENCODED_TYPE;
			// add post param
			form.param("countryCode", countryCode);
			form.param("mobile", mobileDes);
			form.param("password", passwordDes);
			// to body
			Entity<?> entity = Entity.entity(form, mediaType);

			RestResult<?> result = request.post(entity, RestResult.class);
			if (!(result.isSuccess()))
				throw new RuntimeException(result.getMessage());

			Map<?, ?> map = (Map<?, ?>) result.getObject();
			String userToken = map.get("ut").toString();
			String userSecret = map.get("secret").toString();

			return new KeyValue<String, String>(userToken, userSecret);
		} catch (Exception e) {
			throw new RuntimeException("执行登录时发生异常：" + e.getMessage(), e);
		}
	}

	private Map<String, String[]> tos(Map<String, String> params) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		for (Entry<String, String> entry : params.entrySet())
			map.put(entry.getKey(), new String[] { entry.getValue() });
		return map;
	}

}
