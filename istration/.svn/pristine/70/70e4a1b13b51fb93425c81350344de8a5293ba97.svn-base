package com.henghao.news.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.henghao.news.entity.GradeResultEntity;
import com.henghao.news.entity.HzGradeDate;
import com.henghao.news.entity.Hzlog;
import com.henghao.news.entity.WorkList;
import com.henghao.news.util.DateUtils;
@Repository
public class GradeDao {
	@Resource
	private SessionFactory sessionFactory;
	private Map<String, Object> remap = new HashMap<String, Object>();
	
	@Value("${GRADE_OVERDUE_SH}") 
	private float GRADE_OVERDUE_SH;//逾期审核
	@Value("${GRADE_OVERDUE_SP}")
	private float GRADE_OVERDUE_SP;//逾期审批
	@Value("${GRADE_OVERDUE_WSH}")
	private float GRADE_OVERDUE_WSH;//逾期未审核
	@Value("${GRADE_OVERDUE_WSP}")
	private float GRADE_OVERDUE_WSP;//逾期未审批

	public Map<String, Object> queryAnjianTongjiOverdueDispose(String tableName, String userId, String flowid, List<String> fLOWID1sh, List<String> fLOWID1sp, String start, String end, List<String> holidayAndWeekend) throws ParseException {
		int count = 0;
		List<GradeResultEntity> relist = new ArrayList<GradeResultEntity>();
		//逾期审核
		float sh = 0;
		for (String node : fLOWID1sh) {
			String sql = "SELECT DOTIME,LIMITTIME,ACTIONTIME FROM TW_HZ_LOG WHERE USERID=? AND FLOWID=? AND NODEID=? AND ACTION<>'stop' AND ACTIONTIME>? AND ACTIONTIME<?"
					+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
			@SuppressWarnings("unchecked")
			List<Object> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userId)
																					.setParameter(1, flowid)
																					.setParameter(2, node)
																					.setParameter(3, start)
																					.setParameter(4, end).list();
			for (Object object : list) {
				count++;
				Object[] obj = (Object[]) object;
				String doTime = obj[0] == null ? null:obj[0].toString();
				String limitTime = obj[1] == null ? null:obj[1].toString();
				String actiontime = obj[2] == null ? null:obj[2].toString();
				if(DateUtils.getDoActionTime(holidayAndWeekend, doTime, actiontime) >= getLimitTime(limitTime)) {
					//逾期天数
					float day = DateUtils.getDoActionTime(holidayAndWeekend, doTime, actiontime) - getLimitTime(limitTime) + 1;
					sh -= day * GRADE_OVERDUE_SH;
					relist.add(new GradeResultEntity("减分", "绩", "逾期审核", String.valueOf(day * GRADE_OVERDUE_SH), actiontime));
				}
			}
		}
		//逾期审批
		float sp = 0;
		for (String node : fLOWID1sp) {
			String sql = "SELECT DOTIME,LIMITTIME,ACTIONTIME FROM TW_HZ_LOG WHERE USERID=? AND FLOWID=? AND ACTION<>'stop' AND NODEID=? AND ACTIONTIME>? AND ACTIONTIME<?"
					+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
			@SuppressWarnings("unchecked")
			List<Hzlog> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, userId).setParameter(1, flowid)
																						.setParameter(2, node)
																						.setParameter(3, start)
																						.setParameter(4, end)
																						.setResultTransformer(Transformers.aliasToBean(Hzlog.class))
																						.list();
			for (Hzlog hzlog : list) {
				count++;
				String doTime = hzlog.getDOTIME();
				String limitTime = hzlog.getLIMITTIME();
				String actiontime = hzlog.getACTIONTIME();
				if(DateUtils.getDoActionTime(holidayAndWeekend, doTime, actiontime) >= getLimitTime(limitTime)) {
					//逾期天数
					float day = DateUtils.getDoActionTime(holidayAndWeekend, doTime, actiontime) - getLimitTime(limitTime) + 1;
					System.out.println("day:::"+day);
					sp = sp - day * GRADE_OVERDUE_SP;
					relist.add(new GradeResultEntity("减分", "绩", "逾期审批", String.valueOf(day * GRADE_OVERDUE_SP), actiontime));
				}
			}
		}
		remap.clear();
		remap.put("relist", relist);
		remap.put("grade", sp+sh);
		remap.put("count", count);
		return remap;
	}

	
	
	private int getLimitTime(String limitTime) {
		if(!(limitTime == null || "".equals(limitTime))) {
			int indexOf = limitTime.indexOf("(");
			return Integer.parseInt(limitTime.substring(0, indexOf));
		}
		return 0;
	}



	public List<WorkList> queryLogNewestByFlowid(String tableName, String userId, String flowid, String start, String end) {
		String sql = "SELECT NODEID,LIMITTIME,SENDTIME FROM tw_hz_worklist  WHERE (workid,id) IN (SELECT workid,MAX(id) FROM tw_hz_worklist  GROUP BY workid) AND FLOWID=? AND SENDTIME >? AND SENDTIME<? AND AUTH_ID=? AND TRACKSTATUS='110'"
				+ " AND WORKID IN (SELECT z.WORKID FROM "+tableName+" y LEFT OUTER JOIN twr_hz_instance z ON y.ID=z.DATAID)";
		@SuppressWarnings("unchecked")
		List<WorkList> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
			.setParameter(0, flowid)
			.setParameter(1, start)
			.setParameter(2, end)
			.setParameter(3, userId)
			.setResultTransformer(Transformers.aliasToBean(WorkList.class)).list();
		return list;
	}
	public Map<String, Object> queryManageIllegal(String username, String type, String start, String end) {
		String sql = "SELECT time FROM report_information a LEFT OUTER JOIN complaint_handling b ON a.`code`=b.`code` WHERE problemCategories=? AND b.examinationResult='属实' AND a.time>? AND a.time<? AND informantsName=?";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
										.setParameter(0, type)
										.setParameter(1, start)
										.setParameter(2, end)
										.setParameter(3, username)
										.list();
		remap.clear();
		remap.put("list", list);
		remap.put("count", list.size());
		return remap;
	}
	
	/**
	 * 查询节假日期
	 */
	public List<?> vacations(String date) {
		String sql = "SELECT * FROM rest_day WHERE date=?";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date).list();
		return list;
	}
	/**
	 * 查询任务安排
	 */
	public List<?> distribution(String date, String name) {
		String sql = "SELECT * FROM aj_rwpf "
				+ "WHERE starttime=? "
				+ "AND (GLNAME1 = ? OR GLNAME2 = ? OR GLNAME3 = ?)";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, name).setParameter(2, name).setParameter(3, name).list();
		return list;
	}
	/**
	 * 查询请假情况
	 */
	public List<?> leave(String date, String name) {
		String sql = "SELECT * FROM aj_qingjia "
				+ "WHERE AGREE='同意' AND AGREE1='同意' "
				+ "AND STARTTIME>= ? "
				+ "AND ENDTIME <= ? "
				+ "AND `NAME` = ?";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).setParameter(2, name).list();
		return list;
	}
	/**
	 * 查询出差情况
	 */
	public List<?> travel(String date, String name) {
		String sql = "SELECT * FROM aj_chuchai "
				+ "WHERE AGREE='同意' AND AGREE1='同意' "
				+ "AND STARTTIME>= ? "
				+ "AND ENDTIME <=? "
				+ "AND CHUCHAI_RY= ?";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date).setParameter(1, date).setParameter(2, name).list();
		return list;
	}
	/**
	 * 查询经纬度
	 */
	public List<?> latAndLon(String date, String name, String date3, String date4) {
		String sql ="SELECT latitude,longitude FROM user_position_log WHERE ctime >= STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') AND ctime <= STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s') AND `name`=?";
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, date3).setParameter(1, date4).setParameter(2, name).list();
		return list;
	}



	public List<HzGradeDate> queryHzGradeClass(String userId, String start, String end) {
		String sql = "SELECT a.ID, a.TITLE, a.Created, a.Creator, a.PEOPLE, b.TIME, b.SM, b.XX, b.FZ FROM aj_grade_data a LEFT OUTER JOIN aj_grade_type b ON a.ID=b.PARENTID "
				+ "WHERE b.TIME>=? AND b.TIME<=? AND a.PEOPLE=?";
		@SuppressWarnings("unchecked")
		List<HzGradeDate> list = sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, userId).setResultTransformer(Transformers.aliasToBean(HzGradeDate.class)).list();
		return list;
	}


	//督责评分
	public List<String> queryHzDuze(String userName, String start, String end) {
		String sql = "SELECT YTSJ FROM aj_dz WHERE BDZYY = ? AND YTSJ >= ? AND YTSJ<=?";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
		.setParameter(0, userName)
		.setParameter(1, start)
		.setParameter(2, end)
		.list();
		return list;
	}
	//问责评分
	public List<String> queryHzWenze(String userName, String start, String end) {
		String sql = "SELECT YTSJ FROM aj_wz WHERE BWZYY = ? AND YTSJ >= ? AND YTSJ<=?";
		@SuppressWarnings("unchecked")
		List<String> list = sessionFactory.getCurrentSession().createSQLQuery(sql)
		.setParameter(0, userName)
		.setParameter(1, start)
		.setParameter(2, end)
		.list();
		return list;
	}
	
}
