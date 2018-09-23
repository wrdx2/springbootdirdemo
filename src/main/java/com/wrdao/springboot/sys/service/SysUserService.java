package com.wrdao.springboot.sys.service;

import com.wrdao.springboot.sys.dao.SysUserDao;
import com.wrdao.springboot.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    public SysUserVo findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    public int save(SysUserVo sysUserVo) {
        sysUserVo.initCreator();
        sysUserVo.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
        return sysUserDao.insert(sysUserVo);
    }

}
