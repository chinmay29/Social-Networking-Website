package com.connect.bll.Chat;

import java.util.List;

import com.connect.models.UserMgmt.User;

public interface ChatMessageService {
	
	public void sendMsg(String msgText, int fromUserId, List<User> receipients, long chatId);
	
	public List<User> getFrequentContacts(int userId);
	
}
