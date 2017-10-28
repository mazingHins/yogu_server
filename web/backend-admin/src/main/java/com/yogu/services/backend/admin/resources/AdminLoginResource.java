package com.yogu.services.backend.admin.resources;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yogu.CommonConstants;
import com.yogu.commons.sdk.user.MazingLoginContext;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.services.backend.admin.annotation.authority.AllowAnonymous;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.context.AdminLoginUser;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.service.AdminAccountService;
import com.yogu.services.backend.admin.util.AppConstants;


/**
 * 登录接口
 * @author ten 2015/11/19.
 */
@Controller
@RequestMapping("/open/mazing/")
public class AdminLoginResource {

    private static final Logger logger = LoggerFactory.getLogger(AdminLoginResource.class);

//    @Autowired
//    private UserRemoteService userRemoteService;

    @Autowired
    private AdminAccountService adminAccountService;

    /**
     * 管理员登录，成功返回可以访问的第一个 url
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return result.success=成功，result.object=返回可以访问的第一个 url
     */
    @RequestMapping("login")
    @AllowAnonymous
    @AdminLog(type = "管理员登录")
    public ModelAndView login(
            int code,
            String message,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	String ip = IpAddressUtils.getClientIpAddr(request);
        logger.info("admin#login | 管理员登录... | ip: {}, code: {}, message: {}", ip, code, message);

        // 判断帐号是否存在
        long uid = MazingLoginContext.getUid();

        // 是否为管理员
        AdminAccount adminAccount = adminAccountService.getById(uid);
        if (adminAccount == null) {
            logger.error("admin#login | 管理员登录错误: 不是管理员 | uid: {}, ip: {}", uid, ip);
            Map<String, Object> model = new HashMap<>(2);
            model.put("message", "您不是管理员，不能登录系统");
            return new ModelAndView("/error", model);
        }

        AdminLoginUser loginUser = new AdminLoginUser();
        loginUser.setAccountId(uid);
        loginUser.setAppId(AppConstants.APP_ID_DEFAULT);
        AdminContext.saveToCookie(response, loginUser);

        logger.info("admin#login | 登录成功 | uid: {}, name: {}, ip: {}", uid, adminAccount.getRealname(), ip);
        return new ModelAndView("/index");
    }

    /**
     * 管理员退出登录
     * @param response
     */
    @RequestMapping("logout")
    @AllowAnonymous
    @AdminLog(type = "管理员退出登录")
    public void logout(@Context HttpServletResponse response) {
        AdminContext.clearCookie(response);
        // TODO 正确的应该是调用 user.mazing.com 清除 cookie
        MazingLoginContext.clearCookie(response);
        try {
            response.sendRedirect("/static/login.html");
        } catch (IOException e) {
            // ignore
        }
    }
}
