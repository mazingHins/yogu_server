package com.yogu.services.order.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.core.enums.BooleanConstants;
import com.yogu.core.enums.merchant.RangeMoneyType;
import com.yogu.core.enums.order.OrderConstants;
import com.yogu.core.utils.ComputeUtils;
import com.yogu.core.web.OrderErrorCode;
import com.yogu.core.web.exception.ServiceException;
import com.yogu.language.OrderMessages;
import com.yogu.services.order.base.dto.vo.SendInfoVO;
import com.yogu.services.store.base.dto.StoreServiceRange;
import com.yogu.services.store.base.dto.StoreServiceRangeVO;

/**
 * 配送相关工具类
 * 
 * @author Hins
 * @date 2015年10月28日 下午3:26:40
 */
public class RangeUtils {

	private static final Logger logger = LoggerFactory.getLogger(RangeUtils.class);

	/**
	 * 计算配送费金额，并装载配送规则
	 * 
	 * @author Hins
	 * @date 2015年10月28日 下午3:33:28
	 * 
	 * @param goodsFee - 订单美食金额
	 * @param fullFreeFreight -  免运费金额（分）（小于等于0表示不做"满免"）
	 * @param money -  配送费用（分）
	 * @param moneyType - 费用类型：1-免费；2-付费；3-的士费*1；4-的士费*2；10-顺丰配送
	 * @return
	 */
	public static SendInfoVO countRange(long goodsFee, StoreServiceRangeVO range) {
		SendInfoVO info = new SendInfoVO();
		long fee = range.getMoney(), fullFreeFreight = range.getFullFreeFreight(), money = range.getMoney(); // 定义实际用户配送费，满免，配送范围定义的配送费
		short moneyType = range.getMoneyType(), isSf = range.getIsSf(); // 定义配送类型，是否满足顺丰配送
		String content = null;	// 客户端显示的配送费说明

		// modify by sky 2015-11-13 未达到满免,显示配送费(免费/20/的士费x2);达到满免, 显示免费

		// modify by sky 2016-03-11 以上需求已变更 -->
		// 1. 收取具体运费多少元，显示价钱“￥X”
		// 2. 收取的士费，“的士费*1”显示“的士费到付”，“的士费*2”显示“来回的士费到付”
		// 3. 消费达免送消费额，显示“消费满￥N免运费”，￥N为该区域的免送消费额

		boolean fullFree = false;

		if (fullFreeFreight > 0 && goodsFee >= fullFreeFreight)// 是否满足 满多少免配送费
			fullFree = true;

		if (fullFree) {// 满免
			fee = 0;
			content = isSf == BooleanConstants.TRUE ? OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_FREE()
					: OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_FULLFREE(ComputeUtils.fenToYuanInt(fullFreeFreight));// "免运费";
		} else {// 没有达到满免 或者 其它计费方式
			if (isSf == BooleanConstants.TRUE) { 
				fee = range.getSfExpress().getUserBearFee();
				content = fee < 1 ? OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_FREE() : "￥ " + ComputeUtils.fenToYuanInt(fee);
			} else if (moneyType == RangeMoneyType.TAXI_FEE.getValue()) { // 的士费*1
				content = OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_TAXIFEE();// "的士费x1";
			} else if (moneyType == RangeMoneyType.TAXI_FEE_DOUBLE.getValue()) { // 的士费*2
				content = OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_TAXIFEEDOUBLE();// "的士费x2";
			} else if (moneyType == RangeMoneyType.FREE.getValue()) { // 免运费
				content = OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_FREE();
			} else { // 付费
				content = "￥ " + ComputeUtils.fenToYuanInt(fee);
			}
		}

		if (0 > fee)
			logger.warn("order#range | 计算的配送费金额小于0 | goodsFee {}, fullFreeFreight {}, money {}, moneyType {}", goodsFee, fullFreeFreight, money, moneyType);
		fee = fee < 0 ? 0 : fee;

		info.setFee(fee);
		info.setTitle(OrderMessages.ORDER_SEND_INFO_TITLE());
		info.setContent(content);
		info.setChannel(range.getIsSf() == BooleanConstants.TRUE ? OrderMessages.SF_EXPRESS_NAME() : "");
		return info;
	}

	/**
	 * 验证配送信息<br>
	 * 验证配送范围是否为空，验证美食金额是否低于起送金额
	 * 
	 * @param fee - 美食价格
	 * @param range - 配送服务范围内容
	 */
	public static void validateRange(long fee, StoreServiceRange range) {
		if (range == null) {
			logger.error("order#service | StoreServiceRange为空");
			throw new ServiceException(OrderErrorCode.OUTSIDE_THE_SCOPE, OrderMessages.ORDER_SETTLE_QUERYSPECSURPLUS_RANGE_EMPTY());
		}
		if (fee < range.getMinimumMoney()) {// 低于起送金额
			logger.error("order#service | 菜品价格低于最低配送费 | goodsFee: {}, minimumMoney: {}", fee, range.getMinimumMoney());
			throw new ServiceException(OrderErrorCode.MINIMUM_MONEY_NOT_ENOUGH, OrderMessages.ORDER_RANGEUTILS_COUNTRANGE_FEE_MIN());
		}
	}


}
