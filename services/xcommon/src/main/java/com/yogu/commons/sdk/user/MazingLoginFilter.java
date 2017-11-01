/**
 * 
 */
package com.yogu.commons.sdk.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import com.yogu.commons.sdk.user.encrypt.DefaultConfigurationDecoder;

/**
 * Mazing统一登录接入方式的Filter <br>
 * 
 * 应用程序通过 {@link MazingLoginContext} 获取用户信息
 *
 * @author JFan 2015年11月9日 下午7:55:43
 * @modified by ten 2015/11/16
 */
public class MazingLoginFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(MazingLoginFilter.class);

	private String callbackUrl;// 登录帐号后回调的地址

	private static final String LOGIN_URL = "/admin/login.xhtm";

	private static final Set<String> STATIC_FILE_SET = new HashSet<>();

	static {
		// 静态文件
		STATIC_FILE_SET.add(".html");
		STATIC_FILE_SET.add(".htm");
		STATIC_FILE_SET.add(".css");
		STATIC_FILE_SET.add(".js");
		STATIC_FILE_SET.add(".jpg");
		STATIC_FILE_SET.add(".jpeg");
		STATIC_FILE_SET.add(".png");
		STATIC_FILE_SET.add(".gif");
		STATIC_FILE_SET.add("");
	}

	/**
	 * 不做登录检查的路径
	 */
	private List<String> excludePath;

	/**
	 * 不做登录检查的地址
	 */
	private Set<String> excludeUrl;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String callbackUrl = filterConfig.getInitParameter("callback");
		String appkey = filterConfig.getInitParameter("appkey");
		String appsecret = filterConfig.getInitParameter("appsecret");
		String decoder = filterConfig.getInitParameter("decoder");
		// 不做登录检查的地址列表
		String exclude = filterConfig.getInitParameter("exclude");

		Args.notBlank(callbackUrl, "'callback' cannot be empty");
		Args.notBlank(appkey, "'appid' cannot be empty");
		Args.notBlank(appsecret, "'appsecret' cannot be empty");
		this.callbackUrl = callbackUrl;
		if (StringUtils.isBlank(decoder)) {
			decoder = DefaultConfigurationDecoder.class.getName();
		}

		logger.info("mazing#sdk#filter | callback: {}", callbackUrl);

		initExclude(exclude);
	}

	/**
	 * 初始化排除的 exclude
	 * @param exclude
	 */
	private void initExclude(String exclude) {
		if (StringUtils.isNotBlank(exclude)) {
			String[] urls = exclude.split(",");
			excludePath = new ArrayList<>(urls.length);
			excludeUrl = new HashSet<>();
			for (String url : urls) {
				url = url.trim();
				if (url.endsWith("/") || url.endsWith("*")) {
					url = url.replace("*", "");
					excludePath.add(url);
				}
				else {
					excludeUrl.add(url);
				}
			}
		}
		else {
			excludePath = Collections.emptyList();
			excludeUrl = Collections.emptySet();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		// 读取 cookie
		// 设置到 context
		MazingLoginContext context = new MazingLoginContext();
		context.load(request);

		try {
			if (context.getUid() <= 0 && !isExcludeUrl(request)) {
				// 注意回调URL不要再次登录
				String url = createLoginUrl();
				logger.info("mazing#sdk#filter | 未登录，跳转到登录 | url: {}", url);
				response.sendRedirect(url);
			} else {
				logger.debug("mazing#sdk#filter | 已登录 | uid: {}", context.getUid());
				chain.doFilter(request, response);
			}
		} finally {
			context.clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
		excludePath = null;
		excludeUrl = null;
	}

	/**
	 * 生成登录URL
	 * @return
	 */
	private String createLoginUrl() {
		return LOGIN_URL;
	}

	/**
	 * 判断是否为不做登录验证的URL
	 * @param request
	 * @return
	 */
	private boolean isExcludeUrl(HttpServletRequest request) {

		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);

		uri = uri.replaceFirst(ctxPath, "");
		// 对 callback 地址不做登录验证 ??
		boolean isExclude = (callbackUrl.indexOf(uri) >= 0);
		if (!isExclude) {
			if (excludeUrl.contains(uri)) {
				isExclude = true;
			} else {
				int pos = uri.lastIndexOf(".");
				if (pos >= 0) {
					// 对文件后缀做判断，静态文件不做登录检查
					String suffix = uri.substring(pos).toLowerCase();
					if (STATIC_FILE_SET.contains(suffix)) {
						return true;
					}
				}
				for (String path : excludePath) {
					if (uri.startsWith(path)) {
						isExclude = true;
						break;
					}
				}
			}
		}
		return isExclude;

	}

}
