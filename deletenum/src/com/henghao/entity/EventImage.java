package com.henghao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 图片保存信息   ------>该类已注销实体信息在UserEventTrackEntity类
 * @author Administrator
 *
 */
@Entity
@Table(name="aj_eventimag")
public class EventImage {
	
	/**
	 * id
	 */
	private Integer ID;
	/**
	 * 事件编码
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
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	public String getTOKEN() {
		return TOKEN;
	}

	public void setTOKEN(String tOKEN) {
		TOKEN = tOKEN;
	}

	public String getSAVE_NAME() {
		return SAVE_NAME;
	}

	public void setSAVE_NAME(String sAVE_NAME) {
		SAVE_NAME = sAVE_NAME;
	}

	public String getFILENAME() {
		return FILENAME;
	}

	public void setFILENAME(String fILENAME) {
		FILENAME = fILENAME;
	}

	public String getFILETYPE() {
		return FILETYPE;
	}

	public void setFILETYPE(String fILETYPE) {
		FILETYPE = fILETYPE;
	}

	public String getSAVE_TIME() {
		return SAVE_TIME;
	}

	public void setSAVE_TIME(String sAVE_TIME) {
		SAVE_TIME = sAVE_TIME;
	}

	
	
}
