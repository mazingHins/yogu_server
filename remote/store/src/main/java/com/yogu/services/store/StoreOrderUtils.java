package com.yogu.services.store;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.enums.merchant.StoreBizType;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.context.SecurityContext;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.language.StoreMessages;
import com.yogu.services.store.base.dto.Store;
import com.yogu.services.store.order.StoreSettleOrderVO.BookTimeVO;
import com.yogu.services.store.order.StoreSettleOrderVO.SendTimeVO;

public class StoreOrderUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(StoreOrderUtils.class);
	
	/**
	 * 日期对应的周下标显示的文本
	 */
	private static final String[] WEEK_DAYS = { "(周日)", "(周一)", "(周二)", "(周三)", "(周四)", "(周五)", "(周六)" };
	/**
	 * 英文星期文本
	 */
	private static final String[] WEEK_EN_DAYS = { "(Sun)", "(Mon)", "(Tue)", "(Wed)", "(Thur)", "(Fri)", "(Sat)" };
	
	/**
	 * 尽快送达时间
	 */
	public static final long SOON_DELIERY = 0L;
	
	public static final String HOURS_FORMAT = "；{0}[{1},{2}]";	// 餐厅营业时间格式化模板
	
	/**
	 * 将餐厅的营业时间/配送时间的json字符串转成按“星期几”+“时：分”格式的内容<br>
	 * 并按周的从小到大排序<br>
	 * 如果出现异常，返回原数据hours
	 * 
	 * @param time
	 * @author hins
	 * @date 2016年11月16日 上午11:45:25
	 * @return String
	 */
	public static String formatTime(String hours) {
		if (StringUtils.isBlank(hours)) {
			return "";
		}

		// 1. 解析门店配送时间规则. key-周下标，value-[开始营业分钟数，结束营业分钟数]数组
		Map<String, int[]> hoursMap = JsonUtils.parseMap(hours, String.class, int[].class);
		if (hoursMap == null) {
			return "";
		}
		
		StringBuffer sf = new StringBuffer();
		for(String key : hoursMap.keySet()){
			int [] value = hoursMap.get(key);
			switch (key) {
			case "1":
				key = "周日";
				break;
			case "2":
				key = "周一";
				break;
			case "3":
				key = "周二";
				break;
			case "4":
				key = "周三";
				break;
			case "5":
				key = "周四";
				break;
			case "6":
				key = "周五";
				break;
			case "7":
				key = "周六";
				break;
			default:
				break;
			}
			sf.append(MessageFormat.format(HOURS_FORMAT, key, DateUtils.minuteToHhMm(value[0]), DateUtils.minuteToHhMm(value[1])));
//			sf.append("；").append(key).append("[").append(DateUtils.minuteToHhMm(value[0])).append(",").append(DateUtils.minuteToHhMm(value[1])).append("]");
		}
		return sf.toString().substring(1, sf.length());
	}
	
