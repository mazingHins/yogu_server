package com.yogu.core.sms;

import java.io.Serializable;

/**
 * 敏感词转换“替代词”对象
 *
 * @date 2016年7月27日 下午6:21:51
 * @author hins
 */
public class SensitiveMpBean implements Serializable {

	private static final long serialVersionUID = 5968902946982371772L;

	/**
	 * 敏感词
	 */
	private String sensitive;
	
	/**
	 * 替换的文本
	 */
	private String replace;

	public String getSensitive() {
		return sensitive;
	}

	public void setSensitive(String sensitive) {
		this.sensitive = sensitive;
	}

	public String getReplace() {
		return replace;
	}

	public void setReplace(String replace) {
		this.replace = replace;
	}
	
}
