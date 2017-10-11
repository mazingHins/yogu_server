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

}
