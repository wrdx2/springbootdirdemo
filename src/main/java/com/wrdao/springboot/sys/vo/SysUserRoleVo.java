package com.wrdao.springboot.sys.vo;

import com.wrdao.springboot.common.vo.BaseVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_role_tr")
public class SysUserRoleVo extends BaseVo {

    @Id
    @Column(columnDefinition ="char(32)")
    private String surtId;
    @Column(columnDefinition ="char(32)")
    private String userId;
    @Column(columnDefinition ="char(32)")
    private String roleId;

    public String getSurtId() {
        return surtId;
    }

    public void setSurtId(String surtId) {
        this.surtId = surtId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
