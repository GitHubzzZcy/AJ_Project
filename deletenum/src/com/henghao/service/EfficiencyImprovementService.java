package com.henghao.service;

import com.henghao.entity.Result;

public interface EfficiencyImprovementService {
	/**
	 * 人员信息
	 * @param name
	 * @return
	 */
	public Result personnelInformation(String name); 
	/**
	 * 局领导查询
	 * @param name
	 * @return
	 */
	public Result bureauLeader(); 

	/**
	 * 得分统计详情
	 * @param date
	 * @return
	 */
	public Result scoreStatistics(String name);
	/**
	 * 个人综合评分
	 * @param date
	 * @return
	 */
	public Result comprehensiveScore(String name);
	/**
	 * 加分条数及详情
	 * @param name
	 * @return
	 */
	public Result plusCountAndDetails(String name);
	/**
	 * 减分条数及详情
	 * @param name
	 * @return
	 */
	public Result reductionCountAndDetails(String name);
	/**
	 * 行驶情况
	 * @param name
	 * @return
	 */
	public Result vehicleStatistics(String name); 
	/**
	 * 事件信息
	 * @param name
	 * @return
	 */
	public Result eventInformationAndImg(String userId,String date);
	/**
	 * 领导评分
	 * @param userId
	 * @param date
	 * @return
	 */
	public Result leadershipScore(String userId);
	/**
	 * 会议派车查询
	 * @return
	 */
	public Result sendCarQuery();
}
