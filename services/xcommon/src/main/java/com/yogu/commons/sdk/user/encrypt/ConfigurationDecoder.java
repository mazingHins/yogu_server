package com.yogu.commons.sdk.user.encrypt;

/**
 * 实现配置文件里的密钥的encode, decode
 * @author ten 2015/11/17.
 */
public interface ConfigurationDecoder {

    /**
     * 加密
     * @param text 要加密的内容
     * @return
     */
    String encode(String text);

    /**
     * 解密
     * @param text 要加密的内容
     * @return
     */
    String decode(String text);
}
