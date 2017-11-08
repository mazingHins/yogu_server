package com.yogu.services.backend.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.VOUtil;
import com.yogu.commons.utils.encrypt.EncryptUtil;
import com.yogu.commons.utils.resource.MenuItem;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.backend.admin.context.AdminContext;
import com.yogu.services.backend.admin.dao.MenuDao;
import com.yogu.services.backend.admin.dao.MenuResourcesRelationDao;
import com.yogu.services.backend.admin.dao.RoleResRelationDao;
import com.yogu.services.backend.admin.dao.UrlResourceDao;
import com.yogu.services.backend.admin.dto.AccountRoleRelation;
import com.yogu.services.backend.admin.dto.Menu;
import com.yogu.services.backend.admin.dto.RoleMenuRelation;
import com.yogu.services.backend.admin.dto.UrlResource;
import com.yogu.services.backend.admin.entry.MenuPO;
import com.yogu.services.backend.admin.entry.MenuResourcesRelationPO;
import com.yogu.services.backend.admin.entry.UrlResourcePO;
import com.yogu.services.backend.admin.service.AdminAccountService;
import com.yogu.services.backend.admin.service.MenuService;
import com.yogu.services.backend.admin.service.RoleService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * MenuService 的实现类
 * 
 * @author ten 2015-08-05
 */
