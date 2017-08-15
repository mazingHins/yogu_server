package com.yogu.services.backend.admin.dao;

import com.yogu.commons.datasource.annocation.TheDataDao;
import com.yogu.services.backend.admin.entry.MenuPO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mz_menu 表的操作接口
 * 
 * @author ten 2015-08-05
 */
@TheDataDao
public interface MenuDao {

	/**
	 * 保存数据
	 * 
	 * @param po 对象
	 */
	public void save(MenuPO po);

	/**
	 * 根据主键删除数据
	 *
	 */
	public int deleteById(@Param("menuId") int menuId);

	/**
	 * 修改数据，以主键更新
	 * 
	 * @param po - 要更新的数据
	 * @return 更新的行数
	 */
	public int update(MenuPO po);

	/**
	 * 根据主键读取记录
	 */
	public MenuPO getById(@Param("menuId") int menuId);

	/**
	 * 查询全部记录
	 * 
	 * @return 返回所有的数据，如果没有数据，返回emtpy list
	 */
	public List<MenuPO> listAll();

	/**
	 * 根据应用系统ID和菜单名读取菜单
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param menuName
	 *            - 菜单名
	 * @return 如果记录存在，返回该记录，否则返回null
	 */
	MenuPO getByAppIdAndMenuName(@Param("appId") int appId, @Param("menuName") String menuName);

	/**
	 * 根据appId和menuName更新菜单数据:
	 * url,parent_menu_id,sequence,operator,hide,last_modify
	 *
	 * @param po
	 * @return rows affected
	 */
	int updateMenu(MenuPO po);

	/**
	 * 读取应用系统下所有的菜单
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @return hide=0的所有菜单列表，按sequence倒序,parentMenuId倒序；如果没有记录，返回size=0的List
	 * 			(@author chenjianhong 目前已调整成按parantMenuId倒序,sequence倒序)
	 */
	List<MenuPO> listByAppId(@Param("appId") int appId);

	/**
	 * 获取父菜单的子菜单主键
	 * @param parentMenuId
	 * @return
	 */
	List<Integer> listMenuIdByParent(@Param("parentMenuId") int parentMenuId);

	/**
	 * 统计父菜单的子菜单数量
	 * @param parentMenuId
	 * @return
	 */
	int countByParent(@Param("parentMenuId") int parentMenuId);
}
