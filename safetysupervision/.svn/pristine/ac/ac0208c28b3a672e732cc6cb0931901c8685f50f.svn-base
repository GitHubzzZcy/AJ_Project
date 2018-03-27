package com.henghao.service;

import com.henghao.entity.Result;

public interface AttendanceDetailsService {
	/**
	 * 规定日期迟到情况
	 * @param date
	 * @return
	 */
	public Result leave(String date1,String date2);
	/**
	 * 规定日期早退情况
	 * @param date
	 * @return
	 */
	public Result early(String date1,String date2);
	/**
	 * 按月份查询迟到记录
	 * @param date
	 * @return
	 */
	public Result monthLate(String date);
	/**
	 * 按月份查询早退记录
	 * @param date
	 * @return
	 */
	public Result monthEarly(String date);
	/**
	 * 按时间段无故外出次数
	 * @return
	 */
	public Result withoutReason(String date1,String date2);
	/**
	 * 按 时间段 年月周查询 迟到早退人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result findLateAndearlyNum(String date1,String date2);
	/**
	 * 按年月周时间段查询无故外出人数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result findwithoutReasonPersonleNum(String date1,String date2);
	
		
	
	
}
