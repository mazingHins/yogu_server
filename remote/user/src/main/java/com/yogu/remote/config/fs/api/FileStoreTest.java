package com.yogu.remote.config.fs.api;

import org.apache.commons.io.FileUtils;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.util.UUID;

/**
 * 文件测试
 * @author linyi 2015/8/5.
 */
public class FileStoreTest {

    public static void main(String[] args) throws Exception {
        if (args.length <= 0 || args[0].lastIndexOf('.') < 0) {
            System.out.println("e.g.: FileStoreTest a.jpg");
            System.exit(0);
        }
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext(
                "classpath*:META-INF/mazing-applicationContext.xml");
        byte[] bytes = FileUtils.readFileToByteArray(new File(args[0]));
        FileStore fileStore = FileStoreFactory.createFileStore();
        String filename = UUID.randomUUID().toString() + args[0].substring(args[0].lastIndexOf('.'));
        fileStore.storeImage(filename, FileCategory.STORE, bytes);
        System.out.println("filename: " + filename);
    }
}
