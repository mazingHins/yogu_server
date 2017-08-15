package com.yogu.core.sms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.NoHttpResponseException;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.StringUtils;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;


/**
 * 云片网发送短信的实现
 * https://www.yunpian.com/api/sms.html#c1
 * @author ten 2015/12/2.
 */
public class SmsServiceYunpianImpl extends AbstractSmsServiceImpl {

//    private static final Logger logger = LoggerFactory.getLogger(SmsServiceYunpianImpl.class);

    // 通用发送接口的http地址
//    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";
    
    /** 获取发送报告的接口 */
    private static String URI_QUERY_SMS_STATUS = "https://sms.yunpian.com/v1/sms/pull_status.json";
    
    private static final Integer TIME_OUT = 5000;// 请求超时时间

    public SmsServiceYunpianImpl() {
        super(SmsConfig.SMS_INSTANCE_YUN_PIAN, "【米星科技】");
    }

    @Override
    public boolean doSend() {
    	String templateText = getTemplateText();

        // 把签名加上
        String text = getSignName() + templateText;

        // 单个
        if (mobile.indexOf(",") < 0)
            return sendSingle(mobile, text, true);

        // 批量
        return sendBatchSms(mobile, text);
    }
    
    @Override
    public String queryStatus() {
    	Map<String, String> params = new HashMap<>(4);
        params.put("apikey", getAppKey());
        params.put("page_size", PAGE_SIZE + "");
        String str = "";
        try {
        	str = HttpClientUtils.doPost(URI_QUERY_SMS_STATUS, TIME_OUT, params, "UTF-8", "UTF-8");
        } catch (Exception e) {
        	logger.error("sms#yunpian | 查询发送报告失败 | pageSize: {}", PAGE_SIZE, e);
            throw new ServiceException(ResultCode.PARAMETER_ERROR, "查询发送报告失败");
        }
        return str;
    }

    /**
     * 发短信给单个用户
     * @param mobile 手机号码
     * @param text 文本
     * @param throwException 是否抛出异常
     * @return true=发送成功，false=发送失败
     */
    private boolean sendSingle(String mobile, String text, boolean throwException) {
    	// 尝试替换敏感词
    	text = replaceSensitive(text);
    	
        Map<String, String> params = new HashMap<>(4);
        params.put("apikey", getAppKey());
        params.put("text", text);
        mobile = filterErrorPhone(mobile);
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
        params.put("mobile", mobile);
        String str = null;
        try {
            str = HttpClientUtils.doPost(URI_SEND_SMS, TIME_OUT, params, "UTF-8", "UTF-8");
        } catch (Exception e) {
            if (throwException) {
                if (e instanceof NoHttpResponseException) {
                    logger.error("sms#yunpian | 调用发送短信接口失败 | mobile: " + mobile + ", text: " + text);
                    throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信失败，请重试");
                }
                throw e;
            }
            return false;
        }

        logger.info("sms#yunpian | 发送短信结果 | mobile: {}, text: {}, sms result: {}, encryptmobile: {}",
                LogUtil.hidePassport(mobile), text, str, LogUtil.encrypt(mobile));
        if (str != null) {
        	// 将结果保存到缓存中
        	logResultToRedis(str);
            if (str.indexOf("\"code\":0") >= 0) {
                // 简单处理，不转换成Map判断了
                return true;
            } else if (str.indexOf("\"code\":22") >= 0) {
                logger.error("sms#yunpian | 短信验证码发送过于频繁 | mobile: " + mobile);
                if (throwException) {
                    throw new ServiceException(ResultCode.PARAMETER_ERROR, "发送短信过于频繁，请1小时后再试");
                }
            }
        }
        return false;
    }
    
    /**
     * 将短信发送结果的sid和发送时间缓存在redis
     * @param result {"code":0,"msg":"OK","result":{"count":1,"fee":1,"sid":4827601044}}
     */
    private void logResultToRedis(String result) {
    	Map<String, Object> resultMap = JsonUtils.parseMap(result, Object.class);
    	@SuppressWarnings("unchecked")
		Map<String, Object> detailMap = (Map<String, Object>) resultMap.get("result");
        if (detailMap != null) {
            redis().set(SMS_ID_KEY_PREFIX + detailMap.get("sid"), new Date(), EXPIRE_TIME);
        }
        else {
            logger.error("sms#yunpian | 短信验证码发送失败，不能计时 | result: {}", result);
        }
    }

    /**
     * 发短信给多个用户
     * @param mobile
     * @param text
     * @return 只要有1个发送成功，就返回true；所有的都失败，才返回false
     */
    private boolean sendBatchSms(String mobile, String text) {
        String[] array = mobile.split(",");
        int i = 0;
        int successCount = 0;
        for (String str : array) {
            boolean success = sendSingle(str, text, false);
            if (success) {
                successCount++;
            }
            i++;
            if (i % 10 == 0) {
                try {
                    // 歇一下
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
        return (successCount > 0);
    }

    public static void main(String[] args) {
    	
    }
}
