package com.wrdao.springboot.util.mybatis.pagination;

import com.wrdao.springboot.common.vo.BaseVo;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageList;
import com.wrdao.springboot.util.mybatis.paginator.domain.Paginator;

import java.util.List;

public class PageDto<T extends BaseVo> {

	private Paginator paginator;

	private List<T> list;
	
	public PageDto() {
	}
	
	public PageDto(PageList<T> pageList) {
		if (pageList != null) {
			this.paginator = pageList.getPaginator();
			this.list = pageList;
		}
	}
	
	public PageDto(Paginator paginator, List<T> list) {
		this.paginator = paginator;
		this.list = list;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public static PageDto ValueOf(PageList baseVos) {
		return new PageDto<>(baseVos);
	}

}
