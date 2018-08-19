package com.wrdao.springboot.sys.service;

import com.wrdao.springboot.sys.dao.SysPermissionDao;
import com.wrdao.springboot.sys.vo.SysPermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionService {
    @Autowired
    SysPermissionDao sysPermissionDao;

    public List<SysPermissionVo> getPermissionListByPermissionIdList(List<String> permissionIdList) {
        return sysPermissionDao.getPermissionListByPerId(permissionIdList);
    }
}
