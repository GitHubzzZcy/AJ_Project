package com.henghao.news.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.news.service.MeetingStatisticsService;
import com.henghao.news.util.Result;

/**
 * @Description:融合平台会议管理页面统计数据
 * @author zcy  
 * @date 2017年11月14日 上午10:37:14
 * @version 1.0
 */
@RequestMapping("/meetingStatistics")
@Controller
public class MeetingStatisticsController {
	
	@Resource
	private MeetingStatisticsService meetingStatisticsService;

	/**
	 * 会议总数，会议类型统计
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryMeetingTypeCount", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryMeetingTypeCount(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = meetingStatisticsService.queryMeetingTypeCount(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 各部门会议统计
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryDeptMeetingCount", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryDeptMeetingCount(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = meetingStatisticsService.queryDeptMeetingCount(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 会议时长统计
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryMeetingTimeCount", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryMeetingTimeCount(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = meetingStatisticsService.queryMeetingTimeCount(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 会议异常统计
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryExceptionMeetingCount", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryExceptionMeetingCount(@RequestParam(required=false) String start, @RequestParam(required=false) String end) {
		try {
			Object obj = meetingStatisticsService.queryExceptionMeetingCount(start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 会议总数列表查询
	 * @param page
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryMeetingCountList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryMeetingCountList(@RequestParam(defaultValue = "1")int page, 
										@RequestParam(defaultValue = "10")int size,
										@RequestParam(required=false) String start, 
										@RequestParam(required=false) String end) {
		try {
			Object obj = meetingStatisticsService.queryMeetingCountList(page, size, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 参会异常数据列表
	 * @param page
	 * @param size
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping(value = "/queryExceptionMeetingCountList", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryExceptionMeetingCountList(@RequestParam(defaultValue = "1")int page, 
										@RequestParam(defaultValue = "10")int size,
										@RequestParam(required=false) String start, 
										@RequestParam(required=false) String end) {
		try {
			Object obj = meetingStatisticsService.queryExceptionMeetingCountList(page, size, start, end);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
}
