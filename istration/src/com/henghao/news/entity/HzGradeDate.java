package com.henghao.news.entity;

/**
 * 工作流评分数据录入
 * @Description:
 * @author zcy  
 * @date 2017年12月5日 下午10:42:33
 * @version 1.0
 */
public class HzGradeDate {

	private String ID;
	private String TITLE;
	private String Created; //录入时间
	private String Creator;//录入人
	private String PEOPLE; //人员
	private String TIME; //发生时间
	private String SM; //说明
	private String XX;//选项类别
	private String FZ;
	
	
	
	public HzGradeDate() {
		super();
	}



	public HzGradeDate(String iD, String tITLE, String created, String creator, String pEOPLE, String tIME, String sM,
			String xX, String fZ) {
		super();
		ID = iD;
		TITLE = tITLE;
		Created = created;
		Creator = creator;
		PEOPLE = pEOPLE;
		TIME = tIME;
		SM = sM;
		XX = xX;
		FZ = fZ;
	}



	public String getID() {
		return ID;
	}



	public void setID(String iD) {
		ID = iD;
	}



	public String getTITLE() {
		return TITLE;
	}



	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}



	public String getCreated() {
		return Created;
	}



	public void setCreated(String created) {
		Created = created;
	}



	public String getCreator() {
		return Creator;
	}



	public void setCreator(String creator) {
		Creator = creator;
	}



	public String getPEOPLE() {
		return PEOPLE;
	}



	public void setPEOPLE(String pEOPLE) {
		PEOPLE = pEOPLE;
	}



	public String getTIME() {
		return TIME;
	}



	public void setTIME(String tIME) {
		TIME = tIME;
	}



	public String getSM() {
		return SM;
	}



	public void setSM(String sM) {
		SM = sM;
	}



	public String getXX() {
		return XX;
	}



	public void setXX(String xX) {
		XX = xX;
	}



	public String getFZ() {
		return FZ;
	}



	public void setFZ(String fZ) {
		FZ = fZ;
	}
	
}
