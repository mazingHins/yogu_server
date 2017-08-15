/**
 * 
 */
package com.yogu.core.remote.store.provider;

/**
 * 读取store域相关信息的适配器<br>
 * <br>
 * 如果是storeapi，那么使用DB实现 <br>
 * 其他的域则是使用http实现 <br>
 * <br>
 * 注意：当前包下有个HTTP实现，在storeapi项目下有个DB实现（DB实现的优先级高于HTTP）
 *
 * @author JFan 2015年11月9日 下午4:49:57
 */
public interface StoreProvider {

	/** 记录用户是否是这个门店下的员工（注意：存储的是boolean）；组装key需要两个参数：storeId、uid */
	public static final String STORE_USER_STAFF_PREFIX = "StoreUserStaff:";
	/** 手动指定KEY，永久缓存；除非删除菜品信息，否则所属的门店ID不会变 */
	public static final String STORE_DISH_PREFIX = "SApiAuth::Dish";
	/** 手动指定KEY，一般修改服务范围，ID会变 */
	public static final String STORE_RANGE_PREFIX = "SApiAuth::Range";

	/**
	 * 获取用户在指定门店下的角色
	 * 
	 * @param storeId 门店ID
	 * @param uid userId
	 */
	public Boolean whetherStoreStaff(long storeId, long uid);

	/**
	 * 读取菜品所属的门店ID
	 * 
	 * @param dishId 菜品ID
	 */
	public Long getDishStoreId(long dishId);

	/**
	 * 读取服务范围所属的门店ID
	 * 
	 * @param rangeId 配送ID
	 */
	public Long getRangeStoreId(long rangeId);

}
