package com.yogu.services.backend.admin.resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.sdk.user.MazingLoginContext;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.core.utils.MazingDomainUtils;
import com.yogu.services.backend.admin.resources.form.ApplyLoginForm;
import com.yogu.services.backend.admin.resources.form.LoginForm;

/**
 * 用户登录
 * @author ten 2015/11/14.
 */
@Controller
@RequestMapping("/open/yogu")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String host = CommonConstants.USER_DOMAIN;

    /**
     * 登录主页，xhtm 仅用于展示页面
     * @return
     */
    @RequestMapping("login.xhtm")
    public ModelAndView index(@Valid ApplyLoginForm applyLoginForm, BindingResult bindingResult) throws Exception {
    	logger.info("login.xhtm start");
    	Map<String, Object> model = new HashMap<>();
        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            logger.error("open#mazing#login | 应用请求登录参数错误");
            model.put("message", message);
            return new ModelAndView("/open/mazing/error", model);
        }
        logger.info("login.xhtm success");
        long time = System.currentTimeMillis();
        model.put("t", time);
        return new ModelAndView("/open/mazing/login", model);
    }

    /**
     * 执行登录的功能
     * @param form 登录表单数据
     * @param bindingResult 表单校验结果，spring注入
     * @return result.success=true表示成功，result.callback=要跳转的地址
     */
    @ResponseBody
    @RequestMapping("login.do")
    public String login(@Valid LoginForm form,
                                     BindingResult bindingResult,
                                     HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ip = IpAddressUtils.getClientIpAddr(request);
		logger.info("open#mazing#login | 用户登录start | countryCode: {}, passport: {}, ip: {}", form.getCountryCode(),
				LogUtil.hidePassport(form.getPassport()), ip);

		String message = "";

		if (bindingResult.hasErrors()) {
			message = bindingResult.getAllErrors().get(0).getDefaultMessage();
			logger.error("open#mazing#login | 参数错误 | message: {}", message);
			 return ("open/mazing/error");

		}
		if (!MazingDomainUtils.isMazingDomain(form.getCallback())) {
			message = "非法的域名";
			logger.error("open#mazing#login | 用户登录结果，回调url非法 | callback: {}", form.getCallback());
			return ("open/mazing/error");
		} else {
			Map<String, Object> loginResult = doLogin(form, ip);

			if (loginResult.containsKey("success")) {
				boolean success = (Boolean) loginResult.get("success");
				message = (String) loginResult.get("message");
				logger.info("open#mazing#login | 用户登录结果 | success: {}, code: {}, message: {}", success, loginResult.get("code"), message);
				if (success) {
					Map<String, Object> user = (Map<String, Object>) loginResult.get("object");
					// 写 cookie
					Map<String, Object> map = new HashMap<>(4);
					map.put("token", user.get("ut"));
					map.put("uid", user.get("uid"));
					map.put("nickname", user.get("nickname"));
					map.put("loginTime", System.currentTimeMillis());
					MazingLoginContext.saveMazingLoginUserToCookie(response, map);
					// redirect
					message = "成功";
				}
			}
		}

		return "admin/welcome.xhtm";
	}


    /**
     * 读取帐号系统接口执行登录
     * @param form
     * @return 返回接口登录的结果
     */
    private Map<String, Object> doLogin(LoginForm form, String ip) {

        long t = System.currentTimeMillis();
        Map<String, String> params = new HashMap<>(8);
        params.put("t", t + "");
        params.put("countryCode", form.getCountryCode());
        params.put("passport", form.getPassport());
        params.put("password", form.getPassword());
        // 把IP也传过去 felix
        params.put("ip", ip);

        String json = HttpClientUtils.doPost(host + "/api/security/webLogin.do", params);

        try {
            Map<String, Object> result = JsonUtils.parseObject(json, new TypeReference<Map<String, Object>>() {
            });
            return result;
        } catch (Exception e) {
            logger.error("open#mazing#login | 登录帐号出错", e);
        } finally {
            long time = System.currentTimeMillis() - t;
            logger.info("open#mazing#login | 登录帐号耗时 | time: {}", time);
        }
        return Collections.emptyMap();
    }

}
