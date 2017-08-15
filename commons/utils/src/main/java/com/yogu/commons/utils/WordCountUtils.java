package com.yogu.commons.utils;

import java.util.Arrays;

/**
 * 计算字符串长度的工具类
 * @author felix
 *
 */
public class WordCountUtils {
	
	/**
	 * 从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。
	 * 
	 * @param s
	 * @return 字符的字节长度
	 * 
	 * @author felix
	 */
	public static int getWordCount(String s) {
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			int ascii = Character.codePointAt(s, i);
			if (ascii >= 0 && ascii <= 255)
				length++;
			else
				length += 2;
		}
		return length;
	}
	
	/**
	 * 根据要求的长度，截断文本，并返回前面部分的内容<br>
	 * 建议size为偶数<br>
	 * 注意：一个中文表示2个长度，所以返回的内容可能会比要求的长度还要长1
	 */
	public static String cutsWord(String word, int size) {
		if (StringUtils.isEmpty(word))
			return word;
		if (0 >= size)
			return StringUtils.EMPTY;

		int tmpSize = 0;
		int finalLength = 0;
		char[] tmp = word.toCharArray();
		for (; tmpSize < size && finalLength < tmp.length;) {
			int ascii = Character.codePointAt(tmp, finalLength);
			if (255 < ascii || ascii < 0)
				tmpSize += 2;
			else
				tmpSize += 1;
			finalLength += 1;
		}

		// 最终的数据
		if (finalLength >= tmp.length)
			return word;
		return new String(Arrays.copyOf(tmp, finalLength));
	}
	
	

}
