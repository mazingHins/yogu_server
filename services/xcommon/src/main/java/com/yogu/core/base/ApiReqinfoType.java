/**
 * 
 */
package com.yogu.core.base;

/**
 * 描述接口的请求类型 <br>
 * api、p、a、s、open
 *
 * @author JFan 2015年8月17日 上午9:43:48
 */
public enum ApiReqinfoType {

	API(false, false, false) // /api/开头的请求
	, P(true, false, false) // /p/开头的请求
	, A(true, true, false) // /a/开头的请求
	, S(true, true, true) // /s/开头的请求
	, OPEN(false, false, false) // /open/开头的请求
	;

	/**
	 * URL以这些开头的，将被识别<br>
	 * 注意：nginx中要开启对应的location配置
	 */
	public static ApiReqinfoType pathOf(String pathInfo) {
		if (null != pathInfo) {
			if (pathInfo.startsWith("/api/")) // nginx: 全部域都开了
				return API;
			if (pathInfo.startsWith("/p/"))// nginx: 全部域都开了
				return P;
			if (pathInfo.startsWith("/a/"))// nginx: 全部域都开了
				return A;
			if (pathInfo.startsWith("/s/"))// nginx: storeapi域开了
				return S;
			if (pathInfo.startsWith("/open/")) // nginx: payapi域开了
				return OPEN;
		}
		return null;
	}

	/** 是否需要验签 */
	private boolean check;
	/** 是否需要校验ut参数 */
	private boolean checkUT;
	/** 是否需要装载门店员工信息 */
	private boolean checkStaff;

	private ApiReqinfoType(boolean check, boolean checkUT, boolean checkStaff) {
		this.check = (checkStaff ? checkStaff : (checkUT ? checkUT : check));// 校验ut、staff则必须验签
		this.checkUT = (checkStaff ? checkStaff : checkUT);// 校验staff，必须校验UT
		this.checkStaff = checkStaff;
	}

	public boolean isCheck() {
		return check;
	}

	public boolean isCheckUT() {
		return checkUT;
	}

	public boolean isCheckStaff() {
		return checkStaff;
	}

}
