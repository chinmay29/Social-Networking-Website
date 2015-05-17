package com.connect.models.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ChatBox {
	
	private List<Integer> receipients = new ArrayList<Integer>();
	private Stack<ChatMessage> messages = new Stack<ChatMessage>();
	
	public ChatBox() {
	
	}

	public List<Integer> getReceipients() {
		return receipients;
	}

	public Stack<ChatMessage> getMessages() {
		return messages;
	}
	
	public void addReceipient(int userId) {
		receipients.add((Integer) userId);
	}
	
	public void removeReceipient(int userId) {
		receipients.remove((Integer) userId);
	}
	
	public void addChatMessage(ChatMessage chatMsg) {
		messages.push(chatMsg);
	}
	
	public void removeChatMessage(ChatMessage chatMsg) {
		messages.removeElement(chatMsg);
	}
	
	public boolean checkReceipient(int userId) {
		return receipients.contains(userId);
	}
	
	public void resetReceipients() {
		receipients.clear();
	}

}
