package com.mazing.utils.store.order.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 预下单时，可选送达时间VO
 * @author hins
 *
 */
public class BookTimeVO implements Serializable {

	private static final long serialVersionUID = 7012833863809765958L;

	private String day; // 日期文本

	private List<SendTimeVO> time; // 当天可选送达时间对象

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<SendTimeVO> getTime() {
		return time;
	}

	public void setTime(List<SendTimeVO> time) {
		this.time = time;
	}
	
}
