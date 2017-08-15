/**
 * 
 */
package com.mazing.test.runtime;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.jayway.jsonpath.JsonPath;
import com.mazing.test.core.ApiReq;
import com.mazing.test.core.HttpRequestBuild;
import com.mazing.test.core.KeyValue;

/**
 * 运行时数据存储类
 * 
 * @author jfan 2016年9月29日 下午12:18:27
 */
@SuppressWarnings("unchecked")
public class RuntimeData {

	private static final Random r = new Random();

	private static KeyValue<String, String>[] uts;
	private static Set<String> userAlreadyLogin;

	private static Map<String, String[]> orders = new HashMap<>();
	private static Map<String, Integer> ordreNums = new HashMap<>();

	protected static String[] cityCodes;
	protected static String[] langCodes;
	protected static String[] storeIds;
	protected static String[] dishKeys;
	protected static String[] newsBids;

	private static final boolean loop = false;// 上面的cityCodes、storeIds等，是否通过循环的方式使用
	private static volatile Integer cityCodesIndex = 0;
	private static volatile Integer langCodesIndex = 0;
	private static volatile Integer storeIdsIndex = 0;
	private static volatile Integer dishKeysIndex = 0;
	private static volatile Integer newsBidsIndex = 0;
	private static volatile Integer utsIndex = 0;

	// 同步登录，算是启动的一部分吧！
	static {
		System.out.println("========>>>>>>>> init class: " + Config.class);
		System.out.println("========>>>>>>>> Config user: " + Config.giveRandomBySetNotBlank("user"));
		System.out.println("========>>>>>>>> Config storeSearch: " + Config.giveRandomBySetNotBlank("storeSearch"));
		System.out.println("========>>>>>>>> Config dishSearch: " + Config.giveRandomBySetNotBlank("dishSearch"));

		// 登录前X个用户
		int tmpInitSize = 7;
		String[] allUser = Config.giveAllNotBlank("user");
		int from = r.nextInt(allUser.length - tmpInitSize);// [1 ~ 100]，这里的意思是返回1~93随机数
		String[] tmp = Arrays.copyOfRange(allUser, from, from + tmpInitSize);// 3

		// go
		uts = goLogin("初始化", tmp);

		userAlreadyLogin = new HashSet<>();
		for (String user : tmp)
			userAlreadyLogin.add(user);

		// 后台打印收集到的数据量
		// new PrintDataThread().start();
	}

	/** 开始有任务跑的时候调用 */
	private static Object lock = new Object();
	private static boolean runGo = false;

	public static void runGo() {
		if (!runGo) {
			synchronized (lock) {
				if (!runGo) {
					new LoginThread().start();
					runGo = true;
				}
			}
		}
	}

	// ####

	public static String giveCityCode() {
		if (ArrayUtils.isEmpty(cityCodes))
			return null;
		if (loop) {
			if (cityCodesIndex >= cityCodes.length)
				cityCodesIndex = 0;
			return cityCodes[cityCodesIndex++];
		}
		return random(cityCodes);
	}

	public static String giveLangCode() {
		if (ArrayUtils.isEmpty(langCodes))
			return null;
		if (loop) {
			if (langCodesIndex >= langCodes.length)
				langCodesIndex = 0;
			return langCodes[langCodesIndex++];
		}
		return random(langCodes);
	}

	public static KeyValue<String, String> giveUserToken() {
		if (ArrayUtils.isEmpty(uts))
			return null;
		if (loop) {
			if (utsIndex >= uts.length)
				utsIndex = 0;
			return uts[utsIndex++];
		}
		return random(uts);
	}

	public static String giveStoreId() {
		if (ArrayUtils.isEmpty(storeIds))
			return null;
		if (loop) {
			if (storeIdsIndex >= storeIds.length)
				storeIdsIndex = 0;
			return storeIds[storeIdsIndex++];
		}
		return random(storeIds);
	}

	public static String giveDishKey() {
		if (ArrayUtils.isEmpty(dishKeys))
			return null;
		if (loop) {
			if (dishKeysIndex >= dishKeys.length)
				dishKeysIndex = 0;
			return dishKeys[dishKeysIndex++];
		}
		return random(dishKeys);
	}

	// public static String randomDishKey(String storeId) {
	// return null;
	// }

	public static String giveNewsBid() {
		if (ArrayUtils.isEmpty(newsBids))
			return null;
		if (loop) {
			if (newsBidsIndex >= newsBids.length)
				newsBidsIndex = 0;
			return newsBids[newsBidsIndex++];
		}
		return random(newsBids);
	}

	public static String[] allDishKey(String storeId) {
		return dishKeys;
	}

	public static String randomOrderNoByUT(String ut) {
		if (MapUtils.isEmpty(orders))
			return null;
		String[] vs = orders.get(ut);
		return random(vs);
	}

	// ####

	public static boolean yesAvailableStoreId() {
		return ArrayUtils.isNotEmpty(storeIds);
	}

	public static boolean yesAvailableCityCode() {
		return ArrayUtils.isNotEmpty(cityCodes);
	}

	public static boolean yesAvailableDishKey() {
		return ArrayUtils.isNotEmpty(dishKeys);
	}

	public static boolean yesAvailableNewsBid() {
		return ArrayUtils.isNotEmpty(newsBids);
	}

