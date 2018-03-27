package com.henghao.istration.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.IUserDataServeDao;
@Repository("userDataServeDao")
public class UserDataServeDaoImpl implements IUserDataServeDao {

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public String getUserPhone(String username) {
		String sql = "SELECT TELEPHONE FROM to_horizon_user WHERE NAME=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, username).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}

	@Override
	public String getUserLead(String username, String operType) {
		String sql = "SELECT TELEPHONE FROM to_horizon_user WHERE ID = (" +
				"SELECT c.USER_ID FROM to_horizon_user a LEFT OUTER JOIN " +
				"tor_horizon_user_dept b ON a.ID=b.USER_ID LEFT OUTER JOIN " +
				"to_horizon_dept_admin c ON b.DEPT_ID=c.DEPT_ID " +
				"WHERE a.name=? AND c.OPER_TYPE=?)";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, username).setParameter(1, operType).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}
}
