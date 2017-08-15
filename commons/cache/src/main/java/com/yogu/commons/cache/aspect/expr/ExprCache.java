package com.yogu.commons.cache.aspect.expr;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 处理 CacheCleanExpr expr 的方法类 <br>
 * 基于map做cache，避免重复解析
 *
 * @author JFan 2015年8月21日 下午4:45:09
 */
public class ExprCache {

	/** expr作为key，value为解析之后的对象 */
	private static final Map<String, ExprNode[]> exprCache = new ConcurrentHashMap<>();

	/**
	 * 根据expr，从args中读取需要的内容
	 * 
	 * @throws Exception
	 */
	public static Object[] getParams(Object[] args, String expr) throws Exception {
		ExprNode[] parseExpr = parseExpr(expr);
		if (ArrayUtils.isEmpty(parseExpr))
			return null;

		int index = 0;
		Object[] params = new Object[parseExpr.length];

		/* 任何异常，往外抛 */
		for (ExprNode node : parseExpr) {
			int argsIndex = node.getArgsIndex();
			String field = node.getFieldName();

			Object param = null;
			Object arg = args[argsIndex];// 找到入参对象
			if (null == field)
				param = arg;// 直接去args中 对应下标的对象
			else
				param = FieldCache.readField(arg, field);// 读取对象中的field属性

			params[index++] = param;
		}
		return params;
	}

	/**
	 * 解读expr，如果已经解读过了，则使用缓存的
	 */
	private static ExprNode[] parseExpr(String expr) throws Exception {
		ExprNode[] ins = exprCache.get(expr);
		if (null == ins) {
			ins = new ExprParser(expr).parser();
			exprCache.put(expr, ins);
		}
		return ins;
	}

}
