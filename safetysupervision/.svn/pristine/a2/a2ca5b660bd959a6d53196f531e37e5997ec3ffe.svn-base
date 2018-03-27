package com.henghao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 签到业务实体类
 *
 */
@Entity
@Table(name="CI_checkingIn")
public class Checking implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//用户的id
	private String userId;
	private String userName;
	//上班时间
	@Temporal(TemporalType.TIME)
	private Date clockInTime;
	//下班时间
	@Temporal(TemporalType.TIME)
	private Date clockOutTime;
	//上午考勤地点
	/**
	 * 20170608根据需要修改为保存上午打卡是否迟到
	 */
	private String checkLocation;
	//上午打卡的经度
	private String lon;
	//上午打卡的纬度
	private String lat;
	//下午考勤地点
	/**
	 * 20170608根据需要修改为保存下午打卡是否早退
	 */
	private String afterLocation;
	//下午打卡的经度
	private String afterLon;
	//下午打卡的纬度
	private String afterLat;
	//考勤类型，1为正常考勤，2为外勤签到
	private String checkType;
	//当天上午打卡的次数
	private int morningCount;
	//当天下午打卡的次数
	private int afterCount;
	//当前日期
	@Temporal(TemporalType.DATE)
	private Date currentDate;
	//部门
	private String dept;
	//岗位、
	private String job;
	//星期几
	private String week;
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getClockInTime() {
		return clockInTime;
	}
	public void setClockInTime(Date clockInTime) {
		this.clockInTime = clockInTime;
	}
	public Date getClockOutTime() {
		return clockOutTime;
	}
	public void setClockOutTime(Date clockOutTime) {
		this.clockOutTime = clockOutTime;
	}
	public String getCheckLocation() {
		return checkLocation;
	}
	public void setCheckLocation(String checkLocation) {
		this.checkLocation = checkLocation;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getAfterLocation() {
		return afterLocation;
	}
	public void setAfterLocation(String afterLocation) {
		this.afterLocation = afterLocation;
	}
	public String getAfterLon() {
		return afterLon;
	}
	public void setAfterLon(String afterLon) {
		this.afterLon = afterLon;
	}
	public String getAfterLat() {
		return afterLat;
	}
	public void setAfterLat(String afterLat) {
		this.afterLat = afterLat;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public int getMorningCount() {
		return morningCount;
	}
	public void setMorningCount(int morningCount) {
		this.morningCount = morningCount;
	}
	public int getAfterCount() {
		return afterCount;
	}
	public void setAfterCount(int afterCount) {
		this.afterCount = afterCount;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public String toString() {
		return "Checking [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", clockInTime=" + clockInTime + ", clockOutTime="
				+ clockOutTime + ", checkLocation=" + checkLocation + ", lon="
				+ lon + ", lat=" + lat + ", afterLocation=" + afterLocation
				+ ", afterLon=" + afterLon + ", afterLat=" + afterLat
				+ ", checkType=" + checkType + ", morningCount=" + morningCount
				+ ", afterCount=" + afterCount + ", currentDate=" + currentDate
				+ ", dept=" + dept + ", job=" + job + ", week=" + week + "]";
	}
	
	

}

























