package com.henghao.news.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;


@Repository
public class WritDataDao {

	
	@Resource
	private SessionFactory sessionFactory;

	public Object queryWritData(int wtId, String entityClass,
			String dataTableName) throws ClassNotFoundException {
		//通过反射获取类
		Class<?> c = Class.forName("com.henghao.news.entity."+entityClass);
		String sql = "SELECT * FROM "+dataTableName+" WHERE wtid=?";
		Object obj = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, wtId).setResultTransformer(Transformers.aliasToBean(c)).uniqueResult();
		return obj;
	}
}
