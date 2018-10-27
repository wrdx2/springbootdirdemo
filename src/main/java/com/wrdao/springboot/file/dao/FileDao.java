package com.wrdao.springboot.file.dao;

import com.wrdao.springboot.common.dao.BaseDao;
import com.wrdao.springboot.file.vo.FileVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao extends BaseDao<FileVo> {

    @Override
    int insert(FileVo fileVo);

    @Override
    int update(FileVo fileVo);

    @Override
    int delete(FileVo fileVo);

    @Override
    List<FileVo> list();

    @Override
    PageList<FileVo> list(PageQo pageQo);

    @Override
    FileVo get(String id);

    int downloadSuccess(String fileId);
}
