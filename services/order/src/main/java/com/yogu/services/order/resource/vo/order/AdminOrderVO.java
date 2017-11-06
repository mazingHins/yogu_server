package com.yogu.services.order.resource.vo.order;

import com.yogu.services.order.base.dto.Order;

/**
 * 内部管理订单VO
 *
 * @date 2016年8月16日 下午4:43:18
 * @author hins
 */
public class AdminOrderVO extends Order{

	private static final long serialVersionUID = 1090532315950744935L;
	
	private String passport;

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}
	
}
