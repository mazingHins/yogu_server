package com.yogu.commons.utils.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 10进制和36进制的互转
 * 
 * @author caven 2013/7/10
 */
public class ThirtySixCountUtil {

	public static final char[] characterSet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//	private static final String[] NUM_TO_STRING = { "0", "1", "2", "3", "4",
//			"5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
//			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
//			"V", "W", "X", "Y", "Z" };

	private static final Map<String, Integer> STRING_TO_NUM = new HashMap<String, Integer>(
			88);
	static {
		STRING_TO_NUM.put("0", 0);
		STRING_TO_NUM.put("1", 1);
		STRING_TO_NUM.put("2", 2);
		STRING_TO_NUM.put("3", 3);
		STRING_TO_NUM.put("4", 4);
		STRING_TO_NUM.put("5", 5);
		STRING_TO_NUM.put("6", 6);
		STRING_TO_NUM.put("7", 7);
		STRING_TO_NUM.put("8", 8);
		STRING_TO_NUM.put("9", 9);
		STRING_TO_NUM.put("A", 10);
		STRING_TO_NUM.put("B", 11);
		STRING_TO_NUM.put("C", 12);
		STRING_TO_NUM.put("D", 13);
		STRING_TO_NUM.put("E", 14);
		STRING_TO_NUM.put("F", 15);
		STRING_TO_NUM.put("G", 16);
		STRING_TO_NUM.put("H", 17);
		STRING_TO_NUM.put("I", 18);
		STRING_TO_NUM.put("J", 19);
		STRING_TO_NUM.put("K", 20);
		STRING_TO_NUM.put("L", 21);
		STRING_TO_NUM.put("M", 22);
		STRING_TO_NUM.put("N", 23);
		STRING_TO_NUM.put("O", 24);
		STRING_TO_NUM.put("P", 25);
		STRING_TO_NUM.put("Q", 26);
		STRING_TO_NUM.put("R", 27);
		STRING_TO_NUM.put("S", 28);
		STRING_TO_NUM.put("T", 29);
		STRING_TO_NUM.put("U", 30);
		STRING_TO_NUM.put("V", 31);
		STRING_TO_NUM.put("W", 32);
		STRING_TO_NUM.put("X", 33);
		STRING_TO_NUM.put("Y", 34);
		STRING_TO_NUM.put("Z", 35);
	}

	/**
	 * 将10进制的数字decimalValue，转换成36进制的字符串<br/>
	 * 36进制的字符串的长度小于length，则会在36进制的字符串前补足相应位数的“0”<br/> 例如返回36进制的字符串为 My6，<br/>
	 * 如果length==0，则返回My6 <br/> 如果length==5，则返回00My6 <br/> 该方法会在一些特定场景中使用 <br/>
	 * 
	 * @param decimalValue
	 *            需要进行转换的10进制数字
	 * @param length
	 *            指定返回字符串的最小长度
	 * @return
	 */
	public static String gen(long decimalValue, int length) {
		return thirtySixCount(decimalValue, new StringBuilder(), length);
	}

	/**
	 * 将10进制的数字decimalValue，转换成36进制的字符串<br/>
	 * 
	 * @param decimalValue
	 *            需要进行转换的10进制数字
	 * @return
	 */
	public static String gen(long decimalValue) {
		return gen(decimalValue, 0);
	}

	/**
	 * 将10进制的数字decimalValue，转换成36进制的字符串
	 * 
	 * @param decimalValue
	 *            需要进行转换的10进制数字
	 * @param value
	 *            递归时使用的值
	 * @param length
	 *            指定返回字符串的最小长度
	 * @return
	 */
	private static String thirtySixCount(long decimalValue, StringBuilder sb,
			int length) {
		int last = (int) (decimalValue % 36);
		long head = decimalValue / 36;
		// sb.insert(0, NUM_TO_STRING[last]);
		sb.insert(0, characterSet[last]);
		if (head >= 36) {
			return thirtySixCount(head, sb, length);
		} else {
			if (head != 0) {
				// sb.insert(0, NUM_TO_STRING[(int)head]);
				sb.insert(0, characterSet[(int)head]);
			}
			length = length - sb.length();
			for (int i = 0; i < length; i++) {
				sb.insert(0, "0");
			}
			return sb.toString();
		}
	}

	/**
	 * 将36进制的字符串，转换成10进制的数字
	 * 
	 * @param value
	 *            36进制的字符串
	 * @return
	 */
	public static long gen(String value) {
		long no = 0;
		StringBuilder sb = new StringBuilder(value);
		if (sb != null) {
			for (int i = 0; i < sb.length(); i++) {
				String getValue = sb.substring(i, i + 1);
				int getMapNo = STRING_TO_NUM.get(getValue);
				if (i == sb.length() - 1) {
					no = no + getMapNo;
				} else {
					no = (long) Math.pow(36, sb.length() - i - 1) * getMapNo
							+ no;
				}
			}
		}
		return no;
	}

	public static void main2(String[] args) {
		// 2074 1893456000 3287232000000
		// 2014 11185577 1404961617105
		long a = System.currentTimeMillis() - 1393776000000L;
		// 取得分钟数
		int time = (int)( a / (1000 * 60));
		System.out.println(time);
		System.out.println(gen(time, 0));
		System.out.println(gen(gen(time, 0)));
		long num = gen("XQNBEXBH");
		System.out.println(num);
		System.out.println(gen(num + 1));
	}
}