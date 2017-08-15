package com.yogu.core.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.yogu.core.web.exception.ServiceException;

/**
 * 参数判断的工具类
 * 
 * @author linyi 2014年3月21日
 */
public class ParameterUtil {

	/**
	 * 从http request中取出参数'key'的值，并转换成short类型返回<br>
	 * value为空时(包括不传)，返回0
	 */
	public static short getShortParam(HttpServletRequest request, String key) {
		return getShortParam(request, key, (short) 0);
	}

	/**
	 * 从http request中取出参数'key'的值，并转换成short类型返回<br>
	 * value为空时(包括不传)，使用指定的默认值
	 */
	public static short getShortParam(HttpServletRequest request, String key, short def) {
		String value = request.getParameter(key);
		if (StringUtils.isBlank(value))
			return def;
		return Short.parseShort(value);
	}

	/**
	 * 从http request中取出参数'key'的值，并转换成int类型返回<br>
	 * value为空时(包括不传)，返回0
	 */
	public static int getIntParam(HttpServletRequest request, String key) {
		return getIntParam(request, key, 0);
	}

	/**
	 * 从http request中取出参数'key'的值，并转换成int类型返回<br>
	 * value为空时(包括不传)，使用指定的默认值
	 */
	public static int getIntParam(HttpServletRequest request, String key, int def) {
		String value = request.getParameter(key);
		if (StringUtils.isBlank(value))
			return def;
		return Integer.parseInt(value);
	}

	/**
	 * 从http request中取出参数'key'的值，并转换成int类型返回<br>
	 * value为空时(包括不传)，返回0
	 */
	public static long getLongParam(HttpServletRequest request, String key) {
		return getIntParam(request, key, 0);
	}

	/**
	 * 从http request中取出参数'key'的值，并转换成int类型返回<br>
	 * value为空时(包括不传)，使用指定的默认值
	 */
	public static long getLongParam(HttpServletRequest request, String key, long def) {
		String value = request.getParameter(key);
		if (StringUtils.isBlank(value))
			return def;
		return Integer.parseInt(value);
	}

	/**
	 * 按指定的字符分隔字符串，转换成String[]<br>
	 * 忽略blank的
	 */
	public static String[] str2strs(String str, char separatorChars) {
		String string = StringUtils.trimToNull(str);
		if (null == string)
			return null;

		String[] split = StringUtils.split(string, separatorChars);
		List<String> list = new ArrayList<String>();
		for (String s : split) {
			String tmp = StringUtils.trimToNull(s);
			if (null == tmp)
				continue;
			list.add(tmp);
		}

		return list.toArray(new String[list.size()]);
	}

	/**
	 * 按指定的字符分隔字符串，转换成String[]<br>
	 * 忽略blank的
	 */
	public static String[] str2strs(String str, String separatorChars) {
		String string = StringUtils.trimToNull(str);
		if (null == string)
			return null;

		String[] split = StringUtils.split(string, separatorChars);
		List<String> list = new ArrayList<String>();
		for (String s : split) {
			String tmp = StringUtils.trimToNull(s);
			if (null == tmp)
				continue;
			list.add(tmp);
		}

		return list.toArray(new String[list.size()]);
	}

	/**
	 * 将英文逗号分隔的字符串，转换成String[]<br>
	 * 忽略blank的
	 */
	public static String[] str2strs(String str) {
		return str2strs(str, ",");
	}

	/**
	 * 将英文逗号分隔的字符串，转换成int[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static int[] str2ints(String str) {
		return str2ints(str, ",");
	}

	/**
	 * 将指定符号分隔的字符串，转换成int[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static int[] str2ints(String str, String separatorChars) {
		String[] strs = str2strs(str);
		if (null == strs)
			return null;

		int[] ls = new int[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Integer.parseInt(tmp);
		return ls;
	}

	/**
	 * 将英文逗号分隔的字符串，转换成long[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static long[] str2longs(String str) {
		return str2longs(str, ",");
	}

	/**
	 * 将指定符号分隔的字符串，转换成long[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static long[] str2longs(String str, String separatorChars) {
		String[] strs = str2strs(str, separatorChars);
		if (null == strs)
			return new long[0];

		long[] ls = new long[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Long.parseLong(tmp);
		return ls;
	}

	/**
	 * 将英文逗号分隔的字符串，转换成long[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static short[] str2shorts(String str) {
		return str2shorts(str, ",");
	}

	/**
	 * 将英文逗号分隔的字符串，转换成long[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static short[] str2shorts(String str, String separatorChars) {
		String[] strs = str2strs(str, separatorChars);
		if (null == strs)
			return null;

		short[] ls = new short[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Short.parseShort(tmp);
		return ls;
	}

	/**
	 * 将英文逗号分隔的字符串，转换成double[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static double[] str2double(String str) {
		return str2double(str, ",");
	}

	/**
	 * 将指定字符串分隔的字符串，转换成double[]<br>
	 * 忽略blank的<br>
	 * 中间转换异常，则抛出外层
	 */
	public static double[] str2double(String str, String separatorChars) {
		String[] strs = str2strs(str, separatorChars);
		if (null == strs)
			return null;

		double[] ls = new double[strs.length];
		int index = 0;
		for (String tmp : strs)
			ls[index++] = Double.parseDouble(tmp);
		return ls;
	}
	
