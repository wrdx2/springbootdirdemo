package com.wrdao.springboot.sys.vo;

import com.wrdao.springboot.common.vo.BaseVo;

import javax.persistence.*;

@Entity
@Table(name = "sys_role_bas")
public class SysRoleVo extends BaseVo {

    @Id
    @Column(columnDefinition ="char(32)")
    private String roleId; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}