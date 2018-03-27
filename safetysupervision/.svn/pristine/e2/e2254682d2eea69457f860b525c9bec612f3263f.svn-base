package com.henghao.dao;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public interface EfficiencyImprovementDao extends IBaseDao<T>  {
	/**
	 * 人员信息
	 * @param name
	 * @return
	 */
	public List<?> personleSex();
	/**
	 * 部门查询
	 * @param name
	 * @return
	 */
	public List<?> findDept();
	/**
	 * 部门人员人数
	 * @param date
	 * @return
	 */
	public List<?> deptPersonleNum();
	/**
	 * 按时间段查询出差
	 * @param date
	 * @return
	 */
	public String travelNum(String date1,String date2);
	/**
	 * 按时间段查询请假
	 * @param name
	 * @return
	 */
	public String leaveNum(String date1,String date2);
	/**
	 * 按时间段查询休假
	 * @param name
	 * @return
	 */
	public String vacationNum(String date1,String date2);
	
	/**
	 * 查询年龄
	 * @param name
	 * @return
	 */
	public List<String> ageAll();
}
