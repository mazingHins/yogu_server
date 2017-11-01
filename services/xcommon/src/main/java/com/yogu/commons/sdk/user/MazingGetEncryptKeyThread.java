package com.yogu.commons.sdk.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yogu.commons.sdk.user.encrypt.ConfigurationDecoder;
import com.yogu.commons.utils.encrypt.AESUtil;

/**
 * 定时从帐号系统获取米星的加密 key
 * @author ten 2015/11/16.
 */
public class MazingGetEncryptKeyThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MazingGetEncryptKeyThread.class);

    private volatile boolean stop = false;

    /**
     * 读取key间隔
     */
    private static final long INTERVAL = 5 * 60 * 1000;

    private static final String ENCRYPT_KEY = "!Xxcjg()#[;'o3!283010932#'";

    private String appKey;

    private String appSecret;

    private ConfigurationDecoder decoder;

    private MazingWebContext context;
    
 // cookie过期时间：14天 （单位: 秒）
 	private static final int DEFAULT_COOKIE_TIMEOUT = 14 * 24 * 3600;

    /**
     * 构造函数
     * @param appKey 应用的appid，AES加密
     * @param appSecret 应用的appkey，AES加密
     * @param decoderClass 解密前两个参数的类
     */
    public MazingGetEncryptKeyThread(String appKey, String appSecret, String decoderClass, MazingWebContext context) {
        super("MazingGetEncryptKeyThread");
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.context = context;
        initDecoder(decoderClass);
    }

    /**
     * 初始化 decoder
     * @param decoderClass
     */
    private void initDecoder(String decoderClass) {
        try {
            Class<?> clazz = Class.forName(decoderClass);
            decoder = (ConfigurationDecoder) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find class: " + decoderClass, e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot init class: " + decoderClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot init class: " + decoderClass, e);
        }
    }

    public void stopThread() {
        if (!stop) {
            stop = true;
            interrupt();
        }
    }

    @Override
    public void run() {
        while (!stop) {
            // 使用固定的key
            try {
                long t1 = System.currentTimeMillis();
                Map<String, Object> map = readKey();
                long time = System.currentTimeMillis() - t1;
                if (map != null) {
                    context.setEncryptKey((String)map.get("key1"), (String) map.get("key2"));
                    Number timeout = (Number) map.get("timeout");
                    if (timeout != null && timeout.intValue() > 0) {
                        logger.info("mazing#sdk#login | cookie过期时间 | timeout: {}", timeout);
                        context.setCookieTimeout(timeout.intValue());
                    }
                    logger.info("mazing#sdk#login | 读取 key 成功 | time: {}", time);
                } else {
                    logger.error("mazing#sdk#login | 读取 key 失败 | time: {}", time);
                }
            } catch (Exception e) {
                logger.error("mazing#sdk#login | 读取key失败", e);
            }

            try {
                if (context.isKeyEmpty()) {
                    // 如果 key 读取不成功，5秒后再读取
                    Thread.sleep(5000);
                }
                else {
                    Thread.sleep(INTERVAL);
                }
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }

    /**
     * 返回解密后的appId
     * @return
     */
    public String getAppKey() {
        return decoder.decode(appKey);
    }

    /**
     * 返回解密后的appKey
     * @return
     */
    public String getAppSecret() {
        return decoder.decode(appSecret);
    }

    /**
     * 从帐号系统读取key
     * @return
     * @throws Exception
     */
    private Map<String, Object> readKey() throws Exception {
    	
    	String key1 = AESUtil.encrypt(ENCRYPT_KEY, "232561032u32uXf-8930kxbs-1ytemn");
		String key2 = AESUtil.encrypt(ENCRYPT_KEY, "plskdki3223234-8930kxbs-xfd-32");
		
		Map<String, Object> map = new HashMap<>(4);
        if (key1 != null) {
            map.put("key1", AESUtil.decrypt(ENCRYPT_KEY, key1));
        }
        if (key2 != null) {
            map.put("key2", AESUtil.decrypt(ENCRYPT_KEY, key2));
        }
        map.put("timeout", DEFAULT_COOKIE_TIMEOUT);
        return map;
    }


}
