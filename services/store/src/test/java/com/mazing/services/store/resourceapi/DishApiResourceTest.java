package com.mazing.services.store.resourceapi;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.web.RestResult;

/**
 * DishApiResource的测试类 <br>
 * 
 * @author JFan 2015年7月20日 下午2:32:25
 */
public class DishApiResourceTest extends BaseResourceTest {

	public DishApiResourceTest() {
		host = "http://storeapi.mazing.com";
	}

	/**
	 * 查看菜品信息
	 */
	@Test
	public void favNum() {
		int id = 722;

		ApiReq<RestResult<?>> req = build("api/dish/get");
		req.putGet("dishId", "" + id);
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
		assertEquals(id, map.get("dishId"));
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
