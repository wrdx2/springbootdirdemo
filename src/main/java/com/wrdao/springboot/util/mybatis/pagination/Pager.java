package com.wrdao.springboot.util.mybatis.pagination;

import com.wrdao.springboot.util.mybatis.paginator.domain.PageBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pager<T> extends PageBounds {
	
	private int page;
	private int pageSize;
	private int total;
	private int totalPage;
	private int begin;

	private List<T> results;
	private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象  

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public Pager<T> initPager(Integer total) {
		setPageSize(10);
		setTotal(total);
		totalPage = total%getPageSize()==0?(total/getPageSize()):(total/getPageSize()+1);
		if (page<=0) {page = 1;}
		if (page>=totalPage) {page = totalPage;}
		begin = (page-1)*pageSize;
		return this;
	}

	public Pager<T> initData(List<T> all) {
		this.setResults(all);
		return this;
	}

}
