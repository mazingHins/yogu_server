package com.yogu.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

/**
 * double加减乘除等运算工具
 * 
 * @author Hins
 * @date 2015年9月22日 上午10:38:29
 */
public class ComputeUtils {

	/**
	 * 匹配一位小数最后是0的正则表达式
	 */
	private static final Pattern ONE_DECIMAL_PATTERN = Pattern.compile("^[0-9]+(.[0]{1})?$");
	
	/**
	 * 匹配2位小数最后是0的的正则表达式
	 */
	private static final Pattern TWO_DECIMAL_PATTERN = Pattern.compile("^[0-9]+(.[0-9]{1}+[0]{1})?$");
	
//	private static DecimalFormat df = new DecimalFormat("0.00");

//	/**
//	 * 提供精确的加法运算。
//	 *
//	 * @param v1 被加数
//	 * @param v2 加数
//	 * @return 两个参数的和
//	 */
//
//	public static double add(double v1, double v2) {
//		BigDecimal b1 = new BigDecimal(Double.toString(v1));
//		BigDecimal b2 = new BigDecimal(Double.toString(v2));
//		return b1.add(b2).doubleValue();
//	}

//	/**
//	 * 提供精确的减法运算。
//	 *
//	 * @param v1 被减数
//	 * @param v2 减数
//	 * @return 两个参数的差
//	 */
//
//	public static double sub(double v1, double v2) {
//		BigDecimal b1 = new BigDecimal(Double.toString(v1));
//		BigDecimal b2 = new BigDecimal(Double.toString(v2));
//		return b1.subtract(b2).doubleValue();
//	}

	/**
	 * 元单位转成分，元小数点后面2位忽略
	 * 
	 * @author Hins
	 * @date 2015年11月28日 下午12:01:02
	 * 
	 * @param value
	 * @return
	 */
	public static long yuanToFen(String value) {
		BigDecimal bd = new BigDecimal(value);
		return bd.multiply(new BigDecimal(100)).longValue();
	}

	/**
	 * 分转元，精确到小数点后2位
	 * @param amount 金额，单位：分
	 * @return 返回元
	 * @author ten 2015/11/28
	 */
	public static String fenToYuan(long amount) {
		BigDecimal value = new BigDecimal(amount);
		value = value.divide(new BigDecimal(100));
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}
	
	/**
	 * 分转元，若小数点后面有多余的0，则去掉<br>
	 * 去除规则：如果小数点最后2位都是0，小数点+2位都去掉
	 * 		     如果小数点后只有一位小数且是0，则小数点+1位去掉
	 * 
	 * @param amount 金额，单位：分
	 * @return 返回元
	 * @author hins 2015/12/23
	 */
	public static String fenToYuanInt(long amount) {
		BigDecimal value = new BigDecimal(amount);
		value = value.divide(new BigDecimal(100));
		DecimalFormat df = new DecimalFormat("0.00");
		String result = df.format(value);
		Matcher matcher = TWO_DECIMAL_PATTERN.matcher(result);
		// 如果2位小数的最后一位是0，则去掉
		if (matcher.find()) {
			result = result.substring(0, result.length() - 1);
		}
		matcher = ONE_DECIMAL_PATTERN.matcher(result);
		// 如果小数点最后一位是0，则去掉
		if (matcher.find()) {
			result = result.substring(0, result.length() - 2);
		}
		return result;
	}
	
	/**
	 * 分转元，并精确成整数单位
	 * 
	 * @author Hins
	 * @date 2015年12月31日 下午4:17:32
	 * 
	 * @param amount 金额，单位：分
	 * @return 整数元单位
	 */
	public static long fenToYuanLong(long amount){
		BigDecimal value = new BigDecimal(amount);
		value = value.divide(new BigDecimal(100));
		return value.longValue();
	}
	
