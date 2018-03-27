package com.henghao.istration.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.ICarapplyDao;
import com.henghao.istration.entity.CarRealtime;
import com.henghao.istration.entity.Carapply;
@Repository("carapplyDao")
public class CarapplyDaoImpl implements ICarapplyDao {

	@Resource
	private SessionFactory sessionFactory;
	
	
	
	

	@Override
	public List<Carapply> findApply(String carNumber, String dates) {
		dates = dates+"%";
		String sql = "SELECT ID,CARNO,USERS_NAME,CREATED,STARTTIME,ENDTIME,DESTINATIONS,TEL FROM tz_app_car_book WHERE CARNO=? AND STARTTIME LIKE ? OR ENDTIME LIKE ?";
		@SuppressWarnings("unchecked")
		List<Carapply> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(Carapply.class)).
														setParameter(0, carNumber).
														setParameter(1, dates).
														setParameter(2, dates).list();
		return list;
	}
	@Override
	public List<Object> findByCarnum(String carNumber, String dates) {
		
		String sql = "SELECT carNumber,longitude,latitude,carTime FROM aj_carrealtime WHERE carNumber=? AND YTD=? ORDER BY carTime DESC ";
		@SuppressWarnings("unchecked")
		List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(sql).
				setParameter(0, carNumber).setParameter(1, dates).
				setResultTransformer(Transformers.aliasToBean(CarRealtime.class)).list();
		return list;
	}

	
}
