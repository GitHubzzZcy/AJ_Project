package com.henghao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户个人轨迹、个人事件
 * @Description:
 * @author zcy  
 * @date 2017年11月16日 下午3:16:47
 * @version 1.0
 */
@Entity
@Table(name="aj_event_usertrack" )
public class UserEventTrackEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String userId;
	// * 日期
	private String eventDate;
	// * 事件名称
	private String eventName;
	// * 地点
	private String eventAddress;
	// * 上传时间
	private String eventTime;
	private String eventEndTime; //时间结束时间
	//图片地址
	private String eventImagePath;

	public UserEventTrackEntity() {
		super();
	}

	

	public UserEventTrackEntity(int id, String userId, String eventDate, String eventName, String eventAddress,
			String eventTime, String eventEndTime, String eventImagePath) {
		super();
		this.id = id;
		this.userId = userId;
		this.eventDate = eventDate;
		this.eventName = eventName;
		this.eventAddress = eventAddress;
		this.eventTime = eventTime;
		this.eventEndTime = eventEndTime;
		this.eventImagePath = eventImagePath;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventImagePath() {
		return eventImagePath;
	}

	public void setEventImagePath(String eventImagePath) {
		this.eventImagePath = eventImagePath;
	}

	public String getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(String eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	@Override
	public String toString() {
		return "UserEventTrackEntity [id=" + id + ", userId=" + userId + ", eventDate=" + eventDate + ", eventName="
				+ eventName + ", eventAddress=" + eventAddress + ", eventTime=" + eventTime + ", eventEndTime="
				+ eventEndTime + ", eventImagePath=" + eventImagePath + "]";
	}

	
	
	
}