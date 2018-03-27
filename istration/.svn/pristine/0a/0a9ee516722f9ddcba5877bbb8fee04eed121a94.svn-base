package com.henghao.news.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * 消息定义类
 * @author admin
 *
 */
@Entity
@Table(name="aj_meeting_message")
public class JPushToUser {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cid;
	private Integer mid;
	private String uid; //消息接收人id
	private Integer isRead; //0 未查看 ，1已查看
	private Integer type; //1 代表发给领导审批，2 审批不通过发给自己， 3代表通知大家开会， 4是等待审批结果
	private long msg_id;  //推送消息唯一标示
	private String messageTitle;    //消息头	
    private String messageContent;	//消息内容
    
    private String messageSendTime; //发起会议审批时间
    private String messageSendPeople; //发起会议人名字
    
	
	
	public JPushToUser() {
		super();
	}









	public JPushToUser(Integer cid, Integer mid, String uid, Integer isRead, Integer type, long msg_id,
			String messageTitle, String messageContent, String messageSendTime, String messageSendPeople) {
		super();
		this.cid = cid;
		this.mid = mid;
		this.uid = uid;
		this.isRead = isRead;
		this.type = type;
		this.msg_id = msg_id;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
		this.messageSendTime = messageSendTime;
		this.messageSendPeople = messageSendPeople;
	}









	









	public Integer getMid() {
		return mid;
	}






	public void setMid(Integer mid) {
		this.mid = mid;
	}






	public String getMessageSendTime() {
		return messageSendTime;
	}






	public void setMessageSendTime(String messageSendTime) {
		this.messageSendTime = messageSendTime;
	}






	public String getMessageSendPeople() {
		return messageSendPeople;
	}






	public void setMessageSendPeople(String messageSendPeople) {
		this.messageSendPeople = messageSendPeople;
	}






	public Integer getCid() {
		return cid;
	}



	public void setCid(Integer cid) {
		this.cid = cid;
	}



	public String getUid() {
		return uid;
	}



	public void setUid(String uid) {
		this.uid = uid;
	}



	public Integer getIsRead() {
		return isRead;
	}



	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public long getMsg_id() {
		return msg_id;
	}



	public void setMsg_id(long msg_id) {
		this.msg_id = msg_id;
	}



	public String getMessageTitle() {
		return messageTitle;
	}



	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}



	public String getMessageContent() {
		return messageContent;
	}



	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}



}
