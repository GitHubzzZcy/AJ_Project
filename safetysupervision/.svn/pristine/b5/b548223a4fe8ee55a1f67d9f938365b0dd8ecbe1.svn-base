package com.henghao.dao;

import java.util.List;
import java.util.Map;

import com.henghao.entity.Checking;
/**
 * 考勤dao接口
 * @author NWL
 *
 */

public interface ICheckingDao extends IBaseDao<Checking>{
	/**
	 * 查询指定用户某个时间段的考勤信息
	 */
	public Map<String,Object> findAllByUserName(String startDate,String endDate,String userId);
	
	/**
	 * 查询指定时间段内的旷工记录
	 */
	public int findKgListByUserId(String userId,String startDate,String endDate);
	/**
	 * 查询组名
	 * @return
	 */
	public List<Object> findGroup();
	/**
	 * 查询组员
	 * @param groupName
	 * @return
	 */
	public List<Object> findGroupPersonle(String groupName);
	
	
}
