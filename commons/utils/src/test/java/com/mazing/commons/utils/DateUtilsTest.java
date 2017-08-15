/**
 * 
 */
package com.mazing.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.yogu.commons.utils.DateUtils;

/**
 * DateUtils测试类 <br>
 *
 * @author JFan 2015年8月21日 下午3:33:50
 */
public class DateUtilsTest {

	/**
	 * currentTime YMD<br>
	 * date -> 20150821
	 * 
	 * @throws ParseException
	 */
	@Test
	public void currentTimeDay() throws ParseException {
		assertEquals("20150021", 20141221);// 解析日期的时候往前挪一个月份

		assertEquals("20150121", 20150121);
		assertEquals("20150221", 20150221);
		assertEquals("20150321", 20150321);
		assertEquals("20150421", 20150421);
		assertEquals("20150521", 20150521);
		assertEquals("20150621", 20150621);
		assertEquals("20150721", 20150721);
		assertEquals("20150821", 20150821);
		assertEquals("20150921", 20150921);
		assertEquals("20151021", 20151021);
		assertEquals("20151121", 20151121);
		assertEquals("20151221", 20151221);

		assertEquals("20151321", 20160121);// 解析日期的时候往后挪一个月份
	}

	private void assertEquals(String dayStr, int dayInt) throws ParseException {
		Date date = date(dayStr);
		int day = DateUtils.currentTimeDay(date);
		Assert.assertEquals(day, dayInt);
	}

	private Date date(String dayStr) throws ParseException {
		SimpleDateFormat format = DateUtils.getDateFormat(DateUtils.YYYYMMDD);
		return format.parse(dayStr);
	}

}
