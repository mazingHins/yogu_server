package com.yogu.services.backend.admin.service.impl;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.backend.admin.dao.AccountAppRelationDao;
import com.yogu.services.backend.admin.dao.AccountRoleRelationDao;
import com.yogu.services.backend.admin.dao.AdminAccountDao;
import com.yogu.services.backend.admin.dto.AccountRoleRelation;
import com.yogu.services.backend.admin.dto.AdminAccount;
import com.yogu.services.backend.admin.dto.RoleAppRelation;
import com.yogu.services.backend.admin.entry.AccountRoleRelationPO;
import com.yogu.services.backend.admin.entry.AdminAccountPO;
import com.yogu.services.backend.admin.entry.RolePO;
import com.yogu.services.backend.admin.service.AdminAccountService;
import com.yogu.services.backend.admin.service.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * AdminAccountService 的实现类
 * 
 * @author ten 2015-08-05
 */
@Service
public class AdminAccountServiceImpl implements AdminAccountService {

	private static final Logger logger = LoggerFactory.getLogger(AdminAccountServiceImpl.class);

	@Autowired
	private AdminAccountDao adminAccountDao;

	@Autowired
	private AccountAppRelationDao accountAppRelationDao;

	@Autowired
	private AccountRoleRelationDao accountRoleRelationDao;

	@Autowired
	private RoleService roleService;

	@Override
	public AdminAccount getById(long uid) {
		AdminAccountPO po = adminAccountDao.getById(uid);
		if (null == po)
			return null;
		return po2dto(po);
	}

	@Override
	public List<AdminAccount> listAll() {
		List<AdminAccountPO> list = adminAccountDao.listAll();
		List<AdminAccount> result = new ArrayList<AdminAccount>();
		if (null != list)
			for(AdminAccountPO po : list)
				result.add(po2dto(po));
		return result;
	}

	// ####
	// ## private func
	// ####

	public AdminAccount po2dto(AdminAccountPO po) {
		return VOUtil.from(po, AdminAccount.class);
	}

	public AdminAccountPO dto2po(AdminAccount dto) {
		return VOUtil.from(dto, AdminAccountPO.class);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void addOrUpdate(AdminAccount account, Set<Integer> roleIds) {
		ParameterUtil.assertNotNull(account, "account不能为null");
		if (account.getUid() <= 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "用户ID错误");
		}
		AdminAccountPO po = adminAccountDao.getById(account.getUid());
		if (po == null) {
			add(account, roleIds);
		}
		else {
			update(account, roleIds);
		}
	}

	/**
	 * 增加新帐号
	 * @param account 帐号信息
	 * @param roleIds 角色ID，至少要有一个角色
	 */
	private void add(AdminAccount account, Set<Integer> roleIds) {
		validateAdminAccount(account, roleIds, true);

		// 帐号校验
		if (adminAccountDao.getById(account.getUid()) != null) {
			logger.info("admin#account#add | 帐号已存在 | uid: {}", account.getUid() );
			throw new ServiceException(ResultCode.RECORD_EXISTED, "帐号已存在：" + account.getUid() + "");
		}
		AdminAccountPO po = VOUtil.from(account, AdminAccountPO.class);

		// 处理帐号
		po.setCreateTime(new Date());
		po.setLastModify(po.getCreateTime());

		this.adminAccountDao.save(po);
		long uid = account.getUid();

		logger.info("admin#account#add | 删除用户角色关系 | uid: {}", uid);
		accountRoleRelationDao.deleteByAccount(uid);
		// 处理帐号角色关系
		for (Integer roleId : roleIds) {
			AccountRoleRelationPO relation = new AccountRoleRelationPO();
			relation.setUid(uid);
			relation.setRoleId(roleId);
			logger.info("admin#account#add | 添加用户角色关系 | uid: {}, roleId: {}", uid, roleId);
			accountRoleRelationDao.save(relation);
		}
		logger.info("admin#account#add | 添加用户成功 | uid: {}", uid);
	}

	/**
	 * 更新帐号信息，但不会更新帐号的状态（冻结、正常）
	 * @param account 帐号信息
	 * @param roleIds 角色ID，至少要有一个角色
	 */
	private void update(AdminAccount account, Set<Integer> roleIds) {
		validateAdminAccount(account, roleIds, false);

		// 帐号校验
		AdminAccountPO po = adminAccountDao.getById(account.getUid());
		ParameterUtil.assertNotNull(po, "帐号不存在.");

		// 处理帐号
		po = VOUtil.from(account, AdminAccountPO.class);
		po.setLastModify(new Date());

		logger.info("admin#account#update | 更新帐号 | uid: {}", po.getUid());
		int rows = adminAccountDao.update(po);
		ParameterUtil.assertGreaterThanZero(rows, "更新帐号失败.");

		logger.info("admin#account#update | 删除帐号角色关系 | uid: {}", po.getUid());
		accountRoleRelationDao.deleteByAccount(po.getUid());
		// 处理帐号角色关系
		for (Integer roleId : roleIds) {
			AccountRoleRelationPO relation = new AccountRoleRelationPO();
			relation.setUid(po.getUid());
			relation.setRoleId(roleId);
			logger.info("admin#account#update | 添加用户角色关系 | uid: {}, roleId: {}", relation.getUid(), roleId);
			accountRoleRelationDao.save(relation);
		}
	}

