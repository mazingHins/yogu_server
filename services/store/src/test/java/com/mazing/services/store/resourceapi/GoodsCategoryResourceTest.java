package com.mazing.services.store.resourceapi;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class GoodsCategoryResourceTest extends HttpResourceTest {

	public GoodsCategoryResourceTest() {
		host = "http://storeapi.yogubc.com";
	}

	/**
	 * 查看菜品信息
	 */
	@Test
	public void listAll() {

		ApiReq<RestResult<?>> req = build("p/v1/store/category/list");
		req.putGet("tagId", "1");
		req.putGet("sort", "1");
		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
	}

	/**
	 * 批量获取菜品信息
	 */
	@Test
	public void list() {
		ApiReq<RestResult<?>> req = build("api/dish/list");
		req.putGet("dishKeys", "10001,10002");
		RestResult<?> result = req.doGet();

		List<?> list = assertList(result);
		assertEquals(2, list.size());
	}
	
	/**
	 * 批量获取菜品信息
	 */
	@Test
	public void listTrack() {
		ApiReq<RestResult<?>> req = build("api/dishTrack/list");
		req.putGet("dishIds", "10001,10002");
		RestResult<?> result = req.doGet();

		List<?> list = assertList(result);
		assertEquals(2, list.size());
	}
	
	/**
	 * 批量获取菜品信息
	 */
	@Test
	public void getTrack() {
		ApiReq<RestResult<?>> req = build("api/dishTrack/get");
		req.putGet("dishId", "10001");
		RestResult<?> result = req.doGet();

		List<?> list = assertList(result);
	}

	/**
	 * 修复更新订单详情图片
	 */
	@Test
	public void replaceOrderImg() {
		ApiReq<RestResult<?>> req = build("api/dish/replaceOrderImg");
		RestResult<?> result = req.doGet();

	}
	
	@Test
	public void testValidateSpecSurplus(){
		ApiReq<RestResult<?>> req = build("api/dish/spec/validation/surplus");
		RestResult<?> result = req.doGet();
		
		System.out.println(result);
		
	}

	@Test
	public void testGetSpecSnapshot(){
		
		ApiReq<RestResult<?>> req = build("api/dish/spec/snapshot/getById");
		
		req.putGet("specId", "1");
		RestResult<?> result = req.doGet();
		
		System.out.println(result);
		
	}
}
