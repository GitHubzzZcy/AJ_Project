package com.henghao.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.henghao.dao.IntegrityManagementDao;
import com.henghao.entity.CarBookEntity;
import com.henghao.entity.ReturnCar;

@Repository
public class IntegrityManagementDaoImpl extends BaseDao<ReturnCar> implements IntegrityManagementDao {
	/**
	 * 得分统计详情
	 */
	@Override
	public List<?> scoreStatistics(String name1) {
		String sql = " SELECT h.TITLE,SUM(FENZHI) FROM honesty_enter he,honesty_enterfujian h "
				+ "WHERE he.ID = h.PARENTID AND NAME like '%" + name1 + "%' " + "GROUP BY h.TITLE";
		// System.out.println(sql);
		List<?> list = getSession().createSQLQuery(sql).list();
		// System.out.println(sql);
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
	 * 人员信息查询
	 */
	@Override
	public List<Object> personnelInformation(String deptName) {
		String sql = "select u.NAME,u.POSITION,u.TELEPHONE,h.SAVE_NAME,h.EXTENTION,dept_Name,u.ORDER_NO from to_horizon_dept d "
				+ "INNER JOIN tor_horizon_user_dept ud  ON ud.DEPT_ID = d.ID "
				+ "INNER JOIN to_horizon_user u ON u.ID = ud.USER_ID "
				+ "LEFT JOIN ta_horizon_info h ON u.ID = h.OBJECT_ID "
				+ "WHERE DEPT_NAME <>'管理员' "
				+ "AND DEPT_NAME like '%" + deptName + "%'";
		@SuppressWarnings("unchecked")
		List<Object> list = getSession().createSQLQuery(sql).list();
		// System.out.println(list);
		return list;
	}

	/**
	 * 按部门人员名称查询
	 */
	@Override
	public List<Object> personnelInfo(String deptName, String name) {
		String sql = "select u.NAME,u.POSITION,u.TELEPHONE,h.SAVE_NAME,h.EXTENTION,dept_Name,u.ORDER_NO from to_horizon_dept d "
				+ "INNER JOIN tor_horizon_user_dept ud  ON ud.DEPT_ID = d.ID "
				+ "INNER JOIN to_horizon_user u ON u.ID = ud.USER_ID "
				+ "LEFT JOIN ta_horizon_info h ON u.ID = h.OBJECT_ID "
				+ "WHERE DEPT_NAME <>'管理员' "
				+ "AND DEPT_NAME like '%" + deptName + "%' "
				+ "AND u.`NAME` = ?";
		@SuppressWarnings("unchecked")
		List<Object> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 按部门查询人员
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> deptPersonle(String deptName) {
		String sql = "select u.NAME from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID AND DEPT_NAME like '%" + deptName + "%'";
		List<Object> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 查询所有人员信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> personnelInfoAll(String deptName, String name) {
		String sql = "select u.NAME,u.POSITION,u.TELEPHONE,h.SAVE_NAME,h.EXTENTION,dept_Name, u.ORDER_NO from to_horizon_dept d "
				+ "INNER JOIN tor_horizon_user_dept ud  ON ud.DEPT_ID = d.ID "
				+ "INNER JOIN to_horizon_user u ON u.ID = ud.USER_ID "
				+ "LEFT JOIN ta_horizon_info h ON u.ID = h.OBJECT_ID "
				+ "WHERE DEPT_NAME <>'管理员'";

		List<Object> list = getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按名称查询人员名称
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> deptAndPersonleName(String deptName, String name) {
		String sql = "select u.NAME from to_horizon_dept d,to_horizon_user u,tor_horizon_user_dept ud "
				+ "where u.ID = ud.USER_ID AND ud.DEPT_ID = d.ID " + "AND DEPT_NAME like '%" + deptName + "%' "
				+ "AND u.`NAME` = ?";
		List<Object> list = getSession().createSQLQuery(sql).setParameter(0, name).list();
		return list;
	}

	/**
	 * 车辆座位类型
	 */
	@Override
	public List<?> carNumType() {
		String sql = "select SEATINGNUM,COUNT(*) from tz_app_car_info  GROUP BY SEATINGNUM";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按时间段查询用车次数
	 */
	@Override
	public List<?> useCarType(String date1, String date2) {
//		String sql = "select TITLE,COUNT(*) from tz_app_car_book WHERE CREATETIME>=" + date1 + "  AND CREATETIME<="
//				+ date2 + " GROUP BY TITLE";
		//1月3号zcy改sql，以上sql未判断用车申请是否通过
		String sql = "SELECT a.TITLE, COUNT(*) FROM tz_app_car_book a LEFT  OUTER JOIN to_horizon_user b "
				+ "ON SUBSTRING(a.USERS, 3, LENGTH(a.USERS))=b.ID LEFT OUTER JOIN twr_hz_instance c ON a.ID=c.DATAID "
				+ "LEFT OUTER JOIN tw_hz_log d ON c.WORKID=d.WORKID WHERE d.NEXTNODEIDS='End2;' AND a.ENDTIME>? AND a.ENDTIME <= ? GROUP BY TITLE;";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	/**
	 * 车辆基本信息
	 */
	@Override
	public List<?> carInfo(String carNo) {
		String sql = "select i.TITLE,i.CARNO,i.COLOR,i.SEATINGNUM,i.TYPE,i.DISPLACEMEN,i.CREATETIME,i.CLSSBM,i.DRIVER,d.TEL FROM tz_app_car_info i,tz_app_car_driver d "
				+ "WHERE CARNO= ? AND d.TITLE_NAME = i.DRIVER";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, carNo).list();
		return list;
	}

	/**
	 * 车辆保养信息
	 */
	@Override
	public List<?> maintainInfo(String carNo) {
		String sql = "SELECT TYPE,MILEAGE,STARTTIME,ENDTIME,MEMO FROM tz_app_car_upkeep" + " WHERE TITLE LIKE '%"
				+ carNo + "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 所有车辆信息
	 */
	@Override
	public List<?> carInfomation() {
		String sql = "select TITLE,CARNO,h.SAVE_NAME,h.EXTENTION FROM tz_app_car_info c,ta_horizon_info h WHERE c.ID = h.OBJECT_ID ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 用车申请
	 */
	@Override
	public List<?> useCarApply(String carNo, String date1, String date2) {
		String sql = " SELECT CARNO FROM tz_app_car_book " + "where STARTTIME >=? " + "AND STARTTIME <=? "
				+ "AND CARNO =? ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2)
				.setParameter(2, carNo).list();
		return list;
	}

	/**
	 * 保养类型
	 */
	@Override
	public List<?> maintainType(String carNo, String date1, String date2) {
		String sql = "SELECT TYPE FROM tz_app_car_upkeep " + "WHERE TITLE like'%" + carNo + "%' " + "AND STARTTIME >=? "
				+ "AND STARTTIME <=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	/**
	 * 用车信息
	 */
	@Override
	public List<?> carUseInfo(String carNo) {
		String sql = " SELECT COSTDEPT,STARTTIME,ENDTIME,ORIGIN,DESTINATIONS,"
				+ "TRAVELSCOPE,TRAVELCATEGOR,TITLE,USERS_NAME,F1 FROM tz_app_car_book " + "where CARNO =?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, carNo).list();
		return list;
	}

	/**
	 * 查询车辆车牌号
	 */
	@Override
	public List<?> carInfo() {
		String sql = "SELECT c.CARNO,SAVE_NAME,EXTENTION from tz_app_car_info c,ta_horizon_info h WHERE c.ID = h.OBJECT_ID ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 用车中
	 */
	@Override
	public List<?> useCar(String carNo, String date1, String date2) {
		String sql = "SELECT CARNO FROM tz_app_car_book " + "where CREATETIME >='" + date1 + "%' "
				+ "AND CREATETIME <='" + date2 + "%' " + "AND CARNO =?  ";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, carNo).list();
		return list;
	}

	/**
	 * 按时间段年月周查询周末日期
	 */
	@Override
	public List<?> findWeekend(String date1, String date2) {
		String sql = "SELECT date FROM rest_day " + "WHERE `status` = '周末' " + "AND date>=? " + "AND date<=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	/**
	 * 按日期查询用车信息
	 */
	@Override
	public List<?> findUseCarInfo(String date) {
		String sql = "SELECT * FROM tz_app_car_userecord WHERE CREATETIME LIKE '" + date + "%' ";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按时间段年月周查询节假日期
	 */
	@Override
	public List<?> findVacations(String date1, String date2) {
		String sql = "SELECT date FROM rest_day " + "WHERE `status` = '法定假日' " + "AND date>=? " + "AND date<=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).list();
		return list;
	}

	/**
	 * 按时间段 年月周查询驾驶员与车不符
	 */
	@Override
	public List<?> driverByCarNo(String date1, String date2) {
		String sql = "SELECT d.TITLE_NAME,i.DRIVER FROM tz_app_car_userecord u,tz_app_car_driver d,tz_app_car_info i "
				+ "WHERE u.CREATOR = d.ID " + "AND u.CARID = i.ID " + "AND STARTTIME>='" + date1 + "%' "
				+ "AND ENDTIME <= '" + date2 + "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 按时间段查询用车超时
	 */
	@Override
	public List<?> useCarByOvertime(String date1, String date2) {
		String sql = "SELECT * FROM tz_app_car_userecord " + "WHERE CSSJ > 0 " + "AND STARTTIME>='" + date1 + "%' "
				+ "AND ENDTIME <= '" + date2 + "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 查询用车信息 和时间
	 */
	@Override
	public List<?> useCarTime() {
		String sql = "SELECT CARNO,STARTTIME,ENDTIME,DESTINATIONS FROM tz_app_car_book";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 查询目的地偏离
	 */
	@Override
	public List<?> addressInconsistent(String date1, String date2) {
		String sql = "SELECT COUNT(*) FROM deviate_type WHERE deviateType ='偏离' " + "AND startTime >='" + date1 + "%'"
				+ " AND endTime<='" + date2 + "%'";
		List<?> list = super.getSession().createSQLQuery(sql).list();
		return list;
	}

	/**
	 * 查询人员经纬度 和定位时间
	 */
	@Override
	public List<?> personleLatLng(String name, String time1, String time2) {
		String sql = "SELECT latitude,longitude,ctime FROM user_position_log " + "WHERE `name` = ? " + "AND ctime >=? "
				+ "AND ctime <=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, time1)
				.setParameter(2, time2).list();
		return list;
	}

	/**
	 * 按时间段查询车辆使用时间及用车人员
	 */
	@Override
	public List<CarBookEntity> carUseTime(String date1, String date2) {
		String sql = "SELECT ID, USERS, CARNO, TITLE, STARTTIME, ENDTIME, ORIGIN, DESTINATIONS, TRAVELCATEGOR FROM tz_app_car_book WHERE STARTTIME >=? AND STARTTIME<=?";
		@SuppressWarnings("unchecked")
		List<CarBookEntity> list = super.getSession().createSQLQuery(sql).setParameter(0, date1).setParameter(1, date2).setResultTransformer(Transformers.aliasToBean(CarBookEntity.class)).list();
		return list;
	}

	/**
	 * 查询数据库是否有车回人不回信息
	 */
	@Override
	public List<?> findReturnCarInfo(String name, String time) {
		String sql = "SELECT * FROM returncar_notpersonle WHERE `name`=? AND time=?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, name).setParameter(1, time).list();
		return list;
	}

	@Override
	public Map<String, Object> queryUseCarDetails(String start, String end, int page, int size) {
		String csql = "SELECT COUNT(*) "
				+ "FROM tz_app_car_book a LEFT  OUTER JOIN to_horizon_user b ON SUBSTRING(a.USERS, 3, LENGTH(a.USERS))=b.ID "
				+ "LEFT OUTER JOIN twr_hz_instance c ON a.ID=c.DATAID "
				+ "LEFT OUTER JOIN tw_hz_log d ON c.WORKID=d.WORKID WHERE d.NEXTNODEIDS='End2;' AND a.ENDTIME>? AND a.ENDTIME <= ?";
		String string = super.getSession().createSQLQuery(csql).setParameter(0, start).setParameter(1, end).uniqueResult().toString();
		int total = Integer.parseInt(string);
		if(size > total) {
			size = total;
		}
		if((page-1) * size > total) {
			page = total % size == 0 ? total/size : total/size + 1;
		}
		String sql = "SELECT b.`NAME`, a.STARTTIME, a.ENDTIME, a.TITLE, a.TRAVELSCOPE, a.ORIGIN, a.DESTINATIONS "
				+ "FROM tz_app_car_book a LEFT  OUTER JOIN to_horizon_user b ON SUBSTRING(a.USERS, 3, LENGTH(a.USERS))=b.ID "
				+ "LEFT OUTER JOIN twr_hz_instance c ON a.ID=c.DATAID "
				+ "LEFT OUTER JOIN tw_hz_log d ON c.WORKID=d.WORKID WHERE d.NEXTNODEIDS='End2;' AND a.ENDTIME>? AND a.ENDTIME <= ? LIMIT ?,?";
		List<?> list = super.getSession().createSQLQuery(sql).setParameter(0, start).setParameter(1, end).setParameter(2, (page-1)*size).setParameter(3, size).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("list", list);
		return map;
	}

}
