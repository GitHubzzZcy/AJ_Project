package com.henghao.news.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.henghao.news.dao.JPushDao;
import com.henghao.news.dao.MeetingTrajectoryDao;
import com.henghao.news.entity.JPushToUser;
import com.henghao.news.entity.MeetingEntity;
import com.henghao.news.entity.MeetingTrajectoryEntity;
import com.henghao.news.entity.PersonnelEntity;
import com.henghao.news.util.DateUtils;
import com.henghao.news.util.MessagePush;

@Service
public class JPushService {
	@Resource
	private JPushDao jPushDao;
	@Resource
	private FirmdataService firmdataService;
	@Resource
	private UserDataService userDataService;
	@Resource
	private JPushToUserService jPushToUserService;
	@Resource
	private MeetingTrajectoryDao meetingTrajectoryDao;
	@Resource
	private MeetingTrajectoryService meetingTrajectoryService;
	
	public final static String NOTIFICATION_TITLE = "会议管理";
	public final static String NOTIFICATION_TITLE_A = "(待审批)";
	public final static String NOTIFICATION_TITLE_B = "(等待审批)";
	public final static String NOTIFICATION_TITLE_C = "(会议召开)";
	public final static String NOTIFICATION_TITLE_D = "(审批未通过)";
	public final static String NOTIFICATION_TITLE_E = "(已审批)";
	public final static String NOTIFICATION_TITLE_F = "(审批已通过)";
	public final static String NOTIFICATION_LEAD = "。你有一条需要审批的预约会议，请尽快审批！";
	public final static String NOTIFICATION_CONTENT = "。你发起的预约会议已成功推送至";
	public final static String NOTIFICATION_CONTENT_2 = "审批,请留意受理结果！";
	public final static String NOTIFICATION_CONFERREE = "。你有一个会议需要参加，请关注！";
	public final static String NOTIFICATION_PASS = "。你发起的会议申请已经审批通过，请关注！";
	public final static String NOTIFICATION_NOPASS = "。你发起的会议申请审批未通过！";

