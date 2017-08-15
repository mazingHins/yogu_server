package com.yogu.core.sms;

/**
 * 短信服务定义。
 * 只可以通过模板发送短信
 * @author ten 2015/12/2.
 */
public interface SmsService {

    /**
     * 设置米星的模版ID
     * @param mazingTemplate
     * @return SmsService
     */
    SmsService mazingTemplate(String mazingTemplate);

    /**
     * 设置要发送的手机号码，多个号码用英文逗号隔开
     * @param mobile 手机号码
     * @return SmsService
     */
    SmsService mobile(String mobile);

    /**
     * 设置短信模版变量，可以多次调用，每次调用表示一个参数
     * @param name 变量名，可以为空 ("")
     * @param value 变量值，不能为空
     * @return SmsService
     */
    SmsService param(String name, String value);

    /**
     * 加密值
     * @param value 要加密的值
     * @return 返回加密后的值
     */
    String encode(String value);

    /**
     * 发送短信，方式：同步，如果网络比较慢可能会卡住线程
     * @return 成功返回 true
     */
    boolean send();
    
    /**
     * 过滤错误的手机号码，并将过滤的结果返回，该方法支持批量<br>
     * 如果批量过滤，每个号码必须用英文逗号分隔<br>
     * 120xxxxxxxx,131xxxxxxxx（若第一个是错误号码，返回值131xxxxxxxx）
     * 
     * @author Hins
     * @date 2016年1月13日 下午3:39:29
     * 
     * @param phone - 原手机号码
     * @return 过滤结果，可能为null
     */
    String filterErrorPhone(String phone);
    
    /**
     * 批量查询短信发送状态
     * @return
     */
    String queryStatus();

}
