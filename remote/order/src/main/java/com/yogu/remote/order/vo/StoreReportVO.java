package com.yogu.remote.order.vo;

import java.io.Serializable;

/**
 * 餐厅报表
 * 
 * @author east
 * @date 2016年11月24日
 */
public class StoreReportVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1840111716490841188L;

	/** 餐厅名称 */
	private String storeName;

	/** excel字节码的表示形式 */
	private byte[] byteArray;

	/**
	 * email标题
	 */
	private String title;

	/**
	 * excel文件名
	 */
	private String fileName;

	public StoreReportVO() {
	}

	public StoreReportVO(String storeName, byte[] byteArray, String title, String fileName) {
		this.storeName = storeName;
		this.byteArray = byteArray;
		this.title = title;
		this.fileName = fileName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "StoreReportVO [storeName=" + storeName + ", title=" + title + ", fileName=" + fileName + "]";
	}

}
