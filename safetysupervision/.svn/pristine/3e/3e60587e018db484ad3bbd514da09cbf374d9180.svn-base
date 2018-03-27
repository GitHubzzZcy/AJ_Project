package com.henghao.dao;

import java.util.List;

import com.henghao.entity.ComplaintHandling;

public interface ComplaintHandlingDao extends IBaseDao<ComplaintHandling> {
	/**
	 * 保存投诉办理信息
	 */
	public void addComplaintHandling();

	/**
	 * 根据id查询投诉案办理信息
	 * 
	 * @param id
	 * @return
	 */
	public List<?> complaintHandlingById(String code);

	/**
	 * 更新投诉办理信息
	 * 
	 * @param problem
	 * @param examinationName
	 * @param isTrue
	 * @param details
	 */
	public void updatecomplaintHandling(String code, String examinationResult, String examinationName,
			String examinationDescribe);

	/**
	 * 时间段查询投诉类型
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public List<?> reportType(String date1, String date2);

}
