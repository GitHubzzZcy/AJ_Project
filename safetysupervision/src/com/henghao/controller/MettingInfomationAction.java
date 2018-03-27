package com.henghao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henghao.entity.Result;
import com.henghao.service.MeetingInfomationService;

/**
 * 会议信息
 * @author longhong
 *
 */
@Controller
@RequestMapping("/meetingInfo")
public class MettingInfomationAction {
	@Autowired
	private MeetingInfomationService meetingInfomationService;
	/**
	 * 按时间段年月周 查询未签到 未参加会议次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/meetSignInfo")
	public Result meetSignInfo(String date1, String date2)throws Exception{
		return meetingInfomationService.meetSignInfo(date1, date2);
	}
	/**
	 * 按时间段年月周 查询未上传会议记录次数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/notUploadMeetMinutes")
	public Result notUploadMeetMinutes(String date1, String date2) throws Exception{
		return meetingInfomationService.notUploadMeetMinutes(date1, date2);
	}
	/**
	 * 按时间段年月周 查询会议类型
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/meetingType")
	public Result meetingType(String date1, String date2) throws Exception{
		return meetingInfomationService.meetingType(date1, date2);
	}
	/**
	 * 按时间段年月周 查询会议时长
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/meetingLongTime")
	public Result meetingLongTime(String date1, String date2)throws Exception{
		return meetingInfomationService.meetingLongTime(date1, date2);
	}
	/**
	 * 按时间段年月周 查询部门会议数
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deptMeetingStatistics")
	public Result deptMeetingStatistics(String date1, String date2)throws Exception{
		return meetingInfomationService.deptMeetingStatistics(date1, date2);
	}

}
