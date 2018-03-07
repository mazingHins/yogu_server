package com.mazing.services.order.resources;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class CouponResouceTest  extends HttpResourceTest {
	
	public CouponResouceTest() {
		host = "http://orderapi.yogubc.com";
		userHost = "http://userapi.yogubc.com";
	}
	
	
	@Test
	public void listUserCoupon() {
		ApiReq<RestResult<?>> req = build("a/v1/coupon/list");
		req.login("86", "13926426236", "abcd1234");
		req.putPost("pageIndex", "1");
		req.putPost("pageSize", "10");
		RestResult<?> result = req.doGet();

		Assert.assertNotNull(result);
	}
}
