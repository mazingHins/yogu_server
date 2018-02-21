package com.mazing.services.order.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class AdminCouponControllerTest   extends HttpResourceTest {
	
	public AdminCouponControllerTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void giftCoupon() {
		ApiReq<RestResult<?>> req = build("api/coupon/admin/queryCouponRules");
		req.putPost("uid", "8002");
		RestResult<?> result = req.doGet();
	}
}
