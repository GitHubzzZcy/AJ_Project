package com.henghao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.IWorkTimeService;
/**
 * 考勤时间段controller
 * @author NWL
 *
 */
@Controller
@RequestMapping("/WT")
public class WorkTimeController {
	@Resource
	private IWorkTimeService iwservice;
	//设置考勤时间段
	@RequestMapping("/setWorkTime")
	@ResponseBody
	public Result setWorkTime(Integer deptNo,String starTime,String endTime){
		Result result =iwservice.setWorkTime(deptNo,starTime,endTime);
		
		return result;
	}
}
