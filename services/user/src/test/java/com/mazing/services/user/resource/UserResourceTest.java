package com.mazing.services.user.resource;

import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * @author Hins
 * @version createTime：2015年7月16日 下午6:53:47 类说明
 */
public class UserResourceTest extends HttpResourceTest {

	public UserResourceTest() {
		userHost = "http://userapi.mazing.com";
		host = "http://userapi.mazing.com";
	}

//	@Test
	public void init() {
		ApiReq<RestResult<?>> req = build("a/v1/user/profile");
		req.login();

		RestResult<?> result = req.doGet();
		Map<?, ?> map = assertMap(result);

		assertNotNull(map.get("nickname"));
	}

//	@Test
	public void update() {
		ApiReq<RestResult<?>> req = build("a/v1/user/profile/update.do");
		req.login();

//		String nickname = "junjun" + DateUtils.currentTimeSeconds();
		req.setPost("isPush", "1");
		RestResult<?> result = req.doPost();
		assertMap(result);// update.do 返回null数据，需要修改（返回最新数据）后再跑测试

//		req.setUri("a/v1/user/profile");
//		result = req.doGet();
//		Map<?, ?> map = assertMap(result);
//		Object nn = map.get("nickname");
//		assertEquals(nickname, nn);
	}
	/**
	 * 
	 * 修复imid 
	 *
	 * @author ben
	 * @date 2015年11月16日 下午1:59:15
	 */
//	@Test
	public void fixImId() {
		ApiReq<RestResult<?>> req = build("api/user/fixImID.do");
		req.login();
		RestResult<?> result = req.doPost();
		assertMap(result);// update.do 返回null数据，需要修改（返回最新数据）后再跑测试
	}
	
//	@Test
	public void updateSex() {
		ApiReq<RestResult<?>> req = build("a/v1/user/profile/updateSex.do");
		req.login("86", "13580480984", "12345678");

//		String nickname = "junjun" + DateUtils.currentTimeSeconds();
		req.setPost("sex", "2");
		req.doPost();
	}

	@Test
	public void updateNickname() {
		ApiReq<RestResult<?>> req = build("a/v1/user/profile/updateNickname.do");
		req.login();

		//
		req.setPost("nickname", "");
		RestResult<?> result = req.doPost();

		assertEquals(ResultCode.PARAMETER_ERROR, result.getCode());
		assertEquals("请输入昵称！", result.getMessage());

		//
		req.setPost("nickname", "AAAAAAAAAAAAAAA嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿嘿");
		result = req.doPost();

		assertEquals(ResultCode.PARAMETER_ERROR, result.getCode());
		assertEquals("昵称最多只能输入15个中文，或者30个英文！", result.getMessage());

		//
		req.setPost("nickname", "张李王");
		result = req.doPost();

		assertEquals(ResultCode.SUCCESS, result.getCode());
	}
	
	@Test
	public void listByTag(){
		ApiReq<RestResult<?>> req = build("a/v1/user/customize/listCustomize");
		req.login("86", "13926426236", "abcd1234");

		RestResult<?> result = req.doGet();
	}
	
	@Test
	public void uploadUserTag(){
		ApiReq<RestResult<?>> req = build("a/v1/user/customize/uploadUser.do");
		req.login("86", "13926426236", "abcd1234");

		req.setPost("city", "020");
		req.setPost("customizeIds", "30101,30201,30102");
		RestResult<?> result = req.doPost();
	}

}
