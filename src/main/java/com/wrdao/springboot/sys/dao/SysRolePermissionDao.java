package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.sys.vo.SysRolePermissionVo;
import com.wrdao.springboot.sys.vo.SysRoleVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolePermissionDao extends JpaRepository<SysRolePermissionVo, String> {

    List<String> getPermissionIdListByRoleId(SysRoleVo role);
}
