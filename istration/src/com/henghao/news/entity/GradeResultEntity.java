package com.henghao.news.entity;

import java.io.Serializable;

public class GradeResultEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gradeType; //加分或减分
	private String gradeModule; //德能勤绩廉
	private String gradeDesc; //内容
	private String gradeScore; //分值
	private String gradeTime; //时间
	
	
	
	public GradeResultEntity(String gradeType, String gradeDesc, String gradeScore, String gradeTime) {
		super();
		this.gradeType = gradeType;
		this.gradeDesc = gradeDesc;
		this.gradeScore = gradeScore;
		this.gradeTime = gradeTime;
	}
	public GradeResultEntity(String gradeType, String gradeModule, String gradeDesc, String gradeScore,
			String gradeTime) {
		super();
		this.gradeType = gradeType;
		this.gradeModule = gradeModule;
		this.gradeDesc = gradeDesc;
		this.gradeScore = gradeScore;
		this.gradeTime = gradeTime;
	}
	public String getGradeType() {
		return gradeType;
	}
	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}
	public String getGradeModule() {
		return gradeModule;
	}
	public void setGradeModule(String gradeModule) {
		this.gradeModule = gradeModule;
	}
	public String getGradeDesc() {
		return gradeDesc;
	}
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}
	public String getGradeScore() {
		return gradeScore;
	}
	public void setGradeScore(String gradeScore) {
		this.gradeScore = gradeScore;
	}
	public String getGradeTime() {
		return gradeTime;
	}
	public void setGradeTime(String gradeTime) {
		this.gradeTime = gradeTime;
	}
	@Override
	public String toString() {
		return "GradeResultEntity [gradeType=" + gradeType + ", gradeModule=" + gradeModule + ", gradeDesc=" + gradeDesc
				+ ", gradeScore=" + gradeScore + ", gradeTime=" + gradeTime + "]";
	}
	
	
}
