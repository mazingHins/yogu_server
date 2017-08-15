package com.mazing.services.user.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class LocalUserMsgApiResourceTest extends HttpResourceTest {
	public LocalUserMsgApiResourceTest() {
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}
	
	@Test
	public void test() {
		ApiReq<RestResult<?>> req = build("api/v1/user/message/send.do");
		
		req.putPost("uid", "100010");
		req.putPost("type", "1");
		req.putPost("target", "http");
		req.putPost("title", "aaabbb");
		req.putPost("content", "hello world");
		req.putPost("expireTime", "0");
		
		RestResult<?> restResult = req.doPost();
		
		assertEquals(restResult.getCode(), ResultCode.SUCCESS);
	}
}
