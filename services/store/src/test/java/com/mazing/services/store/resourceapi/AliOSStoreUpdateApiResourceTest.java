package com.mazing.services.store.resourceapi;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

public class AliOSStoreUpdateApiResourceTest extends HttpResourceTest {
	public AliOSStoreUpdateApiResourceTest(){
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}
	
	@Test
	public void test(){
		ApiReq<RestResult<?>> req = build("api/store/alios/update/visibility");
		req.login();
		req.setGet("adminId", "0");
		req.setPoint(113.416542, 23.130796);
		
		RestResult<?> result = req.doGet();
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
	@Test
	public void testDish(){
		for(int i = 0; i < 10; i++){
			ApiReq<RestResult<?>> req = build("api/dish/alios/update/storeVisibility");
			req.login("86","13580480984", "12345678");
			
			req.setGet("adminId", "0");
			req.setPoint(113.416542, 23.130796);
			
			RestResult<?> result = req.doGet();
			assertEquals(result.getCode(), ResultCode.SUCCESS);
			
		}
		
	}
	
	
	@Test
	public void testRange(){
		ApiReq<RestResult<?>> req = build("api/store/alios/update/range");
		req.login("86", "13580480984", "12345678");

		req.setGet("adminId", "0");
		req.setPoint(113.416542, 23.130796);

		RestResult<?> result = req.doGet();
		assertEquals(result.getCode(), ResultCode.SUCCESS);	
		
	}
	
	@Test
	public void testPushStore(){
		ApiReq<RestResult<?>> req = build("api/store/alios/push/store");
		req.login("86", "13580480984", "12345678");

//		req.setGet("adminId", "0");
		req.setPoint(113.416542, 23.130796);

		RestResult<?> result = req.doGet();
		assertEquals(result.getCode(), ResultCode.SUCCESS);	
		
	}
	
	@Test
	public void testUpdateOpenHours(){
		for (int i = 0; i < 30; i++) {
			ApiReq<RestResult<?>> req = build("api/store/alios/update/openHours");
			req.login("86", "13580480984", "12345678");

			req.setGet("adminId", "0");
			// req.setPoint(113.416542, 23.130796);

			RestResult<?> result = req.doGet();
			assertEquals(result.getCode(), ResultCode.SUCCESS);
		}
	}
	
	@Test
	public void testUpdateAll(){
		ApiReq<RestResult<?>> req = build("api/store/alios/update/all");
		req.login("86", "13580480984", "12345678");

		req.setGet("adminId", "0");
		// req.setPoint(113.416542, 23.130796);

		RestResult<?> result = req.doGet();
		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
	
}
