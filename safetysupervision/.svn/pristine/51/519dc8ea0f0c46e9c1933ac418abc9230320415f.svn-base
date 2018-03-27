package com.henghao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 举报信息表实体类
 * @author longhong
 *
 */
@Entity
@Table(name = "report_information")
public class ReportInformation {
	/**
	 * 表ID
	 */
	private Integer id; 
	/**
	 * 举报人姓名
	 */
	private String informantName;
	/**
	 * 举报人身份证
	 */
	private String informantIdCard;
	/**
	 * 举报人联系电话
	 */
	private String informantphone;
	/**
	 * 举报人政治面貌
	 */
	private String informantPoliticalOutlook;
	/**
	 * 举报人地址
	 */
	private String informantAddress;
	/**
	 * 举报人级别
	 */
	private String informantLevel;
	/**
	 * 表编码
	 */
	private String token;
	/**
	 * 被举报人姓名
	 */
	private String informantsName;
	/**
	 * 被举报单位
	 */
	private String informantsCompany;
	/**
	 * 被举报职务
	 */
	private String informantsPosition;
	/**
	 * 被举报地址
	 */
	private String informantsAddress;
	/**
	 * 被举报级别
	 */
	private String informantsLevel;
	/**
	 * 投诉标题
	 */
	private String title;
	/**
	 * 问题类别
	 */
	private String problemCategories;
	/**
	 * 问题细类
	 */
	private String problemClassification;
	/**
	 * 问题类容
	 */
	private String content;
	/**
	 * 案件编码
	 */
	private String code;
	/**
	 * 投诉时间
	 */
	private String time;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id",unique = true,nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="code",nullable = true)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(name ="time",nullable = true)
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Column(name ="informantName",nullable = true)
	public String getInformantName() {
		return informantName;
	}
	public void setInformantName(String informantName) {
		this.informantName = informantName;
	}
	@Column(name="informantIdCard",nullable=true)
	public String getInformantIdCard() {
		return informantIdCard;
	}
	public void setInformantIdCard(String informantIdCard) {
		this.informantIdCard = informantIdCard;
	}
	@Column(name="informantphone",nullable=true)
	public String getInformantphone() {
		return informantphone;
	}
	public void setInformantphone(String informantphone) {
		this.informantphone = informantphone;
	}
	@Column(name="informantPoliticalOutlook",nullable=true)
	public String getInformantPoliticalOutlook() {
		return informantPoliticalOutlook;
	}
	public void setInformantPoliticalOutlook(String informantPoliticalOutlook) {
		this.informantPoliticalOutlook = informantPoliticalOutlook;
	}
	@Column(name="informantAddress",nullable=true)
	public String getInformantAddress() {
		return informantAddress;
	}
	public void setInformantAddress(String informantAddress) {
		this.informantAddress = informantAddress;
	}
	@Column(name="informantLevel",nullable=true)
	public String getInformantLevel() {
		return informantLevel;
	}
	public void setInformantLevel(String informantLevel) {
		this.informantLevel = informantLevel;
	}
	@Column(name="informantsName",nullable=true)
	public String getInformantsName() {
		return informantsName;
	}
	public void setInformantsName(String informantsName) {
		this.informantsName = informantsName;
	}
	@Column(name="informantsCompany",nullable=true)
	public String getInformantsCompany() {
		return informantsCompany;
	}
	public void setInformantsCompany(String informantsCompany) {
		this.informantsCompany = informantsCompany;
	}
	@Column(name="informantsPosition",nullable=true)
	public String getInformantsPosition() {
		return informantsPosition;
	}
	public void setInformantsPosition(String informantsPosition) {
		this.informantsPosition = informantsPosition;
	}
	@Column(name="informantsAddress",nullable=true)
	public String getInformantsAddress() {
		return informantsAddress;
	}
	public void setInformantsAddress(String informantsAddress) {
		this.informantsAddress = informantsAddress;
	}
	@Column(name="informantsLevel",nullable=true)
	public String getInformantsLevel() {
		return informantsLevel;
	}
	public void setInformantsLevel(String informantsLevel) {
		this.informantsLevel = informantsLevel;
	}
	@Column(name = "token",nullable =true)
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Column(name ="title",nullable=false,length = 50)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name ="problemCategories",nullable=true,length = 200)
	public String getProblemCategories() {
		return problemCategories;
	}
	public void setProblemCategories(String problemCategories) {
		this.problemCategories = problemCategories;
	}
	@Column(name ="problemClassification",nullable=true,length = 200)
	public String getProblemClassification() {
		return problemClassification;
	}
	public void setProblemClassification(String problemClassification) {
		this.problemClassification = problemClassification;
	}
	@Column(name ="content",nullable=true,length = 500)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
