package com.henghao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 投诉处理
 * @author longhong
 *
 */
@Entity
@Table(name ="complaint_handling")
public class ComplaintHandling {
	private Integer id;// 表id
	private String code;// 案件编码
	private String examinationResult; //审核审查结果
	private String examinationName;  //审核人
	private String examinationDescribe;  //审核描述
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="id",unique = true,nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="examinationDescribe",nullable = true,length=500)
	public String getExaminationDescribe() {
		return examinationDescribe;
	}
	public void setExaminationDescribe(String examinationDescribe) {
		this.examinationDescribe = examinationDescribe;
	}
	@Column(name ="code",nullable = true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name ="examinationResult",nullable = true)
	public String getExaminationResult() {
		return examinationResult;
	}
	public void setExaminationResult(String examinationResult) {
		this.examinationResult = examinationResult;
	}
	@Column(name ="examinationName",nullable = true)
	public String getExaminationName() {
		return examinationName;
	}
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

}
