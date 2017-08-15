package com.yogu.commons.utils.img;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

/**
 * 图片尺寸的工具类
 * @author linyi 2015/6/25.
 */
public class ImageSizeHelper {

    /**
     * 在mazing系统中的保存名称
     */
    private String fsName;

    /**
     * 图片内容
     */
    private byte[] content;

    /**
     * 原始文件名
     */
    private String originFilename;

    /**
     * 文件后缀
     */
    private String suffix;

    /** 图片宽度 */
    private int width;

    /** 图片高度 */
    private int height;

    /**
     * 图片格式
     */
    private String format;

    public ImageSizeHelper(String fsName, byte[] content) throws IOException {
        this.fsName = fsName;
        this.content = content;
        init();
    }

    /**
     * 初始化参数
     * @throws IOException
     */
    private void init() throws IOException {
        if (StringUtils.isEmpty(this.fsName)) {
            this.fsName = UUID.randomUUID().toString();
        }

        this.format = "." + getFormat();
        int pos = fsName.lastIndexOf('.');
        if (pos > 0) {
            // 把文件后缀和文件名分离
            originFilename = fsName.substring(0, pos);
            suffix = fsName.substring(pos);
        }
        else {
            originFilename = fsName;
            suffix = format;
        }

        // 如果文件名本身含有 "_宽x高"，去掉宽高及下划线
        pos = originFilename.lastIndexOf('_');
        if (pos > 0 && originFilename.indexOf('x', pos) > 0) {
            originFilename = originFilename.substring(0, pos);
        }

    }

    /**
     * 返回文件图片文件格式，不带英文的点(.)
     * @return 取值：jpg,png
     * @throws IOException
     */
    public String getFormat() throws IOException {
        ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(content));

        // get all currently registered readers that recognize the image format
        Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

        if (!iter.hasNext()) {
            throw new RuntimeException("No readers found!");
        }
        ImageReader reader = iter.next();
        String format = reader.getFormatName();

        BufferedImage bi = ImageIO.read(iis);
        width = bi.getWidth();
        height = bi.getHeight();
        format = format.toLowerCase();
        if ("jpeg".equals(format)) {
            format = "jpg";
        }
        return format;
    }

	public static ImageReader imgReader(byte[] content) throws IOException {
		ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(content));

		// get all currently registered readers that recognize the image format
		Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);

		if (!iter.hasNext()) {
			throw new RuntimeException("No readers found!");
		}
		ImageReader reader = iter.next();
		return reader;
	}

    /**
     * 返回带原始尺寸的文件名
     * @return 返回文件名，格式: xxxx_高x宽.后缀
     */
    public String getOriginFileNameWithSize() {
        return originFilename + "_" + width + "x" + height + suffix;
    }

    /**
     * 返回缩放到固定宽度下的文件名
     * @param newWidth 指定宽度，单位：像素
     * @return 返回文件名，格式: xxxx_高x宽.后缀
     */
    public String getFileNameForWidth(int newWidth) {
        double scale = (double) newWidth / (double) width;
        int newHeight = (int)(height * scale);
        return originFilename + "_" + newWidth + "x" + newHeight + suffix;
    }

    /**
     * 返回缩放到固定高度下的文件名
     * @param newHeight 指定高度，单位：像素
     * @return 返回文件名，格式: xxxx_高x宽.后缀
     */
    public String getFileNameForHeight(int newHeight) {
        double scale = (double) newHeight / (double) height;
        int newWidth = (int) (width * scale);
        return originFilename + "_" + newWidth + "x" + newHeight + suffix;
    }

	/**
	 * @return suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	public static void main(String[] args) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("E:\\temp\\img\\Penguins.jpg"));
        String fsName = "";
        ImageSizeHelper sizeHelper = new ImageSizeHelper(fsName, bytes);
        System.out.println(sizeHelper.getOriginFileNameWithSize());
        System.out.println(sizeHelper.getFileNameForWidth(120));
        System.out.println(sizeHelper.getFileNameForHeight(100));
    }

}