	public Object addMeetingEntity(MeetingEntity gson) {
		//获取发起人的信息
		PersonnelEntity queryByIdUser = firmdataService.queryByIdUser(gson.getUid());
		Set<PersonnelEntity> set = gson.getMeetingPeople();
		//获取参会人员id
		StringBuffer sb = new StringBuffer();
		String userIds = null;
		for (PersonnelEntity user : set) {
			//如果在选择参会人员是选择了自己。就把他去掉
			if(!user.getId().equals(gson.getUid())){
				sb.append(user.getId()+",");
			}
		}
		if(sb.length() > 0) {
			userIds = sb.substring(0, sb.length()-1);
		}
		gson.setUserIds(userIds);
		String jsonData = JSONObject.toJSONString(gson);
		
		//获取发起人主管领导的信息
		//PersonnelEntity lead = jPushDao.queryLeadByUserName(gson.getUid());
		
		//由于需求改为APP选择审批人，所以审批领导的ID由APP提交，下面不会走if,全都会走else。但防止以后再修改需求，保留if的逻辑
		PersonnelEntity lead = firmdataService.queryByIdUser(gson.getLeadId());
		
		//无上级领导直接发送通知参会人员开会
		if(lead == null) {
			// 增加会议签到轨迹
			List<MeetingTrajectoryEntity> meetingTrajectoryEntityList = new ArrayList<MeetingTrajectoryEntity>();
			//无领导设置审批为通过
			gson.setWhetherPass(1);
			Set<JPushToUser> set1 = new LinkedHashSet<JPushToUser>();
			Set<String> alias = new LinkedHashSet<String>();
			Set<PersonnelEntity> meetingPeopleList = gson.getMeetingPeople();
			for (PersonnelEntity personnelEntity : meetingPeopleList) {
				alias.add(personnelEntity.getLogin_name());
				//会议签到对象
				meetingTrajectoryEntityList.add(new MeetingTrajectoryEntity(personnelEntity.getId()));
			}
			//将自己也添加到参会人员列表里
			alias.add(queryByIdUser.getLogin_name());
			meetingTrajectoryEntityList.add(new MeetingTrajectoryEntity(queryByIdUser.getId()));
			//发送给所有参会人员
			MessagePush push = new MessagePush(NOTIFICATION_TITLE+NOTIFICATION_TITLE_C, gson.getMeetingTheme()+NOTIFICATION_CONFERREE);
			
			long sendPushuser = push.sendPushAlias(alias,jsonData, NOTIFICATION_TITLE+NOTIFICATION_TITLE_C, gson.getMeetingTheme()+NOTIFICATION_CONFERREE);
			for (String loginname : alias) {
				//消息定义
				JPushToUser jPushToUser = new JPushToUser();
				jPushToUser.setIsRead(0);
				jPushToUser.setType(3);
				//通过登录名查询用户id
				PersonnelEntity personnelEntity = firmdataService.queryByIdLoginName(loginname);
				jPushToUser.setUid(personnelEntity.getId());
				jPushToUser.setMsg_id(sendPushuser);
				jPushToUser.setMessageTitle(NOTIFICATION_TITLE+NOTIFICATION_TITLE_C);
				jPushToUser.setMessageContent(gson.getMeetingTheme());
				jPushToUser.setMessageSendTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				jPushToUser.setMessageSendPeople(queryByIdUser.getName());
				set1.add(jPushToUser);
				
			}
			//保存会议
			jPushDao.add(gson);
			//保存会议签到实体
			for (MeetingTrajectoryEntity meetingTrajectoryEntity : meetingTrajectoryEntityList) {
				meetingTrajectoryEntity.setMid(gson.getMid());
				meetingTrajectoryDao.add(meetingTrajectoryEntity);
			}
			//获取会议id保存消息到数据库
			for (JPushToUser jPushToUser : set1) {
				jPushToUser.setMid(gson.getMid());
				jPushToUserService.addJPushToUser(jPushToUser);
			}
		}else{
			//发给领导，提醒审批
			Set<String> alias = new LinkedHashSet<String>();
			alias.add(lead.getLogin_name());
			MessagePush push = new MessagePush(NOTIFICATION_TITLE+NOTIFICATION_TITLE_A, gson.getMeetingTheme()+NOTIFICATION_LEAD);
			long sendPushLead = push.sendPushAlias(alias, jsonData, NOTIFICATION_TITLE+NOTIFICATION_TITLE_A, gson.getMeetingTheme()+NOTIFICATION_LEAD);
			//消息定义
			
			JPushToUser jPushToUser = new JPushToUser();
			jPushToUser.setIsRead(0);
			jPushToUser.setType(1);
			jPushToUser.setUid(lead.getId());
			jPushToUser.setMsg_id(sendPushLead);
			jPushToUser.setMessageTitle(NOTIFICATION_TITLE+NOTIFICATION_TITLE_A);
			jPushToUser.setMessageContent(gson.getMeetingTheme());
			jPushToUser.setMessageSendTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			jPushToUser.setMessageSendPeople(queryByIdUser.getName());
			
			//获取发起人信息，推送给会议发起人，提示消息推送给领导成功
			PersonnelEntity user = firmdataService.queryByIdUser(gson.getUid());
			Set<String> aliasuser = new LinkedHashSet<String>();
			aliasuser.add(user.getLogin_name());
			MessagePush push2 = new MessagePush(NOTIFICATION_TITLE+NOTIFICATION_TITLE_B, gson.getMeetingTheme()+NOTIFICATION_CONTENT + lead.getName() + NOTIFICATION_CONTENT_2);
			long sendPushuser = push2.sendPushAlias(aliasuser,jsonData, NOTIFICATION_TITLE+NOTIFICATION_TITLE_B, gson.getMeetingTheme()+NOTIFICATION_CONTENT + lead.getName() + NOTIFICATION_CONTENT_2);
			//消息定义/4/提示发起人等待审批结果
			JPushToUser jPushToUser2 = new JPushToUser();
			jPushToUser2.setIsRead(0);
			jPushToUser2.setType(4);
			jPushToUser2.setUid(user.getId());
			jPushToUser2.setMsg_id(sendPushuser);
			jPushToUser2.setMessageTitle(NOTIFICATION_TITLE+NOTIFICATION_TITLE_B);
			jPushToUser2.setMessageContent(gson.getMeetingTheme());
			jPushToUser2.setMessageSendTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			jPushToUser2.setMessageSendPeople(queryByIdUser.getName());
			//保存消息定义列表
			Set<JPushToUser> jPushToUserSet = new LinkedHashSet<JPushToUser>();
			jPushToUserSet.add(jPushToUser);
			jPushToUserSet.add(jPushToUser2);
			gson.setjPushToUser(jPushToUserSet);
			gson.setLeadName(lead.getName());
			//保存会议
			jPushDao.add(gson);
			//获取会议id保存消息到数据库
			for (JPushToUser jPushToUserdb : jPushToUserSet) {
				jPushToUserdb.setMid(gson.getMid());
				jPushToUserService.addJPushToUser(jPushToUserdb);
			}
		}
		
		return null;
	}

