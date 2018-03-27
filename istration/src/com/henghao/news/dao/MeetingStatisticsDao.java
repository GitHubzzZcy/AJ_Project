package com.henghao.news.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
/**
 * @Description:融合平台会议管理页面统计数据
 * @author zcy  
 * @date 2017年11月14日 上午10:45:51
 * @version 1.0
 */
@Repository
public class MeetingStatisticsDao {
	@Resource
	private SessionFactory sessionFactory;

	//查询已经同意会议的总条数
	public int queryMeetingCount(String start, String end) {
		String sql = "SELECT COUNT(*) FROM aj_meeting_push WHERE meetingStartTime>? AND meetingStartTime<? AND whetherPass=1";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).uniqueResult();
		if(null == result) {
			return 0;
		}
		return Integer.parseInt(result.toString());
	}
	//查询各类型会议的总数
	public int queryMeetingTypeCount(String meetingType, String start, String end) {
		String sql = "SELECT COUNT(*) FROM aj_meeting_push WHERE meetingStartTime>? AND meetingStartTime<? AND whetherPass=1 AND meetingType=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, meetingType).uniqueResult();
		if(null == result) {
			return 0;
		}
		return Integer.parseInt(result.toString());
	}

}
