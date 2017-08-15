package com.yogu.core.consumer.handler;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.consumer.MQHandler;
import com.yogu.core.consumer.MQHandlerConstants;
import com.yogu.core.consumer.handler.bean.SmsNotifyBean;
import com.yogu.core.sms.SmsService;
import com.yogu.core.sms.SmsServiceFactory;
import com.yogu.core.web.ParameterUtil;
import com.yogu.core.web.RestResult;
import com.yogu.core.web.ResultCode;

/**
 * MQ处理短信发送的模块
 * 
 * @author felix
 */
public class SmsNotifyHandler implements MQHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(SmsNotifyHandler.class);

	@Override
	public void execute(Map<String, Object> params) {
		logger.info("MQ#SmsNotifyHandler | 进入MQ handler处理方法 | params: {}", JsonUtils.toJSONString(params));
		
		SmsNotifyBean bean = (SmsNotifyBean) params.get(MQHandlerConstants.SMS_NOTIFY);
		
		logger.info("MQ#SmsNotifyHandler | 进入MQ handler参数校验");
		
		if (null == bean) {
			logger.warn("MQ#SmsNotifyHandler | 参数bean为空, 跳过处理");
			return;
		}
		
		ParameterUtil.assertNotBlank(bean.getUids(), "短信发送的用户不能为空");
		ParameterUtil.assertNotBlank(bean.getSmsType(), "短信模板类型不能为空");
		
		logger.info("MQ#SmsNotifyHandler | MQ handler参数校验成功");
		
//		String smsTemplate = getSmsTemplate(bean.getSmsType());
		
		// 将参数塞入模板
//		if (null != bean.getSmsParams() && bean.getSmsParams().size() > 0) {
//			smsTemplate = MessageFormat.format(smsTemplate, bean.getSmsParams().toArray());
//		}
		
		logger.info("MQ#SmsNotifyHandler | 获取用户信息");
		// 获取用户信息并拼装成字符串用英文逗号分隔
		List<Map<String, String>> result = getUserProfileByUids(bean.getUids());
		
		StringBuilder sbBuilder = new StringBuilder();
		if (null != result && result.size() > 0){
			for (Map<String, String> item : result){
				sbBuilder.append(",").append(item.get("phone"));
			}
		}
		String mobiles = sbBuilder.length() > 0 ?  sbBuilder.substring(1) : "";
		
		logger.info("MQ#SmsNotifyHandler | MQ handler执行短信发送");
		
		// 获取短息服务 SmsService 2015-12-08
		SmsService smsService = SmsServiceFactory.getInstance().mazingTemplate(bean.getSmsType()).mobile(mobiles);
		for (int i = 0; i < bean.getSmsParams().size(); i++) {
			// 逐一添加模板参数
			smsService.param("param" + i, bean.getSmsParams().get(i));
		}
		smsService.send();
		
		logger.info("MQ#SmsNotifyHandler | MQ handler执行短信发送完毕");
	}
	
//	private String getSmsTemplate(String smsType){
//		try {
//			String smsTemplate = ConfigRemoteService.getConfig(SmsConstant.GROUP, smsType);
//			return StringUtils.isNotBlank(smsTemplate) ? smsTemplate : "";
//		} catch (Exception e) {
//			logger.error("MQ#SmsNotifyHandler | 获取短信模板失败 | smsTemplate: {}", smsType);
//		}
//		return "";
//	}
	
	
	/**
	 * 远程访问user, 根据userId 获取用户信息, 如电话, 国家码
	 * 
	 * @param uids 用户ID, 可多个
	 * @return
	 */
	private List<Map<String, String>> getUserProfileByUids(String uids) {
		try {
			String json = HttpClientUtils.doGet(CommonConstants.USER_DOMAIN + "/api/v1/user/listInfo?uids=" + uids);

			RestResult<List<Map<String, String>>> result = JsonUtils.parseObject(json, new TypeReference<RestResult<List<Map<String, String>>>>() {
			});
			if (result.getCode() != ResultCode.SUCCESS){
				return Collections.<Map<String, String>>emptyList();
			}
			return result.getObject();
		} catch (Exception e) {
			logger.error("SmsSenderConsumer#consume | get user information error | uids: {}, message: {}", uids, e.getMessage(), e);
		}
		return Collections.<Map<String, String>>emptyList();
	}

}
