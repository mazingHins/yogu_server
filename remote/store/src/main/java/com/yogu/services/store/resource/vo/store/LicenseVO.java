package com.yogu.services.store.resource.vo.store;

import java.io.Serializable;

public class LicenseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6743979138250459472L;
	private short type; // 证件类型 1：营业执照 2：营业资质证件照
	private String img;// 证件照图片链接

	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
