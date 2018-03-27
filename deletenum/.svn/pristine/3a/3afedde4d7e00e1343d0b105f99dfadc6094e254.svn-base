package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.SignificantAmountDao;
import com.henghao.entity.Personle;
@Repository
public class SignificantAmountDaoImpl extends BaseDao<Personle> implements SignificantAmountDao {
	/**
	 * 重大资金使用比例
	 */
	@Override
	public List<?> significantAmountProportion() {
		String sql = "SELECT SUM(m.ZJ),DEPT_NAME  FROM aj_great_meeting m,"
				+ " to_horizon_dept d, to_horizon_user u, "
				+ " tor_horizon_user_dept ud "
				+ " WHERE u.ID = ud.USER_ID"
				+ " AND ud.DEPT_ID = d.ID"
				+ " AND m.MTINITIATOR = u.NAME"
				+ " GROUP BY DEPT_NAME";
		List<?> list = getSession().createSQLQuery(sql).list();
		System.out.println(sql);
		return list;
	}
	
	/**
	 * 重大资金使用详情
	 */
	@Override
	public List<?> significantAmountDetails() {
		String sql = "SELECT Title,ZJ,SXJSRQ FROM aj_great_meeting ORDER BY SXJSRQ DESC";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

}
