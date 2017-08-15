package com.yogu.services.backend.admin.resources;

import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.services.backend.admin.annotation.authority.AllowAnonymous;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 欢迎界面
 * @author ten 2015/10/17.
 */
@Controller
@RequestMapping("/admin/")
public class WelcomeResource {

    /**
     * 增加管理员主页，xhtml 仅用于展示页面
     * @return
     */
    @RequestMapping("welcome.xhtm")
    @MenuResource("欢迎界面")
    @AllowAnonymous
    public String index() {
        return "/index";
    }
}
