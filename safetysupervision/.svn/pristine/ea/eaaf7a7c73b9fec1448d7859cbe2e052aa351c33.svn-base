package com.henghao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 无故外出数据存储表
 * @author longhong
 *
 */
@Entity
@Table(name = "without_reason")
public class WithoutReason {

	private Integer id; //表ID
	private String name; // 人名
	private String type; //状态
	private String groupName; //分组
	private String time;  // 时间
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id",unique=true,nullable=false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name",nullable=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "type",nullable=true)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "groupName",nullable=true)
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name = "time",nullable=true)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
