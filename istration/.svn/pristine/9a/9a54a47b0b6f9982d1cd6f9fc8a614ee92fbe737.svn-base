package com.henghao.istration.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.henghao.istration.dao.IPerformanceDao;

@Repository("performanceDao")
public class PerformanceDaoImpl implements IPerformanceDao {


	@Resource
	private SessionFactory sessionFactory;
	@Override
	public String findByUserId(String name) {
		String userId;
		try {
			String sql = "SELECT A.ID FROM to_horizon_user A WHERE NAME=?";
			userId = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).uniqueResult().toString();
			return userId;
		} catch (NullPointerException e) {
		}
		return null;
	}
	@Override
	public List<String> queryPerf(String name, String string, String formatedDate) {
		String sql = "SELECT FEN FROM honesty_enter WHERE NAME=? AND ENTER_CHECK=? AND TIME LIKE ?";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, string).setParameter(2, formatedDate+"%").list();
		return list;
	}
	@Override
	public String querylog(String name, String formatedDate) {
		String sql = "SELECT COUNT(*) FROM tw_hz_log A WHERE A.USERNAME = ? AND A.ACTIONTIME LIKE ?";
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, formatedDate+"%").uniqueResult().toString();
		return count;
	}
	@Override
	public String querylcCount(String string, String formatedDate) {
		String sql = null;
		if(string.equals("yichu")) {
			sql = "SELECT COUNT(*) FROM tw_hz_log WHERE FLOWID ='feimeikuangshan(yichu)' AND NODEID='Start' AND ACTIONTIME LIKE ?";
		}
		if(string.equals("scone")) {
			sql = "SELECT COUNT(*) FROM tw_hz_log WHERE FLOWID ='sichu(chucishenqing)' AND NODEID='Start' AND ACTIONTIME LIKE ?";
		}
		if(string.equals("sctwo")) {
			sql = "SELECT COUNT(*) FROM tw_hz_log WHERE FLOWID ='sichu(fushen)' AND NODEID='Start' AND ACTIONTIME LIKE ?";
		}
		String count = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, formatedDate+"%").uniqueResult().toString();
		return count;
	}
	@Override
	public String queryDept(String name) {
		String sql = "SELECT C.DEPT_NAME FROM to_horizon_user A JOIN tor_horizon_user_dept B ON A.ID=B.USER_ID JOIN to_horizon_dept C ON B.DEPT_ID=C.ID WHERE A.`NAME`=?";
		String dept = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, name).uniqueResult().toString();
		return dept;
	}

}
