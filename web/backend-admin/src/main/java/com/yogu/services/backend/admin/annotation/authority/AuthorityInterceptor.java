package com.yogu.services.backend.admin.annotation.authority;

import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.ResultCode;
import com.yogu.services.backend.admin.context.AdminContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员权限拦截器
 * @author ten 2015/12/28.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    private Authorizable authorizable = null;

    private static final String DENIED_MESSAGE = "{\"code\":" + ResultCode.UNAUTHORIZED_ACCESS + ",\"message\":\"您没有权限访问\"}";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("admin#auth | 权限拦截器 start");
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod method = (HandlerMethod) handler;
            // 对只有 MenuResource 注解的方法做处理
            RequestMapping requestMapping = method.getMethodAnnotation(RequestMapping.class);
            if (method.getMethodAnnotation(MenuResource.class) != null
                    && requestMapping != null) {
                AllowAnonymous allowAnonymous = method.getMethodAnnotation(AllowAnonymous.class);
                if (allowAnonymous == null) {
                    String uri = getURI(request);
                    boolean pass = hasAuthorizable(AdminContext.getAccountId(), uri);
                    // 处理没有权限时的返回值
                     if (pass == false) {
                         // 没有权限
                         logger.error("admin#auth | 没有权限访问 | uri: {}", uri);
                         if (method.getMethodAnnotation(ResponseBody.class) == null) {
                             // 这是一个页面
                             response.sendRedirect("/admin/error?message=没有权限访问");
                         }
                         else {
                             response.getWriter().println(DENIED_MESSAGE);
                         }
                         return false;
                     }

                }
            }
        }
        logger.debug("admin#auth | 权限拦截器 end");
        return true;
    }

    private Authorizable getAuthorizable() {
        if (authorizable == null) {
            authorizable = MainframeBeanFactory.getBean(Authorizable.class);
        }
        return authorizable;
    }

    /**
     * 判断是否有权限，如果没有任何类实现 Authorizable，默认是通过权限判断
     * @param adminId 管理员ID
     * @param uri url地址
     * @return true=有权限
     */
    private boolean hasAuthorizable(long adminId, String uri) {
        return getAuthorizable().hasAuthorization(adminId, uri);
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
}
