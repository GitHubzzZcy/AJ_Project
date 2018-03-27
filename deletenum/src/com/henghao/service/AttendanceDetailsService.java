package com.henghao.service;

import com.henghao.entity.Result;

public interface AttendanceDetailsService {
	/**
	 * 规定日期出差情况
	 * @param date
	 * @return
	 */
	public Result evection(String date);
	/**
	 * 规定日期请假情况
	 * @param date
	 * @return
	 */
	public Result late(String date);
	/**
	 * 规定日期迟到情况
	 * @param date
	 * @return
	 */
	public Result leave(String date);
	/**
	 * 规定日期早退情况
	 * @param date
	 * @return
	 */
	public Result early(String date);
	/**
	 * 规定日期上午签到情况
	 * @param date
	 * @return
	 */
	public Result noSignIn(String date);
	/**
	 * 规定日期下午签到情况
	 * @param date
	 * @return
	 */
	public Result signedIn(String date);
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
	 * 按月份查询缺卡记录
	 * @param date
	 * @return
	 */
	public Result missingCard(String date);
	/**
	 * 按月份查询旷工记录
	 * @param date
	 * @return
	 */
	public Result absenteeism(String date);
}
