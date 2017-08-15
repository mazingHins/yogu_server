package com.yogu.remote.config.fs.api;

/**
 * 常量定义
 * @author linyi 2015/5/28.
 */
public class FileStoreConstant {

    /**
     * 文件名里的分隔符
     */
    public static final String DELIMITER = "-";

    /**
     * 文件夹分隔符
     */
    public static final String PATH_DELIMITER = "/";

    // ------------- 以下bucket在 init.sql中定义
    // 规则：cloud_bucket_名称
    // 比如：ali_bucket_img 表示在阿里的图片bucket，通过 mz_config.config_key='ali_bucket_img' 获取真正的bucket名

    /**
     * aliyun oss / aws s3 的 bucket 名：static
     */
    public static final String BUCKET_STATIC = "static";

    /**
     * aliyun oss / aws s3 的图片 bucket 名：img
     * 图片有3个bucket
     */
    public static final String BUCKET_IMAGE = "img";

    /**
     * jpeg图片的压缩率
     */
    public static final float JPEG_QUALITY = 0.9f;
}
