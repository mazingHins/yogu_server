package com.yogu.commons.utils.img;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.yogu.commons.utils.FileUtils;

/**
 * 
 * 生成水印
 * 
 */
public class ImageMarkUtil {

	/** 水印透明度 */
	private static float alpha = 1.0f;
	/** 水印图片旋转角度 */
	private static double degree = 0;
	/** 水印图片间隔 */
	private static int interval = 20;

	/**
	 * 设置水印参数，不设置就使用默认值
	 * @param alpha 水印透明度
	 * @param degree 水印图片旋转角度 *
	 * @param interval 水印图片间隔
	 */
	public static void setImageMarkOptions(float alpha, int degree, int interval) {
		if (alpha != 0.0f) {
			ImageMarkUtil.alpha = alpha;
		}
		if (degree != 0f) {
			ImageMarkUtil.degree = degree;
		}
		if (interval != 0f) {
			ImageMarkUtil.interval = interval;
		}

	}

	/**
	 * 给图片添加水印图片
	 * @param logoMarkPath 水印图片路径
	 * @param srcImgPath 源图片路径
	 * @param targerPath 目标图片路径
	 */
	public static void markByImg(String logoMarkPath, String srcImgPath, String targerPath){
		try {
			//读取源图片
			File file = new File(srcImgPath);
			InputStream in = new FileInputStream(file);
			//加水印并转为字节数组
			byte[] markByByte = markByByte(logoMarkPath, FileUtils.readStream(in), file.getName().split("\\.")[1]);
			
			//输出水印图片
			File outFile = new File(targerPath);
			OutputStream out = new FileOutputStream(outFile);
			out.write(markByByte);
//			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 给图片添加水印图片、可设置水印图片旋转角度
	 * @param logoMarkPath 水印图片路径
	 * @param srcImgPath 源图片路径
	 */
	public static byte[] markByByte(String logoMarkPath, byte[] image, String ext){
		ByteArrayInputStream in = null;
		ByteArrayOutputStream os = null;
		try {
			in = new ByteArrayInputStream(image);
			Image srcImg = ImageIO.read(in);
			ByteArrayInputStream input = new ByteArrayInputStream(new byte[10]);
			ImageIO.read(input);
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), srcImg.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			// 1、得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 2、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg.getHeight(null), Image.SCALE_SMOOTH), 0,
					0, null);
			// 3、设置水印旋转
			if (0 != degree) {
				g.rotate(Math.toRadians(degree), (double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);
			}

			// 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(logoMarkPath);

			// 5、得到Image对象。
			Image img = imgIcon.getImage();

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			// 6、水印图片的位置
//			for (int height = interval + imgIcon.getIconHeight(); height < buffImg.getHeight(); height = height
//					+ interval + imgIcon.getIconHeight()) {
//				for (int weight = interval + imgIcon.getIconWidth(); weight < buffImg.getWidth(); weight = weight
//						+ interval + imgIcon.getIconWidth()) {
//					g.drawImage(img, weight - imgIcon.getIconWidth(), height - imgIcon.getIconHeight(), null);
//				}
//			}
			int widthCount = buffImg.getWidth() % imgIcon.getIconWidth() == 0 ? buffImg.getWidth() / imgIcon.getIconHeight() : buffImg.getWidth() / imgIcon.getIconHeight() + 1;
			int heightCount = buffImg.getHeight() % imgIcon.getIconHeight() == 0 ? buffImg.getHeight() / imgIcon.getIconHeight() : buffImg.getHeight() / imgIcon.getIconHeight() + 1;
			for (int x = 0; x <= widthCount; x++) {
				for(int y = 0; y <= heightCount; y++){
					g.drawImage(img, x * imgIcon.getIconWidth(), y * imgIcon.getIconHeight(), null);
				}
			}
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			// 7、释放资源
			g.dispose();

			os = new ByteArrayOutputStream();
			
			// 8、生成图片
			ImageIO.write(buffImg, ext, os);
			System.out.println("图片完成添加水印图片");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return os.toByteArray();
	}
	
	public static void main(String[] args) throws IOException {

		System.out.println("..添加水印图片开始...");
		/**
		 * logoMarkPath 水印图片地址 加水印图片地址 上传成功后文件地址
		 */
		// 修改默认参数
		String logoMarkPath = "C:\\logo.png"; // 测试水印图片
		String srcImgPath = "C:\\水印前.jpg";// 测试需加水印图片
		String targerPath = "C:\\水印后.jpg";
		ImageMarkUtil.markByImg(logoMarkPath, srcImgPath, targerPath);
		System.out.println("..添加水印图片结束...");
		
	}

}