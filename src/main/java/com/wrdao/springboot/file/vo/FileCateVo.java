package com.wrdao.springboot.file.vo;

import com.wrdao.springboot.common.vo.BaseVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "file_cate_dic")
public class FileCateVo extends BaseVo {

    /**** 文件类别id ****/
    @Id
    @Column(columnDefinition ="char(32)")
    private String fileCateId;

    /**** 类别名称 ****/
    private String fileCateName;

    /**** 存储目录 ****/
    private String dir;

    /**** 备注 ****/
    private String remark;

    /**
     * 获取文件类别id
     */
    public String getFileCateId() {
        return fileCateId;
    }

    /**
     * 设置文件类别id
     */
    public void setFileCateId(String fileCateId){
        this.fileCateId = fileCateId;
    }
    /**
     * 获取类别名称
     */
    public String getFileCateName() {
        return fileCateName;
    }

    /**
     * 设置类别名称
     */
    public void setFileCateName(String fileCateName){
        this.fileCateName = fileCateName;
    }
    /**
     * 获取存储目录
     */
    public String getDir() {
        return dir;
    }

    /**
     * 设置存储目录
     */
    public void setDir(String dir){
        this.dir = dir;
    }
    /**
     * 获取备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(String remark){
        this.remark = remark;
    }

}
