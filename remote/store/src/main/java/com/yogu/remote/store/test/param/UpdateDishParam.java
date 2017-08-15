package com.yogu.remote.store.test.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * @Description: 修改菜品API接收参数,用于简易后台
 * @author Felix
 * @date 2015年8月28日 下午3:22:29
 */
public class UpdateDishParam extends AddDishParam {
	
	/**
	 * 需要删除的菜品介绍图片Id，多个用英文逗号分隔
	 */
	@FormParam("delPicIds")
	@DefaultValue("")
	private String delPicIds;
	
	/**
	 * 图片Id和图片路径的键值对，键值之间以双英文冒号分隔，多个键值对用英文逗号分隔
	 * 1，新添加的图片Id用0表示
	 * 2，删除的图片不用加入到该参数
     * 3，键值对的顺序要按照页面显示的顺序
	 */
	@FormParam("idPathEntry")
	@DefaultValue("")
	private String idPathEntry;
	
	public String getIdPathEntry() {
		return idPathEntry;
	}

	public void setIdPathEntry(String idPathEntry) {
		this.idPathEntry = idPathEntry;
	}

	public String getDelPicIds() {
		return delPicIds;
	}

	public void setDelPicIds(String delPicIds) {
		this.delPicIds = delPicIds;
	}

	
	
	
}
