package com.mazing.services.backend.admin.service.impl;

import com.mazing.services.backend.admin.BaseServiceTest;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.service.AdminAccountService;
import com.yogu.services.backend.admin.service.RoleService;

import org.junit.Test;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * 帐号服务测试
 * @author ten 2015/8/14
 */
public class AdminAccountServiceImplTest extends BaseServiceTest {

    @Inject
    private AdminAccountService adminAccountService;

    @Inject
    private RoleService roleService;

    @Test
    public void testSave() throws Exception {
        // 先新增一个角色
        int roleId = addRoleAndReturnId();
        Set<Integer> roleIds = new HashSet<>();
        roleIds.add(roleId);

        long uid = 10000009;
        AdminAccount current = adminAccountService.getById(uid);
        if (current == null) {
            // 新增记录
            AdminAccount adminAccount = new AdminAccount();
            adminAccount.setMobileNo("18620075025");
            adminAccount.setUid(uid);
            adminAccount.setRealname("一个人");
            // save
            adminAccountService.addOrUpdate(adminAccount, roleIds);

            current = adminAccountService.getById(uid);
            assertTrue(current.getStatus() == AdminAccount.NOMAL);
        }

        // 测试更新
        current.setRealname("man" + System.currentTimeMillis());
        adminAccountService.addOrUpdate(current, roleIds);
        AdminAccount updated = adminAccountService.getById(uid);
        assertEquals(current.getRealname(), updated.getRealname());

        // 冻结
        adminAccountService.lockAccount(uid);
        AdminAccount locked = adminAccountService.getById(uid);
        assertTrue(locked.getStatus() == AdminAccount.LOCKED);

        // 解冻
        adminAccountService.unlockAccount(uid);
        AdminAccount unlocked = adminAccountService.getById(uid);
        assertTrue(unlocked.getStatus() == AdminAccount.NOMAL);

        // 删除角色
        deleteRole(roleId);
    }

    /**
     * 增加角色并返回roleId
     * @return roleId
     */
    private int addRoleAndReturnId() {
        Role role = new Role();
        role.setRoleName("!!$$测试角色$$");
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
        return theRole.getRoleId();
    }

    /**
     * 删除角色
     * @param roleId 角色ID
     */
    private void deleteRole(int roleId) {
        roleService.delete(roleId);
        Role role = roleService.getById(roleId);
        assertNull(role);
    }

}