	/**
	 * 检查参数
	 * @param vo 帐号信息
	 * @param roleIds 角色列表
	 * @param isAddOperate 是否为新增操作
	 */
	private void validateAdminAccount(AdminAccount vo, Set<Integer> roleIds, boolean isAddOperate) {
		ParameterUtil.assertNotNull(vo, "帐号不能为空.");

		ParameterUtil.assertNotBlank(vo.getRealname(), "真实姓名不能为空");
		ParameterUtil.assertMaxLength(vo.getRealname(), 30, "真实姓名长度不能超过30个字符");

		if (vo.getUid() <= 0) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "帐号ID不能为空.");
		}

		if (roleIds == null || roleIds.isEmpty()) {
			throw new ServiceException(ResultCode.PARAMETER_ERROR, "需要分配角色给用户，如果没有角色，请先创建角色.");
		}
		for (Integer roleId : roleIds) {
			if (roleId.intValue() <= 0) {
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "角色ID错误，请选择一个正确的角色.");
			}
		}
	}

	@Override
	public List<AccountRoleRelation> listAccountRoles(long uid) {
		return VOUtil.fromList(accountRoleRelationDao.listRoleByAccount(uid), AccountRoleRelation.class);
	}

	@Override
	public boolean isSuperAdmin(long uid) {
		AdminAccountPO adminAccount = this.adminAccountDao.getById(uid);
		return isSuperAdmin(adminAccount);
	}

	private boolean isSuperAdmin(AdminAccountPO adminAccount) {
		return adminAccount != null && adminAccount.getDefaultData() == 1;
	}

	@Override
	public void lockAccount(long uid) {
		// 判断帐号是否存在
		AdminAccountPO current = adminAccountDao.getById(uid);
		ParameterUtil.assertNotNull(current, "帐号不存在，请重试。");
		if (current.getStatus() != AdminAccount.NOMAL) {
			throw new ServiceException(ResultCode.FAILURE, "帐号状态错误，只能冻结正常的用户帐号。");
		}

		int rows = adminAccountDao.updateStatus(uid, AdminAccount.LOCKED, current.getStatus(), new Date());
		if (rows > 0) {
			logger.info("admin#account#lockAccount | 冻结帐号成功 | uid: {}", uid);
		}
		else {
			logger.error("admin#account#lockAccount | 冻结帐号失败 | uid: {}", uid);
			throw new ServiceException(ResultCode.FAILURE, "冻结用户帐号失败，未知错误，请重试");
		}
	}

	@Override
	public void unlockAccount(long uid) {
		// 判断帐号是否存在
		AdminAccountPO current = adminAccountDao.getById(uid);
		ParameterUtil.assertNotNull(current, "帐号不存在，请重试。");
		if (current.getStatus() != AdminAccount.LOCKED) {
			throw new ServiceException(ResultCode.FAILURE, "帐号状态错误，只能解冻被冻结的用户帐号。");
		}

		int rows = adminAccountDao.updateStatus(uid, AdminAccount.NOMAL, current.getStatus(), new Date());
		if (rows > 0) {
			logger.info("admin#account#unlockAccount | 解冻帐号成功 | uid: {}", uid);
		}
		else {
			logger.error("admin#account#unlockAccount | 解冻帐号失败 | uid: {}", uid);
			throw new ServiceException(ResultCode.FAILURE, "冻结用户帐号失败，未知错误，请重试");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteAccount(long uid) {
		AdminAccountPO current = adminAccountDao.getById(uid);
		ParameterUtil.assertNotNull(current, "帐号不存在，请重试。");

		logger.info("admin#account#deleteAccount | 删除管理员账号 | uid: {}", uid);
		accountAppRelationDao.deleteByAccountId(uid);
		accountRoleRelationDao.deleteByAccount(uid);
		adminAccountDao.deleteById(uid);
		logger.info("admin#account#deleteAccount | 删除管理员账号成功 | uid: {}", uid);
	}

	@Override
	public List<Integer> listAccountApps(long uid) {
		List<Integer> result = new ArrayList<>();
		// 读取角色列表
		List<RolePO> roleList = accountRoleRelationDao.listRoleByAccount(uid);
		for (RolePO po : roleList) {
			int roleId = po.getRoleId();
			List<RoleAppRelation> list = roleService.listRoleAppRelations(roleId);
			for (RoleAppRelation vo : list) {
				result.add(vo.getAppId());
			}
		}
		return result;
	}

	@Override
	public List<AdminAccount> listAccounts(String keyword, int page, int pageSize) {
		if (page <= 0 || pageSize <= 0) {
			throw new IllegalArgumentException("page or pageSize error");
		}

		int startIndex = (page - 1) * pageSize;
		List<AdminAccountPO> accounts = adminAccountDao.listAccounts(keyword, startIndex, pageSize);
		List<AdminAccount> result = VOUtil.fromList(accounts, AdminAccount.class);
		return result;
	}

	@Override
	public int statAccount(String keyword) {
		return adminAccountDao.statAccount(keyword);
	}

}
