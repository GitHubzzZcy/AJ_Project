package com.henghao.istration.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆实时信息实体类
 * @author 周承耀
 *
 */

@Entity
@Table(name="aj_CarRealtime")
public class CarRealtime implements Serializable{
	
	private static final long serialVersionUID = -8422864249923539171L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String workid;
	
	private String carNumber;
	//经度
	private String longitude;
	//纬度
	private String latitude;
	//时间
	private Date carTime;
	//我的时间
	private Date myTime;
	//年月日时间
	private String YTD;
	
	private String site;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkid() {
		return workid;
	}
	public void setWorkid(String workid) {
		this.workid = workid;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Date getCarTime() {
		return carTime;
	}
	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}
	public Date getMyTime() {
		return myTime;
	}
	public void setMyTime(Date myTime) {
		this.myTime = myTime;
	}
	public String getYTD() {
		return YTD;
	}
	public void setYTD(String yTD) {
		YTD = yTD;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	
}
