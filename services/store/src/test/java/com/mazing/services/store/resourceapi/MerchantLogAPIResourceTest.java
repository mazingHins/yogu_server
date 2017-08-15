package com.mazing.services.store.resourceapi;

import org.junit.Test;

import com.yogu.core.enums.merchant.MerchantLogModule;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 商家操作日志测试类
 * 
 * @author felix
 *
 */
public class MerchantLogAPIResourceTest extends HttpResourceTest {
	public MerchantLogAPIResourceTest() {
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}

	@Test
	public void test() {
		ApiReq<RestResult<?>> req = build("api/v1/merchant/log.do");
		req.putPost("storeId", "10008");
		req.putPost("uid", "100010");
		req.putPost("type", "添加");
		req.putPost("module", MerchantLogModule.DISH.getValue() + "");
		req.putPost("content", "try sth");
		
		RestResult<?> result = req.doPost();
		assertEquals(ResultCode.SUCCESS, result.getCode());
	}
}
