package com.mazing.services.user.resource.pub;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * 用户登录测试类 <br>
 * 
 * @author JFan 2015年7月24日 上午11:24:36
 */
public class LoginResourceTest extends HttpResourceTest {

	public LoginResourceTest() {
//		host = "http://119.23.15.225:8080/yogu-api-user";
		host = "http://userapi.yogubc.com/";
		//host = "http://localhost:8090";
	}

	/**
	 * 登录
	 */
	@Test
	public void login() throws Exception {
		ApiReq<RestResult<?>> req = build("p/v1/user/login.do");
		req.putPost("countryCode", "86");
		//req.putPost("mobile", encrypt("18600000001"));
		//req.putPost("password", encrypt("zhj5529636"));
		req.putPost("mobile", encrypt("13926426236"));
		req.putPost("password", encrypt("abcd1234"));

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("ut"));
		assertNotNull(map.get("uid"));
		assertNotNull(map.get("secret"));
	}

	@Test
	public void loginNoPwd() throws Exception {
		ApiReq<RestResult<?>> req = build("p/v1/user/loginWithoutPwd.do");
		req.putPost("countryCode", "86");
		req.putPost("mobile", encrypt("13012345092"));
		req.putPost("idcode", "736471");

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("ut"));
		assertNotNull(map.get("uid"));
		assertNotNull(map.get("secret"));
	}

	@Test
	public void testSmsCode() throws Exception {
		ApiReq<RestResult<?>> req = build("p/v1/user/smsCode.do");
		req.putPost("countryCode", "86");
		req.putPost("receiver", encrypt("13012345095"));

		RestResult<?> result = req.doPost();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("isRegistered"));
	}

}
