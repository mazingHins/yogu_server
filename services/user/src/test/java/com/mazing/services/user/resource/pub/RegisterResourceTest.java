package com.mazing.services.user.resource.pub;

import java.util.Map;

import org.junit.Test;

import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * RegisterResource 测试
 */
public class RegisterResourceTest extends HttpResourceTest {

	public RegisterResourceTest() {
		host = "http://userapi.yogubc.com";
		//host = "http://localhost:8090";
	}

	@Test
	public void reg() throws Exception {
		ApiReq<RestResult<?>> req = build("p/v1/user/reg.do");

		// int index = 2;
		req.putPost("countryCode", "86");
		req.putPost("mobile", encrypt("13926426236"));
		req.putPost("password", encrypt("abcd1234"));
		req.putPost("nickname", "kimmy");
		req.putPost("idcode", "301669");
		// req.putPost("lng", "0.0");// base 参数中已包含，也可调用req.putPoint(x, y);
		// req.putPost("lat", "0.0");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("ut"));
		assertNotNull(map.get("uid"));
		assertNotNull(map.get("secret"));
	}

	@Test
	public void mReg() throws Exception {
		final ApiReq<RestResult<?>> req = build("p/v1/user/reg.do");
		req.putPost("countryCode", "86");
		req.putPost("mobile", encrypt("12012345002"));
		req.putPost("password", encrypt("mazing123"));
		req.putPost("nickname", "TTEESSTT");
		req.putPost("idcode", "2291");

		for (int i = 1; i <= 3; i++) {
			final int index = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("run " + index);
					RestResult<?> result = req.doPost();
					Map<?, ?> map = assertMap(result);
					System.out.println("run result " + index + " " + JsonUtils.toJSONString(map));
				}
			}).start();
		}

		Thread.sleep(5000);
		System.out.println("END");
	}

	@Test
	public void regNoPwd() throws Exception {
		ApiReq<RestResult<?>> req = build("p/v1/user/regWithoutPwd.do");

		// int index = 2;
		req.putPost("countryCode", "86");
		req.putPost("mobile", encrypt("13012345090"));
		req.putPost("idcode", "1820");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("ut"));
		assertNotNull(map.get("uid"));
		assertNotNull(map.get("secret"));
	}

}
