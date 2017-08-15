package com.mazing.services.order.resources;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * OrderServiceResource测试
 * @author Hins
 * @date 2015年9月9日 上午10:08:08
 */
public class OrderServiceResourceTest extends HttpResourceTest {
	
	public OrderServiceResourceTest() {
		host = "http://orderapi.mazing.com";
	}
	
	@Test
	public void finishCooking(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/operate/finishcooking.do");
		req.login();
		req.putGet("storeId", "16");

		req.putPost("orderNo", "1509420277534104");
		RestResult<?> result = req.doPost();
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
	}
	
	/**
	 * 商户查询已结束订单列表（分页正常）
	 * @author Hins
	 * @date 2015年9月9日 上午10:10:04
	 *
	 */
	@Test
	public void orderHistory(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/list/history");
		req.login("86", "18824620125" ,"xzq01256918");
		req.putGet("storeId", "15001");

		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "20");
		RestResult<?> result = req.doGet();
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
	}
	
	/**
	 * 商户查询已结束订单列表（不传每页大小）
	 * @author Hins
	 * @date 2015年9月9日 上午10:10:04
	 *
	 */
	@Test
	public void orderHistoryNoSize(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/detail");
		req.login();
		req.putGet("storeId", "1");

		req.putGet("pageIndex", "1");
		RestResult<?> result = req.doGet();
		Assert.assertEquals(ResultCode.SUCCESS, result.getCode());
	}
	
	/**
	 * 商户查询订单详情
	 * @author Hins
	 * @date 2015年9月9日 上午10:09:44
	 *
	 */
	@Test
	public void storeOrderDetail() {
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/detail");
		req.login();
		req.putPost("storeId", "12701");
		req.putPost("orderNo", "151018015920847");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
		//Assert.assertNull(object);
		
	}
	
	/**
	 * 
	 * 订单报表
	 * @author Hins
	 * @date 2015年9月19日 上午11:04:39
	 *
	 */
	@Test
	public void orderFlow() {
		ApiReq<RestResult<?>> req = build("/s/v1/statistics/order/flow2");
		req.login("86", "13012345601", "abcd1234");
		req.putGet("storeId", "12701");

		req.putGet("day", "7");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
		//Assert.assertNull(object);
		
	}
	
	/**
	 * 
	 * 营业报表
	 * @author Hins
	 * @date 2015年9月19日 上午11:04:39
	 *
	 */
	@Test
	public void saleFlow() {
		ApiReq<RestResult<?>> req = build("/s/v1/statistics/sale/flow");
		req.login();
		req.putGet("storeId", "16");

		req.putGet("day", "7");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
		//Assert.assertNull(object);
		
	}
	
	/**
	 * 收支明细
	 * @author Hins
	 * @date 2015年9月22日 下午5:52:35
	 *
	 */
	@Test
	public void accountsDetail() {
		ApiReq<RestResult<?>> req = build("/s/v1/statistics/accounts/detail");
		req.login();
		req.putGet("storeId", "16");

		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
		
	}
	
	@Test
	public void ordersOnCooking(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/oncooking");
		req.login();
		req.putGet("storeId", "11901");

		req.putGet("pickType", "0");
		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
	}
	
	@Test
	public void pendingConfirm(){
		ApiReq<RestResult<?>> req = build("/s/v1/store/orders/pendingconfirm2");
		req.login("86","13012345601","abcd1234");
		req.putGet("storeId", "12701");

//		req.putGet("pickType", "0");
//		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
		
	}
	
	/**
	 * 
	 * 营业报表
	 * @author Hins
	 * @date 2015年9月19日 上午11:04:39
	 *
	 */
	@Test
	public void querySaleMonth() {
		ApiReq<RestResult<?>> req = build("s/v1/statistics/sale/monthDetail");
		req.login("86", "13012345601", "abcd1234");
		req.putGet("storeId", "12701");

		req.putGet("month", "201512");

		RestResult<?> result = req.doGet();
		
		Object object = result.getObject();
		Assert.assertNotNull(object);
		//Assert.assertNull(object);
		
	}

}
