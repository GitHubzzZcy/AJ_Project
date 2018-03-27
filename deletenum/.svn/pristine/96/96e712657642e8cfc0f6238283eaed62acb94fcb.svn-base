package com.henghao.dao;
import java.util.List;
import com.henghao.entity.Personle;

public interface IntegrityManagementDao extends IBaseDao<Personle>  {
	/**
	 * 得分统计详情
	 * @param date
	 * @return
	 */
	public List<?> scoreStatistics(String name);
	/**
	 * 个人综合评分
	 * @param date
	 * @return
	 */
	public List<?> comprehensiveScore(String name);
	/**
	 * 加分条数
	 * @param name
	 * @return
	 */
	public List<?> plusCount(String name);
	/**
	 * 加分条数详情
	 * @param name
	 * @return
	 */
	public List<?> plusCountDetails(String name);
	/**
	 * 录入选项
	 * @param name
	 * @return
	 */
	public List<?> option();
	/**
	 * 减分条数
	 * @param name
	 * @return
	 */
	public List<?> reductionCount(String name);
	/**
	 * 减分条数详情
	 * @param name
	 * @return
	 */
	public List<?> reductionCountDetails(String name);
	/**
	 * 减分条数详情
	 * @param name
	 * @return
	 */
	public List<?> quarterScore(String name,String date1,String date2);
}
