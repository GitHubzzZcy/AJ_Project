package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.IntegrityManagementDao;
import com.henghao.entity.Personle;
@Repository
public class IntegrityManagementDaoImpl extends BaseDao<Personle> implements IntegrityManagementDao{
	/**
	 * 得分统计详情
	 */
	@Override
	public List<?> scoreStatistics(String name) {
		String sql =" SELECT SUM(FENZHI),h.TITLE FROM honesty_enter he,honesty_enterfujian h "
				+ "WHERE he.ID = h.PARENTID AND NAME like '%"+name+"%' "
				+ "GROUP BY h.TITLE";
	//	System.out.println(sql);
		List<?> list = getSession().createSQLQuery(sql).list();
	//	System.out.println(sql);
		return list;
	}
	
	/**
	 * 个人综合评分
	 */
	@Override
	public List<?> comprehensiveScore(String name) {
		String sql = "SELECT SUM(FEN) FROM honesty_enter WHERE NAME=? ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 加分条数
	 */
	@Override
	public List<?> plusCount(String name) {
		String sql = "SELECT COUNT(*) FROM honesty_enter WHERE NAME=? AND FEN NOT LIKE '-%' ";
		List<?> list= getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 加分条数详情
	 */
	@Override
	public List<?> plusCountDetails(String name) {
		String sql = "SELECT ENTER_TITLE FROM honesty_enter WHERE NAME=? AND FEN NOT LIKE '-%' ";
		List<?> list= getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 减分条数
	 */
	@Override
	public List<?> reductionCount(String name) {
		String sql ="SELECT COUNT(*) FROM honesty_enter WHERE NAME=? AND FEN LIKE '-%' ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 减分条数详情
	 */
	@Override
	public List<?> reductionCountDetails(String name) {
		String sql ="SELECT ENTER_TITLE FROM honesty_enter WHERE NAME=? AND FEN LIKE '-%' ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 季度分数比较
	 */
	@Override
	public List<?> quarterScore(String name,String date1,String date2) {
		String sql = "SELECT SUM(FEN),ENTER_CHECK FROM honesty_enter"
				+ " WHERE NAME=? AND DATE_FORMAT(TIME,'%Y-%m-%d')"
				+ " BETWEEN '"+date1+"' AND '"+date2+"' "
				+ "GROUP BY ENTER_CHECK";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		System.out.println(sql);
		return list;
	}

	@Override
	public List<?> option() {
		String sql ="select DICT_NAME FROM ts_horizon_dictionary WHERE PARENT_ID = 'HZ9080b95b6bb488015b6bb5dcb6004c'";
		List<?> list = getSession().createSQLQuery(sql).list();
		System.out.println(list);
		return list;
	}

}
