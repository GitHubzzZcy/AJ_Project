package com.henghao.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 强制措施决定文书
 *@author: zcy
 *@date： 2017-9-25 上午10:48:22
 *@version 1.0
 */

@Entity
@Table(name="aj_writ_forcemeasures")
public class ForceMeasuresEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int wtid;
    private String checkUnit;       //被检查单位
    private String checkYinhuanList;  //检查隐患的集合 里面显示描述信息
    private String measuresBasics;      //强制措施依据
    private String measures;            //强制措施
    private String governmentName1;  //政府名字
    private String mechanism;        //什么机构
    private String courtName;        //法院名字
    private String recordingTime;   //文书记录时间
    
    
    
	public ForceMeasuresEntity() {
		super();
	}
	public ForceMeasuresEntity(int wtid, String checkUnit,
			String checkYinhuanList, String measuresBasics, String measures,
			String governmentName1, String mechanism, String courtName,
			String recordingTime) {
		super();
		this.wtid = wtid;
		this.checkUnit = checkUnit;
		this.checkYinhuanList = checkYinhuanList;
		this.measuresBasics = measuresBasics;
		this.measures = measures;
		this.governmentName1 = governmentName1;
		this.mechanism = mechanism;
		this.courtName = courtName;
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
	public String getMeasuresBasics() {
		return measuresBasics;
	}
	public void setMeasuresBasics(String measuresBasics) {
		this.measuresBasics = measuresBasics;
	}
	public String getMeasures() {
		return measures;
	}
	public void setMeasures(String measures) {
		this.measures = measures;
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
	public String getRecordingTime() {
		return recordingTime;
	}
	public void setRecordingTime(String recordingTime) {
		this.recordingTime = recordingTime;
	}
	@Override
	public String toString() {
		return "ForceMeasuresEntity [wtid=" + wtid + ", checkUnit=" + checkUnit
				+ ", checkYinhuanList=" + checkYinhuanList
				+ ", measuresBasics=" + measuresBasics + ", measures="
				+ measures + ", governmentName1=" + governmentName1
				+ ", mechanism=" + mechanism + ", courtName=" + courtName
				+ ", recordingTime=" + recordingTime + "]";
	}

    
    
}