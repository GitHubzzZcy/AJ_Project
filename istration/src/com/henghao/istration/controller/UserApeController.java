package com.henghao.istration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.istration.service.IUserApeService;
import com.henghao.istration.util.Result;

/**
 * 个人行政审批页面数据
 * @author admin
 *
 */
@Controller
@RequestMapping("/userapprove")
public class UserApeController {

	@Autowired
	private IUserApeService userApeService;
	
	//月日办件数统计
	@RequestMapping(value = "/queryApproveCount",method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result queryApprove(String name) {
		Result result = userApeService.queryApprove(name);
		return result;
	}
	//超期、临期、代办、已办
	@RequestMapping(value= "/queryBacklog", method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result queryBacklog(String name){
		Result result = userApeService.queryBacklog(name);
		return result;
	}
	/**
	 * 查询承诺办件
	 * @param name
	 * @return
	 */
	@RequestMapping(value="/queryPromise",method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result queryPromise(String name){
		Result result = userApeService.queryPromise(name);
		return result;
	}
	
	/**
	 * 个人人事任免
	 */
	@RequestMapping(value="/personnel",method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result personnel(String name){
		Result result = userApeService.personnel(name);
		return result;
	}
	/**
	 * 查询流程启动办理时间滚动展示
	 */
	@RequestMapping(value="/flowRoll",method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result flowRoll(String name){
		Result result = userApeService.flowRoll(name);
		return result;
	}
	/**
	 * 年度统计
	 */
	@RequestMapping(value="/yearCount",method=RequestMethod.POST, produces="application/json; charset=UTF-8")
	@ResponseBody
	public Result yearCount(String name){
		Result result = userApeService.yearCount(name);
		return result;
	}
	
	
}
