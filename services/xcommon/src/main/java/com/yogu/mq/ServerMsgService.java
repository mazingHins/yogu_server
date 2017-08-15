package com.yogu.mq;

/**
 * 服务器集群之间的消息推送 <br>
 * 
 * 应当基于AMQP协议
 * 
 * <br>
 * JFan - 2015年6月18日 下午6:26:12
 */
public interface ServerMsgService {

	/**
	 * 发送消息给集群上的其他机器
	 * 
	 * @param key 消息key
	 * @param message 消息内容
	 * @return 是否成功
	 */
	public boolean send(String key, String message);

	/**
	 * 登记通知处理类<br>
	 * 实现类需要注意线程安全问题
	 * 
	 * @param key 登记的KEY
	 * @param notice 通知处理类
	 */
	public void regNotice(String key, ServerMsgNotice notice);

}
