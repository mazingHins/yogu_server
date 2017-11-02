package com.yogu.services.backend.admin.service.impl;

import com.yogu.commons.utils.VOUtil;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.dao.RoleAppRelationDao;
import com.yogu.services.backend.admin.dao.RoleDao;
import com.yogu.services.backend.admin.dao.RoleMenuRelationDao;
import com.yogu.services.backend.admin.dao.RoleResRelationDao;
import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.dto.RoleAppRelation;
import com.yogu.services.backend.admin.dto.RoleMenuRelation;
import com.yogu.services.backend.admin.dto.RoleResRelation;
import com.yogu.services.backend.admin.entry.RoleAppRelationPO;
import com.yogu.services.backend.admin.entry.RoleMenuRelationPO;
import com.yogu.services.backend.admin.entry.RolePO;
import com.yogu.services.backend.admin.entry.RoleResRelationPO;
import com.yogu.services.backend.admin.service.RoleService;

import org.apache.commons.lang3.StringUtils;
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
 * RoleService 的实现类
 * 
 * @author ten 2015-08-05
 */
@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleMenuRelationDao roleMenuRelationDao;

	@Autowired
	private RoleAppRelationDao roleAppRelationDao;

	@Autowired
	private RoleResRelationDao roleResRelationDao;

	@Override
	public Role getById(int roleId) {
		RolePO po = roleDao.getById(roleId);
		if (null == po)
			return null;
		return po2dto(po);
	}


	// ####
	// ## private func
	// ####

	public Role po2dto(RolePO po) {
		return VOUtil.from(po, Role.class);
	}

	public RolePO dto2po(Role dto) {
		return VOUtil.from(dto, RolePO.class);
	}

	/**
	 * 保存角色数据
	 * @param entity 角色数据
	 */
	private void save(Role entity) {
		validate(entity);

		Role role = getRoleByName(entity.getRoleName());
		if (role != null) {
			throw new ServiceException(ResultCode.RECORD_EXISTED, "角色已存在：" + entity.getRoleName());
		}
		else {
			RolePO po = VOUtil.from(entity, RolePO.class);
			Date now = new Date();
			po.setOperator(AdminContext.getAccountId());
			po.setCreateTime(now);
			po.setLastModify(now);
			roleDao.save(po);
		}

		logger.info("admin#role#save | 更新角色成功 | roleName: {}, operator: {}",
				entity.getRoleName(), AdminContext.getAccountId());
	}

	/**
	 * 校验参数
	 * @param role
	 */
	private void validate(Role role) {
		ParameterUtil.assertNotNull(role, "角色不能为空.");
		ParameterUtil.assertNotBlank(role.getRoleName(), "角色名称不能为空.");
		ParameterUtil.assertNotBlank(role.getRemarks(), "角色备注不能为空.");

		ParameterUtil.assertMaxLength(role.getRoleName(), 40, "角色名称长度太长");
		ParameterUtil.assertMaxLength(role.getRemarks(), 200, "角色备注长度太长");
	}

	@Override
	public Role getRoleByName(String name) {
		if (StringUtils.isEmpty(name) || name.length() > 30) {
			logger.error("name illegal");
			throw new IllegalArgumentException("name illegal");
		}
		RolePO po = roleDao.getByName(name);

		return VOUtil.from(po, Role.class);
	}

	@Override
	public int update(Role role) {
		validate(role);

		ParameterUtil.assertGreaterThanZero(role.getRoleId(), "角色ID不能为空.");

		// 验证待修改的角色名不存在
		Role roleInDB = getRoleByName(role.getRoleName());
		if (roleInDB != null && role.getRoleId() != roleInDB.getRoleId()) {
			throw new IllegalArgumentException("角色名已存在.");
		}

		RolePO po = VOUtil.from(role, RolePO.class);
		po.setLastModify(new Date());
		po.setOperator(AdminContext.getAccountId());
		int rows = roleDao.update(po);

		logger.info("admin#role#update | 更新角色成功 | roleId: {}, operator: {}",
				role.getRoleId(), AdminContext.getAccountId());
		return rows;
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(int roleId) {
		ParameterUtil.assertGreaterThanZero(roleId, "角色ID不能为空.");

		// 删除角色
		this.roleDao.deleteById(roleId);

		// 删除角色应用关系
		roleAppRelationDao.deleteByRoleId(roleId);

		// 删除角色菜单关系
		roleMenuRelationDao.deleteByRoleId(roleId);
		logger.info("admin#role#delete | 删除角色成功，应用关系和菜单关系已经删除 | roleId: {}, operator: {}",
				roleId, AdminContext.getAccountId());

	}

	@Override
	public int statRole(String rolename) {
		return this.roleDao.statRole(rolename);
	}

	@Override
	public List<Role> listRoles(String roleName, int page, int pageSize) {
		if (page < 1) page = 1;
		if (pageSize < 1) pageSize = 20;
		int startIndex = (page - 1) * pageSize;
		return VOUtil.fromList(roleDao.listRolesForPaging(roleName, startIndex, pageSize), Role.class);
	}

	@Override
	public List<Role> listAll() {
		return VOUtil.fromList(this.roleDao.listRoles(), Role.class);
	}

	/**
	 * 增加角色对应的app权限
	 * @param roleId - 角色ID
	 * @param appIds - APP ID 表
	 */
	private void batchAddRoleAppRelation(int roleId, Set<Integer> appIds) {
		roleAppRelationDao.deleteByRoleId(roleId);
		List<RoleAppRelationPO> pos = new ArrayList<>(appIds.size());
		for (Integer appId : appIds) {
			RoleAppRelationPO po = new RoleAppRelationPO();
			po.setRoleId(roleId);
			po.setAppId(appId);
			pos.add(po);
		}
		this.roleAppRelationDao.batchAdd(pos);
	}

	/**
	 * 批量更新角色对应的菜单权限
	 * @param roleId - 角色ID
	 * @param menuIds - 菜单ID
	 */
	private void batchUpdateRoleMenu(int roleId, Set<Integer> menuIds) {
		List<RoleMenuRelationPO> pos = new ArrayList<>(menuIds.size());
		for (int id : menuIds) {
			RoleMenuRelationPO po = new RoleMenuRelationPO();
			po.setRoleId(roleId);
			po.setMenuId(id);
			pos.add(po);
		}
		// 删除角色菜单信息
		roleMenuRelationDao.deleteByRoleId(roleId);
		// 重新保存角色菜单信息
		roleMenuRelationDao.insertBatch(pos);
	}

	/**
	 * 批量保存角色列表
	 * @param roleId
	 * @param resIds
	 * @author ten 2015/12/7
	 */
	private void batchUpdateRoleRes(int roleId, Set<Integer> resIds) {
		List<RoleResRelationPO> list = new ArrayList<>(resIds.size());
		for (int resId: resIds) {
			RoleResRelationPO po = new RoleResRelationPO();
			po.setRoleId(roleId);
			po.setResId(resId);
			list.add(po);
		}
		// 删除角色的资源权限再重新一次性保存
		roleResRelationDao.deleteByRole(roleId);
		roleResRelationDao.insertBatch(list);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void authRole(Role role, Set<Integer> appIds, Set<Integer> menuIds, Set<Integer> resIds) {
		ParameterUtil.assertNotNull(role, "角色不能为空.");
		if (appIds == null || appIds.isEmpty()) {
			throw new ServiceException(ResultCode.FAILURE, "请分配应用系统权限.");
		}
		if (menuIds == null || menuIds.isEmpty() || resIds == null || resIds.isEmpty()) {
			throw new ServiceException(ResultCode.FAILURE, "请分配菜单资源权限.");
		}

		// 保存角色
		if (role.getRoleId() > 0) {
			RolePO old = this.roleDao.getById(role.getRoleId());
			if (old == null) {
				throw new ServiceException(ResultCode.RECORD_NOT_EXIST, "角色不存在，roleId：" + role.getRoleId());
			}
			this.update(role);
		}
		else {
			RolePO old = this.roleDao.getByName(role.getRoleName());
			if (old != null) {
				throw new ServiceException(ResultCode.RECORD_EXISTED, "角色已经存在：" + old.getRoleName());
			}
			this.save(role);
		}
		int roleId = this.roleDao.getByName(role.getRoleName()).getRoleId();

		// 更新角色应用关系
		batchAddRoleAppRelation(roleId, appIds);

		// 更新角色菜单关系
		batchUpdateRoleMenu(roleId, menuIds);

		// 更新角色资源关系
		batchUpdateRoleRes(roleId, resIds);

		logger.info("admin#role#authRole | 新增角色并授权成功 | roleId: {}, operator: {}",
				roleId, AdminContext.getAccountId());
	}

	@Override
	public List<Role> listByAccount(int accountId) {
		ParameterUtil.assertGreaterThanZero(accountId, "帐号ID不能为空.");
		return VOUtil.fromList(roleDao.listByAccount(accountId), Role.class);
	}

	@Override
	public List<RoleAppRelation> listRoleAppRelations(int roleId) {
		return VOUtil.fromList(roleAppRelationDao.listByRoleId(roleId), RoleAppRelation.class);
	}

	@Override
	public List<RoleMenuRelation> listRoleMenus(int roleId) {
		return VOUtil.fromList(roleMenuRelationDao.listAll(), RoleMenuRelation.class);
	}

	@Override
	public void deleteMenu(int menuId) {
		roleMenuRelationDao.deleteByMenu(menuId);
	}

	@Override
	public List<RoleResRelation> listRoleResources(int roleId) {
		return VOUtil.fromList(roleResRelationDao.listByRole(roleId), RoleResRelation.class);
	}
}
