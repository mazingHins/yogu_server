package com.yogu.services.store.resource.vo.store;

import java.util.List;

import com.yogu.services.store.dish.dto.DishGroup;

/**
 * 美食分组的VO
 * @author felix 2016-03-24
 */
public class DishGroupVO extends DishGroup{
	
	private static final long serialVersionUID = 4178553053182787608L;
	
	/** 分组包含的菜品key列表 */
	private List<Long> dishKeyList;

	public List<Long> getDishKeyList() {
		return dishKeyList;
	}

	public void setDishKeyList(List<Long> dishKeyList) {
		this.dishKeyList = dishKeyList;
	}
	
}
