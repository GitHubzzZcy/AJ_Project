package com.henghao.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.henghao.dao.EfficiencyImprovementDao;
@Repository
public class EfficiencyImprovementDaoImpl extends BaseDao<T> implements EfficiencyImprovementDao{
	/**
	 * 人员信息查询
	 */
	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public List<?> personleSex() {
		String sql ="select u.SEX,COUNT(*) from to_horizon_dept d,to_horizon_user u, tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID "
				+ "AND DEPT_NAME <> '管理员' "
				+ "GROUP BY SEX ";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
	//	System.out.println(list);
		return list;
	}
	/**
	 * 部门查询
	 */
	@Override
	public List<?> findDept() {
		String sql ="select DEPT_NAME FROM to_horizon_dept WHERE DEPT_LEVEL <> 0 AND DEPT_LEVEL <> 1";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
	//	System.out.println(list);
		return list;
	}
	/**
	 * 部门人数查询
	 */
	@Override
	public List<?> deptPersonleNum() {
		String sql ="SELECT g.GROUP_NAME,COUNT(u.`NAME`) FROM to_horizon_user u,tor_horizon_user_group ug,to_horizon_group g "
				+ "WHERE u.ID = ug.USER_ID "
				+ "AND ug.WORKGROUP_ID = g.ID "
				+ "AND g.PARENT_ID='HZ28e7ef21e2b6520121e2f11e7005f6' "
				+ "GROUP BY g.GROUP_NAME";		
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return list;
	}
	
	/**
	 * 按时间段查询出差
	 */
	@Override
	public String travelNum(String date1,String date2) {
		String sql = "SELECT COUNT(*) FROM aj_chuchai a LEFT OUTER JOIN twr_hz_instance b ON a.ID = b.DATAID "
					+ "LEFT OUTER JOIN tw_hz_log c ON b.WORKID=c.WORKID "
					+ "WHERE c.NEXTNODEIDS='End1;' "
					+ "AND (a.AGREE<>'不同意' OR a.AGREE1<>'不同意')  "
					+ "AND a.STARTTIME>= ? AND a.STARTTIME <= ?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).uniqueResult().toString();
	}
	/**
	 * 按时间段查询请假
	 */
	@Override
	public String leaveNum(String date1,String date2) {
		String sql = "SELECT COUNT(*) FROM aj_qingjia a LEFT OUTER JOIN twr_hz_instance b ON a.ID = b.DATAID "
					+ "LEFT OUTER JOIN tw_hz_log c ON b.WORKID=c.WORKID "
					+ "WHERE c.NEXTNODEIDS='End1;' "
					+ "AND (a.AGREE<>'不同意' OR a.AGREE1<>'不同意')  "
					+ "AND a.LEAVECAUSE <>'休假' "
					+ "AND a.STARTTIME>= ? AND a.STARTTIME <= ?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).uniqueResult().toString();
	}
	/**
	 * 按时间段查询休假
	 */
	@Override
	public String vacationNum(String date1,String date2) {
		String sql = "SELECT COUNT(*) FROM aj_qingjia a LEFT OUTER JOIN twr_hz_instance b ON a.ID = b.DATAID "
					+ "LEFT OUTER JOIN tw_hz_log c ON b.WORKID=c.WORKID "
					+ "WHERE c.NEXTNODEIDS='End1;' "
					+ "AND (a.AGREE<>'不同意' OR a.AGREE1<>'不同意')  "
					+ "AND a.LEAVECAUSE ='休假' "
					+ "AND a.STARTTIME>= ? AND a.STARTTIME <= ?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).uniqueResult().toString();
	}

	/**
	 * 查询出生日期
	 */
	@Override
	public List<String> ageAll() {
		String sql ="SELECT BIRTH_DATE FROM to_horizon_user ";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return list;
	}

}
