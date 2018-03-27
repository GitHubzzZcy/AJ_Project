package com.henghao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.EnforcementService;

@Controller
@RequestMapping("/enforcement")
public class EnforcementAction {

	@Autowired
	private EnforcementService enforcementService;
	/**
	 * 查询执法次数
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/enforcementCount")
	@ResponseBody
	public Result enforcementCount(String name) throws Exception{
		return enforcementService.enforcementCount(name);
	}
	/**
	 * 查询每月执法次数
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/monthEnforcementCount")
	@ResponseBody
	public Result monthEnforcementCount(String name) throws Exception{
		return enforcementService.monthEnforcementCount(name);
	}
	/**
	 * 执法企业分类
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/enforcementEnterprise")
	@ResponseBody
	public Result enforcementEnterprise(String name) throws Exception{
		return enforcementService.enforcementEnterprise(name);
	}
}
