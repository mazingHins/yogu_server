package com.yogu.remote.config.fs.api;

/**
 * 文件存储、图片存储接口。
 * （1）提供统一的存储接口；
 * （2）文件名组成：name=云提供商ID + 机房ID + 类别（36进制） + UUID.后缀
 *     名称加上图片大小：name_28x28.jpg
 * @author linyi 2015/5/28.
 */
public interface FileStore {

    /**
     * 存储非图片文件，失败时抛出异常
     * @param filename - 保存到文件系统的文件名，不能为空
     * @param fileCategory - 文件目录，不能为空，取值是 FileCategory.STATIC
     * @param content - 文件内容，不能为空
     */
    void storeFile(String filename, FileCategory fileCategory, byte[] content);

    /**
     * 保存图片文件
     * @param filename - 保存到文件系统的文件名，不能为空
     * @param fileCategory - 文件目录，不能为空，取值是 FileCategory.SHOP/PROFILE/FEED
     * @param content - 文件内容，不能为空
     */
    void storeImage(String filename, FileCategory fileCategory, byte[] content);

    /**
     * 根据文件名，返回完整的HTTP地址
     * @param filename - 文件名
     * @return 返回HTTP地址
     */
    String getHttpUrl(String filename);
}
