package com.yogu.commons.sdk.user;

import com.yogu.commons.utils.MazingCookie;
import com.yogu.commons.utils.encrypt.AESUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 米星的登录cookie的信息，域名: .mazing.com
 * @author ten 2015/11/16.
 */
public class MazingLoginContext extends MazingWebContext{

//    private static final Logger logger = LoggerFactory.getLogger(MazingLoginContext.class);

    private static final ThreadLocal<MazingLoginUser> loginUserThreadLocal = new ThreadLocal<>();

    private static final String COOKIE_KEY = "mazing_id";

    private static final String DOMAIN = "yogubc.com";

    /**
     * 获取 uid
     * @return 如果登录成功，返回 uid，否则返回 0
     */
    public static long getUid() {
        MazingLoginUser user = loginUserThreadLocal.get();
        return (user == null ? 0L : user.getUid());
    }

    /**
     * 返回昵称，如果没有登录，返回 ""
     * @return
     */
    public static String getNickname() {
        MazingLoginUser user = loginUserThreadLocal.get();
        return (user == null ? "" : user.getNickname());
    }

    /**
     * 清除内存数据
     */
    public void clear() {
        loginUserThreadLocal.remove();
    }

    /**
     * 清除cookie
     */
    public static void clearCookie(HttpServletResponse response) {
        MazingCookie mc = new MazingCookie(DOMAIN, true);
        mc.setCookie(response, COOKIE_KEY, "", 0);
    }

    /**
     * 返回 token，禁止业务系统读取
     * @return
     */
    static String getToken() {
        MazingLoginUser user = loginUserThreadLocal.get();
        return (user == null ? null : user.getToken());
    }

//    /**
//     * 设置加密 cookie 用的 key
//     * @param key1 密钥，从认证server获取
//     */
//    static void setEncryptKey(String key1, String key2) {
//        encryptKey = key1;
//        encryptKey2 = key2;
//    }

    /**
     * 从 cookie 中读取用户数据
     * @param request
     */
    public void load(HttpServletRequest request) {
        if (StringUtils.isBlank(encryptKey2)) {
            throw new IllegalStateException("无法获得加密cookie的key，请确认MazingLoginFilter是否在web.xml里配置");
        }
        MazingCookie mc = new MazingCookie(DOMAIN, true);
        String value = mc.getCookie(request, COOKIE_KEY);

        if (StringUtils.isNotBlank(value)) {
            String[] decryptValue = decrypt(value);
            String context = (decryptValue == null ? null : decryptValue[0]);
            if (StringUtils.isNotBlank(context)) {
                parseCookie(context, decryptValue[1]);
            }
            else {
                logger.error("mazing#sdk#login | cookie为空或解密失败");
            }
        }
    }

    /**
     * 解析 cookie
     * @param context 经过解密的cookie内容
     * @param tmpKey 签名 key
     */
    static void parseCookie(String context, String tmpKey) {
        String[] array = context.split("\\|");
        Map<String, Object> map = new HashMap<>();
        String sign = null;
        MazingLoginUser user = new MazingLoginUser();
        for (String str : array) {
            String[] entry = str.split("=");
            if (entry.length == 2) {
                String name = entry[0];
                String tmpValue = entry[1];
                if ("uid".equals(name)) {
                    long uid = NumberUtils.toLong(tmpValue, 0);
                    if (uid <= 0) {
                        break;
                    }
                    user.setUid(uid);
                    map.put("uid", uid);
                } else if ("loginTime".equals(name)) {
                    long loginTime = NumberUtils.toLong(tmpValue, 0);
                    if (loginTime <= 0) {
                        break;
                    }
                    else if (System.currentTimeMillis() - loginTime >= (cookieTimeout * 1000)) {
                        // 过期
                        break;
                    }
                    // 判断是否超时
                    user.setLoginTime(loginTime);
                    map.put("loginTime", loginTime);
                } else if ("nickname".equals(name)) {
                    user.setNickname(tmpValue);
                    map.put("nickname", tmpValue);
                } else if ("sign".equals(name)) {
                    sign = tmpValue;
                }
                else if ("token".equals(name)) {
                    user.setToken(tmpValue);
                    map.put("token", tmpValue);
                }
                else {
                    map.put(name, tmpValue);
                }
            }
        }
        // 验证 sign
        String tmpContext = mapToString(map);
        String tmpSign = getContextSign(tmpContext, tmpKey);
        if (tmpSign.equals(sign)) {
            loginUserThreadLocal.set(user);
        }
        else {
            logger.error("mazing#sdk#login | cookie签名错误");
        }
    }

    /**
     * 保存 cookie 到 cookie
     * @param response
     * @param params
     */
    public static void saveMazingLoginUserToCookie(HttpServletResponse response, Map<String, Object> params) {
        if (StringUtils.isBlank(encryptKey)) {
            throw new IllegalStateException("无法获得加密cookie的key，请确认MazingLoginFilter是否在web.xml里配置");
        }
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException("params 不能为空");
        }

        String context = mapToString(params);
        // 用最新的密钥加密
        String sign = getContextSign(context, encryptKey2);
        context = context + "|sign=" + sign;
        try {
            String value = AESUtil.encrypt(encryptKey2, context);
            MazingCookie mc = new MazingCookie(DOMAIN, true);
            mc.setCookie(response, COOKIE_KEY, value, cookieTimeout);
        } catch (Exception e) {
            logger.error("mazing#sdk#login | 加密cookie失败 | encryptKey2: {}, context: {}", encryptKey2, context, e);
            throw new RuntimeException("加密cookie失败");
        }
    }

    /**
     * 把 map 转成 签名用的 string
     * @param params
     * @return
     */
    private static String mapToString(Map<String, Object> params) {
        SortedMap<String, Object> map = new TreeMap<>();
        map.putAll(params);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry: map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    /**
     * Cookie保存的信息
     */
    static class MazingLoginUser {

        private long uid;

        private String nickname;

        private long loginTime;

        private String token;

        public long getUid() {
            return uid;
        }

        public void setUid(long uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public long getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(long loginTime) {
            this.loginTime = loginTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static void main(String[] args) throws Exception {

    }
}
