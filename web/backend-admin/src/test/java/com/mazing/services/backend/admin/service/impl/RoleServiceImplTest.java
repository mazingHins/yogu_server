package com.mazing.services.backend.admin.service.impl;

import com.mazing.services.backend.admin.BaseServiceTest;
import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.service.RoleService;

import org.junit.Test;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * 角色逻辑测试
 * @author ten 2015/8/14
 *
 */
public class RoleServiceImplTest extends BaseServiceTest {

    @Inject
    private RoleService roleService;

    @Test
    public void testSave() throws Exception {

        Role role = new Role();
        role.setRoleName("!$$测试$$");
        role.setRemarks("这是一个unit test");

        // 保存
        Set<Integer> appIds = new HashSet<>();
        appIds.add(1);
        Set<Integer> menuIds = new HashSet<>();
        menuIds.add(1);
        Set<Integer> resIds = new HashSet<>();
        resIds.add(1);
        roleService.authRole(role, appIds, menuIds, resIds);

        // 根据name读取
        Role theRole = roleService.getRoleByName(role.getRoleName());
        assertNotNull(theRole);
        assertEquals(role.getRoleName(), theRole.getRoleName());

        // 统计
        int count = roleService.statRole(role.getRoleName());
        assertTrue(1 == count);

        // 查询
        List<Role> roles = roleService.listRoles(role.getRoleName(), 1, 20);
        assertNotNull(roles);
        assertTrue(1 == roles.size());

        // 删除
        roleService.delete(theRole.getRoleId());

        // 再读取，看看是否成功删除
        Role deleted = roleService.getById(theRole.getRoleId());
        assertNull(deleted);
    }
}