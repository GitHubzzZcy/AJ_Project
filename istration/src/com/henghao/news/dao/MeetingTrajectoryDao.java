package com.henghao.news.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.news.entity.MeetingTrajectoryEntity;

@Repository
public class MeetingTrajectoryDao extends BaseDaoImpl<MeetingTrajectoryEntity> {

	
	//被别人先选的代替人其他人不能选
	@SuppressWarnings("unchecked")
	public List<MeetingTrajectoryEntity> queryByMid(int mid) {
		String hql = "FROM MeetingTrajectoryEntity WHERE mid=?";
		return super.getSession().createQuery(hql).setParameter(0, mid).list();
	}
	//判断是不是第一次提交代替人
	public MeetingTrajectoryEntity queryByMidAndShouldAttendMetting(int mid, String supersededId) {
		String hql = "FROM MeetingTrajectoryEntity WHERE mid=? AND shouldAttendMetting=? OR substitute=?";
		MeetingTrajectoryEntity uniqueResult = (MeetingTrajectoryEntity) super.getSession().createQuery(hql)
							.setParameter(0, mid)
							.setParameter(1, supersededId)
							.setParameter(2, supersededId)
							.uniqueResult();
		return uniqueResult;
	}
	public MeetingTrajectoryEntity queryMeetingTrajectoryEntityByMidAndUserIdList(int mid, String userId) {
		String hql = "FROM MeetingTrajectoryEntity WHERE mid=? AND  substitute=?";
		MeetingTrajectoryEntity uniqueResult = (MeetingTrajectoryEntity) super.getSession().createQuery(hql)
							.setParameter(0, mid)
							.setParameter(1, userId)
							.uniqueResult();
		if(uniqueResult != null) {
			return uniqueResult;
		}else{
			String sql = "FROM MeetingTrajectoryEntity WHERE mid=? AND  shouldAttendMetting=?";
			MeetingTrajectoryEntity result = (MeetingTrajectoryEntity) super.getSession().createQuery(sql)
					.setParameter(0, mid)
					.setParameter(1, userId)
					.uniqueResult();
			return result;
		}
		
	}
	@SuppressWarnings("unchecked")
	public List<MeetingTrajectoryEntity> queryMteByShouldAttendList(String userId) {
		
		
		String hql = "FROM MeetingTrajectoryEntity WHERE  substitute=?";
		List<MeetingTrajectoryEntity>  uniqueResult = super.getSession().createQuery(hql)
							.setParameter(0, userId)
							.list();
		if(uniqueResult.size() > 0) {
			return uniqueResult;
		}else{
			String sql = "FROM MeetingTrajectoryEntity WHERE  shouldAttendMetting=?";
			List<MeetingTrajectoryEntity> result = super.getSession().createQuery(sql)
					.setParameter(0, userId)
					.list();
			return result;
		}
	}

	

}
