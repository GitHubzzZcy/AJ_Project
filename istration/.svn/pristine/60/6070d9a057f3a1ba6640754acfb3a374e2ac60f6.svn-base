package com.henghao.news.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * 执法计划
 *@author: zcy
 *@date： 2017-9-15 下午5:29:21
 *@version 1.0
 */
@Entity
@Table(name="aj_azhifaplan")
public class Plan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int pid;
	private String enterpriseid;		//检查企业的id
    private String checkPeople1;        //检查人员1  也就是系统登录人员
    private String checkPeople2;        //检查人员2  也就是被选中的执法人员
    private String checkTime;           //计划检查时间
    private Date createPlanTime;     //创建计划时间
   
    private String checkSite;           //检查现场
    private String siteResponse;        //企业现场负责人
    private String siteImagePath;       //巡查现场合照
    private Date finishTime;    //实际检查时间
    @Column(nullable=false, columnDefinition="INT default 0") 
    private Integer resultStatus; //检查结果处理状态 默认0：我要检查列表， 1：无隐患归档 ，2：转到复查，3：转到调查取证 ，4：
    @Transient
    private Troopemp troopemp; //执法队伍人员表
    @Transient
    private Enterprise enterprise; //企业信息实体类
    //  现在是一方维护多方了，主控权交给了一方，设置级联，一方要设置懒加载，推荐！
//  @OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER)
//  @JoinColumn(name="pid") // 设置一方的外键，这里是cid，因为实际上这个外键还是加在多方，只不过控制权变了。
    @Transient
    private Set<JianchaMaterialEntity> jianchaMaterialEntityList;
    //关联执法文书定义
    @Transient
    private Set<DocTypeDefine> docTypeDefineList;
    
    
    
    public Plan() {
	}

	public Plan(int pid, String enterpriseid, String checkPeople1,
			String checkPeople2, String checkTime, Date createPlanTime,
			String checkSite, String siteResponse, String siteImagePath,
			Date finishTime, Troopemp troopemp, Enterprise enterprise,
			Set<JianchaMaterialEntity> jianchaMaterialEntityList,
			Set<DocTypeDefine> docTypeDefineList, Integer resultStatus) {
		super();
		this.pid = pid;
		this.enterpriseid = enterpriseid;
		this.checkPeople1 = checkPeople1;
		this.checkPeople2 = checkPeople2;
		this.checkTime = checkTime;
		this.createPlanTime = createPlanTime;
		this.checkSite = checkSite;
		this.siteResponse = siteResponse;
		this.siteImagePath = siteImagePath;
		this.finishTime = finishTime;
		this.troopemp = troopemp;
		this.enterprise = enterprise;
		this.jianchaMaterialEntityList = jianchaMaterialEntityList;
		this.docTypeDefineList = docTypeDefineList;
		this.resultStatus = resultStatus;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getEnterpriseid() {
		return enterpriseid;
	}

	public void setEnterpriseid(String enterpriseid) {
		this.enterpriseid = enterpriseid;
	}

	public String getCheckPeople1() {
		return checkPeople1;
	}

	public void setCheckPeople1(String checkPeople1) {
		this.checkPeople1 = checkPeople1;
	}

	public String getCheckPeople2() {
		return checkPeople2;
	}

	public void setCheckPeople2(String checkPeople2) {
		this.checkPeople2 = checkPeople2;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCreatePlanTime() {
		return createPlanTime;
	}

	public void setCreatePlanTime(Date createPlanTime) {
		this.createPlanTime = createPlanTime;
	}

	public String getCheckSite() {
		return checkSite;
	}

	public void setCheckSite(String checkSite) {
		this.checkSite = checkSite;
	}

	public String getSiteResponse() {
		return siteResponse;
	}

	public void setSiteResponse(String siteResponse) {
		this.siteResponse = siteResponse;
	}

	public String getSiteImagePath() {
		return siteImagePath;
	}

	public void setSiteImagePath(String siteImagePath) {
		this.siteImagePath = siteImagePath;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Troopemp getTroopemp() {
		return troopemp;
	}

	public void setTroopemp(Troopemp troopemp) {
		this.troopemp = troopemp;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Set<JianchaMaterialEntity> getJianchaMaterialEntityList() {
		return jianchaMaterialEntityList;
	}

	public void setJianchaMaterialEntityList(
			Set<JianchaMaterialEntity> jianchaMaterialEntityList) {
		this.jianchaMaterialEntityList = jianchaMaterialEntityList;
	}

	public Set<DocTypeDefine> getDocTypeDefineList() {
		return docTypeDefineList;
	}

	public void setDocTypeDefineList(Set<DocTypeDefine> docTypeDefineList) {
		this.docTypeDefineList = docTypeDefineList;
	}

	public Integer getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(Integer resultStatus) {
		this.resultStatus = resultStatus;
	}

	@Override
	public String toString() {
		return "Plan [pid=" + pid + ", enterpriseid=" + enterpriseid
				+ ", checkPeople1=" + checkPeople1 + ", checkPeople2="
				+ checkPeople2 + ", checkTime=" + checkTime
				+ ", createPlanTime=" + createPlanTime + ", checkSite="
				+ checkSite + ", siteResponse=" + siteResponse
				+ ", siteImagePath=" + siteImagePath + ", finishTime="
				+ finishTime + ", troopemp=" + troopemp + ", enterprise="
				+ enterprise + ", jianchaMaterialEntityList="
				+ jianchaMaterialEntityList + ", docTypeDefineList="
				+ docTypeDefineList + ", resultStatus=" + resultStatus + "]";
	}
    
	
}
