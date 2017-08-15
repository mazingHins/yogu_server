package com.mazing.services.order.resources;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;


/**
 * 用户评分api测试类 <br>
 * 
 * @author Felix 2015年9月9日
 */
public class StoreUserResourceTest extends HttpResourceTest {
	public StoreUserResourceTest() {
		host = "http://orderapi.mazing.com";
	}
	
	@Test
	public void testRatingUser(){
		ApiReq<RestResult<?>> req = build("s/v1/rating/user.do");
		req.login();
		
		req.putGet("storeId", "93");
		req.putPost("orderId", "17302");
		req.putPost("userId", "10100");
		req.putPost("star", "45");
		
		RestResult<?> result = req.doPost();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void testOrdersOnCooking(){
		ApiReq<RestResult<?>> req = build("s/v1/store/orders/oncooking");
		req.login("86", "18620076075", "12345678");
		req.putGet("storeId", "75");
		req.putGet("pickType", "0");
		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void storeNewOrders(){
		ApiReq<RestResult<?>> req = build("s/v1/store/orders/refund2");
		req.login("86", "13012345601", "abcd1234");
		req.putGet("storeId", "12701");
		req.putGet("pickType", "0");
		req.putGet("refundCreateTime", "0");
		req.putGet("pageSize", "10");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
}
