package com.yogu.web;

import java.util.Calendar;
import java.util.UUID;

/**
 * 生成token的类
 * 
 * @author linyi 2015/5/17.
 */
public class UserToken {

	/**
	 * 版本号, 1 byte，默认是版本1
	 */
	private byte ver = '1';

	/**
	 * 是否已登录,1 byte，取值为 '0', '1'
	 */
	private byte logined = '0';

	/**
	 * 哪个机房，1 byte，取值 a-z，0-9暂时不用
	 */
	private byte idc = 'a';

	/**
	 * token生成的年，1byte，以2015为基准，year=当前年-2015 +'a'
	 */
	private byte year;

	/**
	 * token生成的月，1~12 + 'a'
	 */
	private byte month;

	/**
	 * token生成的日期，最小是1
	 */
	private int day;

	/**
	 * 保留字段, 1 byte
	 */
	private byte reserved = '0';

	/**
	 * 随机的uuid
	 */
	private String uuid;

	/**
	 * 构造函数，取当前日期
	 */
	public UserToken() {
		Calendar cal = Calendar.getInstance();
		int iYear = cal.get(Calendar.YEAR);
		int iMonth = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		setYear((byte) ((iYear - 2015) + 'a'));
		setMonth((byte) (iMonth + 1 + 'a'));
	}

	/**
	 * 构造函数
	 * 
	 * @param year - 年，比如2015
	 * @param month - 月，1~12
	 * @param day - 日，1~31
	 */
	public UserToken(int year, int month, int day) {
		setYear((byte) ((year - 2015) + 'a'));
		setMonth((byte) (month + 'a'));
		this.day = day;
	}

	public byte getVer() {
		return ver;
	}

	public byte getLogined() {
		return logined;
	}

	public UserToken setLogined(byte logined) {
		this.logined = logined;
		return this;
	}

	public byte getIdc() {
		return idc;
	}

	public UserToken setIdc(byte idc) {
		this.idc = idc;
		return this;
	}

	public byte getYear() {
		return year;
	}

	public UserToken setYear(byte year) {
		this.year = year;
		return this;
	}

	public byte getMonth() {
		return month;
	}

	public UserToken setMonth(byte month) {
		this.month = month;
		return this;
	}

	public int getDay() {
		return day;
	}

	public UserToken setDay(int day) {
		this.day = day;
		return this;
	}

	public byte getReserved() {
		return reserved;
	}

	public UserToken setReserved(byte reserved) {
		this.reserved = reserved;
		return this;
	}

	@Override
	public String toString() {
		if (uuid == null) {
			uuid = UUID.randomUUID().toString().replaceAll("-", "");
		}
		byte dayPrefix = '0';
		byte daySuffix = '0';
		if (day >= 10) {
			dayPrefix = (byte) (day / 10 + '0');
			daySuffix = (byte) ((day % 10) + '0');
		} else {
			daySuffix = (byte) (day + '0');
		}
		byte[] buf = new byte[] { ver, logined, idc, year, month, dayPrefix, daySuffix, reserved };

		StringBuilder tmp = new StringBuilder(buf.length + uuid.length());
		tmp.append(new String(buf)).append(uuid);
		return tmp.toString();
	}

	/**
	 * 生成新的token
	 * @author sky
	 * @date 2015/08/21 15:03:05
	 * @return
	 */
	public static String getNew() {
		return new UserToken().toString();
	}

	public static void main(String[] args) {
		UserToken ut = new UserToken();
		String token = ut.toString();
		System.out.println("token=" + token + ", len=" + token.length());
		System.out.println("token=" + new UserToken(2015, 12, 1).toString());
		System.out.println("token=" + new UserToken(2016, 1, 31).toString());
	}
}
