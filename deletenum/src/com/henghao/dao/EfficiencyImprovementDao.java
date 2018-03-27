package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Personle;

public interface EfficiencyImprovementDao extends IBaseDao<Personle>  {
	/**
	 * 人员信息
	 * @param name
	 * @return
	 */
	public List<?> personnelInformation(String name);
	/**
	 * 局领导查询
	 * @param name
	 * @return
	 */
	public List<?> bureauLeader();
	/**
	 * 得分统计详情
	 * @param date
	 * @return
	 */
	public List<?> scoreStatistics(String name,String date);
	/**
	 * 个人综合评分
	 * @param date
	 * @return
	 */
	public List<?> comprehensiveScore(String name,String year);
	/**
	 * 加分条数
	 * @param name
	 * @return
	 */
	public List<?> plusCount(String name,String year);
	/**
	 * 加分条数详情
	 * @param name
	 * @return
	 */
	public List<?> plusCountDetails(String name,String year);
	
	/**
	 * 减分条数
	 * @param name
	 * @return
	 */
	public List<?> reductionCount(String name,String year);
	/**
	 * 减分条数详情
	 * @param name
	 * @return
	 */
	public List<?> reductionCountDetails(String name,String year);
	/**
	 * 用车情况统计 市内外次数
	 * @param name
	 * @return
	 */
	public List<?> vehicleStatisticsCount(String name,String year);
	/**
	 * 行驶类别次数  单程 往返
	 * @param name
	 * @return
	 */
	public List<?> travelCategoryCount(String name,String year);
	/**
	 * 用车总次数
	 * @param name
	 * @return
	 */
	public List<?> travelTotalCount(String name,String year);
	/**
	 * 事件信息
	 * @param name
	 * @return
	 */
	public List<?> eventInformation(String userId,String date);
	/**
	 * 事件图片
	 * @param name
	 * @return
	 */
	public List<?> eventInPicture(String token);
	/**
	 * 领导评分
	 * @param token
	 * @return
	 */
	public List<?> leadershipScore(String userId,String date);
	/**
	 * 派车查询
	 * @return
	 */
	public List<?> sendCarQuery();
}
