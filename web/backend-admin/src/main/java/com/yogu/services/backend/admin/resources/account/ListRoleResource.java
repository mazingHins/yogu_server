package com.yogu.services.backend.admin.resources.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.PageResult;
import com.yogu.core.web.RestResult;
import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.service.RoleService;

/**
 * 角色管理
 * @author linyi 2015/6/10.
 */
@Menu(name="角色列表", parent = "帐号管理", sequence = 590000)
@Controller
@RequestMapping("/admin/account/")
public class ListRoleResource {

    @Autowired
    private RoleService roleService;

    /**
     * 增加管理员主页，xhtml 仅用于展示页面，ajax 调用 .do 接口获取参数
     * @return
     */
    @MenuResource("角色列表主页")
    @RequestMapping("listRole.xhtm")
    public String index() {
        return ("/account/list_role");
    }

    /**
     * 读取管理员列表
     * @param keyword 搜索的关键字
     * @param page 第几页
     * @param pageSize 每页多少
     * @return
     */
    @RequestMapping("queryRole")
    @ResponseBody
    @MenuResource("角色列表")
    public PageResult<List<Role>> listRole(
            @DefaultValue("") String keyword,
            @DefaultValue("1") int page,
            @DefaultValue("20") int pageSize,
            HttpServletRequest request) {
        List<Role> list = roleService.listRoles(keyword, page, pageSize);
        int total = roleService.statRole(keyword);

        PageResult<List<Role>> result = new PageResult<>(list);
        result.setCurrentPage(page);
        result.setPageSize(pageSize);
        result.setCount(total);
        return result;
    }

    /**
     * 返回所有的角色列表
     * @return result.object=List，List不为null
     */
    @MenuResource("读取所有角色列表")
    @RequestMapping("allRoles")
    @ResponseBody
    public RestResult<List<Role>> listAllRoles() {
        List<Role> list = roleService.listAll();
        return new RestResult<>(list);
    }
}
