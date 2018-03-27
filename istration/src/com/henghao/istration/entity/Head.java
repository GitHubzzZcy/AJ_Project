package com.henghao.istration.entity;

/**
 * 头像路径
 * @author 周承耀
 *
 */
public class Head {
	//object_id
	//文件名
	private String SAVE_NAME;
	//文件后缀
	private String EXTENTION;
	//文件路径
	private String SAVE_PATH;
	
	public String getSAVE_NAME() {
		return SAVE_NAME;
	}
	public void setSAVE_NAME(String sAVE_NAME) {
		SAVE_NAME = sAVE_NAME;
	}
	public String getEXTENTION() {
		return EXTENTION;
	}
	public void setEXTENTION(String eXTENTION) {
		EXTENTION = eXTENTION;
	}
	public String getSAVE_PATH() {
		return SAVE_PATH;
	}
	public void setSAVE_PATH(String sAVE_PATH) {
		SAVE_PATH = sAVE_PATH;
	}
	
	
}
