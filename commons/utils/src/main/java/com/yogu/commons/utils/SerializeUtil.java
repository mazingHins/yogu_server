package com.yogu.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化、反序列化工具 <br>
 *
 * @author JFan 2015年8月19日 下午4:31:06
 */
public class SerializeUtil {

	/**
	 * 序列化
	 * 
	 * @throws IOException
	 */
	public static byte[] serialize(Object object) throws IOException {
		// 序列化
		ByteArrayOutputStream baos = new ByteArrayOutputStream();// auto close
		ObjectOutputStream oos = new ObjectOutputStream(baos);// auto close
		oos.writeObject(object);
		byte[] bytes = baos.toByteArray();
		return bytes;
	}

	/**
	 * 反序列化
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object unserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		// 反序列化
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);// auto close
		ObjectInputStream ois = new ObjectInputStream(bais);// auto close
		return ois.readObject();
	}

}
