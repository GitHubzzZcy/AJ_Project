package com.henghao.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 责令限期整改指令书实体
 *@author: zcy
 *@date： 2017-9-21 下午4:58:11
 *@version 1.0
 */
@Entity
@Table(name="aj_writ_orderchange")
public class OrderChangeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int wtid;
    private String checkUnit;       //被检查单位
    private String checkYinhuanList;  //检查隐患的集合 里面显示描述信息
    private String checkNum;        //责令整改的第几项
    private String checkTime;       //整改时间
    private String governmentName1;  //政府名字
    private String mechanism;        //什么机构
    private String courtName;        //法院名字
    private String checkPeople1;     //检查执法人员1
    private String documentsId1;    //证件号码1
    private String checkPeople2;     //检查执法人员2
    private String documentsId2;    //证件号码2
    private String beCheckedPeople; //被检查负责人
    private String recordingTime;   //文书记录时间
    
    
	public OrderChangeEntity() {
		super();
	}
	public OrderChangeEntity(int wtid, String checkUnit,
			String checkYinhuanList, String checkNum, String checkTime,
			String governmentName1, String mechanism, String courtName,
			String checkPeople1, String documentsId1, String checkPeople2,
			String documentsId2, String beCheckedPeople, String recordingTime) {
		super();
		this.wtid = wtid;
		this.checkUnit = checkUnit;
		this.checkYinhuanList = checkYinhuanList;
		this.checkNum = checkNum;
		this.checkTime = checkTime;
		this.governmentName1 = governmentName1;
		this.mechanism = mechanism;
		this.courtName = courtName;
		this.checkPeople1 = checkPeople1;
		this.documentsId1 = documentsId1;
		this.checkPeople2 = checkPeople2;
		this.documentsId2 = documentsId2;
		this.beCheckedPeople = beCheckedPeople;
		this.recordingTime = recordingTime;
	}
	public int getWtid() {
		return wtid;
	}
	public void setWtid(int wtid) {
		this.wtid = wtid;
	}
	public String getCheckUnit() {
		return checkUnit;
	}
	public void setCheckUnit(String checkUnit) {
		this.checkUnit = checkUnit;
	}
	public String getCheckYinhuanList() {
		return checkYinhuanList;
	}
	public void setCheckYinhuanList(String checkYinhuanList) {
		this.checkYinhuanList = checkYinhuanList;
	}
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getGovernmentName1() {
		return governmentName1;
	}
	public void setGovernmentName1(String governmentName1) {
		this.governmentName1 = governmentName1;
	}
	public String getMechanism() {
		return mechanism;
	}
	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	public String getCourtName() {
		return courtName;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public String getCheckPeople1() {
		return checkPeople1;
	}
	public void setCheckPeople1(String checkPeople1) {
		this.checkPeople1 = checkPeople1;
	}
	public String getDocumentsId1() {
		return documentsId1;
	}
	public void setDocumentsId1(String documentsId1) {
		this.documentsId1 = documentsId1;
	}
	public String getCheckPeople2() {
		return checkPeople2;
	}
	public void setCheckPeople2(String checkPeople2) {
		this.checkPeople2 = checkPeople2;
	}
	public String getDocumentsId2() {
		return documentsId2;
	}
	public void setDocumentsId2(String documentsId2) {
		this.documentsId2 = documentsId2;
	}
	public String getBeCheckedPeople() {
		return beCheckedPeople;
	}
	public void setBeCheckedPeople(String beCheckedPeople) {
		this.beCheckedPeople = beCheckedPeople;
	}
	public String getRecordingTime() {
		return recordingTime;
	}
	public void setRecordingTime(String recordingTime) {
		this.recordingTime = recordingTime;
	}
	

    
    
}
