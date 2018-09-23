package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.common.dao.BaseDao;
import com.wrdao.springboot.sys.vo.SysUserRoleVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRoleVo> {

    @Override
    int insert(SysUserRoleVo sysUserRoleVo);

    @Override
    int update(SysUserRoleVo sysUserRoleVo);

    @Override
    int delete(SysUserRoleVo sysUserRoleVo);

    @Override
    int validN(SysUserRoleVo sysUserRoleVo);

    @Override
    int validY(SysUserRoleVo sysUserRoleVo);

    @Override
    List<SysUserRoleVo> list();

    @Override
    PageList<SysUserRoleVo> list(PageQo pageQo);

    @Override
    SysUserRoleVo get(@Param("surtId") String id);

    List<String> getRoleIdListByUserId(@Param("userId") String userId);
}
