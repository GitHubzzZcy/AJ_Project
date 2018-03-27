package com.henghao.entity;


/**
 * 执法工作量报报表
 * @author 龙宏
 *
 */
public class WorkNum {
	/**
	 * 工作次数
	 */
	private String workNum;
	/**
	 * 横坐标 天数
	 */
	private Double days;
	/**
	 * 工作的时间
	 */
	private String time;
	public String getWorkNum() {
		return workNum;
	}
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	public Double getDays() {
		return days;
	}
	public void setDays(Double days) {
		this.days = days;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
}
