package com.mazing.services.order.resources;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class OrderSearchResourceTest extends HttpResourceTest {
	public OrderSearchResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void testQuery(){
		ApiReq<RestResult<?>> req = build("s/v1/store/orders/list/query");
		req.login("86", "13012345601", "abcd1234");
		req.putGet("storeId", "12701");
		req.putGet("content", "0");
		req.putGet("category", "0");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
}
