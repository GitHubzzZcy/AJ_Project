package com.henghao.news.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.MeetingEntity;
import com.henghao.news.entity.PersonnelEntity;

@Repository
public class JPushDao  extends BaseDaoImpl<MeetingEntity>{

	private Map<String, Object> remap = new HashMap<String, Object>();
	//根据人名查询领导信息
	public PersonnelEntity queryLeadByUserName(String userid) {
		String sql = "SELECT ID AS id,`NAME` AS name,LOGIN_NAME AS login_name FROM to_horizon_user WHERE ID = ("+
				"SELECT c.USER_ID FROM to_horizon_user a LEFT OUTER JOIN "+
				"tor_horizon_user_dept b ON a.ID=b.USER_ID LEFT OUTER JOIN "+
				"to_horizon_dept_admin c ON b.DEPT_ID=c.DEPT_ID "+
				"WHERE a.ID=? AND c.OPER_TYPE='C')";
		PersonnelEntity user = (PersonnelEntity) super.getSession().createSQLQuery(sql).setParameter(0, userid).setResultTransformer(Transformers.aliasToBean(PersonnelEntity.class)).uniqueResult();
		return user;
	}

	public MeetingEntity queryMeetingEntityByMsgid(Long msg_id) {
		String hql = "FROM MeetingEntity WHERE msg_id=?";
		MeetingEntity uniqueResult = (MeetingEntity) super.getSession().createQuery(hql).setParameter(0, msg_id).uniqueResult();
		return uniqueResult;
	}

	//根据用户查询发起并审批通过的会议列表
	public List<MeetingEntity> queryMeetingEntityByUserList(String uid) {
		String hql = "FROM MeetingEntity WHERE uid=? AND whetherPass=1";
		@SuppressWarnings("unchecked")
		List<MeetingEntity> list = super.getSession().createQuery(hql).setParameter(0, uid).list();
		return list;
	}

	public List<MeetingEntity> queryPassAll(String start, String end) {
		String hql = "FROM MeetingEntity WHERE meetingStartTime>? AND meetingStartTime<? AND whetherPass=1";
		@SuppressWarnings("unchecked")
		List<MeetingEntity> list = super.getSession().createQuery(hql).setParameter(0, start).setParameter(1, end).list();
		return list;
	}
	//融合平台查询所有通过会议数据列表
	public Map<String, Object> queryMeetingCountList(int page, int size, String start, String end) {
		String hql = "SELECT COUNT(*) FROM MeetingEntity WHERE meetingStartTime>? AND meetingStartTime<? AND whetherPass=1";
		int total = Integer.parseInt(super.getSession().createQuery(hql).setParameter(0, start).setParameter(1, end).uniqueResult().toString());
		if(total< size){
			size = total;
		 }
		 if((page-1)*size >= total){
			page = page-1;
		 }
		 String sql = "FROM MeetingEntity WHERE meetingStartTime>? AND meetingStartTime<? AND whetherPass=1";
		 @SuppressWarnings("unchecked")
		List<MeetingEntity> list = super.getSession().createQuery(sql).setParameter(0, start).setParameter(1, end).setFirstResult((page - 1) * size).setMaxResults(size).list();
		 remap.clear();
		 remap.put("total", total);
		 remap.put("list", list);
		return remap;
	}

	//用户评分
	public List<MeetingEntity> queryPassUser(String userId, String start, String end) {
		String hql = "FROM MeetingEntity WHERE meetingStartTime>? AND meetingStartTime<? AND whetherPass=1 AND (uid=? OR userIds LIKE ?)";
		@SuppressWarnings("unchecked")
		List<MeetingEntity> list = super.getSession().createQuery(hql).setParameter(0, start).setParameter(1, end).setParameter(2, userId).setParameter(3, "%"+userId+"%").list();
		return list;
	}

	

}
