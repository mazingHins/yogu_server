package com.mazing.services.user.resource.pub;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.core.KeyValue;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.core.test.ApiReq;
import com.yogu.core.test.HttpResourceTest;
import com.yogu.core.web.RestResult;
import com.yogu.services.user.base.dao.impl.UserDaoImpl;

public class PasswordResourceTest extends HttpResourceTest {

	public PasswordResourceTest() {
		// host = "http://userapi.mazing.com";
		// userHost = "http://userapi.mazing.com";
		host = "http://localhost:8090";
		userHost = "http://localhost:8090";
	}

	@Test
	public void testUpdatePassword() throws Exception {

		// ApiReq.testOnLocalServer(true, host);

		ApiReq<RestResult<?>> req = build("p/v1/user/password/update.do");

		KeyValue<String, String> kv = req.login();

		// 旧的userToken
		String old_userToken = kv.getKey();

		Assert.assertNotNull(old_userToken);

		// post 参数
		String mobile = "13580480984";
		String password = "12345678";

		password = encrypt(password);
		mobile = encrypt(mobile);

		// 没有加密的密码
		String old_password = "12345678";
		// req.putPost("oldpassword", old_password);

		// put post params
		req.putPost("oldpassword", encrypt(old_password));
		req.putPost("password", password);
		req.putPost("mobile", mobile);
		req.putPost("countryCode", "86");

		req.putGet("ut", old_userToken);

		// 请求 修改密码
		RestResult<?> result = req.doPost();

		// 没有加密的参数 断言
		// assertEquals(10, result.getCode());

		assertEquals(1, result.getCode());
	}

	@Test
	public void testResetPassword() throws Exception {

		ApiReq<RestResult<?>> req = build("p/v1/user/password/reset.do");

		String mobile = "18144722133";
		String password = "12345678";
		String idcode = "test";

		// error mobile
		// String mobile = "13268233163";

		password = encrypt(password);
		mobile = encrypt(mobile);

		// idcode 缓存key
		String idcode_key = "IdCodeService.code_" + mobile;

		// 设置 短信验证码 到缓存
		redis().setex(idcode_key, 2 * 60, idcode);

		// error idcode
		// req.putPost("idcode", "test2");

		// put post params
		req.putPost("idcode", idcode);
		req.putPost("password", password);
		req.putPost("mobile", mobile);
		req.putPost("countryCode", "86");

		RestResult<?> result = req.doPost();
		assertEquals(1, result.getCode());

	}

	@Test
	public void testRedis() throws InterruptedException {
		// String key =
		// "17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...17:36:34.832 [main] INFO  c.m.m.i.l.LocalBroadcastRedisImpl - mq#LocalBroadcastRedisImpl#init start ...testaaaaaaaaaaaaaaaaaaalxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxlllllllllllllllllllllllllaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaddddddddddddddddddddddddddddsssssssssssssasdfassssssssssssssssssssssssssssssseeeecssssssssssccccccccc";
		// redis().set(key, "test_redis");
		// String value = redis().get(key);
		// System.out.println(value);

		for (int i = 0; i < 15; i++) {

			testRedisCount();
			Thread.sleep(1000);

		}

	}

	/**
	 * redis计数测试
	 */
	private void testRedisCount() {
		int num = 3;// 10次
		int second = 5;// 5s
		String key = "test_expire_6";// key

		int total = NumberUtils.toInt(redis().get(key));
		System.out.println("current count=" + total + ", currTime=" + System.currentTimeMillis());

		if (total <= 0)
			redis().del(key);

		if (total < num) {
			redis().incr(key);
			if (total <= 0)
				redis().expire(key, second);
			System.out.println("current count < set count");
		} else {
			System.out.println("current count >set count");
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		ApiReq<RestResult<?>> req = build("p/v1/user/password/update.do");
		req.login("86", "13580480984", "12345678");
		
		// post 参数
		String mobile = "13580480984";
		String password = "12345678a";

		password = encrypt(password);
		mobile = encrypt(mobile);

		// 没有加密的密码
		String old_password = "12345678";
		// req.putPost("oldpassword", old_password);

		// put post params
		req.putPost("oldpassword", encrypt(old_password));
		req.putPost("password", password);
		req.putPost("mobile", mobile);
		req.putPost("countryCode", "86");
		
		RestResult<?> result = req.doPost();
		assertEquals(1, result.getCode());
	}

	private Redis redis() {
		return (Redis) MainframeBeanFactory.getBean("redis");
	}

	public static void main(String[] args) {
		String mobile = "13268233163";
		String table = UserDaoImpl.getTableName("86", mobile);

		System.out.println(table);
	}

}
