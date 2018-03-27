package com.henghao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.EnterpriceService;

/**
 * @author Nwl
 *信息统计
 */
@Controller
@RequestMapping("/Enterise")
public class EnterpriseController {
	@Resource
	private EnterpriceService serivce;
	//领导去向查询，传入要查询的最新领导去向条数，不传参数默认3条
	@RequestMapping("/findLeaderWhere")
	@ResponseBody
	public Result findLeaderWhere(String num){
		Result result=serivce.findLeaderWhere(num);		
		return result;
	}

	//查询最新诚信建设内容，传入最新的条数，不传参数默认1条
	@RequestMapping("/findCxjs")
	@ResponseBody
	public Result findCxjs(String num){
		Result result=serivce.findCxjs(num);		
		return result;
	}

	//查询所有用户信息，返回用户id，姓名，部门，岗位
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findAllUser")
	@ResponseBody
	public Result findAllUser(String uname){
		Result result=serivce.findAllUser(uname);		
		return result;
	}	
	
	
	// 按照用户名查询分析他被投诉情况统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findTSnum")
	@ResponseBody
	public Result findTSnum(String date,String user){
		Result result=serivce.findTSnum(date,user);
		return result;
	}
	//按照用户名查询他审批的工作量总数、退回总次数、拿回总次数；
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findSPnum")
	@ResponseBody
	public Result findSPnum(String date,String user){
		Result result=serivce.findSPnum(date,user);
		return result;
	}
	// 按照用户名查询分析他的审批事件的同意和拒接数量
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findSPstatusNum")
	@ResponseBody
	public Result findSPstatusNum(String date,String user){
		Result result=serivce.findSPstatusNum(date,user);
		return result;
	}
	// 按照用户名查询分析他的各季度审批案件数量
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findSPNumByjidu")
	@ResponseBody
	public Result findSPNumByjidu(String date,String user){
		Result result=serivce.findSPNumByjidu(date,user);
		return result;
	}
	// 按照用户名查询审批案件时间长短的统计
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findSPtimeSize")
	@ResponseBody
	public Result findSPtimeSize(String date,String user){
		Result result=serivce.findSPtimeSize(date,user);
		return result;
	}
	
	//查询安监执法大队个人员
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findZfddPeople")
	@ResponseBody
	public Result findZfddPeople(String name, String daduiNum) {
		Result result=serivce.findZfddPeople(name,daduiNum);
		return result;//2017-5-17下午1:46:37宁万龙
	}
	
	//查询某个执法大队的某个时间的执法超限 限定数目
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findZfcxNum")
	@ResponseBody
	public Result findZfcxNum(String daduiName, String date) {
		Result result=serivce.findZfcxNum(daduiName,date);
		return result;//2017-5-17下午1:46:37宁万龙
	}
	
	//查询指定时间段指定用户的位置偏离情况，平均偏离距离。
	@RequestMapping(produces="application/json;charset=UTF-8",value="/findPositionByDate")
	@ResponseBody
	public Result findPositionByDate(String uname,String startDate,String endDate,double lat, double lng ){
		Result result=serivce.findPositionByDate(uname, startDate, endDate,lat,lat);
		return result;
	}
	
	
}