	/**
	 * 将Number类型列表转成字符串，每个元素用英文逗号分隔
	 * @param list - 列表数据
	 * @author hins
	 * @date 2016年9月22日 下午5:11:40
	 * @return String
	 */
	public static String listToString(List<? extends Number> list) {
		if (list == null || list.isEmpty())
			return "";
		return StringUtils.join(list, ",");
	}
	
	
	/**
	 * 将Long类型数组转成字符串，每个元素用英文逗号分隔
	 * @param array 数组
	 * @author hins
	 * @date 2016年9月22日 下午5:14:09
	 * @return String
	 */
	public static String listToString(Long[] array) {
		if (array == null || array.length ==0)
			return "";

		return StringUtils.join(array, ",");
	}
	
	/**
	 * 限定参数不能为空（null，""），也不可以全部字符是空格，否则抛出ServiceException
	 * 
	 * @param s
	 *            - 字符串
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws com.yogu.core.web.exception.ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertNotBlank(String s, String errorMessage) {
		if (StringUtils.isBlank(s)) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定参数必须为true，否则抛出ServiceException
	 * 
	 * @param b
	 *            - 布尔值
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 * 
	 * @author liuying 2014-08-18
	 */
	public static void assertTrue(boolean b, String errorMessage) {
		if (!b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}
	
	/**
	 * 限定参数的长度不能超过指定值，否则抛出异常。 当 s 不等于null，并且长度超出maxLength才抛出异常。
	 * 
	 * @param s
	 *            - 要判断的字符串
	 * @param maxLength
	 *            - 指定长度
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertMaxLength(String s, int maxLength,
			String errorMessage) {
		if (s != null && s.length() > maxLength) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定一个整数不能是负数，否则抛出ServiceException
	 * 
	 * @param n
	 *            - 要判断的整数
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertNonnegativeInt(int n, String errorMessage) {
		if (n < 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定a必须大于等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanOrEqual(int a, int b,
			String errorMessage) {
		if (a < b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}
	

	/**
	 * 限定a必须大于等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanOrEqual(short a, short b,
			String errorMessage) {
		if (a < b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}
	
	/**
	 * 限定a必须等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertEqual(int a, int b,
			String errorMessage) {
		if (a != b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定a必须大于等于b，否则抛出ServiceException
	 * 
	 * @param a
	 * @param b
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanOrEqual(long a, long b,
			String errorMessage) {
		if (a < b) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 限定输入值必须是指定列表的其中之一，否则抛出ServiceException
	 * 
	 * @param a
	 *            - 输入值
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @param values
	 *            - 指定列表
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertOneOfThem(short a, String errorMessage,
			short... values) {
		boolean pass = false;
		if (values != null) {
			for (short n : values) {
				if (a == n) {
					pass = true;
					break;
				}
			}
		} else {
			throw new IllegalArgumentException("values为空");
		}
		if (!pass) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断参数必须大于 0，否则抛出异常
	 * 
	 * @param n
	 *            - 要判断的数字
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanZero(int n, String errorMessage) {
		if (n < 1) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断参数必须大于 0，否则抛出异常
	 *
	 * @param n
	 *            - 要判断的数字
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertGreaterThanZero(long n, String errorMessage) {
		if (n < 1L) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断参数列表里必须有一个大于0
	 * 
	 * @param values
	 *            - 要判断的数字组合
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertOneOfThemThanZero(String errorMessage,
			int... values) {
		boolean exception = true;
		if (values != null) {
			for (int n : values) {
				if (n >= 0) {
					exception = false;
					break;
				}
			}
		}
		if (exception) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}

	}

	/**
	 * 判断参数不能为null，否则抛出异常
	 * 
	 * @param o
	 *            - 要判断的参数
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertNotNull(Object o, String errorMessage) {
		if (o == null) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 判断时间范围的合法性，end>=start，否则抛出异常
	 * 
	 * @param start
	 *            - 开始时间，不能为null
	 * @param end
	 *            - 结束时间，不能为null
	 * @param errorMessage
	 *            - 出错时的异常错误信息
	 * @throws ServiceException
	 *             - 出错时抛出异常
	 */
	public static void assertTimeRange(Date start, Date end, String errorMessage) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("start或end为空");
		}
		if (start.after(end)) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, errorMessage);
		}
	}

	/**
	 * 确保页码是正确的
	 * 
	 * @param page
	 *            - 要判断的页码
	 * @return 如果页面错误，令页码=1，否则返回传入的值
	 */
	public static int ensurePage(int page) {
		if (page < 1) {
			page = 1;
		}
		return page;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] str2Arrars(String str, Class<T> clazz) throws Exception {
		String[] split = str.split(",");
		List<T> list = new ArrayList<T>(split.length);
		for(int i = 0; i < split.length; i++){
			if(clazz.newInstance() instanceof Integer){
				list.add((T) split[i]);
			}
		}
		System.out.println(Arrays.toString((T[]) list.toArray()));
		return (T[])list.toArray();
	}
	
	public static void main(String[] args) throws Exception{
		String str = "1,2,3";
		Integer[] str2Arrars = ParameterUtil.str2Arrars(str, Integer.class);
		System.out.println(str2Arrars);
	}
}
