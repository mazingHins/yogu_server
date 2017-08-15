package com.yogu.remote.config.idcode;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yogu.CommonConstants;
import com.yogu.commons.utils.HttpClientUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.core.web.RestResult;

/**
 * 短信验证码服务
 * @author ten 2015/11/26.
 */
@Named
public class IdCodeRemoteService {

    private static final Logger logger = LoggerFactory.getLogger(IdCodeRemoteService.class);

    /**
     * 读取已经发送到用户手机的验证码
     * @param countryCode
     * @param mobile 手机号码
     * @return 返回验证码，如果没有，返回""
     */
    public String getSmsCode(String countryCode, String mobile) {
        String code = null;
        try {
            String json = HttpClientUtils.doGet(CommonConstants.USER_DOMAIN + "/api/sms/getSmsCode?mobile=" + mobile);
            logger.info("remote#config#getSmsCode | 读取短信验证码 | json: {}", json);
            RestResult<String> result = JsonUtils.parseObject(json, new TypeReference<RestResult<String>>() {
            });
            code =  result.getObject();
        } catch (Exception e) {
            logger.error("remote#config#getSmsCode | 读取短信验证码错误", e);
        }
        if (code == null) {
            code = "";
        }
        return code;
    }
}
