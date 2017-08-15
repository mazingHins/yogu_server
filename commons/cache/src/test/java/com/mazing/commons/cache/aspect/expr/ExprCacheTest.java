/**
 * 
 */
package com.mazing.commons.cache.aspect.expr;

import org.junit.Assert;
import org.junit.Test;

import com.mazing.commons.cache.aspect.expr.vo.PersonUser;
import com.mazing.commons.cache.aspect.expr.vo.User;
import com.yogu.commons.cache.aspect.expr.ExprCache;

/**
 * <br>
 *
 * @author JFan 2015年8月21日 下午6:20:27
 */
public class ExprCacheTest {

	@Test
	public void test() throws Exception {
		PersonUser person = new PersonUser();
		person.setAge(101);
		person.setName("IsMe");
		person.setSex(true);

		User user = new User();
		user.setLevel(99);
		user.setQq("10086");

		Object[] args = new Object[] { person, user, 100, false, "mazing" };

		Object[] params = ExprCache.getParams(args, "0.age");
		assertSize(params, 1);
		Assert.assertEquals(101, params[0]);

		params = ExprCache.getParams(args, "0.name");
		assertSize(params, 1);
		Assert.assertEquals("IsMe", params[0]);

		params = ExprCache.getParams(args, "2");
		assertSize(params, 1);
		Assert.assertEquals(100, params[0]);

		params = ExprCache.getParams(args, "3");
		assertSize(params, 1);
		Assert.assertFalse((Boolean) params[0]);

		params = ExprCache.getParams(args, "4");
		assertSize(params, 1);
		Assert.assertEquals("mazing", params[0]);

		params = ExprCache.getParams(args, "0.name , 1.qq ,    3  , 4 ");
		assertSize(params, 4);
		Assert.assertEquals("IsMe", params[0]);
		Assert.assertEquals("10086", params[1]);
		Assert.assertFalse((Boolean) params[2]);
		Assert.assertEquals("mazing", params[3]);
	}

	private void assertSize(Object[] params, int size) {
		Assert.assertNotNull(params);
		Assert.assertEquals(size, params.length);
	}

}
