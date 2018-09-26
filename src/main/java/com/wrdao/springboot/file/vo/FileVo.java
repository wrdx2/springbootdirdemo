package com.wrdao.springboot.file.vo;

import com.wrdao.springboot.common.vo.BaseVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_bas")
public class FileVo extends BaseVo {

    /**** 是否有缩略图('Y'/'N') ****/
    private String thumb;

    /**** 缩略图文件ID ****/
    private String thumbFileId;

    /**** 类别id ****/
    private String fileCateId;

    /**** 扩展名 ****/
    private String extension;

    /**** 文件类型 ****/
    private String fileType;

    /**** 上传者ip ****/
    private String ipAddr;

    /**** 文件存储id ****/
    @Id
    @Column(columnDefinition ="char(32)")
    private String fileId;

    /**** 上传的文件名 ****/
    private String fileName;

    /**** 存储路径 ****/
    private String filePath;

    /**** 文件MIME类型 ****/
    private String contentType;

    /**** 显示顺序 ****/
    private int displayIndex;

    /**** 下载次数 ****/
    private int downloadNum;

    /**** 文件大小 ****/
    @Column(columnDefinition ="BIGINT")
    private long size;

    /**
     * 获取是否有缩略图('Y'/'N')
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * 设置是否有缩略图('Y'/'N')
     */
    public void setThumb(String thumb){
        this.thumb = thumb;
    }

    public String getThumbFileId() {
        return thumbFileId;
    }

    public void setThumbFileId(String thumbFileId) {
        this.thumbFileId = thumbFileId;
    }

    /**
     * 获取类别id
     */
    public String getFileCateId() {
        return fileCateId;
    }

    /**
     * 设置类别id
     */
    public void setFileCateId(String fileCateId){
        this.fileCateId = fileCateId;
    }
    /**
     * 获取扩展名
     */
    public String getExtension() {
        return extension;
    }

    /**
     * 设置扩展名
     */
    public void setExtension(String extension){
        this.extension = extension;
    }
    /**
     * 获取文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置文件类型
     */
    public void setFileType(String fileType){
        this.fileType = fileType;
    }
    /**
     * 获取上传者ip
     */
    public String getIpAddr() {
        return ipAddr;
    }

    /**
     * 设置上传者ip
     */
    public void setIpAddr(String ipAddr){
        this.ipAddr = ipAddr;
    }
    /**
     * 获取文件存储id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置文件存储id
     */
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    /**
     * 获取上传的文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置上传的文件名
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    /**
     * 获取存储路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置存储路径
     */
    public void setFilePath(String filePath){
        this.filePath = filePath;
    }
    /**
     * 获取文件MIME类型
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * 设置文件MIME类型
     */
    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    /**
     * 获取显示顺序
     */
    public int getDisplayIndex() {
        return displayIndex;
    }

    /**
     * 设置显示顺序
     */
    public void setDisplayIndex(int displayIndex){
        this.displayIndex = displayIndex;
    }
    /**
     * 获取下载次数
     */
    public int getDownloadNum() {
        return downloadNum;
    }

    /**
     * 设置下载次数
     */
    public void setDownloadNum(int downloadNum){
        this.downloadNum = downloadNum;
    }
    /**
     * 获取文件大小
     */
    public long getSize() {
        return size;
    }

    /**
     * 设置文件大小
     */
    public void setSize(long size){
        this.size = size;
    }
}
