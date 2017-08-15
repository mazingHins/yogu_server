package com.yogu.services.store.ticket.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用于核销记录 某具体ticket信息
 * 
 * @author sky
 *
 */
public class CheckedTicketShowVO extends UserTicketShowVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5675149401729217696L;

	private String phone;// 购买账号

	private String buyerName;// 购买者昵称

	private String checkerName;// 核销员昵称

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
