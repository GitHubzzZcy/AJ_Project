package com.henghao.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 投诉举报实体类
 * @author 周承耀
 *aj_cn_complain
 */
@Entity
@Table(name="aj_cn_complain")
public class Complain implements Serializable{
	
	private static final long serialVersionUID = -2164126232239738057L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//提交投诉时间
	private Date complainTime;
	//举报项类
	private String items;
	//联系方式
	private String nophone;
	//姓名
	private String complainName;
	//通讯地址
	private String address;
	private String executorType; //字段为12月5号新增。举报执法人时选择执法项
	//举报对象
	private String objects;
	//所在地区
	private String  region;
	//详细地址
	private String detailedRegion;
	//举报内容
	private String complainDesc;
	//图片地址
	private String filePath;
	//举报单状态    未查看:默认   同意立案，结案
	private String complainStatus;
	//拒绝立案意见
	private String disposeDesc;
	//反馈意见
	private String feedback;
	//满意度  12345分
	private Integer satisfaction;
	//评价
	private String evaluate;
	@Transient
	private String access_token;
	@Transient
	private String[] serverId;
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Complain [id=" + id + ", complainTime=" + complainTime + ", items=" + items + ", nophone=" + nophone
				+ ", complainName=" + complainName + ", address=" + address + ", executorType=" + executorType
				+ ", objects=" + objects + ", region=" + region + ", detailedRegion=" + detailedRegion
				+ ", complainDesc=" + complainDesc + ", filePath=" + filePath + ", complainStatus=" + complainStatus
				+ ", disposeDesc=" + disposeDesc + ", feedback=" + feedback + ", satisfaction=" + satisfaction
				+ ", evaluate=" + evaluate + ", access_token=" + access_token + ", serverId="
				+ Arrays.toString(serverId) + "]";
	}
	public Complain(int id, Date complainTime, String items, String nophone, String complainName, String address,
			String executorType, String objects, String region, String detailedRegion, String complainDesc,
			String filePath, String complainStatus, String disposeDesc, String feedback, Integer satisfaction,
			String evaluate, String access_token, String[] serverId) {
		super();
		this.id = id;
		this.complainTime = complainTime;
		this.items = items;
		this.nophone = nophone;
		this.complainName = complainName;
		this.address = address;
		this.executorType = executorType;
		this.objects = objects;
		this.region = region;
		this.detailedRegion = detailedRegion;
		this.complainDesc = complainDesc;
		this.filePath = filePath;
		this.complainStatus = complainStatus;
		this.disposeDesc = disposeDesc;
		this.feedback = feedback;
		this.satisfaction = satisfaction;
		this.evaluate = evaluate;
		this.access_token = access_token;
		this.serverId = serverId;
	}
	public Complain() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getComplainTime() {
		return complainTime;
	}
	public void setComplainTime(Date complainTime) {
		this.complainTime = complainTime;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getNophone() {
		return nophone;
	}
	public void setNophone(String nophone) {
		this.nophone = nophone;
	}
	public String getComplainName() {
		return complainName;
	}
	public void setComplainName(String complainName) {
		this.complainName = complainName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getExecutorType() {
		return executorType;
	}
	public void setExecutorType(String executorType) {
		this.executorType = executorType;
	}
	public String getObjects() {
		return objects;
	}
	public void setObjects(String objects) {
		this.objects = objects;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDetailedRegion() {
		return detailedRegion;
	}
	public void setDetailedRegion(String detailedRegion) {
		this.detailedRegion = detailedRegion;
	}
	public String getComplainDesc() {
		return complainDesc;
	}
	public void setComplainDesc(String complainDesc) {
		this.complainDesc = complainDesc;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getComplainStatus() {
		return complainStatus;
	}
	public void setComplainStatus(String complainStatus) {
		this.complainStatus = complainStatus;
	}
	public String getDisposeDesc() {
		return disposeDesc;
	}
	public void setDisposeDesc(String disposeDesc) {
		this.disposeDesc = disposeDesc;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Integer getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String[] getServerId() {
		return serverId;
	}
	public void setServerId(String[] serverId) {
		this.serverId = serverId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