//	public static void main(String[] args) {
//		System.out.println(formatTime("{\"2\":[570,1080],\"1\":[570,1080],\"6\":[570,1080],\"4\":[570,1080],\"3\":[570,1080],\"7\":[570,1080],\"5\":[570,1080]}"));
//	}
	
	/**
	 * 根据门店营业类型，提前下单天数，可提前预定天数，营业时间规则，区域配送时间等计算出可选预定时间列表
	 * 
	 * @author Hins
	 * @date 2015年11月12日 上午10:21:50
	 * 
	 * @param store - 门店对象
	 * @param rangeMinute - 区域配送时间 （分钟）
	 * @param advanceMinute - 门店提前下单时间（分钟）
	 * @return 可选预定时间列表，若无，返回empty
	 */
	public static List<Long> eachTime(Store store, int rangeMinute, int advanceMinute) {
		int advanceDay = store.getAdvanceReserveDays(); // 门店可提前预定天数
		String serviceHours = StoreServiceTimeUtils.getStoreServiceHours(store); // 营业时间规则json字符串
		if (store.getBizType() == StoreBizType.ADVBOOK.getValue()) {
			// 如果是纯预定餐厅，可提前预定天数默认=10，addMinute不考虑区域配送时间
			advanceDay = 10;
		}

		int addMinute = rangeMinute + advanceMinute;
		logger.info("order#service#eachSendTime | 开始装载配送时间 | advanceReserveDays: {}, serviceHours: {}, addMinute: {}", advanceDay,
				serviceHours, addMinute);
		if (StringUtils.isBlank(serviceHours)) {
			logger.info("order#service#eachSendTime | 门店没有设置营业时间 | storeId: {}", store.getStoreId());
			return Collections.emptyList();
		}
		// 1. 解析门店配送时间规则
		Map<Integer, List<Integer>> serviceHourMap = StoreServiceTimeUtils.getHours(serviceHours);
		if (serviceHourMap == null) {
			return Collections.emptyList();
		}

		Calendar cal = Calendar.getInstance();
		int addMinuteMs = addMinute * 60 * 1000;// 配送时间毫秒数
		// 当天可以开始配送时间（所有的配送时间都要在此之后）
		long serviceTime = cal.getTimeInMillis() + addMinuteMs;
		long baseTime = serviceTime; // 基准时间，用于判断是否有尽快送达

		// 2. 装载每一天的配送时间
		// 如果门店营业类型是"预定类餐厅"，则判断现在的时间是否超过截单时间，若超过，则只能预订后一个营业日的订单；若为超过，只能预订下一个营业日的订单
		int startDay = StoreServiceTimeUtils.setStartServiceDay(cal, store.getBizType(), store.getAcceptOrderDeadline());

		List<Long> result = new ArrayList<Long>();
		for (; startDay <= advanceDay; startDay++) {
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (serviceHourMap.containsKey(dayOfWeek)) {
				List<Integer> hours = serviceHourMap.get(dayOfWeek);
				result.addAll(StoreServiceTimeUtils.addBookTime(hours, serviceTime, cal.getTime(), addMinute, false));
				if (!result.isEmpty()) {
					// 重新修正"当天可以开始配送时间"的基准时间，防止24小时营业时候，今天的最后一个时间跟第二天的第一个时间相同
					serviceTime = result.get(result.size() - 1);
				}
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		// 3. 判断是否满足“尽快送达”
		if (store.getBizType() == StoreBizType.NORMAL.getValue()) {
			validateInSoon(serviceHourMap, baseTime + StoreServiceTimeUtils.DELIERY_INTERVAL_MS, result);
		}
		return result;
	}
	
	/**
	 * 判断是否满足"尽快送达"。<br>
	 * 条件：若"可选配送时间列表"首条的值小于"最早可配送时间"，则满足，将尽快送达装载到"可选配送时间列表"
	 * 
	 * @author Hins
	 * @date 2015年11月12日 上午11:56:43
	 * 
	 * @param serviceHourMap - 餐厅营业时间规则[当天开始营业时间分钟数，时间间隔（30分钟），时间间隔（30分钟）...直到结束营业时间]
	 * @param serviceTime - 最早可配送时间
	 * @param bookTime - 可选配送时间列表
	 */
	private static void validateInSoon(Map<Integer, List<Integer>> serviceHourMap, long serviceTime, List<Long> bookTime) {
		if (bookTime.isEmpty()) {
			return;
		}
		Calendar cal = Calendar.getInstance();
		// 当前时间分钟数，用于判断当前时间是否在一天的营业范围内
		int nowMinute = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);

		int begin = 0; // 门店开始营业的分钟数
		int end = 0; // 门店结束营业的分钟数

		for (int startDay = 1; startDay <= 7; startDay++) {
			if (serviceHourMap.containsKey(startDay)) {
				List<Integer> hours = serviceHourMap.get(startDay);
				begin = hours.get(0);
				end = 0;
				for (int i = 0; i < hours.size(); i++) {
					end += hours.get(i);
				}
				break;
			}
		}
		// 防止跨天
		if (end > 1440 && begin > nowMinute) {
			nowMinute += 1440;
		}

		// 只有时间在营业范围一天的营业范围内，才可能存在尽快送达
		if (begin < nowMinute && nowMinute < end) {
			if (serviceTime > bookTime.get(0)) {
				bookTime.add(0, SOON_DELIERY);
			}
		}
	}
	
	/**
	 * 将一天的配送时间转成BookTimeVO列表
	 * 
	 * @author Hins
	 * @date 2015年11月17日 下午4:00:35
	 * 
	 * @param timeMap - 每天的配送时间，key-一天的结束时间，value-当天的可选配送时间（SendTimeVO对象）
	 * @return 一天的配送时间 BookTimeVO列表，若不存在，返回empty
	 */
	public static List<BookTimeVO> toBookTimeJsonArray(LinkedHashMap<Long, List<SendTimeVO>> timeMap) {
		// 当前天数的0点0分，所有计算“2个时间段内经过的天数”都要基于当前时间，用于计算可选配送时间距离现在的天数
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date now = cal.getTime();

		List<BookTimeVO> result = new ArrayList<BookTimeVO>();
		for (long key : timeMap.keySet()) {
			BookTimeVO vo = new BookTimeVO();
			if (key == SOON_DELIERY) {
				vo.setDay(OrderMessages.ORDER_RANGEUTILS_SOONDELIVERY_TEXT());
				vo.setTime(timeMap.get(key));
				result.add(vo);
				continue;
			}

			cal.setTimeInMillis(key);
			// 可选配送时间距离现在的天数
			int countDay = DateUtils.countDay(now, cal.getTime());
			String day = null;
			switch (countDay) {
			case 0:
				day = StoreMessages.STORE_TIME_TODAY();
				break;
			case 1:
				day = StoreMessages.STORE_TIME_TOMORROW();
				break;
			case 2:
				// day = "后日";
				cal.set(Calendar.HOUR_OF_DAY, +2);
				day = DateUtils.formatDate(cal.getTime(), DateUtils.MM_DD);
				break;
			default:
            	day = DateUtils.formatDate(cal.getTime(), DateUtils.MM_DD);
				break;
			}
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

			// 语言环境判断
			if (SecurityContext.change2English())
				day = day + WEEK_EN_DAYS[dayOfWeek - 1];
			else
				day = day + WEEK_DAYS[dayOfWeek - 1];

			vo.setDay(day);
			vo.setTime(timeMap.get(key));
			result.add(vo);
		}
		return result;
	}
	
	/**
	 * 根据可选配送时间列表，将可选配送时间按天归类。 key-天（那天最后日期23:59:59 999）,value-那天可选配送时间SendTimeVO列表
	 * 
	 * @author Hins
	 * @date 2015年11月16日 下午5:00:32
	 * 
	 * @param times - 可选配送时间
	 * @param rangeMinute - 区域配送时间
	 * @param advanceMinute - 最大提前下单时间
	 * @return
	 */
	public static LinkedHashMap<Long, List<SendTimeVO>> loadBookTimeMap(List<Long> times, int rangeMinute, int advanceMinute) {
		// 同一天的配送时间只有一个key，value-那天的可选配送时间
		LinkedHashMap<Long, List<SendTimeVO>> timeMap = new LinkedHashMap<Long, List<SendTimeVO>>();
		// 判断首条是否尽快送达
		if (times.get(0) == SOON_DELIERY) {
			timeMap.put(SOON_DELIERY, getSoonSendTime(times.get(0), rangeMinute, advanceMinute));
			times.remove(0);
		}

		// 当遍历的时间是00:00时候，可能时间要转换成昨天的24:00，tmpTime用于判断当前遍历的时间是否要转成昨天
		long tmpTime = 0;

		// 每一天的结束日期(23:59:59)，用于指定timeMap的key
		long baseTime = times.get(0);
		baseTime = DateUtils.oneDayEndTime(baseTime);

		// 思路：将当天没超过23:59:59 999的可选配送时间，归纳到同一个LinkedHashMap，key-当天的结束时间23:59:59 999
		// 若可选配送时间是00:00，且跟上一个时间的天数只相隔一天，则归纳到上一天的LinkedHashMap
		for (Long t : times) {
			if (baseTime < t) {
				baseTime = DateUtils.oneDayEndTime(t);
			}
			SendTimeVO send = formatBookTime(t);
			// 当前时间是00:00，且跟上一个时间的天数只相隔一天，则timeMap要取上一天
			if (send.getName().equals("00:00") && (DateUtils.countDay(new Date(tmpTime), new Date(baseTime)) == 1)) {
				baseTime = tmpTime;
				send.setName("24:00");
			} else if (tmpTime < baseTime) {
				tmpTime = baseTime;
			}
			send.setTitle(OrderMessages.ORDER_RANGEUTILS_SEND_TITLE(send.getName()));

			// 装载当天的可选配送时间，格式: {"name": "11:00","timestamp": 1439469000160 }
			List<SendTimeVO> sendList = null;
			if (timeMap.containsKey(baseTime)) {
				sendList = timeMap.get(baseTime);
				sendList.add(send);
			} else {
				sendList = new ArrayList<SendTimeVO>();
				sendList.add(send);
				timeMap.put(baseTime, sendList);
			}
		}
		return timeMap;
	}
	
	/**
	 * 装载时间信息到配送时间列表。<br>
	 * 
	 * @author Hins
	 * @date 2015年8月24日 下午4:47:41
	 * 
	 * @param time - 时间
	 * @return 可选配送时间对象
	 */
	private static SendTimeVO formatBookTime(long time) {
		SendTimeVO vo = new SendTimeVO();
		String name = DateUtils.formatDate(new Date(time), DateUtils.HH_MM);
		// // 若出现了"00:00"，则替换时间文本
		// if (!isFrist && name.contains("00:00")) {
		// name = name.replaceAll("00:00", "24:00");
		// }
		vo.setName(name);
		vo.setTimestamp(time);
		return vo;
	}
	
	/**
	 * 获得尽快送达时间的sendTime列表
	 * 
	 * @author Hins
	 * @date 2015年11月13日 下午6:32:31
	 * 
	 * @param time - 尽快送达时间撮
	 * @return - sendTime列表
	 */
	private static List<SendTimeVO> getSoonSendTime(long time, int rangeMinute, int advanceMinute) {
		List<SendTimeVO> sendList = new ArrayList<SendTimeVO>(1);
		SendTimeVO vo = new SendTimeVO();
		vo.setName("");
		Date deliveryTime = recountDelivery(time, rangeMinute + advanceMinute);

		String name = DateUtils.formatDate(deliveryTime, DateUtils.HH_MM);
		if (name.equals("00:00")) {
			name = "24:00";
		}
		vo.setTitle(OrderMessages.ORDER_RANGEUTILS_SEND_TITLE(name));
		vo.setTimestamp(time);
		sendList.add(vo);
		return sendList;
	}

	/**
	 * 重新计算预计送达时间<br>
	 * 根据选择的预定时间，和配送范围时间，判断是否尽快送达<br>
	 * a). 是，则将当前时间加上配送范围时间，作为结果返回<br>
	 * b). 否，则判断预计送达时间-当前时间剩余的分钟数是否大于配送范围时间<br>
	 *     b1). 是，则直接返回delivery
	 *     b2). 否，如果预计送达时间<当前时间，抛异常；否则，预计送达时间加上超出的分钟数作为结果返回
	 * 
	 * @param delivery - 用户选择配送时间
	 * @param minute - 商家配送时间
	 * @return 预计送达时间
	 */
	public static Date recountDelivery(long delivery, int minute) {
		Calendar cal = Calendar.getInstance(); // 计算送达时间
		if (delivery == SOON_DELIERY || delivery == 1) {// #TODO 兼容客户端旧版本，等更新后去除该行 2015/10/26
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.add(Calendar.MINUTE, minute);
		} else {
			long now = cal.getTimeInMillis();
			if (now > delivery) {
				logger.error("order#OrderUtils#recountDelivery | 当前时间超过预计送达时间 | now: {}, delivery: {}", now, delivery);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, OrderMessages.ORDER_UTILS_RECOUNTDELIVERY_DELIVERY_TIMEOUT());
			}
			int interval = (int) ((delivery - now) / (1000 * 60));// 预定送达与当前时间的分钟差
			cal.setTimeInMillis(delivery);
			if (interval < minute) // 若分钟差小于商家配送时间，则送达时间还要加上分钟差
				cal.add(Calendar.MINUTE, interval);
		}
		return cal.getTime();
	}
	
	/**
	 * 根据预送达时间，定位服务日期（扣除库存日期，门店当天序列号日期等）（年月日的数值形式，月日各为两位数；例如：20150709）<br>
	 * 前置条件：因为存在营业时间跨天[22:00-02:00]，如果当前时间是01:00，则要扣除昨天的库存<br>
	 * 
	 * @author Hins
	 * @date 2015年11月5日 下午3:37:20
	 * 
	 * @param time - 预送达时间
	 * @param serviceHours - 门店营业时间规则json对象
	 * @param addMinute - 配送区域时间+美食最大提前下单时间
	 * @param bizType - 门店的营业类型 felix 2016-03-15
	 * @return 扣除库存日期，为0，表示失败
	 */
	public static int seekServiceDay(long time, String serviceHours, int addMinute, StoreBizType bizType) {
		logger.info("order#seekServiceDay | 根据预送达时间，定位服务日期 start | time: {}, serviceHours: {}, addMinute: {}", time, serviceHours,
				addMinute);
		// 思路：考虑到跨天，所以查询昨天和今天的配送时间，从昨天开始判断，若遍历的配送时间大于time，则返回对应时间的日期
		// 若time=尽快送达，则time用当前时间+addMinute判断
		JsonObject json = JsonUtils.toJsonObject(serviceHours);
		if (json == null) {
			logger.info("order#seekServiceDay | 门店营业时间规则不是合法json字符串 | time: {}, serviceHours: {}, addMinute: {}", time, serviceHours,
					addMinute);
			return 0;
		}
		// 1. 解析门店配送时间规则
		Map<Integer, List<Integer>> serviceHourMap = StoreServiceTimeUtils.getHours(serviceHours);
		if (serviceHourMap == null) {
			logger.info("order#seekServiceDay | 解析门店配送规则出错 | time: {}, serviceHours: {}, addMinute: {}", time, serviceHours, addMinute);
			return 0;
		}

		if (time == SOON_DELIERY) {
			time = System.currentTimeMillis() + addMinute * 60 * 1000;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);

		// 如果是预订类餐厅, 直接返回预计送达时间的日期为服务日期
		if (bizType.equals(StoreBizType.ADVBOOK)) {
			return Integer.valueOf(DateUtils.formatDate(cal.getTime(), DateUtils.YYYYMMDD));
		}

		// 因为存在跨天问题，所以从昨日开始查，只查昨日和今日
		cal.add(Calendar.DAY_OF_MONTH, -1);

		// 遍历最近2天的时间，直到找到有可选送达时间，则返回对应的日期（yyyyMMdd格式）
		for (int i = 0; i < 2; i++) {
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (serviceHourMap.containsKey(dayOfWeek)) {
				List<Integer> hours = serviceHourMap.get(dayOfWeek);
				List<Long> bookTimes = StoreServiceTimeUtils.addBookTime(hours, time, cal.getTime(), addMinute, true);
				if (!bookTimes.isEmpty()) {
					return Integer.valueOf(DateUtils.formatDate(cal.getTime(), DateUtils.YYYYMMDD));
				}
			}
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return 0;
	}

}
