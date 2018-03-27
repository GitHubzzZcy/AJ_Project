package com.henghao.istration.entity;

/**
 * 审批节点时间，审批次数统计
 * @author 周承耀
 *
 */
public class Logtime {

	private String title;
	private String approveTime;
	private Integer time;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	
}
