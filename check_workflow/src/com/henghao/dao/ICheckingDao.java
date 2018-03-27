package com.henghao.dao;

import java.util.List;
import java.util.Map;

import com.henghao.entity.Checking;
import com.henghao.util.PageBaen;
/**
 * 考勤dao接口
 * @author NWL
 *
 */

public interface ICheckingDao extends IBaseDao<Checking>{
	/**
	 * 查询指定用户某个时间段的考勤信息
	 */
	public Map<String,Object> findAllByUserName(String startDate,String endDate,PageBaen pageBaen,String user);
	/**
	 * 根据用户id查询当天的签到记录
	 */
	public Map<String, Object> findByUserId(String userId,String date);
	/**
	 * 查询指定时间段内的签到记录
	 */
	public Map<String, Object> findQdListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的请假记录
	 */
	public Map<String, Object> findQjListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的出差记录
	 */
	public Map<String, Object> findCcListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的补签记录
	 */
	public Map<String, Object> findBqListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的迟到记录
	 */
	public Map<String, Object> findCdListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen,String chInTime);
	/**
	 * 查询指定时间段内的早退记录
	 */
	public Map<String, Object> findZtListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen,String chOutTime);
	/**
	 * 查询指定时间段内的旷工记录
	 */
	public Map<String, Object> findKgListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的缺卡记录
	 */
	public Map<String, Object> findQkListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的所有人考勤信息
	 */
	public Map<String, Object> findAllListByDate(String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 按姓名模糊查询指定时间段内的所有人考勤信息
	 */
	public Map<String, Object> findAllListByDateAndUseName(String startDate,String endDate,String userName,PageBaen pageBaen);
	/**
	 * 按签到id查询签到信息
	 */
	public Map<String, Object> findCkByCkid(String ckId);
	/**
	 * 按部门查询指定一个月内的部门人的考勤信息
	 */
	public Map<String, Object> findAllListByDateAndDept(String startDate,String endDate,String dept,PageBaen pageBaen);
	/**
	 * 查询统计当天的人员信息统计
	 */
	public Map<String, Object> findCurrentCk(String date);
	/**
	 * 查询指定时间段内的出差天数
	 */
	public int findCcDays(String userId,String startDate,String endDate);
	/**
	 * 查询指定时间段内的请假天数
	 */
	public int findQjDays(String userId,String startDate,String endDate);
	/**
	 * 查询指定时间段内的正常打卡天数
	 */
	public int findNormalCkDays(String userId,String startDate,String endDate);
	/**
	 * 查询三重一大统计（重大问题抉择、重要干部任免、重大项目安排、大额资金使用）的申请总数、已审批、待审批数。
	 */
	public Map<String, Object> findSZYD(String startDate, String endDate);
	/**
	 * 查询所有人部门，姓名，考勤率，本月正常打卡次数，经纬度
	 */
	public Map<String, Object> findck_seatByDept(String startDate, String endDate,String ckin,String ckout,String dept);
	/**
	 * 查询最新轮播图片
	 */
	public Map<String, Object> findImg(String condition);
	/**
	 * 查看指定时间内的所有人签到记录、请假记录、出差记录、补签记录。
	 */
	public Map<String, Object> findAllRecord(String startDate,String endDate);
	public List<String> queryMkqUser();
}
