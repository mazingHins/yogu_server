package com.mazing.services.order.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class OrderApiResourceTest  extends HttpResourceTest {
	
	public OrderApiResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void testFixPayBizStatus() {
		ApiReq<RestResult<?>> req = build("api/order/fixPayBizStatus");
		req.putGet("day", "20160830");
		RestResult<?> result = req.doGet();
	}
}
