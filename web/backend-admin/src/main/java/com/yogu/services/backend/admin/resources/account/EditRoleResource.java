package com.yogu.services.backend.admin.resources.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.utils.ListUtils;
import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuItem;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.dto.AppDefine;
import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.dto.RoleMenuRelation;
import com.yogu.services.backend.admin.dto.RoleResRelation;
import com.yogu.services.backend.admin.service.AppDefineService;
import com.yogu.services.backend.admin.service.MenuService;
import com.yogu.services.backend.admin.service.RoleService;

/**
 * 增加角色
 * @author linyi 2015/6/10.
 */
@Controller
@RequestMapping("/admin/account/")
@Menu(name="增加/修改角色", parent = "帐号管理", sequence = 1300000)
public class EditRoleResource {

    private static final Logger logger = LoggerFactory.getLogger(EditRoleResource.class);

    @Autowired
    private AppDefineService appService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;
    
    @Resource(name = "redis")
	private Redis redis;

    /**
     * 增加角色主页，xhtml 仅用于展示页面，ajax 调用 .do 接口获取参数
     * @return
     */
    @RequestMapping("editRole.xhtm")
    @MenuResource("增加/修改角色主页")
    public String index() {
        return ("/account/edit_role");
    }

    /**
     * 增加或修改角色信息
     * @param role JSON格式数据, {roleId:角色ID, rolename:角色名称, remarks:备注}
     * @param menuIds JSON格式数据，菜单ID列表 []
     * @param resIds JSON格式数据，资源ID列表 []
     * @param appIds JSON格式数据，APP ID 列表 []
     * @return result.success=true表示成功
     */
    @MenuResource("增加/修改角色信息")
    @RequestMapping(value = "updateRole.do", method = RequestMethod.POST)
    @AdminLog
    @ResponseBody
    public RestResult<Object> updateRole(String role,
                                         String menuIds,
                                         String resIds,
                                         String appIds) {
        logger.info("admin#role#updateRole | 新增/修改角色 | role: " + role + ", menuIds: " + menuIds + ", resIds: " + resIds + ", appIds: " + appIds);
        ParameterUtil.assertNotBlank(role, "role不能为空");
        ParameterUtil.assertNotBlank(menuIds,  "menuIds不能为空");
        ParameterUtil.assertNotBlank(resIds,  "resIds不能为空");

        Map<String, Object> myRole = JSON.parseObject(role, new TypeReference<HashMap<String, Object>>(){
        });
        List<Integer> menuIdList = JSON.parseObject(menuIds, new TypeReference<List<Integer>>() {
        });
        List<Integer> appIdList = JSON.parseObject(appIds, new TypeReference<List<Integer>>() {
        });
        List<Integer> resIdList = JSON.parseObject(resIds, new TypeReference<List<Integer>>() {
        });

        logger.info("admin#role#updateRole | 新增/修改角色 | role: {}, menuIdList: {}, appIdList: {} ",
                JSON.toJSONString(myRole), JSON.toJSONString(menuIdList), JSON.toJSONString(appIdList));
        Role theRole = validateRole(myRole, menuIdList, appIdList);
        roleService.authRole(theRole, ListUtils.toSet(appIdList), ListUtils.toSet(menuIdList), ListUtils.toSet(resIdList));
        
        // 删除node的菜单缓存
     	redis.del("admin_menu");
     		
        return new RestResult<>(ResultCode.SUCCESS, "操作成功.");
    }

    /**
     * 校验参数是否正确
     * @param myRole
     * @param menuIdList
     * @param appIdList
     * @return
     */
    private Role validateRole(Map<String, Object> myRole, List<Integer> menuIdList, List<Integer> appIdList) {
        ParameterUtil.assertNotNull(menuIdList, "必须要选择菜单权限");
        ParameterUtil.assertNotNull(appIdList, "不需要选择app权限");
        Role role = null;
        String rolename = (String) myRole.get("rolename");
        String remarks = (String) myRole.get("remarks");
        int roleId = NumberUtils.toInt(String.valueOf(myRole.get("roleId")), 0);
        if (StringUtils.isNotBlank(rolename) && StringUtils.isNotBlank(remarks)) {
            role = new Role();
            role.setRemarks(remarks);
            role.setRoleName(rolename);
            role.setRoleId(roleId);
        }

        ParameterUtil.assertNotNull(role, "role数据错误，rolename、remarks不能为空");
        return role;
    }

    /**
     * 读取所有APP的菜单，及角色的菜单权限
     * @param roleId 角色ID
     * @return
     */
    @MenuResource("读取角色菜单")
    @RequestMapping("allAppMenus")
    @ResponseBody
    public Map<String, Object> listAllAppMenu(
            int roleId) {
        Map<String, Object> map = new HashMap<>(4);
        List<AppDefine> apps = appService.listAll();
        List<MenuItem> appMenus = new ArrayList<>(apps.size());
        for (AppDefine app : apps) {
            appMenus.add(menuService.getMenuTree(app.getAppId()));
        }
        map.put("success", Boolean.TRUE);
        map.put("appList", apps);
        map.put("appMenus", appMenus);

        if (roleId > 0) {
            List<RoleMenuRelation> menuIds = roleService.listRoleMenus(roleId);
            map.put("menuIds", menuIds);

            Role role = roleService.getById(roleId);
            map.put("role", role);

            List<RoleResRelation> resList = roleService.listRoleResources(roleId);
            map.put("resIds", resList);
        }
        return map;
    }
}
