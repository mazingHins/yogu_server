package com.mazing.services.order.resources;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class OrderIMResourceTest extends HttpResourceTest {
	public OrderIMResourceTest() {
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}
	
	@Test
	public void testIMList() {
		ApiReq<RestResult<?>> req = build("s/v1/order/im/getInProgressOrders");
		req.login("86", "13580480984", "12345678a");
		
		req.putGet("storeId", "10008");
		req.putGet("imId", "11929");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
}
