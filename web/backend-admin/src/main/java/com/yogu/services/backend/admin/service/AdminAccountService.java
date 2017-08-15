package com.yogu.services.backend.admin.service;

import java.util.List;
import java.util.Set;

import com.yogu.services.backend.admin.dto.AccountRoleRelation;
import com.yogu.services.backend.admin.dto.AdminAccount;

/**
 * 管理员名单 <br>
 * 的操作接口
 * 
 * @author ten 2015-08-05
 */
public interface AdminAccountService {

	/**
	 * 根据主键读取记录
	 */
	public AdminAccount getById(long uid);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<AdminAccount> listAll();

	/**
	 * 返回管理员对哪些应用系统有访问权限，以appId的升序排列
	 *
	 * @param uid
	 *            - 管理员ID
	 * @return 符合条件的appId列表，如果没有记录，返回size=0的List
	 */
	List<Integer> listAccountApps(long uid);

	/**
	 * 分页查询账号信息. 如果keyword参数为空，则显示所有账号信息；否则模糊查询满足条件的账号信息
	 *
	 * @param keyword 用户 keyword 或真实姓名，可为空
	 * @param page
	 *            当前页面 (从1开始)
	 * @param pageSize
	 *            当前页面显示条数
	 * @return List<AdminAccount> 用户实体列表
	 */
	List<AdminAccount> listAccounts(String keyword, int page, int pageSize);

	/**
	 * 冻结帐号。
	 * 只有正常状态的帐号才可以被冻结。
	 * @param uid - 帐号ID
	 * @since 2014/7/18
	 */
	void lockAccount(long uid);

	/**
	 * 解冻帐号。
	 * 只有冻结状态的帐号才可以被解冻。
	 * @param uid - 帐号ID
	 * @since 2014/7/18
	 */
	void unlockAccount(long uid);

	/**
	 * 删除管理员账号<br>
	 * 同时删除mz_account_role_relation、mz_account_app_relation中的绑定关系
	 */
	void deleteAccount(long uid);

	/**
	 * 统计账号数
	 * @param keyword 关键字，可以为空。如果不为空，搜索管理员的真实名称
	 * @return 符合条件的帐号数量
	 */
	int statAccount(String keyword);

	/**
	 * 判断是否属于超级管理员，超级管理员只在系统初始化的时候设置
	 * @param uid 用户ID
	 * @return true=帐号为超级管理员
	 */
	boolean isSuperAdmin(long uid);

	/**
	 * 增加或更新帐号。如果是更新帐号，不会更新帐号状态（冻结、正常等状态）
	 * @param account 帐号信息
	 * @param roleIds 角色ID列表，至少要有一个角色
	 */
	void addOrUpdate(AdminAccount account, Set<Integer> roleIds);

	/**
	 * 返回帐号拥有的角色列表
	 * @param uid 帐号
	 * @return 返回角色列表，如果没有数据，返回size=0的List
	 */
	List<AccountRoleRelation> listAccountRoles(long uid);
}
