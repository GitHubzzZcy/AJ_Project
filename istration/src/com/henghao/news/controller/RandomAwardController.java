package com.henghao.news.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.RandomAwardService;
import com.henghao.news.util.Result;

@Controller
@RequestMapping("/randomAward")
public class RandomAwardController {

	/**
	 * 查询所有
	 */
	@Resource
	private RandomAwardService randomAwardService;
	
	@RequestMapping(value = "/queryUserAward", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryUserAward() {
		List<?> list;
		try {
			list = randomAwardService.queryUserAward();
			return new Result(0, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	/**
	 * 更新奖项等级
	 */
	@RequestMapping(value = "/updateAwardGrade", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result updateAwardGrade(String name, int grade) {
		try {
			Map<String, Object>  map = randomAwardService.updateAwardGrade(name, grade);
			return new Result(0, "更新成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Result(1, "更新失败", null);
	}
}
