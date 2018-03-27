package com.henghao.news.entity;

/**
 * 封装资料不全返回
 * @author admin
 *
 */
public class DetailsEntity {

	private String id;
	private String itemType;//受理事项
	private String firmName;	//企业名字
	private String disposeDept; //受理部门
	private String disposeUserName; //受理人
	private String disposeTime;  //受理时间
	private String errorType;
	private String errorDesc;
	private String createDate;
	
	
	
	
	public DetailsEntity() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public String getDisposeDept() {
		return disposeDept;
	}
	public void setDisposeDept(String disposeDept) {
		this.disposeDept = disposeDept;
	}
	public String getDisposeUserName() {
		return disposeUserName;
	}
	public void setDisposeUserName(String disposeUserName) {
		this.disposeUserName = disposeUserName;
	}
	public String getDisposeTime() {
		return disposeTime;
	}
	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
}
