package com.henghao.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import java.util.Map.Entry;
import com.henghao.dao.IBaseDao;
import com.henghao.util.PageBaen;

@Repository
public class BaseDao<T> implements IBaseDao<T>{
	
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseDao(){
		Class c = getClass();
		System.out.println("c "+c);
		Type t = c.getGenericSuperclass();
		if(t instanceof ParameterizedType){
			System.out.println("in if");
			Type[] p = ((ParameterizedType)t).getActualTypeArguments();
			System.out.println("Arrays "+Arrays.toString(p));
			clazz = (Class<T>)p[0];
			System.out.println("clazz "+clazz);
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
	//查询所有分页
	@SuppressWarnings("unchecked")
	public void paging(PageBaen pageBaen ){
		int page = pageBaen.getCurrentPage();
		int pageSize = pageBaen.getPageSize();
		Criteria criteria = getSession().createCriteria(clazz);
		long total2 = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		if(total2 < pageSize){
			pageSize = (int) total2;
		}
		criteria.setProjection(null);
		if((page-1)*pageSize >= total2){
			page = page-1;
		}
		//System.out.println("查询的pageSize:"+pageSize);
		criteria.setFirstResult((page-1)*pageSize);
		criteria.setMaxResults(pageSize);
		//排序，是以时间倒序来进行排列
		criteria.addOrder(Order.desc("id"));
		List<T> list = criteria.list();
		int total = (int) total2;
		pageBaen.setList(list);
		pageBaen.setTotal(total);
	}
	//条件查询分页
	public void findCondition(PageBaen pageBaen) {
		System.out.println("进来的pageSize "+pageBaen.getPageSize());
		Map<String, String> map = pageBaen.getConditionMap();
		Iterator<?> iter = map.entrySet().iterator();
		String hql="";
		while (iter.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			String key = (String) entry.getKey();
			System.out.println("key "+key);
			String val = (String) entry.getValue();
			System.out.println("value "+val);
			int value = Integer.parseInt(val);
			//System.out.println(key+"..............."+val);
			hql = "select COUNT(*) from "+clazz.getSimpleName()+" where "+key+"=? ORDER BY id DESC";
			System.out.println("hql "+hql.toString());
			long total2 =(Long) getSession().createQuery(hql).setParameter(0, value).uniqueResult();
			int total = (int) total2;
			System.out.println("total "+total);
			int pageSize = pageBaen.getPageSize();
			System.out.println("pageSize "+pageSize);
			int page = pageBaen.getCurrentPage();
			if(total< pageSize){
				pageSize = total;
			 }
			 if((page-1)*pageSize >= total){
				page = page-1;
			 }
			 System.out.println("page "+page+" pageSize "+pageSize);
			String hql2 = "FROM "+clazz.getSimpleName()+" where "+key+"=? ORDER BY id DESC";
			System.out.println("hql2 "+hql2.toString());
			@SuppressWarnings("unchecked")
			List<T> list = getSession().createQuery(hql2).setParameter(0, value).
							setFirstResult((page-1)*pageSize).
							setMaxResults(pageSize).list();
			pageBaen.setList(list);
			pageBaen.setTotal(total);
		}
	}

}
