package com.henghao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 文件保存信息
 * @author Administrator
 *
 */
@Entity
@Table(name="report_information_file")
public class ReportInformationFile {
	
	/**
	 * id
	 */
	private Integer ID;
	/**
	 * 文件编码
	 */
	private String TOKEN;
	/**
	 * 保存名称
	 */
	private String SAVE_NAME;
	/**
	 * 文件名称
	 */
	private String FILENAME;
	/**
	 *  文件类型
	 */
	private String FILETYPE;
	/**
	 * 保存时间
	 */
	private String SAVE_TIME;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID",unique=true,nullable =false)
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	@Column(name = "TOKEN",nullable =true)
	public String getTOKEN() {
		return TOKEN;
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}
	@Column(name = "SAVE_NAME",nullable =true)
	public String getSAVE_NAME() {
		return SAVE_NAME;
	}

	public void setSAVE_NAME(String sAVE_NAME) {
		SAVE_NAME = sAVE_NAME;
	}

	@Column(name = "FILENAME",nullable =true)
	public String getFILENAME() {
		return FILENAME;
	}

	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}

	@Column(name = "FILETYPE",nullable =true)
	public String getFILETYPE() {
		return FILETYPE;
	}

	public void setFILETYPE(String fILETYPE) {
		FILETYPE = fILETYPE;
	}
	
	@Column(name = "SAVE_TIME",nullable =true)
	public String getSAVE_TIME() {
		return SAVE_TIME;
	}

	public void setSAVE_TIME(String sAVE_TIME) {
		SAVE_TIME = sAVE_TIME;
	}

	
	
}
