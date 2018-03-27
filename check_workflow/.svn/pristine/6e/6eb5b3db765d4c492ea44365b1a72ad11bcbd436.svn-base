package com.henghao.service;


import java.util.Map;

import com.henghao.entity.Checking;
import com.henghao.entity.Result;
import com.henghao.util.PageBaen;

/**
 * 考勤业务逻辑处理接口
 * @author Administrator
 *
 */
public interface ICheckingService {
	/**
	 * 查询指定用户某个时间段的考勤信息
	 * @return
	 */
	public Result findAllByUserName(String startDate,String endDate,PageBaen pageBaen,String user);
	/**
	 * 签到
	 */
	public Result addChecking(Checking checking);
	/**
	 * 根据用户id查询当天的签到记录
	 */
	public Result findByUserId(String userId,String date);
	/**
	 * 查询指定时间段内的签到记录
	 */
	public Result findQdListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的请假记录
	 */
	public Result findQjListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的出差记录
	 */
	public Result findCcListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的补签记录
	 */
	public Result findBqListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的迟到记录
	 */
	public Result findCdListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的早退记录
	 */
	public Result findZtListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的旷工记录
	 */
	public Result findKgListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的缺卡记录
	 */
	public Result findQkListByUserId(String userId,String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询指定时间段内的所有人考勤信息
	 */
	public Result findAllListByDate(String startDate,String endDate,PageBaen pageBaen);
	/**
	 * 查询某个人的指定时间段的考勤数目统计
	 */
	public Result findAllNumByUserId(String date,String userId,PageBaen pageBaen);
	/**
	 * 按姓名模糊查询指定一个月内的人的考勤信息
	 */
	public Result findAllListByDateAndUseName(String startDate,String endDate, String userName, PageBaen pageBaen);
	/**
	 * 按姓名模糊查询指定时间段内的所有人考勤信息
	 */
	public Result findCkByCkid(String ckId);
	/**
	 * 按部门查询指定一个月内的部门人的考勤信息
	 */
	public Result findAllListByDateAndDept(String startDate, String endDate,String dept,PageBaen pageBaen);
	/**
	 * 查询统计当天的人员信息统计
	 */
	public Result findCurrentCk(String date);
	/**
	 * 查询三重一大统计（重大问题抉择、重要干部任免、重大项目安排、大额资金使用）的申请总数、已审批、待审批数。
	 */
	public Result findSZYD(String date);
	/**
	 * 查询所有人部门，姓名，考勤率，本月正常打卡次数，经纬度
	 */
	public Result findck_seatByDept(String date,String dept);
	/**
	 * 查询最新轮播图片
	 */
	public Result  findImg(String condition);
	/**
	 * 查看指定时间内的所有人签到记录、请假记录、出差记录、补签记录。
	 */
	public Map<String, Object> findAllRecord(String startDate,String endDate);
}
