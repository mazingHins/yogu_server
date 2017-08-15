package com.yogu.core.sms;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信配置
 * @author ten 2015/12/2.
 */
public class SmsConfig {

    /**
     * sms 模板在 Config 的 groupCode
     */
    public static final String SMS_TEMPLATE_GROUP = "smsTemplate2";

    /**
     * sms 配置(key, secret)在 Config 的 groupCode
     */
    public static final String SMS_SETTING_GROUP = "smsSetting";
    
    /**
     * 同一手机号码，同一业务下发送几次验证码后，动态切换运营商key
     */
    public static final String SMS_SETTING_SERVICE_CHANGE_LIMIT_CODE_SEND_COUNT = "smsChangeCodeSendCount";
    /**
     * 多少秒内达到验证码发送次数限制（建议7分钟 也就是 420s） key
     */
    public static final String SMS_SETTING_SERVICE_CHANGE_VALID_TIME = "smsChangeValidTimeSecond";
    

    /**
     * configKey: app key
     */
    public static final String SMS_CONFIG_APP_KEY = "accesskey_";

    /**
     * configKey: app secret
     */
    public static final String SMS_CONFIG_SECRET_KEY = "accesssecret_";
    
    /**
     * configKey: 短信敏感词库
     */
    public static final String SMS_CONFIG_SENSITIVE = "sms_sensitive";

    /**
     * 短信实例在redis的key
     * @author ten 2015/12/2
     */
    public static final String SMS_INSTANCE_KEY = "sms_instance_key";

    /**
     * 短信实例：云片网
     * @author ten 2015/12/2
     */
    public static final int SMS_INSTANCE_YUN_PIAN = 1;

    /**
     * 短信实例：网易
     * @author ten 2015/12/2
     */
    public static final int SMS_INSTANCE_NETEASE = 2;

    /**
     * 短信实例：云片网营销实例
     * @author ten 2016/1/26
     */
    public static final int SMS_INSTANCE_YUN_PIAN_MARKETING = 3;
    
    /**
     * 短信实例：阿里大鱼
     */
    public static final int SMS_INSTANCE_ALI_DAYU = 4;

    /**
     * 所有的短信实例列表
     */
    public static final int[] SMS_ALL_INSTANCE = new int[] {SMS_INSTANCE_YUN_PIAN, SMS_INSTANCE_NETEASE};
    
    /**
     * 短信验证码发送渠道配置的key
     */
    public static final String SMS_CODE_CHANNEL = "sms_code_channel";

    /**
     * 当前的短信提供商，默认是云片网
     */
    static volatile int currentServiceProvider = SmsConfig.SMS_INSTANCE_YUN_PIAN;

    private static final Map<Integer, String> spName = new HashMap<>(4);

    static {
        spName.put(Integer.valueOf(SmsConfig.SMS_INSTANCE_YUN_PIAN), "云片网(" + SmsConfig.SMS_INSTANCE_YUN_PIAN + ")");
        spName.put(Integer.valueOf(SmsConfig.SMS_INSTANCE_NETEASE), "网易(" + SmsConfig.SMS_INSTANCE_NETEASE + ")");
    }

    /**
     * 获取供应商名称
     * @param sp
     * @return
     */
    public static String getSpName(int sp) {
        String name = spName.get(Integer.valueOf(sp));
        if (name == null) {
            name = "(未知)";
        }
        return name;
    }

    /**
     * 返回当前的短信供应商名称
     * @return
     */
    public static String getCurrentSpName() {
        return getSpName(currentServiceProvider);
    }
}
