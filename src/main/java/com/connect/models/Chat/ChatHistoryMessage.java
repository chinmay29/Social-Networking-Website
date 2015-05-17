package com.connect.models.Chat;

import java.util.Date;

public class ChatHistoryMessage {

	private long msgId;
	private String msgText;
	private Date msgTime;
	private long chatId;
	private int fromUserId;
	private int toUserId;
	
	public ChatHistoryMessage() {
	}

	public ChatHistoryMessage(long msgId, String msgText, Date msgTime,
			long chatId, int fromUserId, int toUserId) {
		this.msgId = msgId;
		this.msgText = msgText;
		this.msgTime = msgTime;
		this.chatId = chatId;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
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

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	
}
