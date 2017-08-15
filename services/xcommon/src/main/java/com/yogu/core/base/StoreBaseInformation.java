package com.yogu.core.base;

import java.io.Serializable;

public class StoreBaseInformation implements Serializable {

	private static final long serialVersionUID = 5716354630967985941L;

	/** 店铺ID */
	private long storeId;

	/** 门店名称（可限定频率地修改，例如100天修改一次） */
	private String storeName;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
