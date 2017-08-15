package com.mazing.services.order.resourceapi;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * OrderTasksApiResource 测试
 * @author Hins
 * @date 2015年10月10日 下午5:29:48
 */
public class OrderTasksApiResourceTest extends HttpResourceTest  { //BaseResourceTest

	public OrderTasksApiResourceTest() {
//		host = "http://orderapi.mazing.com";
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}

	@Test
	public void getPay() {
		ApiReq<RestResult<?>> req = build("api/order/tasks/autoCancelAndRelease");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);

	}
	
	@Test
	public void autoCancelDeliveryOrder() {
		ApiReq<RestResult<?>> req = build("api/order/tasks/autoCancelDeliveryOrder");

		RestResult<?> result = req.doGet();
		assertNotNull(result);

	}
	
	@Test
	public void autoCaculateStoreStar(){

		ApiReq<RestResult<?>> req = build("api/order/tasks/autoCaculateStoreStar");

		RestResult<?> result = req.doGet();
		assertNotNull(result);
	}
	

	@Test
	public void autoCaculateStoreCommentCount(){

		ApiReq<RestResult<?>> req = build("api/order/tasks/autoCaculateStoreCommentCount");

		RestResult<?> result = req.doGet();
		assertNotNull(result);
	}
	
	@Test
	public void testRerunJob() {
		ApiReq<RestResult<?>> req = build("api/order/tasks/rerunCallPay");

		RestResult<?> result = req.doGet();
		assertNotNull(result);
	}

}
