package com.yogu.services.backend.admin.service;

import com.yogu.commons.utils.resource.MenuItem;
import com.yogu.services.backend.admin.dto.Menu;
import com.yogu.services.backend.admin.dto.UrlResource;

import java.util.List;
import java.util.Map;

/**
 * 菜单 <br>
 * 的操作接口
 * 
 * @author ten 2015-08-05
 */
public interface MenuService {

	/**
	 * 根据主键读取记录
	 */
	public Menu getById(int menuId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<Menu> listAll();

	/**
	 * 更新整个应用系统的菜单
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param root
	 *            - 根节点，不会入库
	 */
	void updateMenu(int appId, MenuItem root);

	/**
	 * 读取菜单树，包括UrlResource的资源
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @return 根节点，根节点不包含在菜单里面
	 */
	MenuItem getMenuTree(int appId);

	/**
	 * 读取菜单树，包括UrlResource的资源（仅生成有权限访问的部分菜单）
	 *
	 * @param accountId
	 *            - 管理员ID
	 * @param appId
	 *            - 应用系统ID
	 * @return 根节点，根节点不包含在菜单里面
	 */
	MenuItem getMenuTree(long accountId, int appId);

	/**
	 * 读取一个用户在某个 应用系统 下所有的资源列表
	 * @param accountId
	 *            - 管理员ID
	 * @param appId
	 *            - 应用系统ID
	 * @return 返回相应的资源，不会返回null
	 */
	List<UrlResource> listMenuResourceByAccount(long accountId, int appId);

	/**
	 * 读取菜单树，不包括资源
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @return 根节点，根节点不包含在菜单里面
	 */
	MenuItem getMenuTreeWithoutResource(int appId);

	/**
	 * 清除多余菜单/资源数据
	 *
	 * @param appId
	 * @param root
	 */
	void clearRedundantMenu(int appId, MenuItem root);

	/**
	 * 获取多余菜单/资源数据
	 *
	 * @param appId
	 * @param root
	 * @return
	 */
	Map<String, Object> getRedundantMenu(int appId, MenuItem root);
}
