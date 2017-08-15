package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.AccountAppRelationPO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_account_app_relation 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface AccountAppRelationDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(AccountAppRelationPO po);

	/**
	 * 根据主键删除数据
	 * public int deleteById(@Param("appId") int appId, @Param("uid") long uid);
	 */

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(AccountAppRelationPO po);

	/**
	 * 根据主键读取记录
	 */
	public AccountAppRelationPO getById(@Param("appId") int appId, @Param("uid") long uid);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<AccountAppRelationPO> listAll();

	/**
	 * 删除管理员所有的应用系统访问关系
	 *
	 * @param uid
	 *            - 管理员ID
	 * @return 删除的记录数
	 */
	int deleteByAccountId(@Param("uid") long uid);

	/**
	 * 读取管理员有哪些系统的访问权限，以app_id 的升序排列
	 *
	 * @param uid
	 *            - 管理员ID
	 * @return 返回符合条件的记录；如果没有数据，返回size=0的List
	 */
	List<AccountAppRelationPO> listByAccountId(@Param("uid") long uid);

	/**
	 * 批量添加用户应用关联关系
	 *
	 * @param entities
	 *            用户应用关联关系 实体集合
	 */
	void insertBatch(List<AccountAppRelationPO> entities);

}
