package com.henghao.istration.entity;

import java.util.List;



public class User {

	private String ID;
	private String NAME;
	private String PASSWORD;
	private String headPath;
	private String dept;
	//融合平台权限
	private List<String> jurisdiction;
	
	
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	
	public List<String> getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(List<String> jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	@Override
	public String toString() {
		return "User [ID=" + ID + ", NAME=" + NAME + ", PASSWORD=" + PASSWORD
				+ ", headPath=" + headPath + ", dept=" + dept
				+ ", jurisdiction=" + jurisdiction + "]";
	}
	
	
}
