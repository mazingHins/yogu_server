package com.mazing.services.store.resourceapi;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * DishApiResource的测试类 <br>
 * 
 * @author JFan 2015年7月20日 下午2:32:25
 */
public class GoodsResourceTest extends HttpResourceTest {

	public GoodsResourceTest() {
		host = "http://userapi.yogubc.com";
	}

	/**
	 * 查看菜品信息
	 */
	@Test
	public void detail() {

		ApiReq<RestResult<?>> req = build("p/v1/goods/details");
		req.putGet("goodsKey", "3001");
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
	}

	@Test
	public void listByTag() {

		ApiReq<RestResult<?>> req = build("p/v1/goods/listByTag");
		req.putGet("tagId", "1001");
		req.putGet("sort", "1");
		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
	}
	
}