	public MeetingEntity queryMeetingEntityByMsgid(Long msg_id, String uid) {
		Integer mid = jPushToUserService.queryJPushToUserMidByMsgId(msg_id);
		MeetingEntity meetingEntity = jPushDao.findById(mid);
		//
		MeetingTrajectoryEntity queryMetByMidAndUserId = (MeetingTrajectoryEntity) meetingTrajectoryService.queryMetByMidAndUserId(mid, uid);
		meetingEntity.setMeetingTrajectoryEntity(queryMetByMidAndUserId);
		//根据msg_id和uid查询JPushToUser
		JPushToUser queryByMsg_id = jPushToUserService.queryByMsgId(msg_id,uid);
		Set<JPushToUser> set = new LinkedHashSet<JPushToUser>();
		set.add(queryByMsg_id);
		meetingEntity.setjPushToUser(set);
		
		String userIds = meetingEntity.getUserIds();
		String[] split = userIds.split(",");
		StringBuffer sb = new StringBuffer();
		String usernames = null;
		for (String userId : split) {
			PersonnelEntity queryByIdUser = firmdataService.queryByIdUser(userId);
			sb.append(queryByIdUser.getName()+",");
		}
		if(sb.length() > 0) {
			usernames = sb.substring(0, sb.length()-1);
		}
		meetingEntity.setUserIds(usernames);
		return meetingEntity;
	}

	public Object queryMeetingEntityByUserIdAll(String uid) {
		List<MeetingEntity> relist = new ArrayList<MeetingEntity>();
		List<Integer> jPushToUserMidAll = jPushToUserService.queryJPushByUserAllmid(uid);
		for (Integer mid : jPushToUserMidAll) {
			MeetingEntity meetingEntity = jPushDao.findById(mid);
			List<JPushToUser> jPushToUserList = jPushToUserService.queryByMidUser(mid, uid);
			Set<JPushToUser> set = new LinkedHashSet<JPushToUser>();
			set.addAll(jPushToUserList);
			meetingEntity.setjPushToUser(set);
			relist.add(meetingEntity);
		}
		return relist;
	}

	/**
	 * 审批更新
	 * @param mid
	 * @param whetherPass
	 * @return
	 */
	public Object updateMeetingEntityWhetherPass(int mid, int whetherPass,String noPassReason) {
		MeetingEntity gson = jPushDao.findById(mid);
		//获取发起人信息
		PersonnelEntity queryByIdUser = firmdataService.queryByIdUser(gson.getUid());
		String jsonData = JSONObject.toJSONString(gson);
		Set<JPushToUser> set = new LinkedHashSet<JPushToUser>();
		// 增加会议签到轨迹
		List<MeetingTrajectoryEntity> meetingTrajectoryEntityList = new ArrayList<MeetingTrajectoryEntity>();
		
		if(1 == whetherPass) {
			gson.setWhetherPass(whetherPass);
			//获取参会人员的别名集合
			String userIds = gson.getUserIds();
			String[] userId = userIds.split(",");
			Set<String> alias = new LinkedHashSet<String>();
			for (String uid : userId) {
				PersonnelEntity personnelEntity = firmdataService.queryByIdUser(uid);
				alias.add(personnelEntity.getLogin_name());
				//会议签到对象
				meetingTrajectoryEntityList.add(new MeetingTrajectoryEntity(gson.getMid(), personnelEntity.getId()));
			}
			//将自己也添加到参会人员列表里
			alias.add(queryByIdUser.getLogin_name());
			//自己会议签到
			meetingTrajectoryEntityList.add(new MeetingTrajectoryEntity(gson.getMid(), queryByIdUser.getId()));
			//发送给所有参会人员
			MessagePush push = new MessagePush(NOTIFICATION_TITLE+NOTIFICATION_TITLE_C, gson.getMeetingTheme()+NOTIFICATION_CONFERREE);
			long sendPushuser = push.sendPushAlias(alias,jsonData, NOTIFICATION_TITLE+NOTIFICATION_TITLE_C, gson.getMeetingTheme()+NOTIFICATION_CONFERREE);
			for (String loginname : alias) {
				//消息定义
				JPushToUser jPushToUser = new JPushToUser();
				jPushToUser.setIsRead(0);
				jPushToUser.setType(3);
				//通过登录名查询用户id
				PersonnelEntity personnelEntity = firmdataService.queryByIdLoginName(loginname);
				jPushToUser.setUid(personnelEntity.getId());
				jPushToUser.setMsg_id(sendPushuser);
				jPushToUser.setMessageTitle(NOTIFICATION_TITLE+NOTIFICATION_TITLE_C);
				jPushToUser.setMessageContent(gson.getMeetingTheme());
				jPushToUser.setMessageSendTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				jPushToUser.setMessageSendPeople(queryByIdUser.getName());
				set.add(jPushToUser);
			}
			jPushDao.update(gson);
		}else{
			gson.setWhetherPass(whetherPass);
			//审批不通过发送给自己
			Set<String> alias2 = new LinkedHashSet<String>();
			alias2.add(queryByIdUser.getLogin_name());
			MessagePush push2 = new MessagePush(NOTIFICATION_TITLE+NOTIFICATION_TITLE_D, gson.getMeetingTheme()+NOTIFICATION_NOPASS);
			long sendPushuser2 = push2.sendPushAlias(alias2,jsonData, NOTIFICATION_TITLE+NOTIFICATION_TITLE_D, gson.getMeetingTheme()+NOTIFICATION_NOPASS);
			JPushToUser jPushToUser = new JPushToUser();
			jPushToUser.setIsRead(0);
			jPushToUser.setType(2);
			jPushToUser.setUid(queryByIdUser.getId());
			jPushToUser.setMsg_id(sendPushuser2);
			jPushToUser.setMessageTitle(NOTIFICATION_TITLE+NOTIFICATION_TITLE_D);
			jPushToUser.setMessageContent(gson.getMeetingTheme());
			jPushToUser.setMessageSendTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			jPushToUser.setMessageSendPeople(queryByIdUser.getName());
			set.add(jPushToUser);
			//审批未通过，保存不通过理由
			gson.setNoPassReason(noPassReason);
			jPushDao.update(gson);
		}
		//数据库存在的消息
		List<JPushToUser> dblist = jPushToUserService.queryByMid(gson.getMid());
		Set<JPushToUser> newlist = new LinkedHashSet<JPushToUser>();
		for (JPushToUser jPushToUser : dblist) {
			//不管通不通过修改领导（待审批）为（已审批）
			if(jPushToUser.getType() == 1) {
				jPushToUser.setMessageTitle(NOTIFICATION_TITLE+NOTIFICATION_TITLE_E);
			}
			newlist.add(jPushToUser);
			
		}
		newlist.addAll(set);
		
		
		for (JPushToUser jPushToUserdb : newlist) {
			jPushToUserdb.setMid(gson.getMid());
			jPushToUserService.addJPushToUser(jPushToUserdb);
		}
		//保存会议签到实体
		for (MeetingTrajectoryEntity meetingTrajectoryEntity : meetingTrajectoryEntityList) {
			meetingTrajectoryDao.add(meetingTrajectoryEntity);
		}
		return null;
	}
	//将消息标记为已查看
	public void updateJPushToUserIsRead(int cid) {
		JPushToUser jPushToUser = jPushToUserService.queryByCid(cid);
		jPushToUser.setIsRead(1);
		jPushToUserService.updateJPushToUser(jPushToUser);
	}

