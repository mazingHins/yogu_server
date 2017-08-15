/**
 * 
 */
package com.yogu.mq;

/**
 * 
 * <br>
 * 
 * <br>
 * JFan - 2015年6月18日 下午6:48:38
 */
public interface ServerMsgNotice {

	/**
	 * 收到消息后，处理的方法。
	 * 消息可能会重复推送，根据 messageId 判断是否已经处理过。
	 * 如果发生异常，处理同一种消息的所有 listener 都会重新接收这条消息，不管之前是否成功处理过。
	 * @param messageId 消息ID，唯一
	 * @param message 消息内容
	 */
	public void notice(String messageId, String message);

}
