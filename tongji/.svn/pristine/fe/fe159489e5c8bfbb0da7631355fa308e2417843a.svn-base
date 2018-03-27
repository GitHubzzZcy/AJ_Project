package com.henghao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 短信校验
 * 如果某一执法信息预警短信已经发送，怎将该执法信息的id持久到数据库
 * @author 周承耀
 *
 */
@Entity
@Table(name="henghao_note")
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	//执法计划id
	private String planId;
	private Date currentTime;
	private Integer numbers;
	
	
	public Note(int id, String planId, Date currentTime, Integer numbers) {
		super();
		Id = id;
		this.planId = planId;
		this.currentTime = currentTime;
		this.numbers = numbers;
	}
	
	public Note() {
		super();
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	public Integer getNumbers() {
		return numbers;
	}
	public void setNumbers(Integer numbers) {
		this.numbers = numbers;
	}
	
	
	
	
	
}
