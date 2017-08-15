package com.mazing.services.user.resource.pub;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.commons.core.KeyValue;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.web.RestResult;

public class LogoutResourceTest extends BaseResourceTest {

	public LogoutResourceTest() {
		// host = "http://userapi.mazing.com";
		// userHost = "http://userapi.mazing.com";
		host = "http://localhost:8080";
		userHost = "http://localhost:8080";
	}

	@Test
	public void testLogout() {
		
		//ApiReq.testOnLocalServer(true, host);
		
		ApiReq<RestResult<?>> req = build("p/v1/user/logout");
		
		KeyValue<String, String> kv = req.login();
		String userToken =  kv.getKey();
		
		Assert.assertNotNull(userToken);
		
		req.doGet();
	}
}
