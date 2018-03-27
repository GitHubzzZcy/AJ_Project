package com.henghao.news.controller;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.henghao.news.entity.JPushToUser;
import com.henghao.news.entity.MeetingEntity;
import com.henghao.news.entity.MeetingUploadEntity;
import com.henghao.news.service.JPushService;
import com.henghao.news.service.MeetingUploadService;
import com.henghao.news.util.Result;
/**
 * 
 * @Description:会议管理控制层
 * @author zcy  
 * @date 2017年10月24日 下午2:59:43
 * @version 1.0
 */
@Controller
@RequestMapping("/JPush")
public class JPushController {

	@Resource
	private JPushService jPushService;
	@Resource
	private MeetingUploadService meetingUploadService;
	//private static final Logger LOGGER = LoggerFactory.getLogger(JPushController.class);
	@RequestMapping(value = "/addMeetingEntity", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result addMeetingEntity(String json) {
		try {
			MeetingEntity gson = JSON.parseObject(json, MeetingEntity.class);
			Object obj = jPushService.addMeetingEntity(gson);
			return new Result(0, "保存成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
			//LOGGER.error("新增会议出错！gson = {}",json);
		}
		return new Result(1, "保存失败", null);
	}
	
	/**
	 * 根据消息的msg_id查询消息详情信息
	 * @param msg_id
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/queryMeetingEntityByMsgid", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result queryMeetingEntityByMsgid(Long msg_id, String uid) {
		try {
			MeetingEntity obj = jPushService.queryMeetingEntityByMsgid(msg_id, uid);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 根据用户id查询所有推送消息
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/queryMeetingEntityByUserIdAll", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryMeetingEntityByUserIdAll(String uid) {
		try {
			Object obj = jPushService.queryMeetingEntityByUserIdAll(uid);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 领导审批提交会议申请，修改MeetingEntity状态
	 * @param mid
	 * @param whetherPass //审批结果状态 1：通过，2：不通过
	 * @param noPassReason //不通过理由
	 * @return
	 */
	@RequestMapping(value="/updateMeetingEntityWhetherPass", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result updateMeetingEntityWhetherPass(int mid, int whetherPass, 
			@RequestParam(value="noPassReason",required=false)String noPassReason) {
		try {
			jPushService.updateMeetingEntityWhetherPass(mid, whetherPass,noPassReason);
			return new Result(0, "审批修改状态成功", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "操作失败", null);
	}
	/**
	 * 消息查看后修改数据标记为已查看
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="/updateJPushToUserIsRead", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result updateJPushToUserIsRead(int cid) {
		try {
			jPushService.updateJPushToUserIsRead(cid);
			return new Result(0, "修改为已查看状态成功", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "修改为已查看状态失败", null);
	}
	/**
	 * 根据用户id查询未查看过的消息列表
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/queryJPushToUserUnRead", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryJPushToUserUnRead(String uid) {
		try {
			List<JPushToUser> list = jPushService.queryJPushToUserUnRead(uid);
			return new Result(0, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	
	/**
	 * 根据用户查询发起并审批通过的会议列表
	 * @param uid
	 * @return
	 */
	@RequestMapping(value="/queryMeetingEntityByUserList", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result queryMeetingEntityByUserList(String uid) {
		try {
			List<MeetingEntity> list = jPushService.queryMeetingEntityByUserList(uid);
			return new Result(0, "查询成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "查询失败", null);
	}
	/**
	 * 会议发起人将会议标记为结束
	 */
	@RequestMapping(value="/updateMeetingEntityByIsEnd", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Result updateMeetingEntityByIsEnd(int mid) {
		try {
			jPushService.updateMeetingEntityByIsEnd(mid);
			return new Result(0, "操作成功", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(1, "操作失败", null);
	}
	/**
	 * 会议上传新增实体
	 * @param files
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/addMeetingUploadEntity", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result addMeetingUploadEntity(@RequestParam(required = false) MultipartFile[]  files, String json) {
		try {
			MeetingUploadEntity gson = JSON.parseObject(json, MeetingUploadEntity.class);
			Object obj = meetingUploadService.addMeetingUploadEntity(gson, files);
			return new Result(0, "保存成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
			//LOGGER.error("新增会议出错！gson = {}",json);
		}
		return new Result(1, "保存失败", null);
	}
	/**
	 * 查询上传的会议实体列表
	 * @param files
	 * @param json
	 * @return
	 */
	@RequestMapping(value = "/queryMeetingUploadEntityList", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public  Result queryMeetingUploadEntityList(@RequestParam(defaultValue="1",required=false)int page, @RequestParam(defaultValue="10000", required=false)int size) {
		try {
			Object obj = meetingUploadService.queryMeetingUploadEntityList(page, size);
			return new Result(0, "查询成功", obj);
		} catch (Exception e) {
			e.printStackTrace();
			//LOGGER.error("查询会议出错！");
		}
		return new Result(1, "查询失败", null);
	}
	
	
}
