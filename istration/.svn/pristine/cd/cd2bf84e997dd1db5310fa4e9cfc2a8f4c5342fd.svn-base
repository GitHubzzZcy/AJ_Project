package com.henghao.news.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 会议管理消息推送实体
 *@author: zcy
 *@date： 2017-9-26 下午8:15:28
 *@version 1.0
 */

@Entity
@Table(name="aj_meeting_push")
public class MeetingEntity {
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int mid;    //会议id
	private String uid;     //发起会议人的id
    private String meetingTheme; //会议主题
    private String meetingStartTime; //开始时间
    private String meetingDuration;//会议时长
    private String meetingPlace;//会议地址
    private String wifiSSID; //wifi的id   //#平台页面用这个字段代替异常情况
    @Transient
    private Set<PersonnelEntity> meetingPeople;    //参会人员
    private int whetherPass;   //是否通过审批   默认0， 1：通过审批 , 2：申请被拒绝
    private String userIds;   //参会人员id
    private String noPassReason;  //不通过理由
    private String leadName;    //审批领导名字
    private String leadId;    //#审批人ID，由于需求变为选择审批人，所以才加的这个字段
    @Transient
    private  Set<JPushToUser> jPushToUser;    //参会人员
    @Transient
    private MeetingTrajectoryEntity  meetingTrajectoryEntity;
    private String meetingType; //会议类型
	private int isEnd; //默认0：未结束。1为结束

	public MeetingEntity() {
		super();
	}
	//该构造方法为MeetingStatisticsService类中解决hibernate对象缓存
	public MeetingEntity(MeetingEntity meetingEntity) {
		this.mid = meetingEntity.getMid();
		this.uid = meetingEntity.getUid();
		this.meetingTheme = meetingEntity.getMeetingTheme();
		this.meetingStartTime = meetingEntity.getMeetingStartTime();
		this.meetingDuration = meetingEntity.getMeetingDuration();
		this.meetingPlace = meetingEntity.getMeetingPlace();
		this.wifiSSID = meetingEntity.getWifiSSID();
		this.meetingPeople = meetingEntity.getMeetingPeople();
		this.whetherPass = meetingEntity.getWhetherPass();
		this.userIds = meetingEntity.getUserIds();
		this.noPassReason = meetingEntity.getNoPassReason();
		this.leadName = meetingEntity.getLeadName();
		this.jPushToUser = meetingEntity.getjPushToUser();
		this.meetingTrajectoryEntity = meetingEntity.getMeetingTrajectoryEntity();
		this.meetingType = meetingEntity.getMeetingType();
		this.isEnd = meetingEntity.getIsEnd();
		this.leadId = meetingEntity.getLeadId();
	}
	


	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public int getIsEnd() {
		return isEnd;
	}
	public void setIsEnd(int isEnd) {
		this.isEnd = isEnd;
	}
	public String getMeetingType() {
		return meetingType;
	}




	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}




	public MeetingTrajectoryEntity getMeetingTrajectoryEntity() {
		return meetingTrajectoryEntity;
	}




	public void setMeetingTrajectoryEntity(MeetingTrajectoryEntity meetingTrajectoryEntity) {
		this.meetingTrajectoryEntity = meetingTrajectoryEntity;
	}




	public String getLeadName() {
		return leadName;
	}




	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}




	public int getMid() {
		return mid;
	}




	public void setMid(int mid) {
		this.mid = mid;
	}




	public String getUid() {
		return uid;
	}




	public void setUid(String uid) {
		this.uid = uid;
	}




	public String getMeetingTheme() {
		return meetingTheme;
	}




	public void setMeetingTheme(String meetingTheme) {
		this.meetingTheme = meetingTheme;
	}




	public String getMeetingStartTime() {
		return meetingStartTime;
	}




	public void setMeetingStartTime(String meetingStartTime) {
		this.meetingStartTime = meetingStartTime;
	}




	public String getMeetingDuration() {
		return meetingDuration;
	}




	public void setMeetingDuration(String meetingDuration) {
		this.meetingDuration = meetingDuration;
	}




	public Set<PersonnelEntity> getMeetingPeople() {
		return meetingPeople;
	}




	public void setMeetingPeople(Set<PersonnelEntity> meetingPeople) {
		this.meetingPeople = meetingPeople;
	}




	



	public int getWhetherPass() {
		return whetherPass;
	}




	public void setWhetherPass(int whetherPass) {
		this.whetherPass = whetherPass;
	}




	public String getUserIds() {
		return userIds;
	}




	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}




	public Set<JPushToUser> getjPushToUser() {
		return jPushToUser;
	}




	public void setjPushToUser(Set<JPushToUser> jPushToUser) {
		this.jPushToUser = jPushToUser;
	}




	public String getNoPassReason() {
		return noPassReason;
	}




	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}




	public String getMeetingPlace() {
		return meetingPlace;
	}




	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}




	public String getWifiSSID() {
		return wifiSSID;
	}




	public void setWifiSSID(String wifiSSID) {
		this.wifiSSID = wifiSSID;
	}




	public MeetingEntity(int mid, String uid, String meetingTheme, String meetingStartTime, String meetingDuration,
			String meetingPlace, String wifiSSID, Set<PersonnelEntity> meetingPeople, int whetherPass, String userIds,
			String noPassReason, String leadName, Set<JPushToUser> jPushToUser,
			MeetingTrajectoryEntity meetingTrajectoryEntity, String meetingType, int isEnd, String leadId) {
		super();
		this.mid = mid;
		this.uid = uid;
		this.meetingTheme = meetingTheme;
		this.meetingStartTime = meetingStartTime;
		this.meetingDuration = meetingDuration;
		this.meetingPlace = meetingPlace;
		this.wifiSSID = wifiSSID;
		this.meetingPeople = meetingPeople;
		this.whetherPass = whetherPass;
		this.userIds = userIds;
		this.noPassReason = noPassReason;
		this.leadName = leadName;
		this.jPushToUser = jPushToUser;
		this.meetingTrajectoryEntity = meetingTrajectoryEntity;
		this.meetingType = meetingType;
		this.isEnd = isEnd;
		this.leadId = leadId;
	}
	@Override
	public String toString() {
		return "MeetingEntity [mid=" + mid + ", uid=" + uid + ", meetingTheme=" + meetingTheme + ", meetingStartTime="
				+ meetingStartTime + ", meetingDuration=" + meetingDuration + ", meetingPlace=" + meetingPlace
				+ ", wifiSSID=" + wifiSSID + ", meetingPeople=" + meetingPeople + ", whetherPass=" + whetherPass
				+ ", userIds=" + userIds + ", noPassReason=" + noPassReason + ", leadName=" + leadName + ", leadId="
				+ leadId + ", jPushToUser=" + jPushToUser + ", meetingTrajectoryEntity=" + meetingTrajectoryEntity
				+ ", meetingType=" + meetingType + ", isEnd=" + isEnd + "]";
	}
	




	




	








	





	
}