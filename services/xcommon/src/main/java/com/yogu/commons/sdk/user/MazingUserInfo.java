package com.yogu.commons.sdk.user;

import com.yogu.CommonConstants;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.encrypt.HMacSHA1;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.JsonObject;

/**
 * 用户信息
 * @author ten 2015/11/18.
 */
public class MazingUserInfo {

    private static final Logger logger = LoggerFactory.getLogger(MazingUserInfo.class);

    /**
     * 校验token的url
     */
    private static final String VALIDATE_TOKEN_URL = CommonConstants.USER_DOMAIN + "/api/security/validateToken";

    private String appKey;

    private String appSecret;
    /**
     * 构造函数
     * @param appKey 颁发的akey
     * @param appSecret 颁发的密钥
     */
    public MazingUserInfo(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    /**
     * 判断用户是否真的有效，某些关键业务操作前最好使用这个方法。
     * 此方法将远程访问帐号系统的接口判断。
     * @return
     */
    public boolean isValidUser() {
        String url = createUrlAndSign();
        try {
            return readUrl(url);
        } catch (Exception e) {
            logger.error("验证token失败", e);
        }
        return false;
    }

    /**
     * 创建校验 token 的 url
     * @return
     */
    private String createUrlAndSign() {
        String token = MazingLoginContext.getToken();
        Args.notNull(token, "用户未登录");

        long t = System.currentTimeMillis();
        String source = appKey + t + token;
        String sign = HMacSHA1.getSignature(source, appSecret);
        String url = VALIDATE_TOKEN_URL + "?akey=" + appKey + "&t=" + t + "&token=" + token + "&sign=" + sign;
        return url;
    }

    /**
     * 读取 url 结果
     * @param url
     * @return
     * @throws Exception
     */
    protected boolean readUrl(String url) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(UserSdkConstants.CONNECT_TIMEOUT)
                .setSocketTimeout(UserSdkConstants.SO_TIMEOUT).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpclient.execute(httpGet);

        try {
            HttpEntity entity = response.getEntity();

            String value = EntityUtils.toString(entity);
            if (StringUtils.isNotBlank(value)) {
                JsonObject jo = JsonUtils.toJsonObject(value);
                return jo.getBoolean("success", false);
            }
        } finally {
            response.close();
        }
        return false;
    }

}
