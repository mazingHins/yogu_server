package com.yogu.commons.spring.auth.jsp;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Web项目（有HTML页面的）验签拦截器
 * 
 * @author ten 2015/11/20
 */
public class WebAuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(WebAuthInterceptor.class);

	/**
	 * 需要验签的路径
	 */
	private List<String> paths;

	/**
	 * 不需要验签的url
	 */
	private Set<String> exclude;

	public void setPaths(String paths) {
		if (StringUtils.isNotBlank(paths)) {
			String[] array = paths.split(",");
			this.paths = new ArrayList<>();
			for (String path : array) {
				this.paths.add(path.trim().replace("*", ""));
			}
		}
	}

	public List<String> getPaths() {
		return paths;
	}

	public Set<String> getExclude() {
		return exclude;
	}

	public void setExclude(Set<String> exclude) {
		this.exclude = exclude;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (needAuth(request)) {
			WebSignUtils.check(request);
		}

		return true;
	}

	/**
	 * 获取系统资源地址
	 * 
	 * @param request
	 */
	private String getURI(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);

		return uri.replaceFirst(ctxPath, "");
	}

	/**
	 * 判断是否需要验签
	 * @param request
	 * @return
	 */
	private boolean needAuth(HttpServletRequest request) {
		if (paths != null && paths.size() > 0) {
			String uri = getURI(request);
			if (exclude != null && exclude.contains(uri)) {
				return false;
			}
			if (StringUtils.isNotEmpty(uri)) {
				if (paths.size() == 1) {
					return uri.startsWith(paths.get(0));
				}
				for (String path : paths) {
					if (uri.startsWith(path)) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
