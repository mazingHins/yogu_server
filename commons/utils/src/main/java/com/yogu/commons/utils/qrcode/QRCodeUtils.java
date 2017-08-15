/**
 * 
 */
package com.yogu.commons.utils.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码、条形码 工具类 <br>
 * 包含生成和解读
 *
 * @author JFan 2015年9月14日 下午3:11:06
 */
public final class QRCodeUtils {

	/** 二维码图片的最小宽度 */
	private static final int MIN_WIDTH = 30;
	/** 二维码图片的最小高度 */
	private static final int MIN_HEIGHT = 30;
	/** 编码 */
	private static final String encode = "UTF-8";
	/** 二维码的颜色（黑） */
	private static final int QR_COLOR = 0xff000000;
	/** 二维码背景色（白） */
	private static final int BACK_COLOR = 0xfffffff;
	/** 二维码写码器（factory） */
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

	// ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
	// TODO 目前只是可用的状态，先提供出去使用，还有校验等事情没做
	// ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

	/**
	 * 根据内容生成指定宽高的二维码图片数据
	 * 
	 * @param body 二维码中的内容；不允许null
	 * @param width 生成的二维码宽度；最小宽度为{@value #MIN_WIDTH}
	 * @param height 生成的二维码高度；最小高度为{@value #MIN_HEIGHT}
	 * @param formatName 需要转成成什么格式
	 * @return 二维码内容
	 * @throws Exception
	 */
	public static byte[] encode(String body, int width, int height, String formatName) throws Exception {
		// 生成二维码（像素数组）
		int[] pixels = encode(body, width, height, null, 0);
		return toImgBody(pixels, width, height, formatName);
	}

	/**
	 * 根据内容生成指定宽高的二维码图片数据<br>
	 * 同时在二维码中间嵌入一张图片（可选）
	 * 
	 * @param body 二维码中的内容
	 * @param width 生成的二维码宽度；最小宽度为{@value #MIN_WIDTH}
	 * @param height 生成的二维码高度；最小高度为{@value #MIN_HEIGHT}
	 * @param formatName 需要转成成什么格式
	 * @param srcBody 需要嵌入的图片内容；可以为null
	 * @param srcwidth 需要嵌入的图片在二维码中的宽度；srcBody不为null的情况下必填；最大值不能超过width的1/4
	 * @param srcHeight 需要嵌入的图片在二维码中的高度；srcBody不为null的情况下必填；最大值不能超过height的1/4
	 * @return 二维码内容
	 * @throws Exception
	 */
	public static byte[] encode(String body, int width, int height, String formatName, byte[] srcBody, int srcwidth, int srcHeight)
			throws Exception {
		// 读取图片
		BufferedImage scaleImage = scale(srcBody, srcwidth, srcHeight, true);
		// 将图片转成 像素数组
		int[][] srcPixels = new int[srcwidth][srcHeight];
		for (int i = 0; i < scaleImage.getWidth(); i++)
			for (int j = 0; j < scaleImage.getHeight(); j++)
				srcPixels[i][j] = scaleImage.getRGB(i, j);

		// 生成二维码（像素数组）
		int[] pixels = encode(body, width, height, srcPixels, srcwidth);
		return toImgBody(pixels, width, height, formatName);
	}

	/**
	 * 根据内容生成指定宽高的二维码图片数据<br>
	 * 同时在二维码中间嵌入一张图片（可选）
	 * 
	 * @param body 二维码中的内容
	 * @param width 生成的二维码宽度；最小宽度为{@value #MIN_WIDTH}
	 * @param height 生成的二维码高度；最小高度为{@value #MIN_HEIGHT}
	 * @param srcImgPixels 需要嵌入的图片内容（rgb像素数组）；可以为null
	 * @param srcwidth 需要嵌入的图片在二维码中的宽度；srcBody不为null的情况下必填；最大值不能超过width的1/4
	 * @return 二维码图片的像素数组
	 * @throws Exception
	 */
	public static int[] encode(String body, int width, int height, int[][] srcImgPixels, int srcwidth) throws Exception {
		int IMAGE_HALF_WIDTH = srcwidth / 2;
		int FRAME_WIDTH = 2;

		// 初始化参数
		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hint.put(EncodeHintType.CHARACTER_SET, encode);
		// 生成二维码
		BitMatrix matrix = mutiWriter.encode(body, BarcodeFormat.QR_CODE, width, height, hint);
		// 二维矩阵转为一维像素数组
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				// 读取图片
				if (x > halfW - IMAGE_HALF_WIDTH && x < halfW + IMAGE_HALF_WIDTH && y > halfH - IMAGE_HALF_WIDTH
						&& y < halfH + IMAGE_HALF_WIDTH) {
					pixels[y * width + x] = srcImgPixels[x - halfW + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
				}
				// 在图片四周形成边框
				else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH - IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
					pixels[y * width + x] = BACK_COLOR;
				} else {
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? QR_COLOR : BACK_COLOR;
				}
			}
		}

		return pixels;
	}

	// ####
	// ##
	// ####

	/**
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
	 * 
	 * @param srcImageBody 图片数据
	 * @param height 目标高度
	 * @param width 目标宽度
	 * @param hasFiller 比例不对时是否需要补白：true为补白; false为不补白;
	 * @throws IOException
	 */
	private static BufferedImage scale(byte[] srcImageBody, int height, int width, boolean hasFiller) throws IOException {
		double ratio = 0.0; // 缩放比例
		ByteArrayInputStream bis = new ByteArrayInputStream(srcImageBody);
		BufferedImage srcImage = ImageIO.read(bis);
		Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			else
				graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	/**
	 * 将像素数组 转成 流数据
	 * 
	 * @param pixels 一维像素数据
	 * @param width 宽度
	 * @param height 高度
	 * @param formatName 需要转成成什么格式
	 * @throws Exception
	 */
	private static byte[] toImgBody(int[] pixels, int width, int height, String formatName) throws Exception {
		// 2 img
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);
		// 2 stream
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ImageIO.write(image, formatName, bao);
		byte[] bytes = bao.toByteArray();
		return bytes;
	}

}
