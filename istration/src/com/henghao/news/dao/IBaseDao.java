package com.henghao.news.dao;


import java.util.List;
import java.util.Map;



public interface IBaseDao<T> {
		
		public void add(T t);
		public void update(T t);
		public T findById(int id);
		public List<T> findAll();
		public void saveOrUpdate(T t);
		//分页
		public Map<String,Object> pageQuery(int page,int pageSize);
}
