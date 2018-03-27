package com.henghao.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会议上传实体类
 * @Description:
 * @author zcy  
 * @date 2017年10月16日 下午下午3:56:21
 * @version 1.0
 */
@Entity
@Table(name="aj_meeting_upload")
public class MeetingUploadEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int muid;        //上传会议的id
    private int mid;        //预约会议id
    private String meetingUploadTheme;      //会议上传主题
    private String meetingUploadPeople;      //会议组织人 发起人
    private String meetingUploadStartTime;      //会议举行开始时间
    private String meetingUploadDuration;      //会议举行时长
    private String meetingUploadPlace;      //会议举行地点
    private String meetingUploadJoinPeople;     //会议应到场人员
    private String meetingUploadQiandaoPeople;      //会议到场签到人员
    private String meetingUploadContent;      //会议的内容
    private String meetingUploadSummary;      //会议总结
    private String meetingUploadImagePath;      //会议现场所有图片的路径
    
    
	public MeetingUploadEntity() {
		super();
	}
	public int getMuid() {
		return muid;
	}
	public void setMuid(int muid) {
		this.muid = muid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMeetingUploadTheme() {
		return meetingUploadTheme;
	}
	public void setMeetingUploadTheme(String meetingUploadTheme) {
		this.meetingUploadTheme = meetingUploadTheme;
	}
	public String getMeetingUploadPeople() {
		return meetingUploadPeople;
	}
	public void setMeetingUploadPeople(String meetingUploadPeople) {
		this.meetingUploadPeople = meetingUploadPeople;
	}
	public String getMeetingUploadStartTime() {
		return meetingUploadStartTime;
	}
	public void setMeetingUploadStartTime(String meetingUploadStartTime) {
		this.meetingUploadStartTime = meetingUploadStartTime;
	}
	public String getMeetingUploadDuration() {
		return meetingUploadDuration;
	}
	public void setMeetingUploadDuration(String meetingUploadDuration) {
		this.meetingUploadDuration = meetingUploadDuration;
	}
	public String getMeetingUploadPlace() {
		return meetingUploadPlace;
	}
	public void setMeetingUploadPlace(String meetingUploadPlace) {
		this.meetingUploadPlace = meetingUploadPlace;
	}
	public String getMeetingUploadJoinPeople() {
		return meetingUploadJoinPeople;
	}
	public void setMeetingUploadJoinPeople(String meetingUploadJoinPeople) {
		this.meetingUploadJoinPeople = meetingUploadJoinPeople;
	}
	public String getMeetingUploadQiandaoPeople() {
		return meetingUploadQiandaoPeople;
	}
	public void setMeetingUploadQiandaoPeople(String meetingUploadQiandaoPeople) {
		this.meetingUploadQiandaoPeople = meetingUploadQiandaoPeople;
	}
	public String getMeetingUploadContent() {
		return meetingUploadContent;
	}
	public void setMeetingUploadContent(String meetingUploadContent) {
		this.meetingUploadContent = meetingUploadContent;
	}
	public String getMeetingUploadSummary() {
		return meetingUploadSummary;
	}
	public void setMeetingUploadSummary(String meetingUploadSummary) {
		this.meetingUploadSummary = meetingUploadSummary;
	}
	public String getMeetingUploadImagePath() {
		return meetingUploadImagePath;
	}
	public void setMeetingUploadImagePath(String meetingUploadImagePath) {
		this.meetingUploadImagePath = meetingUploadImagePath;
	}
    
    

}