package com.mazing.services.user.resourceapi;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * LocalPushApiResource 测试类
 * @author felix
 *
 */
public class LocalPushApiResourceTest extends HttpResourceTest {
	public LocalPushApiResourceTest() {
		host = "http://userapi.mazing.com";
	}

	@Test
	public void testPushParam() {
		ApiReq<RestResult<?>> req = build("api/user/push.do");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("msg", "............................");
		param.put("uid", "111001");

		Map<String, Object> customerFields = new HashMap<String, Object>();
		customerFields.put("msgId", "adgffffa");
		customerFields.put("type", 2);
		Map<String, Object> additionalFields = new HashMap<String, Object>();
		additionalFields.put("action", "bCancelSfExpress");
		additionalFields.put("orderNo", "1610145788431722");
		additionalFields.put("storeId", "12701");

		customerFields.put("params", additionalFields);

		param.put("customFields", customerFields);

		req.putPost("params", JsonUtils.toJSONString(param));

		RestResult<?> result = req.doPost();

		assertEquals(result.getCode(), ResultCode.SUCCESS);
	}
}
