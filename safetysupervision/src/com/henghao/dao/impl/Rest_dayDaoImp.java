package com.henghao.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.Rest_dayDao;
import com.henghao.entity.Rest_day;

@Repository("RestDao")
public class Rest_dayDaoImp extends BaseDao<Rest_day> implements Rest_dayDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Rest_day isRestDay(Date date) {
		String hql="FROM Rest_day WHERE date = ?";
		List<Rest_day> list=(List<Rest_day>)super.getSession().createQuery(hql).setParameter(0, date).list();
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;//2017-6-6上午8:57:14宁万龙
	}


}
