package com.henghao.news.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.henghao.news.service.MeetingTrajectoryService;
import com.henghao.news.util.Result;

/**
 * @Description:会议轨迹控制层
 * @author zcy  
 * @date 2017年10月26日 下午3:29:07
 * @version 1.0
 */
@Controller
@RequestMapping("/meetingTrajectory")
public class MeetingTrajectoryController {

	@Resource
	private MeetingTrajectoryService meetingTrajectoryService;
	
	/**
	 * 开会人员选择开会代替人，更新会议签到实体
	 * @param mid
	 * @param supersededId 被代替人id
	 * @param fungibleId 代替人id
	 * @return
	 */
	@RequestMapping(value = "/updateMeetingTrajectoryEntity", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result updateMeetingTrajectoryEntity(int mid, String supersededId, String fungibleId) {
		try {
			Object obj = meetingTrajectoryService.updateMeetingTrajectoryEntity(mid, supersededId, fungibleId);
			return new Result(0, "操作成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "操作失败", null);
	}
	/**
	 * 会议开始签到
	 * @param mid
	 * @param userId 签到人的id
	 * @param startSignInCoordinates 签到经纬度
	 * @return
	 */
	@RequestMapping(value = "/updateMteStartSignInCoordinates", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result updateMteStartSignInCoordinates(int mid, String userId, String startSignInCoordinates) {
		try {
			Object obj = meetingTrajectoryService.updateMteStartSignInCoordinates(mid, userId, startSignInCoordinates);
			return new Result(0, "操作成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "操作失败", null);
	}
	/**
	 * 会议退场签到
	 * @param mid
	 * @param userId
	 * @param endSignInCoordinates  退场经纬度
	 * @return
	 */
	
	@RequestMapping(value = "/updateMteEndSignInCoordinates", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result updateMteEndSignInCoordinates(int mid, String userId, String endSignInCoordinates) {
		try {
			Object obj = meetingTrajectoryService.updateMteEndSignInCoordinates(mid, userId, endSignInCoordinates);
			return new Result(0, "操作成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "操作失败", null);
	}
	/**
	 * 会议纪要，会议图片上传
	 * @param mid
	 * @param userId
	 * @param meetingSummary
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/updateMeetingImagePath", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result updateMeetingImagePath(int mid, String userId, String meetingSummary, @RequestParam(required = false) MultipartFile[]  files) {
		try {
			Object obj = meetingTrajectoryService.updateMeetingImagePath(mid, userId, meetingSummary, files);
			return new Result(0, "操作成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "操作失败", null);
	}
	/**
	 * 根据mid和签到人员id来查询签到信息
	 * @param mid
	 * @param shouldAttendMetting
	 * @return
	 */
	@RequestMapping(value = "/queryMetByMidAndUserIdList", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result queryMetByMidAndUserId(int mid, String userId) {
		try {
			Object obj = meetingTrajectoryService.queryMetByMidAndUserId(mid, userId);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 查询签到列表
	 * @param mid
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryMeetingTrajectoryEntityByUserIdList", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result queryMeetingTrajectoryEntityByUserIdList(String userId) {
		try {
			Object obj = meetingTrajectoryService.queryMeetingTrajectoryEntityByUserIdList(userId);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
}
