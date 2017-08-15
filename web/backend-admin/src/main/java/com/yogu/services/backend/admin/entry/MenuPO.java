package com.yogu.services.backend.admin.entry;

import java.io.Serializable;
import java.util.Date;


/**
 * 菜单
 * 
 * <pre>
 *     自动生成代码: 表名 mz_menu, 日期: 2015-08-03
 *     menu_id <PK>            int(11)
 *     parent_menu_id    int(11)
 *     app_id            int(11)
 *     menu_name         varchar(30)
 *     url               varchar(200)
 *     sequence          int(11)
 *     operator          int(11)
 *     last_modify       datetime(19)
 *     hide              tinyint(1)
 *     create_time       datetime(19)
 * </pre>
 */
public class MenuPO implements Serializable {

	private static final long serialVersionUID = -3074457344837992196L;

	/** 菜单ID */
	private int menuId;

	/** 父菜单id */
	private int parentMenuId = 0;

	/** 应用系统id */
	private int appId;

	/** 名称 */
	private String menuName;

	/** 资源 */
	private String url;

	/** 排序id号 */
	private int sequence = 0;

	/** 操作者 */
	private long operator;

	/** 更新时间 */
	private Date lastModify;

	/** 菜单是否隐藏，true隐藏false不隐藏 */
	private short hide = 0;

	/** 创建时间 */
	private Date createTime;

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public int getParentMenuId() {
		return parentMenuId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getAppId() {
		return appId;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getSequence() {
		return sequence;
	}

	public void setOperator(long operator) {
		this.operator = operator;
	}

	public long getOperator() {
		return operator;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setHide(short hide) {
		this.hide = hide;
	}

	public short getHide() {
		return hide;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

}
