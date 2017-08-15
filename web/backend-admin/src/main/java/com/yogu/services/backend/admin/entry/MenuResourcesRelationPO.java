package com.yogu.services.backend.admin.entry;

import java.io.Serializable;


/**
 * 菜单与资源对应表
 * 
 * <pre>
 *     自动生成代码: 表名 mz_menu_resources_relation, 日期: 2015-08-03
 *     menu_id <PK>     int(11)
 *     res_id <PK>      int(11)
 * </pre>
 */
public class MenuResourcesRelationPO implements Serializable {

	private static final long serialVersionUID = -3074457347266402998L;

	/** 菜单id */
	private int menuId;

	/** 资源id */
	private int resId;

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public int getResId() {
		return resId;
	}

}
