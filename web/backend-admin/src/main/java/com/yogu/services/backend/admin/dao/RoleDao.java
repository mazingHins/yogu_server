package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.RolePO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_role 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface RoleDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(RolePO po);

	/**
	 * 根据主键删除数据
	 *
	 */
	public int deleteById(@Param("roleId") int roleId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(RolePO po);

	/**
	 * 根据主键读取记录
	 */
	public RolePO getById(@Param("roleId") int roleId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<RolePO> listAll();


	/**
	 * 根据角色名获取角色信息
	 *
	 * @param name
	 *            角色名称
	 * @return
	 */
	RolePO getByName(@Param("name") String name);

	/**
	 * 获取所有角色信息
	 *
	 * @return List<RolePO> 角色实体列表
	 */
	List<RolePO> listRoles();


	/**
	 * 获取角色数量
	 * @param name 角色名称，可以为空
	 * @return
	 */
	int statRole(@Param("name") String name);

	/**
	 * 获取角色信息(分页)
	 * @param rolename 角色名称，可以为空
	 * @param startIndex
	 * @param limit
	 * @return
	 */
	List<RolePO> listRolesForPaging(@Param("rolename") String rolename,
									@Param("startIndex") int startIndex, @Param("limit") int limit);

	/**
	 * 依据用户获取用户目前所有的角色的信息
	 *
	 * @param uid
	 * @return
	 */
	List<RolePO> listByAccount(@Param("uid") long uid);
}
