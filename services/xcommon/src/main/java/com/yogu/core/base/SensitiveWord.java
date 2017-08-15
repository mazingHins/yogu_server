package com.yogu.core.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 敏感词库表
 * 
 */
public class SensitiveWord implements Serializable {

	private static final long serialVersionUID = -3074457346954295729L;

	/** 敏感词类型：1-昵称；2-内容文本（包含门店美食信息，评论，用户地址，订单备注等） */
	private short wordType = 0;

	/** 敏感词内容 */
	private String content;
	
	/**
	 * 敏感词类型 枚举
	 * @author Hins
	 * @date 2015年10月8日 下午4:01:50
	 */
	public enum WordType{
		/**
		 * 昵称
		 */
	    NICKNAME((short)1),

	    /**
	     * 内容文本
	     */
	    CONTENT((short)2);
	    
	    private short value;

	    private WordType(short value) {
	        this.value = value;
	    }

	    public short getValue() {
	        return value;
	    }
	}

	public void setWordType(short wordType) {
		this.wordType = wordType;
	}

	public short getWordType() {
		return wordType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
