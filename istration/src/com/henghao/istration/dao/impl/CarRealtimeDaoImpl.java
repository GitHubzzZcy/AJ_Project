package com.henghao.istration.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.ICarRealtimeDao;
import com.henghao.istration.entity.CarRealtime;
import com.henghao.istration.entity.Carapply;
import com.henghao.istration.entity.Exceed;
import com.henghao.news.dao.BaseDaoImpl;
@Repository("carRealtimeDao")
public class CarRealtimeDaoImpl extends BaseDaoImpl<CarRealtime> implements
		ICarRealtimeDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CarRealtime> findByCarnum(String carNumber, String dates) {
		String hql  = "SELECT carNumber,longitude,latitude,carTime FROM aj_carrealtime WHERE carNumber=? AND YTD=?";
		@SuppressWarnings("unchecked")
		List<CarRealtime> list = sessionFactory.getCurrentSession().createSQLQuery(hql).
								setResultTransformer(Transformers.aliasToBean(CarRealtime.class)).
								setParameter(0, carNumber).setParameter(1, dates).list();
		return list;
	}


	@Override
	public List<String> queryCarlist() {
		String sql = "SELECT CARNO FROM tz_app_car_info";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return list;
	}


	//超时统计
	@Override
	public List<Exceed> exceed() {
		String sql = "SELECT USERS.CARID,C.NAME AS USEUSER,USERS.STARTTIME,USERS.CSSJ,USERS.ENDTIME FROM (SELECT B.CARNO AS CARID,RIGHT(A.USEUSER,LENGTH(A.USEUSER)-2) AS USERNAME,STARTTIME,CSSJ,ENDTIME FROM TZ_APP_CAR_USERECORD A LEFT OUTER JOIN tz_app_car_info B  ON A.CARID=B.ID  WHERE A.CSSJ <> '0' ORDER BY A.ID DESC) AS USERS LEFT OUTER JOIN to_horizon_user C ON USERS.USERNAME=C.ID" ;
		@SuppressWarnings("unchecked")
		List<Exceed> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Exceed.class)).list();
		return list;
	}


	@Override
	public List<String> carlog() {
		String sql = "SELECT WORKID FROM tw_hz_log WHERE FLOWID='clyd' AND NEXTNODEIDS='End2;' ORDER BY ACTIONTIME DESC";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return list;
	}


	@Override
	public Carapply carbook(String wrokid) {
		String sql = "SELECT DATAID FROM twr_hz_instance WHERE WORKID = ?";
		String dataid = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, wrokid).uniqueResult().toString();
		String sql2 = "select B.ID,B.CARNO,B.USERS_NAME,B.STARTTIME,B.DESTINATIONS FROM tz_app_car_book B WHERE B.ID=?";
		Object uniqueResult = sessionFactory.getCurrentSession().createSQLQuery(sql2).setResultTransformer(Transformers.aliasToBean(Carapply.class)).setParameter(0, dataid).uniqueResult();
		Carapply apply = null;
		if(null != uniqueResult) {
			apply = (Carapply) uniqueResult;
		}
		return apply;
	}


	@Override
	public List<CarRealtime> lnglat(String substring , String carno) {
		String sql = "SELECT A.car_latitude AS longitude,A.car_latitude  AS latitude FROM sa_test_car A WHERE A.car_num=?  AND A.car_upTime LIKE ?";
		@SuppressWarnings("unchecked")
		List<CarRealtime> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CarRealtime.class)).setParameter(0, carno).setParameter(1, substring+"%").list();
		return list;
	}


	//查询没辆车每天最后一条数据作为停车点的数据
	@Override
	public List<CarRealtime> park() {
		String sql = "SELECT * FROM (SELECT A.car_test_id AS id,A.car_longitude AS longitude,A.car_latitude AS latitude,A.car_num AS carNumber,LEFT(A.car_upTime,10) AS YTD FROM sa_test_car A GROUP BY YTD,carNumber,id ORDER BY YTD,carNumber,id DESC) AS car GROUP BY car.carNumber,car.YTD ORDER BY car.YTD DESC";
		@SuppressWarnings("unchecked")
		List<CarRealtime> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CarRealtime.class)).list();
		return list;
	}


	

}
