package com.mazing.commons.utils;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.commons.utils.WordCountUtils;

/**
 * 字符串长度计算工具的测试类
 * 
 * @author felix
 *
 */
public class WordCountUtilsTest {

	/**
	 * 测试纯中文
	 */
	@Test
	public void chineseOnly() {
		String test = "你好啊兄弟";
		Assert.assertEquals(10, WordCountUtils.getWordCount(test));
	}

	/**
	 * 测试纯英文
	 */
	@Test
	public void englishOnly() {
		String test = "ABCDE";
		Assert.assertEquals(5, WordCountUtils.getWordCount(test));
	}

	/**
	 * 中英混合
	 */
	@Test
	public void chinenglish() {
		String test = "你好啊nv";
		Assert.assertEquals(8, WordCountUtils.getWordCount(test));
	}

	/**
	 * 杂食
	 */
	@Test
	public void mixed() {
		String test = "你好啊SB!@#$%^&*()1234567890壹ぜψㄗ⒆";
		Assert.assertEquals(38, WordCountUtils.getWordCount(test));
	}
}
