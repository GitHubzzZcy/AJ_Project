package com.henghao.news.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.henghao.news.util.DateUtils;

@Repository
public class UserDataDao {

	@Resource
	private SessionFactory sessionFactory;
	public String getUserId(String username) {
		String sql = "SELECT ID FROM to_horizon_user WHERE NAME=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, username).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}

	public String getUserName(String id) {
		String sql = "SELECT NAME FROM to_horizon_user WHERE ID=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, id).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}
	
	public String getUserDept(String username) {
		String sql = "SELECT c.DEPT_NAME FROM to_horizon_user A LEFT OUTER JOIN tor_horizon_user_dept B ON A.ID=B.USER_ID LEFT OUTER JOIN to_horizon_dept c ON b.DEPT_ID=c.ID WHERE A.`NAME`=?";
		Object result = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, username).uniqueResult();
		if(null != result) {
			return result.toString();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<String> getHolidayAndWeekend() {
		int year = Integer.parseInt(DateUtils.getCurrentDate("yyyy"));
		String sql = "SELECT date FROM rest_day WHERE date LIKE '"+year+"%' OR date LIKE '"+(year-1)+"%'";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	public List<String> getUserIdAll() {
		String sql = "select USER_ID from tor_horizon_user_dept WHERE DEPT_ID<>'HZ28e7f51e816d28011e816da7f1001f'";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).list();
	}

	@SuppressWarnings("unchecked")
	public List<String> getDeptNameUserIdAll(String deptName) {
		String sql = "select USER_ID from tor_horizon_user_dept a LEFT OUTER JOIN to_horizon_dept b ON a.DEPT_ID=b.ID WHERE b.DEPT_NAME=?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, deptName).list();
	}

}
