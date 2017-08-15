package com.yogu.remote.config.fs.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件描述，避免和 java.io.File 同名，加上VO后缀
 * @author linyi 2015/6/19.
 */
public class FileVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 图片ID（分配的ID） */
    private long fileId;

    /** 文件路径（不含文件名），文件存储在DB中是没有路径的。这个字段相当于扩展字段，存你想存的内容。 */
    private String path = "";

    /** 原始名称，含后缀 */
    private String name = "";

    /** 文件内容 */
    private byte[] content;

    /** 文件类型（1：webp、2：jpg、3：png、4：icon、100：excel、101：ppt） */
    private FileType fileType = FileType.PNG;

    /** 上传文件的用户ID */
    private long uid;

    /** 创建时间 */
    private Date createTime;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
