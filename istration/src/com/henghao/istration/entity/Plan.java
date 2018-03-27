package com.henghao.istration.entity;

/**
 * 审批节点数据实体类
 * @author 周承耀
 *
 */
public class Plan {
	
	
	private String title;
	
	private String week;
	//周末时间
	private String weekend;
	//规定时间
	private Integer date;
	//用时
	private Integer workday;
	//通过，办理中，未进行
	private String state;
	//头像
	private String img;
	//人名
	private String approveName;
	//部门
	private String department;
	//职位
	private String position;
	//时间
	private String approveTime;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getWeekend() {
		return weekend;
	}
	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Integer getWorkday() {
		return workday;
	}
	public void setWorkday(Integer workday) {
		this.workday = workday;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getApproveName() {
		return approveName;
	}
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}
	
	
	
}
