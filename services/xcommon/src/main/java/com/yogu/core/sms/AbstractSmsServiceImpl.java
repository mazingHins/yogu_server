package com.yogu.core.sms;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.commons.cache.redis.Redis;
import com.yogu.commons.core.KeyValue;
import com.yogu.commons.server.context.MainframeBeanFactory;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.cfg.DesPropertiesEncoder;
import com.yogu.core.remote.config.ConfigRemoteService;

/**
 * @author ThinkPad 2015/12/4.
 */
abstract public class AbstractSmsServiceImpl implements SmsService {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractSmsServiceImpl.class);
    
    public static final String SMS_ID_KEY_PREFIX = "sms_id_key_prefix:";
    
    /** 发送报告的每次请求数 */
    public static final int PAGE_SIZE = 100;
    
    public static final int EXPIRE_TIME = 60 * 60; // 1小时, 单位秒
    
    protected Redis redis;
    
    protected Redis redis() {
    	if (null == redis)
			redis = (Redis) MainframeBeanFactory.getBean("redis");
		return redis;
    }

    /**
     * 手机号码，多个号码以 英文逗号 隔开
     */
    protected String mobile = "";

    /**
     * 在运营商的模版ID
     */
    protected String templateId = "";

    /**
     * 在米星的模版ID
     */
    protected String mazingTemplate = "";

    protected List<KeyValue<String, String>> keyValues = new LinkedList<>();

    /**
     * 模板的配置信息，格式
     * {
     *     "sp1" : "id1",
     *     "sp2" : "id2",
     *     "text" : "模板文字，不包含签名，例如：你的订单 {0} 已经在配送途中，请保持电话畅通"
     * }
     */
    private Map<String, String> templateSetting = null;

    /**
     * 哪个供应商
     */
    protected int sp = 0;

    /**
     * SP 短信签名
     */
    protected String signName;

    private DesPropertiesEncoder decoder = new DesPropertiesEncoder();

    // 成功、失败的计数，非线程安全
    private static final LRUMap COUNTER = new LRUMap(32);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");

    // 非法号码的正则
    private static final Pattern PHONE_PATTERN =  Pattern.compile("^((120)|(140))\\d{8}$");

    public AbstractSmsServiceImpl(int sp, String signName) {
        this.sp = sp;
        this.signName = signName;
    }

    @Override
    public SmsService mazingTemplate(String mazingTemplate) {
        if (StringUtils.isBlank(mazingTemplate)) {
            throw new IllegalArgumentException("mazingTemplate不能为空");
        }
        this.mazingTemplate = mazingTemplate;
        return this;
    }

    @Override
    public SmsService mobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            throw new IllegalArgumentException("mobile不能为空");
        }
        this.mobile = mobile;
        return this;
    }

    @Override
    public SmsService param(String name, String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("value不能为空");
        }
        keyValues.add(new KeyValue(name, value));
        return this;
    }

    @Override
    public String encode(String value) {
        return decoder.encode(value);
    }

    public String getSignName() {
        return signName;
    }

    /**
     * 返回参数值列表
     * @return 返回不为null的结果
     */
    protected String[] getValues() {
        String[] args = new String[keyValues.size()];
        int index = 0;
        for (KeyValue<String, String> keyValue : keyValues) {
            args[index++] = keyValue.getValue();
        }
        return args;
    }

    /**
     * 返回参数名列表
     * @return 返回不为null的结果
     */
    protected String[] getKeys() {
        String[] args = new String[keyValues.size()];
        int index = 0;
        for (KeyValue<String, String> keyValue : keyValues) {
            args[index++] = keyValue.getKey();
        }
        return args;
    }

    /**
     * 返回当前 SP 的模板ID，可能为空（null, ""）
     * @return
     */
    protected String getTemplateId() {
        String templateId = getTemplateSetting().get(sp + "");
        return templateId;
    }

    protected Map<String, String> getTemplateSetting() {
        if (templateSetting == null) {
            String configValue = ConfigRemoteService.getConfig(SmsConfig.SMS_TEMPLATE_GROUP, mazingTemplate);
            if (StringUtils.isBlank(configValue)) {
                throw new IllegalStateException("mazingTemplate没有设置，或者系统没有这个短信模板：" + mazingTemplate);
            }
            Map<String, String> map = JsonUtils.parseObject(configValue, new TypeReference<Map<String, String>>() {
            });
            templateSetting = map;
        }
        return templateSetting;
    }

    /**
     * 返回模板的文字，可能为空（null, ""）
     * @return
     */
    protected String getText() {
        return getTemplateSetting().get("text");
    }

    /**
     * 获取短信发送文本，已处理过占位符，处理带单引号短信模版发送不出去问题
     * @return    
     * @author east
     * @date 2017年1月16日 下午3:16:51
     */
    protected String getTemplateText() {
		String text = getText();
		//处理带有单引号的文本，MessageFormat.format会对单引号吃掉，所以要对单引号做转义，it''s=it's
    	if(text.contains("'"))
    		text = text.replace("'", "''");
        String templateText = MessageFormat.format(text, getValues());
        return templateText;
	}
    
    /**
     * 返回 appKey
     * @return
     */
    protected String getAppKey() {
        return ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP, SmsConfig.SMS_CONFIG_APP_KEY + sp);
    }

    @Override
    public final boolean send() {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }

        boolean success = doSend();
        dealResult(success);
        return success;
    }
    
    @Override
    public String queryStatus() {
    	return "";
    }
    
    @Override
	public final String filterErrorPhone(String phone) {
    	if (StringUtils.isBlank(phone)) {
			return null;
		}
		String[] phoneArray = phone.split(",");

		StringBuilder phoneSB = new StringBuilder();
		for (String p : phoneArray) {
			if (PHONE_PATTERN.matcher(p).find())
				continue;
			phoneSB.append(p).append(",");
		}
		
		String result = phoneSB.toString();
		if (StringUtils.isBlank(result)) {
			return result;
		} else {
			return result.substring(0, result.length() - 1);
		}
	}

    /**
     * 处理结果
     * @param success
     */
    private synchronized void dealResult(boolean success) {
        String today = SDF.format(new Date());
        String key = null;
        if (success) {
            // 对成功次数做限制
            key = today + "_succ";
        }
        else {
            // 对失败次数做限制
            key = today + "_fail";
        }
        Integer count = (Integer) COUNTER.get(key);
        if (count == null) {
            count = Integer.valueOf(1);
            COUNTER.put(key, count);
        }
        else {
            count = count + 1;
            COUNTER.put(key, count);
        }
        if ((count % 5000) == 0) {
            // DO SOMETHING
            logger.error("sms#base#dealResult | SMS_WARN 发送短信的数量过大 | today: {}, count: {}, key: {}", today, count, key);
        }
    }

    /**
     * 执行发送的逻辑
     * @return
     */
    abstract protected boolean doSend();

    /**
     * 返回
     * @return
     */
    protected String getAppSecret() {
        return ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP, SmsConfig.SMS_CONFIG_SECRET_KEY + sp);
    }
    
    /**
     * 尝试替换短信内容中敏感词<br>
     * 返回替换后的短信内容
     * 
     * @param text - 待发送的短信内容
     * @author hins
     * @date 2016年7月27日 下午6:25:53
     * @return 替换后的短信内容（如果没敏感词，则原值返回）
     */
    protected String replaceSensitive(String text) {
//		String sensitive = "[{\"sensitive\": \"好色\",\"replace\": \"haose\"}]";
		String sensitive = ConfigRemoteService.getConfig(SmsConfig.SMS_SETTING_GROUP, SmsConfig.SMS_CONFIG_SENSITIVE);
		if(StringUtils.isBlank(sensitive))
			return text;
		try {
			List<SensitiveMpBean> slist = JsonUtils.parseArray(sensitive, SensitiveMpBean.class);
			
			for(SensitiveMpBean mp : slist){
				text = text.replaceAll(mp.getSensitive(), mp.getReplace());
			}
			
		} catch (Exception e) {
			logger.error("sms#base#replaceSensitive | 替换敏感词失败 | mobile: {}, text: {}", mobile, text, e);
		}
		
		return text;

	}
}
