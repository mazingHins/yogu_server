package com.yogu.services.order.utils;

import java.util.HashMap;
import java.util.Map;

import com.yogu.commons.utils.StringUtils;
import com.yogu.core.consumer.PushTitle;
import com.yogu.core.consumer.messageBean.CommonMsgPushBean;

/**
 * 在order域, 为推送消息准备所需参数
 * 
 * @author felix
 * @data 2015-10-16
 */
public class OrderPushMsgHandler {
	/**
	 * 准备推送所需要的数据
	 * 
	 * @param uids - 需推送的用户ID, 多个用英文逗号分隔
	 * @param type - 消息类型
	 * @param msg - 推送消息本体
	 * @param title - 消息标题,传null时默认使用DEFAULT标题
	 * @return
	 * 
	 * @author felix
	 * @date 2015/10/06
	 */
	public static CommonMsgPushBean prepareMsg(String uids, short type, String msg, String title) {
		CommonMsgPushBean bean = new CommonMsgPushBean();
		bean.setUids(uids);
		bean.setMsg(msg);
		bean.setTitle(StringUtils.isBlank(title)? PushTitle.DEFAULT_TITLE : title);
		
		bean.setCustomFields(getCustomFields(type));
		
		return bean;
	}
	
	public static Map<String, Object> getCustomFields(short type){
		Map<String, Object> customFields = new HashMap<String, Object>();
		// 暂定msgId为0, 兼容以后版本, 可能以后推送消息过长需要将部分参数放入数据库
		customFields.put("msgId", 0);
		// 推送的类型, 用于前端选择URL/跳至的页面和刷新的页面
		customFields.put("type", type);
		
		return customFields;
	}
 }
