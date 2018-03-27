package com.henghao.news.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.EfficiencyCarDataService;
import com.henghao.news.util.Result;

/**
 * @Description: 效能页面，重写龙宏代码
 * @author zcy  
 * @date 2017年12月4日 下午2:27:00
 * @version 1.0
 */
@Controller
@RequestMapping("/efficiencyData")
public class EfficiencyCarDataController {

	@Resource
	private EfficiencyCarDataService efficiencyCarDataService;
	//用车异常次数
	@RequestMapping(value = "/queryCarBookException", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryCarBookException(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = efficiencyCarDataService.queryCarBookException(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	//queryCarDeparture
	@RequestMapping(value = "/queryCarBookUserException", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryCarBookUserException(String userId, @RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = efficiencyCarDataService.queryCarBookException(userId, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	@RequestMapping(value = "/queryCarDeparture", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryCarDeparture(String userId, String start, String end) {
		try {
			Object obj = efficiencyCarDataService.queryCarDeparture(userId, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
}
