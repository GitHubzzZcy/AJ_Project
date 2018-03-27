package com.henghao.news.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.CarBookEntity;

@Repository
public class EfficiencyCarDataDao {

	@Resource
	private SessionFactory sessionFactory;
	/**
	 * 查询通过申请的用车
	 * @param userId
	 * @param date1
	 * @param date2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CarBookEntity> queryCarUseTime(String userId, String date1, String date2) {
		if(userId == null || "".equals(userId)) {
			String sql = "SELECT a.ID, a.USERS, a.CARNO, a.TITLE, a.STARTTIME, a.ENDTIME, a.ORIGIN, a.DESTINATIONS, a.TRAVELCATEGOR FROM tz_app_car_book a LEFT OUTER JOIN twr_hz_instance b ON a.ID=b.DATAID LEFT OUTER JOIN tw_hz_log c ON b.WORKID=c.WORKID WHERE c.NEXTNODEIDS='End2;' AND a.STARTTIME >=? AND a.ENDTIME <=? ";
			List<CarBookEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).setResultTransformer(Transformers.aliasToBean(CarBookEntity.class)).list();
			return list;
		}else {
			String sql = "SELECT a.ID, a.USERS, a.CARNO, a.TITLE, a.STARTTIME, a.ENDTIME, a.ORIGIN, a.DESTINATIONS, a.TRAVELCATEGOR FROM tz_app_car_book a LEFT OUTER JOIN twr_hz_instance b ON a.ID=b.DATAID LEFT OUTER JOIN tw_hz_log c ON b.WORKID=c.WORKID WHERE c.NEXTNODEIDS='End2;' AND a.STARTTIME >=? AND a.ENDTIME <=? AND USERS=?";
			List<CarBookEntity> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).setParameter(2, userId).setResultTransformer(Transformers.aliasToBean(CarBookEntity.class)).list();
			return list;
		}
	}
	/**
	 * 查询人员手机定位数据
	 * @param name
	 * @param time1
	 * @param time2
	 * @return
	 */
	public List<?> queryPersonleLatLng(String userId, String time1, String time2) {
		String sql ="SELECT latitude,longitude,ctime FROM user_position_log WHERE ctime >= STR_TO_DATE(?,'%Y-%m-%d %H:%i') AND ctime <= STR_TO_DATE(?,'%Y-%m-%d %H:%i') AND uid=?"; //TODO
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, time1)
				.setParameter(1, time2).setParameter(2, userId).list();
		return list;
	}
	//用车超时数据
	@SuppressWarnings("unchecked")
	public List<String> queryCarOvertime(String userId, String start, String end) {
		if(userId == null || "".equals(userId)) {
			String sql = "SELECT CREATETIME FROM tz_app_car_userecord WHERE CSSJ > 0 AND STARTTIME>=? AND ENDTIME <=?";
			return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).list();
		}else{
			String sql = "SELECT CREATETIME FROM tz_app_car_userecord WHERE CSSJ > 0 AND STARTTIME>=? AND ENDTIME <=? AND USEUSER=?";
			return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, userId).list();
		}
	}
}
