package com.henghao.news.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业信息实体类
 * @author admin
 *
 */
public class Enterprise {

	private String enterpriseid;                                                                 
	private String entname;                                                                      
	private String regnum;                                                                       
	private String district1;      //数据字典                                                              
	private String district2;       //数据字典                                                             
	private String district3;       //数据字典                                                             
	private String district4;       //数据字典                                                             
	private BigDecimal hascompany;
	private String pcompanyregcode;                                                              
	private String industry1;            //数据字典                                                        
	private String industry2;            //数据字典                                                                                                                           
	private String department;           //数据字典                                                        
	private String relevel;                //数据字典                                                      
	private String enterprisecode;                                                               
	private BigDecimal state; 
	private Date createtime; 
	private Date checktime; 
	private String checker;                                                                                                                                     
	private String scale;               //数据字典                                                         
	private String regaddress;                                                                   
	private Date regtime; 
	private String employeenum;                                                                                                                              
	private String longitude;                                                                    
	private String latitude;                                                                     
	private String businesscope;                                                                 
	private String post;                                                                         
	private String legalpeople;                                                                  
	private String legalmobilephone;                                                             
	private String linkman;                                                                                                                                    
	private String linkmobilephone;                                                                                                                                
	private String productaddress;                                                               
	private String sczch;                                                                        
	private String officeaddress;                                                                
	private BigDecimal zczb;
	private BigDecimal nyysr;
	private String qyzclx;                                                                       
	private BigDecimal scjycsmj; 
	private String zzjgt;                                                                        
	private String dwpmt;                                                                        
	private String superviseclassify;                                                            
	private BigDecimal isabove;
	private String sdorgan;                                                                      
	private Date updatetime;
	private String isplan;
	public Enterprise() {
		super();
	}
	public String getEnterpriseid() {
		return enterpriseid;
	}
	public void setEnterpriseid(String enterpriseid) {
		this.enterpriseid = enterpriseid;
	}
	public String getEntname() {
		return entname;
	}
	public void setEntname(String entname) {
		this.entname = entname;
	}
	public String getRegnum() {
		return regnum;
	}
	public void setRegnum(String regnum) {
		this.regnum = regnum;
	}
	public String getDistrict1() {
		return district1;
	}
	public void setDistrict1(String district1) {
		this.district1 = district1;
	}
	public String getDistrict2() {
		return district2;
	}
	public void setDistrict2(String district2) {
		this.district2 = district2;
	}
	public String getDistrict3() {
		return district3;
	}
	public void setDistrict3(String district3) {
		this.district3 = district3;
	}
	public String getDistrict4() {
		return district4;
	}
	public void setDistrict4(String district4) {
		this.district4 = district4;
	}
	public BigDecimal getHascompany() {
		return hascompany;
	}
	public void setHascompany(BigDecimal hascompany) {
		this.hascompany = hascompany;
	}
	public String getPcompanyregcode() {
		return pcompanyregcode;
	}
	public void setPcompanyregcode(String pcompanyregcode) {
		this.pcompanyregcode = pcompanyregcode;
	}
	public String getIndustry1() {
		return industry1;
	}
	public void setIndustry1(String industry1) {
		this.industry1 = industry1;
	}
	public String getIndustry2() {
		return industry2;
	}
	public void setIndustry2(String industry2) {
		this.industry2 = industry2;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRelevel() {
		return relevel;
	}
	public void setRelevel(String relevel) {
		this.relevel = relevel;
	}
	public String getEnterprisecode() {
		return enterprisecode;
	}
	public void setEnterprisecode(String enterprisecode) {
		this.enterprisecode = enterprisecode;
	}
	public BigDecimal getState() {
		return state;
	}
	public void setState(BigDecimal state) {
		this.state = state;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getChecktime() {
		return checktime;
	}
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	public String getChecker() {
		return checker;
	}
	public void setChecker(String checker) {
		this.checker = checker;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getRegaddress() {
		return regaddress;
	}
	public void setRegaddress(String regaddress) {
		this.regaddress = regaddress;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	public String getEmployeenum() {
		return employeenum;
	}
	public void setEmployeenum(String employeenum) {
		this.employeenum = employeenum;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getBusinesscope() {
		return businesscope;
	}
	public void setBusinesscope(String businesscope) {
		this.businesscope = businesscope;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getLegalpeople() {
		return legalpeople;
	}
	public void setLegalpeople(String legalpeople) {
		this.legalpeople = legalpeople;
	}
	public String getLegalmobilephone() {
		return legalmobilephone;
	}
	public void setLegalmobilephone(String legalmobilephone) {
		this.legalmobilephone = legalmobilephone;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkmobilephone() {
		return linkmobilephone;
	}
	public void setLinkmobilephone(String linkmobilephone) {
		this.linkmobilephone = linkmobilephone;
	}
	public String getProductaddress() {
		return productaddress;
	}
	public void setProductaddress(String productaddress) {
		this.productaddress = productaddress;
	}
	public String getSczch() {
		return sczch;
	}
	public void setSczch(String sczch) {
		this.sczch = sczch;
	}
	public String getOfficeaddress() {
		return officeaddress;
	}
	public void setOfficeaddress(String officeaddress) {
		this.officeaddress = officeaddress;
	}
	public BigDecimal getZczb() {
		return zczb;
	}
	public void setZczb(BigDecimal zczb) {
		this.zczb = zczb;
	}
	public BigDecimal getNyysr() {
		return nyysr;
	}
	public void setNyysr(BigDecimal nyysr) {
		this.nyysr = nyysr;
	}
	public String getQyzclx() {
		return qyzclx;
	}
	public void setQyzclx(String qyzclx) {
		this.qyzclx = qyzclx;
	}
	public BigDecimal getScjycsmj() {
		return scjycsmj;
	}
	public void setScjycsmj(BigDecimal scjycsmj) {
		this.scjycsmj = scjycsmj;
	}
	public String getZzjgt() {
		return zzjgt;
	}
	public void setZzjgt(String zzjgt) {
		this.zzjgt = zzjgt;
	}
	public String getDwpmt() {
		return dwpmt;
	}
	public void setDwpmt(String dwpmt) {
		this.dwpmt = dwpmt;
	}
	public String getSuperviseclassify() {
		return superviseclassify;
	}
	public void setSuperviseclassify(String superviseclassify) {
		this.superviseclassify = superviseclassify;
	}
	public BigDecimal getIsabove() {
		return isabove;
	}
	public void setIsabove(BigDecimal isabove) {
		this.isabove = isabove;
	}
	public String getSdorgan() {
		return sdorgan;
	}
	public void setSdorgan(String sdorgan) {
		this.sdorgan = sdorgan;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getIsplan() {
		return isplan;
	}
	public void setIsplan(String isplan) {
		this.isplan = isplan;
	}
	
	
	
	
}
