package com.henghao.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.henghao.dao.AttendanceDetailsDao;
import com.henghao.entity.Personle;
@Repository
public class AttendanceDetailsDaoImpl extends BaseDao<Personle> implements AttendanceDetailsDao {

	/**
	 * 统计规定日期出差情况
	 */
	@Override
	public List<?> evection(String date) {
		String sql = "SELECT u.ID ,CHUCHAI_RY,DEPT_NAME FROM aj_chuchai ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE STARTTIME <= ?"
				+ "and  ENDTIME>= ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND AGREE ='同意' AND AGREE1 = '同意' "
				+ "AND ch.CHUCHAI_RY = u.NAME "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).list();
		return list;

	}

	/**
	 * 统计规定日期请假情况
	 */
	@Override
	public List<?> leave(String date) {
		String sql = "SELECT u.ID ,q.name,DEPT_NAME FROM aj_qingjia q,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ " WHERE STARTTIME <=? and  ENDTIME>=?"
				+ " AND AGREE ='同意' AND AGREE1='同意' "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND q.name = u.NAME "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).list();
		return list;
	}

	/**
	 * 统计规定日期迟到情况
	 */
	@Override
	public List<?> late(String date) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME,checkLocation,clockInTime FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE currentDate = ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND checkLocation LIKE '迟到%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date).list();
		return list;
	}
	/**
	 * 统计规定日期早退情况
	 */
	@Override
	public List<?> early(String date) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME,afterLocation,clockOutTime FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud  "
				+ "WHERE currentDate = ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND afterLocation LIKE '早退%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date).list();
		return list;
	}

	/**
	 * 统计规定日期上午打卡情况
	 */
	@Override
	public List<?> morningAttendance(String date) {
		String sql = "SELECT u.ID FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE currentDate = ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND clockInTime IS NOT NULL "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date).list();
		return list;
	}

	/**
	 * 统计规定日期已打卡情况
	 */
	@Override
	public List<?> signedIn(String date) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE currentDate = ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND clockInTime IS NOT NULL "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date).list();
		return list;
	}
	
	/**
	 * 所有签到人员
	 */
	@Override
	public List<?> attendancePersonle() {
		String sql = "SELECT u.ID FROM to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID "
				+ "and DEPT_NAME <> '管理员' ";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}
	/**
	 * 人员信息
	 */
	@Override
	public List<?> attendInfo(String userId) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME FROM to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID AND u.ID =?"
				+ "and DEPT_NAME <> '管理员' ";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, userId).list();
		return list;
	}

	/**
	 * 按月份查询迟到记录
	 */
	@Override
	public List<?> monthLate(String date) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME,checkLocation,clockInTime,currentDate FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') like '"+date+"%' "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND checkLocation LIKE '迟到%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按月份查询早退记录
	 */
	@Override
	public List<?> monthEarly(String date) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME,afterLocation,clockOutTime,currentDate FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud  "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') like '"+date+"%'  "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND afterLocation LIKE '早退%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按月查询缺卡记录
	 */
	@Override
	public List<?> missingCard(String date) {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME,clockInTime,clockOutTime,currentDate FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud  "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') like '"+date+"%'"
				+ "AND (clockInTime IS NULL OR clockOutTime IS NULL) "
				+ "and DEPT_NAME <>'管理员' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	@Override
	public List<?> absenteeism() {
		String sql = "SELECT u.ID ,u.NAME,DEPT_NAME FROM to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID "
				+ "and DEPT_NAME <> '管理员' ";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

}
