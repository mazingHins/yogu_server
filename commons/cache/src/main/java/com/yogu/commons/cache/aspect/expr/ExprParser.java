/**
 * 
 */
package com.yogu.commons.cache.aspect.expr;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 解读expt <br>
 *
 * @author JFan 2015年8月21日 下午5:05:01
 */
public class ExprParser {

	private String expr;

	public ExprParser(String expr) {
		this.expr = expr;
	}

	/**
	 * 解析expr<br>
	 * 
	 * 具体格式参考 @CacheCleanExpr 中的说明
	 */
	public ExprNode[] parser() throws Exception {
		if (StringUtils.isBlank(expr))
			return null;

		List<ExprNode> list = new ArrayList<>();
		// 解读
		String[] split = StringUtils.splitByWholeSeparatorPreserveAllTokens(expr, ",");
		int index = 1;
		// str的格式为x.y；x为数字
		for (String str : split) {
			String tmp = StringUtils.trimToNull(str);
			if (null == tmp)// 指定了空白的内容
				throw new Exception("blank format, '" + expr + "' index " + index);

			ExprNode node = new ExprNode();

			// 直接是一个数字
			if (StringUtils.isNumeric(tmp)) {
				int argsIndex = Integer.parseInt(tmp);
				node.setArgsIndex(argsIndex);
			} else {
				String[] xy = StringUtils.split(tmp, ".");
				if (2 != xy.length)// 不是x.y格式
					throw new Exception("format is not correct n.y, '" + expr + "' index " + index);

				int argsIndex = Integer.parseInt(xy[0]);
				String fieldName = xy[1];
				fieldName = StringUtils.trimToNull(fieldName);// ‘x. y ’ 的情况

				if (null == fieldName)
					throw new Exception("format is not correct, y Blank, '" + expr + "' index " + index);

				node.setArgsIndex(argsIndex);
				node.setFieldName(fieldName);
			}

			list.add(node);
			index += 1;
		}

		return list.toArray(new ExprNode[list.size()]);
	}

}
