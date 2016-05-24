package com.mazing.utils.store.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mazing.utils.common.DateUtils;
import com.mazing.utils.dto.Store;
import com.mazing.utils.store.StoreBizType;
import com.mazing.utils.store.StoreServiceTimeUtils;
import com.mazing.utils.store.order.vo.BookTimeVO;
import com.mazing.utils.store.order.vo.SendTimeVO;

public class OrderUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderUtils.class);
	
	public static void main(String[] args) {
		String serviceHours = "{\"1\":[0,0], \"2\":[0,0],\"3\":[0,0],\"4\":[0,0],\"5\":[0,0],\"6\":[0,0],\"7\":[0,0]}";
		// System.out.println(RangeUtils.seekServiceDay(1448078400000L,
		// serviceHours, 15));

		int ADVANCE_RESERVE_DAY = 2;

		Store store = new Store();
		store.setAdvanceReserveDays(ADVANCE_RESERVE_DAY);
		store.setAcceptOrderDeadline((short) 200);
		store.setBizType(StoreBizType.NORMAL.getValue());
		store.setAdvanceReserveDays(5);
		store.setServiceHours(serviceHours);
		store.setOpenHours(serviceHours);
		
		List<BookTimeVO> list = loadBookTime(store, 60);
		for (BookTimeVO book : list) {
			String day = book.getDay();
			List<SendTimeVO> times = book.getTime();
			for (SendTimeVO v : times) {
				System.out.println(day + " : " + v.getName());
			}
		}
	}
	
	/**
	 * 尽快送达时间
	 */
	public static final long SOON_DELIERY = 0L;
	
	/**
	 * 日期对应的周下标显示的文本
	 */
	private static final String[] WEEK_DAYS = { "(周日)", "(周一)", "(周二)", "(周三)", "(周四)", "(周五)", "(周六)" };

	/**
	 * 计算餐厅在预下单的时候，可选的送达时间
	 * 
	 * @author hins
	 * @date 2016年5月23日下午2:51:24
	 * 
	 * @param store - 餐厅对象
	 * @param rangeMinute - 区域配送时间，单位分钟
	 * @return 可选送达时间列表，若无，返回empty
	 */
	public static List<BookTimeVO> loadBookTime(Store store, int rangeMinute) {
		// 1. 获取全部可选配送时间(long 毫秒数类型)
		List<Long> times = eachTime(store, rangeMinute);
		if (times.isEmpty()) {
			return new ArrayList<BookTimeVO>(0);
		}

		// 2. 将可选配送时间归类成map，按照插入排序
		// 同一天的配送时间只有一个key，value-那天的可选配送时间
		LinkedHashMap<Long, List<SendTimeVO>> timeMap = loadBookTimeMap(times, rangeMinute);

		// 3. 装载返回结果
		List<BookTimeVO> result = toBookTimeJsonArray(timeMap);
		return result;
	}
	
	/**
	 * 计算餐厅所有的可选送达时间列表（列表值为long类型，格林威治时间毫秒数）
	 * 
	 * @author hins
	 * @date 2016年5月23日下午2:54:09
	 * 
	 * @param store - 餐厅对象
	 * @param rangeMinute - 区域配送时间，单位分钟
	 * @return 可选送达时间列表，若无，返回empty list
	 */
	private static List<Long> eachTime(Store store, int rangeMinute) {
		int advanceDay = store.getAdvanceReserveDays(); // 门店可提前预定天数
		String serviceHours = StoreServiceTimeUtils.getStoreServiceHours(store);	// 营业时间规则json字符串
		if (store.getBizType() == StoreBizType.ADVBOOK.getValue()) {
			// 如果是纯预定餐厅，可提前预定天数默认=10，addMinute不考虑区域配送时间
			advanceDay = 10;
		} 

		logger.info("order#service#eachSendTime | 开始装载配送时间 | advanceReserveDays: {}, serviceHours: {}, rangeMinute: {}", advanceDay,
				serviceHours, rangeMinute);
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
		int addMinuteMs = rangeMinute * 60 * 1000;// 配送时间毫秒数
		// 当天可以开始配送时间（所有的配送时间都要在此之后）
		long serviceTime = cal.getTimeInMillis() + addMinuteMs;
		long baseTime = serviceTime;	// 基准时间，用于判断是否有尽快送达

		// 2. 装载每一天的配送时间
		// 如果门店营业类型是"预定类餐厅"，则判断现在的时间是否超过截单时间，若超过，则只能预订后一个营业日的订单；若为超过，只能预订下一个营业日的订单
		int startDay = StoreServiceTimeUtils.setStartServiceDay(cal, store.getBizType(), store.getAcceptOrderDeadline());

		List<Long> result = new ArrayList<Long>();
		for (; startDay <= advanceDay; startDay++) {
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			if (serviceHourMap.containsKey(dayOfWeek)) {
				List<Integer> hours = serviceHourMap.get(dayOfWeek);
				result.addAll(StoreServiceTimeUtils.addBookTime(hours, serviceTime, cal.getTime(), rangeMinute));
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
	 * @author hins
	 * @date 2016年5月23日下午3:12:32
	 * 
	 * @param serviceHourMap- 餐厅营业时间规则[当天开始营业时间分钟数，时间间隔（30分钟），时间间隔（30分钟）...直到结束营业时间]
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

		int begin = 0;	// 门店开始营业的分钟数
		int end = 0;	// 门店结束营业的分钟数
		
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
	 * 根据可选送达时间列表，将时间按天归类。
	 * key-天（那天最后日期23:59:59 999）,value-那天可选送达时间SendTimeVO列表
	 * 
	 * @author hins
	 * @date 2016年5月23日下午3:21:34
	 * 
	 * @param times - 可选送达时间
	 * @param rangeMinute - 区域配送睡觉
	 * @return
	 */
	private static LinkedHashMap<Long, List<SendTimeVO>> loadBookTimeMap(List<Long> times, int rangeMinute) {
		// 同一天的配送时间只有一个key，value-那天的可选配送时间
		LinkedHashMap<Long, List<SendTimeVO>> timeMap = new LinkedHashMap<Long, List<SendTimeVO>>();
		// 判断首条是否尽快送达
		if (times.get(0) == SOON_DELIERY) {
			timeMap.put(SOON_DELIERY, getSoonSendTime(times.get(0), rangeMinute));
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
			send.setTitle("预计" + send.getName() + "送达");

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
	 * 计算尽快送达的sendTime列表
	 * 
	 * @author hins
	 * @date 2016年5月23日下午3:25:04
	 * 
	 * @param time - 尽快送达时间戳
	 * @param rangeMinute - 区域配送时间
	 * @return
	 */
	private static List<SendTimeVO> getSoonSendTime(long time, int rangeMinute) {
		List<SendTimeVO> sendList = new ArrayList<SendTimeVO>(1);
		SendTimeVO vo = new SendTimeVO();
		vo.setName("");
		Date deliveryTime = OrderUtils.recountDelivery(time, rangeMinute);

		String name = DateUtils.formatDate(deliveryTime, DateUtils.HH_MM);
		if (name.equals("00:00")) {
			name = "24:00";
		}
		vo.setTitle("预计" + name + "送达");
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
	 * @author hins
	 * @date 2016年5月23日下午3:23:30
	 * 
	 * @param delivery - 用户选择配送时间
	 * @param minute - 商家配送时间
	 * @return 新的预计送达时间
	 */
	private static Date recountDelivery(long delivery, int minute) {
		Calendar cal = Calendar.getInstance(); // 计算送达时间
		if (delivery == SOON_DELIERY || delivery == 1) {// #TODO 兼容客户端旧版本，等更新后去除该行 2015/10/26
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.add(Calendar.MINUTE, minute);
		} else {
			long now = cal.getTimeInMillis();
			if (now > delivery) {
				logger.error("order#OrderUtils#recountDelivery | 当前时间超过预计送达时间 | now: {}, delivery: {}", now, delivery);
				throw new RuntimeException("预计送达时间超时");	// 其实是抛出自定义异常
				// throw new ServiceException(ResultCode.PARAMETER_ERROR, "预计送达时间超时");
			}
			int interval = (int) ((delivery - now) / (1000 * 60));// 预定送达与当前时间的分钟差
			cal.setTimeInMillis(delivery);
			if (interval < minute) // 若分钟差小于商家配送时间，则送达时间还要加上分钟差
				cal.add(Calendar.MINUTE, interval);
		}
		return cal.getTime();
	}
	
	/**
	 * 装载送达时间戳到配送时间列表。<br>
	 * 
	 * @author hins
	 * @date 2016年5月23日下午3:26:21
	 * 
	 * @param time - 时间
	 * @return
	 */
	private static SendTimeVO formatBookTime(long time) {
		SendTimeVO vo = new SendTimeVO();
		String name = DateUtils.formatDate(new Date(time), DateUtils.HH_MM);
		vo.setName(name);
		vo.setTimestamp(time);
		return vo;
	}
	
	/**
	 * 将一天的配送时间转成BookTimeVO列表
	 * 
	 * @author hins
	 * @date 2016年5月23日下午3:27:11
	 * 
	 * @param timeMap - 每天的配送时间，key-一天的结束时间，value-当天的可选配送时间（SendTimeVO对象）
	 * @return 一天的配送时间 BookTimeVO列表，若不存在，返回empty
	 */
	private static List<BookTimeVO> toBookTimeJsonArray(LinkedHashMap<Long, List<SendTimeVO>> timeMap) {
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
				vo.setDay("尽快送达");
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
				day = "今日";
				break;
			case 1:
				day = "明日";
				break;
			case 2:
				day = "后日";
				break;
			default:
				day = DateUtils.formatDate(cal.getTime(), DateUtils.MM_DD);
				break;
			}
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			day = day + WEEK_DAYS[dayOfWeek - 1];
			vo.setDay(day);
			vo.setTime(timeMap.get(key));
			result.add(vo);
		}
		return result;
	}
}
