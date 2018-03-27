package com.henghao.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.henghao.entity.Result;
import com.henghao.service.IUserEventTrackService;

/**
 * 重写接口
 * @Description: 个人轨迹，个人事件
 * @author zcy  
 * @date 2017年11月16日 下午4:05:27
 * @version 1.0
 */
@Controller
@RequestMapping("/userEventTrack")
public class UserEventTrackController {
	
	@Resource
	private IUserEventTrackService userEventTrackService;
	/**
	 * 周承耀11月16号重写/deletenum/event/addEventInformationAndImage接口
	 * @param userId
	 * @param eventName
	 * @param eventAddress
	 * @param eventTime
	 * @param eventDate
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/addUetEntity", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result addEntity(String userId,String eventName,
							String eventAddress,String eventTime, String eventEndTime, String eventDate,
							@RequestParam(value = "files", required = false) MultipartFile[] files) {
		try {
			if("".equals(eventAddress) || null == eventAddress) {
				return new Result(1, "未获取到地址，添加失败！", null);
			}
			Object obj = userEventTrackService.addEntity(userId, eventName, eventAddress, eventTime, eventEndTime, eventDate, files);
			return new Result(0, "添加成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "添加失败", null);
	}
	/**
	 * 根据用户和日期查询
	 * @param userId
	 * @param eventDate
	 * @return
	 */
	@RequestMapping(value = "/queryByUserIdAndDate", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryByUserIdAndDate(String userId,String eventDate) {
		try {
			Object obj = userEventTrackService.queryByUserIdAndDate(userId, eventDate);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
}
