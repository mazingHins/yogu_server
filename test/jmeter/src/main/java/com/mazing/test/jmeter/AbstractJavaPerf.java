/**
 * 
 */
package com.mazing.test.jmeter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.jayway.jsonpath.JsonPath;
import com.mazing.test.core.KeyValue;
import com.mazing.test.runtime.CollectData;
import com.mazing.test.runtime.RuntimeData;
import com.mazing.test.runtime.impl.AllDishKeyCodeCollect;
import com.mazing.test.runtime.impl.AllStoreIdCodeCollect;

/**
 * <br>
 *
 * @author JFan 2015年12月22日 下午5:36:53
 */
public abstract class AbstractJavaPerf extends AbstractJavaSamplerClient {

	static {
		System.out.println("========>>>>>>>> init class: " + RuntimeData.class);
		System.out.println("========>>>>>>>> RuntimeData randomUserToken: " + RuntimeData.giveUserToken());
	}

	protected String activityHost = "http://activityapi.mazing.com";
	protected String configHost = "http://configapi.mazing.com";
	protected String orderHost = "http://orderapi.mazing.com";
	protected String storeHost = "http://storeapi.mazing.com";
	protected String userHost = "http://userapi.mazing.com";

	protected Random r = new Random();
	private Integer[] loginWhethers = new Integer[] { 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0// 17/50 是需要登录的，其余不用登录
			, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1 };

	/**
	 * 压测的接口，返回值是arr还是obj<br>
	 * true：array，false：obj<br>
	 */
	private boolean resultIsArray;

	/**
	 * 压测的接口，是否是必须登录的<br>
	 * 决定，压测时是否给予UserToken、UserSecter信息
	 */
	private boolean mustLogin;

	public AbstractJavaPerf(boolean mustLogin, boolean resultIsArray) {
		this.resultIsArray = resultIsArray;
		this.mustLogin = mustLogin;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setupTest(JavaSamplerContext context) {
		RuntimeData.runGo();
		initial();
	}

	/**
	 * 每个线程初始化方法，一个线程只初始化一次
	 */
	protected void initial() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		// 准备数据
		KeyValue<String, String> ut = null;
		if (mustLogin || 1 == random(loginWhethers))
			ut = RuntimeData.giveUserToken();

		// 判定是否需要执行
		if (!whetherRun(ut))
			return null;

		// 准备执行
		SampleResult results = new SampleResult();
		results.sampleStart();// 事务开启
		String data = call(ut);// 请求目标
		results.sampleEnd(); // 事务结束
		// # 执行结束

		// 数据处理
		// results.setResponseData(data, "UTF-8");
		boolean success = determine(data);// 校验结果
		// 收集数据
		if (success)
			collectData(null != ut ? ut.getKey() : null, data);

		results.setSuccessful(success);
		return results;
	}

	/**
	 * 获取随机内容
	 */
	protected <T> T random(T[] vs) {
		if (ArrayUtils.isEmpty(vs))
			return null;
		return vs[r.nextInt(vs.length)];
	}

	/**
	 * 返回是否需要执行本次请求<br>
	 * 如果返回false，则不会记入有效请求次数（不算失败，也不算一个事务）<br>
	 * <br>
	 * 如果有需要，请覆盖
	 * 
	 * @param ut 随机得到一个已经登录的UT信息（如果是必须登录的接口，这里不为null；如果是非必登录的，可能会得到null）
	 * @param map UT对应的用户配置信息（perf.data文件中对应的某行配置）
	 * @return 是否继续执行
	 */
	protected boolean whetherRun(KeyValue<String, String> ut) {
		// 如果需要登录，但是又没有拿到有效UT
		if (mustLogin && null == ut)
			return false;
		// 需要的数据，但是确没有
		if (needStoreId && !(RuntimeData.yesAvailableStoreId()))
			return false;
		if (needDishKey && !(RuntimeData.yesAvailableDishKey()))
			return false;
		if (needCityCode && !(RuntimeData.yesAvailableCityCode()))
			return false;
		if (needNewsBid && !(RuntimeData.yesAvailableNewsBid()))
			return false;

		if (needOrderNo && !(RuntimeData.yesAvailableOrderNo(ut.getKey())))
			return false;

		return true;
	}

	/**
	 * 执行接口调用的方法<br>
	 * 并将执行请求得到的结果返回
	 * 
	 * @param ut 随机得到一个已经登录的UT信息（如果是必须登录的接口，这里不为null；如果是非必登录的，可能会得到null）
	 */
	protected abstract String call(KeyValue<String, String> ut);

	/**
	 * 判定返回值是否符合接口要求<br>
	 * 默认只检测两点：<br>
	 * 1：code==1<br>
	 * 2：object不为空（resultIsArray为true时：!='[]'，否则判定!='{}'）
	 */
	protected boolean determine(String data) {
		// String code = StringUtils.readJsonParam(data, "code");
		Integer code = JsonPath.read(data, "$.code");

		// code = 1
		boolean success = (null != code && 1 == code);

		// 目前只检测code=1，需要检测是否返回了内容的，请自行扩展
		// if (success) {
		// // 返回的内容不为空
		// String object = StringUtils.readJsonParam(data, "object");
		// success = (resultIsArray ? !("[]".equals(object)) : !("{}".equals(object)));
		// }

		if (!success)
			onError(data);
		return success;
	}

	/**
	 * 如果 determine() 返回false，会回调这个方法
	 */
	protected void onError(String data) {
		System.out.println(this.getClass() + " (" + mustLogin + ", " + resultIsArray + ") " + (null == data ? "-Null-" : data));
	}

	// ####
	// #### 数据收集部分
	// ####

	// 运行时需要什么数据
	private boolean needStoreId, needDishKey, needOrderNo, needCityCode, needNewsBid;
	// 返回的结果正确的话，能够收集到什么数据
	private boolean collectOrderNo;
	private Set<Class<? extends CollectData>> collectClasses = new HashSet<>();
	private Map<Class<? extends CollectData>, Integer> collectNum = new HashMap<>();
	private Map<Class<? extends CollectData>, Integer> alreadyNum = new HashMap<>();

	protected void needStoreId() {
		needStoreId = true;
	}

	protected void needDishKey() {
		needDishKey = true;
	}

	protected void needOrderNo() {
		needOrderNo = true;
	}

	protected void needCityCode() {
		needCityCode = true;
	}

	protected void needNewsBid() {
		needNewsBid = true;
	}

	//

	protected void collectOrderNo() {
		collectOrderNo = true;
	}

	protected void collectStoreId() {
		collectByClass(200, AllStoreIdCodeCollect.class);
	}

	protected void collectDishKey() {
		collectByClass(200, AllDishKeyCodeCollect.class);
	}

	protected void collectByClass(int max, Class<? extends CollectData> clz) {
		if (null == clz || 1 > max) {
			onError("XXXXXXXXXXXXXXXXXXXXXX");
			return;
		}
		collectClasses.add(clz);
		collectNum.put(clz, max);
	}

	private void collectData(String ut, String json) {
		if (collectOrderNo)
			RuntimeData.collectOrderNo(ut, json);

		for (Class<? extends CollectData> clz : collectClasses) {
			Integer max = collectNum.get(clz);
			Integer already = alreadyNum.get(clz);

			int a = giveInt(already);
			if (a >= giveInt(max))
				continue;

			RuntimeData.collectData(ut, json, clz);
			alreadyNum.put(clz, ++a);
		}
	}

	private int giveInt(Integer num) {
		return null == num ? 0 : num;
	}

}
