package com.wrdao.springboot.sys.dao;

import com.wrdao.springboot.common.dao.BaseDao;
import com.wrdao.springboot.sys.vo.SysUserVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao extends BaseDao<SysUserVo> {

    @Override
    int insert(SysUserVo sysUserVo);

    @Override
    int update(SysUserVo sysUserVo);

    @Override
    int delete(SysUserVo sysUserVo);

    @Override
    int validN(SysUserVo sysUserVo);

    @Override
    int validY(SysUserVo sysUserVo);

    @Override
    List<SysUserVo> list();

    @Override
    PageList<SysUserVo> list(PageQo pageQo);

    @Override
    SysUserVo get(@Param("userId") String id);

    /*@Select("select user_id as userId,password as password,salt as salt,username as username,name as name,available as available from sys_user_bas where username = #{username}")*/
    SysUserVo findByUsername(@Param("username") String username);
}
