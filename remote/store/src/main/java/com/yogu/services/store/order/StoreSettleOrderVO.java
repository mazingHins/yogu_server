package com.yogu.services.store.order;

import java.io.Serializable;
import java.util.List;

import com.yogu.services.store.base.dto.Store;
import com.yogu.services.store.base.dto.StoreServiceRangeVO;
import com.yogu.services.store.dish.dto.DishDetailVO;
import com.yogu.services.store.dish.dto.DishSurplusVO;

/**
 * 预下单、结算service。所需store域的所有信息实体<br>
 * 包括：餐厅信息，美食信息，库存信息，配送费信息
 *
 * @date 2016年10月1日 上午10:18:23
 * @author hins
 */
public class StoreSettleOrderVO {
	
	/**
	 * 餐厅信息
	 */
	private Store store;
	
	/**
	 * 菜品明细信息
	 */
	private List<DishDetailVO> list;
	
	/**
	 * 配送信息
	 */
	private StoreServiceRangeVO range;
	
	/**
	 * 库存信息
	 */
	private List<DishSurplusVO> surplus;
	
	/**
	 * 最大需要提前下单时间(分钟)
	 */
	private int advanceMinute = 0;
	
	/** 新的配送时间选择 */
	private List<BookTimeVO> bookTime;
	
	/**
	 * 美食总价
	 */
	private long goodsFee;
	
	/**
	 * 定位的服务日期，格式yyyyMMdd
	 */
	private int serviceDay;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<DishDetailVO> getList() {
		return list;
	}

	public void setList(List<DishDetailVO> list) {
		this.list = list;
	}

	public StoreServiceRangeVO getRange() {
		return range;
	}

	public void setRange(StoreServiceRangeVO range) {
		this.range = range;
	}

	public List<DishSurplusVO> getSurplus() {
		return surplus;
	}

	public void setSurplus(List<DishSurplusVO> surplus) {
		this.surplus = surplus;
	}

	public int getAdvanceMinute() {
		return advanceMinute;
	}

	public void setAdvanceMinute(int advanceMinute) {
		this.advanceMinute = advanceMinute;
	}
	
	public List<BookTimeVO> getBookTime() {
		return bookTime;
	}

	public void setBookTime(List<BookTimeVO> bookTime) {
		this.bookTime = bookTime;
	}
	
	public long getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(long goodsFee) {
		this.goodsFee = goodsFee;
	}
	

	public int getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(int serviceDay) {
		this.serviceDay = serviceDay;
	}

	public static class SendTimeVO implements Serializable {

		private static final long serialVersionUID = 7434327528828099512L;

		/** 显示文本 */
		private String name;
		
		/**
		 * 选中后的时间内容
		 */
		private String title;

		/** 时间毫秒数(格林威治时间) */
		private long timestamp;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

	}
	
	/**
	 * 预下单settle.do接口，可选配送时间VO
	 * 
	 * @author Hins
	 * @date 2015年11月12日 下午3:07:29
	 */
	public static class BookTimeVO {

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
	
}
