package com.yogu.commons.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cookie 操作，不涉及任何加密的内容
 * @author ten 2015/11/16.
 */
public class MazingCookie {
	
	 protected static final Logger logger = LoggerFactory.getLogger(MazingCookie.class);

    // private static final Logger logger = LoggerFactory.getLogger(MazingCookie.class);

    /**
     * cookie存放的域名
     */
    private String domain;

    private boolean secure;

    /**
     * 构造函数
     * @param domain 域名，比如 mazing.com，.admin.mazing.com<br>
     * xxxx@param domain 域名，比如 .mazing.com，.admin.mazing.com
     */
    public MazingCookie(String domain, boolean secure) {
		// 因为tomcat8.5开始，cookie交验规则有更改：
		// 1、必须是1-9、a-z、A-Z、. 、- （注意是-不是_）这几个字符组成
		// 2、必须是数字或字母开头 （所以以前的cookie的设置为.admin.mazing.com 的机制要改为 admin.mazing.com）
		// 3、必须是数字或字母结尾
		if (StringUtils.isBlank(domain)) {// || !domain.startsWith(".")
            throw new IllegalArgumentException("'domain' must start with '.'");
        }
        this.domain = domain;
        this.secure = secure;
    }

    /**
     * 设置cookie，加密处理，域名为：.mazing.com
     * @param response - HttpServletResponse
     * @param name
     *            - cookie名字
     * @param value
     *            - cookie值
     */
    public void setCookie(HttpServletResponse response,String name, String value) {
        setCookie(response,name, value, -1);
    }

    /**
     * 设置 cookie
     *
     * @param name
     *            - 名称
     * @param value
     *            - 值
     * @param maxAge
     *            - 有效时间
     */
    public void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value == null ? "" : value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setDomain(domain);
        cookie.setHttpOnly(true); // 禁止js读取cookie
        cookie.setSecure(secure); // 如果为true，https下才传输cookie，解决cookie劫持的问题
        // 注：防止 cookie 拷贝没有意义，如果黑客能拷贝目标用户的 cookie，他实际上可以做任何事情
        response.addCookie(cookie);
    }

    /**
     * 读取cookie的值，自动解密
     *
     * @param name
     *            - cookie名字
     * @return - cookie值，不存在时为null
     */
    public String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
        	logger.info(" cookie name | name: {}, cname: {}, cvalue: {}", name, cookies[i].getName(), cookies[i].getValue());
            if (name.equals(cookies[i].getName())) {
                String value = cookies[i].getValue();

                return value;
            }
        }
        return null;
    }
}
