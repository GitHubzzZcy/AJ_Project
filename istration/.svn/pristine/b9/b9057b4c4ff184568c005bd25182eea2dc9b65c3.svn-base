package com.henghao.istration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.IApproveService;
import com.henghao.istration.util.Result;
/**
 * 审批进度查看控制层
 * @author 周承耀
 *
 */
@Controller
@RequestMapping("/approve")
public class ApproveController {

	@Autowired
	private IApproveService approveService;
	
	/**
	 * 查询各申请类别总条数
	 * @return
	 */
	@RequestMapping(value="/approveClass", produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result queryApproveClass() {
		Result result = approveService.queryApproveClass();
		return result;
	}
	/**
	 * 查询企业列表
	 * @param flowname
	 * @return
	 */
	@RequestMapping(value="/queryfirmname", produces="application/json; charset=UTF-8 ")
	@ResponseBody
	public Result queryfirmname(String flowid) {
		Result result = approveService.queryfirmname(flowid);
		return result;
	}
	/**
	 * 查询所有企业列表数据
	 */
	@RequestMapping(value="/queryfirmAll", produces="application/json; charset=UTF-8 ")
	@ResponseBody
	public Result queryfirmAll() {
		Result result = approveService.queryfirmAll();
		return result;
	}
	
	/**
	 * 查询审批详情
	 * @param applyid
	 * @return
	 */
	@RequestMapping(value="/findByApplyid", produces="application/json; charset=UTF-8 ")
	@ResponseBody
	public Result findByApplyid(String applyid) {
		Result result = approveService.findByApplyid(applyid);
		return result;
	}
	/**
	 * 承诺见超限统计，承诺见提前办结统计
	 * 2017-06-05
	 */
	@RequestMapping(value="/promise", produces="application/json; charset=UTF-8 ")
	@ResponseBody
	public Result promise() {
		Result result  = approveService.promise();
		return result;
	}
	
}
