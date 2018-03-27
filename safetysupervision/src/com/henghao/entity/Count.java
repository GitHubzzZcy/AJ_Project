package com.henghao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 所有预警保存表
 * @author longhong
 *
 */
@Entity
@Table(name="count")
public class Count {
	private Integer id;  //表ID
	private String enforcementNum;  // 执法预警次数
	private String approvalNum; // 审批预警次数
	private String carManagementNum; //车辆管理次数
	private String accountabilityNum; //督责问责次数
	private String meetingsNum;      // 会议管理次数
	private String attendanceNum;   // 考勤管理次数
	private String time;             // 时间
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id",unique =true,nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="enforcementNum",nullable=true)
	public String getEnforcementNum() {
		return enforcementNum;
	}
	public void setEnforcementNum(String enforcementNum) {
		this.enforcementNum = enforcementNum;
	}
	@Column(name="approvalNum",nullable=true)
	public String getApprovalNum() {
		return approvalNum;
	}
	public void setApprovalNum(String approvalNum) {
		this.approvalNum = approvalNum;
	}
	@Column(name ="carManagementNum",nullable=true)
	public String getCarManagementNum() {
		return carManagementNum;
	}
	public void setCarManagementNum(String carManagementNum) {
		this.carManagementNum = carManagementNum;
	}
	@Column(name="accountabilityNum",nullable=true)
	public String getAccountabilityNum() {
		return accountabilityNum;
	}
	public void setAccountabilityNum(String accountabilityNum) {
		this.accountabilityNum = accountabilityNum;
	}
	@Column(name="meetingsNum",nullable=true)
	public String getMeetingsNum() {
		return meetingsNum;
	}
	public void setMeetingsNum(String meetingsNum) {
		this.meetingsNum = meetingsNum;
	}
	@Column(name= "attendanceNum",nullable=true)
	public String getAttendanceNum() {
		return attendanceNum;
	}
	public void setAttendanceNum(String attendanceNum) {
		this.attendanceNum = attendanceNum;
	}
	@Column(name = "time",nullable=true)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
} 
