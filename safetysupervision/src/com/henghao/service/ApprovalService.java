package com.henghao.service;

import com.henghao.entity.Result;

public interface ApprovalService {

	/**
	 * 查询个人审批信息
	 * @param userId
	 * @return
	 */
	public Result findMyApproval(String userId,int page,int pageSize);
	/**
	 * 所有审批
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Result findApprovalTotal();
	/**
	 * 所有审核
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Result findExamineTotal();
	/**
	 * 查询个人未办理件数
	 * @param userId
	 * @return
	 */
	public Result findMyApprovalNum(String userId);
	/**
	 * 三重一大警告判断
	 * @param date1
	 * @param date2
	 * @return
	 */
	public Result findMajorStatus(String date1,String date2);
	
}
