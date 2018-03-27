package com.henghao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 休息日类，对应休息日表。包含周末和假日
 * @类功能说明：  
 * @类修改者：  
 * @修改日期：  
 * @修改说明：  
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-5 下午12:44:22  
 * @版本：V1.0
 */
@Entity
@Table(name="Rest_day")
public class Rest_day {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//日期时间
	@Temporal(TemporalType.DATE)
	private Date date;
	//节假日状态周末和节日
	private String status;
	public Date getDate() {
		return date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Rest_day() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rest_day(int id, Date date, String status) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Rest_day [id=" + id + ", date=" + date + ", status=" + status
				+ "]";
	}
	
}
