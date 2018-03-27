package com.henghao.dao;

import java.util.Map;

import com.henghao.entity.WorkTime;

public interface IWorkTimeDao extends IBaseDao<WorkTime>{
	/**
	 * 设置考勤时间段
	 */
	public Map<String, Object> setWorkTime(Integer deptNo,String starTime,String endTime);
	/**
	 * 查询应该用户的当前考勤时间段
	 * 参数：userid
	 * @throws Exception 
	 */
	public WorkTime findWorktime(String userid) throws Exception;
}
