package com.yogu.services.order.base.dto.vo;

import java.io.Serializable;
import java.util.List;

import com.yogu.core.web.ResultCode;
import com.yogu.services.store.dish.dto.DishDetailVO;
import com.yogu.services.store.dish.dto.DishSurplusVO;
import com.yogu.services.store.order.StoreSettleOrderVO.BookTimeVO;
import com.yogu.services.store.order.StoreSettleOrderVO.SendTimeVO;

/**
 * 订单预下单接口-结算、异常信息VO
 *
 * @date 2016年10月1日 下午12:57:33
 * @author hins
 */
public class OrderSettleVO {
	
	/**
	 * 结算信息
	 */
	private SettleVO settle;
	
	/**
	 * 异常信息。默认无异常
	 */
	private SettleMessageVO settleMessage;
	
	public OrderSettleVO(){
		settleMessage = new SettleMessageVO(ResultCode.SUCCESS);
		settle = new SettleVO();
	}
	
	public SettleVO getSettle() {
		return settle;
	}

	public void setSettle(SettleVO settle) {
		this.settle = settle;
	}

	public SettleMessageVO getSettleMessage() {
		return settleMessage;
	}

	public void setSettleMessage(SettleMessageVO settleMessage) {
		this.settleMessage = settleMessage;
	}

	/**
	 * 预下单接口-结算信息
	 *
	 * @date 2016年10月1日 下午12:59:20
	 * @author hins
	 */
	public static class SettleVO implements Serializable {

		private static final long serialVersionUID = 6196802479657481969L;

		/** 菜品明细 */
		private List<DishDetailVO> settleItem;
		
		/** 菜品明细-v1.1.0-预订类餐厅设计方案 调整 */
		private List<DishDetailVO> dishItem;

		/** 订单总价格 */
		private long totalFee;

		/** 优惠总价格 */
		private long totalDiscountFee;

		/** 商品总价格 */
		private long goodsFee;

		/** 预计送达时间 */
		private long deliveryTime;

		/** 配送费信息 */
		private SendInfoVO sendInfo;

		/** 用餐人数 */
		private short mealNumber = 0;

		/** 下单可选特殊项 */
		private FeatureVO feature;


		/** 配送时间选择（旧版本） */
		private List<SendTimeVO> sendTime;

		/** 新的配送时间选择 */
		private List<BookTimeVO> bookTime;
		
		
		/**
		 * 客户端可用优惠券button标题
		 */
		private String optionalCouponTitle;

		public List<DishDetailVO> getSettleItem() {
			return settleItem;
		}

		public void setSettleItem(List<DishDetailVO> settleItem) {
			this.settleItem = settleItem;
		}
		
		public List<DishDetailVO> getDishItem() {
			return dishItem;
		}

		public void setDishItem(List<DishDetailVO> dishItem) {
			this.dishItem = dishItem;
		}

		public long getTotalFee() {
			return totalFee;
		}

		public void setTotalFee(long totalFee) {
			this.totalFee = totalFee;
		}

		public long getTotalDiscountFee() {
			return totalDiscountFee;
		}

		public void setTotalDiscountFee(long totalDiscountFee) {
			this.totalDiscountFee = totalDiscountFee;
		}

		public long getGoodsFee() {
			return goodsFee;
		}

		public void setGoodsFee(long goodsFee) {
			this.goodsFee = goodsFee;
		}

		public short getMealNumber() {
			return mealNumber;
		}

		public void setMealNumber(short mealNumber) {
			this.mealNumber = mealNumber;
		}

		public long getDeliveryTime() {
			return deliveryTime;
		}

		public void setDeliveryTime(long deliveryTime) {
			this.deliveryTime = deliveryTime;
		}

		public SendInfoVO getSendInfo() {
			return sendInfo;
		}

		public void setSendInfo(SendInfoVO sendInfo) {
			this.sendInfo = sendInfo;
		}

		public FeatureVO getFeature() {
			return feature;
		}

		public void setFeature(FeatureVO feature) {
			this.feature = feature;
		}


		public List<SendTimeVO> getSendTime() {
			return sendTime;
		}

		public void setSendTime(List<SendTimeVO> sendTime) {
			this.sendTime = sendTime;
		}

		public List<BookTimeVO> getBookTime() {
			return bookTime;
		}

		public void setBookTime(List<BookTimeVO> bookTime) {
			this.bookTime = bookTime;
		}


		public String getOptionalCouponTitle() {
			return optionalCouponTitle;
		}

		public void setOptionalCouponTitle(String optionalCouponTitle) {
			this.optionalCouponTitle = optionalCouponTitle;
		}
		
		
	}
	
	/**
	 * 下单特殊选择VO
	 * @author Hins
	 * @date 2015年7月28日 上午11:08:55
	 */
	public static class FeatureVO implements Serializable {

		private static final long serialVersionUID = -604178032645413784L;

		/** 选择类型 口味等 */
		private String name;

		/** 可选项 */
		private List<Object> listValues;

		/** 选中值 */
		private String value;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Object> getListValues() {
			return listValues;
		}

		public void setListValues(List<Object> listValues) {
			this.listValues = listValues;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
	
	/**
	 * 订单预下单接口-结算结果提示VO
	 * 
	 * @author Hins
	 * @date 2015年11月13日 上午10:31:37
	 */
	public static class SettleMessageVO implements Serializable {

		private static final long serialVersionUID = 3928199375647610892L;

		/**
		 * 能否下单:1-可以，其他不可以下单
		 */
		private int resultCode = 1;

		/**
		 * 不可以下单的提示语
		 */
		private String message;

		/**
		 * 美食库存信息
		 */
		private List<DishSurplusVO> dishSurplus;

		/** 菜品明细-v1.1.0-预订类餐厅设计方案 调整 */
		private List<DishDetailVO> dishItem;

		public SettleMessageVO(int resultCode) {
			this.resultCode = resultCode;
		}

		public SettleMessageVO(int resultCode, String message) {
			this.resultCode = resultCode;
			this.message = message;
		}

		public int getResultCode() {
			return resultCode;
		}

		public void setResultCode(int resultCode) {
			this.resultCode = resultCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public List<DishSurplusVO> getDishSurplus() {
			return dishSurplus;
		}

		public void setDishSurplus(List<DishSurplusVO> dishSurplus) {
			this.dishSurplus = dishSurplus;
		}

		public List<DishDetailVO> getDishItem() {
			return dishItem;
		}

		public void setDishItem(List<DishDetailVO> dishItem) {
			this.dishItem = dishItem;
		}

	}

}
