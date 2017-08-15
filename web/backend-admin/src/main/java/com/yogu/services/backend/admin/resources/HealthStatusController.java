package com.yogu.services.backend.admin.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 健康检测
 * @author ten 2015/11/21.
 */
@Controller
@RequestMapping("/open/health")
public class HealthStatusController {

    @RequestMapping("status")
    @ResponseBody
    public String status() {
        return "1";
    }
}
