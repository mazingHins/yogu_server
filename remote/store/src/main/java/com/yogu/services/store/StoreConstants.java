package com.yogu.services.store;

import com.yogu.core.enums.merchant.StoreStatus;
import com.yogu.language.StoreMessages;

/**
 * 门店有关的常量 <br>
 * 
 * @author JFan 2015年7月20日 下午5:02:21
 */
public class StoreConstants {

	/** 新开的餐厅默认logo图片 */
	public static final String DEF_LOGO_PATH = "default/store-default-logo.png";
	/** 新开的餐厅默认topic图片 */
	public static final String DEF_TOPIC_PATH = "default/store-default-pic.jpg";

	/** 首页数据自动推送：块在同一时间内生效的个数 */
	public static final int BLOCK_EFFECTIVE_NUM = 4;

	/** 服务范围类型：半径（米） */
	public static final short SERVICE_RANGE_TYPE_DISTANCE = 1;
	/** 服务范围类型：多边形坐标 */
	public static final short SERVICE_RANGE_TYPE_POLYGON = 2;

	/** 门店人员：客服 */
	public static final short STAFF_CS = 3;

	/** 门店人员：配送员 */
	public static final short STAFF_DS = 4;

	/** 上传的图片最大值，单位 byte */
	public static final int UPLOAD_MAX_PIC_SIZE = 1024000;
	
	/** 菜品状态上架值 */
	public static final short DISH_STATUS_ON = 1;
	
	 /** 菜品状态下架值 */
	public static final short DISH_STATUS_OFF = 2;
	
	/**
	 * 门店支持现金的标志位
	 */
	public static final short SUPPORT_CASH = 1;
	
	/** 可搜索 */
	public static final short VISIBLE = 1;
	
	/** 不可搜索 */
	public static final short NON_VISIBLE = 2;
	
	/** 订单是否跳过'制作'TAG, 在简化订单流程的需求中, '制作'的TAG已经被移除*/
	public static final boolean SKIP_COOKING = true;
	
	/**
	 * store报表模版名称
	 */
	public static final String TEMPLATE_NAME = "store_report";
	
	/**
	 * 餐厅没开业的business提示语
	 */
	public static  String CHECKING_BUSINESS_MSG = StoreMessages.CHECKING_BUSINESS_MSG();
	
	/**
	 * 餐厅状态=即将开业的business提示语
	 */
	public static  String COMING_SOON_BUSINESS_MSG = StoreMessages.COMING_SOON_BUSINESS_MSG();
	
	/**
	 * 餐厅状态=休业的business提示语
	 */
	public static  String IN_REST_BUSINESS_MSG = StoreMessages.IN_REST_BUSINESS_MSG();
	
	/**
	 * 餐厅状态=结业的business提示语
	 */
	public static  String CLOSED_BUSINESS_MSG = StoreMessages.CLOSED_BUSINESS_MSG();
	
	/**
	 * 餐厅状态=冻结的business提示语
	 */
	public static  String FROST_BUSINESS_MSG = StoreMessages.FROST_BUSINESS_MSG();
	
	/**
	 * 餐厅休息中的business提示语
	 */
	public static  String OUT_BUSINESS_MSG = StoreMessages.OUT_BUSINESS_MSG();
	
	/**
	 * 运营部门的邮件组，现在暂时用于确定商家营业状态改变后，发送邮件接受者
	 */
	public static final String OPERATE_EAMIL_GROUP_KEY = "operateEamil";
	
	/**
	 * 返回餐厅是否允许被搜索到
	 */
	public static short visibility(short status) {
		return (StoreStatus.canSearch(status) ? VISIBLE : NON_VISIBLE);
	}

}
