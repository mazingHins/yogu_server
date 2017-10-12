package com.yogu.services.order.resource.vo;

import java.io.Serializable;
import java.util.List;

import com.yogu.remote.user.dto.UserAddress;
import com.yogu.services.store.GoodsOrderVO;
import com.yogu.services.store.GoodsVO;

public class OrderSettleVO {
	
	/**
	 * 能否下单:1-可以，其他不可以下单
	 */
	private int resultCode = 1;

	/**
	 * 不可以下单的提示语
	 */
	private String message;
	
	
	private SettleVO settle;
	
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

	public SettleVO getSettle() {
		return settle;
	}

	public void setSettle(SettleVO settle) {
		this.settle = settle;
	}

	/**
	 * 预下单接口-结算信息
	 *
	 * @date 2016年10月1日 下午12:59:20
	 * @author hins
	 */
	public static class SettleVO implements Serializable {

		private static final long serialVersionUID = 2842482913437830900L;

		/** 商品明细 */
		private List<GoodsOrderVO> goodsList;

		/** 订单总价格 */
		private long totalFee;

		/** 优惠总价格 */
		private long totalDiscountFee;

		/** 商品总价格 */
		private long goodsFee;

		/** 用户地址列表 */
		private List<UserAddress> address;

		/**
		 * 可用优惠券列表
		 */
		private List<SettleCouponVO> couponList;
		
		/**
		 * 客户端可用优惠券button标题
		 */
		private String optionalCouponTitle;

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

		public List<UserAddress> getAddress() {
			return address;
		}

		public void setAddress(List<UserAddress> address) {
			this.address = address;
		}

		public List<GoodsOrderVO> getGoodsList() {
			return goodsList;
		}

		public void setGoodsList(List<GoodsOrderVO> goodsList) {
			this.goodsList = goodsList;
		}

		public List<SettleCouponVO> getCouponList() {
			return couponList;
		}

		public void setCouponList(List<SettleCouponVO> couponList) {
			this.couponList = couponList;
		}

		public String getOptionalCouponTitle() {
			return optionalCouponTitle;
		}

		public void setOptionalCouponTitle(String optionalCouponTitle) {
			this.optionalCouponTitle = optionalCouponTitle;
		}
		
	}
	
	

}
