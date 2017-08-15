/**
 * 
 */
package com.yogu.core.remote.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogu.commons.cache.CacheExtendService;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.ServiceLoaderUtils;
import com.yogu.core.broadcast.BroadcastKey;
import com.yogu.core.remote.config.provider.ConfigProvider;
import com.yogu.mq.ServerMsgNotice;
import com.yogu.mq.ServerMsgService;

/**
 * 地理信息(省市区，非坐标) 远程服务类 <br>
 * 
 * <br>
 * JFan - 2015年6月22日 上午10:33:13
 */
@Service
public class AreaRemoteService implements ServerMsgNotice {

	private static final Logger logger = LoggerFactory.getLogger(AreaRemoteService.class);

	// key: cityCode
	private Map<String, Area> cacheMap = new HashMap<String, Area>();

	@Autowired
	private ServerMsgService serverMsgService;

	private static ConfigProvider configProvider;

	@PostConstruct
	public void initial() {
		serverMsgService.regNotice(BroadcastKey.AREA_CHANGE, this);

		// 装载configProvider对象
		logger.debug("Loading 'ConfigProvider' ......");
		configProvider = ServiceLoaderUtils.orderlyOne(ConfigProvider.class);
		Args.check(null != configProvider, "没有找到configProvider的实现");
		logger.info("Loading 'ConfigProvider' is {}", configProvider.getClass());
	}

	/**
	 * 获取所有已开通的城市列表
	 */
	public List<Area> listAllCity() {
		return configProvider.listAllCity();
	}

	/**
	 * 查询地理对象<br>
	 * code严格区分大小写
	 */
	public Area getAreaByCode(String code) {
		return getArea(code, true);
	}

	/**
	 * 查询指定的地理信息（不做缓存，实时查询）<br>
	 * code严格区分大小写
	 */
	public Area getAreaWithoutCache(String code) {
		return getArea(code, false);
	}

	// ####
	// ## private
	// ####

	/**
	 * 查询指定的地理信息<br>
	 * cache=false:直接远程加载，并set到缓存<br>
	 * cache=true:优先读取缓存，没有才远程加载，并set到缓存
	 */
	protected Area getArea(String code, boolean cache) {
		Area area = null;
		if (cache)// 先读缓存（要求的）
			area = cacheMap.get(code);

		if (null != area)
			return area;

		try {
			// load数据
			area = configProvider.getArea(code);
			if (null != area)
				cacheMap.put(area.getCode(), area);
		} catch (Exception e) {
			logger.error("area#remote#getArea | error | code: '{}', message: '{}'", code, e.getMessage(), e);
		}

		return area;
	}

	// ####
	// ## notice
	// ####

	/*
	 * （非 Javadoc）
	 * 
	 * @see com.mazing.mq.ServerMsgNotice#notice(java.lang.String, java.lang.String)
	 */
	@Override
	public void notice(String messageId, String message) {
		cacheMap.remove(message);
		
		CacheExtendService cacheExtendService = getCacheExtendService();
		cacheExtendService.delete("Area3LevelS:3");//fuck，手动删除了，还没有地区城市的相应管理 
	}

	private CacheExtendService getCacheExtendService() {
		return MainframeBeanFactory.getBean(CacheExtendService.class);
	}
	
}
