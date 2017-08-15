package com.yogu.commons.cache;

/**
 * 缓存服务基类 <br>
 * <br>
 * 
 * @author JFan - 2014年10月30日 上午11:01:46
 */
public interface BaseCacheService {

	// 秒
	public static final int EXP_1_S  = 1;
	public static final int EXP_2_S  = EXP_1_S * 2;
	public static final int EXP_3_S  = EXP_1_S * 3;
	public static final int EXP_5_S  = EXP_1_S * 5;
	public static final int EXP_15_S = EXP_5_S * 3;
	public static final int EXP_30_S = EXP_5_S * 6;
	public static final int EXP_45_S = EXP_5_S * 9;

	// 分钟
	public static final int EXP_1_M      = EXP_30_S * 2;
	public static final int EXP_1_M_30_S = EXP_30_S * 3;
	public static final int EXP_2_M      = EXP_30_S * 4;
	public static final int EXP_2_M_30_S = EXP_30_S * 5;
	public static final int EXP_3_M      = EXP_30_S * 6;
	public static final int EXP_5_M      = EXP_1_M * 5;
	public static final int EXP_10_M     = EXP_5_M * 2;
	public static final int EXP_15_M     = EXP_5_M * 3;
	public static final int EXP_30_M     = EXP_5_M * 6;
	public static final int EXP_45_M     = EXP_5_M * 9;

	// 小时
	public static final int EXP_1_H     = EXP_30_M * 2;
	public static final int EXP_1H_30_M = EXP_30_M * 3;
	public static final int EXP_2_H     = EXP_30_M * 4;
	public static final int EXP_2H_30_M = EXP_30_M * 5;
	public static final int EXP_3_H     = EXP_30_M * 6;
	public static final int EXP_6_H     = EXP_3_H * 2;
	public static final int EXP_12_H     = EXP_6_H * 2;

	// 天
	public static final int EXP_1_D = EXP_12_H * 2;
	public static final int EXP_2_D = EXP_1_D * 2;
	public static final int EXP_3_D = EXP_1_D * 3;
	public static final int EXP_5_D = EXP_1_D * 5;
	public static final int EXP_7_D = EXP_1_D * 7;

	/**
	 * 默认30天，Memcache最大时效时间
	 */
	public static final int DEF_EXP = EXP_1_D * 30;

	public static final String DEFAULT_CHARSET = "UTF-8";

	// ###############################################################

	// ## SELECT

	/**
	 * get cache by key, null will return if not found
	 */
	public <V> V get(String key);

//	/**
//	 * get cache by key, null will return if not found <br>
//	 * And security requirements of conversion
//	 */
//	public <V> V get(String key, Transcoder<V> trans);

	/**
	 * The existence of
	 */
	public boolean isExist(String key);

	// ## DELETE

	/**
	 * delete cache by key
	 */
	public boolean delete(String key);

	// ## SET

	/**
	 * put 'key' 'value' to cache, Failure time after 'DEF_EXP'
	 */
	public <V> boolean set(String key, V value);

//	/**
//	 * put 'key' 'value' to cache, Failure time after 'DEF_EXP' <br>
//	 * And security requirements of conversion
//	 */
//	public <V> boolean set(String key, V value, Transcoder<V> trans);

	/**
	 * put 'key' 'value' to cache, Failure time after 'exp'
	 */
	public <V> boolean set(String key, V value, int exp);

//	/**
//	 * put 'key' 'value' to cache, Failure time after 'exp' <br>
//	 * And security requirements of conversion
//	 */
//	public <V> boolean set(String key, V value, int exp, Transcoder<V> trans);

}
