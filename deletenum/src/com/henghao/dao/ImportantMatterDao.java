package com.henghao.dao;

import java.util.List;

import com.henghao.entity.Personle;

public interface ImportantMatterDao extends IBaseDao<Personle>  {
	/**
	 *  重大项目月安排
	 * @param date
	 * @return
	 */
	public List<?> importantMatterMonth(String date);
	/**
	 * 重大项目年安排
	 * @param date
	 * @return
	 */
	public List<?> importantMatterYear(String date);
	/**
	 * 重大项目分布情况
	 * @param date
	 * @return
	 */
	public List<?> importantMatterDistribution(String deptName);
	/**
	 * 重大事项积累事件数
	 * @param date
	 * @return
	 */
	public List<?> importantMatterNumber();
}
