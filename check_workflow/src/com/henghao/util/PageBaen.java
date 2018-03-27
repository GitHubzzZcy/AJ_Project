package com.henghao.util;

import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 * @author 周承耀
 *
 */

public class PageBaen {
	
	private int currentPage;//当前页
	private int pageSize;//每页显示数
	private int total;//总记录数
	private Map<String, String> conditionMap;//包装查询条件
	private List<?> list;//当前页查询的结果
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Map<String, String> getConditionMap() {
		return conditionMap;
	}
	public void setConditionMap(Map<String, String> conditionMap) {
		this.conditionMap = conditionMap;
	}
	@Override
	public String toString() {
		return "PageBaen [currentPage=" + currentPage + ", pageSize="
				+ pageSize + ", total=" + total + ", conditionMap="
				+ conditionMap + ", rows=" + list + "]";
	}
	public PageBaen() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBaen(int currentPage, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	
}
	

