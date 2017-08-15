package com.yogu.services.store.base.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 图片对象
 */
public class PicVO implements Serializable {

	private static final long serialVersionUID = -6120442459666804498L;

	/** 图片ID */
	private long picId;

	/** 图片相对路径 */
	private String path;

	/** 图片配字（允许为空） */
	private String content;

	/** 是否启用；1：启用、其他：不启用 */
	private short effective = 1;

	/** 排序，大的在前 */
	private int sequence = 0;

	/**
	 * @return picId
	 */
	public long getPicId() {
		return picId;
	}

	/**
	 * @param picId 要设置的 picId
	 */
	public void setPicId(long picId) {
		this.picId = picId;
	}

	/**
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path 要设置的 path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content 要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return effective
	 */
	public short getEffective() {
		return effective;
	}

	/**
	 * @param effective 要设置的 effective
	 */
	public void setEffective(short effective) {
		this.effective = effective;
	}

	/**
	 * @return sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence 要设置的 sequence
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
