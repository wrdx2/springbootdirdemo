package com.wrdao.springboot.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseVo implements Serializable {

    @Column(columnDefinition ="BIT")
    private byte available;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    private String creator;
    private String updater;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;

    public BaseVo initCreator(){
        this.available = 0;
        this.createtime = new Date();
        this.updatetime = new Date();

        if (SecurityContextHolder.getContext().getAuthentication() != null){
            this.creator = SecurityContextHolder.getContext().getAuthentication().getName();
            this.updater = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            this.creator = "unlogin";
            this.updater = "unlogin";
        }

        return this;
    }

    public BaseVo initUpdater(){
        //this.available = 0;
        this.updatetime = new Date();

        if (SecurityContextHolder.getContext().getAuthentication() != null){
            this.updater = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            this.updater = "unlogin";
        }

        return this;
    }

    public byte getAvailable() {
        return available;
    }

    public void setAvailable(byte available) {
        this.available = available;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
