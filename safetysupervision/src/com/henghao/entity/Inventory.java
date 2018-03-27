package com.henghao.entity;

/**
 * 督责问责下 权责清单实体
 * @Description:
 * @author zcy  
 * @date 2018年3月26日 下午3:50:38
 * @version 1.0
 */
public class Inventory {

		//	类别
	private String ZFLB;
		//时间
	private String TIME;
		//执法人员
	private String ZFRY;
		//	部门
	private String ZFBM;
	//处罚情况
	private String SM;
	//法律依据
	private String  FLYJ;
	
	
	public String getZFLB() {
		return ZFLB;
	}
	public void setZFLB(String zFLB) {
		ZFLB = zFLB;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getZFRY() {
		return ZFRY;
	}
	public void setZFRY(String zFRY) {
		ZFRY = zFRY;
	}
	public String getZFBM() {
		return ZFBM;
	}
	public void setZFBM(String zFBM) {
		ZFBM = zFBM;
	}
	public String getSM() {
		return SM;
	}
	public void setSM(String sM) {
		SM = sM;
	}
	public String getFLYJ() {
		return FLYJ;
	}
	public void setFLYJ(String fLYJ) {
		FLYJ = fLYJ;
	}
	public Inventory() {
		super();
	}
	
	
	
	
}
