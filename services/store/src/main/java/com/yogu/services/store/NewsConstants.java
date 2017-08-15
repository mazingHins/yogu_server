package com.yogu.services.store;

import com.yogu.commons.cache.aspect.AnnoCacheExtendAspecter;

/**
 * 资讯相关的常量
 * 
 * @author sky 2016-05-19
 *
 */
public class NewsConstants {

	public static final int ONE_PAGE_RECORD_SIZE = 20;// 一页数据记录条数

	public static final int MAXPAGE = 10;// 资讯首页最多展示10页数据

	/**
	 * 资讯首页分页数据缓存key前缀
	 */
	private static final String NEWS_BLOCK_FOR_SHOW = "newsIndexPage";

	/**
	 * 资讯阅读数 缓存可以前缀
	 */
	private static final String NEWS_READ_COUNT = "newsReadCount";
	
	
	private static final String ENGLISH_CONFIG_HAS_DATA = "englishHasConfig";

	/**
	 * 分页资讯缓存key
	 * 
	 * @param cityCode
	 * @param pageIndex
	 * @return
	 */
	public static String getIndexPageNewsCacheKey(String cityCode, String langCode, int pageIndex) {
		String key = AnnoCacheExtendAspecter.toKey(NEWS_BLOCK_FOR_SHOW, cityCode, langCode, pageIndex, ONE_PAGE_RECORD_SIZE);
		return key;
	}

	/**
	 * 阅读数 缓存key
	 * 
	 * @param bid 资讯推荐块id
	 * @return
	 */
	public static String getReadCountCacheKey(long bid) {
		String key = AnnoCacheExtendAspecter.toKey(NEWS_READ_COUNT, bid);
		return key;
	}
	
	
	/**
	 * 英文版是否有配置数据 缓存标识key
	 * @return
	 */
	public static  String isEnglishHasConfigDataCacheKey(){
		String key = AnnoCacheExtendAspecter.toKey(ENGLISH_CONFIG_HAS_DATA);
		return key;
	}

}
