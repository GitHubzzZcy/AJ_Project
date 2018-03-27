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
 * 工作时间实体类
 *
 */
@Entity
@Table(name="ci_worktime_java")
public class WorkTime implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//上班规定时间
	@Temporal(TemporalType.TIME)
	private Date clockIn;
	//下班规定时间
	@Temporal(TemporalType.TIME)
	private Date clockOut;
	//上下班卡区分规定时间
	@Temporal(TemporalType.TIME)
	private Date middleTime;
	
	public WorkTime() {
		super();
	}
	public WorkTime(Date clockIn, Date clockOut) {
		super();
		this.clockIn = clockIn;
		this.clockOut = clockOut;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getClockIn() {
		return clockIn;
	}
	public void setClockIn(Date clockIn) {
		this.clockIn = clockIn;
	}
	public Date getClockOut() {
		return clockOut;
	}
	public void setClockOut(Date clockOut) {
		this.clockOut = clockOut;
	}
	public Date getMiddleTime() {
		return middleTime;
	}
	public void setMiddleTime(Date middleTime) {
		this.middleTime = middleTime;
	}
	@Override
	public String toString() {
		return "WorkTime [id=" + id + ", clockIn=" + clockIn + ", clockOut="
				+ clockOut + ", middleTime=" + middleTime + "]";
	}
	
	
	

}
