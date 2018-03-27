package com.henghao.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 人员位置历史记录实体类
 * @类功能说明：  
 * @类修改者：  
 * @修改日期：  
 * @修改说明：  
 * @公司名称：恒昊  
 * @作者： 宁万龙
 * @创建时间：2017-6-15 下午3:45:01  
 * @版本：V1.0
 */
@Entity
@Table(name="user_position_log")
public class User_position_log {
	@Id
	@GeneratedValue(generator="system_uuid")
	@GenericGenerator(name="system_uuid",strategy="uuid") 
	private String id;
	//时间(年月日时分秒)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ctime;
	//用户id
	private String uid;
	//姓名
	private String name;
	//经度
	private double longitude;
	//纬度
	private double latitude;
	//地址位置中文
	private String address;

	public User_position_log() {
		super();
	}
	
	
	
	public Date getCtime() {
		return ctime;
	}



	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "User_position_log [id=" + id + ", ctime=" + ctime + ", uid="
				+ uid + ", name=" + name + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", address=" + address + "]";
	}



	public User_position_log(Date ctime, String uid, String name,
			double longitude, double latitude, String address) {
		super();
		this.ctime = ctime;
		this.uid = uid;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}



	public User_position_log(String id, Date ctime, String uid, String name,
			double longitude, double latitude, String address) {
		super();
		this.id = id;
		this.ctime = ctime;
		this.uid = uid;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
