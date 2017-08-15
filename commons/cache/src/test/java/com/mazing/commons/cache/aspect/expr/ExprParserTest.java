/**
 * 
 */
package com.mazing.commons.cache.aspect.expr;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.commons.cache.aspect.expr.ExprNode;
import com.yogu.commons.cache.aspect.expr.ExprParser;

/**
 * 测试解析expr <br>
 * 具体格式参考 @CacheCleanExpr 中的说明
 *
 * @author JFan 2015年8月21日 下午6:12:48
 */
public class ExprParserTest {

	/**
	 * 解读expr
	 */
	@Test
	public void ok() throws Exception {
		Assert.assertNull(new ExprParser(null).parser());
		Assert.assertNull(new ExprParser("").parser());
		Assert.assertNull(new ExprParser(" ").parser());

		ExprNode[] parser = new ExprParser("0.name").parser();
		Assert.assertNotNull(parser);
		Assert.assertEquals(1, parser.length);
		Assert.assertEquals(0, parser[0].getArgsIndex());
		Assert.assertEquals("name", parser[0].getFieldName());

		parser = new ExprParser("0.name, 1.qq").parser();
		Assert.assertNotNull(parser);
		Assert.assertEquals(2, parser.length);
		Assert.assertEquals(0, parser[0].getArgsIndex());
		Assert.assertEquals("name", parser[0].getFieldName());
		Assert.assertEquals(1, parser[1].getArgsIndex());
		Assert.assertEquals("qq", parser[1].getFieldName());

		parser = new ExprParser("0.name, 1").parser();
		Assert.assertNotNull(parser);
		Assert.assertEquals(2, parser.length);
		Assert.assertEquals(0, parser[0].getArgsIndex());
		Assert.assertEquals("name", parser[0].getFieldName());
		Assert.assertEquals(1, parser[1].getArgsIndex());
		Assert.assertNull(parser[1].getFieldName());
	}

	// @Test
	public void error() {
	}

}
