package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.sys.vo.SysUserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends JpaRepository<SysUserVo, String> {

    //@Query("select t from SysUserVo t where t.username = :username")
    SysUserVo findByUsername(String username);
}