	public List<JPushToUser> queryJPushToUserUnRead(String uid) {
		List<JPushToUser> list = jPushToUserService.queryJPushToUserUnRead(uid);
		return list;
	}
	//根据用户查询发起并审批通过的会议列表
	public List<MeetingEntity> queryMeetingEntityByUserList(String uid) {
		List<MeetingEntity> list = jPushDao.queryMeetingEntityByUserList(uid);
		List<MeetingEntity> relist = new ArrayList<MeetingEntity>();
		for (MeetingEntity meetingEntity : list) {
			StringBuffer sb = new StringBuffer();
			String userIds = meetingEntity.getUserIds();
			String[] split = userIds.split(",");
			for (String userid : split) {
				String userName = userDataService.getUserName(userid);
				sb.append(userName+",");
			}
			if(sb.length() > 0) {
				sb.substring(0, sb.length()-1);
				meetingEntity.setUserIds(sb.toString());
			}
			relist.add(meetingEntity);
		}
		return relist;
	}
	//修改会议字段，标记为会议结束，结束后将不能再去签到
	public void updateMeetingEntityByIsEnd(int mid) {
		MeetingEntity findById = jPushDao.findById(mid);
		findById.setIsEnd(1);
		jPushDao.update(findById);
	}
	//会议选择代替参会人员时消息推送是调用
	public MeetingEntity queryByMid(int mid) {
		MeetingEntity findById = jPushDao.findById(mid);

		return findById;
	}
	//融合平台查询所有通过会议
	public List<MeetingEntity> queryPassAll(String start, String end) {
		List<MeetingEntity> passAll = jPushDao.queryPassAll(start, end);
		return passAll;
	}
	//用户评分
	public List<MeetingEntity> queryPassUser(String userId, String start, String end) {
		List<MeetingEntity> passAll = jPushDao.queryPassUser(userId, start, end);
		return passAll;
	}
	//融合平台查询所有通过会议数据列表
	public Map<String, Object> queryMeetingCountList(int page, int size, String start, String end) {
		Map<String, Object> map = jPushDao.queryMeetingCountList(page, size, start, end);
		return map;
	}

	
	
}
