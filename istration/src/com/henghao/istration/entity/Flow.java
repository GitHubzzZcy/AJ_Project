package com.henghao.istration.entity;

/**
 * 审批类别统计
 * @author 周承耀
 *
 */
public class Flow {

	private String id;
	private String name;
	private String counts;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	
}
