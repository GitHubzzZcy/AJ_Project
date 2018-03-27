package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Personle;



public interface AttendanceDetailsDao extends IBaseDao<Personle>  {
	/**
	 * 出差详情
	 * @param date
	 * @return
	 */
	public List<?> evection(String date);
	/**
	 * 请假情况
	 * @param date
	 * @return
	 */
	public List<?> leave(String date);
	/**
	 * 迟到情况
	 * @param date
	 * @return
	 */
	public List<?> late(String date);
	/**
	 * 早退情况
	 * @param date
	 * @return
	 */
	public List<?> early(String date);
	/**
	 * 上午已签到
	 * @param date
	 * @return
	 */
	public List<?> morningAttendance(String date);
	/**
	 * 下午已签到
	 * @param date
	 * @return
	 */
	public List<?> signedIn(String date);
	/**
	 * 签到人员
	 * @param date
	 * @return
	 */
	public List<?> attendancePersonle();
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
	 * 按月份查询缺卡记录
	 * @param date
	 * @return
	 */
	public List<?> missingCard(String date);
	/**
	 * 按月份查询旷工记录
	 * @param date
	 * @return
	 */
	public List<?> absenteeism();

}