@Service
public class MenuServiceImpl implements MenuService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private UrlResourceDao urlResourceDao;

	@Autowired
	private MenuResourcesRelationDao menuResourcesRelationDao;

	@Autowired
	private RoleService roleService;

	@Autowired
	private AdminAccountService adminAccountService;

	@Autowired
	private RoleResRelationDao roleResRelationDao;

	// 用于生成菜单的时候，菜单ID、资源ID是否重复
	private static ThreadLocal<Set<Integer>> MENU_ID_SET=  new ThreadLocal<>();
	private static ThreadLocal<Set<Integer>> RESOURCE_ID_SET = new ThreadLocal<>();

	@Override
	public Menu getById(int menuId) {
		MenuPO po = menuDao.getById(menuId);
		if (null == po)
			return null;
		return po2dto(po);
	}

	@Override
	public List<Menu> listAll() {
		List<MenuPO> list = menuDao.listAll();
		List<Menu> result = new ArrayList<Menu>();
		if (null != list)
			for(MenuPO po : list)
				result.add(po2dto(po));
		return result;
	}

	// ####
	// ## private func
	// ####

	public Menu po2dto(MenuPO po) {
		return VOUtil.from(po, Menu.class);
	}

	public MenuPO dto2po(Menu dto) {
		return VOUtil.from(dto, MenuPO.class);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void updateMenu(int appId, MenuItem root) {
		MENU_ID_SET.set(new HashSet<Integer>(128));
		RESOURCE_ID_SET.set(new HashSet<Integer>(256));
		try {
			saveMenuNode(appId, root, null);
		} finally {
			// clear
			MENU_ID_SET.remove();
			RESOURCE_ID_SET.remove();;
		}
	}

	/**
	 * 保存菜单树
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param item
	 *            - 要保存的节点
	 * @param parent
	 *            - 父节点
	 */
	private void saveMenuNode(int appId, MenuItem item, MenuItem parent) {
		List<MenuItem> children = item.getChildren();
		if (children == null || children.isEmpty()) {
			// 没有下一层菜单了，这是资源
//			saveMenuResource(appId, item, parent);
		}
		else {
			if (!item.isRoot()) {
				// root节点不处理
				saveMenu(appId, item, parent);
			}
			// 处理子节点
			for (MenuItem mi : children) {
				saveMenuNode(appId, mi, item);
			}
		}
	}

	/**
	 * 为固定的 name 生成一个固定的ID
	 * @param isMenu
	 * @param name 资源名称
	 * @param url 资源url
	 * @return
	 */
	private int generateIdForName(boolean isMenu, String name, String url) {
		Set<Integer> set = (isMenu ? MENU_ID_SET.get() : RESOURCE_ID_SET.get());
		String md5 = EncryptUtil.getMD5(name);
		int hashCode = md5.hashCode();
		Integer code = Math.abs(hashCode);
		if (set.contains(code)) {
			logger.error("admin#menu | 计算ID重复 | name: {}, id: {}, url: {}", name, code, url);
			throw new ServiceException(ResultCode.FAILURE, (isMenu ? "菜单" : "资源") + "ID重复: " + name);
		}
		// 加入 set
		set.add(code);
		logger.info("admin#menu | 计算ID | name: {}, id: {}, url: {}", name, code, url);
		return code.intValue();
	}

	/**
	 * 保存菜单信息
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param item
	 *            - 要保存的节点
	 * @param parent
	 *            - 父节点
	 */
	private void saveMenu(int appId, MenuItem item, MenuItem parent) {
		MenuPO menu = menuDao.getByAppIdAndMenuName(appId, item.getName());
		MenuPO parentMenu = null;
		if (parent != null) {
			parentMenu = menuDao.getByAppIdAndMenuName(appId, parent.getName());
		}
		if (menu == null) {
			// 新增菜单
			menu = new MenuPO();

			if (parentMenu != null) {
				menu.setParentMenuId(parentMenu.getMenuId());
			}
			menu.setMenuId(generateIdForName(true, item.getName(), item.getUrl()));
			menu.setAppId(appId);
			menu.setCreateTime(new Date());
			menu.setLastModify(menu.getCreateTime());
			menu.setHide((short) 0);
			menu.setMenuName(item.getName());
			menu.setOperator(AdminContext.getAccountId());
			menu.setUrl(item.getUrl());
			menu.setSequence(item.getSequence());
			menuDao.save(menu);
		}
		else {
			// 更新菜单
			// url,parent_menu_id,sequence,operator,hide,last_modify
			if (parentMenu != null) {
				menu.setParentMenuId(parentMenu.getMenuId());
			}
			// menu.setUrl(item.getUrl());
			menu.setOperator(AdminContext.getAccountId());
			menu.setUrl(item.getUrl());
			menu.setSequence(item.getSequence());
			menu.setLastModify(new Date());
			menuDao.updateMenu(menu);
		}
	}

	/**
	 * 保存资源信息。 NOTE：各资源独立，暂时没有 parentResId
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param item
	 *            - 要保存的节点
	 * @param parentMenu
	 *            - 属于哪个菜单
	 */
	private void saveMenuResource(int appId, MenuItem item, MenuItem parentMenu) {
		if (parentMenu == null) {
			throw new IllegalArgumentException("资源所属的菜单为空, item=" + item.toString());
		}
//		UrlResourcePO res = urlResourceDao.getByAppIdAndUri(appId, item.getUrl());
		MenuPO menu = menuDao.getByAppIdAndMenuName(appId, parentMenu.getName());
		if (menu == null) {
			logger.error("资源所属的菜单在数据库里不存在， item: {}, parent: {}", item.getId(), parentMenu.getId());
			throw new IllegalArgumentException("资源所属的菜单在数据库里不存在");
		}

	}

	@Override
	public MenuItem getMenuTree(int appId) {
		List<MenuPO> menuList = menuDao.listAll();
		MenuItem root = new MenuItem("root", "", true);
		if (menuList.size() > 0) {
			buildTreeMap(appId, root, menuList);
			loadMenuResource(appId, root);
		}
		return root;
	}

	@Override
	public MenuItem getMenuTree(long accountId, int appId) {
		if (accountId <= 0) {
			return new MenuItem("root", "", true);
		}
		List<MenuPO> menuList = menuDao.listAll();
		menuList = gainHasPermissionMenu(accountId, menuList);
		MenuItem root = new MenuItem("root", "", true);
		if (menuList.size() > 0) {
			buildTreeMap(appId, root, menuList);
//			loadMenuResource(appId, root);
		}
		return root;
	}

	/**
	 * 获取有权限的菜单
	 *
	 * @param accountId
	 * @param menuList
	 * @return
	 */
	private List<MenuPO> gainHasPermissionMenu(long accountId, List<MenuPO> menuList) {

		return menuList;

//		// 读取角色
//		List<AccountRoleRelation> accountRoleList = adminAccountService.listAccountRoles(accountId);
//		List<RoleMenuRelation> roleMenuList = new LinkedList<>();
//		// 读取每个角色的菜单ID
//		for (AccountRoleRelation arr : accountRoleList) {
//			roleMenuList.addAll(roleService.listRoleMenus(arr.getRoleId()));
//		}
//
//		Set<Integer> accountMenuIdSet = new HashSet<>(roleMenuList.size());
//		for (RoleMenuRelation rmr : roleMenuList) {
//			accountMenuIdSet.add(Integer.valueOf(rmr.getMenuId()));
//		}
//
//		// 判断哪个菜单有权限
//		List<MenuPO> list = new LinkedList<>();
//		StringBuilder buf = new StringBuilder(1024);
//		for (MenuPO menuPO : menuList) {
//			if (accountMenuIdSet.contains(Integer.valueOf(menuPO.getMenuId()))) {
//				list.add(menuPO);
//				if (buf.length() > 0) buf.append(" , ");
//				buf.append(menuPO.getMenuId()).append(": ").append(menuPO.getMenuName());
//			}
//		}
//		return list;
	}

	@Override
	public MenuItem getMenuTreeWithoutResource(int appId) {
		List<MenuPO> menuList = menuDao.listByAppId(appId);
		MenuItem root = new MenuItem("root", "", true);
		if (menuList.size() > 0) {
			buildTreeMap(appId, root, menuList);
		}
		return root;
	}

	/**
	 * 加载每个菜单下的资源
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param item
	 *            - 菜单项
	 */
	private void loadMenuResource(int appId, MenuItem item) {
		List<MenuItem> children = item.getChildren();
		if (children == null || children.isEmpty()) {
			// 叶子节点才是带有资源的菜单
			if (item.isRoot()) {
				// 没有菜单
				return;
			}
			MenuPO menu = menuDao.getByAppIdAndMenuName(appId, item.getName());
			List<UrlResourcePO> resList = menuResourcesRelationDao.listSpecResourceByMenu(menu.getMenuId(), appId);
			for (UrlResourcePO res : resList) {
				MenuItem resItem = new MenuItem(res.getName(), res.getUri());
				// TIP chenjianhong 主键提供给界面
				resItem.setId(res.getResId());
				item.createChildren().add(resItem);
			}
		}
		else {
			for (MenuItem mi : children) {
				loadMenuResource(appId, mi);
			}
		}
	}

	/**
	 * 建立菜单树
	 *
	 * @param appId
	 *            - 应用系统ID
	 * @param root
	 *            - 根节点
	 * @param menuList
	 *            - 菜单列表
	 */
	private void buildTreeMap(int appId, MenuItem root, List<MenuPO> menuList) {
		Map<Integer, MenuItem> tmpMap = new HashMap<>(menuList.size() * 4 / 3);
		// 2014/8/14 增加，原因：个别程序员增加了几个找不到 parentMenuId 的菜单，导致死循环
		final int LOOP_MAX = menuList.size() * menuList.size() + 10;
		int loopCount = 0;
		int i;
		for (i = 0; i < menuList.size() && loopCount < LOOP_MAX;) {
			MenuPO menu = menuList.get(i);
			if (menu.getParentMenuId() == 0) {
				if (tmpMap.containsKey(menu.getMenuId())) {
					// 忽略，不可能出现
					;
				}
				else {
					MenuItem item = new MenuItem(menu.getMenuName(), "");
					item.setId(menu.getMenuId());
					item.setSequence(menu.getSequence());
					root.createChildren().add(item);
					tmpMap.put(menu.getMenuId(), item);
				}
				i++;
			}
			else {
				MenuItem parent = tmpMap.get(menu.getParentMenuId());
				if (parent == null) {
					// 找不到父菜单，放到队列后面处理
					if (i == menuList.size() - 1) {
						// 如果已经跑到队列最后，终止，避免死循环
						break;
					}
					// 碰到有问题的菜单，可能有死循环 2014/8/14
					menuList.remove(i);
					menuList.add(menu); // 放到最后
				}
				else {
					// 增加一个子菜单
					MenuItem item = new MenuItem(menu.getMenuName(), "");
					item.setId(menu.getMenuId());
					item.setSequence(menu.getSequence());
					parent.createChildren().add(item);
					tmpMap.put(menu.getMenuId(), item);
					i++; // next
				}
			}
			++loopCount;
		}
		if (loopCount >= LOOP_MAX) {
			MenuPO tmp = (i < menuList.size() ? menuList.get(i) : null);
			logger.info("菜单死循环, LOOP_MAX=" + LOOP_MAX + ", appId=" + appId + ", 有问题的菜单=" + JSON.toJSONString(tmp));
//			AlarmSender.create().alarm("菜单死循环！", "菜单死循环 LOOP_MAX=" + LOOP_MAX + ", menu=" + tmp);
		}
	}

	/**
	 * 删除菜单
	 * @param menu
	 */
	private void deleteMenu(Menu menu) {
		int menuId = menu.getMenuId();
		ParameterUtil.assertGreaterThanZero(menuId, "菜单不能为空.");
		ParameterUtil.assertGreaterThanZero(menu.getAppId(), "所属应用不能为空.");
		ParameterUtil.assertNonnegativeInt(menu.getParentMenuId(), "所属菜单不能为空.");

		// 判断逻辑: 保证所传menuId对应的数据的正确性; 旨在解决传输非法构造数据以及错把资源当菜单的情况
		MenuPO menuInDB = menuDao.getById(menu.getMenuId());
		ParameterUtil.assertEqual(menu.getAppId(), menuInDB.getAppId(), "该应用系统下不存在此菜单(确认下是否属于资源).");
		ParameterUtil.assertEqual(menu.getParentMenuId(), menuInDB.getParentMenuId(), "该父菜单下不存在此菜单(确认下是否属于资源).");

		// delete role_menu
		logger.info("删除角色菜单关系." + "[menuId=" + menuId + "]");
		roleService.deleteMenu(menuId);

		// delete menu
		logger.info("删除菜单." + "[menuId=" + menuId + "]");
		menuDao.deleteById(menuId);

		// 获取菜单下面的子菜单(一级)
		List<Integer> subMenuIds = menuDao.listMenuIdByParent(menuId);

		// 如果子菜单不为空, 则递归删除子菜单; 如果子菜单为空, 则删除菜单资源关系以及对应的资源
		if (subMenuIds != null && !subMenuIds.isEmpty()) {
			for (Integer subMenuId : subMenuIds) {
				MenuPO subMenuPO = menuDao.getById(subMenuId);
				Menu subMenu = VOUtil.from(subMenuPO, Menu.class);
				deleteMenu(subMenu);
			}
		}
		else {
			List<Integer> resIds = menuResourcesRelationDao.listResource(Arrays.asList(menuId));
			for (Integer resId : resIds) {
				// delete menu_resource
				logger.info("删除菜单资源关系." + "[resId=" + resId + "]");
				menuResourcesRelationDao.deleteByResource(resId);

				// delete resource
				logger.info("删除资源." + "[resId=" + resId + "]");
				urlResourceDao.deleteById(resId);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void clearRedundantMenu(int appId, MenuItem root) {
		List<Menu> menus = listToBeDeletedMenu(appId, root);
		List<UrlResource> resources = listToBeDeletedResource(appId, root);

		for (Menu menu : menus) {
			logger.info("删除多余菜单." + "[menu=" + JSON.toJSONString(menu) + "]");
			deleteMenu(menu);
		}

		for (UrlResource resource : resources) {
			int resId = resource.getResId();

			logger.info("删除多余菜单资源关系." + "[resId=" + resId + "]");
			menuResourcesRelationDao.deleteByResource(resId);

			logger.info("删除多余资源." + "[resId=" + resId + "]");
			urlResourceDao.deleteById(resId);
		}
	}

	/**
	 * 获取待删除的菜单
	 *
	 * @param appId
	 * @param root
	 * @return
	 */
	private List<Menu> listToBeDeletedMenu(int appId, MenuItem root) {
		List<Menu> menus = new ArrayList<>();

		List<MenuPO> menuPOs = menuDao.listByAppId(appId);
		Map<String, Boolean> menuMap = mapMenus(appId, root);

		for (MenuPO menuPO : menuPOs) {
			String key = menuPO.getAppId() + "_" + menuPO.getMenuName();
			if (!menuMap.containsKey(key)) {
				Menu menu = VOUtil.from(menuPO, Menu.class);
				menus.add(menu);
			}
		}

		return menus;
	}

	/**
	 * 获取待删除的资源
	 *
	 * @param appId
	 * @param root
	 * @return
	 */
	private List<UrlResource> listToBeDeletedResource(int appId, MenuItem root) {
		List<UrlResource> resources = new ArrayList<>();

		List<UrlResourcePO> urlResourcePOs = urlResourceDao.listByApp(appId);
		Map<String, Boolean> resourceMap = mapResources(appId, root);

		for (UrlResourcePO urlResourcePO : urlResourcePOs) {
			String key = urlResourcePO.getAppId() + "_" + urlResourcePO.getUri();
			if (!resourceMap.containsKey(key)) {
				UrlResource resource = VOUtil.from(urlResourcePO, UrlResource.class);
				resources.add(resource);
			}
		}

		return resources;
	}

	/**
	 * 获取类路径下类所包含的菜单列表
	 *
	 * @param appId
	 * @param root
	 * @return
	 */
	private Map<String, Boolean> mapMenus(int appId, MenuItem root) {
		Map<String, Boolean> map = new HashMap<>();
		List<MenuItem> children = root.getChildren();
		if (children != null && !children.isEmpty()) {
			String key = appId + "_" + root.getName();
			map.put(key, Boolean.TRUE);

			for (MenuItem item : children) {
				Map<String, Boolean> childrenMap = mapMenus(appId, item);
				map.putAll(childrenMap);
			}
		}
		return map;
	}

	/**
	 * 获取类路径下类所包含的资源列表
	 *
	 * @param appId
	 * @param root
	 * @return
	 */
	private Map<String, Boolean> mapResources(int appId, MenuItem root) {
		Map<String, Boolean> map = new HashMap<>();
		List<MenuItem> children = root.getChildren();
		if (children == null || children.isEmpty()) {
			String key = appId + "_" + root.getUrl();
			map.put(key, Boolean.TRUE);
			return map;
		}
		else {
			for (MenuItem item : children) {
				Map<String, Boolean> childrenMap = mapResources(appId, item);
				map.putAll(childrenMap);
			}
			return map;
		}
	}

	@Override
	public Map<String, Object> getRedundantMenu(int appId, MenuItem root) {
		Map<String, Object> map = new HashMap<>();

		List<Menu> menus = listToBeDeletedMenu(appId, root);
		List<UrlResource> resources = listToBeDeletedResource(appId, root);

		map.put("menus", menus);
		map.put("resources", resources);

		return map;
	}

	@Override
	public List<UrlResource> listMenuResourceByAccount(long accountId, int appId) {

		// 读取角色
		List<AccountRoleRelation> accountRoleList = adminAccountService.listAccountRoles(accountId);
//		List<RoleMenuRelation> roleMenuList = new LinkedList<>();
		// 读取每个角色的菜单ID
//		for (AccountRoleRelation arr : accountRoleList) {
//			roleMenuList.addAll(roleService.listRoleMenus(arr.getRoleId()));
//		}

		/*
		Map<Integer, UrlResourcePO> resourceMap = new HashMap<>(roleMenuList.size() * 4 + 16);

		for (RoleMenuRelation rmr : roleMenuList) {
			List<UrlResourcePO> tmpList = menuResourcesRelationDao.listSpecResourceByMenu(rmr.getMenuId(), appId);
			for (UrlResourcePO res : tmpList) {
				Integer resId = res.getResId();
				if (!resourceMap.containsKey(resId)) {
					resourceMap.put(resId, res);
				}
			}
		}
		Collection<UrlResourcePO> collections = resourceMap.values();
		return VOUtil.fromList(collections, UrlResource.class);
		*/
		Map<Integer, UrlResourcePO> resourceMap = new HashMap<>(10);
		List<UrlResourcePO> list = urlResourceDao.listAll();
		for (UrlResourcePO res : list) {
			Integer resId = res.getResId();
			if (!resourceMap.containsKey(resId)) {
				resourceMap.put(resId, res);
			}
		}
//		for (AccountRoleRelation arr : accountRoleList) {
//			List<UrlResourcePO> list = roleResRelationDao.listResourceByRole(arr.getRoleId());
//			for (UrlResourcePO res : list) {
//				Integer resId = res.getResId();
//				if (!resourceMap.containsKey(resId)) {
//					resourceMap.put(resId, res);
//				}
//			}
//		}
		Collection<UrlResourcePO> collections = resourceMap.values();
		return VOUtil.fromList(collections, UrlResource.class);
	}
}
