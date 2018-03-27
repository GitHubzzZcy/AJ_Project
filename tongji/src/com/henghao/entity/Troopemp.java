package com.henghao.entity;

import java.math.BigDecimal;

/**
 * 执法队伍人员表
 * @author admin
 *
 */
public class Troopemp {
	
	private String ID;
	private String NAME;
	private String TROOPNAME;
	private String CIDNO;
	private BigDecimal ROLE;
	private String PHONE;
	private String LOGINID;
	private String PASSWORD;
	
	
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
	
	public String getTROOPNAME() {
		return TROOPNAME;
	}
	public void setTROOPNAME(String tROOPNAME) {
		TROOPNAME = tROOPNAME;
	}
	public String getCIDNO() {
		return CIDNO;
	}
	public void setCIDNO(String cIDNO) {
		CIDNO = cIDNO;
	}
	public BigDecimal getROLE() {
		return ROLE;
	}
	public void setROLE(BigDecimal rOLE) {
		ROLE = rOLE;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getLOGINID() {
		return LOGINID;
	}
	public void setLOGINID(String lOGINID) {
		LOGINID = lOGINID;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	
	
}
