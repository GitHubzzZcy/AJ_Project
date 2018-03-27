package com.henghao.news.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.henghao.news.dao.MeetingTrajectoryDao;
import com.henghao.news.entity.JPushToUser;
import com.henghao.news.entity.MeetingEntity;
import com.henghao.news.entity.MeetingTrajectoryEntity;
import com.henghao.news.entity.PersonnelEntity;
import com.henghao.news.util.DateUtils;
import com.henghao.news.util.MessagePush;

/**
 * @Description:会议签到实体业务
 * @author zcy  
 * @date 2017年10月26日 下午4:00:19
 * @version 1.0
 */
@Service
public class MeetingTrajectoryService {

	@Resource
	private MeetingTrajectoryDao meetingTrajectoryDao;
	@Resource
	private FirmdataService firmdataService;
	@Resource
	private JPushService jPushService;
	@Resource
	private JPushToUserService jPushToUserService;
	@Value("${MEETINGIMAGE}")
	private String MEETINGIMAGE;//图片地址在filepath.properties中
	
	//开会人员选择开会代替人，更新会议签到实体
	public Object updateMeetingTrajectoryEntity(int mid, String supersededId, String fungibleId) {
		List<String> dbUserIds = new ArrayList<String>();
		List<MeetingTrajectoryEntity> dblist = meetingTrajectoryDao.queryByMid(mid);
		for (MeetingTrajectoryEntity meetingTrajectoryEntity : dblist) {
			if(meetingTrajectoryEntity.getShouldAttendMetting() != null) {
				dbUserIds.add(meetingTrajectoryEntity.getShouldAttendMetting());
			}
			if(meetingTrajectoryEntity.getSubstitute() != null) {
				dbUserIds.add(meetingTrajectoryEntity.getSubstitute());
			}
		}
		if(dbUserIds.contains(fungibleId)) {
			return "你选择的人是参会人员或是已经被别人先选择了，请重新选择！";
		}
		MeetingTrajectoryEntity dbMeetingTrajectoryEntity = meetingTrajectoryDao.queryByMidAndShouldAttendMetting(mid, supersededId);
		dbMeetingTrajectoryEntity.setSubstitute(fungibleId);
		
		
		MeetingEntity queryByMid = jPushService.queryByMid(mid);
		String jsonData = JSONObject.toJSONString(queryByMid);
		String title = null;
		if(supersededId.equals(dbMeetingTrajectoryEntity.getShouldAttendMetting())) {
			PersonnelEntity queryByIdUser = firmdataService.queryByIdUser(supersededId);
			//A选择B代替参加一个会议！
			title = queryByIdUser.getName() + "选择你代替参加一个会议！";
		}else{
			PersonnelEntity queryByIdUser = firmdataService.queryByIdUser(supersededId);
			PersonnelEntity queryByIdUser2 = firmdataService.queryByIdUser(dbMeetingTrajectoryEntity.getShouldAttendMetting());
			//B选择C代替A参加一个会议！
			title = queryByIdUser.getName() + "选择你代替" + queryByIdUser2.getName() + "参加一个会议！";
		}
		MessagePush push = new MessagePush("会议管理（会议召开）", title);
		Set<String> alias = new LinkedHashSet<String>();
		String login_name = firmdataService.queryByIdUser(fungibleId).getLogin_name();
		alias.add(login_name);
		long sendPushuser = push.sendPushAlias(alias,jsonData, "会议管理（会议召开）", queryByMid.getMeetingTheme() + title);
		//消息定义
		JPushToUser jPushToUser = new JPushToUser();
		jPushToUser.setIsRead(0);
		jPushToUser.setType(3);
		//通过登录名查询用户id
		jPushToUser.setUid(fungibleId);
		jPushToUser.setMsg_id(sendPushuser);
		jPushToUser.setMessageTitle("会议管理（会议召开）");
		jPushToUser.setMessageContent(title);
		jPushToUser.setMessageSendTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
		PersonnelEntity queryByIdUser = firmdataService.queryByIdUser(queryByMid.getUid());
		jPushToUser.setMessageSendPeople(queryByIdUser.getName());//会议发起人
		jPushToUser.setMid(mid);
		jPushToUserService.addJPushToUser(jPushToUser);
		meetingTrajectoryDao.update(dbMeetingTrajectoryEntity);
		return null;
	}

