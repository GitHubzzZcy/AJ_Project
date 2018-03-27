package com.henghao.entity;
/**
 * 异常率
 * @author 龙宏
 *
 */
public class AbnormalRate {
	/**
	 * 企业检查次数
	 */
	private String checkNum;
	/**
	 * 企业复查次数
	 */
	private String reviewNum;
	/**
	 * 企业下令整改次数
	 */
	private String rectificationNum;
	/**
	 * 企业处罚次数
	 */
	private String punishNum;
	public String getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
	public String getReviewNum() {
		return reviewNum;
	}
	public void setReviewNum(String reviewNum) {
		this.reviewNum = reviewNum;
	}
	public String getRectificationNum() {
		return rectificationNum;
	}
	public void setRectificationNum(String rectificationNum) {
		this.rectificationNum = rectificationNum;
	}
	public String getPunishNum() {
		return punishNum;
	}
	public void setPunishNum(String punishNum) {
		this.punishNum = punishNum;
	}
	

}
