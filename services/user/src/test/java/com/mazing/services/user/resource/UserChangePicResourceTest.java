package com.mazing.services.user.resource;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.junit.Test;

import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;

/**
 * 测试更新头像 <br>
 *
 * @author JFan 2015年7月28日 下午7:12:13
 */
public class UserChangePicResourceTest extends HttpResourceTest {

	public UserChangePicResourceTest() {
		host = "http://userapi.mazing.com";
	}

	/**
	 * 修改头像
	 */
	@Test
	public void changePic() {
		URL resource = UserChangePicResourceTest.class.getResource("/pic.jpg");
		assertNotNull(resource);

		ApiReq<RestResult<?>> req = build("a/v1/user/profile/changePic.do");
		req.login();

		req.putFile("pic", new File(resource.getPath()));

		RestResult<?> result = req.doMultipartPost();
		System.out.println(result);
		Map<?, ?> map = assertMap(result);
		Object url = map.get("profileUrl");
		assertNotNull(url);
	}

}
