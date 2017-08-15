package com.yogu.core.consumer.messageBean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * 商家有新订单 的短信通知 messageBean(producer 中 传递的 message body, consumer 获取该message body做相关业务操作)<br>
 * 
 * 简单来说,可以将该message bean 当做一个参数传递的对象封装, consumer从 message 中获取body, body 反向序列化得到该对象,从而得到所需业务参数
 * 
 * 
 * 
 * @author sky
 *
 */
public class NewOrdersNotifyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5276072450951537848L;

	private long orderNo;// 新单 订单号

	private String storeName;// 餐厅名

	private String name;// 商家名

	private Date createTime;// 订单创建时间

	private String phone;// 商家手机

	private String countryCode;// 国家代码

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
