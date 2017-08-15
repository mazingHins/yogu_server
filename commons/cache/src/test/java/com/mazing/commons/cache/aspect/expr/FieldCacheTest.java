package com.mazing.commons.cache.aspect.expr;

import org.junit.Assert;
import org.junit.Test;

import com.mazing.commons.cache.aspect.expr.vo.PersonUser;
import com.yogu.commons.cache.aspect.expr.FieldCache;

/**
 * 测试FieldCache读取对象属性 <br>
 *
 * @author JFan 2015年8月21日 下午6:04:14
 */
public class FieldCacheTest {

	@Test
	public void test() throws Exception {
		PersonUser user = new PersonUser();
		user.setAge(12);
		user.setName("QQ");

		PersonUser person = new PersonUser();
		person.setAge(101);
		person.setName("IsMe");
		person.setUser(user);

		Object value = FieldCache.readField(user, "age");
		Assert.assertEquals(12, value);
		value = FieldCache.readField(user, "name");
		Assert.assertEquals("QQ", value);

		value = FieldCache.readField(person, "age");
		Assert.assertEquals(101, value);
		value = FieldCache.readField(person, "name");
		Assert.assertEquals("IsMe", value);
		value = FieldCache.readField(person, "user");
		Assert.assertEquals(user, value);

		value = FieldCache.readField(person, "sex");
		Assert.assertNull(value);
		person.setSex(false);
		value = FieldCache.readField(person, "sex");
		Assert.assertFalse((Boolean) value);
		person.setSex(true);
		value = FieldCache.readField(person, "sex");
		Assert.assertTrue((Boolean) value);
	}

}
