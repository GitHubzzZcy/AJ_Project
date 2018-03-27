package com.henghao.dao;
import java.util.List;
import java.util.Map;

import com.henghao.entity.CarBookEntity;
import com.henghao.entity.ReturnCar;

public interface IntegrityManagementDao extends IBaseDao<ReturnCar>  {
	/**
	 * 得分统计详情
	 * @param date
	 * @return
	 */
	public List<?> scoreStatistics(String name1);
	/**
	 * 个人综合评分
	 * @param date
	 * @return
	 */
	public List<?> comprehensiveScore(String name);
	/**
	 * 人员信息
	 * @param name
	 * @return
	 */
	public List<Object> personnelInformation(String deptName);
	/**
	 * 人员信息
	 * @param deptName
	 * @param name
	 * @return
	 */
	public List<Object> personnelInfo(String deptName,String name);
	/**
	 * 所有人员信息
	 * @param deptName
	 * @param name
	 * @return
	 */
	public List<Object> personnelInfoAll(String deptName,String name);
	/**
	 * 按部门查询
	 * @param dateName
	 * @return
	 */
	public List<Object> deptPersonle(String deptName);
	/**
	 * 按部门人员查询
	 * @param deptName
	 * @param name
	 * @return
	 */
	public List<Object> deptAndPersonleName(String deptName,String name);
	/**
	 * 车辆座位类型及车辆数量
	 * @return
	 */
	public List<?> carNumType();
	/**
	 * 按时间查询车辆使用类型次数
	 * @return
	 */
	public List<?> useCarType(String date1,String date2);
	 /**
	  * 车辆基本信息查询
	  * @param carNo
	  * @return
	  */
	public List<?> carInfo(String carNo);
	/**
	 * 车辆信息查询
	 * @return
	 */
	public List<?> carInfomation();
	/**
	 * 查询车辆车牌号
	 * @return
	 */
	public List<?> carInfo();
	/**
	 * 车辆保养信息
	 * @param carNo
	 * @return
	 */
	public List<?> maintainInfo(String carNo);
	/**
	 * 用车申请
	 * @param carNo
	 * @return
	 */
	public List<?> useCarApply(String carNo,String date1,String date2);
	/**
	 * 用车中
	 * @param carNo
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> useCar(String carNo,String date1,String date2);
		
	/**
	 * 车辆保养还是修理查询
	 * @param carNo
	 * @return
	 */
	public List<?> maintainType(String carNo,String date1,String date2);
	/**
	 * 车辆使用信息查询
	 * @param carNo
	 * @return
	 */
	public List<?> carUseInfo(String carNo);
	/**
	 * 按时间段 年月日查询周末信息
	 * @return
	 */
	public List<?> findWeekend(String date1,String date2);
	/**
	 * 按日期查询用车信息
	 * @return
	 */
	public List<?> findUseCarInfo(String date);
	/**
	 * 按时间段 年月日查询节假日信息
	 * @return
	 */
	public List<?> findVacations(String date1,String date2);
	/**
	 * 按时间段 年月周查询驾驶员与车不符次数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> driverByCarNo(String date1,String date2);
	/**
	 * 按年月周时间段查询用车超时次数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> useCarByOvertime(String date1,String date2);
	/**
	 * 查询用车信息 车牌号 开始结束时间
	 * @return
	 */
	public List<?> useCarTime();

	/**
	 * 查询用车地不符
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> addressInconsistent(String date1,String date2);
	/**
	 * 人员经纬度查询
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> personleLatLng(String name,String time1,String time2);
	/**
	 * 用车查询 时间 用车人名
	 * @return
	 */
	public List<CarBookEntity> carUseTime(String date1,String date2);
	
	/**
	 * 查询车回人不回信息
	 * @param name
	 * @param time
	 * @return
	 */
	public List<?> findReturnCarInfo(String name,String time);
	
	
	
	public Map<String, Object> queryUseCarDetails(String start, String end, int page, int size);
	
		
}
