package com.henghao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.AttendanceDetailsService;

/**
 * 规定日期考勤统计
 * @author 龙宏
 *
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceDetailsAction {
	@Autowired
	private AttendanceDetailsService attendanceDetailsService;
	
	/**
	 * 规定日期迟到情况
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/leave")
	public Result leave(String date1,String date2)throws Exception{
		return attendanceDetailsService.leave(date1, date2);
	}

	/**
	 * 规定日期早退情况
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/early")
	public Result early(String date1,String date2) throws Exception {
		return attendanceDetailsService.early(date1, date2);
	}
	
	/**
	 * 查询本月迟到记录
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/monthLate")
	public Result monthLate(String date1,String date2)throws Exception{
		String date = date1.substring(0,6);
		return attendanceDetailsService.monthLate(date);
	}
	/**
	 * 查询本年迟到记录
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/yearLate")
	public Result yearLate(String date1,String date2)throws Exception{
		String date = date1.substring(0,4);
		return attendanceDetailsService.monthLate(date);
	}
	
	/**
	 *查询本月早退记录
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/monthEarly")
	public Result monthEarly(String date1,String date2) throws Exception {
		String date = date1.substring(0,6);
		return attendanceDetailsService.monthEarly(date);
	}
	/**
	 *查询当年早退记录
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/yearEarly")
	public Result yearEarly(String date1,String date2) throws Exception {
		String date = date1.substring(0,6);
		return attendanceDetailsService.monthEarly(date);
	}
	/**
	 * 按时间段查询无故外出 周 年 月 时间段 查询部门无故外出次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */@ResponseBody
	 @RequestMapping("/withoutReason")
	public Result withoutReason(String date1, String date2){
		 
		 return attendanceDetailsService.withoutReason(date1, date2);
	}
	 /**
	  * 按时间段 年月周 查询迟到早退人数
	  * @param date1
	  * @param date2
	  * @return
	  * @throws Exception
	  */
	 @ResponseBody
	 @RequestMapping("/findLateAndearlyNum")
	 public Result findLateAndearlyNum(String date1, String date2){
		 return attendanceDetailsService.findLateAndearlyNum(date1, date2);
	 }
	 /**
	  * 按时间段 年月周 查询迟到无故外出人数
	  * @param date1
	  * @param date2
	  * @return
	  * @throws Exception
	  */
	 @ResponseBody
	 @RequestMapping("/findwithoutReasonPersonleNum")
	 public Result findwithoutReasonPersonleNum(String date1, String date2)throws Exception{
		 return attendanceDetailsService.findwithoutReasonPersonleNum(date1, date2);
	 }
}
