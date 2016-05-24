package com.mazing.utils.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mazing.utils.common.JsonUtils;
import com.mazing.utils.dto.Store;

public class StoreServiceTimeUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreServiceTimeUtils.class);
	
	/**
	 * 配送间隔时间，单位分钟
	 */
	public static final int DELIERY_INTERVAL = 30;

	
	/**
	 * 配送间隔时间，单位毫秒数
	 */
	public static final int DELIERY_INTERVAL_MS = DELIERY_INTERVAL * 60 * 1000;

	/**
	 * 获取餐厅营业时间规则json字符串<br>
	 * 若餐厅是纯预定餐厅，返回餐厅的serviceHours<br>
	 * 若餐厅非纯预定餐厅，返回餐厅的openHours
	 * 
	 * @author hins
	 * @date 2016年5月23日下午2:36:25
	 * 
	 * @param store
	 * @return
	 */
	public static String getStoreServiceHours(Store store){
		String serviceHours = null; // 营业时间规则json字符串
		if (store.getBizType() == StoreBizType.ADVBOOK.getValue()) {
			// 如果是纯预定餐厅，serviceHours取服务时间
			serviceHours = store.getServiceHours();
		} else {
			// 如果非纯预定餐厅，serviceHours去营业时间
			serviceHours = store.getOpenHours();
		}
		return serviceHours;
	}
	
	/**
	 * 解析门店营业时间规则，得到门店一周的营业时间数据。 <br>
	 * 数据格式：key-周下标，value-[开始营业时间段（分钟）,间隔时间段（毫秒）...结束营业时间段（毫秒）]<br>
	 * 此方法可以将结果存入缓存，以后再优化吧
	 * 
	 * @author hins
	 * @date 2016年5月23日下午2:38:31
	 * 
	 * @param serviceHours
	 * @return
	 */
	public static Map<Integer, List<Integer>> getHours(String serviceHours) {
		if (StringUtils.isBlank(serviceHours)) {
			logger.info("store#remote#getHours | 门店没有设置营业时间 ");
			return null;
		}
		
		// 若不是合法json字符串，返回null
		JsonObject json = null;
		try {
			json = JsonUtils.toJsonObject(serviceHours);
		} catch (Exception e) {
			logger.error("store#remote#getHours | 门店营业时间不是合法字符串 | serviceHours: {}", serviceHours);
		}

		if (json == null) {
			return null;
		}

		Map<Integer, List<Integer>> result = new HashMap<Integer, List<Integer>>(7);
		for (int day = 1; day < 8; day++) {
			List<Integer> hours = splitServiceHours(json, day);
			if (!hours.isEmpty()) {
				result.put(day, hours);
			}
		}
		return result;
	}
	
	/**
	 * 解析当天营业时间规则，得到结果[开始营业时间段（分钟）,间隔时间段（分钟）...结束营业时间段（分钟）]
	 * 
	 * @author hins
	 * @date 2016年5月23日下午2:46:40
	 * 
	 * @param json - 营业时间规则json对象
	 * @param dayOfWeek - 日期在周的下标
	 * @return 营业时间间隔列表，若今天不营业，返回empty list	
	 */
	private static List<Integer> splitServiceHours(JsonObject json, int dayOfWeek) {
		JsonArray array = json.getJsonArray(String.valueOf(dayOfWeek));
		// 不支持当前天数配送，则遍历下一天
		if (array == null) {
			return new ArrayList<Integer>(0);
		}

		int start = array.getInt(0); // 当前日期开始配送时间（分钟数）
		int end = array.getInt(1); // 当前日期结束配送时间（分钟数）
		int size = 0; // 营业开始时间到结束时间，一共经过DELIERY_INTERVAL的时间段（考虑跨天）
		if (start >= end) {// 跨天
			end += 1440;
		}
		size = (end - start) / StoreServiceTimeUtils.DELIERY_INTERVAL;
		List<Integer> result = new ArrayList<Integer>(size);
		result.add(start);

		for (int j = 0; j < size; j++) {
			result.add(StoreServiceTimeUtils.DELIERY_INTERVAL);
		}
		return result;
	}

	/**
	 * 将cal设为门店开始配送日期时间<br>
	 * 若门店营业类型是：
	 * 		1. 常规类型，cal日期-1，返回-1
	 * 		2. 预定类型，判断 cal当前时间是否超出截单时间
	 * 			a). 超出，cal日期+2，返回2
	 * 			b). 未超出，cal日期+1，返回1
	 * 
	 * @author hins
	 * @date 2016年5月23日下午3:06:12
	 * 
	 * @param cal - 当前时间
	 * @param bizType - 门店营业类型
	 * @param acceptOrderDeadline - 截单时间（分钟数）
	 * @return 传入的cal日期到门店开始配送日期时间跨越的天数
	 */
	public static int setStartServiceDay(Calendar cal, short bizType, short acceptOrderDeadline) {
		if (StoreBizType.ADVBOOK.getValue() == bizType) {
			int minute = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);// 当前时间分钟数
			if (minute > acceptOrderDeadline) {
				cal.add(Calendar.DAY_OF_MONTH, 2);
				return 2;
			} else {
				cal.add(Calendar.DAY_OF_MONTH, 1);
				return 1;
			}
		} else {
			// 将昨天的时间一并加入计算，因为首日存在跨天营业的问题
			cal.add(Calendar.DAY_OF_MONTH, -1);
			return -1;
		}
	}
	
	/**
	 * 生成某天的可选送达时间列表。 列表的值是格林威治时间long类型
	 * 
	 * @author hins
	 * @date 2016年5月23日下午3:09:11
	 * 
	 * @param hours - 餐厅某天的营业时间解析结果
	 * @param serviceTime - 最早可选送达时间（毫秒数）
	 * @param days - 基准时间（可选的送达时间要大于此值）
	 * @param addMinute - 区域配送时间
	 * @return - 可选送达时间列表，若无，返回empty list
	 */
	public static List<Long> addBookTime(List<Integer> hours, long serviceTime, Date days, int addMinute) {
		if (hours.isEmpty()) {
			return Collections.emptyList();
		}
		// 将时间设为当天00:00:00
		Calendar cal = Calendar.getInstance();
		cal.setTime(days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MINUTE, addMinute);
		List<Long> result = new ArrayList<Long>(hours.size());
		
		// 2016-03-21 modify by hins 内容：配送时间只能出现00或30的值，只需确认首个hours是30的倍数
		Integer offset = (hours.get(0) + addMinute) % DELIERY_INTERVAL;
		if (offset > 0) { // 开始时间不是30分钟的倍数
			cal.add(Calendar.MINUTE, DELIERY_INTERVAL - offset);
		}

		for (int i = 0; i < hours.size(); i++) {
			cal.add(Calendar.MINUTE, hours.get(i));
			long curent = cal.getTimeInMillis();
			
			// 早于“最早可配送时间”不考虑。
			if (curent > serviceTime) {
				result.add(curent);
			}
		}
		
		return result;
	}
	
}
