package com.yogu.core.broadcast;

public class BroadcastKey {

	/**
	 * 配置文件有更新,执行reload
	 */
	public static final String CONFIG_CHANGE = "reload_all_config";

	/**
	 * 区域code有变动
	 */
	public static final String AREA_CHANGE = "area_changed";

	/**
	 * 用于白名单的 reload。这个不是 key，而是 key=CONFIG_CHANGE，消息内容=WHITELIST_CHANGE_CONTENT。 白名单广播 和 config 广播共享同一个消息 key (CONFIG_CHANGE) ten 2016/1/14
	 */
	public static final String WHITELIST_CHANGE_CONTENT = "reloadWhiteList";

	/**
	 * 用于餐厅标签的reload. 标签广播 和 config 广播共享同一个消息 key (CONFIG_CHANGE)
	 */
	public static final String STORE_TAG_CHANGE_CONTENT = "reloadStoreTags";

	/**
	 * 资讯推荐的餐厅 数据有变化的reload
	 */
	public static final String NEWS_STORE_RELATION_CHANGE = "reloadStoreStoreInfoRelation";
	
	/**
	 * 用户标签定制池有变化
	 */
	public static final String TAG_CUSTOMIZE_POOL_CHANGE = "tagCustomizePool";
	
	/**
	 * 用于筛选标签有变化值reload
	 */
	public static final String FILTER_TAG_CHANGE = "filterTagChange";

}
