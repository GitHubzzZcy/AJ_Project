package com.henghao.dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IUserDataDao;
@Repository("userDataDao")
public class UserDataDaoImpl implements IUserDataDao {

	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public String getUserName(String userId) {
		String sql = "SELECT NAME FROM to_horizon_user WHERE ID=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userId).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return "";
	}

	@Override
	public String getUserId(String userName) {
		String sql = "SELECT ID FROM to_horizon_user WHERE NAME=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userName).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return "";
	}

	@Override
	public String getUserDeptName(String userDeptId) {
		String sql = "SELECT DEPT_NAME FROM to_horizon_dept WHERE ID=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userDeptId).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return "";
	}
}
