package com.henghao.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 执法文书信息关联定义表
 *@author: zcy
 *@date： 2017-9-21 上午11:05:15
 *@version 1.0
 */
@Entity
@Table(name="aj_writ_doctypedefine")
public class DocTypeDefine {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int dtid;
	private String docTypeName; //文书名称
	private int pid; //计划id
	private int wtid;  //文书id
	private String dataTableName;  //文书对应表名
	private String entityClass; //文书对应实体类
	private String htmlUrl;    //文书对应的web页面
	
	
	
	
	public DocTypeDefine() {
		super();
	}




	public DocTypeDefine(int dtid, String docTypeName, int pid, int wtid,
			String dataTableName, String entityClass, String htmlUrl) {
		super();
		this.dtid = dtid;
		this.docTypeName = docTypeName;
		this.pid = pid;
		this.wtid = wtid;
		this.dataTableName = dataTableName;
		this.entityClass = entityClass;
		this.htmlUrl = htmlUrl;
	}




	public int getDtid() {
		return dtid;
	}




	public void setDtid(int dtid) {
		this.dtid = dtid;
	}




	public String getDocTypeName() {
		return docTypeName;
	}




	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}




	public int getPid() {
		return pid;
	}




	public void setPid(int pid) {
		this.pid = pid;
	}




	




	public int getWtid() {
		return wtid;
	}




	public void setWtid(int wtid) {
		this.wtid = wtid;
	}




	public String getDataTableName() {
		return dataTableName;
	}




	public void setDataTableName(String dataTableName) {
		this.dataTableName = dataTableName;
	}




	public String getEntityClass() {
		return entityClass;
	}




	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}




	public String getHtmlUrl() {
		return htmlUrl;
	}




	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	
}