	/**
	 * 乘法运算
	 * 
	 * @author Hins
	 * @date 2015年12月13日 下午9:41:12
	 * 
	 * @param v1 - 原金额
	 * @param v2 - 乘数
	 * @return 返回相乘后的分单位
	 */
	public static long multCeiling(long v1, long v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).longValue();
	}
	
	/**
	 * 乘法运算：先（100-乘数v2）除以100，再进行乘法运算（结果向下取整）<br>
	 * 方法使用场景：用于计算折扣券的优惠金额（折扣值 x100，即7.5折扣，v2=75）
	 * 
	 * @author Hins
	 * @date 2016年1月6日 上午9:42:35
	 * 
	 * @param v1 - 原金额
	 * @param v2 - 乘数
	 * @return 返回相乘后的分单位
	 */
	public static long initCouponFee(long v1, int v2) {
		BigDecimal b1 = new BigDecimal(v1);
		int mult = 100 - v2;
		if (mult <= 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "优惠券金额异常，请重新尝试");
		}
		BigDecimal b2 = new BigDecimal(mult);
		b2 = b2.divide(new BigDecimal(100));
		return b1.multiply(b2).longValue();
	}
	
	/**
	 * 将分单位的v1除以v2，结果还是分单位<br>
	 * 如果除数=0，直接返回0。结果向上取整（精确到个位数）
	 * 
	 * @author Hins
	 * @date 2015年12月21日 上午11:47:48
	 * 
	 * @param v1 - 原金额（分）
	 * @param v2 - 除数
	 * @return 相除后的分单位
	 */
	public static long div(long v1, int v2) {
		if (v2 == 0) {
			return 0;
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, 0 , BigDecimal.ROUND_CEILING).longValue();
	}
	
	/**
	 * 将单位的v1除以v2，结果还是分单位<br>
	 * v1/v2，如果除数=0，直接返回0
	 * 
	 * @param v1 - 原金额（分）
	 * @param v2 - 除数
	 * @author hins
	 * @date 2016年9月23日 下午4:50:34
	 * @return long
	 */
	public static long divByLong(long v1, long v2) {
		if(v2 ==0){
			return 0;
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, 0 , BigDecimal.ROUND_CEILING).longValue();
	}
	
	
	/**
	 * 除法运算：除以10，并将结果精确到小数点后1位<br>
	 * 如果小数=0，则去除小数点后的数字（包括小数点），返回小数点之前的值
	 * 
	 * @author Hins
	 * @date 2016年1月5日 下午3:16:38
	 * 
	 * @param v - 被除数
	 * @return 除法运算后结果
	 */
	public static String divTen(long v) {
		BigDecimal value = new BigDecimal(v);
		value = value.divide(new BigDecimal(10));
		DecimalFormat df = new DecimalFormat("0.0");
		String result = df.format(value);
		if (result.indexOf(".0") > -1) {
			return result.substring(0, result.length() - 2);
		}
		return result;
	}
	
	/**
	 * 减法运算<br>
	 * v1-v2.返回值可能少于0(v2>v1)
	 * @param v1 - 被减数
	 * @param v2 - 减数
	 * @author hins
	 * @date 2016年9月23日 下午4:38:36
	 * @return long
	 */
	public static long subtract(long v1, long v2){
		BigDecimal value1 = new BigDecimal(v1);
		BigDecimal value2 = new BigDecimal(v2);
		return value1.subtract(value2).longValue();
	}
	
	/**
	 * long 类型金额转int类型，精度不变
	 * @param v
	 * @author hins
	 * @date 2016年10月10日 下午2:55:50
	 * @return int
	 */
	public static int longToInt(long v){
		BigDecimal value = new BigDecimal(v);
		return value.intValue();
	}
	
	/**
	 * 2个数相加
	 * @param v1
	 * @param v2
	 * @author hins
	 * @date 2016年10月11日 下午5:54:21
	 * @return long
	 */
	public static long add(long v1, long v2){
		BigDecimal value1 = new BigDecimal(v1);
		BigDecimal value2 = new BigDecimal(v2);
		return value1.add(value2).longValue();
	}
	
	/**
	 * 3个数相加
	 * @param v1
	 * @param v2
	 * @param v3
	 * @return
	 * @author hins
	 * @date 2016年10月11日 下午6:53:46
	 * @return long
	 */
	public static long add(long v1, long v2, long v3){
		BigDecimal value1 = new BigDecimal(v1);
		BigDecimal value2 = new BigDecimal(v2);
		BigDecimal value3= new BigDecimal(v3);
		return value1.add(value2).add(value3).longValue();
	}

//	/**
//	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
//	 *
//	 * @param v1 被除数
//	 * @param v2 除数
//	 * @return 两个参数的商
//	 */
//
//	public static double div(double v1, double v2) {
//		return div(v1, v2, DEF_DIV_SCALE);
//	}

//	/**
//	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
//	 *
//	 * @param v1 被除数
//	 * @param v2 除数
//	 * @param scale 表示表示需要精确到小数点以后几位。
//	 * @return 两个参数的商
//	 */
//	public static double div(double v1, double v2, int scale) {
//		if (scale < 0) {
//			throw new ServiceException(ResultCode.PARAMETER_ERROR, "精度不能小于0");
//		}
//		BigDecimal b1 = new BigDecimal(Double.toString(v1));
//		BigDecimal b2 = new BigDecimal(Double.toString(v2));
//		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
//	}

//	/**
//	 * 将double值转成字符串，精确到2位小数。
//	 * 如double=1，转换后等于1.00
//	 * @author Hins
//	 * @date 2015年9月22日 下午3:52:28
//	 *
//	 * @param db 要转换的值
//	 * @return 转换后的值
//	 */
//	public static String doubleToStr(double db) {
//		return new DecimalFormat("0.00").format(db);
//	}
//

	public static void main(String[] args) {
		long totalFee = 111;
//		System.out.println(fenToYuan(v));
		long couponFee = initCouponFee(totalFee, 80);
		System.out.println(totalFee - couponFee);
		System.out.println(divByLong(20, 0));
		
		System.out.println(multCeiling(1, 5));
		
	}
}
