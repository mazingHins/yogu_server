/**
 * 
 */
package com.yogu.commons.cache.aspect.expr;

/**
 * 描述如参args下标以及属性名 <br>
 *
 * @author JFan 2015年8月21日 下午5:06:04
 */
public class ExprNode {

	/** args的下标 */
	private int argsIndex;

	/** 读取具体对象中的那个字段 */
	private String fieldName;

	/**
	 * @return argsIndex
	 */
	public int getArgsIndex() {
		return argsIndex;
	}

	/**
	 * @param argsIndex 要设置的 argsIndex
	 */
	public void setArgsIndex(int argsIndex) {
		this.argsIndex = argsIndex;
	}

	/**
	 * @return fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName 要设置的 fieldName
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}
