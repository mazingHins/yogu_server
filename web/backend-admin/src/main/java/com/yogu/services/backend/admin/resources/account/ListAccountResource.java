package com.yogu.services.backend.admin.resources.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yogu.commons.utils.resource.Menu;
import com.yogu.commons.utils.resource.MenuResource;
import com.yogu.core.web.PageResult;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;
import com.yogu.services.backend.admin.annotation.log.AdminLog;
import com.yogu.services.backend.admin.dto.AccountRoleRelation;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.service.AdminAccountService;
import com.yogu.services.backend.admin.service.RoleService;


/**
 * 管理员列表
 * @author linyi 2015/6/7.
 */
@Menu(name="管理员列表", parent = "帐号管理", sequence = 1200000)
@Controller
@RequestMapping("/admin/account/")
public class ListAccountResource {

    private static final Logger logger = LoggerFactory.getLogger(ListAccountResource.class);

    @Autowired
    private AdminAccountService adminAccountService;

    @Autowired
    private RoleService roleService;

    /**
     * 增加管理员主页，xhtml 仅用于展示页面，ajax 调用 .do 接口获取参数
     * @return
     */
    @RequestMapping("listAccount.xhtm")
    @MenuResource("管理员列表主页")
    public String index() {
        return ("/account/list_account");
    }

	/**
	 * 读取管理员列表
	 * 
	 * @param keyword 搜索的关键字
	 * @param page 第几页
	 * @param pageSize 每页多少
	 * @return result.object=数据列表，数据列表不为null
	 */
	@RequestMapping("queryAccount")
	@MenuResource("读取管理员列表")
	@ResponseBody
	public PageResult<List<AdminAccount>> listAccount(String keyword, int page, int pageSize, HttpServletRequest request) {
		List<AdminAccount> list = adminAccountService.listAccounts(keyword, page, pageSize);
		int total = adminAccountService.statAccount(keyword);

		Map<Integer, Role> roleMap = new HashMap<>();

		for (AdminAccount account : list) {
			StringBuilder buf = new StringBuilder();
			List<AccountRoleRelation> tmpList = adminAccountService.listAccountRoles(account.getUid());
			for (AccountRoleRelation arr : tmpList) {
				int roleId = arr.getRoleId();
				Role role = roleMap.get(roleId);
				if (null == role) {
					role = roleService.getById(roleId);
					if (null != role)
						roleMap.put(roleId, role);
				}

				if (role != null) {
					if (buf.length() > 0)
						buf.append("，");
					buf.append(role.getRoleName());
				}
			}
			account.setRoleName(buf.toString());

		}
		PageResult<List<AdminAccount>> result = new PageResult<>(list);
		result.setCurrentPage(page);
		result.setPageSize(pageSize);
		result.setCount(total);
		return result;
	}

    /**
     * 冻结解冻管理员帐号，调用一次接口，做一次反操作。
     * 比如当前是正常状态，操作一次后变成冻结，再操作一次变成解冻
     * @param uid 管理员ID
     * @return result.success=true为成功, result.object=新的状态
     */
    @RequestMapping(value = "lockOrUnlockAccount", method = RequestMethod.GET)
    @MenuResource("冻结/解冻管理员")
    @ResponseBody
    @AdminLog
    public RestResult<Integer> lockOrUnlockAccount(long uid) {
        AdminAccount account = adminAccountService.getById(uid);
        RestResult<Integer> result = null;
        if (account == null) {
            result = new RestResult<>(ResultCode.RECORD_NOT_EXIST, "帐号不存在，uid=" + uid);
        }
        else {
            String message = null;
            int newStatus = 0;
            if (account.getStatus() == AdminAccount.LOCKED) {
                adminAccountService.unlockAccount(uid);
                message = "解冻帐号成功.";
                newStatus = AdminAccount.NOMAL;
                logger.info("admin#account#lockOrUnlockAccount | 解冻帐号成功 | uid: " + uid);
            }
            else {
                adminAccountService.lockAccount(uid);
                newStatus = AdminAccount.LOCKED;
                message = "冻结帐号成功.";
            }
            result = new RestResult<>(ResultCode.SUCCESS, message, newStatus);
            logger.info("admin#account#lockOrUnlockAccount | 冻结帐号成功 | uid: " + uid);
        }
        return result;
    }

	/**
	 * 删除管理员（角色+管理员账号）（PS：不是删除米星账号）
	 */
	@AdminLog
	@ResponseBody
	@MenuResource("删除管理员")
	@RequestMapping(value = "deleteAccount", method = RequestMethod.GET)
	public RestResult<Boolean> deleteAccount(long uid) {
		AdminAccount account = adminAccountService.getById(uid);
		if (account == null)
			return new RestResult<>(ResultCode.RECORD_NOT_EXIST, "帐号不存在，uid=" + uid);

		adminAccountService.deleteAccount(uid);
		return new RestResult<>(true);
	}

}
