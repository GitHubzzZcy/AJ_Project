package com.henghao.dao;

import java.util.List;

import com.henghao.util.PageBaen;


public interface IBaseDao<T> {
	
	public void add(T t);
	public void update(T t);
	public T findById(int id);
	public List<T> findAll();
	public void saveOrUpdate(T t);
	//分页
	public void paging(PageBaen pageBaen);
	public void findCondition(PageBaen pageBaen);			
	public void findAll(PageBaen pageBaen);
	public void findConditionString(PageBaen pageBaen);
}
