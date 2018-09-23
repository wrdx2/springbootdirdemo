package com.wrdao.springboot.common.dao;

import com.wrdao.springboot.common.vo.BaseVo;
import com.wrdao.springboot.util.mybatis.pagination.PageQo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;

import java.util.List;

public interface BaseDao<T extends BaseVo> {
	
	/**
	 * @param t
	 * @return
	 * 新增
	 */
	public int insert(T t);
	
	/**
	 * @param t
	 * @return
	 * 修改
	 */
	public int update(T t);
	
	/**
	 * @param t
	 * @return
	 * 删除数据
	 */
	public int delete(T t);
	
	/**
	 * @param t
	 * @return
	 * 置失效
	 */
	public int validN(T t);
	
	/**
	 * @param t
	 * @return
	 * 置生效
	 */
	public int validY(T t);
	
	/**
	 * @return
	 * 适用字典查询，不分页数据等情况
	 */
	public List<T> list();
	
	/**
	 * @param pageQo
	 * @return
	 * 适用分页数据
	 */
	public PageList<T> list(PageQo pageQo);

	/**
	 *
	 * 通过id获取详情
	 * @param id
	 * @return
	 */
	T get(String id);
}
