package com.wrdao.springboot.sys.vo;

import com.wrdao.springboot.common.vo.BaseVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Sys_role_permission_tr")
public class SysRolePermissionVo extends BaseVo {

    @Id
    @Column(columnDefinition ="char(32)")
    private String srptId;
    @Column(columnDefinition ="char(32)")
    private String roleId;
    @Column(columnDefinition ="char(32)")
    private String permissionId;

    public String getSrptId() {
        return srptId;
    }

    public void setSrptId(String srptId) {
        this.srptId = srptId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}
