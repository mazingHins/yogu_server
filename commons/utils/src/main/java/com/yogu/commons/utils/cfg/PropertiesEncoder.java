package com.yogu.commons.utils.cfg;

/**
 * 针对properties文件的加密、解密接口
 * @author tendy
 *
 */
public interface PropertiesEncoder {

    /**
     * 加密
     * @param str
     * @return 加密后的字符串
     */
    String encode(String str);
    
    /**
     * 解密
     * @param str
     * @return 解密后的字符串
     */
    String decode(String str);
}
