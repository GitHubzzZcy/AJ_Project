package com.henghao.istration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.IPerformanceService;
import com.henghao.istration.util.Result;
/**
 * 个人绩效分析
 * @author 周承耀
 *
 */
@RequestMapping("/performance")
@Controller
public class PerformanceContrller {
	@Autowired
	private IPerformanceService performanceService;

	
	/**
	 * 查询用户列表
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/findByUser", produces="application/json; charset=UTF-8",method= RequestMethod.GET)
	@ResponseBody
	public Result queryDeptUser() {
		Result result = performanceService.queryDeptUser();
		return result;
	}
	/**
	 * 查询绩效
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/data", produces="application/json; charset=UTF-8",method= RequestMethod.GET)
	@ResponseBody
	public Result performance(String name) {
		Result result = performanceService.userPerformance(name);
		return result;
	}
}
