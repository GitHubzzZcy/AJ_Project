package com.henghao.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.henghao.entity.Result;
import com.henghao.service.ICheckingService;
/**
 * 考勤信息controller
 * @author NWL
 *
 */
@Controller
@RequestMapping(value="/checking")
public class CheckingController {

	@Resource
	private ICheckingService service;
	

	// 查询指定一个月内的旷工记录
	@RequestMapping(value="/findKgListByUserId")
	@ResponseBody
	public Result findKgListByUserId(String date1,String date2){
		//获取月份的第一天和最后一天,时间格式如2011-01
		String startDate=date1;
		String endDate=date2;
		Result result = service.findKgListByUserId(startDate, endDate);
		return result;
	}
	/**
	 * 按时间段查询旷工人数
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/findAbsenteeismNum")
	public Result findAbsenteeismNum(String date1, String date2)throws Exception{
		String startDate=date1;
		String endDate=date2;
		return service.findAbsenteeismNum(startDate, endDate);
	}

	
}










