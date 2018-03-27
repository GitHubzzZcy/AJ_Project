package com.henghao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Rest_day;
import com.henghao.entity.Result;
import com.henghao.service.Rest_dayService;
import com.henghao.util.PageBaen;
/**
 * 节假日controller
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-5 下午4:50:57  
 * @版本：V1.0
 */
@Controller
@RequestMapping(value="restday")
public class Rest_dayController {
	
	@Resource
	private Rest_dayService restServices;
	// 更新、设置自定义节假日
	@RequestMapping(value="/updateRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result updateRestDay(Rest_day restDay){
		Result result=restServices.updateRestDay(restDay);
		return result;
	}
	
	// 添加一个假日
	@RequestMapping(value="/addRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result addRestDay(Rest_day restDay){
		Result result=restServices.addRestDay(restDay);
		return result;
	}
	
	// 删除某个节假日
	@RequestMapping(value="/deleteRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result deleteRestDay(Rest_day restDay){
		Result result=restServices.deleteRestDay(restDay);
		return result;
	}
	
	// 查询节假日
	@RequestMapping(value="/findRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Rest_day findRestDay(int id){
		Rest_day result=restServices.findRestDay(id);
		return result;
	}
	
	// 查询全部节假日
	@RequestMapping(value="/findAllRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result findAllRestDay(String startDate,String endDate,PageBaen pageBaen){
		Result result=restServices.findAllRestDay(startDate,endDate,pageBaen);
		return result;
	}
	
	// 自动更新节假日时间
	@RequestMapping(value="/autoUpdateRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result autoUpdateRestDay(int year){
		Result result=restServices.autoUpdateRestDay(year);
		return result;
	}
	//手动从网上自动更新某年节假日库
	@RequestMapping(value="/addOrUpdateRestDay",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result addOrUpdateRestDay(String restDayjson,int year){
		Result result=restServices.addOrUpdateRestDay(restDayjson,year);
		return result;		  
	}

}
