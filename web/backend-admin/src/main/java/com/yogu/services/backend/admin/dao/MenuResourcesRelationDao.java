package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.MenuResourcesRelationPO;
import com.yogu.services.backend.admin.entry.UrlResourcePO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_menu_resources_relation 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface MenuResourcesRelationDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(MenuResourcesRelationPO po);

	/**
	 * 根据主键删除数据
	 * public int deleteById(@Param("menuId") int menuId, @Param("resId") int resId);
	 */

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(MenuResourcesRelationPO po);

	/**
	 * 根据主键读取记录
	 */
	public MenuResourcesRelationPO getById(@Param("menuId") int menuId, @Param("resId") int resId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<MenuResourcesRelationPO> listAll();

	/**
	 * 根据菜单编号和资源编号获取特定菜单/资源关系
	 *
	 * @param menuId
	 *            菜单编号
	 * @param appId
	 *            应用系统编号
	 * @return List<ResourcePO> 资源实体集合
	 */
	List<UrlResourcePO> listSpecResourceByMenu(@Param("menuId") int menuId, @Param("appId") int appId);

	/**
	 * 查看指定菜单/资源关系
	 *
	 * @param menuId
	 *            菜单编号
	 * @param resId
	 *            资源编号
	 * @return
	 */
	MenuResourcesRelationPO getMenuResource(@Param("menuId") int menuId, @Param("resId") int resId);

	/**
	 * 删除指定的菜单资源关系
	 *
	 * @param entity
	 *            MenuResourcesRelationPO
	 */
	void deleteSpecResourceMenu(MenuResourcesRelationPO entity);

	/**
	 * 获取菜单列表的资源列表
	 * @param menuIds
	 * @return
	 */
	List<Integer> listResource(List<Integer> menuIds);

	/**
	 * 依据资源获取菜单资源关系
	 * @param resId
	 * @return
	 */
	MenuResourcesRelationPO getByResource(@Param("resId") int resId);

	/**
	 * 依据资源删除菜单资源关系
	 * @param resId
	 * @return
	 */
	int deleteByResource(@Param("resId") int resId);

	/**
	 * 依据资源更新菜单资源关系
	 * @param menuResourcesRelationPO
	 * @return
	 */
	int updateMenuByResource(MenuResourcesRelationPO menuResourcesRelationPO);
}
