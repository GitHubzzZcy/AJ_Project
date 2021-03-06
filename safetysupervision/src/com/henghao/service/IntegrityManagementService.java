package com.henghao.service;

import java.util.Map;

import com.henghao.entity.Result;

public interface IntegrityManagementService {

	/**
	 * 得分统计详情
	 * @param date
	 * @return
	 */
	public Result scoreStatistics(String deptName,String name);
	/**
	 * 人员信息
	 * @param date
	 * @return
	 */
	public Result personnelInformation(String deptName, String name);
	/**
	 * 车辆坐位类型
	 * @return
	 */
	public Result carNumType();
	/**
	 * 按日期查询用车次数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result useCarType(String date1,String date2);
	/**
	 * 车辆基本信息查询
	 * @param carNo
	 * @return
	 */
	public Result carInfo(String carNo);
	/**
	 * 车辆保养信息
	 * @param carNo
	 * @return
	 */
	public Result maintainInfo(String carNo);
		
	/**
	 * 所有车辆信息
	 * @return
	 */
	public Result carInfomation();
	/**
	 * 车状态
	 * @return
	 */
	public Result carsState(String date1,String date2);
	/**
	 * 用车信息
	 * @param carNo
	 * @return
	 */
	public Result carUseInfo(String carNo);
	/**
	 * 按时间段年月周查询非工作日用车次数
	 * @return
	 */
	public Result weekendUseCar(String date1,String date2);
	/**
	 * 按时间段年月周查询节假日用车次数
	 * @return
	 */
	public Result findVacationsUseCar(String date1,String date2);
	/**
	 * 按时间段 年月周 查询驾驶员与车不符 次数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result driverByCarNo(String date1,String date2);
	/**
	 * 按时间段查询用车超时
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result useCarByOvertime(String date1,String date2);
	
	/**
	 * 目的地是否偏离
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result addressInconsistent(String date1,String date2);
	/**
	 * 车回人不回查询
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result carReturnAndPersonle(String date1,String date2);
	/**
	 * 保存车回人不回信息
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result addCarReturnAndPersonle();
	/**
	 * 人车不符查询
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result menAndCarNot(String date1,String date2);
	/**
	 * 保存人车不符信息
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result addMenAndCarNot();
	
	
	public Map<String, Object> queryUseCarDetails(String start, String end, int page, int size);
	
		
	
}
