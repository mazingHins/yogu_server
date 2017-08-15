package com.yogu.services.backend.admin.service;

import java.util.List;
import java.util.Set;

import com.yogu.services.backend.admin.dto.Role;
import com.yogu.services.backend.admin.dto.RoleAppRelation;
import com.yogu.services.backend.admin.dto.RoleMenuRelation;
import com.yogu.services.backend.admin.dto.RoleResRelation;

/**
 * 角色定义 <br>
 * 的操作接口
 * 
 * @author ten 2015-08-05
 */
public interface RoleService {

//	/**
//	 * 保存数据
//	 *
//	 * @param dto 对象
//	 */
//	public void save(Role dto);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param dto - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(Role dto);

	/**
	 * 根据主键读取记录
	 */
	public Role getById(int roleId);

	/**
	 * 删除角色
	 *
	 * @param roleId
	 */
	void delete(int roleId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<Role> listAll();

	/**
	 * 赋予角色APP权限、菜单权限
	 *
	 * @param role - 角色
	 * @param appIds - APP ID
	 * @param menuIds - 菜单ID
	 * @param resIds - 资源ID
	 */
	void authRole(Role role, Set<Integer> appIds, Set<Integer> menuIds, Set<Integer> resIds);

	/**
	 * 依据用户获取角色
	 *
	 * @param accountId
	 * @return
	 */
	List<Role> listByAccount(int accountId);

	/**
	 * 返回角色拥有的APP权限
	 * @param roleId 角色ID
	 * @return 如果没有数据，返回size=0的数据
	 */
	List<RoleAppRelation> listRoleAppRelations(int roleId);

	/**
	 * 返回角色在某个application拥有的菜单权限
	 * @param roleId - 角色ID
	 * @return
	 */
	List<RoleMenuRelation> listRoleMenus(int roleId);

	/**
	 * 删除菜单和角色的关系
	 * @param menuId 角色ID
	 */
	void deleteMenu(int menuId);

	/**
	 * 根据角色名获取角色信息
	 *
	 * @param name
	 *            角色名 不为空
	 * @return 角色实体
	 */
	Role getRoleByName(String name);

	/**
	 * 获取角色数量
	 * @param rolename 角色名称，可以为空
	 * @return
	 */
	int statRole(String rolename);

	/**
	 * 列举角色
	 * @param roleName - 角色名称，可以为空
	 * @param page - 第几页
	 * @param pageSize - 每页多少记录
	 * @return
	 */
	List<Role> listRoles(String roleName, int page, int pageSize);

	/**
	 * 读取角色拥有的资源权限
	 * @param roleId 角色ID
	 * @return 返回相应的资源列表，如果没有数据，返回size=0的List
	 * @author ten 2015/12/7
	 */
	List<RoleResRelation> listRoleResources(int roleId);
}
