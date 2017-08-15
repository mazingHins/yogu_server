package com.yogu.commons.utils.img;

import org.apache.commons.io.FileUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * 图片工具类。
 * @author linyi 2015/7/1
 * @see <a href="http://luoyahu.iteye.com/blog/1312043">参考地址</a>
 */
public class ImageUtil {

    public static final String IMAGE_FORMAT_JPG = "jpg";

    /**
     * 图片缩放
     * @param originalFile 源文件名，完整路径
     * @param resizedFile 输出文件名，完整路径
     * @param newWidth 新的宽度
     * @param quality 压缩率，0~1.0
     * @throws java.io.IOException
     */
    public static void resize(File originalFile, File resizedFile,
                              int newWidth, float quality) throws IOException {
        byte[] input = FileUtils.readFileToByteArray(originalFile);
        byte[] output = resize(input, newWidth, quality);
        FileUtils.writeByteArrayToFile(resizedFile, output, false);
    }

    /**
     * 图片缩放
     * @param input 输入数据
     * @param newWidth 新的宽度
     * @param quality 压缩率，0~1.0
     * @return 返回缩放后的图片数据
     * @throws java.io.IOException
     */
    public static byte[] resize(byte[] input, int newWidth, float quality) throws IOException {

        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(input));

        int iWidth = bi.getWidth();
        int iHeight = bi.getHeight();
        int newHeight = (newWidth * iHeight) / iWidth;
        return resize(bi, newWidth, newHeight, quality);
    }

    /**
     * 执行缩放
     * @param resizedImage 缩放后的 Image
     * @param quality 压缩率，0~1.0
     * @return 返回缩放后的图片数据
     * @throws java.io.IOException
     */
    private static byte[] resize(Image resizedImage, float quality) throws IOException {
        if (quality > 1) {
            throw new IllegalArgumentException(
                    "Quality has to be between 0 and 1");
        }
        // This code ensures that all the pixels in the image are loaded.
        Image temp = new ImageIcon(resizedImage).getImage();

        // Create the buffered image.
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Copy image to buffered image.
        Graphics g = bufferedImage.createGraphics();

        // Clear background and paint the image.
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        // Soften.
        float softenFactor = 0.05f;
        float[] softenArray = {0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0};
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        // Write the jpeg to a file.
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ImageWriter writer = ImageIO.getImageWritersByFormatName(IMAGE_FORMAT_JPG).next();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // Needed see javadoc
        param.setCompressionQuality(quality); // Highest quality
        writer.setOutput(new MemoryCacheImageOutputStream(out));
        IIOImage outputImage = new IIOImage(bufferedImage, null, null);
        writer.write(null, outputImage, param);
        writer.dispose();

        byte[] content = out.toByteArray();
        out.close();
        return content;
    }


    /**
     * 图片缩放
     * @param input 输入数据
     * @param width 新的宽度
     * @param height 新的高度
     * @param quality 压缩率，0~1.0
     * @return 返回缩放后的图片数据
     * @throws java.io.IOException
     */
    public static byte[] resize(byte[] input, int width, int height, float quality) throws IOException {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(input);
        // 对图片进行缩放
        BufferedImage srcImg = ImageIO.read(inputStream);
        return resize(srcImg, width, height, quality);
    }

    /**
     * 图片缩放
     * @param srcImg 图片数据
     * @param width 新的宽度
     * @param height 新的高度
     * @param quality 压缩率，0~1.0（暂时不起作用）
     * @return 返回缩放后的图片数据
     * @throws java.io.IOException
     */
    public static byte[] resize(BufferedImage srcImg, int width, int height, float quality) throws IOException {
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
                0, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, IMAGE_FORMAT_JPG, outputStream);
        byte[] content = outputStream.toByteArray();
        outputStream.close();
        return content;
    }

    /**
     * 截取垂直中间位置的图片。
     * 例如：750x500 的图片，截取中间高度为350的图片，假设原点坐标是(0,0)，截取区域：
     * (0,75) ~ (749,424)
     * @param input 图片数据
     * @param height 指定高度，如果超出图片高度，抛出异常
     * @return 返回指定高度的数据
     * @throws java.io.IOException 出错抛出异常
     * @see <a href="http://www.cnblogs.com/liyunqi007/archive/2011/11/23/2260052.html">参考地址</a>
     */
    public static byte[] cutVerticalMiddle(byte[] input, int height) throws IOException {
    	return cutVerticalMiddle(input, height, false);
    }

    /**
     * 截取垂直中间位置的图片。
     * 例如：750x500 的图片，截取中间高度为350的图片，假设原点坐标是(0,0)，截取区域：(0,75) ~ (749,424)
     *
     * 2015-09-18 JFan：原cutVerticalMiddle方法逻辑不变，增加accept参数（请查看说明）
     *
     * @param input 图片数据
     * @param height 指定高度，如果超出图片高度，由accept决定是否抛出异常
     * @param accept 如果高度不足时，是否立即返回（false则抛出异常）
     * @return 返回指定高度的数据
     * @throws java.io.IOException 出错抛出异常
     * @see <a href="http://www.cnblogs.com/liyunqi007/archive/2011/11/23/2260052.html">参考地址</a>
     */
    public static byte[] cutVerticalMiddle(byte[] input, int height, boolean accept) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(input));
        int srcWidth = bi.getWidth();
        int srcHeight = bi.getHeight();
		if (srcHeight < height) {
			if (accept)
				return input;
			throw new IllegalArgumentException("New height is bigger than source height: " + height + " > " + srcHeight);
        }
        if (height == srcHeight) {
            return input;
        }

        // 最小坐标点是 (0,0)
        int x = 0;
        int y = (srcHeight - height) / 2;
        Image image = bi.getScaledInstance(srcWidth, srcHeight,
                Image.SCALE_DEFAULT);
        // 四个参数分别为图像起点坐标和宽高
        // 即: CropImageFilter(int x,int y,int width,int height)
        ImageFilter cropFilter = new CropImageFilter(x, y, srcWidth, height);
        Image img = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(image.getSource(),
                        cropFilter));
        BufferedImage tag = new BufferedImage(srcWidth, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(img, 0, 0, srcWidth, height, null); // 绘制切割后的图
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(tag, IMAGE_FORMAT_JPG, out);
        byte[] content = out.toByteArray();
        out.close();
        return content;
    }

    /**
     * 截取水平中间位置的图片。
     * 例如：750x500 的图片，截取中间高度为350的图片，假设原点坐标是(0,0)，截取区域：(0,75) ~ (749,424)
     *
     * 2015-09-18 JFan：原cutVerticalMiddle方法逻辑不变，增加accept参数（请查看说明）
     *
     * @param input 图片数据
     * @param width 指定宽度，如果超出图片宽度，由accept决定是否抛出异常
     * @param accept 如果高度不足时，是否立即返回（false则抛出异常）
     * @return 返回指定高度的数据
     * @throws java.io.IOException 出错抛出异常
     * @see <a href="http://www.cnblogs.com/liyunqi007/archive/2011/11/23/2260052.html">参考地址</a>
     */
    public static byte[] cutHorizontalMiddle(byte[] input, int width, boolean accept) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(input));
        int srcWidth = bi.getWidth();
        int srcHeight = bi.getHeight();
        if (srcWidth < width) {
            if (accept)
                return input;
            throw new IllegalArgumentException("New width is bigger than source width: " + width + " > " + srcWidth);
        }
        if (width == srcWidth) {
            return input;
        }

        // 最小坐标点是 (0,0)
        int x = (srcWidth - width) / 2;
        int y = 0;
        Image image = bi.getScaledInstance(srcWidth, srcHeight,
                Image.SCALE_DEFAULT);
        // 四个参数分别为图像起点坐标和宽高
        // 即: CropImageFilter(int x,int y,int width,int height)
        ImageFilter cropFilter = new CropImageFilter(x, y, width, srcHeight);
        Image img = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(image.getSource(),
                        cropFilter));
        BufferedImage tag = new BufferedImage(width, srcHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(img, 0, 0, width, srcHeight, null); // 绘制切割后的图
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(tag, IMAGE_FORMAT_JPG, out);
        byte[] content = out.toByteArray();
        out.close();
        return content;
    }

    /**
     * 确保图片的尺寸符合要求，不符合要求的话进行 resize
     * @param input 源图片
     * @param width 要求宽
     * @param height 要求宽
     * @return 如果图片的宽、高刚好等于 width、height，直接返回原图，否则缩放到 width x height
     * @throws java.io.IOException
     * @author ten 2015/10/15
     */
    public static byte[] ensureWidthHeightAndResize(byte[] input, int width, int height) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(input));
        int srcWidth = bi.getWidth();
        int srcHeight = bi.getHeight();
        if (srcWidth == width && srcHeight == height) {
            return input;
        }

        return resize(bi, width, height, 1.0f);
    }

    /**
     * 返回图片的宽高，array[0] = 宽，array[1] = 高
     * @param input 图片数据
     * @return array[0] = 宽，array[1] = 高
     * @throws java.io.IOException
     */
    public static int[] getImageSize(byte[] input) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(input));
        int srcWidth = bi.getWidth();
        int srcHeight = bi.getHeight();
        return new int[] {srcWidth, srcHeight};
    }

    /**
     * 把图片裁剪成正方形，然后缩放到指定尺寸。
     * 比如假设原点坐标是(0,0), 800 x 400 的图片，要缩放到120 x 120，
     * 先裁剪出中间的 (200,0)~(599,399)，然后缩放到 120 x 120
     * @param input 图片数据
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @return 返回新的图片数据
     * @throws java.io.IOException
     */
    public static byte[] cutSquareAndResize(byte[] input, int width, int height) throws IOException {
        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(input));
        int srcWidth = bi.getWidth();
        int srcHeight = bi.getHeight();
        int x = 0, y = 0;
        int edgeLength = 0;  // 正方形边长
        if (srcWidth >= srcHeight) {
            edgeLength = srcHeight;
            x = (srcWidth - srcHeight) / 2;
        }
        else {
            edgeLength = srcWidth;
            y = (srcHeight - srcWidth) / 2;
        }
        Image image = bi.getScaledInstance(srcWidth, srcHeight,
                Image.SCALE_DEFAULT);
        // 四个参数分别为图像起点坐标和宽高
        // 即: CropImageFilter(int x,int y,int width,int height)
        ImageFilter cropFilter = new CropImageFilter(x, y, edgeLength, edgeLength);
        Image img = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(image.getSource(),
                        cropFilter));
        BufferedImage tag = new BufferedImage(edgeLength, edgeLength, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(img, 0, 0, edgeLength, edgeLength, null); // 绘制切割后的图
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(tag, IMAGE_FORMAT_JPG, out);
        byte[] content = out.toByteArray();
        out.close();
        return resize(content, width, height, 1.0f);
    }

    public static void main(String[] args) throws IOException {
//        File originalImage = new File("E:\\temp\\pic\\dish\\1.jpg");
//        resize(originalImage, new File("E:\\temp\\pic\\dish\\1_474_7_2.jpg"), 474, 0.7f);
//        resize(originalImage, new File("E:\\temp\\pic\\dish\\1_474_10_2.jpg"), 474, 1f);

//        originalImage = new File("e:\\temp\\pic\\flow\\2.jpg");
//        resize(originalImage, new File("e:\\temp\\pic\\flow\\temp\\22_07.jpg"), 474, 0.7f);
//        resize(originalImage, new File("e:\\temp\\pic\\flow\\temp\\22_10.jpg"), 474, 1f);
        /*
        {
            byte[] input = FileUtils.readFileToByteArray(new File("E:\\temp\\pic\\dish\\1.jpg"));
            byte[] middle = cutVerticalMiddle(input, 300);
            byte[] square = cutSquareAndResize(input, 200, 200);
            FileUtils.writeByteArrayToFile(new File("E:\\temp\\pic\\dish\\1_store.jpg"), middle, false);
            FileUtils.writeByteArrayToFile(new File("E:\\temp\\pic\\dish\\1_logo.jpg"), square, false);
        }

        {
            byte[] input = FileUtils.readFileToByteArray(new File("E:\\temp\\pic\\dish\\2.jpg"));
            byte[] middle = cutVerticalMiddle(input, 300);
            FileUtils.writeByteArrayToFile(new File("E:\\temp\\pic\\dish\\2_store.jpg"), middle, false);
        }

        {
            byte[] input = FileUtils.readFileToByteArray(new File("E:\\temp\\pic\\dish\\3.jpg"));
            byte[] middle = cutVerticalMiddle(input, 300);
            FileUtils.writeByteArrayToFile(new File("E:\\temp\\pic\\dish\\3_store.jpg"), middle, false);
        }
        */
        byte[] pic = FileUtils.readFileToByteArray(new File("E:\\temp\\img\\test\\source.jpg"));
        byte[] tmp = pic;
        int width = 710;
        int height = 350;
        tmp = ImageUtil.cutHorizontalMiddle(tmp, width, true);
        tmp = ImageUtil.cutVerticalMiddle(tmp, height, true);
        FileUtils.writeByteArrayToFile(new File("E:\\temp\\img\\test\\topic_710x350.jpg"), tmp, false);


        width = 170;
        height = 130;
//        pic = FileUtils.readFileToByteArray(new File("E:\\temp\\img\\test\\source.jpg"));
        tmp = ImageUtil.resize(pic, width, 1);
        tmp = ImageUtil.cutVerticalMiddle(tmp, height, true);
        FileUtils.writeByteArrayToFile(new File("E:\\temp\\img\\test\\fav_170x130.jpg"), tmp, false);

        width = 750;
        height = 565;
//        pic = FileUtils.readFileToByteArray(new File("E:\\temp\\img\\test\\source.jpg"));
        tmp = ImageUtil.resize(pic, width, 1);
        tmp = ImageUtil.cutVerticalMiddle(tmp, height, true);
        FileUtils.writeByteArrayToFile(new File("E:\\temp\\img\\test\\card_750x565.jpg"), tmp, false);
    }
}