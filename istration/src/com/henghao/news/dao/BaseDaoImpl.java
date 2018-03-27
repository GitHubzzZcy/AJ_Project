package com.henghao.news.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;






@Repository
public class BaseDaoImpl<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	Map<String, Object> map=new HashMap<String, Object>();
	private Class<T> clazz;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDaoImpl(){

		Class c = getClass();
		Type t = c.getGenericSuperclass();
		if(t instanceof ParameterizedType){
			Type[] p = ((ParameterizedType)t).getActualTypeArguments();
			clazz = (Class<T>)p[0];
		}
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void add(T t) {
		getSession().save(t);
	}

	public void update(T t) {
		getSession().update(t);
	}

	@SuppressWarnings("unchecked")
	public T findById(int id) {
		return (T) getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		String hql = "from "+clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
		
	}
	
	public Map<String,Object> pageQuery(int page,int pageSize){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
		long sizes = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		if(sizes < pageSize){
			pageSize = (int) sizes;
		}
		criteria.setProjection(null);
		if((page-1)*pageSize >= sizes){
			page = page-1;
		}
		criteria.setFirstResult((page-1)*pageSize);
		criteria.setMaxResults(pageSize);
		//排序，是以时间倒序来进行排列
		criteria.addOrder(Order.desc("id"));
		@SuppressWarnings("unchecked")
		List<T> list = criteria.list();
		map.put("total", sizes);
		map.put("list", list);
		return map;
	}
	
		
	
}
