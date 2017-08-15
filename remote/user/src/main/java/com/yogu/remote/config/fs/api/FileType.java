package com.yogu.remote.config.fs.api;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件类型
 * @author linyi 2015/5/28.
 */
public enum FileType {
    JPG(".jpg", 1), // jpg
    JPEG(".jpeg", 6), // jpeg
    PNG(".png", 2),
    GIF(".webp", 3),
    ICO(".ico", 4),
    XLS(".xls", 5);


    private String suffix;

    private int type;

    private FileType(String suffix, int type) {
        this.suffix = suffix;
        this.type = type;
    }

    public String getSuffix() {
        return suffix;
    }

    public int getType() {
        return type;
    }

    private static Map<String, FileType> map = new HashMap<>(8);

    static {
        for (FileType t : FileType.values()) {
            map.put(t.getSuffix(), t);
        }
    }

    /**
     * 根据后缀返回图片类型
     * @param suffix - 文件后缀，例如 .jpg, .png
     * @return 找不到返回null
     */
    public static FileType fromSuffix(String suffix) {
        if (suffix == null) {
            return null;
        }
        if (!suffix.startsWith(".")) {
            suffix = "." + suffix;
        }
        return map.get(suffix.toLowerCase());
    }

    /**
     * 根据文件名返回图片类型
     * @param fileName - 文件名
     * @return 找不到返回null
     */
    public static FileType fromFileName(String fileName) {
        if (fileName != null) {
            int pos = fileName.lastIndexOf(".");
            String name = (pos >= 0 ? fileName.substring(pos) : fileName);
            return fromSuffix(name);
        }
        return null;
    }
}
