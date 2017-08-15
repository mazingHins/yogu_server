package com.yogu.mq.impl;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yogu.mq.impl.aliyun.CommandMQProducer;

/**
 * MQ 的测试，在服务器上跑
 * 
 * @author linyi 2015/8/5.
 */
public class MQProducerConsumerTest {

	private static boolean received = false;

	public static void main(String[] args) throws Exception {
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext("classpath*:META-INF/mazing-applicationContext.xml");

		long time = System.currentTimeMillis();
		System.out.println("Read to send ...");

		for (int i=0; i < 100; i++) {
			CommandMQProducer.get().send("test-ten", time + "_" + i);
		}
//		MQProducer producer = (MQProducer) context.getBean("mqProducer");
//		Redis redis = (Redis) context.getBean("redis");
//		MQConsumer consumer = MQConsumerFactory.createConsumer(redis);
//
//		int index = new Random().nextInt(256);
//		final String type = "test_" + index;
//		System.out.println("type is: " + type);
//
//		consumer.add(type, new ServerMsgNotice() {
//			@Override
//			public void notice(String messageId, String message) {
//				System.out.println("id: " + messageId + ", message: " + message);
//				received = true;
//			}
//		});
//
//		producer.send(type, "HELLO！" + index);
//		int n = 0;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("starting ... " + sdf.format(new Date()));
//		while (!received && n++ < 5) {
//			Thread.sleep(1000);
//		}
//		System.out.println("received: " + received + " ... " + sdf.format(new Date()));
	}

}
