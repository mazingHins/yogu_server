package com.mazing.services.user.resource.business;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * 
 * @Description: FeedbackResource测试
 * @author Hins
 * @date 2015年7月25日 下午4:11:57
 */
public class FeedbackResourceTest extends HttpResourceTest {
	
	public FeedbackResourceTest() {
		host = "http://userapi.mazing.com";
	}

	/**
	 * 提交反馈 不带图片
	 */
	@Test
	public void save1() {
		ApiReq<RestResult<?>> req = build("a/v1/user/feedback/save.do");
		req.login();

		req.setPost("content", "我的反馈，我的说明，我的意见。");

		// RestResult<?> result = req.doMultipartPost();
		RestResult<?> result = req.doPost();
		assertNotNull(result);
		assertEquals(ResultCode.SUCCESS, result.getCode());
		assertTrue(result.getObject().getClass().equals(Long.class));
	}

	/**
	 * 提交反馈 带图片
	 */
	// @Test//反馈不做图片
	public void save2() {
		URL resource = FeedbackResourceTest.class.getResource("/ding.jpg");
		assertNotNull(resource);

		ApiReq<RestResult<?>> req = build("a/v1/user/feedback/save.do");
		req.login();

		req.setPost("content", "我的反馈，我的说明，我的意见。");
		req.putFile("pic", new File(resource.getPath()));

		RestResult<?> result = req.doMultipartPost();
		assertMap(result);
	}

}
