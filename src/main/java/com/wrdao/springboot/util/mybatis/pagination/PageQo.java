package com.wrdao.springboot.util.mybatis.pagination;

import com.wrdao.springboot.util.mybatis.paginator.domain.Order;
import com.wrdao.springboot.util.mybatis.paginator.domain.PageBounds;

public class PageQo extends PageBounds {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5849566170741471562L;

	public PageQo() {
		setPage(1);
		setLimit(10);
		setContainsTotalCount(true);
	}
	
	public PageQo orderBy(String sortString){
		setOrders(Order.formString(sortString));
		return this;
	}

}
