package com.yogu.commons.sdk.user;

import com.yogu.CommonConstants;
import com.yogu.commons.sdk.user.encrypt.ConfigurationDecoder;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.encrypt.AESUtil;
import com.yogu.commons.utils.encrypt.HMacSHA1;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;
import javax.net.ssl.SSLContext;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

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
     * 创建要访问的URL，包括签名
     * @return
     */
    private String createUrlAndSign() {
        String tmpAppId = getAppKey();
        String tmpAppKey = getAppSecret();
        long t = System.currentTimeMillis();
        String str = tmpAppId + t;
		String sign = HMacSHA1.getSignature(str, tmpAppKey);
		return new StringBuilder(CommonConstants.USER_DOMAIN).append("/api/security/getKey")//
				.append("?akey=").append(tmpAppId).append("&t=").append(t).append("&sign=").append(sign).toString();
	}

    /**
     * 从帐号系统读取key
     * @return
     * @throws Exception
     */
    private Map<String, Object> readKey() throws Exception {
        // CloseableHttpClient httpclient = HttpClients.createDefault();
    	CloseableHttpClient httpclient = createSSLClientDefault();// jfan 2016-01-21

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(UserSdkConstants.CONNECT_TIMEOUT)
                .setSocketTimeout(UserSdkConstants.SO_TIMEOUT).build();
        HttpGet httpGet = new HttpGet(createUrlAndSign());
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        try {
            HttpEntity entity = response.getEntity();

            String value = EntityUtils.toString(entity);
            if (StringUtils.isNotBlank(value)) {
                JsonObject jo = JsonUtils.toJsonObject(value);
                boolean success = jo.getBoolean("success", false);
                if (success) {
                    JsonObject obj = jo.getJsonObject("object");

					String key1 = obj.getString("key1");
					String key2 = obj.getString("key2");
					long timeout = obj.getJsonNumber("timeout").longValue();
					logger.info("mazing#sdk#login | 读取到key内容 | key1: {}, key2: {}, timeout: {}", key1, key2, timeout);

                    Map<String, Object> map = new HashMap<>(4);
                    if (key1 != null) {
                        map.put("key1", AESUtil.decrypt(ENCRYPT_KEY, key1));
                    }
                    if (key2 != null) {
                        map.put("key2", AESUtil.decrypt(ENCRYPT_KEY, key2));
                    }
                    map.put("timeout", timeout);
                    return map;
                }

            }
            logger.error("mazing#sdk#login | 读取key失败 | value: {}", value);
        } finally {
            response.close();
        }
        return null;
    }

	/**
	 * 创建httpClient，可自动接受ssl
	 */
	private CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

}
