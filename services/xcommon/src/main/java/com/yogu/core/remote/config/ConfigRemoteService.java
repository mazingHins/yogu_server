package com.yogu.core.remote.config;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.cfg.GlobalSetting;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.ServiceLoaderUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;
import com.yogu.core.broadcast.BroadcastKey;
import com.yogu.core.remote.config.provider.ConfigProvider;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.mq.ServerMsgNotice;
import com.yogu.mq.ServerMsgService;

/**
 * 通过接口读取系统所有配置的信息，缓存在本JVM内
 * 
 * @author linyi 2015/5/17.
 */
@Named
public class ConfigRemoteService implements ServerMsgNotice {

	private static final Logger logger = LoggerFactory.getLogger(ConfigRemoteService.class);

	// 本来打算取消static方法访问的，但是 CloudSettings中要用到，那么为了防止出问题，给个空的内容
	private static Map<String, Map<String, String>> NULL = new HashMap<String, Map<String, String>>();
	// 系统配置表
	private static Map<String, Map<String, String>> configCache = NULL;
	
	/**
	 * 城市下的用户标签池列表。key：cityCode。 value：该城市下的标签配置列表
	 */
	private static Map<String, List<CustomizePoolVO>> customizePoolCache = new HashMap<String, List<CustomizePoolVO>>();
	
	/**
	 * 每个用户标签配置池下的餐厅标签列表。key：用户配置id。value：餐厅标签列表
	 */
	private static Map<Long, List<Integer>> storeTagsByCustomizeCache = new HashMap<>();

	private static final Set<Long> NULL_SET1 = new HashSet<>();
	private static final Set<String> NULL_SET2 = new HashSet<>();

	/** 白名单数据：uid */
	private static Set<Long> whiteUidSet = NULL_SET1;
	/** 白名单数据：did */
	private static Set<String> whiteDidSet = NULL_SET2;

	private static final List<StoreCategoryVO> NULL_TAG = new ArrayList<StoreCategoryVO>();

	private static final int EMPTY_LIST = 0;

	private static List<StoreCategoryVO> storeTags = NULL_TAG;

	private static List<StoreCategoryVO> enStoreTags = NULL_TAG;
	
	private static String lock = "1";
	
	private static List<FilterTagCategoryVO> filterTags = new ArrayList<FilterTagCategoryVO>();
	
	private static List<FilterTagCategoryVO> enFilterTags = new ArrayList<FilterTagCategoryVO>();
	
	private static Map<String, List<FilterTagCategoryVO>> cityFilterTagMap = new HashMap<String, List<FilterTagCategoryVO>>();
	// private static final List<StoreCategoryVO> NULL_TAG = new ArrayList<StoreCategoryVO>();

	@Inject
	private ServerMsgService serverMsgService;

	private static ConfigProvider configProvider;

	@PostConstruct
	public void initial() {
		serverMsgService.regNotice(BroadcastKey.CONFIG_CHANGE, this);

		// 装载configProvider对象
		logger.debug("Loading 'ConfigProvider' ......");
		configProvider = ServiceLoaderUtils.orderlyOne(ConfigProvider.class);
		Args.check(null != configProvider, "没有找到configProvider的实现");
		logger.info("Loading 'ConfigProvider' is {}", configProvider.getClass());
	}

	/**
	 * 返回图片域名，以 / 结尾
	 * 
	 * @return 图片域名，永远不会返回 null
	 */
	public static String getImageDomain() {
		Map<String, String> map = ConfigRemoteService.getConfigMap(ConfigGroupConstants.IMG_SERVER);
		final String DEFAULT_DOMAIN = "http://img.mazing.com/";
		if (map == null || map.isEmpty()) {
			return DEFAULT_DOMAIN;
		} else {
			String domain = map.values().iterator().next();
			if (StringUtils.isNotEmpty(domain) && !domain.endsWith("/")) {
				domain = domain + "/";
			}
			return (StringUtils.isEmpty(domain) ? DEFAULT_DOMAIN : domain);
		}
	}

	/**
	 * 返回https的图片域名
	 * 
	 * @return https开头的图片域名，永远不会返回 null
	 */
	public static String getImageDomainForHttps() {
		return getImageDomain().replace("http://", "https://");
	}

	/**
	 * 获取系统配置集合<br>
	 * 获取配置，严格区分大小写
	 */
	public static Map<String, String> getConfigMap(String groupCode) {
		return getCache().get(groupCode);
	}

	/**
	 * 获取系统配置集合<br>
	 * 获取配置，严格区分大小写
	 */
	public static String getConfig(String groupCode, String configKey) {
		Map<String, String> map = getConfigMap(groupCode);
		logger.info("config map : {}", JsonUtils.toJSONString(map));
		return (null == map ? null : map.get(configKey));
	}
	
