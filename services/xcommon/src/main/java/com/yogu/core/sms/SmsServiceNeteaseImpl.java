package com.yogu.core.sms;

import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.core.web.ResultCode;
import com.yogu.core.web.exception.ServiceException;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 网易的短信接口实现
 * @author ten 2015/12/2.
 */
public class SmsServiceNeteaseImpl extends AbstractSmsServiceImpl {

//    private static final Logger logger = LoggerFactory.getLogger(SmsServiceNeteaseImpl.class);

    private static final String URL = "https://api.netease.im/sms/sendtemplate.action";

    public SmsServiceNeteaseImpl() {
        super(SmsConfig.SMS_INSTANCE_NETEASE, "【米星】");
    }


    // 计算并获取CheckSum
    private static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }

    /**
     * 加密
     * @param algorithm
     * @param value
     * @return
     */
    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    @Override
    public boolean doSend() {
        String templateId = getTemplateId();
        if (templateId == null) {
            throw new ServiceException(ResultCode.FAILURE, SmsConfig.getSpName(sp) + "templateId在系统里没有配置");
        }
        Map<String, String> header = new HashMap<>(8);
        String nonce = UUID.randomUUID().toString();
        String time = (System.currentTimeMillis() / 1000) + "";
        String checkSum = getCheckSum(getAppSecret(), nonce, time);
        header.put("AppKey", getAppKey());
        header.put("Nonce", nonce);
        header.put("CurTime", time);
        header.put("CheckSum", checkSum);

        String[] args = getValues();
        Map<String, String> params = new HashMap<>(4);
        params.put("templateid", templateId);
        mobile = filterErrorPhone(mobile);
		if (StringUtils.isBlank(mobile)) {
			return false;
		}
        params.put("mobiles", JsonUtils.toJSONString(mobile.split(",")));
        
        // 尝试替换敏感词
        params.put("params", replaceSensitive(JsonUtils.toJSONString(args)));
        String str = StringUtils.trimToEmpty(HttpClientUtils.doPost(URL, 5000, header, params, "utf-8", "utf-8"));
        // 简单处理
        // 成功：{ "code":200, "msg":"this is for errmsg!", "obj":"成功数量" }
        logger.info("sms#netease | 发送短信结果 | mobile: {}, templateId: {}, sms result: {}, encryptmobile: {}",
                LogUtil.hidePassport(mobile), templateId, str, LogUtil.encrypt(mobile));

        return str.indexOf("200") > 0;
    }

    public static void main(String[] args) {
//        SmsService smsService = new SmsServiceNeteaseImpl();
//        smsService.mobile("18620075025,18122498580,12013114225").param("0", "151232353757237").send();
    }
}
