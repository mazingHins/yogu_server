package com.yogu.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @Description: 文件操作相关工具类
 * @author Hins
 * @date 2015年7月24日 下午6:48:53
 */
public class FileUtils {
	
	 /** 
     * 读取流 
     * @param inStream 
     * @return 字节数组 
     * @throws Exception 
     */  
    public static byte[] readStream(InputStream in) throws IOException {  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = -1;  
        while ((len = in.read(buffer)) != -1) {  
        	out.write(buffer, 0, len);  
        }  
        out.close();  
        in.close();  
        return out.toByteArray();  
    }  
    
}
