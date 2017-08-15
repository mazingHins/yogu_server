package com.yogu.commons.sdk.user.encrypt;

import com.yogu.commons.utils.encrypt.AESUtil;

/**
 * 配置文件加密、解密的默认实现
 * @author ten 2015/11/17.
 */
public class DefaultConfigurationDecoder implements ConfigurationDecoder {

    private static final String KEY = "3@!3h3sh_=3293u3832-3283js!$#";

    @Override
    public String encode(String text) {
        try {
            return AESUtil.encrypt(KEY, text);
        } catch (Exception e) {
            throw new RuntimeException("加密失败", e);
        }
    }

    @Override
    public String decode(String text) {
        try {
            return AESUtil.decrypt(KEY, text);
        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }

    public static void main(String[] args) {
        System.out.println(DefaultConfigurationDecoder.class.getName());
        DefaultConfigurationDecoder dcd = new DefaultConfigurationDecoder();
        System.out.println(dcd.encode("1234"));

    }
}
