package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.EfficiencyImprovementDao;
import com.henghao.entity.Personle;
@Repository
public class EfficiencyImprovementDaoImpl extends BaseDao<Personle> implements EfficiencyImprovementDao{
	/**
	 * 人员信息查询
	 */
	@Override
	public List<?> personnelInformation(String name) {
		String sql ="select u.ID,u.NAME,u.POSITION,d.DEPT_NAME,u.WORK_DESC from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID "
				+ "AND NAME =?";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
	//	System.out.println(list);
		return list;
	}
	/**
	 * 局领导查询
	 */
	@Override
	public List<?> bureauLeader() {
		String sql ="select u.ID,u.NAME,u.POSITION,d.DEPT_NAME from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID AND d.ID = 'HZ9080955acfcfff015acfdf964b0205'";
		List<?> list = getSession().createSQLQuery(sql).list();
	//	System.out.println(list);
		return list;
	}
	/**
	 * 得分统计详情
	 */
	@Override
	public List<?> scoreStatistics(String name,String date) {
		String sql ="SELECT SUM(FEN),ENTER_CHECK FROM honesty_enter WHERE NAME=?"
				+ "AND DATE_FORMAT(TIME,'%Y-%m-%d') LIKE '"+date+"%'"
				+ " GROUP BY ENTER_CHECK";		
		List<?> list = getSession().createSQLQuery(sql).setParameter(0,name).list();
		return list;
	}
	
	/**
	 * 个人综合评分
	 */
	@Override
	public List<?> comprehensiveScore(String name,String year) {
		String sql = "SELECT SUM(FEN) FROM honesty_enter WHERE NAME=? AND DATE_FORMAT(TIME,'%Y-%m-%d') LIKE '"+year+"%' ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 加分条数
	 */
	@Override
	public List<?> plusCount(String name,String year) {
		String sql = "SELECT COUNT(*) FROM honesty_enter WHERE NAME=? AND FEN NOT LIKE '-%' AND DATE_FORMAT(TIME,'%Y-%m-%d') LIKE '"+year+"%' ";
		List<?> list= getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 加分条数详情
	 */
	@Override
	public List<?> plusCountDetails(String name,String year) {
		String sql = "SELECT ENTER_TITLE FROM honesty_enter WHERE NAME=? AND FEN NOT LIKE '-%' AND DATE_FORMAT(TIME,'%Y-%m-%d') LIKE '"+year+"%' ORDER BY TIME ";
		List<?> list= getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 减分条数
	 */
	@Override
	public List<?> reductionCount(String name,String year) {
		String sql ="SELECT COUNT(*) FROM honesty_enter WHERE NAME=? AND FEN LIKE '-%' AND DATE_FORMAT(TIME,'%Y-%m-%d') LIKE '"+year+"%' ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 减分条数详情
	 */
	@Override
	public List<?> reductionCountDetails(String name,String year) {
		String sql ="SELECT ENTER_TITLE FROM honesty_enter WHERE NAME=? AND FEN LIKE '-%' AND DATE_FORMAT(TIME,'%Y-%m-%d') LIKE '"+year+"%' ORDER BY TIME ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 市内市外次数
	 */
	@Override
	public List<?> vehicleStatisticsCount(String name,String year) {
		String sql = "SELECT COUNT(*),TRAVELSCOPE FROM tz_app_car_book WHERE USERS_NAME =? AND DATE_FORMAT(CREATETIME,'%Y-%m-%d') LIKE '"+year+"%' GROUP BY TRAVELSCOPE";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 行驶单程往返次数
	 */
	@Override
	public List<?> travelCategoryCount(String name,String year) {
		String sql = "SELECT COUNT(*),TRAVELCATEGOR FROM tz_app_car_book WHERE USERS_NAME =? AND DATE_FORMAT(CREATETIME,'%Y-%m-%d') LIKE '"+year+"%' GROUP BY TRAVELCATEGOR";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 用车总次数
	 */
	@Override
	public List<?> travelTotalCount(String name,String year) {
		String sql = "SELECT COUNT(*) FROM tz_app_car_book WHERE USERS_NAME = ? AND DATE_FORMAT(CREATETIME,'%Y-%m-%d') LIKE '"+year+"%'";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}
	/**
	 * 事件信息
	 */
	@Override
	public List<?> eventInformation(String userId,String date) {
		String sql = "SELECT SHIJIAN_EVENT,SHIJIAN_PLACE,SHIJIAN_TIME,TOKEN FROM aj_grgj WHERE USERID =? AND DATE_FORMAT(TIME,'%Y-%m-%d')=?";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, date).list();
		return list;
	}
	/**
	 * 事件图片
	 */
	@Override
	public List<?> eventInPicture(String token) {
		String sql = "SELECT SAVE_NAME FROM aj_eventimag WHERE TOKEN=? ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, token).list();
//		System.out.println(list.size());
//		System.out.println(list.get(0));
		return list;
		
		
	}
	/**
	 * 领导评分
	 */
	@Override
	public List<?> leadershipScore(String userId,String date) {
		String sql = "select STARTTIME,RWPF from aj_rwpf "
				+ "WHERE DATE_FORMAT(STARTTIME,'%Y-%m-%d') LIKE '"+date+"%' AND DUTYNAME LIKE '%"+userId+"'";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}
	/**
	 * 派车查询
	 */
	@Override
	public List<?> sendCarQuery() {
		String sql = "SELECT TITLE ,STARTTIME,DESTINATIONS,USERS_NAME FROM tz_app_car_book WHERE TITLE like '%会议%'  OR TITLE like '%开会%'";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}
	

}
