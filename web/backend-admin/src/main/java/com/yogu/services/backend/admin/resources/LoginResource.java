package com.yogu.services.backend.admin.resources;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.validation.constraints.NotBlank;
import com.yogu.commons.validation.constraints.NotEmpty;
import com.yogu.remote.user.provider.UserRemoteService;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.context.AdminLoginUser;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.service.AdminAccountService;


/**
 * 管理员登录
 * @author ten 2015/10/5.
 */
@Controller
@RequestMapping("/admin/")
public class LoginResource {

    private static final Logger logger = LoggerFactory.getLogger(LoginResource.class);

    @Autowired
    private UserRemoteService userRemoteService;

    @Autowired
    private AdminAccountService adminAccountService;

    /**
     * 管理员登录，成功返回可以访问的第一个 url
     * @param countryCode 国家代码，不能为空
     * @param passport 帐号，比如手机号码
     * @param password 密码，不能为空
     * @return result.success=成功，result.object=返回可以访问的第一个 url
     */
//    @Path("login.do")
//    @MenuResource("管理员登录")
//    @POST
//    @Produces(AppConstants.MEDIA_TYPE_JSON_UTF8)
//    @AllowAnonymous
//    @AdminLog
    public void login(
            @FormParam("countryCode") @NotBlank(message = "国家代码不能为空") String countryCode,
            @FormParam("passport") @NotBlank(message = "帐号不能为空") String passport,
            @FormParam("password") @NotEmpty(message = "密码不能为空") String password,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response,
            @Context UriInfo uriInfo) throws IOException {
        String ip = IpAddressUtils.getClientIpAddr(request);
        logger.info("admin#login | 管理员登录... | countryCode: {}, passport:{}, ip: {}", countryCode, LogUtil.hidePassport(passport), ip);

        // 判断帐号是否存在
        Map<String, Object> map = userRemoteService.getUidByPassport(countryCode, passport);
        Number uid = (Number) map.get("uid");


        if (uid == null) {
            logger.error("admin#login | 管理员登录错误: 帐号不存在 | countryCode: {}, passport:{}, ip: {}", countryCode, LogUtil.hidePassport(passport), ip);
            response.sendRedirect(genLoginView(uriInfo, "帐号不存在"));
            return;
        }

        // 是否为管理员
        AdminAccount adminAccount = adminAccountService.getById(uid.longValue());
        if (adminAccount == null) {
            logger.error("admin#login | 管理员登录错误: 不是管理员 | countryCode: {}, passport:{}, ip: {}", countryCode, LogUtil.hidePassport(passport), ip);
            response.sendRedirect(genLoginView(uriInfo, "你不是管理员"));
            return;
        }

        if (!"@Mazing!AxA".equals(password)) {
            logger.error("admin#login | 密码错误 | countryCode: {}, passport:{}, ip: {}", countryCode, LogUtil.hidePassport(passport), ip);
            response.sendRedirect(genLoginView(uriInfo, "登录失败，密码错误。"));
            return;
        }

        AdminLoginUser loginUser = new AdminLoginUser();
        loginUser.setAccountId(uid.longValue());
        AdminContext.saveToCookie(response, loginUser);

        logger.info("admin#login | 登录成功 | uid: {}, ip: {}", adminAccount.getUid(), ip);
        response.sendRedirect("/admin/welcome.xhtm");
    }

    /**
     * 生成 login 的页面
     * @param mesage
     * @return
     */
    private String genLoginView(UriInfo uriInfo, String mesage) {
//        Viewable view = new Viewable("/static/login.html?message=" + mesage);
//        Response.seeOther(uriInfo.getBaseUriBuilder().path("/static/login.html?message=" + mesage).build()).build();
//        return view;
        return "/static/login.html?message=" + mesage;
    }
}
