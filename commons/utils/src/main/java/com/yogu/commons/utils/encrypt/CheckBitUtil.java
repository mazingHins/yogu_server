package com.yogu.commons.utils.encrypt;

/**
 * 给字符串加一个简单的校验位
 * @author linyi 2015/5/29
 */
public class CheckBitUtil {

    /**
     * xor加密
     * object-c版本：http://www.cnblogs.com/KiloNet/articles/1823480.html
     * java: http://www.cnblogs.com/KiloNet/archive/2011/01/15/1936434.html
     *
     * @param strOld
     * @param strKey
     * @return
     */
    public static String encrypt(String strOld, String strKey) {
        byte[] data = strOld.getBytes();
        byte[] keyData = strKey.getBytes();
        int keyIndex = 0 ;
        for(int x = 0 ; x < strOld.length() ; x++) {
            data[x] = (byte)(data[x] ^ keyData[keyIndex]);
            if (++keyIndex == keyData.length){
                keyIndex = 0;
            }
        }
        return new String(data);
    }

    /**
     * 所有的字符
     */
    private static final char[] CHARS = {'0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'};

    /**
     * 把字符串转成小写，并替换掉非字母数字的字符，然后增加校验位
     * @param s - 字符串
     * @return 返回增加校验位后的字符串
     */
    public static String addCheckBit(final String s) {
        String tmp = s.toLowerCase().replaceAll("[^a-z0-9]", ""); // 全大写
        char x = 'X';
        char z = 'R';
        for (int i=0; i < tmp.length(); i++) {
            char a = tmp.charAt(i);
            x = (char) (x ^ a);
            z = (char) (z % a);
        }
        int n1 = Math.abs((int)x) % CHARS.length;
        int n2 = Math.abs((int)z) % CHARS.length;
        return CHARS[n1] + tmp + CHARS[n2];
    }

    /**
     * 判断字符串是否有效
     * @param s - 字符串
     * @return true=有效
     */
    public static boolean isValid(final String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        String tmp = addCheckBit(s.substring(1, s.length() - 1));
        return tmp.equals(s);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String s = encrypt("2389sdkljfasjfajflasjflajfljasfaj", "xcsdfsf");
        System.out.println(s);
        System.out.println(encrypt(s, "xcsdfsf"));
        final int MAX = 10000;
        // 预热
        for (int i=0; i < MAX; i++)
            addCheckBit("dfjskaljfzzz");
        long t1 = System.currentTimeMillis();
        for (int i=0; i < MAX; i++)
            addCheckBit("dfjskaljfzzz");
        long time = System.currentTimeMillis() - t1;
        System.out.println("time=" + time);
        System.out.println(addCheckBit("23232298928"));
        System.out.println(isValid(addCheckBit("23232298928")));
        System.out.println(isValid(addCheckBit("23232298928") + "a"));
        String s1 = addCheckBit("50F5F942-3754-463B-8FFB-A94685BA86E3");
        System.out.println(s1);
        System.out.println(isValid(s1));
        System.out.println(isValid(s1 + "a"));

    }

}