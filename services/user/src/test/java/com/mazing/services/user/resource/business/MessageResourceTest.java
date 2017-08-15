package com.mazing.services.user.resource.business;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/** 
 * MessageResource测试
 * @author Hins createTime：2015年7月23日 上午11:07:36 
 * 
 */
public class MessageResourceTest extends HttpResourceTest{
	
	public MessageResourceTest() {
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}
	
	@Test
	public void listPage() {
		ApiReq<RestResult<?>> req = build("a/v1/user/message/list");
		req.login();

		req.putGet("pageSize", "20");
		req.putGet("pageIndex", "1");
		RestResult<?> result = req.doGet();

		System.out.println("************************************");
		System.out.println(result);
	}
	
	@Test
	public void read() {
		ApiReq<RestResult<?>> req = build("a/v1/user/message/read");
		req.login();

		req.putGet("messageIds", "1");
		RestResult<?> result = req.doGet();

		System.out.println("************************************");
		System.out.println(result);
		
//		//重复标为已阅读
//		req = build("p/v1/user/message/read");
//		req.login("+86", "18620076051", "123456");
//		req.putGet("messageId", "1");
//		result = req.doGet();
//
//		System.out.println("************************************");
//		System.out.println(result);
//		
//		//不存在的消息id
//		req = build("p/v1/user/message/read");
//		req.login("+86", "18620076051", "699c68ae733a238a5b68bfa9d7bda19cdede9ed8rGAo6lMcXoo=");
//		req.putGet("messageId", "2");
//		result = req.doGet();
//
//		System.out.println("************************************");
//		System.out.println(result);
//		
//		//消息非本人
//		req = build("p/v1/user/message/read");
//		req.login("+86", "18620076031", "7b8c2cb5a7f30beaeb29d2bf76b01267cedce325+y45jT72dtTp3mkIcPwbSQ==");
//		req.putGet("messageId", "1");
//		result = req.doGet();
//
//		System.out.println("************************************");
//		System.out.println(result);
	}
	
	@Test
	public void testCount(){
		ApiReq<RestResult<?>> req = build("a/v1/user/message/count");
		req.login("86","13580480984","12345678");
		
		req.putGet("storeId", "10008");
		
		RestResult<?> result = req.doGet();
		System.out.println(result.getObject());
		assertEquals(ResultCode.SUCCESS, result.getCode());
		
	}
	
	@Test
	public void testList(){
		ApiReq<RestResult<?>> req = build("a/v1/user/message/list");
		req.login("86","13580480984","12345678");
		
		req.putGet("createTime", "0");
		req.putGet("pageSize", "10");
		req.putGet("storeId", "10008");
		
		RestResult<?> result = req.doGet();
		System.out.println(result.getObject());
		assertEquals(ResultCode.SUCCESS, result.getCode());
		
	}
	
	@Test
	public void testRead(){
		ApiReq<RestResult<?>> req = build("a/v1/user/message/read");
		req.login("86","13580480984","12345678");
		
//		req.putGet("storeId", "10008");
		
		RestResult<?> result = req.doGet();
		System.out.println(result.getObject());
		assertEquals(ResultCode.SUCCESS, result.getCode());
		
	}
}
