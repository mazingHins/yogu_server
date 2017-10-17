package com.yogu.services.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.yogu.commons.utils.DateUtils;


/**
 * 支付/退款编号生成工具
 * 
 * @author Hins
 * @date 2016年2月18日 上午10:59:53
 */
public class GenerateUtils {
	
	private static final int PAY_MAX_LEN = 18;

	private static final long[] NUMBER = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L};

	private static final int REFUND_MAX_LEN = 16;

	private static final SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYMMDD);

	private static int counter =(int) (System.currentTimeMillis() % 10000);
	
	private static String getDate() {
		return sdf.format(new Date());
	}
	
	/**
	 * 生成一个长度为18位的支付流水号
	 * 
	 * @param uid - 买家用户ID
	 * @param tradeNo - 平台内部交易号
	 * @return 返回唯一的交易号
	 */
	public static synchronized long nextPayNo(long uid, long tradeNo) {
		// 生成思路:
		// 日期（4位，年月）+ source(2位，不够2位补全2位) + 随机数（1~5位）+ tradeNo（具体位数补齐到16位）
		// 注：（1）这个算法不能保证唯一；
		// （2）算法要保证不能被猜测到；

		// 生成唯一的ID算法参考snowflake：https://github.com/zzxadi/Snowflake-IdWorker/blob/master/IdWorker.java

		Random rand = new Random();
		String date = getDate();
		uid = uid % 10000;

		int n = rand.nextInt(10000);
		long tmpCounter = counter % 97423; // 质数 3571, 97423, 937481
		counter++;
		StringBuilder buf = new StringBuilder();
//		buf.append(date).append(source < 10 ? "0" + source : source).append(tmpCounter).append(n);
		buf.append(date).append(tmpCounter).append(uid).append(n);
		if (PAY_MAX_LEN > buf.length()) {
			// 不足 MAX_LEN，补够位数
			buf.append(tradeNo % (NUMBER[PAY_MAX_LEN - buf.length()]));

			if (buf.length() < PAY_MAX_LEN) {
				long now = System.currentTimeMillis();
				buf.append(now % (NUMBER[PAY_MAX_LEN - buf.length()]));
			}
		}

		return Long.parseLong(buf.toString());
	}
	
	/**
     * 生成一个长度为16位的退款号
     * @param payNo - 平台内部支付编号
     * @return 返回唯一的退款号
     */
    public static long nextRefundNo(long payNo) {
        // 生成思路:
        // 日期（6位，年月）+ 随机数（1~5位）+ payNo（具体位数补齐到16位）
        // 注：（1）这个算法不能保证唯一；
        //     （2）算法要保证不能被猜测到；

        // 生成唯一的ID算法参考snowflake：https://github.com/zzxadi/Snowflake-IdWorker/blob/master/IdWorker.java

        Random rand = new Random();
        String date = getDate();

        int n = rand.nextInt(100000);

        StringBuilder buf = new StringBuilder();
        buf.append(date).append(n);
        if (REFUND_MAX_LEN > buf.length()) {
            // 不足 MAX_LEN，补够位数
            buf.append(payNo % (NUMBER[REFUND_MAX_LEN - buf.length()]));

            if (buf.length() < REFUND_MAX_LEN) {
                long now = System.currentTimeMillis();
                buf.append(now % (NUMBER[REFUND_MAX_LEN - buf.length()]));
            }
        }

        return Long.parseLong(buf.toString());
    }

}
