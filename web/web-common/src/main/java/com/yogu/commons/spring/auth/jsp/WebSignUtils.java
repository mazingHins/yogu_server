package com.yogu.commons.spring.auth.jsp;

import com.yogu.commons.utils.IpAddressUtils;
import com.yogu.commons.utils.LogUtil;
import com.yogu.commons.utils.encrypt.HMacSHA1;
import com.yogu.commons.utils.encrypt.SignUtils;
import com.yogu.core.remote.config.ConfigGroupConstants;
import com.yogu.core.remote.config.ConfigRemoteService;
import com.yogu.core.server.exception.AuthenticationException;
import com.yogu.core.web.ResultCode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 签名帮助
 * @author ten 2015/11/20.
 */
public class WebSignUtils {

    private static final Logger logger = LoggerFactory.getLogger(WebSignUtils.class);

    /**
     * 检查签名是否通过
     * @param request
     */
    public static void check(HttpServletRequest request) {
        String ip = IpAddressUtils.getClientIpAddr(request);
        Map<String, String[]> params = readHeaderParams(request);
        Map<String, String[]> pm = new HashMap<>(request.getParameterMap());
        params.putAll(pm);
        params.remove("sign");
        String sign = getSign(request);
        if (StringUtils.isBlank(sign)) {
            throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "sign is empty.");
        }

        // 校验appKey
        String appKey = SignUtils.getParam(params, "akey", "");
        // 获取appSecret
        String secret = (appKey == null ? null : ConfigRemoteService.getConfig(ConfigGroupConstants.APP_KEY, appKey));
        if (secret == null) {
            logger.error("web#security#interceptor | secret=null | apikey: {}", LogUtil.hideAppKey(appKey));
            throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "app not defined.");
        }
        String source = SignUtils.signSource(params);

        String tmpSign = HMacSHA1.getSignature(source, secret);
        if (tmpSign == null || !tmpSign.equals(sign)) {
            logger.error("web#security#interceptor | 签名失败 | queryString: " + request.getQueryString());
            logger.error("web#security#interceptor | 签名失败 | ip: {}, source: {}, sign: {}, tmpSign: {}", ip, source, sign, tmpSign);
            throw new AuthenticationException(ResultCode.PARAMETER_ERROR, "sign Incorrect.");
        }
    }

    /**
     * 读取header参数
     */
    private static Map<String, String[]> readHeaderParams(HttpServletRequest request) {
        String startStr = "mz-";
        int startLen = startStr.length();

        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String[]> params = new HashMap<String, String[]>();
        while (headerNames.hasMoreElements()) {
            // read keyName
            String key = headerNames.nextElement();
            if (!(key.startsWith(startStr)) || key.length() <= startLen)
                continue;
            String name = key.substring(startLen);
            name = name.trim();
            // read values
            List<String> vs = new LinkedList<String>();
            Enumeration<String> headers = request.getHeaders(key);
            while (headers.hasMoreElements())
                vs.add(headers.nextElement());
            // put
            params.put(name, vs.toArray(new String[vs.size()]));
        }
        return params;
    }

    /**
     * 返回 sign 参数的值
     * @param request
     * @return
     */
    private static String getSign(HttpServletRequest request) {
        String sign = request.getHeader("mz-sign");
        if (StringUtils.isBlank(sign))
            sign = request.getParameter("sign");
        return sign;
    }

}
