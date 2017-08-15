package com.mazing.services.order.resourceapi;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class OrderAccountingApiResourceTest extends HttpResourceTest {
	public OrderAccountingApiResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void testDealing() {
		ApiReq<RestResult<?>> req = build("api/order/accounting/queryDealing.do");
		req.putPost("storeId", "20001");
		req.putPost("start", "0");
		req.putPost("end", "0");
		
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
	@Test
	public void testSettling() {
		ApiReq<RestResult<?>> req = build("api/order/accounting/getSettlingAmount.do");
		req.putPost("storeId", "10008");
		req.putPost("start", "0");
		req.putPost("end", "0");
		
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
	@Test
	public void testActual() {
		ApiReq<RestResult<?>> req = build("api/order/accounting/getDealingActualFee.do");
		req.putPost("storeId", "10008");
		req.putPost("start", "0");
		req.putPost("end", "0");
		
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
	@Test
	public void testDiscount() {
		ApiReq<RestResult<?>> req = build("api/order/accounting/getDealingDiscountFee.do");
		req.putPost("storeId", "10008");
		req.putPost("start", "0");
		req.putPost("end", "0");
		
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
	@Test
	public void queryMazingPaySuccess() {
		ApiReq<RestResult<?>> req = build("api/order/accounting/queryMazingPaySuccess.do");
		req.putPost("storeId", "20001");
		
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
	@Test
	public void testMazingPayDiscountFee() {
		ApiReq<RestResult<?>> req = build("api/order/accounting/getMazingPayDiscountFee.do");
		req.putPost("storeId", "10008");
		req.putPost("start", "0");
		req.putPost("end", "0");
		
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
}
