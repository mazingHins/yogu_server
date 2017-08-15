package com.mazing.services.order.resources;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class MazingPayStoreOrdersResourceTest extends HttpResourceTest{
	
	/**
	 * 存在的餐厅id
	 */
	private static final String STORE_ID = "12701";
	
	/**
	 * 不存在的餐厅id
	 */
	private static final String NOT_EXIST_STORE_ID = "9999999";
	
	public MazingPayStoreOrdersResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	/**
	 * 测试所有参数正常，没有使用优惠券情况下的米星付-买单提交接口
	 * 
	 * @author hins
	 * @date 2016年6月16日 下午2:45:01
	 * @return void
	 */
	@Test
	public void orderHistory() {
		ApiReq<RestResult<?>> req = build("s/v1/order/mazingPay/list/history");
		req.login();
		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "5");
		req.putGet("storeId", "31401");
		RestResult<?> result = req.doGet();
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
	}
	
	@Test
	public void detail() {
		ApiReq<RestResult<?>> req = build("s/v1/order/mazingPay/detail");
		req.login();
		req.putGet("orderNo", "1704601184719420");
		req.putGet("storeId", "31401");
		RestResult<?> result = req.doGet();
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
	}

}
