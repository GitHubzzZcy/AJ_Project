package com.henghao.news.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 执法计划检查项
 *@author: zcy
 *@date： 2017-9-15 下午5:28:54
 *@version 1.0
 */
@Entity
@Table(name="aj_aexamineitem")
public class JianchaMaterialEntity {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cid;
	private int pid;  //10月16号修改不做外键关联上传服务器，但其他业务逻辑还未修改
	private String title;
    private String descript;
	
    private String findTime;    //发现时间
    private String checkDegree; //隐患级别
    private String checkPosition;   //隐患部位
    private String checkDescript;   //隐患描述
    private String selectImagePath;   //隐患图片
    @JsonIgnore//json序列化时忽略该字段
    private Integer status;    //状态信息：默认“空”符合，1为有隐患。
	public JianchaMaterialEntity() {
	}
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getFindTime() {
		return findTime;
	}
	public void setFindTime(String findTime) {
		this.findTime = findTime;
	}
	public String getCheckDegree() {
		return checkDegree;
	}
	public void setCheckDegree(String checkDegree) {
		this.checkDegree = checkDegree;
	}
	public String getCheckPosition() {
		return checkPosition;
	}
	public void setCheckPosition(String checkPosition) {
		this.checkPosition = checkPosition;
	}
	public String getCheckDescript() {
		return checkDescript;
	}
	public void setCheckDescript(String checkDescript) {
		this.checkDescript = checkDescript;
	}
	
	public String getSelectImagePath() {
		return selectImagePath;
	}
	public void setSelectImagePath(String selectImagePath) {
		this.selectImagePath = selectImagePath;
	}
	@Override
	public String toString() {
		return "[cid=" + cid + ", title=" + title
				+ ", descript=" + descript + ", findTime=" + findTime
				+ ", checkDegree=" + checkDegree + ", checkPosition="
				+ checkPosition + ", checkDescript=" + checkDescript
				+ ", selectImagePath=" + selectImagePath + "]";
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
}
