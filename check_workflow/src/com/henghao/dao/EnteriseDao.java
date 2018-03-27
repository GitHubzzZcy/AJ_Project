package com.henghao.dao;

import java.util.Map;


import com.henghao.entity.Enterise;

public interface EnteriseDao extends IBaseDao<Enterise> {

	/**
	 * 领导去向查询，传入要查询的最新领导去向条数
	 * @param num
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> findLeaderWhere(int num) throws Exception;
	/**
	 * 查询最新诚信建设内容，传入最新的条数
	 * @param num
	 * @return
	 */
	public Map<String,Object> findCxjs(int num);
	/**
	 * 查询用户信息，返回用户id，姓名，部门，岗位
	 * @param condition
	 * @return
	 */
	public Map<String,Object> findAllUser(String condition);
	/**
	 * 按照用户名查询分析他被投诉情况统计
	 */
	public Map<String,Object> findTSnum(String startdate,String enddate,String user);
	/**
	 * 审批人员     9. 按照用户名查询他审批的工作量总数、退回总次数、拿回总次数；
	 */
	public Map<String,Object> findSPnum(String startdate,String enddate,String user);
	/**
	 * 按照用户名查询分析他的审批 事件 的同意和拒接数量
	 */
	public Map<String,Object> findSPstatusNum(String startdate,String enddate,String user);
	/**
	 * 按照用户名查询分析他的各季度审批案件数量
	 */
	public Map<String,Object> findSPNumByjidu(String startdate,String enddate,String user);
	/**
	 * 按照用户名查询审批案件时间长短的统计
	 */
	public Map<String,Object> findSPtimeSize(String startdate,String enddate,String user);
	/**
	 * 查询安监执法大队个人员
	 */
	public Map<String,Object> findZfddPeople(String name,String daduiNum);
	/**
	 * 查询某个执法大队的某个时间的执法超限 限定数目
	 */
	public int findZfcxNum(String daduiName,String date);
	
}
