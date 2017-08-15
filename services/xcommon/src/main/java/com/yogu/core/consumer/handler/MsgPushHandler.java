package com.yogu.core.consumer.handler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.consumer.MQHandler;
import com.yogu.core.consumer.MQHandlerConstants;
import com.yogu.core.consumer.handler.bean.MsgPushBean;
import com.yogu.core.web.ParameterUtil;

/**
 * MQ处理推送的模块
 * 
 * @author felix
 */
public class MsgPushHandler implements MQHandler {

	private static final Logger logger = LoggerFactory.getLogger(MsgPushHandler.class);

	@Override
	public void execute(Map<String, Object> params) {
		logger.info("MQ#MsgPushHandler | 进入MQ handler处理方法 | params: {}", JsonUtils.toJSONString(params));

		MsgPushBean bean = (MsgPushBean) params.get(MQHandlerConstants.MESSAGE_PUSH);

		logger.info("MQ#MsgPushHandler | 进入MQ handler参数校验");

		if (null == bean) {
			logger.warn("MQ#MsgPushHandler | 参数bean为空, 跳过处理");
			return;
		}
		ParameterUtil.assertNotBlank(bean.getUids(), "推送的用户不能为空");
		ParameterUtil.assertNotBlank(bean.getMsg(), "推送模板类型不能为空");
		ParameterUtil.assertNotBlank(bean.getTitle(), "推送标题不能为空");

		logger.info("MQ#MsgPushHandler | MQ handler参数校验成功");

		// 将参数塞入模板
		String message = null == bean.getMsgTemplateParams() ? bean.getMsg() : MessageFormat.format(bean.getMsg(), bean
				.getMsgTemplateParams().toArray());
		
		Map<String, Object> mazingFixedFields = new HashMap<String, Object>();
		mazingFixedFields.put("msgId", bean.getMsgId());
		mazingFixedFields.put("type", bean.getMsgType().getValue());
		
		if (null != bean.getMsgParams()) {
			mazingFixedFields.put("params", bean.getMsgParams());
		}
		
		logger.info("MQ#MsgPushHandler | MQ handler执行推送发送");
		
		sendMsg(message, bean.getTitle(), bean.getSound(), bean.getUids(), mazingFixedFields);
		
		logger.info("MQ#MsgPushHandler | MQ handler执行短信发送完毕");
	}
	
	/**
	 * 封装参数, 调用推送API
	 * 
	 * @param msg 消息本体
	 * @param uids 用户ID（目前该push业务只推单个用户）
	 * @param customFields 自定义消息字段
	 */
	private void sendMsg(String msg, String title, String sound, String uid, Map<String, Object> customFields) {
		String url = CommonConstants.USER_DOMAIN + "/api/user/push.do";

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("msg", msg);
		params.put("title", title);
		params.put("sound", null == sound ? "" : sound);
		params.put("uid", uid);
		params.put("customFields", customFields);

		String paramString = JsonUtils.toJSONString(params);
		Map<String, Object> postParam = new HashMap<String, Object>();
		postParam.put("params", paramString);

		HttpClientUtils.doPost(url, postParam);
	}

}