	public static boolean yesAvailableOrderNo(String ut) {
		return (MapUtils.isNotEmpty(orders) && ArrayUtils.isNotEmpty(orders.get(ut)));
	}

	/**
	 * 获取随机内容
	 */
	private static <T> T random(T[] vs) {
		if (ArrayUtils.isEmpty(vs))
			return null;
		return vs[r.nextInt(vs.length)];
	}

	// ####
	// #### 运行时数据收集
	// ####

	private static Object iLock = new Object();
	private static Map<Class<? extends CollectData>, CollectData> map = new HashMap<>();

	public static void collectData(String ut, String json, Class<? extends CollectData> clz) {
		CollectData cd = map.get(clz);
		if (null == cd) {
			synchronized (iLock) {
				if (null == map.get(clz)) {
					try {
						cd = clz.newInstance();
						map.put(clz, cd);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}

		try {
			cd.collect(json);
		} catch (Exception e) {
			System.out.println("XXXX 收集数据发生异常，class: " + cd.getClass() + "，message：" + e.getMessage());
		}
	}

	public static void collectOrderNo(String ut, String json) {
		Integer num = ordreNums.get(ut);
		int orderNum = (null == num ? 0 : num);
		List<String> arr = JsonPath.read(json, "$..orderNo");
		if (CollectionUtils.isNotEmpty(arr)) {
			orderNum += 1;
			ordreNums.put(ut, orderNum);
			mergeONo(ut, toArr(arr));
		}
	}

	private static String[] mergeONo(String ut, String[] nos) {
		String[] strings = orders.get(ut);
		String[] values = merge(strings, nos);
		orders.put(ut, values);
		return values;
	}

	private static String[] toArr(List<?> list) {
		if (CollectionUtils.isEmpty(list))
			return null;
		String[] arr = new String[list.size()];
		int index = 0;
		for (Object str : list)
			arr[index++] = str.toString();
		return arr;
	}

	/** 合并并去重 */
	private static <T> T[] merge(T[] v1, T[] v2) {
		if (null == v1)
			return v2;
		if (null == v2)
			return v1;
		Set<T> set = new HashSet<>();
		for (T v : v1)
			set.add(v);
		for (T v : v2)
			set.add(v);
		T[] newArr = Arrays.copyOf(v1, set.size());
		int index = 0;
		for (T v : set)
			newArr[index++] = v;
		return newArr;
	}

	/** 纯粹地追加 */
	private static <T> T[] append(T[] v1, T[] v2) {
		if (null == v1)
			return v2;
		if (null == v2)
			return v1;
		int index = v1.length;
		T[] newArr = Arrays.copyOf(v1, index + v2.length);
		for (T v : v2)
			newArr[index++] = v;
		return newArr;
	}

	private static KeyValue<String, String>[] goLogin(String msg, String[] allUser) {
		ApiReq<String> loginReq = new HttpRequestBuild().build("/a/v1/config/store/start");// 临时使用（url无所谓）
		KeyValue<String, String>[] tmp = new KeyValue[allUser.length];

		int index = 0;
		for (String user : allUser) {
			if (null != userAlreadyLogin && userAlreadyLogin.contains(user))
				continue;

			String[] split = StringUtils.split(user, ",");
			String password = split[1];
			String mobile = split[0];
			// System.out.println("login, user: " + mobile + ", pwd: " + password);

			KeyValue<String, String> ut;
			try {
				ut = loginReq.login("86", mobile, password);
			} catch (Exception e) {
				System.out.println("登录时发生异常(" + mobile + " | " + password + ")：" + e.getMessage());
				throw e;
			}
			tmp[index++] = ut;
		}

		System.out.println(msg + "登录结束：" + tmp.length);
		return tmp;
	}

	// ####

	private static class LoginThread extends Thread {

		@Override
		public void run() {
			String[] allUser = Config.giveAllNotBlank("user");
			KeyValue<String, String>[] allUT = goLogin("全部用户", allUser);
			uts = append(uts, allUT);
		}

	}

	protected static class PrintDataThread extends Thread {

		@Override
		public void run() {
			System.out.println("启动打印信息收集量的线程");
			int si = 20;// 只打印20次
			int in = 5000;// 间隔5s
			for (int i = 0; i < si; i++)
				try {
					Thread.sleep(in);
					print();
				} catch (InterruptedException e) {
					// ignore
				}
			System.out.println("打印信息收集量的线程--END");
		}

		private void print() {
			int utsSize = size(uts);
			int cityCodesSize = size(cityCodes);
			int langCodesSize = size(langCodes);
			int storeIdsSize = size(storeIds);
			int dishKeysSize = size(dishKeys);
			int newsBidSize = size(newsBids);
			int orderNoSize = orders.size();
			StringBuilder sb = new StringBuilder();
			sb.append("uts: ").append(utsSize);
			sb.append("，cityCodes: ").append(cityCodesSize);
			sb.append("，langCodes: ").append(langCodesSize);
			sb.append("，storeIds: ").append(storeIdsSize);
			sb.append("，dishKeys: ").append(dishKeysSize);
			sb.append("，newsBids: ").append(newsBidSize);
			sb.append("，orderNo: ").append(orderNoSize);
			System.out.println(sb.toString());
		}

		private <T> int size(T[] arr) {
			return (null == arr ? 0 : arr.length);
		}

	}

}
