/**
 * 
 */
package com.yogu.commons.log.log4j.util;

import java.util.Comparator;

import org.apache.log4j.Logger;

/**
 * logger排序，使用loggerName字符串自身排序 <br>
 * 
 * JFan 2014年12月16日 下午2:17:59
 */
public class LoggerComparator implements Comparator<Logger> {

	/*
	 * （非 Javadoc）
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Logger o1, Logger o2) {
		if (null == o1 || null == o1.getName())
			return -1;
		if (null == o2 || null == o2.getName())
			return 1;
		return o1.getName().compareTo(o2.getName());
	}

}
