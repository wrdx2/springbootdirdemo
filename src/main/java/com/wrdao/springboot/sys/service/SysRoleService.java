package com.wrdao.springboot.sys.service;

import com.wrdao.springboot.sys.dao.SysRoleDao;
import com.wrdao.springboot.sys.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleService {
    @Autowired
    SysRoleDao sysRoleDao;

    public List<SysRoleVo> getRoleListByRoleIdList(List<String> roleIdList) {
        return sysRoleDao.getRoleListByRoleId(roleIdList);
    }

}
