package com.mazing.services.order.resources;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class StoreOrderOperationResourceTest extends HttpResourceTest {
	
	public StoreOrderOperationResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void testAcceptOrder(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/operate/new/accept.do");
		req.login("86","14000000770","kenopen1");
		
		req.putPost("storeId", "26302");
		req.putPost("orderNo", "1610938969362554");
		
		RestResult<?> result = req.doPost();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	
	}
	
	@Test
	public void testAcceptOrder2(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/operate/new/accept2.do");
		req.login();
		
		req.putPost("storeId", "12701");
		req.putPost("orderNo", "1509420277534104");
		
		RestResult<?> result = req.doPost();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	
	}
	
	@Test
	public void testRejectOrder(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/operate/new/reject.do");
		req.login("86", "13926426236", "abcd1234");
		
		req.putGet("storeId", "12701");
		req.putPost("orderNo", "1608750230923802");
		req.putPost("rejectComment", "付错了");
		
		RestResult<?> result = req.doPost();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	
	}
	
	@Test
	public void testDeliveryThird(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/operate/deliveryThird.do");
		req.login("86", "13926426236", "abcd1234");
		
		req.putGet("storeId", "12701");
		req.putPost("orderNo", "1610470703934802");
		req.putPost("expressCode", "1001");
		
		
		RestResult<?> result = req.doPost();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void testListDelivery(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/listDelivery");
		req.login("86", "13926426236", "abcd1234");
		
		req.putGet("storeId", "12701");
		req.putGet("orderNo", "1610470703934802");
		req.putGet("pageSize", "10");
		req.putGet("pageIndex", "1");
		
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
}