	/**
	 * 重新装载每个用户标签配置下的餐厅标签列表数据
	 * 
	 * @param list - 该城市下的用户标签配置下的餐厅标签列表数据
	 * @author hins
	 * @date 2017年1月16日 下午3:31:28
	 * @return void
	 */
	private static void reloadCustomizeStoreTag(List<CustomizePoolVO> list){
		for(CustomizePoolVO pool : list){
			storeTagsByCustomizeCache.put(pool.getCustomizeId(), pool.getTagList());
		}
	}
	
	/**
	 * 根据用户标签配置id，获取该配置下的标签池列表，池结果无序
	 * 
	 * @param customizeId - 用户标签配置id
	 * @author hins
	 * @date 2017年1月16日 下午3:46:43
	 * @return 餐厅标签列表，如果无，返回empty list
	 */
	public static List<Integer> getStoreTagByCustomizeId(long customizeId) {
		List<Integer> list = storeTagsByCustomizeCache.get(customizeId);
//		if (list == null) {
//			return Collections.emptyList();
//		}
		return list == null ? Collections.<Integer> emptyList() : list;
	}

	/**
	 * 返回 key 和 value 是否需要 decode
	 * 
	 * @param groupCode 分组代码，区分大小写
	 * @return true表示 configKey 和 configValue 都需要 decode
	 */
	public static boolean isKeyAndValueNeedDecode(String groupCode) {
		if (groupCode.equals("appKey")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断 value 是否需要 decode
	 * 
	 * @param configKey 配置key，不区分大小写
	 * @return
	 */
	public static boolean isValueNeedDecode(String groupCode, String configKey) {
		if (groupCode.equals("imConfig")) {
			return true;
		}
		configKey = configKey.toLowerCase();
		if (configKey.indexOf("username") >= 0 //
				|| configKey.indexOf("password") >= 0 //
				|| configKey.indexOf("accesskey") >= 0 //
				|| configKey.indexOf("certsecret") >= 0
				|| configKey.indexOf("accesssecret") >= 0
				|| configKey.indexOf("couponidaccesskey") >= 0) {
			return true;
		}
		return false;
	}

	// ####
	// ## private
	// ####

	private static Map<String, Map<String, String>> getCache() {
		if (null == configCache || NULL == configCache){//cache为空才load DB
			
			synchronized (lock) {//资源控制, modify by Sky 2017-02-14(防止服务启动时多个请求都穿透去load DB)
				
				if (null == configCache || NULL == configCache)//再判断一次是否为空，因为这个时候获得了锁，但可能已经被其他请求处理过了(load DB)
					reloadAllConfigs();
			}
			
		}
		return configCache;
	}

	/**
	 * 重新读取所有的系统配置
	 */
	private static void reloadAllConfigs() {
		long t1 = System.currentTimeMillis();
		try {
			List<Config> result = configProvider.getAllConfig();

			DesPropertiesEncoder decoder = new DesPropertiesEncoder();
			Map<String, Map<String, String>> tmpMap = new HashMap<>();
			for (Config c : result) {
				String groupCode = c.getGroupCode();
				Map<String, String> map = tmpMap.get(groupCode);
				if (null == map) {
					map = new HashMap<String, String>();
					tmpMap.put(groupCode, map);
				}
				String theKey = c.getConfigKey();
				String theValue = c.getConfigValue();

				boolean decodeValue = false;
				String tmpKey = theKey.toLowerCase();
				// 根据configGroup来判定是否需要解密(group为全匹配，区分大小写) ---- key、value都需要解密
				if (isKeyAndValueNeedDecode(groupCode)) {
					theKey = decoder.decode(theKey);// 解密key
					decodeValue = true;// 需要解密value
				}
				// 根据configKey来判定是否需要解密
				else if (isValueNeedDecode(groupCode, tmpKey)) {
					decodeValue = true;// 需要解密value
				}

				if (decodeValue) // 解密value
					theValue = decoder.decode(theValue);

				map.put(theKey, theValue);
			}
			configCache = tmpMap;
			long time = System.currentTimeMillis() - t1;
			logger.info("config#remote#reload | 重新加载系统配置成功 | size: {}, time: {}", result.size(), time);
		} catch (Exception e) {
			logger.error("config#remote#reload | 重新加载系统配置异常", e);
		}

	}

	/**
	 * 根据用户的cityCode去获取筛选标签分类列表
	 * @param filterTagList 包含了所有城市的筛选标签分类列表(包含标签列表)
	 * @return    
	 * @author east
	 * @date 2017年1月11日 下午4:10:58
	 */
	private static List<FilterTagCategoryVO> getFilterTagsByCityCode(List<FilterTagCategoryVO> filterTagList){
		//把当前cityCode和lang作为缓存的key
		String key = SecurityContext.getCityCode() + "_" + SecurityContext.getAppLanguage().getCode();
		//用户存放cityCode的筛选标签list
		List<FilterTagCategoryVO> tmpResult = new ArrayList<FilterTagCategoryVO>();
		
		//防止多个同样城市同样语言的用户同时进入,加个锁
		synchronized (key) {
			//根据cityCode把筛选标签放进缓存
			List<FilterTagCategoryVO> result = cityFilterTagMap.get(key);
			if(result != null){
				logger.info("config#remote#getFilterTagsByCityCode | 从缓存中加载筛选标签 | cacheKey:{}, result:{}", key, JsonUtils.toJSONString(result));
				return result;
			}
			
			for(FilterTagCategoryVO tagCategoryVO: filterTagList){
				List<FilterTagVO> tagList = tagCategoryVO.getTagList();
				//创建一个临时的list，来保存当前cityCode对应的数据
				List<FilterTagVO> tmpList = new ArrayList<FilterTagVO>(tagList.size() * 4 / 3);
				
				for(FilterTagVO filterTagVO: tagList){
					//获取用户当前城市编码所对应的标签
					if(StringUtils.isNotBlank(filterTagVO.getCityCode()) && filterTagVO.getCityCode().equals(SecurityContext.getCityCode())){
						tmpList.add(filterTagVO);
					}
				}
				//当前类别如果tag为空，则不返回数据
				if( !tmpList.isEmpty()){
					tagCategoryVO.setTagList(tmpList);
					tmpResult.add(tagCategoryVO);
				}
			}
			
			//如果当前城市没有数据，则默认返回广州
			if(tmpResult.isEmpty())
				tmpResult = cityFilterTagMap.get(CommonConstants.DEFAULT_CITY + "_" + SecurityContext.getAppLanguage().getCode());
			
			//放到jvm缓存中
			if(tmpResult != null && !tmpResult.isEmpty()){
				cityFilterTagMap.put(key, tmpResult);
			}
		}
		
		logger.info("config#remote#getFilterTagsByCityCode | 从DB中加载筛选标签 | cacheKey:{}, result:{}", key, JsonUtils.toJSONString(tmpResult));
		return tmpResult;
	}
	@Deprecated
	private static void reloadStoreTags() {
		enStoreTags = null;
		storeTags = null;
	}
	
	private static void reloadFilterTags() {
		filterTags = null;
		enFilterTags = null;
		cityFilterTagMap = new HashMap<String, List<FilterTagCategoryVO>>();
	}
	// ####
	// ## notice
	// ####

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.mq.ServerMsgNotice#notice(java.lang.String)
	 */
	@Override
	public void notice(String messageId, String message) {

		logger.info("config#remoteService#notice | receive broadcast message , start reload all configs | messageId: {}, message: {}",
				messageId, message);
		if (BroadcastKey.STORE_TAG_CHANGE_CONTENT.equalsIgnoreCase(message)) {
			reloadStoreTags();
		} else if(BroadcastKey.FILTER_TAG_CHANGE.equalsIgnoreCase(message)){
			reloadFilterTags();
		} else {
			reloadAllConfigs();
		}
	}
	
	/**
	 * 根据模块，从config获取每个模块下的机器号。
	 * 该方法不适用于同一个机器，不是2个相同的服务，如126服务器，部署2个user服务
	 * 
	 * @param modul - 模块，使用maven配置(compile脚本)的modul模块名称
	 * @author hins
	 * @date 2017年1月10日 下午2:36:46
	 * @return 机器号，如果出现异常，返回1
	 */
	public static int getWorkerId(String modul) {
		try {
			logger.info("config#remoteService#getWorkerId | 开始获取模块的机器号 | modul: {}", modul);
			
			InetAddress addr = InetAddress.getLocalHost();
			String ip = GlobalSetting.PROJENV_PROD.equalsIgnoreCase(GlobalSetting.getProjenv()) ? addr.getHostAddress().toString() : "1";

			logger.info("config#remoteService#getWorkerId | 本机IP | modul: {}, ip: {}", modul, ip);

			String macId = getConfig(ConfigGroupConstants.SERVER_CONFIG, "node_" + modul + "_" + ip);
			int workerId = Integer.valueOf(macId);
			return workerId;	
		} catch (Exception e) {
			logger.error("config#remoteService#getWorkerId | 获取模块的机器号异常  | ", e);
			return 1;
		}
	}
}
