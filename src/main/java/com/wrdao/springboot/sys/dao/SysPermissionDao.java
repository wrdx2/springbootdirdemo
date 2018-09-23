package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.common.dao.BaseDao;
import com.wrdao.springboot.sys.vo.SysPermissionVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionDao extends BaseDao<SysPermissionVo> {

    @Override
    int insert(SysPermissionVo sysPermissionVo);

    @Override
    int update(SysPermissionVo sysPermissionVo);

    @Override
    int delete(SysPermissionVo sysPermissionVo);

    @Override
    int validN(SysPermissionVo sysPermissionVo);

    @Override
    int validY(SysPermissionVo sysPermissionVo);

    @Override
    List<SysPermissionVo> list();

    @Override
    PageList<SysPermissionVo> list(PageQo pageQo);

    @Override
    SysPermissionVo get(@Param("perId") String perId);

    List<SysPermissionVo> getPermissionListByPerId(@Param("perIds") List<String> perIds);

}
