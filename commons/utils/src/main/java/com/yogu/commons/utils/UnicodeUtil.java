package com.yogu.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 关于字符的工具类
 * @author linyi 2015/5/30.
 */
public class UnicodeUtil {

    /**
     * 判断字符串是不是 中文、字母、数字
     * @param s - 字符串
     * @return 返回 true 表示符合要求
     */
    public static boolean isChineseAndLetterAndNum(String s) {
        String regx = "[0-9,a-z,A-Z,\\u4e00-\\u9fa5]+";
        return s.matches(regx);
    }

    /**
     * mysql 5.5.x 版本下的 UTF-8 最大长度为3字节，但有些UTF-8字符会占4字节，
     * 保存到 mysql 下会出错，用这个方法可以把这些 4 字节以上的字符替换成一个固定的3字节字符，
     * mysql 5.5.x 版本以上要指定 utf8mb4 字符集才可以支持 4 字节的UTF-8字符。
     * 4 字节的UTF-8 字符例子：𐀤
     * @param input
     * @return
     */
    public static String filterMt4BytesUtf8(String input) {
        if (StringUtils.isBlank(input))
            return input;

        ByteArrayOutputStream is = new ByteArrayOutputStream();
        try {
            byte[] bytes = input.getBytes("utf-8");

            outter: for (int i = 0, length = bytes.length; i < length; i++) {
                byte b = bytes[i];
                while (((b & 0xF8) == 0xF0) || ((b & 0xFc) == 0xF8)
                        || ((b & 0xFe) == 0xFc)) {
                    // found a byte of 4, 5, 6 UTF-8 bytes for a character
                    // eat the following bytes of this character
                    while (++i < length && ((b = bytes[i]) & 0xC0) == 0x80)
                        ;
                    // insert a replacement character
                    is.write((byte) 0xEF);
                    is.write((byte) 0xBF);
                    is.write((byte) 0xBD);
                    if (i >= length) {
                        // we reach the end of byte array
                        break outter;
                    }
                }
                // found byte of 1, 2, 3 UTF-8 bytes for a character
                is.write(b);
            }

            return new String(is.toByteArray(), "utf-8");

        } catch (UnsupportedEncodingException e) {
            return input;
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {

            }
        }
    }

    public static void main(String[] args) {
        System.out.println("is gbk=" + isChineseAndLetterAndNum("xZf3202你好"));
        System.out.println("is gbk=" + isChineseAndLetterAndNum("xZf32023/你好"));
    }
}
