package com.mazing.services.order.resources;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class OrderResourceTest extends HttpResourceTest {
	public OrderResourceTest() {
		host = "http://orderapi.yogubc.com";
		userHost = "http://userapi.yogubc.com";
	}
	
	
	/**
	 * @Description: 下单预结算接口测试成功
	 * @author Hins
	 * @date 2015年8月24日 下午3:39:00
	 *
	 */
	@Test
	public void settle() {
		ApiReq<RestResult<?>> req = build("a/v1/order/settle2.do");
		req.login("86", "13926426236", "abcd1234");
		req.putPost("purchaseDetail", "[{\"goodsKey\": 1000,\"purchaseNum\": 2}]");
		RestResult<?> result = req.doPost();

		Assert.assertNotNull(result);
	}
	
}
