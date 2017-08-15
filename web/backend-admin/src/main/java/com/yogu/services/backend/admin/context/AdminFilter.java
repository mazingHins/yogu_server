package com.yogu.services.backend.admin.context;

import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.ThreadLocalContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 管理系统的 filter
 * @author ten 2015/10/6.
 */
public class AdminFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AdminFilter.class);

    // 排除的URL，不需要登录
    private static final Set<String> EXCLUDE_URL = new HashSet<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;


        /**
         * <pre>
         * doFilter的处理逻辑：
         *
         * 1：取出所有的参数；这个步骤必须，否则 jersey无法获取到post的内容（猜测原因：严格的@Consumes定义，必须匹配才会解析）
         * 2：取出client ip，塞入HttpClientUtils中
         * 3：判断请求是否需要转发，是则转发（暂时没有这样的需求）
         * 4：移除ThreadContext中private的数据
         * </pre>
         */
        try {
            String pathInfo = getURI(request);
            // 取参数，导致解析body
            Map<String, String[]> params = request.getParameterMap();
            if (logger.isDebugEnabled())
                logger.debug("request '{}' all {} params: {}", pathInfo, request.getMethod(), JsonUtils.toJSONString(params));

            // set client IP
            String ip = IpAddressUtils.getClientIpAddr(request);
            if (logger.isDebugEnabled())
                logger.debug("request '{}' ip '{}'", pathInfo, ip);
            ThreadLocalContext.putThreadValue(ThreadLocalContext.REQ_CLIENT_IP, ip);
            ThreadLocalContext.putThreadValue(AdminContext.REQUEST_KEY, request);


            AdminContext.loadFromCookie(request);

            chain.doFilter(req, resp);

        } finally {
            // clean private data
            ThreadLocalContext.clearThreadValue();
            AdminContext.clear();
        }
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

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        EXCLUDE_URL.add("/static/login.html");
        EXCLUDE_URL.add("/admin/login.do");
        EXCLUDE_URL.add("/admin/welcome.xhtm");
    }

    private boolean isExcludePath(String path) {
        if (path != null) {
            if (EXCLUDE_URL.contains(path) || path.startsWith("/include/")) {
                return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        EXCLUDE_URL.clear();
    }
}
