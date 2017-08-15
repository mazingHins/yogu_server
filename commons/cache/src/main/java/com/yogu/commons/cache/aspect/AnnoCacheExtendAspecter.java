package com.yogu.commons.cache.aspect;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.cache.CacheExtendService;
import com.yogu.commons.cache.annotation.CacheClean;
import com.yogu.commons.cache.annotation.CacheCleanExpr;
import com.yogu.commons.cache.annotation.Cacher;
import com.yogu.commons.cache.aspect.expr.ExprCache;
import com.yogu.commons.utils.JsonUtils;

/**
 * 基于方法如参（key）和返回值（value）来实现自动化缓存 <br>
 * 具备单机防穿透能力（有开关控制） <br>
 * <br>
 * -@Cacher 声明需要做cache<br>
 * -@CacheClean 则是声明该方法需要清理cache
 *
 * @author JFan 2015年8月19日 上午10:26:53
 */
@Aspect
public class AnnoCacheExtendAspecter {

	private static final Logger logger = LoggerFactory.getLogger(AnnoCacheExtendAspecter.class);

	public static final String PREFIX_SEPARATOR = ":";
	public static final String PARAM_SEPARATOR = "_";

	/** 回源防穿透，需要设置preventPenetration=true */
	private ConcurrentHashMap<String, FutureTask<Object>> preventPenetrationMap = new ConcurrentHashMap<>();
	private boolean preventPenetration;

	private CacheExtendService extendService;

	/**
	 * 方法返回结果 缓存处理方法
	 */
	@Around(value = "@annotation(cacher) && target(bean)", argNames = "cacher, bean")
	public Object cacheAround(ProceedingJoinPoint joinPoint, Cacher cacher, Object bean) throws Throwable {
		boolean enable = enable();

		if (enable) {
			// generate cache key
			Object[] params = joinPoint.getArgs();
			String cacheKey = toKey(cacher.value(), params);

			// MethodInvocationProceedingJoinPoint MethodSignatureImpl
			Object cache = extendService.get(cacheKey);

			// 命中缓存
			if (null != cache) {
				// logger.info("cacher#aspecter | hitCache | key: '{}'", cacheKey);
				if (logger.isDebugEnabled())
					logger.debug("cacher#aspecter | hitCache Print | key: '{}', result: {}", cacheKey, JsonUtils.toJSONString(cache));
				return cache;
			}

			// 回源
			// Object value = joinPoint.proceed();
			Object value = preventPenetration(cacheKey, joinPoint);// 防穿透（需要开启）

			// 缓存时间
			Integer time = cacher.time();
			if (0 == time)// 等于0：默认缓存时间
				time = extendService.defaultCacheTime();
			else if (0 > time)// 小于0：永久缓存
				time = null;

			// 缓存回源的结果
			if (null != value) {
				if (logger.isDebugEnabled()) {
					String json = JsonUtils.toJSONString(value);
					logger.debug("cacher#aspecter | cacheResult Print | key: '{}', time: {}, result: {}", cacheKey, time, json);
				} else {
					logger.info("cacher#aspecter | cacheResult | key: '{}', time: {}", cacheKey, time);
				}

				extendService.set(cacheKey, value, time);
			}

			return value;
		} else {
			// 走你
			// 各回各家，各找各妈
			return joinPoint.proceed();
		}
	}

//	/**
//	 * 清理缓存 处理方法（方法正常结束，才执行）
//	 */
//	@AfterReturning(value = "@annotation(cacheClean) && target(bean)", argNames = "cacheClean, bean, reval", returning = "reval")
//	public void cacheCleanAfterReturning(JoinPoint joinPoint, CacheClean cacheClean, Object bean, Object reval) {
//		boolean enable = enable();
//
//		if (enable) {
//			// func args
//			Object[] args = joinPoint.getArgs();
//
//			// 循环处理--中间出异常则跳过
//			CacheCleanExpr[] exprs = cacheClean.value();
//			if (ArrayUtils.isNotEmpty(exprs))
//				for (CacheCleanExpr expr : exprs) {
//					try {
//						Object[] params = toCleanParams(args, expr);
//						// generate cache key
//						String cacheKey = toKey(expr.value(), params);
//						// clean
//						logger.debug("cacher#aspecter | cleanCache | key: '{}'", cacheKey);
//						extendService.delete(cacheKey);
//					} catch (Exception e) {
//						logger.error("cacher#aspecter | cleanCache Error | message: {}", e.getMessage(), e);
//					}
//				}
//		}
//	}
	/**
	 * 清理缓存 处理方法（支持是否强制执行）
	 */
	@Around(value = "@annotation(cacheClean) && target(bean)", argNames = "cacheClean, bean")
	public Object cacheCleanAround(ProceedingJoinPoint joinPoint, CacheClean cacheClean, Object bean) throws Throwable {
		boolean enable = enable();

		if (enable) {
			boolean force = cacheClean.force();// 是否强制执行
			boolean returnOk = false;// 是否正常结束
			try {
				Object result = joinPoint.proceed();
				returnOk = true;
				return result;
			} finally {
				// 强制执行 || 成功return
				if (force || returnOk) {
					// func args
					Object[] args = joinPoint.getArgs();
					// 循环处理--中间出异常则跳过
					CacheCleanExpr[] exprs = cacheClean.value();
					if (ArrayUtils.isNotEmpty(exprs))
						for (CacheCleanExpr expr : exprs) {
							try {
								Object[] params = toCleanParams(args, expr);
								// generate cache key
								String cacheKey = toKey(expr.value(), params);
								// clean
								logger.info("cacher#aspecter | cleanCache | key: '{}'", cacheKey);
								extendService.delete(cacheKey);
							} catch (Exception e) {
								logger.error("cacher#aspecter | cleanCache Error | message: {}", e.getMessage(), e);
							}
						}
				}
			}
		} else {
			return joinPoint.proceed();
		}
	}

