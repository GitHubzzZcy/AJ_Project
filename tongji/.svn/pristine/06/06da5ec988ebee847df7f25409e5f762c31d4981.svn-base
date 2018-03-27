package com.henghao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.IWZPEnterpriseService;

/**
 * 企业统计控制层<p>
 * 使用远东数据库
 * @author 王章鹏
 * @time 2017-4-13
 */
@Controller
@RequestMapping("/WZPEnterprise")
public class WZPEnterpriseController {
	
	//企业统计service接口
	@Resource
	private IWZPEnterpriseService enterpriseService;
	
	
	/**
	 * 行政执法统计(statistics enforcement)
	 * <br>统计 未检查、已检查、已执法
	 * @author 王章鹏
	 * @time 2017年4月27日09:20:36
	 */
	@RequestMapping(value="/getEnforcement",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result getEnforcement(){
		Result result = null;
		try {
			result = enterpriseService.getEnforcement();
		} catch (Exception e) {
			result = new Result(1, "系统繁忙", null);
		}
		return result;
	}
	
	
	/**
	 * 查询存在隐患的所有企业
	 * @author 王章鹏
	 * @time 2017-4-13
	 * @return {@link Result}
	 */
	@RequestMapping(value="/getAllEnterprise",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result getAllEnterprise(){
		Result result = enterpriseService.getAllInfo();
		return result;
	}

	/**
	 * 企业统计，包括共有企业数和上报企业数
	 * @author 王章鹏
	 * @time 2017-4-13
	 * @return {@link Result}
	 */
	@RequestMapping(value="/EnterpriseCount",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result EnterpriseCount(){
		return enterpriseService.getEnterpriseCount();
	}
	
	
	/**
	 * 执法综合查询<br>
	 * 查询执法业务信息、各被执法单位有关情况
	 * @author 王章鹏
	 * @time 2017-5-25 
	 * @return {@link Result}
	 */
	@RequestMapping(value="/synthesize",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result zhifaCount(String selectType){
		
		return enterpriseService.getSsynthesize(selectType);
	}
	
	/**
	 * 执法综合查询<br>
	 * 查询执法情况的统计信息
	 * 2017年6月2日12:12:14 修改：
	 * 查询某企业的被执法总数和执法时间
	 * @author 王章鹏
	 * @time 2017-5-26 
	 * @return {@link Result}
	 */
	@RequestMapping(value="/zhifaSituation",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result zhifaSituationCount(String entName){
		
		return enterpriseService.getZhifaSituatinCount(entName);
	}
	
	
	/**
	 * 执法综合查询<br>
	 * 企业被执法详情
	 * 创建时间：2017-6-2 下午2:14:36
	 * @param entName
	 * @param date
	 * @return
	 */
	@RequestMapping(value="/zhifaDetail",produces = "application/json;charset=utf-8")
	@ResponseBody
	public Result zhifaDetail(String entName,String date){
		return enterpriseService.getZhifaDetail(entName, date);
	}
}
