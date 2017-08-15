package com.mazing.services.user.resource;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class UserIMResourceTest extends HttpResourceTest{
	public UserIMResourceTest() {
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}
	
	@Test
	public void testGetInfoByImid() {
		ApiReq<RestResult<?>> req = build("p/v1/user/getUserInfoByImid");
		req.login("86","13580480984", "12345678");

		req.putGet("imid", "11929");
		RestResult<?> result = req.doGet();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("nickname"));
		System.out.println(map.get("nickname"));
	}
}
