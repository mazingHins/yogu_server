package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.AdminAccountPO;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * mz_admin_account 表的操作接口
 * 
 * @author ten 2015-08-03
 */
@TheDataDao
public interface AdminAccountDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(AdminAccountPO po);

	/**
	 * 根据主键删除数据
	 */
	public int deleteById(@Param("uid") long uid);

	/**
	 * 修改数据，以主键更新，但不更新status
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(AdminAccountPO po);

	/**
	 * 根据主键读取记录
	 * @param uid 用户ID
	 * @return 返回帐号记录，如果没有数据，返回null
	 */
	public AdminAccountPO getById(@Param("uid") long uid);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<AdminAccountPO> listAll();

	/**
	 * 修改状态
	 * @param uid 用户ID
	 * @param newStatus 新状态
	 * @param oldStatus 就状态
	 * @param lastModify 最后修改时间
	 * @return 返回更新的记录行数，等于 1 表示成功，其他为失败
	 */
	int updateStatus(@Param("uid") long uid, @Param("newStatus") short newStatus,
					 @Param("oldStatus") short oldStatus, @Param("lastModify") Date lastModify);

	/**
	 * 分页查询账号信息，如果passport参数为空，则显示所有账号信息；否则模糊查询满足条件的账号信息
	 *
	 * @param keyword 查询的关键字
	 * @param startIndex
	 *            第几页
	 * @param limit
	 *            每页显示条数
	 * @return 返回帐号列表，不会为null
	 */
	List<AdminAccountPO> listAccounts(@Param("keyword") String keyword,
									  @Param("startIndex") int startIndex, @Param("limit") int limit);

	/**
	 * 统计用户数
	 * @param keyword 关键字，可以为空。如果不为空，搜索passport 或真实名称
	 * @return 符合条件的帐号数量
	 */
	int statAccount(@Param("keyword") String keyword);
}
