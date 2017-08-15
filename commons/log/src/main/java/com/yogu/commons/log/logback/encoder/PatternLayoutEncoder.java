package com.yogu.commons.log.logback.encoder;

/**
 * 继承logback中同名的样式处理器 <br>
 * <br>
 * 主要是增加一个shutdown钩子，在关闭服务的时候提交未输出的缓存数据
 *
 * @author JFan 2015年11月20日 下午1:46:30
 */
//public class PatternLayoutEncoder extends ch.qos.logback.classic.encoder.PatternLayoutEncoder {
//
//	private static Map<String, PatternLayoutEncoder> buffMap = new HashMap<>();
//	private static boolean addShutdownHook = false;
//
//	public PatternLayoutEncoder() {
//		addShutdownHook();
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void setImmediateFlush(boolean immediateFlush) {
//		super.setImmediateFlush(immediateFlush);
//		// 如果是false，那么记录到 buffMap 中
//		// 使用当前对象的“内存地址”作为KEY
//		if (!(immediateFlush))
//			buffMap.put("" + this, this);
//	}
//
//	// ## 处理Shutdown时，残余日志的输出
//
//	public static void flushAll() {
//		for (PatternLayoutEncoder bufferedWriter : buffMap.values())
//			synchronized (bufferedWriter) {
//				try {
//					if (!(bufferedWriter.isImmediateFlush()))
//						bufferedWriter.outputStream.flush();
//				} catch (IOException e) {
//					// 不处理
//				}
//			}
//	}
//
//	private void addShutdownHook() {
//		if (!addShutdownHook) {
//			Runtime.getRuntime().addShutdownHook(new Thread() {
//
//				/*
//				 * （非 Javadoc）
//				 *
//				 * @see java.lang.Thread#run()
//				 */
//				@Override
//				public void run() {
//					try {
//						PatternLayoutEncoder.flushAll();
//						buffMap = new HashMap<>();// 释放引用
//					} catch (Exception e) {
//						// ignore
//					}
//				}
//
//			});
//			addShutdownHook = true;
//		}
//	}
//
//}
