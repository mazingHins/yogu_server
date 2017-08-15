/**
 * 
 */
package com.mazing.core.test;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * 测试AcceptSeconds注解和拦截器 <br>
 * 
 * <br>
 * JFan - 2015年6月16日 下午5:08:35
 */
public class AcceptPermitInterceprotTest extends HttpResourceTest {

	public AcceptPermitInterceprotTest() {
		host = "http://localhost:8080";
	}

	@Test
	public void test() {
		ApiReq<RestResult<?>> req = build("p/v1/permit/not");
		RestResult<?> result = req.doGet();
		Long time = assertObject(result);
		assertNotNull(time);
	}

	@Test
	public void testLogin() {
		ApiReq<RestResult<?>> req = build("a/v1/permit/login");
		req.login();
		RestResult<?> result = req.doGet();
		Long time = assertObject(result);
		assertNotNull(time);
	}

}
