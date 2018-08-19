package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.sys.vo.SysPermissionVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionDao extends JpaRepository<SysPermissionVo, String> {

    List<SysPermissionVo> getPermissionListByPerId(List<String> perId);
}
