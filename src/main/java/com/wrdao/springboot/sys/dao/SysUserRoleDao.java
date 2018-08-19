package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.sys.vo.SysUserRoleVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRoleVo, String> {

    List<String> getRoleIdListByUserId(String userId);
}
