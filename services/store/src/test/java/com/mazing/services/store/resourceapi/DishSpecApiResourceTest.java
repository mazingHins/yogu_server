package com.mazing.services.store.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class DishSpecApiResourceTest extends HttpResourceTest {
	public DishSpecApiResourceTest() {
		host = "http://localhost:8090";
	}
	
	@Test
	public void testGetByKey() {
		ApiReq<RestResult<?>> req = build("api/dish/spec/getByKeys");
		req.putGet("specKeys", "3,4");
		RestResult<?> result = req.doGet();
		
		assertEquals(ResultCode.SUCCESS, result.getCode());
	}
}
