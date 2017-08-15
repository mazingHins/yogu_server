package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.RoleResRelationPO;
import com.yogu.services.backend.admin.entry.UrlResourcePO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_role_res_relation 表的操作接口
 * 
 * @author ten 2015-12-07
 */
@TheDataDao
public interface RoleResRelationDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(RoleResRelationPO po);

	// /**
	//  * 根据主键删除数据
	//  */
	// public int deleteById(@Param("resId") int resId, @Param("roleId") int roleId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(RoleResRelationPO po);

	/**
	 * 根据roleId读取角色的资源权限
	 * @param roleId 角色ID
	 * @return 返回该角色拥有的资源ID列表
	 */
	public List<RoleResRelationPO> listByRole(@Param("roleId") int roleId);

	/**
	 * 删除角色对应的资源权限
	 * @param roleId 角色ID
	 * @return 删除的记录行数
	 */
	public int deleteByRole(@Param("roleId") int roleId);

	/**
	 * 批量保存数据
	 * @param list 数据列表
	 */
	void insertBatch(List<RoleResRelationPO> list);

	/**
	 * 查询角色访问的URL
	 * @param roleId 角色ID
	 * @return 返回角色拥有的URL权限表
	 */
	List<UrlResourcePO> listResourceByRole(@Param("roleId") int roleId);
}
