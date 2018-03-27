package com.henghao.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.IUserEventTrackDao;
import com.henghao.entity.UserEventTrackEntity;
@Repository("userEventTrackDao")
public class UserEventTrackDaoImpl extends BaseDao<UserEventTrackEntity> implements IUserEventTrackDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<UserEventTrackEntity> queryByUserIdAndDate(String userId, String eventDate) {
		String hql = "FROM UserEventTrackEntity WHERE userId=? AND eventDate=?";
		List<UserEventTrackEntity> list = new ArrayList<UserEventTrackEntity>();
		list = super.getSession().createQuery(hql).setParameter(0, userId).setParameter(1, eventDate).list();
		return list;
	}

	

}
