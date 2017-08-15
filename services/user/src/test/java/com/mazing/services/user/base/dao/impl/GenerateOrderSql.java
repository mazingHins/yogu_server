package com.mazing.services.user.base.dao.impl;

import org.apache.commons.io.FileUtils;

import com.yogu.commons.utils.DateUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 生成测试数据
 * @author ten 2015/11/11.
 */
public class GenerateOrderSql {

    private static int[] status = {10, 15, 20, 25, 35, 40, 45, 50, 55, 60, 65};

    private static int randStatus(Random rand) {
        int num = rand.nextInt(100);
        if (num < 10) {
            // 已支付
            return 15;
        }
        if (num >= 10 && num < 50) {
            // 已经完成
            return 40;
        }
        if (num >= 50 && num < 70) {
            return 20; // 已接单
        }
        if (num >= 70 && num < 80)
            return 25; // 配送中
        if (num >= 80 && num < 85) {
            return 35; // 买家确认收货
        }
        if (num >= 85 && num < 95) {
            return 65;
        }
        if (num >= 95 && num < 99) {
            return 50; // 退款
        }
        return 10; // 未支付
    }

    private static long getSequence(SimpleDateFormat sdf, Date createTime, int serialNum) {
        // 订单的排序号, 订单预计送达时间的yyMMddHHmm 乘以 100000 再加上订单序列号
        Calendar cal = DateUtils.getCalendar();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        String s = sdf.format(cal.getTime());
        long n = Long.parseLong(s) * 100000 + serialNum;
        return n;
    }


    public static void main(String[] args) throws Exception {
        final int N = 100000;
        StringBuilder buf = new StringBuilder(1024 * 1024);
        long orderId = 100000;
        //long orderNo = 10000;
        long payNo = 10000;
        Random rand = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        for (int i=0; i <  N; i++) {
            long uid = Math.abs(rand.nextLong()) % 10000;
            long storeId = 5;//Math.abs(rand.nextLong()) % 15 + 1;
            long totalFee = Math.abs(rand.nextLong()) % 1000000 + 1000;

            Date createTime = new Date();
            String time = sdf.format(createTime);
            int status = randStatus(rand);
            int serialNum = (i + 1);
            long sequence = getSequence(dateFormat, createTime, serialNum);

            String s = "insert into mz_order (order_id, order_no, pay_no, uid, store_id, pay_type, pay_mode, currency_type, total_fee," +
                    " delivery_fee, discount_fee, goods_fee, address, contacts, phone, pick_type, delivery_time, lat, lng, meal_number, " +
                    "feature_content, subject, body, status, remark, delivery_remark, order_begin_time, user_confirm_time," +
                    "store_confirm_time, is_back, back_time, back_number, back_source_status, serial_number, sequence, " +
                    "print_number, reject_remark, service_range_id, service_range_name, service_time, service_day, making_time, " +
                    "comment_id, create_time, update_time, finish_time) values " +
                    "(" + orderId + ", " + orderId + ", " + payNo + ", " + uid +
                    ", " + storeId + ", 1, 1, 1, " + totalFee + ", 0, 0, " + totalFee + ", 'Address', '张三', '13600001111', 1," +
                    " '" + time + "', 120.322, 32.323, 5, '微辣', '飞利浦大闸蟹', '大闸蟹x100', " + status + "," +
                    " '', '', '" + time + "', '" + time + "', '" + time + "', 0, null, 0, 0, '" + serialNum + "'," +
                    sequence + ", 0, '', 1, 'A1', 30, 20151111, 20, 0, '" + time + "', '" + time + "', null);\n";
            buf.append(s);

            if ((serialNum % 500) == 0) {
                FileUtils.write(new File("e:\\temp\\mock_order.sql"), buf.toString(), "utf-8", true);
                buf.setLength(0);
            }
            orderId += 1;
        }
    }
}
