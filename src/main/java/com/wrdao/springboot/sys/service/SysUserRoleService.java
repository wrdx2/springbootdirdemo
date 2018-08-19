package com.wrdao.springboot.sys.service;

import com.wrdao.springboot.sys.dao.SysUserRoleDao;
import com.wrdao.springboot.sys.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {
    @Autowired
    SysUserRoleDao sysUserRoleDao;

    public List<String> getRoleIdListByUserId(SysUserVo sysUserVo) {
        return sysUserRoleDao.getRoleIdListByUserId(sysUserVo.getUserId());
    }
}
