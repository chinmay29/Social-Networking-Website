package com.connect.bll.Chat;

import java.util.List;
import java.util.Map;

import com.connect.models.Chat.ChatHistory;
import com.connect.models.Chat.ChatHistoryMessage;
import com.connect.models.Chat.ChatHistoryUserMessage;
import com.connect.models.UserMgmt.User;

public interface ChatHistoryService {
	
	public List<ChatHistoryMessage> getChatHistoryMessagesForUser(int toUserId);
	
	public Map<Long, List<User>> getChatsForUser(int toUserId);
	
	public List<User> getUsersForChat(long chatId);
	
	public List<ChatHistoryUserMessage> getAllChatHistoryUserMsgsForChatAndUser(long chatId, int userId);
	
	public int deleteChatHistory(ChatHistory chatHistory);
	
}