	//会议开始签到
	public Object updateMteStartSignInCoordinates(int mid, String userId, String startSignInCoordinates) {
		MeetingEntity queryByMid = jPushService.queryByMid(mid);
		if(queryByMid.getIsEnd() == 1) {
			return "该会议已经标记为结束，不可再签到";
		}
		MeetingTrajectoryEntity dbEntity = meetingTrajectoryDao.queryByMidAndShouldAttendMetting(mid, userId);
		dbEntity.setStartSignInCoordinates(startSignInCoordinates);
		dbEntity.setStartSignInTime(new Date());
		meetingTrajectoryDao.update(dbEntity);
		return null;
	}
	//会议结束签到
	public Object updateMteEndSignInCoordinates(int mid, String userId, String endSignInCoordinates)  {
		MeetingEntity queryByMid = jPushService.queryByMid(mid);
		if(queryByMid.getIsEnd() == 1) {
			return "该会议已经标记为结束，不可再签到";
		}
		MeetingTrajectoryEntity dbEntity = meetingTrajectoryDao.queryByMidAndShouldAttendMetting(mid, userId);
		dbEntity.setEndSignInTime(new Date());
		dbEntity.setEndSignInCoordinates(endSignInCoordinates);
		meetingTrajectoryDao.update(dbEntity);
		return null;
	}

	public Object updateMeetingImagePath(int mid, String userId, String meetingSummary, MultipartFile[] files) throws IllegalStateException, IOException {
		File file = new File(MEETINGIMAGE);
		if(!file.exists()) {
			file.mkdirs();
		}
		String meetingImagePath = null;
		if(files != null) {
			StringBuffer sb = new StringBuffer();
			String UID = userId.substring(userId.length()-3, userId.length());
			int i = 0;
			for (MultipartFile multipartFile : files) {
				String filename = multipartFile.getOriginalFilename();
				filename = filename.substring(filename.lastIndexOf("."));
				String path = MEETINGIMAGE + "/" + DateUtils.getCurrentDate("yyyyMMdd-HHmmss")+ UID + i + filename;
				String dbimagename = DateUtils.getCurrentDate("yyyyMMdd-HHmmss") + UID + i + filename;
				File imgepath = new File(path);
				multipartFile.transferTo(imgepath);
				sb.append(dbimagename + ",");
				i++;
			}
			
			if(sb.length() > 0) {
				meetingImagePath = sb.substring(0, sb.length()-1);
			}
		}
		MeetingTrajectoryEntity dbEntity = meetingTrajectoryDao.queryByMidAndShouldAttendMetting(mid, userId);
		dbEntity.setMeetingImagePath(meetingImagePath);
		dbEntity.setMeetingSummary(meetingSummary);
		meetingTrajectoryDao.update(dbEntity);
		return null;
	}
	//根据mid和签到人员id来查询签到信息
	public Object queryMetByMidAndUserId(int mid, String userId) {
		MeetingTrajectoryEntity dbMeetingTrajectoryEntity = meetingTrajectoryDao.queryMeetingTrajectoryEntityByMidAndUserIdList(mid, userId);
		return dbMeetingTrajectoryEntity;
	}

	public Object queryMeetingTrajectoryEntityByUserIdList(String userId) {
		List<MeetingTrajectoryEntity> dbList = meetingTrajectoryDao.queryMteByShouldAttendList(userId);
		List<MeetingTrajectoryEntity> reList = new ArrayList<MeetingTrajectoryEntity>();
		for (MeetingTrajectoryEntity meetingTrajectoryEntity : dbList) {
			if(meetingTrajectoryEntity.getStartSignInCoordinates() != null) {
				MeetingEntity meetingEntity = jPushService.queryByMid(meetingTrajectoryEntity.getMid());
				meetingTrajectoryEntity.setMeetingEntity(meetingEntity);
				reList.add(meetingTrajectoryEntity);
			}
		}
		return reList;
	}
	//融合平台会议时长统计 //根据mid查询所有签到记录
	public List<MeetingTrajectoryEntity> querymeetingTrajectoryEntityListByMid(int mid){
		return meetingTrajectoryDao.queryByMid(mid);
	}

}
