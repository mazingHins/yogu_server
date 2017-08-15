package com.mazing.services.store.resourceapi;

import org.junit.Test;

import com.yogu.core.enums.Role;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.BaseResourceTest;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class StoreStaffRoleApiResourceTest extends HttpResourceTest {
	
	public StoreStaffRoleApiResourceTest(){
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}
	
	@Test
	public void testGetStaffRoles(){
		ApiReq<RestResult<?>> req = build("api/store/staff/roles");
		
		req.putGet("storeId", "1");
		req.putGet("uid", "3");
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void test(){
		ApiReq<RestResult<?>> req = build("api/store/staff/getUidsByRole");
		
		req.putGet("storeId", "10008");
		req.putGet("role", Role.ORDER_ACCEPTER.getValue()+ "");
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void testIfNotify(){
		ApiReq<RestResult<?>> req = build("api/store/staff/ifNotify");
		
		req.putGet("uid", "400000");
		req.putGet("storeId", "10008");
		
		RestResult<?> result = req.doGet();
		
		assertEquals(result.getObject(), Boolean.TRUE);
	}
}
