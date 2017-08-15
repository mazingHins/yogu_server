package com.yogu.commons.sdk.user;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.utils.encrypt.AESUtil;
import com.yogu.commons.utils.encrypt.HMacSHA1;

import javax.servlet.http.HttpServletRequest;

/**
 * web 方式使用 cookie 保存登录信息时的 context
 * @author ten 2016/4/10.
 */
public abstract class MazingWebContext {

    protected static final Logger logger = LoggerFactory.getLogger(MazingWebContext.class);

    // cookie过期默认时间：14天 （单位: 秒）
    protected static final int DEFAULT_COOKIE_TIMEOUT = 14 * 24 * 3600;

    /**
     * 上一个周期的cookie密钥
     */
    protected static String encryptKey = "";
    /**
     * 最新的cookie密钥
     */
    protected static String encryptKey2 = "";

    /**
     * cookie的过期时间，单位：秒
     */
    protected static int cookieTimeout = DEFAULT_COOKIE_TIMEOUT;

    public MazingWebContext() {
    }

    /**
     * 设置 cookie 加密的 key
     * @param key1 上一个周期的cookie密钥
     * @param key2 最新的cookie密钥
     */
    public void setEncryptKey(String key1, String key2) {
        encryptKey = key1;
        encryptKey2 = key2;
    }

    public boolean isKeyEmpty() {
        return StringUtils.isEmpty(encryptKey2);
    }

    /**
     * 设置 cookie 过期的时间
     * @param timeout 过期时间，单位：秒
     */
    public void setCookieTimeout(int timeout) {
        if (timeout >= 0) {
            cookieTimeout = timeout;
        }
    }

    /**
     * 加载cookie 的内容到缓存
     * @param request
     */
    abstract public void load(HttpServletRequest request);

    /**
     * 解密 cookie
     * @param value
     * @return array[0]=返回解密的内容，array[1]=用的是哪个密钥解密
     */
    protected static String[] decrypt(String value) {
        // 用新密钥解密
        try {
            String context = AESUtil.decrypt(encryptKey2, value);
            return new String[] {context, encryptKey2};
        } catch (Exception e) {
            logger.error("mazing#sdk#login | 解密cookie失败，降尝试第二次");
        }

        // 新密钥失败，尝试用旧密钥解密
        if (StringUtils.isNotEmpty(encryptKey)) {
            try {
                String context = AESUtil.decrypt(encryptKey, value);
                return new String[]{context, encryptKey};
            } catch (Exception e) {
                logger.error("mazing#sdk#login | 第二次解密cookie失败，错误的 cookie 数据", e);
            }
        }
        return null;
    }

    /**
     * 把 cookie 内容签名
     * @param context
     * @return
     */
    protected static String getContextSign(String context, String key) {
        String sign = HMacSHA1.getSignature(context, key);
        return sign;
    }
}
