package com.henghao.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.henghao.dao.AttendanceDetailsDao;
import com.henghao.entity.WithoutReason;

@Repository
public class AttendanceDetailsDaoImpl extends BaseDao<WithoutReason> implements AttendanceDetailsDao {

	@Resource
	private SessionFactory sessionFactory;
	
	/**
	 * 按周时间段查询迟到情况
	 */
	@Override
	public List<?> late(String date1,String date2) {
		String sql = "SELECT g.GROUP_NAME,COUNT(*) FROM ci_checkingin ch,to_horizon_group g,to_horizon_user u,tor_horizon_user_group ug "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') >= ? "
				+ "AND DATE_FORMAT(currentDate,'%Y-%m-%d') <= ? "
				+ "AND checkLocation LIKE '迟到%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ug.USER_ID "
				+ "AND ug.WORKGROUP_ID = g.ID "
				+ "GROUP BY g.GROUP_NAME";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}
	/**
	 * 按时间段查询早退情况
	 */
	@Override
	public List<?> early(String date1,String date2) {
		String sql = "SELECT g.GROUP_NAME,COUNT(*) FROM ci_checkingin ch,to_horizon_group g,to_horizon_user u,tor_horizon_user_group ug "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') >= ? "
				+ "AND DATE_FORMAT(currentDate,'%Y-%m-%d') <= ? "
				+ "AND afterLocation LIKE '早退%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ug.USER_ID "
				+ "AND ug.WORKGROUP_ID = g.ID "
				+ "GROUP BY g.GROUP_NAME";
		List<?> list = getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
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
	 * 按月份和年查询迟到记录
	 */
	@Override
	public List<?> monthLate(String date) {
		String sql = "SELECT g.GROUP_NAME,COUNT(*) FROM ci_checkingin ch,to_horizon_group g,to_horizon_user u,tor_horizon_user_group ug "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') like '"+date+"%' "
				+ "AND checkLocation LIKE '迟到%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ug.USER_ID "
				+ "AND ug.WORKGROUP_ID = g.ID "
				+ "GROUP BY g.GROUP_NAME";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按月份和年查询早退记录
	 */
	@Override
	public List<?> monthEarly(String date) {
		String sql = "SELECT g.GROUP_NAME,COUNT(*) FROM ci_checkingin ch,to_horizon_group g,to_horizon_user u,tor_horizon_user_group ug "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') like '"+date+"%' "
				+ "AND afterLocation LIKE '早退%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ug.USER_ID "
				+ "AND ug.WORKGROUP_ID = g.ID "
				+ "GROUP BY g.GROUP_NAME";
		List<?> list = getSession().createSQLQuery(sql).list();
		return list;
	}
	/**
	 * 查询部门分组
	 */
	@Override
	public List<?> groupName() {
		String sql = "SELECT GROUP_NAME FROM to_horizon_group WHERE ORDER_NO <>1";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}
	/**
	 * 按部门查询人员
	 */
	@Override
	public List<?> personleInfo(String groupName) {
		String sql = "SELECT u.`NAME` FROM to_horizon_user u,tor_horizon_user_group ug,to_horizon_group g "
				+ "WHERE u.ID = ug.USER_ID "
				+ "AND ug.WORKGROUP_ID = g.ID "
				+ "AND g.GROUP_NAME=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, groupName).list();
		return list;
	}
	/**
	 * 查询节假日期
	 */
	@Override
	public List<?> vacations(String date) {
		String sql = "SELECT * FROM rest_day WHERE date=?";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date).list();
		return list;
	}
	/**
	 * 查询任务安排
	 */
	@Override
	public List<?> distribution(String date, String name) {
		String sql = "SELECT * FROM aj_rwpf "
				+ "WHERE starttime=? "
				+ "AND (GLNAME1 = ? OR GLNAME2 = ? OR GLNAME3 = ?)";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, name).setParameter(2, name).setParameter(3, name).list();
		return list;
	}
	/**
	 * 查询请假情况
	 */
	@Override
	public List<?> leave(String date, String name) {
		String sql = "SELECT * FROM aj_qingjia "
				+ "WHERE AGREE='同意' AND AGREE1='同意' "
				+ "AND STARTTIME>= ? "
				+ "AND ENDTIME <= ? "
				+ "AND `NAME` = ?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).setParameter(2, name).list();
		return list;
	}
	/**
	 * 查询出差情况
	 */
	@Override
	public List<?> travel(String date, String name) {
		String sql = "SELECT * FROM aj_chuchai "
				+ "WHERE AGREE='同意' AND AGREE1='同意' "
				+ "AND STARTTIME>= ? "
				+ "AND ENDTIME <=? "
				+ "AND CHUCHAI_RY= ?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).setParameter(2, name).list();
		return list;
	}
	/**
	 * 查询经纬度
	 */
	@Override
	public List<?> latAndLon(String date, String name, String date3, String date4) {
		String sql ="SELECT latitude,longitude FROM user_position_log "
				+ "WHERE ctime LIKE '"+date+"%' "
				+ "AND `name` = ? "
				+ "AND ctime >=? "
				+ "AND ctime <=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, date3).setParameter(2, date4).list();
		return list;
	}
	/**
	 * 按时间段 年月周查询迟到人数
	 */
	@Override
	public List<?> findPersonleLateNum(String date1, String date2) {
		String sql = "SELECT COUNT(DISTINCT u.`NAME`) AS num FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') >= ? "
				+ "AND DATE_FORMAT(currentDate,'%Y-%m-%d') <= ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND checkLocation LIKE '迟到%' "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}
	/**
	 * 按时间段 年月周查询早退人数
	 */
	@Override
	public List<?> findPersonleEarly(String date1, String date2) {
		String sql ="SELECT COUNT(DISTINCT u.`NAME`) AS num FROM ci_checkingin ch,to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "WHERE DATE_FORMAT(currentDate,'%Y-%m-%d') >= ? "
				+ "AND DATE_FORMAT(currentDate,'%Y-%m-%d') <= ? "
				+ "and DEPT_NAME <> '管理员' "
				+ "AND afterLocation LIKE '早退%'  "
				+ "AND ch.userId = u.ID "
				+ "AND u.ID = ud.USER_ID "
				+ "AND ud.DEPT_ID = d.ID";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}
	/**
	 * 按时间段 年月周查询无故外出人数
	 */
	@Override
	public List<?> findPersonleWithout(String date1, String date2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<?> findWithoutInfo(String name, String date) {
		String sql ="SELECT * FROM without_reason WHERE `name`=? AND time=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, date).list();
		return list;
	}


}
