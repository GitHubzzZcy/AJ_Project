package com.henghao.news.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class RandomAwardDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public List<?> queryUserAward() {
		String sql = "SELECT * FROM luck_copy";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	public void updateAwardGrade(String name, int awardGrade) {
		String sql = "UPDATE luck_copy SET grade=? WHERE name=?";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, awardGrade).setParameter(1, name).executeUpdate();
	}

}
