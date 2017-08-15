package com.yogu.services.store.ticket.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用于核销记录列表显示
 * 
 * @author sky
 *
 */
public class CheckedTicketListShowVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 905618162526090648L;

	private int checkedCount = 0;// 核销总数

	private long totalMoney = 0;// 核销金额,单位分

	private List<CheckedTicketShowVO> checkedList = new ArrayList<>();

	public int getCheckedCount() {
		return checkedCount;
	}

	public void setCheckedCount(int checkedCount) {
		this.checkedCount = checkedCount;
	}

	public long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<CheckedTicketShowVO> getCheckedList() {
		return checkedList;
	}

	public void setCheckedList(List<CheckedTicketShowVO> checkedList) {
		this.checkedList = checkedList;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
