package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.AccountRoleRelationPO;
import com.yogu.services.backend.admin.entry.RolePO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_account_role_relation 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface AccountRoleRelationDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(AccountRoleRelationPO po);

	/**
	 * 根据主键删除数据
	 * public int deleteById(@Param("roleId") int roleId, @Param("uid") long uid);
	 */

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(AccountRoleRelationPO po);

	/**
	 * 根据主键读取记录
	 */
	public AccountRoleRelationPO getById(@Param("roleId") int roleId, @Param("uid") long uid);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<AccountRoleRelationPO> listAll();


	/**
	 * 根据账号编号获取用户/角色关系集合
	 *
	 * @param uid
	 *            账号
	 * @return
	 */
	List<RolePO> listRoleByAccount(@Param("uid") long uid);

	/**
	 * 用户角色关联关系是否已经存在
	 *
	 * @param entity
	 * @return 0--不存在 ; 大于0 存在
	 */
	int isExist(AccountRoleRelationPO entity);

	/**
	 * 删除特定的用户角色关联关系
	 *
	 * @param entity
	 *            AccountRoleRelationPO 实体
	 */
	void deleteSpecAccountRole(AccountRoleRelationPO entity);

	/**
	 * 依据用户删除用户角色关系
	 *
	 * @param uid
	 */
	void deleteByAccount(@Param("uid") long uid);

}
