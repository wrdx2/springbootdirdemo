package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.common.dao.BaseDao;
import com.wrdao.springboot.sys.vo.SysRolePermissionVo;
import com.wrdao.springboot.sys.vo.SysRoleVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolePermissionDao extends BaseDao<SysRolePermissionVo> {

    @Override
    int insert(SysRolePermissionVo sysRolePermissionVo);

    @Override
    int update(SysRolePermissionVo sysRolePermissionVo);

    @Override
    int delete(SysRolePermissionVo sysRolePermissionVo);

    @Override
    int validN(SysRolePermissionVo sysRolePermissionVo);

    @Override
    int validY(SysRolePermissionVo sysRolePermissionVo);

    @Override
    List<SysRolePermissionVo> list();

    @Override
    PageList<SysRolePermissionVo> list(PageQo pageQo);

    @Override
    SysRolePermissionVo get(@Param("srptId") String srptId);

    List<String> getPermissionIdListByRoleId(SysRoleVo role);
}
