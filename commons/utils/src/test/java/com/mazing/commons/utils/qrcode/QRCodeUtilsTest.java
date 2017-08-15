/**
 * 
 */
package com.mazing.commons.utils.qrcode;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.yogu.commons.utils.qrcode.QRCodeUtils;

/**
 * 二维码测试 <br>
 *
 * @author JFan 2015年9月14日 下午4:49:55
 */
@Ignore
public class QRCodeUtilsTest {

	@Test
	public void test() throws Exception {
		byte[] body = QRCodeUtils.encode("啊啊啊啊啊啊啊啊啊啊", 300, 300, "jpg");
		FileUtils.writeByteArrayToFile(new File("c://aaa.jpg"), body);

		InputStream is = QRCodeUtilsTest.class.getResourceAsStream("./tubiao.jpg");
		byte[] srcBody = IOUtils.toByteArray(is);

		body = QRCodeUtils.encode("啊啊啊啊啊啊啊啊啊啊", 300, 300, "jpg", srcBody, 30, 30);
		FileUtils.writeByteArrayToFile(new File("c://bbb.jpg"), body);
	}

}
