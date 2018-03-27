package com.henghao.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @date 2017-5-27 ----创建时间
 * 
 *       <pre>
 * Title:  被执法单位信息类
 * Description:  查询各被执法单位有关情况的包装类
 * Company:  贵州恒昊软件科技有限公司
 * </pre>
 * @author 王章鹏
 */
public class SelectedEntVo {
	// 企業ID
	// private String ENTERPRISEID;
	// 企业名称
	private String ENTNAME;
	// 工商注册号
	private String REGNUM;
	// 省/自治区/直辖市
	private String DISTRICT1;
	// 地市
	private String DISTRICT2;
	// 区县
	private String DISTRICT3;
	// 乡镇
	private String DISTRICT4;
	// 行业门类
	private String INDUSTRY1;
	// 企业状态（0：销户 1：正常 2：未审核3:审核未通过）
	private BigDecimal STATE;
	// 规模(1:大型，2：中型，3：小型，4：微型)
	private String SCALE;
	// 注册地址
	private String REGADDRESS;
	// 成立日期
	private String REGTIME;
	// 经营范围
	private String BUSINESSCOPE;
	// 法定代表人
	private String LEGALPEOPLE;
	// 联系人
	private String LINKMAN;
	// 联系人办公电话
	private String LINKTELEPHONE;
	// 生产经营场所面积
	private BigDecimal SCJYCSMJ;

	// get and set
	/*
	 * public String getENTERPRISEID() { return ENTERPRISEID; } public void
	 * setENTERPRISEID(String eNTERPRISEID) { ENTERPRISEID = eNTERPRISEID; }
	 */
	public String getENTNAME() {
		return ENTNAME;
	}

	public void setENTNAME(String eNTNAME) {
		ENTNAME = eNTNAME;
	}

	public String getREGNUM() {
		return REGNUM;
	}

	public void setREGNUM(String rEGNUM) {
		REGNUM = rEGNUM;
	}

	public String getDISTRICT1() {
		return DISTRICT1;
	}

	public void setDISTRICT1(String dISTRICT1) {
		DISTRICT1 = dISTRICT1;
	}

	public String getDISTRICT2() {
		return DISTRICT2;
	}

	public void setDISTRICT2(String dISTRICT2) {
		DISTRICT2 = dISTRICT2;
	}

	public String getDISTRICT3() {
		return DISTRICT3;
	}

	public void setDISTRICT3(String dISTRICT3) {
		DISTRICT3 = dISTRICT3;
	}

	public String getDISTRICT4() {
		return DISTRICT4;
	}

	public void setDISTRICT4(String dISTRICT4) {
		DISTRICT4 = dISTRICT4;
	}

	public String getINDUSTRY1() {
		return INDUSTRY1;
	}

	public void setINDUSTRY1(String iNDUSTRY1) {
		INDUSTRY1 = iNDUSTRY1;
	}

	public BigDecimal getSTATE() {
		return STATE;
	}

	public void setSTATE(BigDecimal sTATE) {
		STATE = sTATE;
	}

	public String getSCALE() {
		return SCALE;
	}

	public void setSCALE(String sCALE) {
		SCALE = sCALE;
	}

	public String getREGADDRESS() {
		return REGADDRESS;
	}

	public void setREGADDRESS(String rEGADDRESS) {
		REGADDRESS = rEGADDRESS;
	}

	public String getREGTIME() {
		return REGTIME;
	}

	public void setREGTIME(Date rEGTIME) {
		REGTIME = rEGTIME.toString();
	}

	public String getBUSINESSCOPE() {
		return BUSINESSCOPE;
	}

	public void setBUSINESSCOPE(String bUSINESSCOPE) {
		BUSINESSCOPE = bUSINESSCOPE;
	}

	public String getLEGALPEOPLE() {
		return LEGALPEOPLE;
	}

	public void setLEGALPEOPLE(String lEGALPEOPLE) {
		LEGALPEOPLE = lEGALPEOPLE;
	}

	public String getLINKMAN() {
		return LINKMAN;
	}

	public void setLINKMAN(String lINKMAN) {
		LINKMAN = lINKMAN;
	}

	public String getLINKTELEPHONE() {
		return LINKTELEPHONE;
	}

	public void setLINKTELEPHONE(String lINKTELEPHONE) {
		LINKTELEPHONE = lINKTELEPHONE;
	}

	public BigDecimal getSCJYCSMJ() {
		return SCJYCSMJ;
	}

	public void setSCJYCSMJ(BigDecimal sCJYCSMJ) {
		SCJYCSMJ = sCJYCSMJ;
	}

}