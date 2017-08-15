package com.yogu.commons.server.forward;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.RequestUtils;
import com.yogu.commons.utils.ThreadLocalContext;
import com.yogu.core.remote.config.AppLanguage;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.context.SecurityContext;

/**
 * doFilter的处理逻辑：<br>
 * <br>
 * 1：取出所有的参数；这个步骤必须，否则 jersey无法获取到post的内容（猜测原因：严格的@Consumes定义，必须匹配才会解析）<br>
 * 2：取出client ip，塞入HttpClientUtils中<br>
 * 3：判断请求是否需要转发，是则转发（暂时没有这样的需求）<br>
 * 4：移除ThreadContext中private的数据<br>
 * <br>
 */
public class RestForwardFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RestForwardFilter.class);

	/** 不允许访问的IP列表（在admin系统配置中设定） */
	private Set<String> ipBlockadeSet = new HashSet<>();
	private String ipBlockadeString = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
	 * javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		try {
			// pathInfo
			String pathInfo = RequestUtils.getRelativePath(request);
			ThreadLocalContext.putThreadValue(ThreadLocalContext.REQ_PATH_INFO, pathInfo);

			// 取参数，导致解析body
			Map<String, String[]> params = request.getParameterMap();// 不放到if代码中，就是要它get一次参数
			String method = request.getMethod();
			if (logger.isDebugEnabled())
				logger.debug("request '{}' all {} params: {}", pathInfo, method, JsonUtils.toJSONString(params));

			// set client IP
			String ip = IpAddressUtils.getClientIpAddr(request);// RequestInfo.getRequestIp(request);
			ThreadLocalContext.putThreadValue(ThreadLocalContext.REQ_CLIENT_IP, ip);
			if (ipBlockadeSet().contains(ip)) {// 被封的IP
				String paramsStr = JsonUtils.toJSONString(params);
				logger.warn("请求已被阻止 | method: {}, pathInfo: '{}', ip: '{}', params: {}", method, pathInfo, ip, paramsStr);
				return;
			}

			// language code
			String langCode = giveAppLangCode(request);
			AppLanguage lang = AppLanguage.giveLanguageOrDef(langCode);// 一定不为null
			ThreadLocalContext.putThreadValue(SecurityContext.BASE_APP_LANGUAGE, lang);
			ThreadLocalContext.putThreadValue(HttpClientUtils.APP_LANGUAGE, lang.getCode());

			logger.debug("IP: '{}', pathInfo: '{}', lang: {}", ip, pathInfo, lang.getCode());

			// 监测是否存转发的请求
			String forwardPath = null;
			// no Forward: to jax-rs 内部有验签了，支持注解
			if (null == forwardPath) {
				chain.doFilter(req, resp);
				return;
			}

			// yes Forward
			// ...
		} finally {
			// clean private data
			ThreadLocalContext.clearThreadValue();
		}
	}

	/**
	 * 获取请求参数 language code<br>
	 * 先从request 中获取请求参数， 没有再获取header中的参数<br>
	 * 当前存储的可能是一个空值
	 */
	private String giveAppLangCode(HttpServletRequest request) {
		// APP请求参数
		String langCode = request.getParameter("lang");
		logger.debug("ApiAuth#getLangCode | 开始获取request param 中的language 参数 | langCode: {}", langCode);

		// 如果APP没有设置语言，尝试获取http header中的语言信息
		if (StringUtils.isBlank(langCode)) {
			langCode = request.getHeader(HttpClientUtils.APP_LANGUAGE);
			logger.debug("ApiAuth#getLangCode | 开始获取request header 中的language 参数 | langCode: {}", langCode);
		}

		return langCode;
	}

	/**
	 * 获得拒绝访问的IP列表
	 */
	private Set<String> ipBlockadeSet() {
		String str = ipBlockadeString();
		// 对比配置是否有变更，变更了则重新解析字符串
		if (!(ipBlockadeString.equals(str))) {
			Set<String> tmp = new HashSet<>();

			String[] ss = ParameterUtil.str2strs(str);
			if (ArrayUtils.isNotEmpty(ss))
				for (String s : ss)
					tmp.add(s);

			ipBlockadeSet = tmp;
		}
		// 保证不为null
		return ipBlockadeSet;
	}

	private String ipBlockadeString() {
		String config = ConfigRemoteService.getConfig(ConfigGroupConstants.SERVER_CONFIG, "ipBlockade");
		return (null != config ? config : "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

}
