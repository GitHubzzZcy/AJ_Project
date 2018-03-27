package com.henghao.istration.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.IAppLoadDao;
@Repository("appLoadDao")
public class AppLoadDaoImpl implements IAppLoadDao {

	@Resource
	private SessionFactory sessionFactory;
	@Override
	public String loadFile(String id) {
		String sql = "SELECT CONCAT(A.SAVE_NAME,'.',A.EXTENTION) AS path FROM ta_horizon_info A WHERE A.ID =?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}

}
