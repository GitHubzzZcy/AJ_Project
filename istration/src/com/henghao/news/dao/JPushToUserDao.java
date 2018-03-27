package com.henghao.news.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.news.entity.JPushToUser;

@Repository
public class JPushToUserDao extends BaseDaoImpl<JPushToUser> {
	//根据用户id查询所有会议mid
	public List<Integer> queryJPushByUserAllmid(String uid) {
		String hql = "SELECT mid FROM JPushToUser WHERE uid=? ORDER BY cid DESC";
		@SuppressWarnings("unchecked")
		List<Integer> list = super.getSession().createQuery(hql).setParameter(0, uid).list();
		return list;
	}

	public Integer queryJPushToUserMidByMsgId(Long msg_id) {
		String sql = "SELECT mid FROM JPushToUser WHERE msg_id=? ORDER BY cid DESC";
		Object result = super.getSession().createQuery(sql).setParameter(0, msg_id).uniqueResult();
		if(null != result) {
			return Integer.parseInt(result.toString());
		}
		return null;
	}

	public List<JPushToUser> queryByMid(int mid) {
		String hql = "FROM JPushToUser WHERE mid=?";
		@SuppressWarnings("unchecked")
		List<JPushToUser> list = super.getSession().createQuery(hql).setParameter(0, mid).list();
		return list;
	}

	public List<JPushToUser> queryByMidUser(int mid, String uid) {
		String hql = "FROM JPushToUser WHERE mid=? AND uid=?";
		@SuppressWarnings("unchecked")
		List<JPushToUser> list = super.getSession().createQuery(hql).setParameter(0, mid).setParameter(1, uid).list();
		return list;
	}

	public JPushToUser queryByMsgId(Long msg_id, String uid) {
		String hql = "FROM JPushToUser WHERE msg_id=? AND uid=?";
		JPushToUser jPushToUser = (JPushToUser) super.getSession().createQuery(hql).setParameter(0, msg_id).setParameter(1, uid).uniqueResult();
		return jPushToUser;
	}

	public List<JPushToUser> queryJPushToUserUnRead(String uid) {
		String hql = "FROM JPushToUser WHERE isRead=0 AND uid=?";
		@SuppressWarnings("unchecked")
		List<JPushToUser> list = super.getSession().createQuery(hql).setParameter(0, uid).list();
		return list;
	}

}
