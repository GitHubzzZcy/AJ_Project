package com.henghao.vo;

/**
 * @explan 查询企业被执法时间包装类
 * @author 王章鹏
 * @time 2017-6-2 上午11:56:09
 */
public class ZFTimeVo {

	// 案件建立日期
	private String CREATEDATE;
	// 第几次执法
	private String frequency;
	
	//get and set
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getCREATEDATE() {
		return CREATEDATE;
	}
	public void setCREATEDATE(String cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}
	
	// 空构造
	public ZFTimeVo() {
		
	}

	@Override
	public String toString() {
		return "CountVo [CREATEDATE=" + CREATEDATE + "]";
	}
	
	
}
