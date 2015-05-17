package com.connect.models.Chat;

import java.util.Date;

public class ChatMessage {
	
	private long msgId;
	private String msgText;
	private Date msgTime;
	private int fromUserId;
	
	public ChatMessage() {
		
	}

	public ChatMessage(long msgId, String msgText, Date msgTime, int fromUserId) {
		this.msgId = msgId;
		this.msgText = msgText;
		this.msgTime = msgTime;
		this.fromUserId = fromUserId;
	}

	public ChatMessage(String msgText, int fromUserId) {
		this.msgText = msgText;
		this.fromUserId = fromUserId;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	
}
