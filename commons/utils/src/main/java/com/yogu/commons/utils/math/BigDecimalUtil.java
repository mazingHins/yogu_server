package com.yogu.commons.utils.math;

import java.math.BigDecimal;
/**
 * BigDecimal工具类
 * @author hedream
 *
 */
public class BigDecimalUtil {

	/**
	 * 计算2个long型数据相除，保留指定位数
	 * @param dividend  被除数
	 * @param divisor   除数 
	 * @param scale     保留小数位数
	 * @return BigDecimal
	 */
	public static BigDecimal divide(Long dividend, Long divisor, int scale){
		if(dividend == null || divisor == null || divisor==0)
			return new BigDecimal(0);
		double d = (double)dividend/divisor;
		return new BigDecimal(d).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 计算2个int型数据相除，保留指定位数
	 * @param dividend  被除数
	 * @param divisor   除数 
	 * @param scale     保留小数位数
	 * @return BigDecimal
	 */
	public static BigDecimal divide(int dividend, int divisor, int scale){
		if(divisor == 0)
			return new BigDecimal(0);
		double d = (double)dividend/divisor;
		return new BigDecimal(d).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 计算2个long型数据相除，保留指定位数
	 * @param dividend  被除数
	 * @param divisor   除数 
	 * @param scale     保留小数位数
	 * @return BigDecimal
	 */
	public static BigDecimal divide(Double dividend, Long divisor, int scale){
		if(dividend == null || divisor == null || divisor==0)
			return new BigDecimal(0);
		double d = (double)dividend/divisor;
		return new BigDecimal(d).setScale(scale,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 转换成百分比
	 * @param bigd
	 * @return
	 */
	public static String percentFormat(BigDecimal bigd ){
		BigDecimal result = bigd.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
		return result+"%";
	}
	
	public static void main(String[] args) {
		BigDecimal b = new BigDecimal(0.5);
		System.out.println(percentFormat(b));
	}
	
}
