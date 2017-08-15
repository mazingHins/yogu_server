package com.mazing.services.user.resource;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * TerminalProfileResource测试
 * @author Hins
 * @date 2015年8月25日 上午11:55:50
 */
public class TerminalProfileResourceTest extends HttpResourceTest {
	
	public TerminalProfileResourceTest() {
		host = "http://userapi.mazing.com";
	}
	
	/**
	 * @Description: 上传苹果token(未登录)
	 * @author Hins
	 * @date 2015年8月25日 上午11:58:00
	 *
	 */
	@Test
	public void uploadIosTokenNotLogin() {
		ApiReq<RestResult<?>> req = build("p/v1/upload/iosToken");
//		req.login();
		req.setGet("iosToken", "11223344 55667788");
		RestResult<?> result = req.doGet();

		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	/**
	 * @Description: 上传苹果token(已登录，且该token存在数据)
	 * @author Hins
	 * @date 2015年8月25日 上午11:58:00
	 *
	 */
	@Test
	public void uploadIosTokenHasLogin1() {
		ApiReq<RestResult<?>> req = build("p/v1/upload/iosToken");
		req.login();
		req.setGet("iosToken", "11223344 55667788");
		RestResult<?> result = req.doGet();
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	/**
	 * @Description: 上传苹果token(已登录，且该token不存在数据)
	 * @author Hins
	 * @date 2015年8月25日 上午11:58:00
	 *
	 */
	@Test
	public void uploadIosTokenHasLogin2() {
		ApiReq<RestResult<?>> req = build("p/v1/upload/iosToken");
		req.login();
		req.setGet("iosToken", "1122 3344 5566 7788");
		RestResult<?> result = req.doGet();
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
}
