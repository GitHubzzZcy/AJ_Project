package com.henghao.dao.impl;


import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.henghao.dao.MeetingInfomationDao;
@Repository
public class MeetingInfomationDaoImpl extends BaseDao<T> implements MeetingInfomationDao {

	/**
	 * 按时间段查询会议签到参加信息
	 */
	@Override
	public List<?> meetSignInfo(String date1, String date2) {
		String sql = "SELECT YCHRY,SJCHRY,HYZP,HYSC FROM aj_hygl "
				+ "WHERE HYRQ >=? AND HYRQ <= ?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	@Override
	public List<?> meetingType(String date1, String date2) {
		String sql = "SELECT HYLX,COUNT(*) FROM aj_hygl "
				+ "WHERE HYRQ >=? "
				+ "AND HYRQ <= ? "
				+ "GROUP BY HYLX";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}


}
