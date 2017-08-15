package com.mazing.services.user.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class LocalUserTagApiResourceTest extends HttpResourceTest {
	
	public LocalUserTagApiResourceTest() {
		host = "http://userapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void timerUserSystemTag() {
		ApiReq<RestResult<?>> req = build("api/user/tagRaise/timerUserSystemTag.do");
		
		RestResult<?> restResult = req.doGet();
		
		assertEquals(restResult.getCode(), ResultCode.SUCCESS);
	}
	
}
