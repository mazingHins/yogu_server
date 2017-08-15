package com.mazing.services.order.resourceapi;

import java.text.ParseException;

import org.junit.Test;

import com.yogu.commons.utils.DateUtils;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

public class OrderAdminApiResourceTest extends HttpResourceTest {
	
	public OrderAdminApiResourceTest() {
		host = "http://orderapi.mazing.com";
		userHost = "http://userapi.mazing.com";
	}
	
	@Test
	public void testQueryOrderComments() {
		try {
			ApiReq<RestResult<?>> req = build("api/order/comment/query");
			req.putGet("uid", "0");
			req.putGet("star", "0");
			req.putGet("orderNo", "0");
			req.putGet("beginTime", DateUtils.parseString("20150101", DateUtils.YYYYMMDD).getTime() + "");
			req.putGet("endTime", DateUtils.parseString("20160701", DateUtils.YYYYMMDD).getTime() + "");
			req.putGet("pageIndex", "1");
			req.putGet("pageSize", "5");
			
			RestResult<?> result = req.doGet();
			assertNotNull(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDeleteComment() {
		ApiReq<RestResult<?>> req = build("api/order/comment/delete.do");
		req.putPost("uid", "0");
		req.putPost("orderNo", "1512230144858232");
		req.putPost("commentId", "137001");
		RestResult<?> result = req.doPost();
		assertNotNull(result);
	}
	
	
}
