package com.henghao.news.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Description:会议签到
 * @author zcy  
 * @date 2017年10月26日 下午2:13:07
 * @version 1.0
 */
@Entity
@Table(name="aj_meeting_trajectory")
public class MeetingTrajectoryEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int mtid;
    private int mid;    //会议预约Id
    private String shouldAttendMetting;         //应该本身参加会议的人
    private String substitute;              //替代开会人
    private String startSignInCoordinates;  //进场签到坐标
    private Date startSignInTime;         //进场签到时间
    private String endSignInCoordinates;    //退场签到坐标
    private Date endSignInTime;           //退场签到时间
    private String meetingSummary;          //会议纪要
    private String meetingImagePath;      //会议现场所有图片的路径
    
    @Transient
    private MeetingEntity meetingEntity;
    
    
    
    
	public MeetingTrajectoryEntity(int mid, String shouldAttendMetting) {
		super();
		this.mid = mid;
		this.shouldAttendMetting = shouldAttendMetting;
	}
	public MeetingTrajectoryEntity(String shouldAttendMetting) {
		super();
		this.shouldAttendMetting = shouldAttendMetting;
	}
	public MeetingTrajectoryEntity() {
		super();
	}
	public int getMtid() {
		return mtid;
	}
	public void setMtid(int mtid) {
		this.mtid = mtid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getShouldAttendMetting() {
		return shouldAttendMetting;
	}
	public void setShouldAttendMetting(String shouldAttendMetting) {
		this.shouldAttendMetting = shouldAttendMetting;
	}
	

	public MeetingEntity getMeetingEntity() {
		return meetingEntity;
	}
	public void setMeetingEntity(MeetingEntity meetingEntity) {
		this.meetingEntity = meetingEntity;
	}
	public String getSubstitute() {
		return substitute;
	}
	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}
	public String getStartSignInCoordinates() {
		return startSignInCoordinates;
	}
	public void setStartSignInCoordinates(String startSignInCoordinates) {
		this.startSignInCoordinates = startSignInCoordinates;
	}
	public Date getStartSignInTime() {
		return startSignInTime;
	}
	public void setStartSignInTime(Date startSignInTime) {
		this.startSignInTime = startSignInTime;
	}
	public String getEndSignInCoordinates() {
		return endSignInCoordinates;
	}
	public void setEndSignInCoordinates(String endSignInCoordinates) {
		this.endSignInCoordinates = endSignInCoordinates;
	}
	public Date getEndSignInTime() {
		return endSignInTime;
	}
	public void setEndSignInTime(Date endSignInTime) {
		this.endSignInTime = endSignInTime;
	}
	public String getMeetingSummary() {
		return meetingSummary;
	}
	public void setMeetingSummary(String meetingSummary) {
		this.meetingSummary = meetingSummary;
	}
	public String getMeetingImagePath() {
		return meetingImagePath;
	}
	public void setMeetingImagePath(String meetingImagePath) {
		this.meetingImagePath = meetingImagePath;
	}
    
    
    
    
}