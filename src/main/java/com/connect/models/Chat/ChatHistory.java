package com.connect.models.Chat;

public class ChatHistory {
	
	private long chatId;
	private long msgId;
	private int fromUserId;
	private int toUserId;
	
	public ChatHistory() {
		
	}

	public ChatHistory(long chatId, long msgId, int fromUserId, int toUserId) {
		this.chatId = chatId;
		this.msgId = msgId;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
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
