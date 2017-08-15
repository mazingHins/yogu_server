package com.mazing.services.order.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class OrderAccountDetailApiResourceTest extends HttpResourceTest {
	
	public OrderAccountDetailApiResourceTest() {
		host = "http://activityapi.internal.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	private static final long ACCEPT_ORDER_NO = 1606371631647401L;
	
	private static final long CONFIRM_ORDER_NO = 1606164881942241L;
	
	@Test
	public void acceptOrder() {
		ApiReq<RestResult<?>> req = build("api/activity/account/mazingPaySuccessOrder" );
		req.putGet("orderNo", "" + ACCEPT_ORDER_NO);
//		req.putPost("start", "0");
//		req.putPost("end", "0");
		
		RestResult<?> result = req.doGet();
		assertNotNull(result);
	}
	
	@Test
	public void confirmedOrder() {
		ApiReq<RestResult<?>> req = build("api/order/account/mazingPaySuccessOrder/orderNo/" + CONFIRM_ORDER_NO);
//		req.putGet("orderNo", "" + CONFIRM_ORDER_NO);
		
		RestResult<?> result = req.doGet();
		assertNotNull(result);
	}
}
