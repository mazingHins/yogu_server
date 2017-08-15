package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.RoleAppRelationPO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_role_app_relation 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface RoleAppRelationDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(RoleAppRelationPO po);

	/**
	 * 根据主键删除数据
	 * public int deleteById(@Param("appId") int appId, @Param("roleId") int roleId);
	 */

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(RoleAppRelationPO po);

	/**
	 * 根据主键读取记录
	 */
	public RoleAppRelationPO getById(@Param("appId") int appId, @Param("roleId") int roleId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<RoleAppRelationPO> listAll();

	/**
	 * 批量添加角色应用关系
	 *
	 * @param pos
	 *            - 角色应用关系集合
	 */
	void batchAdd(List<RoleAppRelationPO> pos);

	/**
	 * 依据角色ID删除角色应用关系
	 *
	 * @param roleId
	 *            - 角色ID
	 * @return
	 */
	int deleteByRoleId(@Param("roleId") int roleId);

	/**
	 * 依据角色ID获取角色应用关系
	 *
	 * @param roleId
	 *            - 角色ID
	 * @return
	 */
	List<RoleAppRelationPO> listByRoleId(@Param("roleId") int roleId);
}
