package com.mazing.core.test;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 访问频率限制测试类 <br>
 * 
 * @author JFan 2015年7月17日 下午3:08:29
 */
public class FrequencyLimitationTest extends HttpResourceTest {

	public FrequencyLimitationTest() {
		host = "http://localhost:8080";
	}

	/*
	 * Base方式启动的测试，取消了频率限制，所以需要使用HTTP形式
	 */

	/**
	 * 验证访问限制的有效性
	 */
	@Test
	public void test() throws Exception {
		ApiReq<RestResult<?>> req = build("/p/v1/common/limit");
		RestResult<?> result = req.doGet();

		Assert.assertNotNull(result);
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());

		result = req.doGet();
		Assert.assertEquals(ResultCode.FREQUENCY_OVERLOAD, result.getCode());

		Thread.sleep(300);
		result = req.doGet();
		Assert.assertEquals(ResultCode.FREQUENCY_OVERLOAD, result.getCode());

		Thread.sleep(800);
		result = req.doGet();
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
	}

}
