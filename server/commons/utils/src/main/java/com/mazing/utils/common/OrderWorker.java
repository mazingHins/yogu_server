package com.mazing.utils.common;

import java.util.HashSet;
import java.util.Set;

public class OrderWorker {
	private final long twepoch = 1288834974657L;
	private final long sequenceBits = 14L;
	private final long uidBits = 8L + sequenceBits;
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);
	private final long timestampLeftShift = sequenceBits + uidBits;

	private long sequence = 0L;
	private long lastTimestamp = -1L;

	/**
	 * 生成唯一订单编号
	 * @param uid
	 * @author hins
	 * @date 2016年11月22日 下午7:30:36
	 * @return long
	 */
	public synchronized long nextId(long uid) {
		/*
		 * 结构包含：1bit不用 + 41bit时间戳  + 8位用户id + 14bit序列号
		 * 
		 * 生成唯一的ID算法参考snowflake：https://github.com/zzxadi/Snowflake-IdWorker/blob/master/IdWorker.java
		 */
		
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (uid << uidBits) | sequence;
	}

	protected long timeGen() {
		return System.currentTimeMillis();
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	public static void main(String[] args) {

		OrderWorker idWorker = new OrderWorker();
		// 先运行几次..与jvm有关，具体原理不会说
		for (int i = 0; i < 100; i++) {
			System.out.println(idWorker.nextId(i));
		}

		// 正式测试
		final int MAX = 5000000;
		long dupCount = 0, uid = 10001;
		long t1 = System.currentTimeMillis();
		Set<Long> tmpSet = new HashSet<>(MAX * 4 / 3 + 2);
		for (int i = 0; i < MAX; i++) {
			uid += i;
			long id = idWorker.nextId(i);
			Long idObj = Long.valueOf(id);
			if (tmpSet.contains(idObj)) {
				dupCount++;
			} else {
				tmpSet.add(idObj);
			}
		}

		long t2 = System.currentTimeMillis();
		long time = t2 - t1;
		System.out.println("生成总耗时（单位毫秒）: " + time);
		System.out.println("重复数量: " + dupCount);
	}

}
