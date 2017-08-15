package com.mazing.services.user.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.web.RestResult;

/**
 * 用户提现账号的测试类
 */
public class LocalUserAccountApiResourceTest extends BaseResourceTest {
	public LocalUserAccountApiResourceTest(){
		host = "http://localhost:8080";
	}
	
	@Test
	public void testGetUserAccount(){
		ApiReq<RestResult<?>> req = build("api/v1/user/account/get");
		req.putGet("uid", "10016");
		
		RestResult<?> result = req.doGet();
		
		assertNull(result.getObject());
	}
}
