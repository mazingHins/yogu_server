/**
 * 
 */
package com.mazing.core.test;

import org.junit.Ignore;
import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 测试AcceptSeconds注解和拦截器 <br>
 * 
 * <br>
 * JFan - 2015年6月16日 下午5:08:35
 */
@Ignore
public class AcceptSecondsInterceprotTest extends HttpResourceTest {

	public AcceptSecondsInterceprotTest() {
		host = "http://localhost:8080";
	}

	@Test
	public void test() {
		ApiReq<RestResult<?>> req = build("/p/v1/common/as/test");
		RestResult<?> result = req.doGet();
		assertNotNull(result);
		assertEquals("'t' must not be empty.", result.getMessage());
		req.setGet("t", time(0));
		result = req.doGet();
		assertNotNull(result);
		assertEquals(ResultCode.SUCCESS, result.getCode());

		req.setGet("t", time(3));
		result = req.doGet();
		assertNotNull(result);
		assertEquals(ResultCode.SUCCESS, result.getCode());
		req.setGet("t", time(-3));
		result = req.doGet();
		assertNotNull(result);
		assertEquals(ResultCode.SUCCESS, result.getCode());

		req.setGet("t", "");
		result = req.doGet();
		assertNotNull(result);
		assertEquals("'t' must not be empty.", result.getMessage());
		req.setGet("t", "abc");
		result = req.doGet();
		assertNotNull(result);
		assertEquals("'t' is invalid", result.getMessage());// 格式不正确，是没有.的

		req.setGet("t", time(5));
		result = req.doGet();
		assertNotNull(result);
		assertEquals(ResultCode.SUCCESS, result.getCode());
		req.setGet("t", time(-5));
		result = req.doGet();
		assertNotNull(result);
		assertEquals(ResultCode.SUCCESS, result.getCode());

		req.setGet("t", time(7));
		result = req.doGet();
		assertNotNull(result);
		assertEquals("'t' is invalid.", result.getMessage());// 有.
		req.setGet("t", time(-7));
		result = req.doGet();
		assertNotNull(result);
		assertEquals("'t' is invalid.", result.getMessage());
	}

	protected String time(int jiaSeconds) {
		Long time = System.currentTimeMillis() / 1000;
		time = time + jiaSeconds;
		return time.toString();
	}

}
