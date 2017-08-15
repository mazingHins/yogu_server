package com.yogu.services.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 生成唯一的订单号
 * @author ten 2015/8/7.
 */
public class OrderNO {

    /**
     * 初始化 SimpleDateFormat，因为 SimpleDateFormat 的初始化是很慢的
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyMM");

    public static final int MAX_LEN = 16;

    private static int counter = (int) (System.currentTimeMillis() % 10000);

    private static final int[] NUMBER = {
        1, 10, 100, 1000, 10000, 100000, 1000000,10000000, 100000000, 1000000000
    };

    private static String getDate() {
        return sdf.format(new Date());
    }

    /**
     * 生成一个长度为16位的订单号
     * @param uid 用户ID，全局唯一
     * @param orderId 订单ID，全局唯一
     * @return 返回唯一的订单号
     */
    public static synchronized long next(long uid, long orderId) {
        // 生成思路:
        // 日期（4位，年月）+ uid（后4位）+ ip + 随机数（1~5位）+ orderId（具体位数补齐到16位）
        // 注：（1）这个算法不能保证唯一；
        //     （2）算法要保证不能被猜测到；

        // 生成唯一的ID算法参考snowflake：https://github.com/zzxadi/Snowflake-IdWorker/blob/master/IdWorker.java

        Random rand = new Random();
        String date = getDate();
        uid = uid % 10000;

        int n = rand.nextInt(10000);
        int tmpCounter = counter % 97423; // 质数 3571, 97423, 937481
        StringBuilder buf = new StringBuilder();
        counter++;

        buf.append(date).append(uid).append(tmpCounter).append(n);
        if (MAX_LEN > buf.length()) {
            // 不足 MAX_LEN，补够位数
            buf.append(orderId % (NUMBER[MAX_LEN - buf.length()]));

            if (buf.length() < MAX_LEN) {
                long now = System.currentTimeMillis();
                buf.append(now % (NUMBER[MAX_LEN - buf.length()]));
            }
        }

        return Long.parseLong(buf.toString());
    }

    public static void main(String[] args) {
        for (int i=0; i < 100; i++) {
            System.out.println(next(10323, 13092 + i));
        }
        long t1 = System.currentTimeMillis();
        final int MAX = 5000000;
        Set<Long> tmpSet = new HashSet<>(MAX * 4 / 3);
        int dupCount = 0;
        for (int i=0; i < MAX; i++) {
            long uid = (i + 1) % 1000;
            long id = next(uid, i+1);
            Long idObj = Long.valueOf(id);
            if (tmpSet.contains(idObj)) {
                dupCount++;
            }
            else {
                tmpSet.add(idObj);
            }
        }
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        System.out.println("total time: " + time);
        System.out.println("dup count: " + dupCount);

    }
}
