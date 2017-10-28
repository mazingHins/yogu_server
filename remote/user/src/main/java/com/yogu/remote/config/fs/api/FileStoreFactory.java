package com.yogu.remote.config.fs.api;

import com.yogu.cfg.GlobalSetting;
import com.yogu.remote.config.base.CloudSettings;
import com.yogu.remote.config.fs.service.AliyunOssStore;

/**
 * 工厂类，根据 {@link com.mazing.cfg.GlobalSetting#getCloud} 的值创建相应的 FileStore
 * @author linyi 2015/5/28.
 */
public class FileStoreFactory {

    /**
     * 创建文件存储的实体类，创建成功返回相应的实例，
     * 创建失败抛出异常。
     * @return
     * @throws IllegalStateException 如果不支持当前的cloud设置，抛出异常。
     */
    public static FileStore createFileStore() {
        // 阿里云
        if (GlobalSetting.CLOUD_ALIYUN.equals(GlobalSetting.getCloud())) {
            return new AliyunOssStore("https://oss-cn-shenzhen.aliyuncs.com","LTAIL5xJo5boi1kn", "ILMQJ2aKfGQJumyCAb32TgPdeGZRg2");
        }

//        // 仅用于本地测试
//        if (GlobalSetting.CLOUD_LOCAL.equalsIgnoreCase(GlobalSetting.getCloud())) {
//            return new LocalFileStore();
//        }

        throw new IllegalStateException("Not support cloud: " + GlobalSetting.getCloud());
    }

}
