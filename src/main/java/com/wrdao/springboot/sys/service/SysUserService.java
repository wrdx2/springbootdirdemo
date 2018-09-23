package com.wrdao.springboot.sys.service;

import com.wrdao.springboot.sys.dao.SysUserDao;
import com.wrdao.springboot.sys.vo.SysUserVo;
import com.wrdao.springboot.util.UUIDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    SysUserDao sysUserDao;

    public SysUserVo findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    public int save(SysUserVo sysUserVo) {
        sysUserVo.initCreator();
        sysUserVo.setUserId(UUIDTool.getUUID());
        return sysUserDao.insert(sysUserVo);
    }

}
