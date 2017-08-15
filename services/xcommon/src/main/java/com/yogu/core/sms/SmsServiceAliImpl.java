package com.yogu.core.sms;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NoHttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.core.KeyValue;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.StringUtils;
import com.yogu.commons.utils.encrypt.HMacMD5;
import com.yogu.commons.utils.encrypt.MD5Util;
import com.yogu.core.enums.SmsConstant;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

/**
 * 阿里大鱼短信测试demo<br>
 * 错误码对照表：https://doc.alidayu.com/docs/doc.htm?spm=0.0.0.0.uQAJOf&treeId=136&articleId=104495&docType=1
 *
 * @date 2016年7月22日 上午10:38:53
 * @author hins
 */
public class SmsServiceAliImpl extends AbstractSmsServiceImpl{
	
	private static final Logger logger = LoggerFactory.getLogger(SmsServiceAliImpl.class);
	
	private HashMap<String, String> params = new HashMap<String, String>(14);	// 封签参数

	private static final int MAX_SEND_PHONE_NUMBER = 200;	// 一次性最大发送号码数量
	
	private static KeyValue<String, String> format = new KeyValue<String, String>("format", "json");		// 响应格式。默认为xml格式，可选值：xml，json。
	
	private static KeyValue<String, String> method = new KeyValue<String, String>("method", "alibaba.aliqin.fc.sms.num.send");		// API接口名称
	
	private static KeyValue<String, String> version = new KeyValue<String, String>("v", "2.0");	// API协议版本，可选值：2.0。
	
	private static KeyValue<String, String> signMethod = new KeyValue<String, String>("sign_method", "md5");	// 签名的摘要算法
	
	// ----------------- 业务请求参数名 -------------------
	private static KeyValue<String, String> smsType = new KeyValue<String, String>("sms_type", "normal");	// 短信类型，传入值请填写normal
	
	private static KeyValue<String, String> smsFreeSignName = new KeyValue<String, String>("sms_free_sign_name", "米星科技");	// 短信签名
	
	public SmsServiceAliImpl() {
		super(SmsConfig.SMS_INSTANCE_ALI_DAYU, "【米星科技】");
	}
	
	public static void main(String[] args) {
		SmsServiceAliImpl sms = new SmsServiceAliImpl();
		sms.mobile("13926426236").mazingTemplate(SmsConstant.SMS_CODE).param("code", "123456").send();
		
	}

	@Override
	protected boolean doSend() {

		// 初始化请求参数
		initParams();

		// 防止一次性接收的号码>200个，所以不抛出异常
		int index = 0, i;
		String[] mobileArray = mobile.split(",");
		boolean result = true;
		do {

			StringBuffer tmpPhone = new StringBuffer(); // 截取最多200个号码
			for (i = index; i < MAX_SEND_PHONE_NUMBER && i < mobileArray.length; i++)
				tmpPhone.append(mobileArray[i]).append(",");

			params.put("rec_num", tmpPhone.substring(0, tmpPhone.length() - 1));

			// 生成sign
			createSign();

			// 发送短信
			if (!sendReq() && result)
				result = false;

			index = +MAX_SEND_PHONE_NUMBER;
			tmpPhone.setLength(0);
		} while (mobileArray.length > index);
		
		// 如果批量发，永远返回true
		return mobileArray.length > 1 ? true : result;
	}
	
	/**
	 * 调用发送短信接口
	 * 
	 * @author hins
	 * @date 2016年7月25日 下午12:01:48
	 * @return boolean
	 */
	private boolean sendReq() {
		// 发送短信
		try {
			String res = HttpClientUtils.doPost("http://gw.api.taobao.com/router/rest", params);

			logger.info("sms#alidayu | 调用发送短信接口结果 | mobile: " + mobile + ", res: " + res);
			
			return res.indexOf("\"success\":true") > 0 ? true : false;

		} catch (Exception e) {
			if (e instanceof NoHttpResponseException) {
				logger.error("sms#alidayu | 调用发送短信接口失败 | mobile: " + mobile + ", e: ", e);
				throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信失败，请重试");
			}
			throw e;
		}
	}
	
	/**
	 * 初始化请求参数（不参与生成sign）
	 * 
	 * @author hins
	 * @date 2016年7月22日 下午6:27:53
	 * @return void
	 */
	private void initParams() {
		String templateId = getTemplateId();
		// 生成参数
		params.put("app_key", "23391902");
		params.put(method.getKey(), method.getValue());
		params.put("timestamp", DateUtils.formatDate(new Date(), DateUtils.YYYY_MM_DD_HH_mm_ss));
		
		params.put(format.getKey(), format.getValue());
		params.put(version.getKey(), version.getValue());
		params.put(signMethod.getKey(), signMethod.getValue());

		params.put(smsType.getKey(), smsType.getValue());
		params.put(smsFreeSignName.getKey(), smsFreeSignName.getValue());
		params.put("sms_template_code", templateId);
//		params.put("sms_template_code", "SMS_12585162");

		params.put("sms_param", keyValuesToJson());
	}
	
	/**
	 * 通过请求参数，生成sign
	 * 
	 * @author hins
	 * @date 2016年7月22日 下午6:28:39
	 */
	private void createSign() {
		// 加密算法
		String smethod = signMethod.getValue();

		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		if ("md5".equals(smethod)) {
			query.append("9d50562625a1058914b1a3e502cf0fcd");
		}

		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.isNotBlank(value)) {
				query.append(key).append(value);
			}
		}

		// 第三步：使用MD5/HMAC加密(同时已经转成十六进制了)
		String sign = null;
			
		if ("hmac".equals(smethod)) {
			sign = HMacMD5.getSignature(query.toString(), "9d50562625a1058914b1a3e502cf0fcd");
		} else {
			query.append("9d50562625a1058914b1a3e502cf0fcd");
			sign = MD5Util.getMD5String(query.toString());
		}
		params.put("sign", sign);

	}
	
	/**
	 * 将自定义参数转成json，用于装载发送短信的sms_param请求参数
	 * 
	 * @author hins
	 * @date 2016年7月22日 下午6:51:08
	 * @return String
	 */
	private String keyValuesToJson(){
		
		Map<String, String> tmp = new HashMap<>(keyValues.size());
		
		for (KeyValue<String, String> keyValue : keyValues) {
			tmp.put(keyValue.getKey(), keyValue.getValue());
        }
		
		return JsonUtils.toJSONString(tmp);
		
	}
	
}
