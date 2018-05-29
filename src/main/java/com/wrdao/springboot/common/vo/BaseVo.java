package com.wrdao.springboot.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.Date;

public class BaseVo implements Serializable {

    private Boolean available = Boolean.FALSE; // 是否可用
    private String creator;
    private String updater;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatetime;

    public BaseVo initCreator(){
        this.available = true;
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
        this.available = true;
        this.updatetime = new Date();

        if (SecurityContextHolder.getContext().getAuthentication() != null){
            this.updater = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            this.updater = "unlogin";
        }

        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
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
