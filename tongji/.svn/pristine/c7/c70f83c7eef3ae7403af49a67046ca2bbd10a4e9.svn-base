package com.henghao.dao.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.dao.INoteDao;
import com.henghao.entity.Note;
import com.henghao.util.DateUtils;

@Repository("noteDao")
public class NoteDaoImpl extends BaseDao<Note> implements INoteDao {

	@Resource
	private SessionFactory sessionFactory;
	

	@Override
	public Note queryPlanId(String id) {
		String sql = "SELECT * FROM HENGHAO_NOTE WHERE (planId,Id) IN (SELECT planId,MAX(Id) FROM HENGHAO_NOTE WHERE planId=? GROUP BY planId)";
		Note note = (Note) sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Note.class)).setParameter(0, id).uniqueResult();
		if(null != note) {
			return note;
		}
		//首次发送短信
		return new Note(0,id,DateUtils.getNextDay(new Date()),3);
	}

	
}
