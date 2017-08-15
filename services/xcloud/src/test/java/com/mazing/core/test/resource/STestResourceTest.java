package com.mazing.core.test.resource;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * 测试 /s/ 类接口 <br>
 *
 * @author JFan 2015年8月17日 下午7:32:15
 */
public class STestResourceTest extends HttpResourceTest {

	public STestResourceTest() {
		host = "http://localhost:8080";
	}

	@Test
	public void login() {
		ApiReq<RestResult<?>> req = build("s/v1/store/staff");
		req.login();

		RestResult<?> result = req.doGet();
		Map<?, ?> map = assertMap(result);
		assertNotNull(map);
	}

}
