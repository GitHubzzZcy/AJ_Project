package com.henghao.service;

import com.henghao.entity.Result;

public interface EnterpriceService {
	/**
	 * 领导去向查询，传入要查询的最新领导去向条数
	 * @param num
	 */
	public Result findLeaderWhere(String num);
	/**
	 * 查询最新诚信建设内容，传入最新的条数
	 * @param num
	 */
	public Result findCxjs(String num);
	/**
	 * 查询用户信息，返回用户id，姓名，部门，岗位
	 * @param condition
	 * @return
	 */
	public Result findAllUser(String condition);
	/**
	 * 按照用户名查询分析他被投诉情况统计
	 */
	public Result findTSnum(String date,String user);
	/**
	 * 审批人员     9. 按照用户名查询他审批的工作量总数、退回总次数、拿回总次数；
	 */
	public Result findSPnum(String date,String user);
	/**
	 * 按照用户名查询分析他的审批事件的同意和拒接数量
	 */
	public Result findSPstatusNum(String date,String user);
	/**
	 * 按照用户名查询分析他的各季度审批案件数量
	 */
	public Result findSPNumByjidu(String date,String user);
	/**
	 * 按照用户名查询审批案件时间长短的统计
	 */
	public Result findSPtimeSize(String date,String user);
	/**
	 * 查询安监执法大队个人员
	 */
	public Result findZfddPeople(String name, String daduiNum);
	/**
	 * 查询某个执法大队的某个时间的执法超限 限定数目
	 */
	public Result findZfcxNum(String daduiName,String date);
	/**
	 * 查询指定时间段指定用户的位置偏离情况，平均偏离距离。
	 */
	public Result findPositionByDate(String uname,String sdate,String edate
			,double lat, double lng);

}
