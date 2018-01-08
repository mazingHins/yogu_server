package com.mazing.services.store.resourceapi;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class IndexResourceTest extends HttpResourceTest {

	public IndexResourceTest() {
		host = "http://userapi.yogubc.com";
	}

	/**
	 * 查看菜品信息
	 */
	@Test
	public void listAll() {

		ApiReq<RestResult<?>> req = build("p/v1/index");
		req.putGet("tagId", "1");
		req.putGet("sort", "1");
		req.putGet("pageIndex", "1");
		req.putGet("pageSize", "10");
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
	}

	@Test
	public void listRecommend() {

		ApiReq<RestResult<?>> req = build("p/v1/index/recommend");
		req.putGet("lastTime", "0");
		req.putGet("pageSize", "10");
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
	}
	

	@Test
	public void search() {

		ApiReq<RestResult<?>> req = build("p/v1/search/goods");
		
		req.putGet("keyword", "英莱");
		req.putGet("lastTime", "0");
		req.putGet("pageSize", "10");
		RestResult<?> result = req.doGet();

		Map<?, ?> map = assertMap(result);
	}
	
	
}
