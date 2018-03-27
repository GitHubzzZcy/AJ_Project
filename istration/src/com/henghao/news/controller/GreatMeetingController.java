package com.henghao.news.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.entity.GreatMeeting;
import com.henghao.news.service.GreatMeetingService;
import com.henghao.news.service.RedisService;
import com.henghao.news.util.Result;
/**
 * 三重一大
 * @author 周
 *
 */

@Controller
@RequestMapping("/greatmeeting")
public class GreatMeetingController {

	@Autowired
	private GreatMeetingService greatMeetingService;
	@Resource
	private RedisService redisService;
	/**
	 * 按时间段统计查询各类别三重一大项
	 * @param start开始时间 2017-09
	 * @return
	 */
	@RequestMapping(value = "/querytypecount", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryGreatTypeByDate(@RequestParam(required = false)String start, @RequestParam(required = false)String end) {
		try {
			String value = redisService.get("querytypecount");
			if(value != null) {
				return new Result(0,"缓存命中成功", value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			List<Object> list = greatMeetingService.queryTypeCount(start, end);
			return new Result(0,"查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
	/**
	 * 查詢三重一大列表
	 * @param type
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/querytypelist", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryGreatListByType(@RequestParam(required = false)String type, @RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size) {
		try {
			Map<String, Object> map = greatMeetingService.queryGreatListByType(type, page, size);
			return new Result(0,"查询成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryById", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryById(String id) {
		try {
			GreatMeeting gm = greatMeetingService.queryById(id);
			return new Result(0,"查询成功", gm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1,"查询失败", null);
	}
}
