/**
 * 
 */
package com.yogu.services.utils;

import com.yogu.language.StoreMessages;

/**
 * 距离转意 <br>
 *
 * @author JFan 2015年8月5日 下午6:45:54
 */
public final class DistanceUtils {

	/**
	 * 将距离转意成字符串，用于展示
	 * 
	 * @param distance
	 *            距离（米）
	 */
	public static String distance2string(int distance) {
		// 20米内（含dis为负的情况）
		if (20 >= distance)
			return StoreMessages.DISTANCEUTILS_DISTANCE2STRING_DISTANCE1();
		// 50米内
		if (50 >= distance)
			return StoreMessages.DISTANCEUTILS_DISTANCE2STRING_DISTANCE2();
		// XX米
		if (400 >= distance)
			return StoreMessages.DISTANCEUTILS_DISTANCE2STRING_DISTANCE3(distance);
		// 0.X公里
		if (1000 >= distance)
			return StoreMessages.DISTANCEUTILS_DISTANCE2STRING_DISTANCE4((distance / 100));

		int x = distance / 1000;

		// X.Y公里
		if (10 > x) {
			int y = (distance % 1000) / 100;
			return StoreMessages.DISTANCEUTILS_DISTANCE2STRING_DISTANCE5(x, y);
		}
		// X公里
		else
			return StoreMessages.DISTANCEUTILS_DISTANCE2STRING_DISTANCE6(x);
	}

	public static String distance2EnString(int distance){
		String[] unit = {"m", "km"}; 
	    if (distance <= 100) {
	      return "<100m";
	    }
	    int i = (int) Math.floor(Math.log(distance) / Math.log(1000));
	    i = i >= unit.length ? 1 : i;
	    return Math.round(distance / Math.pow(1000, i)) + unit[i];
	}
	// ####

	public static void main(String[] args) {
//		System.out.println(distance2string(-100));
//		System.out.println(distance2string(0));
//		System.out.println(distance2string(20));
//		System.out.println(distance2string(50));
//		System.out.println(distance2string(66));
//		System.out.println("-------------------");
//
//		System.out.println(distance2string(500));
//		System.out.println("-------------------");
//
//		System.out.println(distance2string(666));
//		System.out.println("-------------------");
//
//		System.out.println(distance2string(1098));
//		System.out.println(distance2string(1166));
//		System.out.println(distance2string(1700));
//		System.out.println(distance2string(1777));
//		System.out.println(distance2string(2555));
//		System.out.println(distance2string(9999));
//		System.out.println(distance2string(10000));
//		System.out.println("-------------------");
//
//		System.out.println(distance2string(12330));
//		System.out.println(distance2string(17000));
//		System.out.println(distance2string(19900));
//		System.out.println(distance2string(66666));
//		System.out.println("-------------------");
//
//		System.out.println(distance2string(170000));
//		System.out.println(distance2string(177777));
//		System.out.println("-------------------");
		
		System.out.println(distance2EnString(16653));
	}

}
