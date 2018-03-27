package com.henghao.dao;

import java.util.List;
import com.henghao.entity.WithoutReason;




public interface AttendanceDetailsDao extends IBaseDao<WithoutReason>  {
	
	/**
	 * 按时间段查询迟到情况
	 * @param date
	 * @return
	 */
	public List<?> late(String date1,String date2);
	/**
	 * 按时间段查询早退情况
	 * @param date
	 * @return
	 */
	public List<?> early(String date1,String date2);
	
	/**
	 * 人员信息
	 * @return
	 */
	public List<?> attendInfo(String userId);
	/**
	 * 按月份查询迟到记录
	 * @param date
	 * @return
	 */
	public List<?> monthLate(String date);
	/**
	 * 按月份查询早退记录
	 * @param date
	 * @return
	 */
	public List<?> monthEarly(String date);
	
	/**
	 * 查询分组名称
	 * @return
	 */
	public List<?> groupName();
	/**
	 * 按分组查询人员信息
	 * @param groupName
	 * @return
	 */
	public List<?> personleInfo(String groupName);
	/**
	 * 查询节假日 休息日
	 * @param date
	 * @return
	 */
	public List<?> vacations(String date);
	/**
	 * 任务派发
	 * @param date
	 * @param name
	 * @return
	 */
	public List<?> distribution(String date,String name);
	/**
	 * 请假查询
	 * @param date
	 * @param name
	 * @return
	 */
	public List<?> leave(String date,String name);
	/**
	 * 出差查询
	 * @param date
	 * @param name
	 * @return
	 */
	public List<?> travel(String date,String name);
		
	/**
	 * 获取经纬度
	 * @return
	 */
	public List<?> latAndLon(String date,String name,String date3,String date4);
	/**
	 * 按年月周时间段查询迟到人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> findPersonleLateNum(String date1,String date2);
	/**
	 * 按时间段查询早退人数    年 月 周
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> findPersonleEarly(String date1 ,String date2);
	/**
	 * 按时间段 年月周查询无故外出人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> findPersonleWithout(String date1,String date2);
	
	/**
	 * 查询无故外出信息
	 * @param name
	 * @param date
	 * @return
	 */
	public List<?> findWithoutInfo(String name,String date);
		
		
	

}
