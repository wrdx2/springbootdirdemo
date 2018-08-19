package com.wrdao.springboot.sys.service;

import com.wrdao.springboot.sys.dao.SysRolePermissionDao;
import com.wrdao.springboot.sys.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRolePermissionService {
    @Autowired
    SysRolePermissionDao sysRolePermissionDao;


    public List<String> getPermissionIdListByRoleVo(SysRoleVo role) {
        return sysRolePermissionDao.getPermissionIdListByRoleId(role);
    }
}