	/**
	 * key为'KEY前缀':param1_params...
	 */
	public static String toKey(String prefix, Object... objects) {
		if (ArrayUtils.isEmpty(objects))
			return prefix;

		String params = StringUtils.join(objects, PARAM_SEPARATOR);
		return StringUtils.join(new String[] { prefix, params }, PREFIX_SEPARATOR);
	}

	/**
	 * 根据args以及expr 格式，解读出具体的内容列表<br>
	 * expr格式请参考@CacheCleanExpr中的说明
	 */
	private Object[] toCleanParams(Object[] args, CacheCleanExpr cleanExpr) throws Exception {
		String expr = cleanExpr.expr();
		if (StringUtils.isBlank(expr))
			return args;

		return ExprCache.getParams(args, expr);
	}

	/**
	 * 在cacheExtendService为null的情况下，就当作不开启cache <br>
	 * enable==true，那么 cacheExtendService 一定不为null
	 */
	private boolean enable() {
		return ((null != extendService) && extendService.isEnable());
	}

	/**
	 * 防止回源时穿透<br>
	 * 有开关控制
	 */
	private Object preventPenetration(final String key, final ProceedingJoinPoint joinPoint) throws Throwable {
		if (!(preventPenetration))
			return joinPoint.proceed();

		// 真正的请求在这里面
		FutureTask<Object> future = new FutureTask<>(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				try {
					logger.info("cacher#aspecter | getSource | key: {}", key);
					return joinPoint.proceed();
				} catch (Throwable e) {
					throw new Exception(e);
				}
			}
		});

		// 如果同一个key全部进入到这里，那么得到的 future 实例为同一个（第一个）
		FutureTask<Object> oldFuture = preventPenetrationMap.putIfAbsent(key, future);

		try {
			if (null == oldFuture) {
				// 请求并等待结果
				future.run();
				return future.get();
			} else {
				// 等待结果
				future = null;
				return oldFuture.get();
			}
		} finally {
			try {
				preventPenetrationMap.remove(key);
			} catch (Exception e) {
				// ignore
			}
		}
	}

	// ####
	// ## set func
	// ####

	/**
	 * @param preventPenetration 要设置的 preventPenetration
	 */
	public void setPreventPenetration(boolean preventPenetration) {
		this.preventPenetration = preventPenetration;
	}

	/**
	 * @param extendService 要设置的 extendService
	 */
	public void setExtendService(CacheExtendService extendService) {
		this.extendService = extendService;
	}

}