/**
 * 
 */
package com.mazing.core.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.commons.utils.encrypt.SignUtils;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 测试 ApiAuthInterceptor <br>
 * 
 * <br>
 * JFan - 2015年6月3日 下午5:11:44
 */
public class ApiAuthInterceptorTest extends HttpResourceTest {

	public ApiAuthInterceptorTest() {
		host = "http://localhost:8080";
	}

	/*
	 * 建议走HTTP模式去验证
	 */

	final String appKey = "13a9b526b3d44bdd8d1553346ebcf9b8";
	final String appSecret = "D5EFAEE2D37B423AAA43AC67B5EDC3FB";

	/**
	 * 验证签名方法一致
	 */
	@Test
	public void test() {
		Map<String, String[]> headers = new HashMap<String, String[]>();
		Map<String, String[]> getParams = new HashMap<String, String[]>();
		Map<String, String[]> postParams = new HashMap<String, String[]>();

		getParams.put("akey", new String[] { appKey });
		getParams.put("sname", new String[] { ApiReq.sname });
		getParams.put("sver", new String[] { ApiReq.sver });
		getParams.put("aname", new String[] { ApiReq.aname });
		getParams.put("aver", new String[] { ApiReq.aver });
		getParams.put("citycode", new String[] { ApiReq.cityCode });
		getParams.put("did", new String[] { ApiReq.did });

		headers.put("userName", new String[] { "JavaFan" });// header参数参与封签时，不用以mz-开头， HttpClient发起请求时才需要
		headers.put("orderId", new String[] { "100009", "100100" });

		getParams.put("shopId", new String[] { "999", "888" });
		getParams.put("lng", new String[] { "23.5587496554" });
		getParams.put("lat", new String[] { "13.0001578" });
		getParams.put("cname", new String[] { "张三" });

		postParams.put("className", new String[] { "计算机网络" });

		Map<String, String[]> params = new HashMap<String, String[]>();
		params.putAll(headers);
		params.putAll(getParams);
		params.putAll(postParams);

		String sign = sign(params, appSecret);
		Assert.assertNotNull(sign);

		ApiReq<RestResult<?>> req = build("p/v1/common/notlogin");
		req.setHeader("userName", new String[] { "JavaFan" });
		req.setHeader("orderId", new String[] { "100009", "100100" });

		req.setGet("shopId", new String[] { "999", "888" });
		req.setGet("lng", new String[] { "23.5587496554" });
		req.setGet("lat", new String[] { "13.0001578" });
		req.setGet("cname", new String[] { "张三" });

		req.putPost("className", new String[] { "计算机网络" });
		String ssign = req.sign();
		Assert.assertNotNull(ssign);

		Assert.assertEquals(sign, ssign);
	}

	/**
	 * 验证不用登录也可以访问应用（签名）
	 */
	@Test
	public void notLogin() {
		int size = (int) (System.currentTimeMillis() % 10000);
		ApiReq<RestResult<?>> req = build("p/v1/common/notlogin");
		req.putGet("size", "" + size);
		RestResult<?> result = req.doGet();

		Assert.assertNotNull(result);
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());

		Object object = result.getObject();
		Assert.assertNotNull(object);
		Assert.assertEquals(size, object);
	}

	/**
	 * 不用登录的p，但是也登录了
	 */
	@Test
	public void notLoginLogin() {
		int size = (int) (System.currentTimeMillis() % 10000);
		ApiReq<RestResult<?>> req = build("p/v1/common/notlogin");
		req.login();

		req.putGet("size", "" + size);
		RestResult<?> result = req.doGet();

		Assert.assertNotNull(result);
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());

		Object object = result.getObject();
		Assert.assertNotNull(object);
		Assert.assertEquals(size, object);
	}

	/**
	 * 验证必须登录才能够访问
	 */
	@Test
	public void login() {
		ApiReq<RestResult<?>> req = build("a/v1/common/login");
		req.login();
		RestResult<?> result = req.doGet();

		Assert.assertNotNull(result);
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());

		Object object = result.getObject();
		Assert.assertNotNull(object);
		Long time = Long.parseLong(object.toString());
		Assert.assertNotNull(time);
	}

	private String sign(Map<String, String[]> params, String secret) {
		String source = SignUtils.signSource(params);
		System.out.println("+签名源：" + source);
		String sign = SignUtils.signHmacSha1(source, secret);
		System.out.println("+签名值：" + sign);
		return sign;
	}

}
