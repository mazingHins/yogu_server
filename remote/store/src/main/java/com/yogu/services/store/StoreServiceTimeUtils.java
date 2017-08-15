package com.yogu.services.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.CollectionUtils;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.enums.merchant.StoreBizType;
import com.yogu.core.enums.merchant.StoreStatus;
import com.yogu.core.web.StoreErrorCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.services.store.base.dto.Store;

/**
 * 门店营业时间相关工具类
 * 
 * @author Hins
 * @date 2015年11月12日 下午5:49:02
 */
public class StoreServiceTimeUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreServiceTimeUtils.class);
	
	/**
	 * 配送间隔时间
	 */
	public static final int DELIERY_INTERVAL = 30;

	public static final int DELIERY_INTERVAL_MS = DELIERY_INTERVAL * 60 * 1000; // 配送间隔时间（毫秒数）
	
	/**
	 * 解析门店营业时间规则，得到门店一周的营业时间数据。 数据格式：key-周下标，value-[开始营业时间段（分钟）,间隔时间段（毫秒）...结束营业时间段（毫秒）]
	 * 
	 * @author Hins
	 * @date 2015年11月12日 上午11:02:46
	 * 
	 * @param serviceHours - 门店一周营业时间json字符串
	 * @return 门店营业时间解析数据集合，若serviceHours为空，则返回null
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
	 * @author Hins
	 * @date 2015年11月3日 上午11:23:44
	 * 
	 * @param json - 营业时间规则json对象
	 * @param dayOfWeek - 日期在周的下标
	 * @return 营业时间间隔列表，若今天不营业，返回empty
	 */
	private static List<Integer> splitServiceHours(JsonObject json, int dayOfWeek) {
		JsonArray array = json.getJsonArray(String.valueOf(dayOfWeek));
		// 不支持当前天数配送，则遍历下一天
		if (array == null) {
			return new ArrayList<Integer>(0);
		}

		int start = array.getInt(0); // 当前日期开始配送时间（分钟数）
		int end = array.getInt(1); // 当前日期结束配送时间（分钟数）
		if (start >= end) {	// 跨天
			end += 1440;
		}
		int size = (end - start) / StoreServiceTimeUtils.DELIERY_INTERVAL;	// 开始时间至结束时间，跨过的时间分隔段
		List<Integer> result = new ArrayList<Integer>(size + 1);
		result.add(start);

		for (int j = 0; j < size; j++) {
			result.add(StoreServiceTimeUtils.DELIERY_INTERVAL);
		}
		return result;
	}

	/**
	 * 生成某天的配送时间列表。 列表的值是格林威治时间long类型
	 * 
	 * @author Hins
	 * @date 2015年11月12日 上午11:49:19
	 * 
	 * @param hours - 门店某天的营业时间解析结果
	 * @param serviceTime - 最早可配送时间(毫秒数)
	 * @param days - 基准时间（配送时间要大于此值）
	 * @param addMinute - 区域配送时间+美食提前预定时间
	 * @param hitSeekDay - true：计算服务日期；false-其他（如计算可选配送时间）
	 * @return 配送时间列表
	 */
	public static List<Long> addBookTime(List<Integer> hours, long serviceTime, Date days, int addMinute, boolean hitSeekDay) {
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
			
			// 早于“最早可配送时间”不考虑。2015-11-21 modify 如果是计算服务日期的，判断条件包含等于(因为24:00是上一天的服务日期)；否则，只判断大于
			if (hitSeekDay && curent >= serviceTime) {
				result.add(curent);
			} else if (!hitSeekDay && curent > serviceTime) {
				result.add(curent);
			}
		}
		
		if(result.isEmpty()){
			return result;
		}
		
		if (!hitSeekDay) { // 最后的时间不是30分钟的倍数，去掉, 2016-03-21 add
			int lastHours = hours.get(hours.size() - 1) + addMinute;
			if (lastHours % DELIERY_INTERVAL > 0) {
				result.remove(result.size() - 1);
			}
		}
		// 2016-03-21 modify by hins end
		
		return result;
	}
	
	
	/**
	 * 生成某天的营业时间列表。 列表的值是格林威治时间long类型<br>
	 * 谨慎调用，此返回在会返回当前时间上一条营业时间段
	 * 
	 * @author Hins
	 * @date 2015年11月12日 上午11:49:19
	 * 
	 * @param hours - 门店某天的营业时间解析结果
	 * @param serviceTime - 最早可配送时间(毫秒数)
	 * @param days - 基准时间（配送时间要大于此值）
	 * @return 配送时间列表
	 */
	private static List<Long> addOpenTime(List<Integer> hours, long serviceTime, Date days) {
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
		List<Long> result = new ArrayList<Long>(hours.size());

		for (int i = 0; i < hours.size(); i++) {
			cal.add(Calendar.MINUTE, hours.get(i));
			// 早于“最早可配送时间”不考虑
			long curent = cal.getTimeInMillis();
			
			if (curent > serviceTime) {
				result.add(curent);
			} 
			
			// 如果不是最后一个时间，则将当前时间的上一个营业时间段也返回
			if (i < hours.size() - 1 && (serviceTime - curent) < DELIERY_INTERVAL_MS) {
				result.add(curent);
			}
		}
		return result;
	}

	/**
	 * 获取门店下一个营业日期。 <br>
	 * 1. 常规类型营业： a: 若今天营业，且当前时间不在上一个跨天时间段内，则返回今天；若在上一个跨天时间段内，则返回昨天 <br>
	 * 				b: 若今天不营业，则返回下一个营业日期 <br>
	 * 2. 预定类型营业： a: 若当前时间未到截单时间，则返回明天 <br>
	 * 				b: 若当前时间已到截单时间，则返回后天 若明天/后天不营业，一直遍历，直到出现营业时间/超出可提前预订天数
	 * 
	 * @author Hins
	 * @date 2015年11月12日 下午4:39:42
	 * 
	 * @param store - 门店
	 * @return 若门店信息为空/营业时间不合法/可提前预定天数内没有营业时间，则返回null
	 */
	public static Date nextServiceDay(Store store) {
		if (store == null) {
			logger.error("remote#store#nextServiceDay | 门店不存在");
			return null;
		}
		
//		if(store.getStoreId() == 18701){
//			System.out.println("debug storeId="+store.getStoreId());
//		}
		
		String serviceHours = StoreServiceTimeUtils.getStoreServiceHours(store); // 营业时间规则json字符串
		if (StringUtils.isBlank(serviceHours)) {
			logger.error("remote#store#nextServiceDay | 获取门店营业时间失败");
			return null;
		}
		
		// 1. 解析门店配送时间规则
		Map<Integer, List<Integer>> serviceHourMap = StoreServiceTimeUtils.getHours(serviceHours);
		if (serviceHourMap == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		// 当前时间，用于排除门店营业时间早于当前时间
		long nowTime = cal.getTimeInMillis();

		// 如果门店营业类型是"预定类餐厅"，则判断现在的时间是否超过截单时间，若超过，则只能预订后一个营业日的订单；若为超过，只能预订下一个营业日的订单
		int startDay = setStartServiceDay(cal, store.getBizType(), store.getAcceptOrderDeadline());

		// 遍历7天时间，直到匹配到有可选开始配送时间，并返回那天的日期
		for (; startDay <= 7; startDay++) {
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (serviceHourMap.containsKey(dayOfWeek)) {
				List<Integer> hours = serviceHourMap.get(dayOfWeek);
				List<Long> bookTimes = StoreServiceTimeUtils.addBookTime(hours, nowTime, cal.getTime(), 0, false);
				if (!bookTimes.isEmpty()) {
					return cal.getTime();
				}
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		return null;
	}
	
	/**
	 * 将cal设为门店开始配送日期时间<br>
	 * 若门店营业类型是：
	 * 		1. 常规类型，cal日期-1，返回-1
	 * 		2. 预定类型，判断 cal当前时间是否超出截单时间
	 * 			a). 超出，cal日期+2，返回2
	 * 			b). 未超出，cal日期+1，返回1
	 * 
	 * @author Hins
	 * @date 2015年11月12日 下午5:05:29
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
	 * 计算 门店截止接单时间（分钟数）的 时间显示(HH:mm 格式)
	 * 
	 * <br>
	 * 目前不支持跨天
	 * 
	 * @param timeMinute 时间的分钟数, 比如24:00的分钟数为1440
	 * @return 时间格式为HH:mm的字符串
	 * @author sky
	 */
	public static String getDeadLineTimeForShowFromTimeMinute(short timeMinute) {

		// TODO 目前只支持deadLine 时间为一天的, 不支持跨天,也就是 timeMinute>1440

		String show = "";

		if (timeMinute <= 0 || timeMinute >= 1440) {

			show = "24:00";

		} else {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, timeMinute);
			cal.set(Calendar.SECOND, 0);

			Date day = cal.getTime();

			show = DateUtils.formatDate(day, "HH:mm");
			//System.out.println(show);
		}
		return show;

	}

	/**
	 * 计算当前可下单的一个最优可用营业日期<br>
	 * <b> 最优可用： <br>
	 * 1. (常规餐厅)若当前时间处在门店营业时间内,那么当前的日期为最优可用日期;<br>
	 * 2. (常规餐厅)若当前时间超过了门店的营业时间,那么下一个营业日期为最优可用营业日期<br>
	 * 3. (预定餐厅) 当前可以预定的第一个营业日,比如餐厅提前预定天数为2且明后两天都营业, 那么当前最优可下单日期为 当前时间的后天</b> <br>
	 * 场景: 不管是当前的用户还是当前的商家,在当前的时间点看到的都是餐厅能被下单的最近的营业日期,库存数据也是能被下单的最近的营业日的库存
	 * 
	 * 
	 * <br>
	 * 若找不到这样的一个日期,返回null,调用者自己处理返回空的情况
	 * 
	 * @param store
	 * @return 最优营业日期,没找到则返回null
	 * @author sky
	 * @date 2015-11-12 20:09
	 */
	public static Date caculateCurrCanBeOrderedDay(Store store) {

		Date day = nextServiceDay(store);
		return day;
	}
	
	/**
	 * 获取餐厅营业时间规则json字符串<br>
	 * 若餐厅是纯预定餐厅，返回餐厅的serviceHours<br>
	 * 若餐厅非纯预定餐厅，返回餐厅的openHours
	 * 
	 * @author Hins
	 * @date 2015年12月9日 上午10:41:05
	 * 
	 * @param store - 餐厅对象
	 * @return 营业时间规则json字符串，若对象的值为空，返回null
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
	
//	/**
//	 * 计算餐厅miniblog底部提示语
//	 * 
//	 * @author Hins
//	 * @date 2016年1月11日 下午5:29:43
//	 * 
//	 * @param store - 餐厅对象
//	 * @return 提示语，可能返回null
//	 */
//	public static String getServiceTimeButton(Store store) {
//		if (store == null) {
//			logger.info("store#remote#getServiceTimeButton | 门店对象为空");
//			return null;
//		}
//
//		if (store.getStatus() != StoreStatus.IN_BUSSINESS.getValue()) {
//			logger.info("store#remote#getServiceTimeButton | 门店状态不是正常营业 | storeId: {}, status: {}", store.getStoreId(), store.getStatus());
//			return null;
//		}
//		
//		if (store.getBizType() == StoreBizType.ADVBOOK.getValue()) {
//			return DateUtils.minuteToHhMm(store.getAcceptOrderDeadline()) + "前下单明天配送/之后下单后天配送";
//		} else if (store.getBizType() == StoreBizType.NORMAL.getValue()) {
//			Date serviceTime = null;
//			try {
//				serviceTime = getAdvanceServiceTime(store); // 最近的营业时间
//			} catch (ServiceException e) {
//				// 餐厅没有可选的配送时间
//				serviceTime = getSevenServiceTime(store);
//				String day = formatButtonDay(serviceTime);
//				if (StringUtils.isNotBlank(day)) {
//					return "餐厅休息中，欢迎" + day + "再来选购";
//				}
//				return null;
//			}
//
//			String day = formatButtonDay(serviceTime);
//			if (StringUtils.isNotBlank(day)) {
//				return "现在下单，" + day + DateUtils.formatDate(serviceTime, DateUtils.HH_MM) + "开始配送";
//			}
//			return formatButtonDay(serviceTime);
//		}
//		return null;
//	}
//	
//	/**
//	 * 格式化指定时间到现在的天数差<br>
//	 * 差0天，返回今天<br>
//	 * 差1天，返回明天<br>
//	 * 差2天，返回后天<br>
//	 * 大于2天，返回MM月dd日<br>
//	 * 
//	 * @author Hins
//	 * @date 2016年1月12日 下午12:19:35
//	 * 
//	 * @param serviceTime - 指定时间
//	 * @return 天数差，可能为空
//	 */
//	private static String formatButtonDay(Date serviceTime){
//		if (serviceTime == null) {
//			// 可能因为餐厅的数据不全（没有openHours等）
//			return null;
//		}
//		Date now = new Date();
//		// 判断是否在营业时间，用当前时间跟最近的营业时间比较
//		if (now.before(serviceTime)) {
//			// 最近的营业时间距离现在的天数
//			int countDay = DateUtils.countDay(DateUtils.getStartTime(now), serviceTime);
//			String time = null;
//			switch (countDay) {
//			case 0:
//				time = "今天";
//				break;
//			case 1:
//				time = "明天";
//				break;
//			case 2:
//				time = "后天";
//				break;
//			default:
//				time = DateUtils.formatDate(serviceTime, DateUtils.MM_DD);
//				break;
//			}
//			return time;
//		}
//		return null;
//	}
	
	/**
	 * 获取可预订天数内最早的营业时间<br>
	 * 1. 常规类型餐厅：
	 * 		A：餐厅在可预订天数范围内，没有可选配送时间，则返回code=2910的ServiceException
	 * 		B：餐厅在可预订天数范围内，有可选配送时间。若当前时间营业，返回上一条可选配送时间；若当前时间不营业，返回下一个可选配送时间
	 * 2. 纯预定餐厅：返回下一个可选配送（非送达！）时间
	 * 可能的异常：没有可选配送时间，返回code=2910的ServiceException
	 * 
	 * @author Hins
	 * @date 2016年1月9日 下午3:45:16
	 * 
	 * @param store - 餐厅对象
	 * @return
	 */
	public static Date getAdvanceServiceTime(Store store) {
		int advanceDay = store.getAdvanceReserveDays(); // 门店可提前预定天数
		if (store.getBizType() == StoreBizType.ADVBOOK.getValue()) {
			// 如果是纯预定餐厅，可提前预定天数默认=10，addMinute不考虑区域配送时间
			advanceDay = 10;
		}
		
		Date serviceTime = getNextServiceTime(advanceDay, store);
		if(serviceTime == null){
			// 在餐厅可提前预定时间内，都没可选营业时间
			throw new ServiceException(StoreErrorCode.STORE_NOT_OPTIONAL_SERVICE_TIME, "餐厅没有可选的配送时间");
		}
		return serviceTime;
	}
	
	/**
	 * 获取在指定提前预定天数内，最近的营业时间
	 * 
	 * @author Hins
	 * @date 2016年1月26日 上午6:13:52
	 * 
	 * @param advanceDay
	 * @param serviceHours
	 * @return
	 */
	private static Date getNextServiceTime(int advanceDay, Store store) {
		// 1. 解析门店配送时间规则
		String serviceHours = StoreServiceTimeUtils.getStoreServiceHours(store); // 营业时间规则json字符串
		Map<Integer, List<Integer>> serviceHourMap = StoreServiceTimeUtils.getHours(serviceHours);
		if (serviceHourMap == null) {
			return null;
		}

		// 当天可以开始配送时间（所有的配送时间都要在此之后）
		Calendar cal = Calendar.getInstance();
		long baseTime = cal.getTimeInMillis();

		// 如果门店营业类型是"预定类餐厅"，则判断现在的时间是否超过截单时间，若超过，则只能预订后一个营业日的订单；若为超过，只能预订下一个营业日的订单
		int startDay = StoreServiceTimeUtils.setStartServiceDay(cal, store.getBizType(), store.getAcceptOrderDeadline());

		// 遍历指定天数的时间，直到匹配的营业时间大于当前时间，则跳出循环
		for (; startDay <= advanceDay; startDay++) {
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (serviceHourMap.containsKey(dayOfWeek)) {
				List<Integer> hours = serviceHourMap.get(dayOfWeek);
				List<Long> result = StoreServiceTimeUtils.addOpenTime(hours, baseTime, cal.getTime());
				if (!result.isEmpty()) {
					long serviceTime = result.get(0);
					return new Date(serviceTime);
				}
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		return null;
	}
	
	/**
	 * 获取7天内最早的营业时间<br>
	 * 1. 常规类型餐厅：
	 * 		A：餐厅在可预订天数范围内，没有可选配送时间，则返回code=2910的ServiceException
	 * 		B：餐厅在可预订天数范围内，有可选配送时间。若当前时间营业，返回上一条可选配送时间；若当前时间不营业，返回下一个可选配送时间
	 * 2. 纯预定餐厅：返回下一个可选配送（非送达！）时间
	 * 
	 * @author Hins
	 * @date 2016年1月12日 下午12:14:42
	 * 
	 * @param store
	 * @return
	 */
	public static Date getSevenServiceTime(Store store) {
		Date serviceTime = getNextServiceTime(7, store);
		return serviceTime;
	}
	
//	public static void main(String[] args) {
//		Store store = new Store();
//		store.setBizType(StoreBizType.ADVBOOK.getValue());
//		System.out.println(StoreBizType.getBizType(StoreBizType.ADVBOOK.getValue()));
//	}
	
	/**
	 * 判断餐厅在当前时间是否营业<br>
	 * 如果餐厅营业时间json数据不合法，返回false<br>
	 * 判断方法：当前时间的分钟在一天的时间范围内，则返回true，否则返回false
	 * 
	 * @author Hins
	 * @date 2016年1月30日 上午10:11:20
	 * 
	 * @param store
	 * @return
	 */
	public static boolean validateInBusiness(Store store) {
		// 1. 解析门店配送时间规则
		String serviceHours = StoreServiceTimeUtils.getStoreServiceHours(store); // 营业时间规则json字符串
		Map<Integer, List<Integer>> serviceHourMap = StoreServiceTimeUtils.getHours(serviceHours);
		if (serviceHourMap == null) {
			return false;
		}

		Calendar cal = Calendar.getInstance();
		// 当前时间分钟数，用于判断当前时间是否在一天的营业范围内
		int nowMinute = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);

		int begin = 0; // 门店开始营业的分钟数
		int end = 0; // 门店结束营业的分钟数
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (serviceHourMap.containsKey(dayOfWeek)) {
			List<Integer> hours = serviceHourMap.get(dayOfWeek);
			begin = hours.get(0);
			end = 0;
			for (int i = 0; i < hours.size(); i++) {
				end += hours.get(i);
			}
		}

		// 如果跨天，判断昨天
		if (end > 1440 && begin > nowMinute) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (!serviceHourMap.containsKey(dayOfWeek)) {
				return false;
			}
			nowMinute += 1440;
		}

		if (begin < nowMinute && nowMinute < end) {
			return true;
		}

		return false;
	}
	
	/**
	 * 获取最近的下一个营业时间到现在的时间差 (分钟)
	 * 若计算出错, 返回-1
	 * 
	 * @param store 餐厅
	 * @return
	 * 
	 * @author felix 2016-05-25
	 */
	public static int getNearestServiceMin(Store store) {
		String serviceHours = StoreServiceTimeUtils.getStoreServiceHours(store); // 营业时间规则json字符串
		Map<Integer, List<Integer>> serviceHourMap = StoreServiceTimeUtils.getHours(serviceHours);
		if (serviceHourMap == null) {
			return -1;
		}
		
		Calendar cal = Calendar.getInstance();
		// 当前时间分钟数，用于判断当前时间是否在一天的营业范围内
		int nowMinute = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int day = dayOfWeek;
		
		for (int i = 0; i < 7; i++) {
			if (serviceHourMap.containsKey(day)) {
				List<Integer> hours = serviceHourMap.get(day);
				int begin = hours.get(0);
				if (day == dayOfWeek && begin > nowMinute) {
					return begin - nowMinute;
				} else if (day != dayOfWeek) {
					return begin + 1440 - nowMinute;
				}
			}
			
			if (day < 7)
				day ++;
			else {
				day = 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * 把规格的备注列表变成 String，英文逗号隔开。
	 * 如果 List 为null或空，返回 ""
	 * @param list 备注列表
	 * @return 返回不为null的String
	 * @author ten 2016/2/23
	 */
	public static String supplementsToString(List<String> list) {
		if (CollectionUtils.isEmpty(list)) {
			return "";
		}
		Collections.sort(list); // 按字母排序，保证任何情况下，顺序都一样
		String supplements = org.apache.commons.lang3.StringUtils.join(list, ',');
		return supplements;
	}
	
}
