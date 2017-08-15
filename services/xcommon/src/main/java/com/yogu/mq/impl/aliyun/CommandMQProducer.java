package com.yogu.mq.impl.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.*;
import com.yogu.commons.utils.Args;
import com.yogu.commons.utils.DateUtils;
import com.yogu.commons.utils.JsonUtils;
import com.yogu.commons.utils.SerializeUtil;
import com.yogu.mq.impl.cfg.MQSetting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.UUID;

/**
 * command mq producer 工具类(实例化一次,单例)<br>
 * 
 * TODO 该类需要剥离consumer项目而独立<br>
 * 
 * 
 * 
 * 调用示例<br>
 * <code>CommandMQProducer.get().send(BussinessType.TEST_MQ_FUNC,"test mq function"); </code><br>
 * <br>
 * <code>CommandMQProducer.get().sendJSON(BussinessType.TEST_MQ_FUNC, new MessageBean()); </code><br>
 * 
 * 
 * @author sky
 *
 */
public class CommandMQProducer {
	private static final Logger logger = LoggerFactory.getLogger(CommandMQProducer.class);

	private static Producer producer = null;

	private static CommandMQProducer instance = null;

	private CommandMQProducer() {
		// 限制只能自己创建
		// ten 2015/10/10
	}

	/**
	 * 获取CommandMQProducer实例
	 * 
	 * @return
	 */
	public static synchronized CommandMQProducer get() {

		if (instance == null) {
			instance = new CommandMQProducer();
			instance.init();
		}

		return instance;
	}

	/**
	 * 初始化 producer,启动producer
	 *
	 */
	private void init() {
		// 改为 private by ten

		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, MQSetting.getQueueProducerId());
		properties.put(PropertyKeyConst.AccessKey, MQSetting.getCloudAccessKey());
		properties.put(PropertyKeyConst.SecretKey, MQSetting.getCloudAccessSecret());
		//properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);

		logger.info("mq#producer#initial |  command mq producer params | producerId: {}, topic: {}", MQSetting.getQueueProducerId(),
				MQSetting.getQueueName());

		producer = ONSFactory.createProducer(properties);

		Args.notNull(producer, "本地producer初始化失败");

		// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
		producer.start();

		logger.info("mq#producer#initial |  command mq producer start success | time: {}", DateUtils.currentTimeDay());

	}

	/**
	 * 发送消息
	 * 
	 * @param type 业务类型
	 * @param message string类型message
	 */
	public void send(String type, String message) {

		Args.notNull(type, "type不能为null");
		Args.notNull(message, "message不能为null");
		Message msg = new Message(
		// Message Topic
				MQSetting.getQueueName(),
				// Message Tag,
				// 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过
				type,
				// Message Body
				// 任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方
				message.getBytes());

		// 设置代表消息的业务关键属性，请尽可能全局唯一。
		// 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
		// 注意：不设置也不会影响消息正常收发
		msg.setKey(UUID.randomUUID().toString().replaceAll("-", ""));

		// 发送消息，只要不抛异常就是成功
		SendResult sendResult = producer.send(msg);

		logger.info("mq#producer#send | message has been sent | result: {}, type: {},  message: {}", sendResult, type, message);

	}

	/**
	 * 发送json数据类型的消息
	 * 
	 * @param type 业务类型
	 * @param obj 数据对象obj
	 */
	public void sendJSON(String type, Object obj) {
		// 2016-6-23 add by hins 内容：即使mq发送错误，也不影响业务
		try {
			Args.notNull(type, "type不能为null");
			Args.notNull(obj, "obj不能为null");
			String json = JSON.toJSONString(obj);
			send(type, json);
		} catch (Exception e) {
			logger.info("mq#producer#sendJSON | sendJSON异常 | obj: {}, type: {}, message: {}", obj, type, e.getMessage(), e);
		}

	}
	
	/**
	 * 发送bytes数据类型的消息
	 * 
	 * @param type 业务类型
	 * @param obj 数据对象obj
	 */
	public void send(String type, Object obj) {
		Args.notNull(type, "type不能为null");
		Args.notNull(obj, "obj不能为null");
		
		byte[] bytes = null;
		try {
			bytes = SerializeUtil.serialize(obj);
		} catch (Exception e) {
			logger.error("mq#producer#send | parse object to bytes error", e);
			throw new RuntimeException(e);
		}
		
		Message msg = new Message(
		// Message Topic
				MQSetting.getQueueName(),
				// Message Tag,
				// 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在ONS服务器过
				type,
				// Message Body
				// 任何二进制形式的数据，ONS不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方
				bytes);

		// 设置代表消息的业务关键属性，请尽可能全局唯一。
		// 以方便您在无法正常收到消息情况下，可通过ONS Console查询消息并补发。
		// 注意：不设置也不会影响消息正常收发
		msg.setKey(UUID.randomUUID().toString().replaceAll("-", ""));

		// 发送消息，只要不抛异常就是成功
		SendResult sendResult = producer.send(msg);

		logger.info("mq#producer#send | message has been sent | result: {}, type: {},  obj: {}", sendResult, type, JsonUtils.toJSONString(obj));
	}
}
