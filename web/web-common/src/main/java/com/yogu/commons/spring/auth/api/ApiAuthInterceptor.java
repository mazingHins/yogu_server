package com.yogu.commons.spring.auth.api;

import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ServiceLoaderUtils;
import com.yogu.core.server.auth.Authenticator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * spring mvc 项目的验签类 <br>
 * 只有指定开头的接口才做这样的校验<br>
 *
 * @author ten 2015/12/24
 */
public class ApiAuthInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(ApiAuthInterceptor.class);

	/**
	 * 不需要验签的路径，除了这些，其他所有都要验签。
	 * 比如 /api/
	 * 处于安全考虑，默认全部验签。
	 * 2015/12/24 by ten
	 */
	private List<String> excludePaths;

	/** 权限校验器列表 */
	private List<Authenticator> authenticatorList;

	@PostConstruct
	public void initial() {
		loadAuthImpl();// 加载authenticatorList
		Args.notNull(authenticatorList, "没有装载到任何权限校验器");
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
			// 验签
			for (Authenticator auth : authenticatorList)
				auth.authenticate(request);

		}

		return true;
	}

	/**
	 * 设置不需要验签的 path
	 * @param excludePaths
	 */
	public void setExcludePaths(String excludePaths) {
		if (StringUtils.isNotBlank(excludePaths)) {
			String[] array = excludePaths.split(",");
			this.excludePaths = new ArrayList<>(array.length);
			for (String path : array) {
				this.excludePaths.add(path.trim().replace("*", ""));
			}
		}
	}

	/**
	 * 判断是否需要验签<br>
	 * 符合path配置的才需要做
	 */
	private boolean needAuth(HttpServletRequest request) {
		if (!excludePaths.isEmpty()) {
			String uri = getURI(request);
			if (StringUtils.isNotEmpty(uri)) {
				for (String path : excludePaths) {
					if (uri.startsWith(path)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 获取系统资源地址
	 */
	private String getURI(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		String ctxPath = helper.getOriginatingContextPath(request);
		return uri.replaceFirst(ctxPath, "");
	}

	/**
	 * 启动时，有可能实例还没有被装载，所以走异步（需要时装载）的形式，但只初始化一次
	 */
	private List<Authenticator> loadAuthImpl() {
		if (null == authenticatorList) {
			logger.debug("Loading 'Authenticator' ......");
			authenticatorList = ServiceLoaderUtils.orderly(Authenticator.class);

			// 打印有那些校验器被装载了
			boolean one = true;
			StringBuffer str = new StringBuffer("[");
			for (Authenticator auth : authenticatorList) {
				if (!one)
					str.append(", ");
				str.append(auth.getClass());
				one = false;
			}
			str.append("]");
			logger.info("Loading 'Authenticator' size: {}, impl: {}", authenticatorList.size(), str.toString());
		}
		return authenticatorList;
	}

}
