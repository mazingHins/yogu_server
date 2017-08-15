package com.mazing.services.order.resources;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class StoreOrderResourceTest extends HttpResourceTest {
	public StoreOrderResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	
	@Test
	public void testNewOrders(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/new");
		req.login();
		
		req.putGet("storeId", "1");
		req.putGet("pickType", "1");
		req.putGet("orderBeginTime", "0");
		req.putGet("pageSize", "5");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void testIfHasNewOrder() {
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/hasNewOrder");
		req.login("86", "15920575057", "cat123456");
		
		req.putGet("storeId", "11204");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void testOrdersOnRefund() {
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/refund2");
		req.login("86", "13012345601", "abcd1234");
		
		req.putGet("storeId", "12701");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	
}
