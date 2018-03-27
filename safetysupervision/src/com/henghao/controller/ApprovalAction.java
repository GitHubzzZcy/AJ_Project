package com.henghao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.henghao.entity.Result;
import com.henghao.service.ApprovalService;

/**
 * 
 * @author longhong
 *
 */
@Controller
@RequestMapping("/approval")
public class ApprovalAction {
	@Autowired
	private ApprovalService approvalService;
	/**
	 * 查询个人审批案件
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findMyApproval")
	public Result findMyApproval(String userId,int page,int pageSize)throws Exception{
		return approvalService.findMyApproval(userId, page, pageSize);
	}
	/**
	 * 查询所有审批
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findApprovalTotal")
	public Result findApprovalTotal() throws Exception{
		return approvalService.findApprovalTotal();
	}
	/**
	 * 查询所有审核
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findExamineTotal")
	public Result findExamineTotal() throws Exception{
		return approvalService.findExamineTotal();
	}
	/**
	 * 查询个人未办理件数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findMyApprovalNum")
	public Result findMyApprovalNum(String userId)throws Exception{
		return approvalService.findMyApprovalNum(userId);
	}
	/**
	 * 查询三重一大预警次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findMajorStatus")
	public Result findMajorStatus(String date1, String date2)throws Exception{
		return approvalService.findMajorStatus(date1, date2);
	}
}
