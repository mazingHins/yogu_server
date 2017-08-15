package com.yogu.services.backend.admin.resources;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yogu.services.backend.admin.annotation.authority.AllowAnonymous;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误处理
 * @author ten 2015/12/28.
 */
@Controller
@RequestMapping("/admin")
public class ErrorController {

    /**
     * 异常页面
     * @param message
     * @return
     */
    @RequestMapping("/exception")
    @AllowAnonymous
    public ModelAndView exception(String message) {
        Map<String, String> model = new HashMap<>(4);
        if (StringUtils.isNotBlank(message)) {
            model.put("message", message);
        }
        return new ModelAndView("/exception", model);
    }

    /**
     * 错误页面
     * @param message
     * @return
     */
    @RequestMapping("/error")
    @AllowAnonymous
    public ModelAndView error(String message) {
        Map<String, String> model = new HashMap<>(4);
        if (StringUtils.isNotBlank(message)) {
            model.put("error", message);
        }
        return new ModelAndView("/error", model);
    }
}
