package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.common.dao.BaseDao;
import com.wrdao.springboot.sys.vo.SysRoleVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao extends BaseDao<SysRoleVo> {

    @Override
    int insert(SysRoleVo sysRoleVo);

    @Override
    int update(SysRoleVo sysRoleVo);

    @Override
    int delete(SysRoleVo sysRoleVo);

    @Override
    int validN(SysRoleVo sysRoleVo);

    @Override
    int validY(SysRoleVo sysRoleVo);

    @Override
    List<SysRoleVo> list();

    @Override
    PageList<SysRoleVo> list(PageQo pageQo);

    @Override
    SysRoleVo get(@Param("roleId") String roleId);

    List<SysRoleVo> getRoleListByRoleId(@Param("roleIds") List<String> roleIds);
}
