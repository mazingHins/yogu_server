package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.RoleMenuRelationPO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_role_menu_relation 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface RoleMenuRelationDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(RoleMenuRelationPO po);

	/**
	 * 根据主键删除数据
	 * public int deleteById(@Param("menuId") int menuId, @Param("roleId") int roleId);
	 */

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(RoleMenuRelationPO po);

	/**
	 * 根据主键读取记录
	 */
	public RoleMenuRelationPO getById(@Param("menuId") int menuId, @Param("roleId") int roleId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<RoleMenuRelationPO> listAll();

	/**
	 * 批量插入角色菜单关系
	 *
	 * @param list
	 */
	void insertBatch(List<RoleMenuRelationPO> list);

	/**
	 * 根据角色编号获取该角色下的菜单编号集合
	 *
	 * @param roleId
	 *            用户角色编号
	 * @return List<RoleMenuRelationDao> 角色菜单实体集合
	 */
	List<RoleMenuRelationPO> listMenusByRoleId(@Param("roleId") int roleId);

	/**
	 * 根据角色编号删除角色/菜单关联关系
	 *
	 * @param roleId
	 *            角色编号
	 */
	void deleteByRoleId(@Param("roleId") int roleId);

	/**
	 * 依据菜单删除角色菜单关系
	 * @param menuId
	 */
	int deleteByMenu(@Param("menuId") int menuId);
}
