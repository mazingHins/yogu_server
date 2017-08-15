/**
 * 
 */
package com.yogu.services.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 补坑工具类 <br>
 *
 * @author JFan 2016年3月30日 上午11:52:29
 */
public final class FillingPitUtils {

	/**
	 * iOS 1.8.0版本，如果首页顶部更换地址的时候，选择了用户的收货地址，那么会导致无法下单<br>
	 * 原因是选择了收货地址后，要求传递addressId参数(long)，却传递了类似：Optional(18714)的参数<br>
	 * 
	 * iOS会在1.8.?版本修复
	 */
	public static long findAddressId(HttpServletRequest request) {
		String addressIdStr = request.getParameter("addressId");
		if (StringUtils.isBlank(addressIdStr))
			return 0L;
		if (StringUtils.isNumeric(addressIdStr))
			return Long.parseLong(addressIdStr);

		// 尝试提取"Optional(xxx)"中的xxx，如果成功，会返回长度为1的list，包含xxx
		List<String> strs = null;
		try {
			strs = findAddressIdByIOS180(addressIdStr);
		} catch (Exception e) {
			// ignore
		}
		if (null != strs && 1 == strs.size())
			return Long.parseLong(strs.get(0));

		// 到了这里，一般会发生异常；也可以考虑直接throw ex
		return Long.parseLong(addressIdStr);
	}

	/**
	 * 提取内容：Optional(18714) >> ['18714']<br>
	 * 不符合这个格式，会返回[]
	 */
	private static List<String> findAddressIdByIOS180(String managers) {
		List<String> ls = new ArrayList<String>();
		Pattern pattern = Pattern.compile("(?<=Optional\\()(.+?)(?=\\))");
		Matcher matcher = pattern.matcher(managers);
		while (matcher.find())
			ls.add(matcher.group());
		return ls;
	}

	/**
	 * 是否需要重定向(因为配置了错误的url)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public static boolean needRedirect4WrongConfigURL(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String from = request.getParameter("s");
		if (StringUtils.isNotBlank(from) && "huifantaika".equals(from.trim())) {
			response.sendRedirect("http://www.mazing.com/download/app.html?s=hoifanpay");
			return true;
		}

		return false;
	}

}
